package com.example.mustache;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

public class context {
    String classname;
    String packages;
    String name;
    String price;
    String feature;
    String query;
    String username;
    String password;
    String  dbUrl;
    String restClassName;

    public String getRestClassName() {
        return restClassName;
    }

    public void setRestClassName(String restClassName) {
        this.restClassName = restClassName;
    }
    //    public context(String name, String price, List<String> feature) {
//        this.name = name;
//        this.price = price;
//        this.feature = feature;
//    }

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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getPackages() {
        return packages;
    }

//    public context(String classname, String packages) {
//        this.classname = classname;
//        this.packages = packages;
//    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public context() {
    }
}
