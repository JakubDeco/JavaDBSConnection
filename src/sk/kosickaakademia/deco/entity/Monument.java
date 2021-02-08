package sk.kosickaakademia.deco.entity;

public class Monument {
    private String country;
    private String city;
    private String name;
    private int id;

    public Monument(String country, String city, String name, int id) {
        this.country = country;
        this.city = city;
        this.name = name;
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
