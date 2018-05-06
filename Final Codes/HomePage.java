/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import java.util.Vector;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import  socialnetwork.SocialNetwork;


/**
 *
 * @author Adel Mahmoud
 */
public class HomePage {
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
     VBox centerVBoxPane;
     Group postsGroup;
     Button searchButton;
     Scene homePageScene;
     Scene profileScene;
     VBox leftVMenu;
     VBox showVBox ;
     ImageView imageView;
     ScrollPane showScrollPane;
     static boolean  friendsListButtonPressed ;
     static boolean groupsListButtonPressed;
     
     public HomePage()
     {}
       public  Scene homePage (user currentUser)
    {
        borderPane = new BorderPane ();
        setUpperHMnue();
        setLeftVMenue();
        setCenterPane();
        borderPane.setTop(upperHMenue);
        borderPane.setLeft(leftVMenu);
        borderPane.setCenter(centerPane);
        homePageScene  = new Scene(borderPane);
        return homePageScene;
    }
       
    void setCenterPane()
    {
        centerPane = new Pane();
        centerPane.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width:0;" + "-fx-border-insets: 0;"
       + "-fx-border-radius: 0;" + "-fx-border-color: #ECF0F1;"+"-fx-background-color: #ECF0F1;"+"flex-direction: column;");
       
        
        
