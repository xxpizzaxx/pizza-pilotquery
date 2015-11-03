package moe.pizza.pilotquery.data

import com.mongodb.DBObject
import com.mongodb.casbah.commons.Imports._
import com.novus.salat._
import com.novus.salat.json._
import com.novus.salat.transformers._
import moe.pizza.pilotquery.core.Pilot

trait SalatContext {

  object PilotTransformer extends CustomTransformer[Pilot, DBObject] {

    def serialize(p: Pilot): DBObject =
      MongoDBObject("type" -> "Pilot")

    def deserialize(o: DBObject): Pilot = {
      new Pilot(
      o.get("_id").asInstanceOf[Int],
      o.get("corporationID").asInstanceOf[Int],
      o.get("allianceName").asInstanceOf[String],
      o.get("soloKills").asInstanceOf[Int],
      o.get("lastSeenDate").asInstanceOf[String],
      o.get("lifeTimeLosses").asInstanceOf[Int],
      o.get("lastSeenShip").asInstanceOf[String],
      o.get("corporationActiveArea").asInstanceOf[String],
      o.get("allianceActiveArea").asInstanceOf[String],
      o.get("lastSeenRegion").asInstanceOf[String],
      o.get("lastSeenSystem").asInstanceOf[String],
      o.get("allianceDate").asInstanceOf[String],
      o.get("lastUpdatedOnBackend").asInstanceOf[String],
      o.get("allianceID").asInstanceOf[Int],
      o.get("corporationDate").asInstanceOf[String],
      o.get("corporationName").asInstanceOf[String],
      o.get("bloodline").asInstanceOf[String],
      o.get("characterName").asInstanceOf[String],
      o.get("facepalms").asInstanceOf[String],
      o.get("lifeTimeKills").asInstanceOf[Int],
      o.get("race").asInstanceOf[String],
      o.get("ePeenSize").asInstanceOf[String],
      o.get("blobKills").asInstanceOf[Int],
      o.get("characterID").asInstanceOf[Int]
      )

    }

  }

  implicit val salatContext = new Context() {
    override val name = "custom_salat_context"
    override val typeHintStrategy = StringTypeHintStrategy(TypeHintFrequency.WhenNecessary, "_t")
    override val jsonConfig = JSONConfig(objectIdStrategy = StringObjectIdStrategy)

    registerCustomTransformer(PilotTransformer)

    registerGlobalKeyOverride(remapThis = "id", toThisInstead = "_id")
  }

}
