import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

public class Symptom {
    private String name;
    private double probability;

    public Symptom(String name, double probability) {
        this.name = name;
        this.probability = probability;
    }
}
public class PredictionHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Read the input data from the HTTP request
        String inputData = readInputStream(exchange.getRequestBody());

        // Convert the input data to a JSON object
        JSONObject inputJson = new JSONObject(inputData);

        // Retrieve the symptoms from the JSON object
        String symptom1 = inputJson.getString("symptom 1");
        String symptom2 = inputJson.getString("symptom 2");
        String symptom3 = inputJson.getString("symptom 3");

        // Estimate the cancer probability based on the symptoms
        double cancerProbability = estimateCancerProbability(symptom1, symptom2, symptom3);

        // Create a JSON object with the cancer probability
        JSONObject outputJson = new JSONObject();
        outputJson.put("Estimate the Probaility", Estimate the Probaility);

        // Send the JSON object as the HTTP response
        exchange.sendResponseHeaders(200, 0);
        outputStream outputStream = exchange.getResponseBody();
        outputStream.write(outputJson.toString().getBytes());
        outputStream.close();
    }

}
public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/predict", new PredictionHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
}