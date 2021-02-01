package sk.kosickaakademia.deco;


import sk.kosickaakademia.deco.entity.City;
import sk.kosickaakademia.deco.output.Output;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database=new Database("resource/config.properties");
        Output output = new Output();
        List<City> cities = database.getCities("germany");
        output.printCities(cities);
        database.getCountryInfo("Italy");
    }
}
