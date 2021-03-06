package main;

import parsers.jetbrains.JetbrainsVersionsParser;
import parsers.jetbrains.idea.IdeaVersionParser;
import parsers.jetbrains.youtrack.YoutrackVersionParser;
import parsers.simple.SimpleVersionParser;
import parsers.spring.SpringVersionParser;
import routes.Route;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.FileNotFoundException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        staticFileLocation("/static_files");
        setupPort();
        setupRoutes();
    }

    public static void setupPort() {
        String port = System.getenv("PORT");
        if(port == null || port.isEmpty()) {
            port = "8080";
        }
        setPort(Integer.valueOf(port));
    }

    private static void setupRoutes() {
        JetbrainsVersionsParser jetbrainsVersionsParser = new JetbrainsVersionsParser();
        new Route("youtrack", new YoutrackVersionParser(jetbrainsVersionsParser)).setup();
        new Route("idea", new IdeaVersionParser(jetbrainsVersionsParser)).setup();
        new Route("spring", new SpringVersionParser()).setup();
        new Conf().getConfigurations().forEach((id, conf) -> new Route(id, new SimpleVersionParser(conf)).setup());
        get("/", (request, response) -> new MustacheTemplateEngine().render(new ModelAndView(null, "index.mustache")));
    }
}
