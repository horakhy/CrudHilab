package br.com.crud.userdao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.crud.user.User;

/*public enum Jobs{
	Desenvolvedor, BDA, Gerente_de_Sistemas, Arquiteto_de_software;
}
 */

public class UserDAO {
	// CRUD:
	//	() Create
	//	() Read
	//	() Update
	//	() Delete

	// Create the user and initializes its values
	public void create(User user) {
		// Variables that're going to store the input data
		String[] name;
		String date;
		String email;
		int jobChoice;
		Map<Integer, String> jobs = new HashMap<Integer, String>();
		jobs.put(1, "Desenvolvedor");
		jobs.put(2, "BDA");
		jobs.put(3, "Gerente de Sistemas");
		jobs.put(4, "Arquiteto de software");
		Scanner scan = new Scanner(System.in);


		user.setId(123);

		// Checks and attributes the name 
		do {
			System.out.print("Name (i.e Raian Gomes): ");
			scan = new Scanner(System.in);
			String inputName = scan.nextLine();
			name = getFirstAndLastName(inputName);
		}while(name.length < 2);
		
		user.setName(name.toString());

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
			System.out.print("Job\n1.Desenvolvedor\n2.BDA\n3.Gerente de Sistemas\n4.Arquiteto de software");
			scan = new Scanner(System.in);
			jobChoice = Integer.parseInt(scan.nextLine());

		}while(!validateJob(jobChoice));
		
		user.setJob(jobs.get(jobChoice));
	}

	// Return the values on the file
	public void read() {

	}

	// Update a value on the file
	public void update() {

	}

	// Deletes a user from the file
	public void delete() {

	}

	public static boolean validateDate(String strDate)
	{
		/* Check if date is 'null' */
		if (strDate.trim().equals(""))
		{
			System.out.println("Invalid Date format");
			return false;
		}
		/* Date is not 'null' */
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
			/* Date format is invalid */
			catch (ParseException e)
			{
				System.out.println(strDate + " is an invalid Date format");
				return false;
			}
			/* Return true if date format is valid */
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
		String[] fullName = new String[100];


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
} // Andre  __
