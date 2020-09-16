package service;

import model.Employee;
import repository.EmployeeRepository;
import java.util.HashSet;

public final class EmployeeService {
    private static EmployeeService instance = null;

    public void addEmployee (Employee employee){
        EmployeeRepository.getInstance().addEmployee(employee);
    }

    public void deleteEmployeeById(Integer id){
        EmployeeRepository.getInstance().deleteEmployeeById(id);
    }

    public Employee findEmployeeById(Integer id){
        return EmployeeRepository.getInstance().findEmployeeById(id);
    }

    public HashSet<Employee> findEmployees(){
        return EmployeeRepository.getInstance().findEmployees();
    }

    public void updateEmployee(Employee employee){
        EmployeeRepository.getInstance().updateEmployee(employee);
    }




    public static EmployeeService getInstance() {
        if (instance == null)
            instance = new EmployeeService();
        return instance;
    }

}
