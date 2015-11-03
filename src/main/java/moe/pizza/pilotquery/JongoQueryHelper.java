package moe.pizza.pilotquery;

import org.jongo.Jongo;
import org.jongo.MongoCursor;

/**
 * Created by Andi on 10/05/2015.
 */
public class JongoQueryHelper {

    public static MongoCursor<JavaPilot> doQuery(String query, String collection, Jongo jongo) {
        return jongo.getCollection(collection).find(query).as(JavaPilot.class);
    }

}
