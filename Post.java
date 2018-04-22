package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Post {
    private int user_id;
    private Label post_label= new Label();
    private String post_text;
    private String post_user;
    private Label user_label= new Label();
    private VBox all_post= new VBox();
    private Button like_button= new Button("Like");
    private Button dislike_button= new Button("DisLike");


   /* public Post ()
    {
        post_label.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        post_label.setAlignment(Pos.CENTER);
        post_label.setMaxWidth(600);
        post_label.setPadding(new Insets(0,10,10,10));
        post_label.setWrapText(true);
        post_label.setText(post_text);

        user_label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
        user_label.setAlignment(Pos.CENTER_LEFT);
        user_label.autosize();
        user_label.setPadding(new Insets(10,10,10,10));
        user_label.setText(post_user);

        HBox hb_buttons=new HBox();
        hb_buttons.getChildren().addAll(like_button,dislike_button);

        all_post.getChildren().addAll(user_label,post_label,hb_buttons);


    }*/

    public  Post (String t, String u, int id)
    {
        post_text=t;
        post_user=u;
        user_id=id;

        post_label.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        post_label.setAlignment(Pos.CENTER);
        post_label.setMaxWidth(600);
        post_label.setPadding(new Insets(5,5,5,5));
        post_label.setWrapText(true);
        post_label.setText(post_text);
        post_label.setStyle("-fx-background-color: #FFFFFF;-fx-background-radius: 4;" +
                "-fx-border-color: #1E90FF; -fx-border-width: 1;-fx-border-radius: 4 ");

        user_label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));
        user_label.setAlignment(Pos.TOP_LEFT);
        user_label.autosize();
        user_label.setPadding(new Insets(5,5,5,5));
        user_label.setText(post_user);
        user_label.setStyle("-fx-background-color: #FFFFFF;-fx-background-radius: 4;" +
                "-fx-border-color: #A9A9A9; -fx-border-width: 1;-fx-border-radius: 4 ");

        HBox hb_buttons=new HBox();
        hb_buttons.getChildren().addAll(like_button,dislike_button);

        all_post.setSpacing(3);
        all_post.getChildren().addAll(user_label,post_label,hb_buttons);

    }

    public VBox getAll_post() {
        return all_post;
    }

    public Label getPost_label() {
        return post_label;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPost_text() {
        return post_text;
    }
    public String getPost_user() {
        return post_user;
    }
    public Label getUser_label() {
        return user_label;
    }

    public void setAll_post(VBox all_post) {
        this.all_post = all_post;
    }

    public void setPost_label(Label post_label) {
        this.post_label = post_label;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPost_text(String post_text) {
        this.post_text = post_text;
    }

    public void setPost_user(String post_user) {
        this.post_user = post_user;
    }

    public void setUser_label(Label user_label) {
        this.user_label = user_label;
    }
}
