package scala.api.module.movcta.infraestructure.dependency_injection

import scala.api.module.movcta.aplication.SaldoCorredorSearcher
import scala.api.module.movcta.infraestructure.repository.DoobieMySqlMovCtaRepository
import scala.api.module.shared.infraestructure.presistence_doobie.DoobieDbConnection

class SaldoModuleDependencyContainer(doobieDbConnection: DoobieDbConnection) {

  val repository: DoobieMySqlMovCtaRepository = new DoobieMySqlMovCtaRepository(doobieDbConnection)

  val saldoCorredorSearcher: SaldoCorredorSearcher = new SaldoCorredorSearcher(repository)

}
