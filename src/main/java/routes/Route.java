package routes;

import com.google.gson.Gson;
import parsers.VersionParser;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class Route {
    private String name;
    private VersionParser parser;

    public Route(String name, VersionParser parser) {
        this.name = name;
        this.parser = parser;
    }

    public void setup() {
        get("/" + name, (request, response) -> {
            response.type("application/json");
            Map<String, String> result = new HashMap<String, String>();
            String version = VersionParser.UNKNOWN_VERSION;

            try {
                version = parser.parseVersion();
            } catch (Exception e) { /* Do not crash if version couldn't be retrieved */ }

            result.put(name, version);
            return new Gson().toJson(result);
        });
    }
}
