package com.example.Handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Handlebar {
    public static void main(String[] args)  throws IOException, ParseException {

        Handlebars handlebars = new Handlebars();
        Template template = handlebars.compile("swaggerapiTemplate");


        FileReader fileReader =new FileReader("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\jsonfiles\\json1.json");
        JSONParser jsonParser = new JSONParser();
        Object obj =jsonParser.parse(fileReader);


        String text=template.apply(obj);
        System.out.println(text);

        Path path1 = Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\resources");
        String filename = "AutogeratedYaml" + ".yml";
        String path = path1 + File.separator + filename;

        FileWriter fileWriter =new FileWriter(path);
        fileWriter.write(text);
        fileWriter.close();
    }
}
