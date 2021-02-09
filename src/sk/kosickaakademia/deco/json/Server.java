package sk.kosickaakademia.deco.json;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.kosickaakademia.deco.Database;
import sk.kosickaakademia.deco.entity.Monument;

import java.util.List;

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
        JSONObject jsonObject = null;
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
}
