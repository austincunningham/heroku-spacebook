package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Accounts extends Controller {
	public static void signup() {
		render();
	}

	public static void login() {
		render();
	}

	public static void logout() {

		session.clear();
		index();

	}

	public static void index() {
		render();
	}

	public static void register(String firstName, String lastName, String email, String password, String age, String nationality) {
		Logger.info(
				"The following user has been added -->" + firstName + " " + lastName + " " + email + " " + password + " " +age + " "+nationality);

		User user = new User(firstName, lastName, email, password, age, nationality);
		user.save();

		index();
	}
	/**
	 * checks input strings to against the session id values
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param age
	 * @param nationality
	 * @param id
	 */
	public static void editdetails(String firstName, String lastName, String email, String password, String age, String nationality,Long id) 
	{
		
        User user = User.findById(id);
        
        if (!firstName.isEmpty())
        {
        	user.firstName = firstName;
        	Logger.info(
    				"The following user first name has been edited -->" + user.firstName + " " + user.lastName);
        }
        if (!lastName.isEmpty())
        {
        	user.lastName= lastName;
        	Logger.info(
    				"The following user last name has been edited -->" + user.firstName + " " + user.lastName);
        }
        if (!email.isEmpty())
        {
        	user.email= email;
        	Logger.info(
    				"The following user email has been edited -->" + user.firstName + " " + user.lastName+ " to " +user.email);
        }
        if (!age.isEmpty()) 
        {
        	user.age = age;
        	Logger.info(
    				"The following user age has been edited -->" + user.firstName + " " + user.lastName + " to " +user.age);
        }
        if (!nationality.isEmpty())
        {
        	user.nationality = nationality;
        	Logger.info(
    				"The following user nationality has been edited -->" + user.firstName + " " + user.lastName + " to " +user.nationality);
        }
        if( !password.isEmpty())
        {
        	user.password = password;
        	Logger.info(
    				"The following user password has been edited -->" + user.firstName + " " + user.lastName+ " to " +user.password);
        }
		user.save();

	Profile.index();
	}

	public static void authenticate(String email, String password) {
		Logger.info("Attempting to authenticate with " + email + ":" + password);

		User user = User.findByEmail(email);
		if ((user != null) && (user.checkPassword(password) == true)) {
			Logger.info("Authentication successful");
			session.put("logged_in_userid", user.id);
			Home.index();
		} else {
			Logger.info("Authentication failed");
			login();
		}
	}
    
	public static User getLoggedin()
	{
		User user = null;
		if (session.get("logged_in_userid") != null)
		{
			String userId = session.get("logged_in_userid");
	        user = User.findById(Long.parseLong(userId));
			
		}
		else
		{
			index();
		}
	    return user;
	}
}