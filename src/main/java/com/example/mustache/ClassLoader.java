package com.example.mustache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class ClassLoader {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(ClassLoader.class);

    /**
     * service method to get json object
     * @param objToJsonize
     * @return
     */

    public static JSONObject getJsonString(Object objToJsonize) {
        String json = "";
        JSONObject jobj = null;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objToJsonize);
            Object obj = new JSONParser().parse(json);
            jobj = (JSONObject) obj;
        } catch (JsonProcessingException | ParseException e) {
            log.error("Exception in getJsonString() : {}", e.getMessage());
        }
        return jobj;
    }

    /**
     * service method to convert into json file for creating swaggerApi yaml file
     * @param
     * @return
      */

    public static void convertIntoAPIJson() {
        JSONObject json2 = new JSONObject();
        try {
            JSONObject json1 = (JSONObject) new JSONParser().parse(new FileReader("C:\\Internal Project\\GeminiProject\\Mustache\\src\\main\\resources\\Books.json"));

            Set<String> keys = json1.keySet();

            json2.put("classname", keys.stream().collect(Collectors.toList()));

            List<Object> values = new ArrayList<>();

            for (Object key : json1.keySet()) {
                JSONObject value = (JSONObject) new JSONParser().parse(json1.get(key).toString());
                values.add(value.keySet());
            }

            json2.put("variables", values);
        } catch (Exception e) {
            log.error("Exception in convertIntoApiJson() : {}", e.getMessage());
        }

        try {
            FileWriter file = new FileWriter("C:\\Internal Project\\GeminiProject\\Mustache\\src\\main\\resources\\SwaggerJson.json");
            file.write(json2.toJSONString());
            file.close();
        } catch (IOException e) {
            log.error("Exception in writing into JSON file" + e.getMessage());
        }
        System.out.println("JSON file created for SwaggerAPI Yaml File: " + json2);
    }

    /**
     * service method to fetch fully qualified classname from package
     * @return
     */

    public static Set<String> readClassName() {
        Reflections reflections =
                new Reflections(new ConfigurationBuilder()
                        .filterInputsBy(new FilterBuilder().includePackage("com.example.entity"))
                        .setUrls(ClasspathHelper.forPackage("com.example.entity"))
                        .setScanners(new SubTypesScanner(false)));

        Set<String> fullyQualifiedClassName = reflections.getAllTypes();
        return fullyQualifiedClassName;
    }

    /**
     * service method is used to load class from fully qualified classname
     * @param fullyQualifiedClassName
     */

    public static void loadClass(Set<String> fullyQualifiedClassName) {
        try {
            JSONObject object = new JSONObject();
            for (String classtype : fullyQualifiedClassName) {
                Object newObject = Class.forName(classtype).getDeclaredConstructor().newInstance();
                String classname = Class.forName(classtype).getSimpleName();
                object.put(classname, getJsonString(newObject));
            }
        } catch (Exception e) {
            log.error("Exception in loadClass():{}" + e.getMessage());

        }
    }


    public static void main(String[] args) {
        SpringApplication.run(Mustache_Example.class, args);

        loadClass(readClassName());

        convertIntoAPIJson();
    }

}

