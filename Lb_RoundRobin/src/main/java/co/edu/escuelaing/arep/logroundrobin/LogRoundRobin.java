package co.edu.escuelaing.arep.logroundrobin;

import java.io.IOException;
import static spark.Spark.*;


/**
 * @author Luis Benavides, modify by
 * @author Ricardo Olarte
 */
public class LogRoundRobin {

    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/log", (req, pesp) -> logMessage(req.queryParams("value")));
    }

    /**
     * Create one port, this port it's phisical
     * Singleton Pattern
     * @return numero entero, indica el puerto
     */
    private static int getPort(){
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 24000;
    }

    private static String logMessage(String string) throws IOException {
        return HttpRemoteCaller.remoteLogCall(string);
    }
}
