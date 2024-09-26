package scala.api.entry_point

import scala.api.entry_point.controller.saldo_corredor.SaldoCorredorController
import scala.api.module.movcta.infraestructure.dependency_injection.SaldoModuleDependencyContainer

final class EntryPointDependencyContainer(
                                           depencies: SaldoModuleDependencyContainer
                                         ) {

  val saldoCorredorController = new SaldoCorredorController(depencies.saldoCorredorSearcher)

}