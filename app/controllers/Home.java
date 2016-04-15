package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Home extends Controller {

    public static void index() 
    {
        User user = Accounts.getLoggedin();
        render(user);
    }
    public static void drop(Long id)
    {
        String userId = session.get("logged_in_userid");
        User user = User.findById(Long.parseLong(userId));

        User friend = User.findById(id);

        user.unfriend(friend);
        Logger.info("Dropping " + friend.email);
        index();
    }
    
    public static void byDate()
    {
    	String userId = session.get("logged_in_userid");
        User user = User.findById(Long.parseLong(userId));
        Logger.info("Sorting inbox conversations sender " + user.email);
        User.selectionSortDate(user.inbox);//it appears to sort
        for(Message y: user.inbox)
        {
      	  Logger.info("Render:"+y.createdOn);
        }//Logger confirms its sorted
        render(user);
    }
    
    public static void byUser()
    {
    	String userId = session.get("logged_in_userid");
        User user = User.findById(Long.parseLong(userId));
        Logger.info("Sorting inbox conversations sender " + user.email);
        User.selectionSortUser(user.inbox);//it appears to sort
        for(Message y: user.inbox)
        {
      	  Logger.info("Render:"+y.from.firstName);
        }//Logger confirms its sorted
        render(user);
    }
    
    public static void byConversation()
    {
    	String userId = session.get("logged_in_userid");
        User user = User.findById(Long.parseLong(userId));
        Logger.info("Sorting inbox conversations message" + user.email);
        User.selectionSort(user.inbox);//it appears to sort
        for(Message y: user.inbox)
        {
      	  Logger.info("Render:"+y.messageText);
        }//Logger confirms its sorted
        render(user);//can't render index as it doesn't sort
    }
    

}