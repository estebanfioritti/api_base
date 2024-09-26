package scala.api.module.movcta.aplication

import cats.effect.unsafe.implicits._

import scala.api.module.movcta.domain.{SaldoCorredor, SaldoCorredorRepository}
import scala.concurrent.Future

class SaldoCorredorSearcher(repository: SaldoCorredorRepository) {

  def getSaldoCorredor(nroBanca: Int, nroAgencia: Int, nroCorredor: Int): Future[Option[SaldoCorredor]] =
    repository.getSaldoCorredor(nroBanca, nroAgencia, nroCorredor).unsafeToFuture()

}

