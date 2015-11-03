package moe.pizza.pilotquery

import com.typesafe.config.ConfigFactory
import moe.pizza.pilotquery.data.DataModule
import moe.pizza.pilotquery.web.{StaticController, WebModule}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.webapp.WebAppContext
import org.slf4j.{Logger, LoggerFactory}

object PilotProvider extends App with WebModule with DataModule {
  val logger = LoggerFactory.getLogger(getClass)
  val config = ConfigFactory.load()
  val server = new Server(config.getInt("http.port"))
  val webCtx = new WebAppContext()
  webCtx.setContextPath(config.getString("http.path"))
  webCtx.setResourceBase("./src/main/webapp/")
  webCtx.addServlet(new ServletHolder(pilotApiController), "/api/*")
  webCtx.addServlet(new ServletHolder(pilotWebController), "/*")
  webCtx.addServlet(new ServletHolder(new StaticController()), "/static/*")

  server.setHandler(webCtx)
  server.start
  logger.info("Server started.")
  server.join
}
