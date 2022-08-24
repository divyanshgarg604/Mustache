package com.example.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Mustache_Example {

    List<Item> items() {
        return Arrays.asList(
                new Item("Item 1", "$19.99", Arrays.asList(new Feature("New!"), new Feature("Awesome!"))),
                new Item("Item 2", "$29.99", Arrays.asList(new Feature("Old."), new Feature("Ugly.")))
        );
    }


    static class Item {
        Item(String name, String price, List<Feature> features) {
            this.name = name;
            this.price = price;
            this.features = features;
        }

        String name, price;
        List<Feature> features;
    }

    static class Feature {
        Feature(String description) {
            this.description = description;
        }

        String description;
    }


    public static void main(String[] args) throws IOException {
        SpringApplication.run(Mustache_Example.class, args);
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("MustacheTemplates/template.mustache");
        mustache.execute(new PrintWriter(System.out), new Mustache_Example()).flush();
    }
}
