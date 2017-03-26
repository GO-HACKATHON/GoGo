package com.gogo.algorithm;

import com.gogo.model.Bus;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.xml.ws.Response;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class TrajectoryPlanner {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collectionBus, collectionRoute1a, collectionRoute1b;

    public TrajectoryPlanner(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        database = this.mongoClient.getDatabase("migi-database");
        collectionBus = database.getCollection("bus");
        collectionRoute1a = database.getCollection("route1a");
        collectionRoute1b = database.getCollection("route1b");
    }

    public Bus findByState(String state) {
        Bus bus = new Bus();
        Document myDoc = collectionBus.find(eq("state", state)).first();
        if (myDoc != null) {
            bus.setID_Bus(myDoc.getObjectId("_id").toHexString());
            bus.setState(myDoc.getString("state"));
            bus.setIsPenuh(myDoc.getString("isPenuh"));
            bus.setArrivalTime(myDoc.getString("arrivaltime"));

            return bus;
        }
        return null;
    }

    public Response getLocation(String addressFrom, String addressTo){
        findByState()
    }
}
