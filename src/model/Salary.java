package model;

import utils.IdGenerator;

public class Salary {
    private final Integer id;
    private Integer value;
    private Integer netValue;
    private Double annualRaise;    //percent

    public Salary(Integer id, Integer value, Integer netValue, Double annualRaise) {
        this.id = id;
        this.value = value;
        this.netValue = value * 8 / 10;
        this.annualRaise = annualRaise;
    }

    public Integer getNetValue() {
        return netValue;
    }

    public void setNetValue(Integer netValue) {
        this.netValue = netValue;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Double getAnnualRaise() {
        return annualRaise;
    }

    public void setAnnualRaise(Double annualRaise) {
        this.annualRaise = annualRaise;
    }

    public Integer getId() {
        return id;
    }
}
