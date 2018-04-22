/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


/**
 *
 * @author Adel Mahmoud
 */
import java.util.*;
public class Post {
    //user postOwner;
    String postOwner;
    String postContent;
    Map <String,String> comment;
    Vector<Comment> postComments;
    Vector<String> likes;
    int numbOfLikes;
    Vector<String> dislike;
    int numbOfDislikes;
    //May Add reactions.
    ScrollPane postArea;
     Button likeButton;
    Button disLikeButton;
    TextField addCommentText;
    Button addComment;
    Button viewComments;
    Button viewReactions;
    
    public Post(String postContent,String user_name )
    {
        this.postOwner = user_name;
        this.postContent = postContent;
    }
    
    
    
    static int getPostHeight()
    {
        return 200;
    }
    static int getPostWidth()
    {
        return 1000;
    }
   public Pane printAPost()
   {
       
       
       Label userNameLabel = new Label(this.postOwner);
       userNameLabel.autosize();
       userNameLabel.setLayoutX(0);
       userNameLabel.setLayoutY(0);
       
       Label postLabel = new Label(this.postContent);
       postLabel.autosize();
       postLabel.setLayoutX(20);
       postLabel.setLayoutY(20);
       
       this. likeButton=new Button();
       likeButton.setText("Like");
       likeButton.setLayoutX(10);
       likeButton.setLayoutY(getPostHeight()/2 + 20);
       likeButton.setPrefSize(60,20);
       this.likeButton.setOnAction(e->{
           //implement LikeButton pressed
       });
       
       
       this. disLikeButton=new Button();
       disLikeButton.setText("DisLike");
       disLikeButton.setLayoutX(10+60+5);
       disLikeButton.setLayoutY(getPostHeight()/2 + 20);
       disLikeButton.setPrefSize(60,20);
       this.disLikeButton.setOnAction(e->{
           //implement LikeButton pressed
       });
       
       this.addCommentText = new TextField();
       addCommentText.setPromptText("Write a comment here...");
       addCommentText.setPrefSize(getPostWidth(),60);
       addCommentText.setLayoutX(10);
       addCommentText.setLayoutY(getPostHeight()/2 + 20 + 20+5);
       
       this.addComment = new Button();
       addComment.setText("Comment");
       addComment.setLayoutX(900);
       addComment.setLayoutY(getPostHeight()/2 + 20 + 20+5 +60 +5 );
       addComment.setPrefSize(100,20);
       this.addComment.setOnAction(e->{
           //implement Comment Function Here
       });
       
       this.viewReactions = new Button();
       viewReactions.setText("View reactions");
       viewReactions.setLayoutX(10+(60+5) *2);
       viewReactions.setLayoutY(getPostHeight()/2 + 20);
       viewReactions.setPrefSize(100,20);
       this.viewReactions.setOnAction(e->{
           //implement ViewReactions Function Here
       });
       this.viewComments = new Button();
       viewComments.setText("View Comments");
       viewComments.setLayoutX(20+(70+5) *3);
       viewComments.setLayoutY(getPostHeight()/2 + 20);
       viewComments.setPrefSize(120,20);
       this.viewComments.setOnAction(e->{
           //implement ViewComments Function Here
       });
       
       this. postArea = new ScrollPane();
       postArea.setPrefSize(getPostWidth(),getPostHeight()/2);
       postArea.setLayoutX(0);
       postArea.setLayoutX(0);
       postArea.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
        + "-fx-border-radius: 1;" + "-fx-border-color: #BDC3C7;"+"-fx-background-color: #ECF0F1;"+"flex-direction: column;");
       Group g = new Group();
       g.getChildren().addAll(userNameLabel,postLabel);
       postArea.setContent(g);
       
      Pane postAndComments = new Pane();
      postAndComments.setPrefSize(getPostWidth(),getPostHeight()/2);
      postAndComments.getChildren().addAll(postArea,likeButton,disLikeButton,addCommentText,addComment,viewReactions,viewComments);
       
       
       return postAndComments;
   }
}
