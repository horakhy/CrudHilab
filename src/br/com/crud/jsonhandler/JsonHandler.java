package br.com.crud.jsonhandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.crud.ConnectionFactory.ConnectionFactory;
import br.com.crud.user.User;

public class JsonHandler {
	private static FileWriter file;
	
	public static void createJsonFile() throws JSONException, ClassNotFoundException, SQLException {
		
		String sql = "SELECT * FROM users";
		Map json = new HashMap();
		List list = new ArrayList();

		Connection conn = ConnectionFactory.createConnectiontoMySQL();

		ArrayList<User> usersArray = new ArrayList<User>();

		Statement stt = conn.createStatement();
		ResultSet rs = stt.executeQuery(sql);

		if(rs!=null)
		{
			try {
				ResultSetMetaData metaData = rs.getMetaData();
				while(rs.next())
				{
					Map<String,Object> columnMap = new HashMap<String, Object>();
					for(int columnIndex=1;columnIndex<=metaData.getColumnCount();columnIndex++)
					{
						if(rs.getString(metaData.getColumnName(columnIndex))!=null)
							columnMap.put(metaData.getColumnLabel(columnIndex), rs.getString(metaData.getColumnName(columnIndex)));
						else
							columnMap.put(metaData.getColumnLabel(columnIndex), "");
					}
					list.add(columnMap);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			json.put("users", list);
		}
		try {

			// Constructs a FileWriter given a file name, using the platform's default charset
			file = new FileWriter("userList.json");
			file.write(JSONValue.toJSONString(json));
			System.out.println("Successfully Copied JSON Object to File...");


		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
}
