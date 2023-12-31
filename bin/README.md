setup
==================

This is a module that has only servlets for updating datastore.

#Adding servlet
Declare it in the web.xml. Set the link as: /internals/setup-project-name


#Adding entities
Do not forget to update the OfyHelper class. 
Create your DAO class and the corresponding methods in DaoFactory class.


#Deploy
To deploy in another project, change the appengine.project.name property in pom.


#DELETE FROM APPENGINE WHEN YOU ARE DONE!!