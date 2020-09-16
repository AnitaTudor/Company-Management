package model;

import utils.IdGenerator;

public class Employee {
    protected Integer id;
    protected Name name;
    protected Salary salary;
    protected Name boss;
    protected Department department;

    public Employee(Integer id, Name name, Salary salary, Name boss, Department department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.boss = boss;
        this.department = department;
    }


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Name getBoss() {
        return boss;
    }

    public void setBoss(Name boss) {
        this.boss = boss;
    }

    public Boolean hasABoss(){
        return boss == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
