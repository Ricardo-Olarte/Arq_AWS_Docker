package co.edu.escuelaing.arep.logservice;

import com.google.gson.JsonArray;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;

import org.bson.Document;
import org.bson.json.JsonObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;

/**
 * @author Luis Benavides, Modify by
 * @author Ricardo Olarte
 */
public class LogService {

    public static void main(String[] args) {
        port(getPort());
        get("/logservice", (req, pesp) -> {
            String val = req.queryParams("value");
            return logMessage(val);
        });

    }

    private static int getPort(){
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 15000;
    }

    private static String logMessage(String string){
        return string;
    }

    private static List<Document> connectionMongo(String string){
        String uriDB = "mongodb://mongodb:27017";

        MongoClient mongoClient = MongoClients.create(uriDB);
        MongoDatabase database = mongoClient.getDatabase("arep-mongoDB");
        MongoCollection<Document> collection = database.getCollection("message");
        List<Document> documents = new ArrayList<>();
        try(MongoCursor<Document> mongoCursor = collection.find().limit(10).sort(Sorts.descending("date")).iterator()){
            while (mongoCursor.hasNext()){
                documents.add(mongoCursor.next());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return documents;
    }


    private static void saveValue(String string){
        MongoClient mongoClient = MongoClients.create("mongodb://mongodb:27017");
        MongoDatabase database = mongoClient.getDatabase("arep-mongoDB");
        MongoCollection<Document> collection = database.getCollection("logs");

        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Document document = new Document("string", string).append("date", currentDate);

        collection.insertOne(document);
        mongoClient.close();
    }

}
