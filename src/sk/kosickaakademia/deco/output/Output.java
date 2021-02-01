package sk.kosickaakademia.deco.output;

import sk.kosickaakademia.deco.entity.City;
import sk.kosickaakademia.deco.entity.Country;

import java.util.List;

public class Output {
    public void printCities(List<City> list){
        for (City temp :
                list) {
            System.out.println(temp.getName()+" "+temp.getPopulation());
        }
    }

    public void printCountry(Country country){
        if (country == null) {
            System.out.println("Country does not exist.");
            return;
        }
        System.out.println("Country: "+country.getName());
        System.out.println("Code: "+country.getCode3());
        System.out.println("Capital: "+country.getCapitalCity());
        System.out.println("Area: "+country.getArea());
        System.out.println("Continent: "+country.getContinent());
    }
}
