package api.entry_point

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import api.module.shared.infraestructure.config.DbConfig

import scala.api.entry_point.{EntryPointDependencyContainer, ReadConfig, Routes}
import scala.api.module.movcta.infraestructure.dependency_injection.SaldoModuleDependencyContainer
import scala.api.module.shared.infraestructure.dependency_injection.SharedModuleDependencyContainer
import scala.concurrent.ExecutionContext
import scala.io.StdIn

object ScalaHttpApi extends App {

  println("Leyendo configuracion de la APP")

  private val appConfig = new ReadConfig().execute(args)

  private val actorSystemName: String = appConfig.actorSystemName
  private val host: String            = appConfig.host
  private val port: Int               = appConfig.port
  private val dbConfig: DbConfig      = appConfig.dbConfig

  private val sharedDependencies = new SharedModuleDependencyContainer(actorSystemName, dbConfig)

  implicit val system: ActorSystem                = sharedDependencies.actorSystem
  implicit val executionContext: ExecutionContext = sharedDependencies.executionContext

  private val container = new EntryPointDependencyContainer(
    new SaldoModuleDependencyContainer(sharedDependencies.doobieDbConnection)
  )

  private val routes: Routes = new Routes(container)

  private val bindingFuture = Http().newServerAt(host, port).bind(routes.all)

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
