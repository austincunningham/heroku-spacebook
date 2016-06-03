# README Spacebook #

### What is this repository for? ###

* Quick summary: heroku version of Play 1.4 Social media app Spacebook written in java
* [Click here for live site](http://spacebook-austin.herokuapp.com)

### Image of user Home page ###

![spacebook.JPG](https://bitbucket.org/repo/6kK9K8/images/2198659219-spacebook.JPG)



### How do I get set up? ###

* Summary of set up
* live version of app at http://spacebook-austin.herokuapp.com/
* Configuration: clone repo, play run , access localhost:9000 on browser
* Dependencies: Need Play framework 1.4 or greater 
* Database configuration:locally need to configure H2:mem 
edit the file spacebook\conf\application.conf 
comment line 
#-----------------------clearDB--------------------------------------------------
db.default=mysql://b51a491b1e270d:91814849@us-cdbr-iron-east-03.cleardb.net/heroku_42821ad588d10e2?reconnect=true
and uncomment line
#db.default=mem

Live version on Heroku configured with MySQL no config needed


### Who do I talk to? ###

* Repo owner or admin
* Austin Cunningham austincunningham@oceanfree.net