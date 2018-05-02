
package socialnetwork;
import java.util.LinkedList;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;


import javafx.scene.layout.BorderPane;

import javafx.stage.Screen;
import socialnetwork.Post;
 import socialnetwork.LoginPage;
import socialnetwork.user;

public class SocialNetwork extends Application {

     public static Stage window;
     public static user currentUser = new user();
     public static Image image ;
     
     public static Button home_button;
     public static Button profile_button;
     
     public static final int hashTableSize =1000;
    public static LinkedList<user>[] usersHashTable = new LinkedList[hashTableSize];
    public static LinkedList<socialnetwork.Group>[] groupHashTable = new LinkedList[hashTableSize];
   
        //************************Hash Function*******************************************************************
    public static long  hashFunc(String user_name,int storageSize)
    {
        long  sum = 0;
        int intLength = user_name.length() / 4;
        for (int j = 0; j < intLength; j++) 
        {
            char c[] = user_name.substring(j * 4, (j * 4) + 4).toCharArray();
             long mult = 1;
            for (int k = 0; k < c.length; k++) 
            {
             sum += c[k] * mult;
             mult *= 256;
            }
        }
        char c[] = user_name.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) 
        {
            sum += c[k] * mult;
            mult *= 256;
        }

     return(Math.abs(sum) % storageSize);
    }
    
    public static user searchUsersHashTable(String user_name)
    {
        user found = null ;
       int hashNumber = (int)(hashFunc(user_name,hashTableSize));
       if(usersHashTable[hashNumber]==null) return found;
       else{
       
       for (user myUser:usersHashTable[hashNumber])
       {
           if(myUser.getUsername() == user_name)
           {
               found = myUser;
           }
       }
       }
       return found;    
    }  
    public static boolean addToHashTable (user user_name)
    {
       boolean added =false;
        if(searchUsersHashTable(user_name.getUsername())!=null)
            added = false;
        else
        {
            int hashNumber = (int)(hashFunc(user_name.getUsername(),hashTableSize));
            if(usersHashTable[hashNumber]==null)
            {
                usersHashTable[hashNumber] = new LinkedList();
                usersHashTable[hashNumber].add(user_name);
            }
            else
                usersHashTable[hashNumber].add(user_name);
            added =true;
        }
        return added;
    }
    public static user searchGroupsHashTable(String group_name)
    {
        socialnetwork.Group found = null ;
       int hashNumber = (int)(hashFunc(group_name,hashTableSize));
       if(groupHashTable[hashNumber]==null) return found;
       else{
       
       for (socialnetwork.Group myUser:groupHashTable[hashNumber])
       {
           if(myUser.getUsername() == group_name)
           {
               found = myUser;
           }
       }
       }
       return found;    
    }  
    public static boolean addToHashTable (user user_name)
    {
       boolean added =false;
        if(searchUsersHashTable(user_name.getUsername())!=null)
            added = false;
        else
        {
            int hashNumber = (int)(hashFunc(user_name.getUsername(),hashTableSize));
            if(usersHashTable[hashNumber]==null)
            {
                usersHashTable[hashNumber] = new LinkedList();
                usersHashTable[hashNumber].add(user_name);
            }
            else
                usersHashTable[hashNumber].add(user_name);
            added =true;
        }
        return added;
    }

     
     
    
    public static void main(String[] args) {
        image = new Image("https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fspecials-images.forbesimg.com%2Fdam%2Fimageserve%2F3b14fc4c32894049af413bd87e5025bb%2F960x0.jpg%3Ffit%3Dscale");
        currentUser.setProfilePicture(image);
         launch(args);     
    }
   
  
    
      
 //***********Profile Page functions******************************************************
    public static Scene profile (user x)
    {
        BorderPane profileBorderPane = new BorderPane();
        Scene Profile=new Scene(profileBorderPane,1000, 1000);
       // Profile.getStylesheets().add("Styles.css");
        return Profile;
    }
    //**********************************************************************************
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        //primaryStage.setTitle("Social Network");
        primaryStage_boundries_set_to_bounds_of_mainScreen(primaryStage);//set primaryStage boundaries to visible bounds of the main screen
       // window.setScene(homePage(getCurrentUser()));
       LoginPage login = new LoginPage();
       window.setScene(login.loginPage(window));
        window.show();    
    }
    public static void primaryStage_boundries_set_to_bounds_of_mainScreen(Stage primaryStage)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }
  
}

