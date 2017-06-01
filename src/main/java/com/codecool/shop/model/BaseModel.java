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

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        BaseModel baseModel = (BaseModel) other;
        if (id != null ? !id.equals(baseModel.id) : baseModel.id != null) return false;
        if (name != null ? !name.equals(baseModel.name) : baseModel.name != null) return false;
        return description != null ? description.equals(baseModel.description) : baseModel.description == null;
    }
}
