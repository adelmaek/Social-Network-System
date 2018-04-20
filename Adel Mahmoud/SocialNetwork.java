
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
import socialnetwork.PostTemplate;

public class SocialNetwork extends Application {

     
     Label userNameLabel;
     Button homeButton;
     Button profileButton;
     TextField  searchTxtBox;
     Button friendsListButton;
     Button groupsListButton;
     private static final int postsMargin = 50;
     
    
    public static void main(String[] args) {
         launch(args);
         
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Social Network");
        
        //set primaryStage boundaries to visible bounds of the main screen
        primaryStage_boundries_set_to_bounds_of_mainScreen(primaryStage);
        
        //adding stackepane layout to the background and will pack other components on it
        BorderPane  borderPane = new BorderPane ();
        
        
        Pane upperHMenue = new Pane();
        upperHMenue.setPadding(new Insets(15, 12, 15, 12));
        
        
        userNameLabel = new Label("User_name_Label");
        userNameLabel.setLayoutX(850);
        userNameLabel.setLayoutY(10);
        homeButton = new Button();
        homeButton.setText("Home");
        homeButton.setLayoutX(1000);
        homeButton.setLayoutY(10);
        homeButton.setPrefSize(100,10);
        
        profileButton = new Button();
        profileButton.setText("Profile");
        profileButton.setPrefSize(100,10);
        profileButton.getStyleClass().add("border-blue");
        profileButton.setLayoutX(1110);
        profileButton.setLayoutY(10);
        
        searchTxtBox=new TextField();
        searchTxtBox.setPromptText("search here...");
        searchTxtBox.setAlignment(Pos.CENTER);
       searchTxtBox.setLayoutX(500);
       searchTxtBox.setLayoutY(10);
       
        
        
        upperHMenue.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
        + "-fx-border-radius: 1;" + "-fx-border-color: #AB4642;"+"-fx-background-color: #AB4642;");
        
        upperHMenue.getChildren().addAll(userNameLabel,homeButton,profileButton,searchTxtBox);
        borderPane.setTop(upperHMenue);
       
       
        friendsListButton = new Button();
        friendsListButton.setText("My Friends");
        friendsListButton.setPrefSize(250,10);
        groupsListButton = new Button();
        groupsListButton.setText("My Groups");
        groupsListButton.setPrefSize(250,10);
        VBox leftVMenu = new VBox();
        leftVMenu.setStyle("-fx-background-color: #AB4642");
        leftVMenu.getChildren().addAll(friendsListButton,groupsListButton);
        borderPane.setLeft(leftVMenu);
        
        
        Pane centerPane = new Pane();
        centerPane.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width:0;" + "-fx-border-insets: 0;"
       + "-fx-border-radius: 0;" + "-fx-border-color: #ECF0F1;"+"-fx-background-color: #ECF0F1;"+"flex-direction: column;");
        borderPane.setCenter(centerPane);
        
        ScrollPane CenterPostsPane = new ScrollPane();
        
        CenterPostsPane.setLayoutX(10);
        CenterPostsPane.setLayoutY(10);
        
        CenterPostsPane.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width:0;" + "-fx-border-insets: 0;"
       + "-fx-border-radius: 0;" + "-fx-border-color: #ECF0F1;"+"-fx-background-color: #ECF0F1;"+"flex-direction: column;");
        CenterPostsPane.setPrefSize(PostTemplate.getPostTemplateWidth()+100,620);
        
        Group postsGroup = new Group();
        
        ScrollPane myPane = PostTemplate.printAPost("postTextaaa aaaa  aaaa aa aa aa aaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaa\n aa \n aaaaaaaaaaaaa","user_name");
        myPane.setLayoutX(10);
        myPane.setLayoutY(10);
        postsGroup.getChildren().add(myPane);
        
        
        ScrollPane myPane2 = PostTemplate.printAPost("postTextaaa aaaa  aaaa aa aa aa aaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaa\n aa \n aaaaaaaaaaaaa","user_name");
         myPane2.setLayoutX(10);
        myPane2.setLayoutY(10+1*PostTemplate.getPostTemplateHeight()+postsMargin);
        postsGroup.getChildren().add(myPane2);
       
        ScrollPane myPane3 = PostTemplate.printAPost("postTextaaa aaaa  aaaa aa aa aa aaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaa\n aa \n aaaaaaaaaaaaa","user_name");
         myPane3.setLayoutX(10);
        myPane3.setLayoutY(10+2*PostTemplate.getPostTemplateHeight()+2*postsMargin);
        postsGroup.getChildren().add(myPane3);
        
        CenterPostsPane.setContent(postsGroup);
        centerPane.getChildren().add(CenterPostsPane);
        
        
        Scene homePageScene  = new Scene(borderPane);
        primaryStage.setScene(homePageScene);
        
        primaryStage.show();
      
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
