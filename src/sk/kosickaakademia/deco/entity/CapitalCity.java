package sk.kosickaakademia.deco.entity;

public class CapitalCity {
    private String country;
    private String name;
    private int population;

    public CapitalCity(String country, String name, int population) {
        this.country = country;
        this.name = name;
        this.population = population;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}
