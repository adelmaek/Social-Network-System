
package socialnetwork;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;


import javafx.scene.layout.BorderPane;

import javafx.stage.Screen;
import socialnetwork.Post;

public class SocialNetwork extends Application {

     public static Stage window;
     public static user currentUser;
     static Group egyfood = new Group();
     static Button home_button = new Button("Home");
     static Button profile_button = new Button("Profile");
    
    public static void main(String[] args) {
        user Sama = new user() ;
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
        currentUser=Sama;
        String test="Control System 2 Next section will be on Monday in 148\n" +
                "Section 3 & 2 from 33824 to 33941: 12:30pm\n" +
                "Section 1 & 2 from 33701 to 33823: 2:10pm\n" +
                "OS section is in 291\n" +
                "Section 3 & 2 from 33824 to 33941: 2:10pm\n" +
                "Section 1 & 2 from 33701 to 33823: 12:30pm";
        String test2 = "بكرة فيه شيفتين معمل للmultithreading و الdouble tank لسكشن 1 و 3\n" +
                "\n" +
                "دول اخر شيفتات للتجربتين دول\n" +
                "\n" +
                "اللي هيعوض عشان مجاش في ميعاده مش هياخد الدرجة كاملة هينقص درجة\n" +
                "\n" +
                "و المواعيد اول شيفت من 10 ل 1 و التاني من 1 و نص ل 4 و نص\n" +
                "\n" +
                "محدش يتأخر و محدش المفروض ييجي بدري ييجي متأخر\n" +
                "\n" +
                "اللي عندهم multithreading المفروض يعملوا اول 2 experiments في البيت قبل ما بيجو و بوريهملي في المعمل";
         Sama.add_post(new Post("hello", "sama"));
         Sama.add_post(new Post(test, "sama"));
         Sama.add_post(new Post(test2, "sama"));
        Sama.add_group(egyfood);
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

