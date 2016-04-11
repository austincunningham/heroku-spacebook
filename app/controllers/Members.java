package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Members extends Controller {

    public static void index(Long id) 
    {
    	String userId = session.get("logged_in_userid");
    	User me = User.findById(Long.parseLong(userId));
    	
    	//User me = User.findById(id);
    	List<User> users = User.findAll();
    	users.remove(me);
        render(users);
    }
    public static void follow(String name)
    {
      Logger.info("Following " + name);
      render(name);
    }
    public static void follow(Long id)
    {
        User friend = User.findById(id);

        String userId = session.get("logged_in_userid");
        User me = User.findById(Long.parseLong(userId));

        me.befriend(friend);
        Logger.info("Following " + friend.firstName +" "+ friend.lastName);
        Home.index();
    }
}