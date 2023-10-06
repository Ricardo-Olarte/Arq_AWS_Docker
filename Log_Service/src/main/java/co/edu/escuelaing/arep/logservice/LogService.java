package co.edu.escuelaing.arep.logservice;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

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
        return """
                    {
                    "m1": "message1"
                    "m2": "message2"
                    "m3": "message3"
                    }
               """;
    }

    private static List
}
