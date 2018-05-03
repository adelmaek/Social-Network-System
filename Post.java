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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

/**
 *
 * @author Adel Mahmoud
 */

 class Post {
    private String postOwner;
    private String postContent;
    //private Map <String,String> comment;
    private Vector<Comment> postComments;
    private Vector<String> likes;
    private int numbOfLikes;
    private Vector<String> dislike;
    int numbOfDislikes;
    //May Add reactions.
    private VBox postArea;
    Button likeButton;
    Button disLikeButton;
    TextArea addCommentText;
    Button addComment;
   // Button viewComments;
    Button viewReactions;
    public Post()
    {
        postArea= new VBox();
        likeButton= new Button("Like");
        disLikeButton= new Button("DisLike");
        addCommentText= new TextArea();
        addComment= new Button("Comment");
       // viewComments = new Button("View Comments");
        viewReactions= new Button("View Reactions");
        postComments= new Vector<Comment>();
        likes=new Vector<>();
        dislike=new Vector<>();
    }

    public String getPostContent() {
        return postContent;
    }

    public Post(String postContent, String user_name )
    {
        postArea= new VBox();
        likeButton= new Button("Like");
        disLikeButton= new Button("DisLike");
        addCommentText= new TextArea();
        addComment= new Button("Comment");
       // viewComments = new Button("View Comments");
        viewReactions= new Button("View Reactions");
        postComments= new Vector<Comment>();
        likes=new Vector<>();
        dislike=new Vector<>();
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
        hb_reactions.getChildren().addAll(post_likes,post_likes_word,post_dislikes,post_dislikes_word,
                viewReactions,new Label("  "),likeButton, new Label("  "),disLikeButton);
        hb_reactions.setPadding(new Insets(5,5,5,5));
        hb_reactions.setMaxWidth(400);
        hb_reactions.setAlignment(Pos.CENTER_LEFT);
        hb_reactions.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius:3;");

        likeButton.setOnAction(e->{                         //increase no likes and dislkes when the buttons is pressed
            if(disLikeButton.isDisabled()) {numbOfDislikes--;disLikeButton.setDisable(false);
            dislike.remove(SocialNetwork.currentUser.getUsername());}
            numbOfLikes++;
            likeButton.setDisable(true);
            post_likes.setText(String.valueOf(numbOfLikes));
            post_dislikes.setText(String.valueOf(numbOfDislikes));
            likes.add(SocialNetwork.currentUser.getUsername());
                });
        disLikeButton.setOnAction(e->{
            if(likeButton.isDisabled()) {numbOfLikes--;likeButton.setDisable(false);
            likes.remove(SocialNetwork.currentUser.getUsername());}
            numbOfDislikes++;
            disLikeButton.setDisable(true);
            post_likes.setText(String.valueOf(numbOfLikes));
            post_dislikes.setText(String.valueOf(numbOfDislikes));
            dislike.add(SocialNetwork.currentUser.getUsername());
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
        for(Comment h:postComments)
        {
            vb_comments.getChildren().add(h.getComment_area());
        }

        addComment.setOnAction(e->{
            if(!ta_add_comments.getText().isEmpty())
            {
                Comment temp = new Comment(ta_add_comments.getText(),SocialNetwork.currentUser.getUsername());
                postComments.add(temp);
                vb_comments.getChildren().add(temp.getComment_area());
                ta_add_comments.setText("");
                ta_add_comments.setPromptText("Add a Comment...");
            }
                });
        viewReactions.setOnAction(e->{
            Stage stage_reactions = new Stage();
            ScrollPane sp = new ScrollPane();
            sp.setMaxSize(300,400);
            VBox vb = new VBox();
            sp.setContent(vb);
            vb.setSpacing(7);
            vb.setPadding(new Insets(10));
            vb.setAlignment(Pos.TOP_CENTER);
            vb.setMaxSize(280,380);
            Label liketemp = new Label("Likes");
            liketemp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
            vb.getChildren().add(liketemp);
            for(String t:likes)
            {
                Label temp = new Label(t);
                temp.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
                vb.getChildren().add(temp);
            }
            Label disliketemp = new Label("DisLikes");
            disliketemp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
            vb.getChildren().add(disliketemp);
            for(String t:dislike)
            {
                Label temp = new Label(t);
                temp.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
                vb.getChildren().add(temp);
            }
            stage_reactions.setScene(new Scene(vb,300,400));
            stage_reactions.showAndWait();



        });


        postArea.getChildren().addAll(user_label,post_label,hb_reactions,vb_comments,hb_add_comments);
        postArea.setAlignment(Pos.CENTER_LEFT);
        postArea.setSpacing(3);

    }

    public VBox getPostArea() {
        return postArea;
    }
}

class Comment
{
    private String comment;
    private String commentOwner;
    private HBox comment_area;

    public Comment()
    {
      comment_area=new HBox();
    }
    public Comment(String comment,String commentOwner)
    {
        this.comment = comment;
        this.commentOwner = commentOwner;
        comment_area=new HBox();
        Label u_name = new Label(commentOwner+ " :  ");
        u_name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
        Label comment_t = new Label(comment);
        comment_t.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13));
        comment_t.setWrapText(true);
        comment_t.setMaxWidth(400);
        comment_area.setAlignment(Pos.CENTER_LEFT);
        comment_area.setPadding(new Insets(5));
        comment_area.getChildren().addAll(u_name,comment_t);
        comment_area.setMaxWidth(480);
    }

    public void setComment_area(HBox comment_area) {
        this.comment_area = comment_area;
    }

    public HBox getComment_area() {
        return comment_area;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentOwner(String commentOwner) {
        this.commentOwner = commentOwner;
    }

    public String getCommentOwner() {
        return commentOwner;
    }
}