package br.com.crud.userdao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;

import br.com.crud.ConnectionFactory.ConnectionFactory;
import br.com.crud.jsonhandler.JsonHandler;
//import br.com.crud.jsonhandler.JsonHandler;
import br.com.crud.user.User;

public class UserDAO {
	// CRUD:
	//	(V) Create
	//	(V) Read
	//	() Update
	//	() Delete

	// Create the user and initializes its values
	public void create(User user) throws ClassNotFoundException, SQLException{

		// Variables that're going to store the input data
		String[] name;;
		String date;
		String email;
		int jobChoice;

		// Maping the job choices
		Map<Integer, String> jobs = new HashMap<Integer, String>();
		jobs.put(1, "Desenvolvedor");
		jobs.put(2, "BDA");
		jobs.put(3, "Gerente de Sistemas");
		jobs.put(4, "Arquiteto de software");

		Scanner scan = new Scanner(System.in);

		//user.setId(123);

		// Checks and stores the name 
		do {
			System.out.print("Name (i.e Raian Gomes): ");
			scan = new Scanner(System.in);
			String inputName = scan.nextLine();
			name = getFirstAndLastName(inputName);
		}while(name.length < 2);

		String fullName = "";
		for(String n: name)
			fullName = fullName + n;

		user.setName(fullName);

		// Validates the date
		do {
			System.out.print("Birth Date (dd/MM/yyyy): ");
			scan = new Scanner(System.in);
			date = scan.nextLine();		
		}while(!validateDate(date));

		// Sets the valid date
		user.setBirthDate(date);


		// Validates the email and stores it if it's valid
		do {
			System.out.print("Email (hello@email.com): ");
			scan = new Scanner(System.in);
			email = scan.nextLine();
		}while(!validateEmail(email));

		// Sets the valid email
		user.setEmail(email);

		do{
			System.out.print("Job\n1.Desenvolvedor\n2.BDA\n3.Gerente de Sistemas\n4.Arquiteto de software\n");
			scan = new Scanner(System.in);
			jobChoice = Integer.parseInt(scan.nextLine());

		}while(!validateJob(jobChoice));

		user.setJob(jobs.get(jobChoice));

		save(user);


	}

