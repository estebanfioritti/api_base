package scala.api.module.shared.infraestructure.presistence_doobie


import api.module.shared.infraestructure.config.DbConfig
import cats.effect.IO
import doobie.{ConnectionIO, Transactor}
import doobie.syntax.ConnectionIOOps
import doobie.util.transactor.Transactor.Aux

import scala.concurrent.Future

final class DoobieDbConnection(dbConfig: DbConfig) {
  val transactor: Aux[IO, Unit] = Transactor.fromDriverManager[IO](
    dbConfig.driver,
    dbConfig.url,
    dbConfig.user,
    dbConfig.password
  )

  def read[T](query: ConnectionIOOps[T]): Future[T] = query.transact(transactor).unsafeToFuture()

}
