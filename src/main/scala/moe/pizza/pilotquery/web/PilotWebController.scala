package moe.pizza.pilotquery.web

import moe.pizza.pilotquery.data.PilotDB
import org.json4s._
import org.scalatra.ScalatraServlet

class PilotWebController(db: PilotDB) extends ScalatraServlet {

  get("/") {
    contentType="text/html"
    html.index.render(db)
  }

  get("/search") {
    contentType="text/html"
    val res = db.rawSearch(request.getParameter("query")).sortBy(_.lastSeenDate).reverse
    html.results.render(res)
  }


}
