package scala.api.module.movcta.domain

import cats.effect.IO

trait SaldoCorredorRepository {

  def getSaldoCorredor(nroBanca: Int, nroAgencia: Int, nroCorredor: Int): IO[Option[SaldoCorredor]]
}
