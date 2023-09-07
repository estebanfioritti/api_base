package api.entry_point

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import api.module.shared.infraestructure.config.DbConfig
import com.typesafe.config.ConfigFactory

import scala.api.entry_point.{EntryPointDependencyContainer, Routes}
import scala.api.module.movcta.infraestructure.dependency_injection.MovCtaModuleDependencyContainer
import scala.api.module.shared.infraestructure.dependency_injection.SharedModuleDependencyContainer
import scala.concurrent.ExecutionContext
import scala.io.StdIn

object ScalaHttpApi {

  def main(args: Array[String]): Unit = {

    val appConfig = ConfigFactory.load("application")
    val serverConfig = ConfigFactory.load("http-server")

    val actorSystemName = appConfig.getString("main-actor-system.name")
    val host = serverConfig.getString("http-server.host")
    val port = serverConfig.getInt("http-server.port")

    val dbConfig = DbConfig(appConfig.getConfig("database"))

    val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig)

    implicit val system: ActorSystem = sharedDependencies.actorSystem
    implicit val executionContext: ExecutionContext = sharedDependencies.executionContext

    val container = new EntryPointDependencyContainer(
      new MovCtaModuleDependencyContainer(sharedDependencies.doobieDbConnection)
    )

    val routes: Routes = new Routes(container)

    val bindingFuture = Http().newServerAt(host, port).bind(routes.all)

    bindingFuture.failed.foreach { t =>
      println(s"Failed to bind to http://$host:$port/:")
      pprint.log(t)
    }

    // let it run until user presses return
    println(s"Server online at http://$host:$port/\nPress RETURN to stop...")
    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => sharedDependencies.actorSystem.terminate())

  }

}
