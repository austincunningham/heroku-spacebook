package controllers;

import play.*;
import play.mvc.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import models.*;



public class PublicProfile extends Controller {

    public static void index() {
        render();
    }
    public static void visit(Long id)
    {
      User user = User.findById(id);
      Logger.info("Just visiting the page for " + user.firstName + " "+ user.lastName);
      render(user);
    }
    public static void sendMessage(Long id, String messageText, String subject, Date d)
    {
      
    	 String userId = session.get("logged_in_userid");
    	 User fromUser = User.findById(Long.parseLong(userId));
    	 User toUser = User.findById(id);
    	 Date date = new Date();  	 
    	 Logger.info("Message from user " + 
    		        fromUser.firstName + ' ' + fromUser.lastName +" to " +
    		        toUser.firstName + ' ' + toUser.lastName +": " +
    		        messageText+ " "+subject+ " "+ date);  
    	 
    	 fromUser.sendMessage(toUser, messageText, subject , date);
       visit (id);
    }
}