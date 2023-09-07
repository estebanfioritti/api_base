package scala.api.module.movcta.infraestructure.repository

import doobie.implicits._

import scala.api.module.movcta.domain.{Descripcion, Detalle, FechaMovCta, Importe, MovCta, MovCtaId, MovCtaRepository}
import scala.api.module.shared.infraestructure.presistence_doobie.DoobieDbConnection
import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlMovCtaRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends MovCtaRepository {

  private def rowToMovCta(row: (Int, java.sql.Date, BigDecimal, Option[String], Option[String])): MovCta =
    MovCta(
      MovCtaId(row._1),
      FechaMovCta(row._2.toString),
      Importe(row._3.toDouble),
      Descripcion(row._4.getOrElse("")),
      Detalle(row._5.getOrElse(""))
    )

  override def get(banca: String,
                   agencia: String,
                   corredor: String,
                   desde: String,
                   hasta: String): Future[Seq[MovCta]] =
    db.read(
      sql"SELECT idMovCtaCorredor,fecha,importe,descripcion,detalle from movctacorredor m where idBanca = $banca and idAgencia = $agencia and idCorredor = $corredor and fecha between $desde and $hasta"
        .query[(Int, java.sql.Date, BigDecimal, Option[String], Option[String])]
        .to[Seq]
        .map(_.map(rowToMovCta))
    )

}
