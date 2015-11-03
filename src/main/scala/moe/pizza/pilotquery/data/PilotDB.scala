package moe.pizza.pilotquery.data

import com.mongodb.casbah.{MongoDB, MongoCollection}
import com.mongodb.casbah.commons.{MongoDBList, MongoDBObject}
import moe.pizza.pilotquery.{JavaPilot, JongoQueryHelper}
import moe.pizza.pilotquery.core.Pilot
import org.jongo.{MongoCursor, Jongo}

class PilotDB(db: MongoDB) extends SalatContext {

  val jongo = new Jongo(db.underlying)
  val characterCollection = db("characters")
  val alliancesCollection = db("alliances")

  def countAlliances(): Long = {
    alliancesCollection.find().count()
  }

  def countCharacters(): Long = {
    characterCollection.find().count()
  }

  val SEARCH_FIELDS = List("lastSeenShip", "lastSeenSystem", "lastSeenRegion")

  def searchCount(terms: List[String]): Long = {
    val matches = MongoDBList.newBuilder
    terms.foreach { term =>
      SEARCH_FIELDS.foreach { field =>
        matches += MongoDBObject(field -> term)
      }
    }
    characterCollection.find(
      MongoDBObject("$or" -> matches.result())
    ).count()
  }

  def search(terms: List[String]): List[Pilot] = {
    val matches = MongoDBList.newBuilder
    terms.foreach { term =>
      SEARCH_FIELDS.foreach { field =>
        matches += MongoDBObject(field -> term)
      }
    }
    characterCollection.find(
      MongoDBObject("$or" -> matches.result())
    ).map(PilotTransformer.deserialize).toList
  }

  def rawSearch(query: String): List[JavaPilot] = {
    val r = JongoQueryHelper.doQuery(query, "characters", jongo)
    println(r)
    import scala.collection.JavaConversions._
    r.iterator().toList
  }

}
