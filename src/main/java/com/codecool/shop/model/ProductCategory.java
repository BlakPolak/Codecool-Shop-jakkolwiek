package com.codecool.shop.model;


public class ProductCategory extends BaseModel {
    private String department;

    public ProductCategory(Integer id, String name, String description, String department) {
        super(id, name, description);
        setDepartment(department);
    }

    private void setDepartment(String department) {
        this.department = department;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}