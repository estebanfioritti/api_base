package scala.api.module.movcta.domain

case class DataCorredor(
                         idBanca: Int,
                         idAgencia: Int,
                         idCorredor: Int,
                         nombre: String,
                         CI: String,
                         telefono: String
                       )