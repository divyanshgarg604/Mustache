package com.example.Handlebars;

import com.example.mustache.ClassLoader;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Handlebar {
    private static final Logger log = LoggerFactory.getLogger(Handlebar.class);
    public static void SwaggerYaml()
    {
        try
        {
            Handlebars handlebars = new Handlebars();
            Template template = handlebars.compile("swaggerapiTemplate");
            Path path1 = Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\resources");
            String filename = "SwaggerYaml" + ".yml";
            String path = path1 + File.separator + filename;
            FileReader fileReader = new FileReader("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\jsonfiles\\json3.json");
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(fileReader);
            FileWriter fileWriter =new FileWriter(path);
            fileWriter.write(template.apply(obj));
            fileWriter.close();
        }
        catch (ParseException e)
        {
            log.error("{}", e);
        }
        catch (FileNotFoundException e)
        {
            log.error("{}", e);
        } catch (IOException e)
        {
            log.error("{}", e);
        }
    }
    public static void main(String[] args) {
        SwaggerYaml();

    }
}
