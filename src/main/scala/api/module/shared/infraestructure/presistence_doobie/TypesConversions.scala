package scala.api.module.shared.infraestructure.presistence_doobie


import doobie.Meta

import java.util.UUID

object TypesConversions {
  implicit val uuidMeta: Meta[UUID] = Meta[String].xmap(UUID.fromString, _.toString)
}
