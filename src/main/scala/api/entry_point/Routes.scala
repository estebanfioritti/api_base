package scala.api.entry_point

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.JsValue

final class Routes(container: EntryPointDependencyContainer) {

  private val movcta = get {
    path("movcta") {
      parameter("banca", "agencia", "corredor", "desde", "hasta") { (banca, agencia, corredor, desde, hasta) =>
        container.movCtaController.get(banca, agencia, corredor, desde, hasta)
      }
    }
  }


  val all: Route = movcta

  private def jsonBody(handler: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => handler(json.asJsObject.fields))
}