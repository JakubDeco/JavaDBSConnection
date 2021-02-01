package sk.kosickaakademia.deco;

import sk.kosickaakademia.deco.entity.City;

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
    //todo delete sensitive information

    public List<City> getCities(String country){
        loadConfig("resource/config.properties");
        String query = "select city.name, JSON_EXTRACT(info,'$.Population') as population from city " +
                "inner join country on city.countryCode=country.code " +
                "where country.name like ? " +
                "order by population desc";
        List<City> cities = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setNString(1,country);
            //System.out.println(ps.toString());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                int population = resultSet.getInt("population");
                cities.add(new City(name, population));
                System.out.println(name+" "+population);
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    private void loadConfig(String filepath){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filepath);

            Properties properties=new Properties();

            if (inputStream == null) {
                System.out.println("Config failed to load.");
                return;
            }

            properties.load(inputStream);

            url=properties.getProperty("url");
            user=properties.getProperty("user");
            password=properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
