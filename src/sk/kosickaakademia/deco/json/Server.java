package sk.kosickaakademia.deco.json;

import org.json.simple.*;
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
}
