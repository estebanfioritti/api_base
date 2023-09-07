package scala.api.module.movcta.domain

import scala.concurrent.Future

trait MovCtaRepository {

  def get(banca: String, agencia: String, corredor: String, desde: String, hasta: String): Future[Seq[MovCta]]
}
