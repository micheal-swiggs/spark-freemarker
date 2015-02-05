package com.simple;

import static spark.Spark.*;
import spark.servlet.SparkApplication;

import java.util.HashMap;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import freemarker.template.Configuration;

public class App implements SparkApplication
{
    public void init()
    {
        /*
         *  This will configure the view directory.
         */
        Configuration viewDir = new Configuration();
        viewDir.setClassForTemplateLoading(App.class, "/views");

        get("/ping", (request, response) -> "pong\n");

        /*
         * Creates a very simple model and returns
         * the template 'hello.ftl' when
         * http://localhost:4567/template/somename is called.
         */
        get("/template/:name", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("name", request.params(":name"));
            return new ModelAndView(model, "hello.ftl");
        }, new FreeMarkerEngine(viewDir));
    }
    public static void main( String[] args )
    {
        new App().init();
    }
}
