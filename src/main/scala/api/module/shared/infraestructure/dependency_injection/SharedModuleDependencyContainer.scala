package scala.api.module.shared.infraestructure.dependency_injection

import akka.actor.ActorSystem
import api.module.shared.infraestructure.config.DbConfig

import scala.api.module.shared.domain.Logger
import scala.api.module.shared.infraestructure.logger.ScalaLoggingLogger
import scala.api.module.shared.infraestructure.presistence_doobie.DoobieDbConnection
import scala.concurrent.ExecutionContext

final class SharedModuleDependencyContainer(
                                             actorSystemName: String,
                                             dbConfig: DbConfig
                                           ) {
  implicit val actorSystem: ActorSystem = ActorSystem(actorSystemName)
  val executionContext: ExecutionContext = actorSystem.dispatcher

  val doobieDbConnection: DoobieDbConnection = new DoobieDbConnection(dbConfig)

  val logger: Logger = new ScalaLoggingLogger
}
