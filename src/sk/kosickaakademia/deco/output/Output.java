package sk.kosickaakademia.deco.output;

import sk.kosickaakademia.deco.entity.CapitalCity;
import sk.kosickaakademia.deco.entity.City;
import sk.kosickaakademia.deco.entity.Country;
import sk.kosickaakademia.deco.entity.Monument;

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

        System.out.print("Official languages: ");
        List<String> languages = country.getLanguages();
        for (int i = 0; i < country.getLanguages().size(); i++) {
            if (i != 0) System.out.print(", "+languages.get(i));
            else System.out.print(languages.get(i));
        }
        System.out.println();
    }

    public void printCapitalCities(List<CapitalCity> list){
        if (list == null || list.isEmpty())
            return;
        for (CapitalCity temp :
                list) {
            System.out.println(temp.getCountry()+"  "+temp.getName()+"  "+temp.getPopulation());
        }
    }

    public void printMonuments(List<Monument> list){
        if (list == null || list.isEmpty())
            return;

        for (Monument temp :
                list) {
            System.out.println(temp.getCity()+"  "+temp.getCountry()+"  "+temp.getName()+"  id:"+temp.getId());
        }
    }
}
