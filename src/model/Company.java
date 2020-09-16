package model;

public class Company {
    private final Integer id;
    private String name;
    private Long marketCap;

    public Company(Integer id, String name, Long marketCap) {
        this.id = id;
        this.name = name;
        this.marketCap = marketCap;
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

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marketCap=" + marketCap +
                '}';
    }
}
