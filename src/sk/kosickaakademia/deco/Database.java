package sk.kosickaakademia.deco;

import java.sql.*;

public class Database {
    public void showCities(String country){
        String url="";
        String user="";
        String password="";
        String query="select city.name, countryCode from city " +
                "inner join country on city.countryCode=country.code " +
                "where country.name like ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setNString(1,country);
            //System.out.println(ps.toString());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                String colName=resultSet.getString("name");
                String colCountryCode=resultSet.getString("countryCode");
                System.out.println(colName+" "+colCountryCode);
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