	public void save(User user) {
		String sql = "INSERT INTO users (name, birth_date, email, job)"
				+ " VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Creating a connection with MySQL DB
			conn = ConnectionFactory.createConnectiontoMySQL();

			// Create PreparedStatement to execute the query
			pstm = (PreparedStatement)conn.prepareStatement(sql);

			pstm.setString(1, user.getName());

			pstm.setString(2, user.getBirthDate());

			pstm.setString(3, user.getEmail());

			pstm.setString(4, user.getJob());

			pstm.execute();

			JsonHandler.createJsonFile();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	// Return the values on the DB
	public void read() throws Exception {
		String sql = "SELECT * FROM users";

		try {
			Connection conn = null;
			PreparedStatement pstm = null;

			// Creating a connection with MySQL DB
			conn = ConnectionFactory.createConnectiontoMySQL();

			// Create PreparedStatement to execute the query
			pstm = (PreparedStatement)conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1) System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(rsmd.getColumnName(i) + ":" + " " + columnValue);
				}
				System.out.println("");
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Update a value from the DB
	public void update() {
		// Variables that're going to store the input data
		String[] name;;
		String date;
		String email;
		int jobChoice;

		String id;
		String fieldChoice;
		Scanner scan = new Scanner(System.in);
		Map<Integer, String> jobs = new HashMap<Integer, String>();
		jobs.put(1, "Desenvolvedor");
		jobs.put(2, "BDA");
		jobs.put(3, "Gerente de Sistemas");
		jobs.put(4, "Arquiteto de software");

		Map<Integer, String> fields= new HashMap<Integer, String>();
		fields.put(1, "Name");
		fields.put(2, "Birth_Date");
		fields.put(3, "Email");
		fields.put(4, "Arquiteto de software");

		System.out.println("Qual o id do usuário a ser modificado?");
		id = scan.nextLine();

		// Checks and stores the name 
		do {
			System.out.print("Name (i.e Raian Gomes): ");
			scan = new Scanner(System.in);
			String inputName = scan.nextLine();
			name = getFirstAndLastName(inputName);
		}while(name.length < 2);

		String fullName = "";
		for(String n: name)
			fullName = fullName + n;

		// Validates the date
		do {
			System.out.print("Birth Date (dd/MM/yyyy): ");
			scan = new Scanner(System.in);
			date = scan.nextLine();		
		}while(!validateDate(date));

		// Validates the email and stores it if it's valid
		do {
			System.out.print("Email (hello@email.com): ");
			scan = new Scanner(System.in);
			email = scan.nextLine();
		}while(!validateEmail(email));

		do{
			System.out.print("Job\n1.Desenvolvedor\n2.BDA\n3.Gerente de Sistemas\n4.Arquiteto de software\n");
			scan = new Scanner(System.in);
			jobChoice = Integer.parseInt(scan.nextLine());

		}while(!validateJob(jobChoice));

		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = "UPDATE users SET name = ?,birth_date = ?,email = ?,job = ? WHERE id = ?";

		try {
			// Creating a connection with MySQL DB
			conn = ConnectionFactory.createConnectiontoMySQL();

			// Create PreparedStatement to execute the query
			pstm = (PreparedStatement)conn.prepareStatement(sql);

			pstm.setString(1, fullName);

			pstm.setString(2, date);

			pstm.setString(3, email);

			pstm.setString(4, jobs.get(jobChoice));

			pstm.setString(5, id);

			pstm.execute();

			JsonHandler.createJsonFile();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Id Inválido!");
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// Deletes a user from the file
	public void delete() {
		String id;
		Scanner scan = new Scanner(System.in);
		System.out.println("Qual o id do usuário a ser deletado?");
		id = scan.nextLine();

		Connection conn = null;
		PreparedStatement pstm = null;
		String sql = "DELETE FROM users WHERE id = ?";

		try {
			// Creating a connection with MySQL DB
			conn = ConnectionFactory.createConnectiontoMySQL();

			// Create PreparedStatement to execute the query
			pstm = (PreparedStatement)conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.execute();

			JsonHandler.createJsonFile();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e){
			e.printStackTrace();
		}


	}

	public static boolean validateDate(String strDate)
	{
		// Check if date is 'null' 
		if (strDate.trim().equals(""))
		{
			System.out.println("Invalid Date format");
			return false;
		}
		// Date is not 'null' 
		else
		{
			/*
			 * Set preferred date format,
			 * For example dd-MM-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
			sdfrmt.setLenient(false); 
			/* Create Date object
			 * parse the string into date 
			 */
			try
			{
				Date javaDate = sdfrmt.parse(strDate); 
				//System.out.println(strDate + " is a valid date format");
			}
			// Date format is invalid 
			catch (ParseException e)
			{
				System.out.println(strDate + " is an invalid Date format");
				return false;
			}
			// Return true if date format is valid 
			return true;
		}
	}

	public static boolean validateEmail(String email) {

		String regex = "^(.+)@(.+).com$";  

		// Compile regular expression to get the pattern  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(email);

		if(matcher.matches())
			return true;

		System.out.println("Invalid Email format");
		return false;
	}

	public static boolean validateJob(int job) {

		for (int i = 1; i <= 4; i++) {
			if(job == i) 
				return true;
		}
		System.out.println("Invalid Choice");
		return false;
	}

	public String[] getFirstAndLastName(String inputName) {
		inputName = inputName.trim();
		String[] name = inputName.split(" ");

		if ( name[0] == null || name[0] == "") {
			System.out.println("Invalid Name Format"); 
			return name; 
		}

		String lastName = name[name.length-1];
		String[] fullName = new String[3];


		if (name.length > 1){
			fullName[0] = name[0];
			fullName[1] = " ";
			fullName[2] = lastName;
			return fullName;            
		}
		else {
			System.out.println("Invalid Name Format");
			return name;
		}
	}
}
