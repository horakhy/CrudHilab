package br.com.crud.jsonhandler;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.crud.user.User;


public class JsonHandler {
	
	
	public static void createJsonFile(User user) throws JSONException {
		JSONObject objJson = new JSONObject();
		
		objJson.put("id", user.getId());
		objJson.put("name", user.getName());
		objJson.put("birth date", user.getBirthDate());
		objJson.put("email", user.getEmail());
		objJson.put("job", user.getJob());
		
		try(FileWriter file = new FileWriter("userList.json")){
			file.write(objJson.toString());
			file.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(obj);
	}
	
	
	
}
