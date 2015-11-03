package moe.pizza.pilotquery.web

import com.softwaremill.macwire.MacwireMacros._
import moe.pizza.pilotquery.data.PilotDB

trait WebModule {

  def pilotDB: PilotDB

  lazy val pilotApiController = wire[PilotAPIController]
  lazy val pilotWebController = wire[PilotWebController]


}
