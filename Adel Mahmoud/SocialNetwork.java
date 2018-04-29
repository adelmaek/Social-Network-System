
package socialnetwork;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import socialnetwork.Post;

public class SocialNetwork extends Application {

     Stage window;
     Label userNameLabel;
     Button homeButton;
     Button profileButton;
     TextField  searchTxtBox;
     Button friendsListButton;
     Button groupsListButton;
     private static final int postsMargin = 50;
     TextField statusBar;
     Button postStatusFromStatusBarButton;
     Pane upperHMenue;
     BorderPane  borderPane;
     Pane centerPane;
     ScrollPane CenterPostsPane;
     Group postsGroup;
     Button searchButton;
      Scene homePageScene;
      Scene profileScene;
     
    
    public static void main(String[] args) {
         launch(args);
         
    }
   
   //***********HOME Page Functions****************************************************
    public  Scene homePage (user currentUser)
    {
        borderPane = new BorderPane ();
        setUpperHMnue();
        borderPane.setTop(upperHMenue);
       
       
        friendsListButton = new Button();
        friendsListButton.setText("My Friends");
        friendsListButton.setPrefSize(250,10);
        groupsListButton = new Button();
        groupsListButton.setText("My Groups");
        groupsListButton.setPrefSize(250,10);
        VBox leftVMenu = new VBox();
        leftVMenu.setStyle("-fx-background-color: #AB4642");
        leftVMenu.getChildren().addAll(groupsListButton,friendsListButton);
        borderPane.setLeft(leftVMenu);
        
        
        setCenterPane();
        //**********************************************************Adding Posts*****************************************************
        Post p1 = new Post("postTextaaa aaaa  aaaa aa aa aa aaa \naaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaa\n aa \n aaaaaaaaaaaaa","user 1");
        Post p2 = new Post("postTextaaa aaaa  aaaa aa aa aa aaa \naaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaa\n aa \n aaaaaaaaaaaaa","user 2");
        Post p3 = new Post("postTextaaa aaaa  aaaa aa aa aa aaa \naaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaa\n aa \n aaaaaaaaaaaaa","user 3");
        Post p4 = new Post("postTextaaa aaaa  aaaa aa aa aa aaa \naaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaa\n aa \n aaaaaaaaaaaaa","user 4");

        postsGroup = new Group();
        
        Pane myPane = p1.printAPost();
        myPane.setLayoutX(10);
        myPane.setLayoutY(10);
        postsGroup.getChildren().add(myPane);
        
        
        Pane myPane2 = p2.printAPost();
        myPane2.setLayoutX(10);
        myPane2.setLayoutY(10+1*Post.getPostHeight()+postsMargin);
        postsGroup.getChildren().add(myPane2);
       
        Pane myPane3 = p3.printAPost();
        myPane3.setLayoutX(10);
        myPane3.setLayoutY(10+2*Post.getPostHeight()+2*postsMargin);
        postsGroup.getChildren().add(myPane3);
        Pane myPane4 = p4.printAPost();
        myPane4.setLayoutX(10);
        myPane4.setLayoutY(10+3*Post.getPostHeight()+3*postsMargin);
        postsGroup.getChildren().add(myPane4);
        
        CenterPostsPane.setContent(postsGroup);
//**********************************************************************************************************************************************
        homePageScene  = new Scene(borderPane);
        return homePageScene;
    }
      void setCenterPane()
    {
        centerPane = new Pane();
        centerPane.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width:0;" + "-fx-border-insets: 0;"
       + "-fx-border-radius: 0;" + "-fx-border-color: #ECF0F1;"+"-fx-background-color: #ECF0F1;"+"flex-direction: column;");
        borderPane.setCenter(centerPane);
        
        statusBar = new TextField();
        statusBar.setPrefSize(1000, 60); 
        statusBar.setLayoutX(10);
        statusBar.setLayoutY(10); 
        statusBar.setPromptText("What's in your mind?...");
        centerPane.getChildren().add(statusBar);
        
        postStatusFromStatusBarButton =new Button();
        postStatusFromStatusBarButton.setLayoutX(800);
        postStatusFromStatusBarButton.setLayoutY(75);
        postStatusFromStatusBarButton.setText("Post");
        postStatusFromStatusBarButton.setPrefSize(60,40);
        centerPane.getChildren().add(postStatusFromStatusBarButton);
        postStatusFromStatusBarButton.setOnAction(e->{
        //implement Post a post here
        });
         
         
        CenterPostsPane = new ScrollPane();
        CenterPostsPane.setLayoutX(10);
        CenterPostsPane.setLayoutY(120);
        CenterPostsPane.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width:0;" + "-fx-border-insets: 0;"
       + "-fx-border-radius: 0;" + "-fx-border-color: #ECF0F1;"+"-fx-background-color: #ECF0F1;"+"flex-direction: column;");
        CenterPostsPane.setPrefSize(Post.getPostWidth()+100,500);
        CenterPostsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        //CenterPostsPane.setFitToHeight(true);
        //CenterPostsPane.setFitToWidth(true);
        CenterPostsPane.setVmax(1);
        
        centerPane.getChildren().add(CenterPostsPane);
    }
      void setUpperHMnue()
    {
        upperHMenue = new Pane();
        upperHMenue.setPadding(new Insets(15, 12, 15, 12));
        
        
        userNameLabel = new Label("User_name_Label");
        userNameLabel.setLayoutX(850);
        userNameLabel.setLayoutY(10);
        homeButton = new Button();
        homeButton.setText("Home");
        homeButton.setLayoutX(1000);
        homeButton.setLayoutY(10);
        homeButton.setPrefSize(100,10);
        homeButton.setOnAction(e->{
            //implement "go to home " here
        });
        
        profileButton = new Button();
        profileButton.setText("Profile");
        profileButton.setPrefSize(100,10);
        profileButton.getStyleClass().add("border-blue");
        profileButton.setLayoutX(1110);
        profileButton.setLayoutY(10);
        profileButton.setOnAction(e->{
            //implement "go to profile " here
            window.setScene(profile(getCurrentUser()));
            window.show();
            
        });
        
        searchTxtBox=new TextField();
        searchTxtBox.setPromptText("search here...");
        searchTxtBox.setAlignment(Pos.CENTER);
       searchTxtBox.setLayoutX(500);
       searchTxtBox.setLayoutY(10);
       
        searchButton = new Button();
        searchButton.setText("Search");
        searchButton.setLayoutX(500 +5 +150);
        searchButton.setLayoutY(10);
        searchButton.setOnAction(e->{
            //implement search here
        });
        
        upperHMenue.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
        + "-fx-border-radius: 1;" + "-fx-border-color: #AB4642;"+"-fx-background-color: #AB4642;");
        
        upperHMenue.getChildren().addAll(userNameLabel,homeButton,profileButton,searchTxtBox,searchButton);
    }
   //************************************************************************************   
      
 //***********Profile Page functions******************************************************
    public static Scene profile (user x)
    {
        BorderPane profileBorderPane = new BorderPane();
        Scene Profile=new Scene(profileBorderPane,1000, 1000);
       // Profile.getStylesheets().add("Styles.css");
        return Profile;
    }
    //**********************************************************************************
   
    user getCurrentUser()
    {
        user loggedInUser = new user();
        //implement  get Current User Here;
        return loggedInUser;
    }
    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("Social Network");
        primaryStage_boundries_set_to_bounds_of_mainScreen(primaryStage);//set primaryStage boundaries to visible bounds of the main screen
        window.setScene(homePage(getCurrentUser()));
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


