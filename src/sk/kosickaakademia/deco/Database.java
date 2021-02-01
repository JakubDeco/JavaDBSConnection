package sk.kosickaakademia.deco;

import sk.kosickaakademia.deco.entity.City;
import sk.kosickaakademia.deco.entity.Country;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    private String url;
    private String user;
    private String password;

    public Database(String filepath){
        loadConfig(filepath);
    }

    public List<City> getCities(String country){
        String query = "select city.name, JSON_EXTRACT(info,'$.Population') as population from city " +
                "inner join country on city.countryCode=country.code " +
                "where country.name like ? " +
                "order by population desc";
        List<City> cities = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setNString(1,country);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                int population = resultSet.getInt("population");
                cities.add(new City(name, population));
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url,user,password);
    }

    private void loadConfig(String filepath){
        try {
            InputStream inputStream = new FileInputStream(filepath);

            Properties properties=new Properties();

            properties.load(inputStream);

            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Country getCountryInfo(String country){
        //JSON_EXTRACT(doc, '$.geography.SurfaceArea') keys ARE CASE SENSITIVE!!!!
        String query = "Select country.name, country.code, city.name, " +
                "JSON_UNQUOTE(JSON_EXTRACT(doc, '$.geography.Continent')) AS continent, " +
                "JSON_EXTRACT(doc, '$.geography.SurfaceArea') AS area " +
                "FROM country " +
                "INNER JOIN city ON country.capital=city.id " +
                "INNER JOIN countryinfo ON country.code = countryinfo._id " +
                "WHERE country.name LIKE ?";

        Country resultCountry = null;

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setNString(1,country);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                String code3=resultSet.getString("country.code");
                String city=resultSet.getString("city.name");
                int area=resultSet.getInt("area");
                String continent=resultSet.getString("continent");
                resultCountry = new Country(country, code3, city, area, continent);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return resultCountry;
    }
}
