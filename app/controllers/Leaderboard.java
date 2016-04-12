package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Leaderboard extends Controller
{
	public static void index() 
	{		
		User user = Accounts.getLoggedin();
        
        List<User> users = User.findAll();
		render(users);
	}

    public static void mostTalkative(Long id)
    {
    	String userId = session.get("logged_in_userid");
        User user = User.findById(Long.parseLong(userId));
    	
    	Logger.info("do you get to mostTalkative?");
    	List<User> users = User.findAll();
    	User.selectionSortOutbox(users);
    	for(User y: users)
        {
          
      	  Logger.info("Render:"+y.outbox.size());
      	  
        }
        render(users);
    }
    public static void mostSocial(Long id)
    {
    	Logger.info("do you get to mostSocial?");
    	List<User> users = User.findAll();
    	User.selectionSortFriendships(users);
        for(User y: users)
        {
      	  Logger.info("Render:"+y.friendships.size());
        }
        render(users);
    }

}
