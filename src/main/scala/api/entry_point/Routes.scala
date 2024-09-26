package scala.api.entry_point

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

final class Routes(container: EntryPointDependencyContainer) {

  //  private val movcta = get {
  //    path("movcta") {
  //      parameter("banca", "agencia", "corredor", "desde", "hasta") { (banca, agencia, corredor, desde, hasta) =>
  //        container.movCtaController.get(banca, agencia, corredor, desde, hasta)
  //      }
  //    }
  //  }

  private val saldo =
    path("saldo") {
      parameters("banca".as[Int], "agencia".as[Int], "corredor".as[Int]) { (banca, agencia, corredor) =>
        get {
          container.saldoCorredorController.get(banca, agencia, corredor)
        }
      }
    }


  val all: Route =
    saldo
  //  movcta ~

  //  private def jsonBody(handler: Map[String, JsValue] => Route): Route =
  //    entity(as[JsValue])(json => handler(json.asJsObject.fields))
}