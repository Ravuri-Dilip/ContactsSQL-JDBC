package com.techlabs.contact.test;

import java.sql.SQLException;
import java.util.Scanner;

import com.techlabs.contact.Contact;

public class ContactTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Contact contact = new Contact();
		contact.createConnection();
		Scanner input = new Scanner(System.in);
		String[] name;
		String fname, lname, email;
		Long number;
		char choice;
		boolean exit = false;
		do {
			System.out.println("Enter 1 to show the contacts.");
			System.out.println("Enter 2 to add a contact.");
			System.out.println("Enter 3 to update details of a contact.");
			System.out.println("Enter 4 to delete a contact.");
			System.out.println("Enter 5 to exit.");

			String s = input.next();
			choice = s.charAt(0);
			// add a switch statement
			switch (choice) {
			////////// To show contacts
			case '1':
				System.out.println("Case 1:(DISPLAY)");
				contact.display();
				break;

			//////// To add a contact
			case '2':
				System.out.println("Case 2:(ADD)");
				while (true) {
					System.out.println();
					name = getNameFromConsole(input);
					fname = name[0];
					lname = name[1];
					if (!contact.exists(fname, lname)) {
						break;
					}
					System.err.println("The contact already exists. Enter different first or last name");
				}
				number = getNumberFromConsole(input, contact);
				email = getEmailFromConsole(input, contact);

				contact.add(fname, lname, number, email);
				break;

			//////// To update contact details
			case '3':
				System.out.println("Case 3:(UPDATE)");
				name = getNameFromConsole(input);
				fname = name[0];
				lname = name[1];

				System.out.println(
						"(Enter 0 for number if number updation is not required\nEnter no for email if email updation is not required)");
				number = getNumberFromConsole(input, contact);
				email = getEmailFromConsole(input, contact);
				contact.update(fname, lname, number, email);
				break;

			//////// To delete a contact
			case '4':
				System.out.println("Case 4:(DELETE)");
				name = getNameFromConsole(input);
				fname = name[0];
				lname = name[1];
				contact.delete(fname, lname);
				break;
			case '5':
				System.out.println("Case 5: (Exit)");
				exit = true;
				break;

			default:
				System.err.println("Error! Pressed wrong key");
				break;
			}
			if (!exit) {
				System.out.println();
				System.out.println("Do you have another query?(yes to continue)");
				String a = input.next();
				if (!a.contentEquals("yes")) {
					System.out.println("NO more Queries!");
					break;
				}
			}
		} while (!exit);

		input.close();
		contact.closeConnection();

	}

	private static String[] getNameFromConsole(Scanner input) {
		String[] arr = new String[2];
		System.out.println("Enter first name:");
		arr[0] = input.nextLine();
		arr[0] = input.nextLine();
		System.out.println("Enter last name:");
		arr[1] = input.nextLine();
		return arr;
	}

	private static Long getNumberFromConsole(Scanner input, Contact contact)
			throws ClassNotFoundException, SQLException {
		Long num;
		while (true) {
			System.out.println();
			System.out.println("Enter number:");
			num = input.nextLong();
			if (!contact.numberExists(num)) {
				break;
			}
			System.err.println("The contact number already exists. Enter different number");
		}

		return num;
	}

	private static String getEmailFromConsole(Scanner input, Contact contact)
			throws ClassNotFoundException, SQLException {
		String email;
		while (true) {
			System.out.println();
			System.out.println("Enter email:");
			email = input.nextLine();
			email = input.nextLine();
			if (!contact.emailExists(email)) {
				break;
			}
			System.err.println("The contact email already exists. Enter different email");
		}

		return email;
	}

}
