package models;

import javax.persistence.Entity;
import play.db.jpa.Model;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.OneToMany;

import play.Logger;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;

import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "`User`") // This is necessary because User is a reserved word in
						// PostGreSQL
public class User extends Model {
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public String statusText;
	public String age;
	public String nationality;
	public Blob profilePicture;

	@OneToMany(mappedBy = "sourceUser")
	public List<Friendship> friendships = new ArrayList<Friendship>();

	@OneToMany(mappedBy = "to")
	public List<Message> inbox = new ArrayList<Message>();

	@OneToMany(mappedBy = "from")
	public List<Message> outbox = new ArrayList<Message>();

	public User(String firstName, String lastName, String email, String password, String age, String nationality)

	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.age = age;
		this.nationality = nationality;
	}

	public static User findByEmail(String email) {
		return find("email", email).first();
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public void befriend(User friend) 
	{
		if (friend == this)
		{
			Logger.info("Opps! You seem to have made a mistake in attempting to befriend yourself");
		}
		else if (friendshipsContains(friend))
		{
			Logger.info("You attempted to befriend " + friend.firstName + " who is already a friend");
		}
		else
		{
			Friendship friendship = new Friendship(this, friend);
			friendships.add(friendship);
			friendship.save();
			save();
		}
	}

    private boolean friendshipsContains(User friend)
    {
        for (int i = 0; i < friendships.size(); i++)
        {
            Friendship friendship = friendships.get(i);
            User targetUser = friendship.targetUser;
            if (friend == friendship.targetUser)
            {
                return true;
            }
        }
        return false;
    }
    
	public void unfriend(User friend) {
		Friendship thisFriendship = null;

		for (Friendship friendship : friendships) {
			if (friendship.targetUser == friend) {
				thisFriendship = friendship;
			}
		}
		friendships.remove(thisFriendship);
		thisFriendship.delete();
		save();
	}

	public void sendMessage(User to, String messageText, String subject, Date date) {
		Message message = new Message(this, to, messageText, subject, date);
		outbox.add(message);
		to.inbox.add(message);
		message.save();
	}

	public static void selectionSort(List<Message> m) {
		for (int i = 0; i < m.size(); i += 1) {
			for (int j = i; j < m.size(); j += 1) {
				if ((m.get(j).messageText.compareToIgnoreCase(m.get(i).messageText) < 0)) {
					swap(m, i, j);
					Logger.info("Swaping (" + m.get(j).messageText + ") with (" + m.get(i).messageText + ")");

				}
			}

		}
		for (Message y : m) {
			Logger.info("Sorted:" + y.messageText);
		}
	}

	private static void swap(List<Message> list, int to, int from) {
		Collections.swap(list, to, from);
	}

	public static void selectionSortUser(List<Message> m) {
		for (int i = 0; i < m.size(); i += 1) {
			for (int j = i; j < m.size(); j += 1) {
				if ((m.get(j).from.firstName.compareToIgnoreCase(m.get(i).from.firstName) < 0)) {
					swap(m, i, j);
					Logger.info("Swaping (" + m.get(j).from.firstName + ") with (" + m.get(i).from.firstName + ")");

				}
			}

		}
		for (Message y : m) {
			Logger.info("Sorted:" + y.from.firstName);
		}
	}

	public static void selectionSortDate(List<Message> m) {
		for (int i = 0; i < m.size(); i += 1) {
			for (int j = i; j < m.size(); j += 1) {
				if ((m.get(j).createdOn.compareTo(m.get(i).createdOn) < 0)) {
					swap(m, i, j);
					Logger.info("Swaping (" + m.get(j).createdOn + ") with (" + m.get(i).createdOn + ")");

				}
			}

		}
		for (Message y : m) {
			Logger.info("Sorted:" + y.createdOn);
		}
	}

	public static void selectionSortOutbox(List<User> users) {
		for (int i = 0; i < users.size(); i += 1) {
			for (int j = i; j < users.size(); j += 1) {
				if ((users.get(j).outbox.size() > users.get(i).outbox.size())) {
					swapUsers(users, i, j);
					Logger.info(
							"Swaping (" + users.get(j).outbox.size() + ") with (" + users.get(i).outbox.size() + ")");

				}
			}

		}
		for (User y : users) {
			Logger.info("Sorted:" + y.outbox.size());
		}
	}

	public static void selectionSortFriendships(List<User> users) {
		for (int i = 0; i < users.size(); i += 1) {
			for (int j = i; j < users.size(); j += 1) {
				if ((users.get(j).friendships.size() > users.get(i).friendships.size())) {
					swapUsers(users, i, j);
					Logger.info("Swaping (" + users.get(j).friendships.size() + ") with ("
							+ users.get(i).friendships.size() + ")");

				}
			}

		}
		for (User y : users) {
			Logger.info("Sorted:" + y.friendships.size());
		}
	}

	private static void swapUsers(List<User> list, int to, int from) {
		Collections.swap(list, to, from);
	}
}