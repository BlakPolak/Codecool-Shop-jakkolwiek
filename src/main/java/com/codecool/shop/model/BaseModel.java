package com.codecool.shop.model;


public class BaseModel {

    protected Integer id;
    protected String name;
    protected String description;

    BaseModel(String name, String description) {
        setName(name);
        setDescription(description);
    }

    BaseModel(Integer id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}