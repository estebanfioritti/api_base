package scala.api.module.movcta.infraestructure.marshaller

import spray.json.{JsNumber, JsString, JsValue, JsonFormat}

import scala.api.module.movcta.domain._
object MovCtaAttributesJsonFormatMarshaller {

  implicit object MovCtaIdMarshaller extends JsonFormat[MovCtaId] {
    override def write(value: MovCtaId): JsValue = JsNumber(value.value)

    override def read(value: JsValue): MovCtaId = MovCtaId(value.asInstanceOf[Int])
  }

  implicit object MovCtaFechaMarshaller extends JsonFormat[FechaMovCta] {
    override def write(value: FechaMovCta): JsValue = JsString(value.fecha.toString)

    override def read(value: JsValue): FechaMovCta = FechaMovCta.apply(value.toString())
  }

  implicit object MovCtaImporteMarshaller extends JsonFormat[Importe] {
    override def write(value: Importe): JsValue = JsString(value.valor.toString)

    override def read(value: JsValue): Importe = Importe.apply(value.toString())
  }

  implicit object MovCtaDescripcionMarshaller extends JsonFormat[Descripcion] {
    override def write(value: Descripcion): JsValue = JsString(value.value)

    override def read(value: JsValue): Descripcion = Descripcion(value.toString())
  }

  implicit object MovCtadetalleMarshaller extends JsonFormat[Detalle] {
    override def write(value: Detalle): JsValue = JsString(value.value)

    override def read(value: JsValue): Detalle = Detalle(value.toString())
  }
}
