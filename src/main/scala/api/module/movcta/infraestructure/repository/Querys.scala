package scala.api.module.movcta.infraestructure.repository

import doobie.Query0
import doobie.implicits._

import scala.api.module.movcta.domain.DataCorredor

private[repository] object Querys {


  def totalImportePorCorredor(idBanca: Int, idAgencia: Int, idCorredor: Int): Query0[Option[BigDecimal]] =
    sql"""
      SELECT SUM(m.importe * t.signo) FROM MovCtaCorredor m
      JOIN tipomovctacorredor t ON m.tipo = t.tipo
      WHERE idBanca = $idBanca AND idAgencia = $idAgencia AND idCorredor = $idCorredor
    """.query[Option[BigDecimal]]

  def getDataCorredor(idBanca: Int, idAgencia: Int, idCorredor: Int): Query0[Option[DataCorredor]] =
    sql"""
    SELECT cor.idBanca, cor.idAgencia, cor.idCorredor, cor.nombre, cor.CI, con.telefono
    FROM corredor cor
    JOIN contacto con ON cor.idContacto = con.idContacto
    WHERE idBanca = $idBanca AND idAgencia = $idAgencia AND idCorredor = $idCorredor
  """.query[Option[DataCorredor]]

}