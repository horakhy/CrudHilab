package br.com.crud.jsonhandler;

import java.io.BufferedWriter;
import java.io.File;
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
		
		try{
			  FileWriter fstream = new FileWriter("userList.json",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(objJson.toString() + "\n\n");
			  out.close();
		  }catch (Exception e){
			 System.err.println("Error while writing to file: " +
		          e.getMessage());
		  }
		
				
//		try(FileWriter file = new FileWriter("userList.json")){
//			if(file.)
//			file.write(objJson.toString());
//			file.flush();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}	
//		System.out.println(objJson);
		
//		File log = new File("userList.json");
//
//				try{
//				    if(!log.exists()){
//				        System.out.println("We had to make a new file.");
//				        log.createNewFile();
//				    }
//
//				    FileWriter fileWriter = new FileWriter(log, true);
//
//				    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//				    bufferedWriter.write("******* " + timeStamp.toString() +"******* " + "\n");
//				    bufferedWriter.close();
//
//				    System.out.println("Done");
//				} catch(IOException e) {
//				    System.out.println("COULD NOT LOG!!");
//				}
	}
}
