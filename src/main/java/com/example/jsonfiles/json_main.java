package com.example.jsonfiles;

//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class json_main {
    public static void main(String[] args) throws IOException, ParseException {
        json_main jso =new json_main();
        Path p = Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\jsonfiles\\json1.json");
//        JSONObject obj1 = new JSONObject("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\jsonfiles\\json2.json");
//        System.out.println(obj1);
        FileReader fileReader =new FileReader("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\jsonfiles\\json3.json");
        JSONParser jsonParser = new JSONParser();
        Object obj =jsonParser.parse(fileReader);
        System.out.println(obj);
    }
}
