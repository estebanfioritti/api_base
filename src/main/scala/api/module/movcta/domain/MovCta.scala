package scala.api.module.movcta.domain

case class MovCta(movCtaId: MovCtaId,
                  fechaMovCta: FechaMovCta,
                  importe: Importe,
                  descripcion: Descripcion,
                  detalle: Detalle)

object MovCta {

  def apply(id: Int,
            fecha: java.sql.Date,
            importe: BigDecimal,
            maybeDescripcion: Option[String],
            maybeDetalle: Option[String]): MovCta =
    MovCta(MovCtaId(id),
      FechaMovCta(fecha.toString),
      Importe(importe.toDouble),
      Descripcion(maybeDescripcion.getOrElse("")),
      Detalle(maybeDetalle.getOrElse("")))

}