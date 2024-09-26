package scala.api.module.movcta.infraestructure.repository

import cats.effect.IO
import cats.implicits.catsSyntaxTuple2Parallel
import doobie.implicits._

import scala.api.module.movcta.domain.{SaldoCorredor, SaldoCorredorRepository}
import scala.api.module.shared.infraestructure.presistence_doobie.DoobieDbConnection

final class DoobieMySqlMovCtaRepository(db: DoobieDbConnection)
  extends SaldoCorredorRepository {

  def getSaldoCorredor(nroBanca: Int, nroAgencia: Int, nroCorredor: Int): IO[Option[SaldoCorredor]] = {
    db.transactor.use { xa =>
      (
        Querys.totalImportePorCorredor(nroBanca, nroAgencia, nroCorredor).option.transact(xa), // Obtener saldo
        Querys.getDataCorredor(nroBanca, nroAgencia, nroCorredor).option.transact(xa) // Obtener DataCorredor
      ).parMapN { (maybeSaldo, maybeDataCorredor) =>
        for {
          saldo <- maybeSaldo.flatten
          dataCorredor <- maybeDataCorredor.flatten
        } yield SaldoCorredor(dataCorredor, saldo.toDouble)
      }
    }
  }

}
