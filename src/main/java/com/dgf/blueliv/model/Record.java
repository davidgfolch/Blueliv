package com.dgf.blueliv.model;

public class Record {

    private String name;
    private String city;
    private String id;

    public Record(String name, String city, String id) {
        this.name=name;
        this.city=city;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getId() {
        return id;
    }

}
