package br.com.crud.aplicacao;

import java.util.Scanner;

import br.com.crud.user.User;
import br.com.crud.userdao.UserDAO;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String choice;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("O que deseja fazer?");
		System.out.println("1.Create\n2.Read\n3.Update\n4.Delete\n5.Exit");
		
		choice = scan.nextLine(); 
		UserDAO userDAO = new UserDAO();
		
		while(true) {
		
			switch(choice) {
				case "1":
					User newUser = new User();
					userDAO.create(newUser);
					break;
					
				case "2":
					userDAO.read();
					break; 
				
				case "3":
					userDAO.update();
					break;
				
				case "4":
					userDAO.delete();
					break;
				case "5":
					System.exit(0);
					break;
					
				default:
					System.out.println("Select a valid option, BRUH");
					break;
			}
			scan = new Scanner(System.in);
			
			System.out.println("\nO que deseja fazer?");
			System.out.println("\n1.Create\n2.Read\n3.Update\n4.Delete\n5.Exit");
			
			if(scan.hasNext())
				choice = scan.nextLine();
		}
	}
}
