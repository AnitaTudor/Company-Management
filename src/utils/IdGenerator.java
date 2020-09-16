package utils;

import model.*;

import java.util.HashMap;

public final class IdGenerator {
    private static IdGenerator instance = null;

    private HashMap<Class<?>, Integer> idMap;

    private IdGenerator() {
        idMap = new HashMap<>();
        idMap.put(Company.class, 0);
        idMap.put(Department.class, 0);
        idMap.put(Employee.class, 0);
        idMap.put(Location.class, 0);
        idMap.put(Name.class, 0);
        idMap.put(Office.class, 0);
        idMap.put(Salary.class, 0);
        idMap.put(Overtime.class, 0);
        idMap.put(DepartmentManager.class,0);
    }

    public static IdGenerator getInstance() {
        if (instance == null)
            instance = new IdGenerator();
        return instance;
    }

    public Integer genId(Class<?> cls) {
        idMap.put(cls, idMap.get(cls) + 1);
        return idMap.get(cls);
    }
}
