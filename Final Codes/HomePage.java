package socialnetwork;

import java.util.*;

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
import javafx.scene.paint.Color;
import  socialnetwork.SocialNetwork;

import static socialnetwork.SocialNetwork.UsersInSystem;
import static socialnetwork.SocialNetwork.currentUser;
import static socialnetwork.SocialNetwork.searchUsersHashTable;


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
     VBox centerVBoxPane = new VBox();
     Group postsGroup;
     Button searchButton;
     Scene homePageScene;
     Scene profileScene;
     VBox leftVMenu;
     VBox showVBox ;
     ImageView imageView;
     ScrollPane showScrollPane;
     Button PeopleYouMayKnowButton;
     static boolean  friendsListButtonPressed ;
     static boolean groupsListButtonPressed;
     
     Label sna;
     
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
       + "-fx-border-radius: 0;" + "-fx-border-color: #ECF0F1;"+"-fx-background-color: #B3B6B7;"+"flex-direction: column;");
       
        //fillScrollPaneWithPosts();
        
        statusBar = new TextField();
        statusBar.setPrefSize(1000, 60); 
        statusBar.setLayoutX(10);
        statusBar.setLayoutY(10); 
        statusBar.setPromptText("What's in your mind?...");
        
        fillScrollPaneWithPosts();
        
        postStatusFromStatusBarButton =new Button();
        postStatusFromStatusBarButton.setLayoutX(800);
        postStatusFromStatusBarButton.setLayoutY(75);
        postStatusFromStatusBarButton.setText("Post");
        postStatusFromStatusBarButton.setPrefSize(60,40);
        postStatusFromStatusBarButton.setOnAction(e->{
        //implement Post a post here
        
        Post x =new Post(statusBar.getText(),SocialNetwork.currentUser.getUsername());
        SocialNetwork.currentUser.add_post(x);
        fillScrollPaneWithPosts();
        statusBar.setText("");
        });
         
         
        CenterPostsPane = new ScrollPane();
        CenterPostsPane.setLayoutX(150);
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
       
        sna = new Label();
        sna.setText("Social Network");
        sna.setStyle("-fx-font:40px \"Serif\";\n" + "-fx-text-fill: white;");
        sna.setLayoutX(10);
        sna.setLayoutY(10);
        
        userNameLabel = new Label();
        userNameLabel.setText(SocialNetwork.currentUser.getUsername());
        userNameLabel.setLayoutX(850);
        userNameLabel.setLayoutY(10);
        //userNameLabel.setPrefWidth(50);
        userNameLabel.setStyle("-fx-font:40px \"Serif\";\n"+"-fx-text-fill: white;"+"-fx-padding:2;"+"font-weight: bold;");
        homeButton = new Button();
        homeButton.setText("Home");
        homeButton.setLayoutX(1000);
        homeButton.setLayoutY(20);
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
        profileButton.setLayoutY(20);
        profileButton.setOnAction(e->{
            //implement "go to profile " here
            SocialNetwork.window.setScene(Profile.Profile(SocialNetwork.currentUser));             
            SocialNetwork.window.show();   
        });
        
        searchTxtBox=new TextField();
        searchTxtBox.setPromptText("search here...");
        searchTxtBox.setAlignment(Pos.CENTER);
       searchTxtBox.setLayoutX(500);
       searchTxtBox.setLayoutY(20);
       String[] autocomp = {"Adel","Sama","Salma"};
       
       
        searchButton = new Button();
        searchButton.setText("Search");
        searchButton.setLayoutX(500 +5 +150);
        searchButton.setLayoutY(20);
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
        
        upperHMenue.setPrefHeight(80);
        upperHMenue.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
        + "-fx-border-radius: 1;" + "-fx-border-color: #979A9A;"+"-fx-background-color: #979A9A;");
        
        upperHMenue.getChildren().addAll(userNameLabel,homeButton,profileButton,searchTxtBox,searchButton,sna);
    }
   //************************************************************************************ 
      Set<String> peopleYouMayKnow(user currentUser)
      {
          // implement people you may know


          Map currentUserMap = SocialNetwork.adjencyMatrix.get(currentUser.getUsername());
          Vector <String> currentUserFriends = currentUser.getFriends();

          Set <String> peopleYouMayKnow = new HashSet<String>();
          for (String i : currentUserFriends)
          {
              user x = searchUsersHashTable(i);
              for ( String j : x.getFriends())
              {

                  Integer  check =  (Integer) currentUserMap.get(j);

                  if ( check == 0 && j.equals(currentUser.getUsername())==false)
                  {
                      //System.out.println(j + "   "+ currentUser.getUsername());
                      peopleYouMayKnow.add(j);
                      //peopleYouMayKnow.add(j);
                  }

              }
          }

          for (String i : peopleYouMayKnow){
              System.out.println(i);
          }
          return peopleYouMayKnow ;
          //showVBox.getChildren().clear();
         //VBox vB = new VBox();
         //return vB;
      }
    void setLeftVMenue()
    {
         showVBox = new VBox();
        showVBox.setPadding(new Insets(15, 12, 15, 12));
        showVBox.setSpacing(10);
        
       //showVBox = peopleYouMayKnow(SocialNetwork.currentUser);
        
        imageView = new ImageView(SocialNetwork.currentUser.getProfilePicture());
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        PeopleYouMayKnowButton = new Button ();
        PeopleYouMayKnowButton.setText("People you may know");
        PeopleYouMayKnowButton.setPrefSize(250,10);
        PeopleYouMayKnowButton.setOnAction(e->{

            Set <String>  peopleyoumayknowlist = peopleYouMayKnow(currentUser);
            showVBox.getChildren().clear();
            Button [] peoplebuttonarray = new Button[peopleyoumayknowlist.size()];
            ImageView [] peopleImageView = new ImageView[peopleyoumayknowlist.size()];
            VBox [] peopleVbox = new VBox[peopleyoumayknowlist.size()];
            int i= 0;
            for(String peopleString :peopleyoumayknowlist )
            {
                peopleImageView[i] = new ImageView(SocialNetwork.searchUsersHashTable(peopleString).getProfilePicture());
                peoplebuttonarray[i] = new Button();
                peoplebuttonarray[i].setText(peopleString);

                peoplebuttonarray[i].setOnAction(ee->{
                  SocialNetwork.window.setScene(Profile.Profile(SocialNetwork.searchUsersHashTable(peopleString))); // Replace with marks function
                });
                peopleVbox[i] = new VBox();
                peopleVbox[i].setSpacing(5);
                peopleVbox[i].getChildren().addAll(peopleImageView[i],peoplebuttonarray[i]);
                showVBox.getChildren().add(peopleVbox[i]);
                i++;
            }


        });

        friendsListButton = new Button();
        friendsListButton.setText("My Friends");
        friendsListButton.setPrefSize(250,10);
        friendsListButton.setOnAction(e->{
            
               showVBox.getChildren().clear();
                Button [] myFriendButton = new Button[SocialNetwork.currentUser.getFriends().size()];
                ImageView [] myFriendImageView = new ImageView[SocialNetwork.currentUser.getFriends().size()];
                VBox [] myFriendVBox = new VBox[SocialNetwork.currentUser.getFriends().size()];
                int i= 0;
                for(String aFriendString:SocialNetwork.currentUser.getFriends() )
                    {
                         myFriendImageView[i] = new ImageView(SocialNetwork.searchUsersHashTable(aFriendString).getProfilePicture());
                         myFriendButton[i] = new Button();
                         myFriendButton[i].setText(SocialNetwork.searchUsersHashTable(aFriendString).getUsername());
               
                        myFriendButton[i].setOnAction(ee->{
                                 SocialNetwork.window.setScene(Profile.Profile(SocialNetwork.searchUsersHashTable(aFriendString))); // Replace with marks function
                            });
                        myFriendVBox[i] = new VBox();
                        myFriendVBox[i].setSpacing(5);
                         myFriendVBox[i].getChildren().addAll(myFriendImageView[i],myFriendButton[i]);
                         showVBox.getChildren().add(myFriendVBox[i]);
                         i++;
                     }
        });
        
        groupsListButton = new Button();
        groupsListButton.setText("My Groups");
        groupsListButton.setPrefSize(250,10);
        groupsListButton.setOnAction(e->{
                showVBox.getChildren().clear();
                Button [] myGroupsButton = new Button[SocialNetwork.currentUser.getGroups_names().size()];
                ImageView [] myGroupsImageView = new ImageView[SocialNetwork.currentUser.getGroups_names().size()];
                VBox [] myGroupsVBox = new VBox[SocialNetwork.currentUser.getGroups_names().size()];
                int i= 0;
                for(String aGroupString :SocialNetwork.currentUser.getGroups_names() )
                    {
                         myGroupsImageView[i] = new ImageView(SocialNetwork.searchGroupsHashTable(aGroupString).getGroup_pic());
                         myGroupsButton[i] = new Button();
                         myGroupsButton[i].setText(SocialNetwork.searchGroupsHashTable(aGroupString).getName());
               
                        myGroupsButton[i].setOnAction(ee->{
                                 SocialNetwork.window.setScene(socialnetwork.Group.Groups(SocialNetwork.searchGroupsHashTable(aGroupString))); // Replace with marks function 
                            });
                        myGroupsVBox[i] = new VBox();
                        myGroupsVBox[i].setSpacing(5);
                         myGroupsVBox[i].getChildren().addAll(myGroupsImageView[i],myGroupsButton[i]);
                         showVBox.getChildren().add(myGroupsVBox[i]);
                         i++;
                     }            
        });
        
        
        
        showScrollPane = new ScrollPane();
        showScrollPane.setContent(showVBox);
        
        leftVMenu = new VBox();
        leftVMenu.setStyle("-fx-background-color: #B3B6B7");
        leftVMenu.setPadding(new Insets(15, 12, 15, 12));
        leftVMenu.setSpacing(10);
        leftVMenu.getChildren().addAll(imageView,groupsListButton,friendsListButton,PeopleYouMayKnowButton,showScrollPane);
       
    }
     private void fillScrollPaneWithPosts()
    {
        Post tempPost;
        int index= 0;
        boolean filledFlag =false;
        
        centerVBoxPane.getChildren().clear();
       
        while(Integer.compare(index, SocialNetwork.currentUser.getPosts().size())==-1 )
        {
            tempPost = new Post(SocialNetwork.currentUser.getPosts().get(SocialNetwork.currentUser.getPosts().size()-1-index).getPostContent(),SocialNetwork.currentUser.getPosts().get(SocialNetwork.currentUser.getPosts().size()-1).getPostOwner());
            centerVBoxPane.getChildren().add(tempPost.getPostArea());
            index++;
            filledFlag =true;
            if(index==2)break;
        }
       
       if(Integer.compare(SocialNetwork.currentUser.getFriends().size(), 0)==1)
       {
            for(String x : SocialNetwork.currentUser.getFriends_names())
            {
               user tempUser = SocialNetwork.searchUsersHashTable(x);
               index=0;
              
               while(Integer.compare(index, SocialNetwork.searchUsersHashTable(x).posts.size())==-1)
               {
               tempPost = new Post(SocialNetwork.searchUsersHashTable(x).posts.get(SocialNetwork.searchUsersHashTable(x).posts.size()-1-index).getPostContent(),SocialNetwork.searchUsersHashTable(x).posts.get(SocialNetwork.searchUsersHashTable(x).posts.size()-1-index).getPostOwner());
               centerVBoxPane.getChildren().add(tempPost.getPostArea());
               filledFlag = true;
               index ++;
               
               }
            } 
        }
        if(!filledFlag)
        {
            Label unfilled = new Label();
            unfilled.setText("No posts to show");
            centerVBoxPane .getChildren().add(unfilled);
        }
    }
}
