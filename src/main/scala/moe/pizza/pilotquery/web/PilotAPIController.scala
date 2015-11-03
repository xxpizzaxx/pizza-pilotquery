package moe.pizza.pilotquery.web

import moe.pizza.pilotquery.data.PilotDB
import org.json4s._
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.{BadRequest, NotFound, Ok, ScalatraServlet}

class PilotAPIController(db: PilotDB) extends ScalatraServlet with JacksonJsonSupport {

  private class LimitValidationException(msg: String) extends IllegalArgumentException(msg)

  before() {
    contentType = formats("json")
  }

  case class Counts(alliances: Long, characters: Long)

  get("/") {
    new Counts(db.countAlliances(), db.countCharacters())
  }

  get("/search/:terms") {
    val query = params("terms").split(" ").toList
    val result = db.search(query)
    result
  }

  override protected implicit def jsonFormats: Formats = DefaultFormats
}
