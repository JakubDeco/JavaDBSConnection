package sk.kosickaakademia.deco.json;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.kosickaakademia.deco.Database;
import sk.kosickaakademia.deco.entity.Monument;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Server {
    public String getMonuments(){
        List<Monument> list = new Database("resource/config.properties").getMonuments();

        if (list.isEmpty())
            return "{}";

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Monument temp :
                list) {
            JSONObject newObject = new JSONObject();
            newObject.put("country",temp.getCountry());
            newObject.put("city",temp.getCity());
            newObject.put("name",temp.getName());
            newObject.put("id",temp.getId());

            jsonArray.add(newObject);
        }

        jsonObject.put("monuments", jsonArray);

        jsonObject.put("count", list.size());

        return jsonObject.toJSONString();
    }

    public boolean insertMonument(String jsonStr){
        if (jsonStr == null || jsonStr.isBlank())
            return false;

        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        String country = (String) jsonObject.get("country");
        String city = (String) jsonObject.get("city");
        String name = (String) jsonObject.get("name");

        Database database = new Database("resource/config.properties");

        return database.insertMonument(country,city,name);
    }

    public String jsonFileToString(String filePath){
        StringBuilder result = new StringBuilder();
        if (filePath == null || filePath.isBlank())
            return result.toString();

        Scanner scanner;
        try {
            scanner = new Scanner(new File(filePath));

        while (scanner.hasNextLine())
            result.append(scanner.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
