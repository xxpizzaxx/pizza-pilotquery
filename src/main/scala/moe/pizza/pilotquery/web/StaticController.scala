package moe.pizza.pilotquery.web

import java.io.{File, FileInputStream}
import java.util.Properties

import org.scalatra.ScalatraServlet

import scala.io.Source

/**
 * Created by Andi on 10/05/2015.
 */
class StaticController extends ScalatraServlet {

  get("/*") {
    println("trying to load static file")
    val filename = multiParams("splat").mkString("/")
    println(filename)
    Option(servletContext.getResourceAsStream("/"+filename)) match {
      case Some(inputStream) => {
        contentType = mimes.mimes.getOrElse(filename.split("\\.").toList.reverse.head, "text/plain")
        inputStream
      }
      case None          => halt(404)
    }
  }
}

