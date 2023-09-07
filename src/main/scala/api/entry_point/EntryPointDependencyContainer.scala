package scala.api.entry_point

import scala.api.entry_point.controller.movcta.MovCtaController
import scala.api.module.movcta.infraestructure.dependency_injection.MovCtaModuleDependencyContainer

final class EntryPointDependencyContainer(
                                           movCtaDeudorDepencies: MovCtaModuleDependencyContainer
                                         ) {

  val movCtaController = new MovCtaController(movCtaDeudorDepencies.movctaSearcher)

}