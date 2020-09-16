package model;

public class Office {
    private final Integer id;
    private Location location;
    private String name;
    private Company company;

    public Office(Integer id, String name,Location location, Company company) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.company = company;
    }

    public Integer getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
