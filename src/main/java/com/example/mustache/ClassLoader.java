package com.example.mustache;


import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Id;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class ClassLoader {

    private static final Logger log = LoggerFactory.getLogger(Mustache_Example.class);

    /**
     * service method to convert into json file for creating swaggerApi yaml file
     *
     * @param
     * @return
     */
    public static void convertIntoAPIJson() {

        JSONObject json1 = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();

        HashMap<String, String> formatMap = new HashMap<>();
        HashMap<String, String> dataTypeMap = new HashMap<>();

        formatMap.put("String", "int64");
        formatMap.put("LocalDateTime", "int64");
        formatMap.put("Double", "int64");
        formatMap.put("Integer", "int64");
        formatMap.put("Boolean", "int64");
        formatMap.put("Short", "int32");
        formatMap.put("Byte", "int64");
        formatMap.put("Long", "int64");
        formatMap.put("BigDecimal", "int64");
        formatMap.put("Float", "int32");
        formatMap.put("LocalDate", "int64");
        formatMap.put("LocalTime", "int32");
        formatMap.put("byte[]", "int64");

        dataTypeMap.put("String", "string");
        dataTypeMap.put("LocalDateTime", "string");
        dataTypeMap.put("Double", "number");
        dataTypeMap.put("Integer", "integer");
        dataTypeMap.put("Boolean", "boolean");
        dataTypeMap.put("Short", "integer");
        dataTypeMap.put("Byte", "string");
        dataTypeMap.put("Long", "integer");
        dataTypeMap.put("BigDecimal", "number");
        dataTypeMap.put("Float", "number");
        dataTypeMap.put("LocalDate", "string");
        dataTypeMap.put("LocalTime", "string");
        dataTypeMap.put("byte[]", "array");


        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader("C:\\Internal Project\\GeminiProject\\Mustache\\src\\main\\resources\\Books.json"));

            for (Object classname : jsonObject.keySet()) {
                JSONObject json2 = new JSONObject();
                JSONArray jsonArray2 = new JSONArray();
                ArrayList<Object> fields = new ArrayList<>();
                ArrayList<Object> fieldTypes = new ArrayList<>();
                ArrayList<Object> formats = new ArrayList<>();

                JSONObject jsonBody = (JSONObject) new JSONParser().parse(jsonObject.get(classname).toString());

                for (Object fieldType : jsonBody.values()) {
                    formats.add(formatMap.get(fieldType));
                    fieldTypes.add(dataTypeMap.get(fieldType));
                }

                System.out.println(formats);


                fields.addAll(jsonBody.keySet());

                System.out.println("fields" + fields);


                json2.put("classname", classname);

                int count = 0;

                while (count != fields.size()) {
                    System.out.println("The size of fields:" + fields.size());

                    JSONObject json3 = new JSONObject();

                    json3.put("fieldName", fields.get(count));
                    json3.put("dataType", fieldTypes.get(count));
                    json3.put("format", formats.get(count));


                    System.out.println("json 4 value  " + json3);
                    json2.put("variable", jsonArray2.put(json3));

                    count++;

                }

                json1.put("classes", jsonArray1.put(json2));

            }
        } catch (Exception e) {
            log.error("Exception in convertIntoApiJson() : {}", e.getMessage());
        }

        try {
            FileWriter file = new FileWriter("C:\\Internal Project\\GeminiProject\\Mustache\\src\\main\\resources\\SwaggerJson.json");
            file.write(json1.toJSONString());
            file.close();
        } catch (IOException e) {
            log.error("Exception in writing into JSON file" + e.getMessage());
        }
        System.out.println("JSON file created for SwaggerAPI Yaml File: " + json1);
    }

    /**
     * service method to fetch fully qualified classname from package
     *
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
     *
     * @param fullyQualifiedClassName
     */

    public static void loadClass(Set<String> fullyQualifiedClassName) {


        JSONObject jsonBody = null;
        try {
            jsonBody = new JSONObject();

            for (String classtype : fullyQualifiedClassName) {
                String classname = Class.forName(classtype).getSimpleName();
                JSONObject object = getJsonBody(classtype);
                jsonBody.put(classname, object);
            }

            System.out.println(jsonBody);
        } catch (Exception e) {
            log.error("Exception in loadClass():{}" + e.getMessage());
        }

        try {
            FileWriter file = new FileWriter("C:\\Internal Project\\GeminiProject\\Mustache\\src\\main\\resources\\Books.json");
            file.write(jsonBody.toJSONString());
            file.close();
        } catch (IOException e) {
            log.error("Exception in writing into JSON file" + e.getMessage());
        }
    }

    /**
     * service method to get json object
     *
     * @param classType
     * @return
     */
    public static JSONObject getJsonBody(String classType) {

        JSONObject object = null;
        try {
            object = new JSONObject();
            Field[] fields = Class.forName(classType).getDeclaredFields();

            List<Field> fieldlist = Arrays.stream(fields).filter(field -> field.getAnnotation(Id.class) != null).collect(Collectors.toList());

            for (Field field : Arrays.stream(fields).skip(1).collect(Collectors.toList())) {
                if (!fieldlist.contains(field)) {
                    fieldlist.add(field);
                }

            }

            for (Field field : fieldlist) {
                String fieldName = field.getName();
                String fieldType = field.getType().getSimpleName();
                object.put(fieldName, fieldType);
            }
        } catch (Exception e) {

            log.error("Exception in getJsonBody():{}" + e.getMessage());
        }
        return object;
    }


    public static void main(String[] args) {
        SpringApplication.run(Mustache_Example.class, args);

        loadClass(readClassName());


        convertIntoAPIJson();
    }

}

