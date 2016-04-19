package models;

import javax.persistence.*;
import play.db.jpa.*;
import java.util.Date;
import java.text.SimpleDateFormat;

@Entity
public class Message extends Model
{
  public String messageText;
  public String subject;

  @ManyToOne
  public User from;

  @ManyToOne
  public User to;
  public Date createdOn;

  public Message(User from, User to, String messageText, String subject, Date date)
  {
    this.from = from;
    this.to = to;
    this.messageText = messageText;
    this.subject = subject;
    this.setCreatedOn(new Date());

  }

  public Date getCreatedOn()
  {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn)
  {
    this.createdOn = createdOn;
  }
}