        statusBar = new TextField();
        statusBar.setPrefSize(1000, 60); 
        statusBar.setLayoutX(10);
        statusBar.setLayoutY(10); 
        statusBar.setPromptText("What's in your mind?...");
        
        
        postStatusFromStatusBarButton =new Button();
        postStatusFromStatusBarButton.setLayoutX(800);
        postStatusFromStatusBarButton.setLayoutY(75);
        postStatusFromStatusBarButton.setText("Post");
        postStatusFromStatusBarButton.setPrefSize(60,40);
        postStatusFromStatusBarButton.setOnAction(e->{
        //implement Post a post here
        Post p = new Post(statusBar.getText(),SocialNetwork.currentUser.getUsername());
        SocialNetwork.currentUser.add_post(p);
        fillScrollPaneWithPosts();
        });
         
         
        CenterPostsPane = new ScrollPane();
        CenterPostsPane.setLayoutX(10);
        CenterPostsPane.setLayoutY(120);
        CenterPostsPane.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width:0;" + "-fx-border-insets: 0;"
       + "-fx-border-radius: 0;" + "-fx-border-color: #ECF0F1;"+"-fx-background-color: #ECF0F1;"+"flex-direction: column;");
        CenterPostsPane.setPrefSize(600,500);
        CenterPostsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        CenterPostsPane.setVmax(1);
        CenterPostsPane.setContent(centerVBoxPane);
        
        
        
        centerPane.getChildren().addAll(statusBar,postStatusFromStatusBarButton,CenterPostsPane);
    }
   
      void setUpperHMnue()
    {
        upperHMenue = new Pane();
        upperHMenue.setPadding(new Insets(15, 12, 15, 12));
        
        
        userNameLabel = new Label();
        userNameLabel.setText(SocialNetwork.currentUser.getUsername());
        userNameLabel.setLayoutX(850);
        userNameLabel.setLayoutY(10);
        userNameLabel.setStyle("-fx-background-color: #AB4642;"+"-fx-padding:2;");
        homeButton = new Button();
        homeButton.setText("Home");
        homeButton.setLayoutX(1000);
        homeButton.setLayoutY(10);
        homeButton.setPrefSize(100,10);
        homeButton.setOnAction(e->{
            //implement "go to home " here user tempUser = SocialNetwork.currentUser;
            fillScrollPaneWithPosts();
        });
        
        profileButton = new Button();
        profileButton.setText("Profile");
        profileButton.setPrefSize(100,10);
        profileButton.getStyleClass().add("border-blue");
        profileButton.setLayoutX(1110);
        profileButton.setLayoutY(10);
        profileButton.setOnAction(e->{
            //implement "go to profile " here
            SocialNetwork.window.setScene(Profile.Profile(SocialNetwork.currentUser));             
            SocialNetwork.window.show();   
        });
        
        searchTxtBox=new TextField();
        searchTxtBox.setPromptText("search here...");
        searchTxtBox.setAlignment(Pos.CENTER);
       searchTxtBox.setLayoutX(500);
       searchTxtBox.setLayoutY(10);
       String[] autocomp = {"Adel","Sama","Salma"};
       
       
        searchButton = new Button();
        searchButton.setText("Search");
        searchButton.setLayoutX(500 +5 +150);
        searchButton.setLayoutY(10);
        searchButton.setOnAction(e->{
            //implement search here
            if (SocialNetwork.searchUsersHashTable(searchTxtBox.getText())!= null)
            {
             SocialNetwork.window.setScene(Profile.Profile(SocialNetwork.searchUsersHashTable(searchTxtBox.getText())));             
             SocialNetwork.window.show();
            }
            else if(SocialNetwork.searchGroupsHashTable(searchTxtBox.getText())!= null)
            {
             SocialNetwork.window.setScene(socialnetwork.Group.Groups(SocialNetwork.searchGroupsHashTable(searchTxtBox.getText())));             
             SocialNetwork.window.show();
            }
            else
            {
                MessageBox.display("Search Failed","User or group not Found" );
            }   
            
        });
        
        upperHMenue.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
        + "-fx-border-radius: 1;" + "-fx-border-color: #AB4642;"+"-fx-background-color: #AB4642;");
        
        upperHMenue.getChildren().addAll(userNameLabel,homeButton,profileButton,searchTxtBox,searchButton);
    }
   //************************************************************************************ 
      VBox peopleYouMayKnow(user currentUser)
      {
          // implement people you may know
         VBox vB = new VBox();
         return vB;
      }
    void setLeftVMenue()
    {
         showVBox = new VBox();
        showVBox.setPadding(new Insets(15, 12, 15, 12));
        showVBox.setSpacing(10);
       showVBox = peopleYouMayKnow(SocialNetwork.currentUser);
        
        imageView = new ImageView(SocialNetwork.currentUser.getProfilePicture());
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        
                
        
        friendsListButton = new Button();
        friendsListButton.setText("My Friends");
        friendsListButton.setPrefSize(250,10);
        friendsListButton.setOnAction(e->{
            if(friendsListButtonPressed)
            {
               showVBox = peopleYouMayKnow(SocialNetwork.currentUser);
                friendsListButtonPressed = false;
            }
            else
            {
                 Vector<String> myFriendsList = SocialNetwork.currentUser.getFriends();
                Button [] myFriendButton = new Button[SocialNetwork.currentUser.getNumberOfFriends()];
                ImageView [] myFriendImageView = new ImageView[SocialNetwork.currentUser.getNumberOfFriends()];
                VBox [] myFriendVBox = new VBox[SocialNetwork.currentUser.getNumberOfFriends()];
                int i= 0;
                for(String aFriendString:myFriendsList )
                    {
                        user aFriend = SocialNetwork.searchUsersHashTable(aFriendString);
                         myFriendImageView[i] = new ImageView(aFriend.getProfilePicture());
                         myFriendButton[i] = new Button();
                         myFriendButton[i].setText(aFriend.getUsername());
               
                        myFriendButton[i].setOnAction(ee->{
                                 SocialNetwork.window.setScene(Profile.Profile(aFriend)); // Replace with marks function
                            });
                        myFriendVBox[i] = new VBox();
                        myFriendVBox[i].setSpacing(5);
                         myFriendVBox[i].getChildren().addAll(myFriendImageView[i],myFriendButton[i]);
                         showVBox.getChildren().add(myFriendVBox[i]);
                         i++;
                     }
                friendsListButtonPressed = true;
             }
        });
        
        groupsListButton = new Button();
        groupsListButton.setText("My Groups");
        groupsListButton.setPrefSize(250,10);
        groupsListButton.setOnAction(e->{
            if(groupsListButtonPressed)
            {
               showVBox = peopleYouMayKnow(SocialNetwork.currentUser);
                groupsListButtonPressed = false;
            }
            else
            {
                // implement myGroups
               // Vector<Group> myGroupList = 
                Vector<String> myGroupList = SocialNetwork.currentUser.getGroups_names();
                Button [] myGroupsButton = new Button[SocialNetwork.currentUser.getGroups_names().size()];
                ImageView [] myGroupsImageView = new ImageView[SocialNetwork.currentUser.getGroups_names().size()];
                VBox [] myGroupsVBox = new VBox[SocialNetwork.currentUser.getGroups_names().size()];
                int i= 0;
                for(String aGroupString :myGroupList )
                    {
                        socialnetwork.Group aGroup = SocialNetwork.searchGroupsHashTable(aGroupString);
                         myGroupsImageView[i] = new ImageView(aGroup.getGroup_pic());
                         myGroupsButton[i] = new Button();
                         myGroupsButton[i].setText(aGroup.getName());
               
                        myGroupsButton[i].setOnAction(ee->{
                                 SocialNetwork.window.setScene(socialnetwork.Group.Groups(aGroup)); // Replace with marks function 
                            });
                        myGroupsVBox[i] = new VBox();
                        myGroupsVBox[i].setSpacing(5);
                         myGroupsVBox[i].getChildren().addAll(myGroupsImageView[i],myGroupsButton[i]);
                         showVBox.getChildren().add(myGroupsVBox[i]);
                         i++;
                     }
                groupsListButtonPressed = true;
             }
            
        });
        
        
        
        showScrollPane = new ScrollPane();
        showScrollPane.setContent(showVBox);
        
        leftVMenu = new VBox();
        leftVMenu.setStyle("-fx-background-color: #AB4642");
        leftVMenu.setPadding(new Insets(15, 12, 15, 12));
        leftVMenu.setSpacing(10);
        leftVMenu.getChildren().addAll(imageView,groupsListButton,friendsListButton,showScrollPane);
       
    }
     private void fillScrollPaneWithPosts()
    {
        Post tempPost;
            tempPost = new Post(SocialNetwork.currentUser.getPosts().get(SocialNetwork.currentUser.getPosts().size()-1).getPostContent(),SocialNetwork.currentUser.getPosts().get(SocialNetwork.currentUser.getPosts().size()-1).getPostOwner());
            centerVBoxPane.getChildren().add(tempPost.getPostArea());
            tempPost = new Post(SocialNetwork.currentUser.getPosts().get(SocialNetwork.currentUser.getPosts().size()-2).getPostContent(),SocialNetwork.currentUser.getPosts().get(SocialNetwork.currentUser.getPosts().size()-2).getPostOwner());
            centerVBoxPane.getChildren().add(tempPost.getPostArea());
            Vector <String> tempFriendList = SocialNetwork.currentUser.getFriends_names();
            for(String x : tempFriendList)
            {
               user tempUser = SocialNetwork.searchUsersHashTable(x);
               Vector<Post> tempPosts = tempUser.getPosts();
               tempPost = new Post(tempPosts.get(tempPosts.size()-1).getPostContent(),tempPosts.get(tempPosts.size()-1).getPostOwner());
               centerVBoxPane.getChildren().add(tempPost.getPostArea());
               tempPost = new Post(tempPosts.get(tempPosts.size()-2).getPostContent(),tempPosts.get(tempPosts.size()-2).getPostOwner());
               centerVBoxPane.getChildren().add(tempPost.getPostArea());
            } 
    }
}
