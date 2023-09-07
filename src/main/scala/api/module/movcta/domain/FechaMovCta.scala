package scala.api.module.movcta.domain

import java.time.LocalDate

case class FechaMovCta(fecha: LocalDate)

object FechaMovCta {

  import java.time.format.DateTimeFormatter

  val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def apply(fecha: String): FechaMovCta = FechaMovCta(LocalDate.parse(fecha, formatter))
}
