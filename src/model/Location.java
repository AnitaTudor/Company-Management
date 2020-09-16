package model;

public class Location {
    private final Integer id;
    private String addressLineA;
    private String addressLineB;
    private String city;
    private String country;

    public Location(Integer id, String addressLineA, String addressLineB, String city, String country) {
        this.id = id;
        this.addressLineA = addressLineA;
        this.addressLineB = addressLineB;
        this.city = city;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getAddressLineA() {
        return addressLineA;
    }

    public void setAddressLineA(String addressLineA) {
        this.addressLineA = addressLineA;
    }

    public String getAddressLineB() {
        return addressLineB;
    }

    public void setAddressLineB(String addressLineB) {
        this.addressLineB = addressLineB;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
