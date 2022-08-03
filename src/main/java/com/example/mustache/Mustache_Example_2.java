package com.example.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.util.InternalArrayList;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class Mustache_Example_2 {
    private static Logger LOG = LoggerFactory.getLogger(Mustache_Example_2.class);

    @Autowired
    private static Item item;



//
//    @Autowired
//    private static context context;
//    List<Item> items=new ArrayList<>(new Item("fdsf","fsdf", {"fwe","Fewfe"}), )

    public static void main(String[] args) throws IOException {

//        context n=new context();
        List<Item> items= Arrays.asList(
                new Item("Item 1", "$19.99", Arrays.asList("uhbjhbj", "jnj")),
                new Item("Item 2", "$29.99", Arrays.asList("hvh", "hgvhv"))
        );

//        item.setName("Divyansh");
//        item.setName("sdjkfnsd");
//        System.out.println(item.getName());
        for (Item s:items)
        {
            System.out.println(s.getName());
        }


        System.out.println();



        context context = new context();
        context.setClassname("Divyansh");
        context.setPackages("com.example.mustache");
        context.setName("Item 1");
        context.setPrice("Rs 100/-");
        context.setFeature("Beautiful");
        context.setDbUrl("jdbc:mysql://localhost:3306/myshop");
        context.setPassword("Gemini@123");
        context.setUsername("root");
//        context.setQuery("select * from cold_drinks_table");
        context.setRestClassName("Restcontroller");
        System.out.println(context.getClassname()+" "+context.getPackages());

        //StringWriter object
        StringWriter writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache2 = mf.compile("template2.mustache");
        mustache2.execute(new PrintWriter(System.out), context).flush();
        System.out.println();
        Path path1 = Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\mustache");
        String filename = context.getClassname() + ".java";
        String path = path1 + File.separator + filename;
        LOG.info("{}",path);
        File files = new File(path);
        try {
            if (files.createNewFile()) {
                Mustache mustache = mf.compile("javatemplate.mustache");
                mustache.execute(writer, context).flush();
                String s = writer.toString();
                Files.write(Paths.get(String.valueOf(path)), s.getBytes(), StandardOpenOption.APPEND);
                LOG.info("File Created Successfully {}.java", context.getClassname());
                writer.close();
            }
            else
                LOG.info("File Exits {}.java", context.getClassname());
        }
        catch (IOException e)
        {
            LOG.error("{}", e);
        }

        Path path2=Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\mustache"+"\\ConnectionPool.java");
        File files1=new File(String.valueOf(path2));
        try {
            if (files1.createNewFile()) {
                Mustache mustache3 = mf.compile("ConnectionPool.mustache");
                mustache3.execute(writer, context).flush();
                Files.write(path2, writer.toString().getBytes(), StandardOpenOption.APPEND);
                LOG.info("File Created Successfully");
                writer.close();
            }
            else
                LOG.info("File Exits");
        }
        catch(IOException e)
        {
            LOG.error("{}",e);
        }
        Path path3=Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\mustache"+"\\Restcontroller.java");
        File files2=new File(String.valueOf(path3));
        try {
            if (files2.createNewFile()) {
                Mustache mustache4 = mf.compile("RestController.mustache");
                mustache4.execute(writer, context).flush();
                Files.write(path3, writer.toString().getBytes(), StandardOpenOption.APPEND);
                LOG.info("File Created Successfully");
                writer.close();
            }
            else
                LOG.info("File Exits");
        }
        catch(IOException e)
        {
            LOG.error("{}",e);
        }
        Path data = Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\resources\\ContextNames.txt");
        String content = String.valueOf(Files.readAllLines(data));
        System.out.println(content);
        FileReader reader= new FileReader(String.valueOf(data));
        System.out.println(reader);
        BufferedReader br = new BufferedReader(new FileReader(String.valueOf(data)));
        String s;
        FileOutputStream fout=new FileOutputStream(String.valueOf(data));
        List<String> list= new ArrayList<>();
        while((s=br.readLine())!=null) {
            list.add(s);
        }


//                Files.readAllLines(data);

        Path path4=Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\mustache"+"\\contextTemplate.java");
        File files3=new File(String.valueOf(path4));
        String p="uyguh";
        try {
            if (files3.createNewFile()) {
                StringReader reader1=new StringReader(content);
                Mustache mustache7 = mf.compile("contextTemplate.mustache");
                mustache7.execute(writer, content).flush();
                Files.write(path4, writer.toString().getBytes(), StandardOpenOption.APPEND);

                LOG.info("File Created Successfully");
                writer.close();
            }
            else
                LOG.info("File Exits");
        }
        catch(IOException e)
        {
            LOG.error("{}",e);
        }


    }
}


//        Path path=Paths.get("D:\\Intellj Projects\\Mustache_Example\\src\\main\\java\\com\\example\\mustache")
//        File directory=new File(System.getProperty("java.com.example.mustache"));
//        File absolutepath=new File(directory.getAbsolutePath()+File.separator+context.getClassname());
//        File files = new File(String.valueOf(absolutepath));
//        System.out.println(absolutepath);

//            Path path = Paths.get("com.example.mustache.");
//            System.out.println(path);
//            Files.deleteIfExists(Path.of(context.getClassname()));
//            Files.wr
////            Files.createFile(path,Path.of(context.getClassname()));
//            Files.createFile(Path.of(context.getClassname()));
////            Files.write(Paths.get(context.getClassname()), m.getBytes(), StandardOpenOption.CREATE_NEW);