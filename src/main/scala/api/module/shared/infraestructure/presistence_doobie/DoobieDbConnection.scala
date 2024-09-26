package scala.api.module.shared.infraestructure.presistence_doobie


import api.module.shared.infraestructure.config.DbConfig
import cats.effect.{IO, Resource}
import doobie._
import doobie.hikari.HikariTransactor

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

case class DoobieDbConnection(dbConfig: DbConfig) {

  implicit val cs: ExecutionContextExecutor = ExecutionContext.global

  val transactor: Resource[IO, HikariTransactor[IO]] = {
    for {
      ce <- ExecutionContexts.fixedThreadPool[IO](10) // our connect EC
      xa <- HikariTransactor.newHikariTransactor[IO](
        dbConfig.driver, // Driver MySQL
        dbConfig.url, // URL de conexión
        dbConfig.user, // usuario de MySQL
        dbConfig.password, // contraseña de MySQL
        ce
      )
    }
    yield xa
  }

  //  val transactor: Aux[IO, Unit] = Transactor.fromDriverManager[IO](
  //    dbConfig.driver,
  //    dbConfig.url,
  //    dbConfig.user,
  //    dbConfig.password
  //  )

  //  def read[T](query: ConnectionIOOps[T]): Future[T] = query.transact(transactor).unsafeToFuture()

}