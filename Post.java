/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
/**
 *
 * @author Adel Mahmoud
 */

 class Post {
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
    VBox postArea;
    Button likeButton;
    Button disLikeButton;
    TextArea addCommentText;
    Button addComment;
    Button viewComments;
    Button viewReactions;

    public Post()
    {
        postArea= new VBox();
        likeButton= new Button("Like");
        disLikeButton= new Button("DisLike");
        addCommentText= new TextArea();
        addComment= new Button("Comment");
    }
    
    public Post(String postContent,String user_name )
    {
        postArea= new VBox();
        likeButton= new Button("Like");
        disLikeButton= new Button("DisLike");
        addCommentText= new TextArea();
        addComment= new Button("Comment");
        this.postOwner = user_name;
        this.postContent = postContent;
        numbOfLikes=0;
        numbOfDislikes=0;

        Label user_label= new Label(user_name);    //post user name
        user_label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
        user_label.setAlignment(Pos.CENTER_LEFT);
        user_label.autosize();
        user_label.setPadding(new Insets(5,5,5,5));
        user_label.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius:3;" +
                "-fx-border-color: #A9A9A9; -fx-border-width: 1;-fx-border-radius: 3 ;");

        Label post_label = new Label(postContent);   //post text
        post_label.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        post_label.setAlignment(Pos.CENTER);
        post_label.setMaxWidth(600);
        post_label.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius:3;"+
                        "-fx-border-color: #1E90FF; -fx-border-width: 1;-fx-border-radius: 3 ;");
        post_label.setPadding(new Insets(10,10,10,10));
        post_label.setWrapText(true);

        HBox hb_reactions = new HBox();       //for likes and dislikes
        Label post_likes = new Label(String.valueOf(numbOfLikes));
        post_likes.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        post_likes.setAlignment(Pos.CENTER_LEFT);
        post_likes.autosize();
        Label post_likes_word = new Label(" Likes, ");
        post_likes_word.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        post_likes_word.setAlignment(Pos.CENTER_LEFT);
        post_likes_word.autosize();
        Label post_dislikes = new Label(String.valueOf(numbOfDislikes));
        post_dislikes.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        post_dislikes.setAlignment(Pos.CENTER_LEFT);
        post_dislikes.autosize();
        Label post_dislikes_word = new Label(" DisLikes.        ");
        post_dislikes_word.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        post_dislikes_word.setAlignment(Pos.CENTER_LEFT);
        post_dislikes_word.autosize();
        hb_reactions.getChildren().addAll(post_likes,post_likes_word,post_dislikes,post_dislikes_word,likeButton,
                new Label("  "),disLikeButton);
        hb_reactions.setPadding(new Insets(5,5,5,5));
        hb_reactions.setMaxWidth(280);
        hb_reactions.setAlignment(Pos.CENTER_LEFT);
        hb_reactions.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius:3;");

        likeButton.setOnAction(e->{                         //increase no likes and dislkes when the buttons is pressed
            if(disLikeButton.isDisabled()) {numbOfDislikes--;disLikeButton.setDisable(false);}
            numbOfLikes++;
            likeButton.setDisable(true);
            post_likes.setText(String.valueOf(numbOfLikes));
            post_dislikes.setText(String.valueOf(numbOfDislikes));
                });
        disLikeButton.setOnAction(e->{
            if(likeButton.isDisabled()) {numbOfLikes--;likeButton.setDisable(false);}
            numbOfDislikes++;
            disLikeButton.setDisable(true);
            post_likes.setText(String.valueOf(numbOfLikes));
            post_dislikes.setText(String.valueOf(numbOfDislikes));
        });

        HBox hb_add_comments = new HBox();

        TextArea ta_add_comments = new TextArea();
        ta_add_comments.setMaxSize(400,60);
        ta_add_comments.setWrapText(true);
        ta_add_comments.setPromptText("Add a Comment...");
        ta_add_comments.setPadding(new Insets(7,7,7,7));
        ta_add_comments.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13));

        hb_add_comments.getChildren().addAll(ta_add_comments,addComment);
        hb_add_comments.setSpacing(8);
        hb_add_comments.setAlignment(Pos.CENTER_LEFT);

        VBox vb_comments = new VBox();
        vb_comments.setMaxWidth(500);
        vb_comments.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius:3;");

      //  for(int i=0 ; i<)

        postArea.getChildren().addAll(user_label,post_label,hb_reactions,vb_comments,hb_add_comments);
        postArea.setAlignment(Pos.CENTER_LEFT);
        postArea.setSpacing(3);

    }

    public VBox getPostArea() {
        return postArea;
    }
}
