package model;

import utils.IdGenerator;

public class Department {
    private final Integer id;
    private String name;
    private Office office;

    public Department(Integer id, String name, Office office) {
        this.id = id;
        this.name = name;
        this.office = office;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
