
package socialnetwork;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;


import javafx.scene.layout.BorderPane;

import javafx.stage.Screen;
import socialnetwork.Post;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class SocialNetwork extends Application {

     public static Stage window;
     public static user currentUser;
     static Group egyfood = new Group();
     static Button home_button = new Button("Home");
     static Button profile_button = new Button("Profile");

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
    public static socialnetwork.Group  searchGroupsHashTable(String group_name)
    {
        socialnetwork.Group found = null ;
        int hashNumber = (int)(hashFunc(group_name,hashTableSize));
        if(groupHashTable[hashNumber]==null) return found;
        else{

            for (socialnetwork.Group myUser:groupHashTable[hashNumber])
            {
                if(myUser.getName() == group_name)
                {
                    found = myUser;
                }
            }
        }
        return found;
    }
    public static boolean addToHashTable (Group user_name)
    {
        boolean added =false;
        if(searchUsersHashTable(user_name.getName())!=null)
            added = false;
        else
        {
            int hashNumber = (int)(hashFunc(user_name.getName(),hashTableSize));
            if(groupHashTable[hashNumber]==null)
            {
                groupHashTable[hashNumber] = new LinkedList();
                groupHashTable[hashNumber].add(user_name);
            }
            else
                groupHashTable[hashNumber].add(user_name);
            added =true;
        }
        return added;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
   

    

    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        //primaryStage.setTitle("Social Network");
        // primaryStage_boundries_set_to_bounds_of_mainScreen(primaryStage);//set primaryStage boundaries to visible bounds of the main screen
       // window.setScene(homePage(getCurrentUser()));
        // LoginPage login = new LoginPage();
       //window.setScene(login.loginPage(window))
        window.setTitle("Social Network");
        window.setScene(Profile.Profile(currentUser));//Group.Groups(egyfood));//Profile(Sama,true));//
        window.setMaximized(true);
        profile_button.setOnAction(e-> {
            window.setScene(Profile.Profile(currentUser));
            window.setMaximized(true);
        });
        window.show();
    }
    public static void primaryStage_boundries_set_to_bounds_of_mainScreen(Stage primaryStage)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
    }
  
}

