package sk.kosickaakademia.deco.entity;

public class Country {
    private String name;
    private String code3;
    private String capitalCity;
    private String area;
    private String continent;

    public Country(String name, String code3, String capitalCity, String area, String continent) {
        this.name = name;
        this.code3 = code3;
        this.capitalCity = capitalCity;
        this.area = area;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getCode3() {
        return code3;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public String getArea() {
        return area;
    }

    public String getContinent() {
        return continent;
    }
}
