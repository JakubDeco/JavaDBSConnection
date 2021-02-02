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
        //todo divide language select into separate query
        //JSON_EXTRACT(doc, '$.geography.SurfaceArea') keys ARE CASE SENSITIVE!!!!
        String query = "Select country.name, country.code, city.name, language, " +
                "JSON_UNQUOTE(JSON_EXTRACT(doc, '$.geography.Continent')) AS continent, " +
                "JSON_EXTRACT(doc, '$.geography.SurfaceArea') AS area " +
                "FROM country " +
                "INNER JOIN city ON country.capital=city.id " +
                "INNER JOIN countryinfo ON country.code = countryinfo._id " +
                "INNER JOIN countrylanguage on country.Code = countrylanguage.CountryCode " +
                "WHERE country.name LIKE ? AND countrylanguage.IsOfficial='T'";

        Country resultCountry = null;

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setNString(1,country);
            ResultSet resultSet = ps.executeQuery();

            String code3 = null;
            String city = null;
            String continent = null;
            int area = 0;
            List<String> languages = new ArrayList<>();

            while (resultSet.next()){
                code3=resultSet.getString("country.code");
                city=resultSet.getString("city.name");
                area=resultSet.getInt("area");
                continent=resultSet.getString("continent");
                languages.add(resultSet.getString("language"));
            }
            resultCountry = new Country(country, code3, city, area, continent, languages);

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return resultCountry;
    }

    public String getCountryCode3(String country){
        String code3 = null;
        if (country == null || country.isEmpty() || country.isBlank()) {
            return code3;
        }
        country = country.trim();

        try {
            Connection connection = getConnection();
            String query="select code from country " +
                    "where name like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setNString(1,country);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                code3 = resultSet.getString("code");
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return code3;
    }

    public void insertCity(City city){
        String country = city.getCountry();
        String code3 = getCountryCode3(country);

        if (code3 == null) System.out.println("Country "+country+" does not exist!");

        String query = "insert into city(name, countrycode, district, info) values(?,?,?,?)";
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setNString(1, city.getName());
            ps.setNString(2, code3);
            ps.setNString(3, city.getDistrict());
            ps.setNString(4, "{\"Population\":"+city.getPopulation()+"}");

            System.out.println(ps.executeUpdate());

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
