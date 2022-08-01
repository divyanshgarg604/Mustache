package com.example.mustache;

import org.springframework.stereotype.Component;

@Component
public class context {
    String classname;
    String packages;

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


    public context() {
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }
}
