package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;
import play.db.jpa.Blob;

public class Profile extends Controller 
{

	public static void index() 
	{
		User user = Accounts.getLoggedin();
		render(user);
	}
	
	public static void EditDetails() 
	{
		String userId = session.get("logged_in_userid");
        User user = User.findById(Long.parseLong(userId));
		//User user = User.findById(id);
	    Logger.info("Editing details for "+user.firstName+" "+user.lastName);
		render(user);
	}

	public static void message(String profiletext) 
	{
		Logger.info("Message has been added --> " + profiletext);
		index();
	}
	public static void changeStatus(String profiletext)
	{
		String userId = session.get("logged_in_userid");
		User user = User.findById(Long.parseLong(userId));
		user.statusText = profiletext;
		user.save();
		Logger.info("Status changed to " + profiletext);
		index();
	} 
	
	public static void getPicture(Long id) 
	  {
	    User user = User.findById(id);
	    Blob picture = user.profilePicture;
	    if (picture.exists())
	    {
	      response.setContentTypeIfNotSet(picture.type());
	      renderBinary(picture.get());
	    }
	  }

	  public static void uploadPicture(Long id, Blob picture)
	  {
	    User user = User.findById(id);
	    user.profilePicture = picture;
	    user.save();
	    index();
	  }
}