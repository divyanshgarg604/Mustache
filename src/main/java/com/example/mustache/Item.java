package com.example.mustache;

import java.util.List;

public class Item {
    private String name;
    private String price;
    private List<String> feature ;

    public Item(String name, String price, List<String> feature) {
        this.name = name;
        this.price = price;
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getFeature() {
        return feature;
    }

    public void setFeature(List<String> feature) {
        this.feature = feature;
    }
}
