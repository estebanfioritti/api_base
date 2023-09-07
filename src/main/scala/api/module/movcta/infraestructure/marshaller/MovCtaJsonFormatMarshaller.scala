package scala.api.module.movcta.infraestructure.marshaller

import scala.api.module.movcta.domain._
import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import scala.api.module.movcta.infraestructure.marshaller.MovCtaAttributesJsonFormatMarshaller._

object MovCtaJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val movCtaFormat: RootJsonFormat[MovCta] = jsonFormat5(MovCta(_: MovCtaId, _: FechaMovCta, _: Importe, _: Descripcion, _: Detalle))
}
