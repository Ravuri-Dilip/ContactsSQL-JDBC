package com.techlabs.contact;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class ContactTestCase {

	@Test
	void testExists() throws ClassNotFoundException, SQLException {
		//
		boolean expected1 = true;
		boolean expected2 = false;

		//
		Contact contact = new Contact();
		contact.createConnection();
		boolean bool1 = contact.exists("Dilip", "Ravuri");
		boolean bool2 = contact.exists("hsd", "hksjdfhsf");
		contact.closeConnection();

		///
		assertEquals(expected1, bool1);
		assertEquals(expected2, bool2);
	}

	@Test
	void testExistsNumber() throws ClassNotFoundException, SQLException {
		//
		boolean expected1 = true;
		boolean expected2 = false;

		//
		Contact contact = new Contact();
		contact.createConnection();
		boolean bool1 = contact.numberExists(Long.parseLong("9346114115"));
		boolean bool2 = contact.numberExists((long) 1);
		contact.closeConnection();

		///
		assertEquals(expected1, bool1);
		assertEquals(expected2, bool2);
	}

	@Test
	void testExistsEmail() throws ClassNotFoundException, SQLException {
		//
		boolean expected1 = true;
		boolean expected2 = false;

		//
		Contact contact = new Contact();
		contact.createConnection();
		boolean bool1 = contact.emailExists("ravuri.dilip93@gmail.com");
		boolean bool2 = contact.emailExists("ndfj@gmail.com");
		contact.closeConnection();

		///
		assertEquals(expected1, bool1);
		assertEquals(expected2, bool2);
	}
}
