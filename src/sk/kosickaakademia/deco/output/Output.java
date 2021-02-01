package sk.kosickaakademia.deco.output;

import sk.kosickaakademia.deco.entity.City;

import java.util.List;

public class Output {
    public void printCities(List<City> list){
        for (City temp :
                list) {
            System.out.println(temp.getName()+" "+temp.getPopulation());
        }
    }
}
