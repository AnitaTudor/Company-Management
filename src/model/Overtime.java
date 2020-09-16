package model;

import utils.IdGenerator;

import java.sql.Date;

public class Overtime {
    private final Integer id = IdGenerator.getInstance().genId(Overtime.class);
    private Date date;
    private Integer hours;
    private Integer hourlyPay;
    private Employee employee;

    public Overtime(Date date, Integer hours, Integer hourlyPay, Employee employee) {
        this.date = date;
        this.hours = hours;
        this.hourlyPay = hourlyPay;
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(Integer hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }
}
