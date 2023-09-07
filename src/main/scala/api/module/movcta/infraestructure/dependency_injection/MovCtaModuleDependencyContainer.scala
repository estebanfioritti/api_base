package scala.api.module.movcta.infraestructure.dependency_injection

import scala.api.module.movcta.aplication.MovCtaSearcher
import scala.api.module.movcta.infraestructure.repository.DoobieMySqlMovCtaRepository
import scala.api.module.shared.infraestructure.presistence_doobie.DoobieDbConnection
import scala.concurrent.ExecutionContext

class MovCtaModuleDependencyContainer(doobieDbConnection: DoobieDbConnection)(
  implicit executionContext: ExecutionContext) {

  val repository: DoobieMySqlMovCtaRepository = new DoobieMySqlMovCtaRepository(doobieDbConnection)

  val movctaSearcher: MovCtaSearcher = new MovCtaSearcher(repository)

}
