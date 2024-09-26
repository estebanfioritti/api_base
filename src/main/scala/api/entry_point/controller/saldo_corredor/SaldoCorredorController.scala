package scala.api.entry_point.controller.saldo_corredor

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, onComplete}
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._

import scala.api.module.movcta.aplication.SaldoCorredorSearcher
import scala.util.{Failure, Success}

class SaldoCorredorController(searcher: SaldoCorredorSearcher) {

  def get(nroBanca: Int, nroAgencia: Int, nroCorredor: Int): Route =
    onComplete(searcher.getSaldoCorredor(nroBanca, nroAgencia, nroCorredor)) {
      case Success(Some(saldo)) => complete(saldo)
      case Success(None) => complete(StatusCodes.NotFound, s"No se encontró saldo del corredor $nroBanca $nroAgencia $nroCorredor")
      case Failure(exception) => complete(StatusCodes.InternalServerError, s"Error interno en servidor $exception")
    }


}

