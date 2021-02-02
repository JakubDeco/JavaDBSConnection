package sk.kosickaakademia.deco.entity;

public class City {
    private String name;
    private int population;
    private String code3;
    private String district;
    private String country;

    public City(String name, int population){
        this.name=name;
        this.population=population;
    }

    public City(String name, int population, String district, String country) {
        this.name = name;
        this.population = population;
        this.district = district;
        this.country = country;
    }

    public void setCode3(String code3) {
        this.code3 = code3;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getCode3() {
        return code3;
    }

    public String getDistrict() {
        return district;
    }

    public String getCountry() {
        return country;
    }
}
