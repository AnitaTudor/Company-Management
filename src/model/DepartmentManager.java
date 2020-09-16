package model;

import utils.IdGenerator;

public class DepartmentManager extends Employee {
    public DepartmentManager(Integer id, Name name, Salary salary, Name boss, Department department) {
        super(id, name, salary, boss,department);
    }
}
