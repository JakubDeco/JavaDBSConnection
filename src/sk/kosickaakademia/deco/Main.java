package sk.kosickaakademia.deco;


import sk.kosickaakademia.deco.entity.City;
import sk.kosickaakademia.deco.entity.Country;
import sk.kosickaakademia.deco.output.Output;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database=new Database("resource/config.properties");
        /*Output output = new Output();
        List<City> cities = database.getCities("germany");
        output.printCities(cities);
        Country country1 = database.getCountryInfo("Switzerland");
        output.printCountry(country1);

        database.getCountryCode3("slovakia");
        City city1 = new City("Kosice",239171,"Kosicky kraj","Slovakia");
        database.insertCity(city1);
        database.updatePopulation("afghanistan","kabul",4273001);*/
        database.getCapitalCities("europe");
    }
}
