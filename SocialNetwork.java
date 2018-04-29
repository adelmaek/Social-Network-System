
package socialnetwork;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;


import javafx.scene.layout.BorderPane;

import javafx.stage.Screen;
import socialnetwork.Post;

public class SocialNetwork extends Application {

     public static Stage window;
     public static user currentUser;
     static user Sama = new user() ;
     static Group egyfood = new Group();
     
    
    public static void main(String[] args) {
        Sama.setUsername("sama");
        Sama.setPassword("***");
        user friend = new user("Perry","ll",1);
        Sama.addFriend(friend);
        System.out.println(Sama.getNumberOfFriends());
        Informations Her = new Informations() ;
        Her.setGender(Informations.Gender.female);
        Her.setStatus(Informations.MaritalStatus.single);
        Her.addLanguage("french");
        Her.addLanguage("English");
        System.out.println(Her.getNumberOfLanguages());
        Her.setBio("I LOVE FOOD ");
        Her.setCity("CAIRO");
        Her.setSchool("NDA");
        Her.setCollege("ENG ASU");
        Her.setBirth_place("Egypt");
        Her.setWork("General Manager");
        Date_Of_Birth date_of_birth = new Date_Of_Birth(13,9,1996);
        Her.setDateOfBirth(date_of_birth);
        Sama.setInfo(Her);
        System.out.println(Sama.toString());
        egyfood.setName("Egyptian Foodies");
        egyfood.add_member(Sama);
        egyfood.add_member(friend);
        egyfood.add_post(new Post("Hello","Mark"));
        egyfood.add_post(new Post("Ana Ga3an","Mark"));
        egyfood.setGroup_info("We search for the best restaurants in Egypt");
        launch(args);
    }
   
  
    

    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        //primaryStage.setTitle("Social Network");
        // primaryStage_boundries_set_to_bounds_of_mainScreen(primaryStage);//set primaryStage boundaries to visible bounds of the main screen
       // window.setScene(homePage(getCurrentUser()));
        // LoginPage login = new LoginPage();
       //window.setScene(login.loginPage(window));
        window.setTitle("Social Network");
        window.setScene(Group.Groups(egyfood));//Profile(Sama,true));//
        window.setMaximized(true);
        window.show();
    }
    private static void primaryStage_boundries_set_to_bounds_of_mainScreen(Stage primaryStage)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }
  
}

