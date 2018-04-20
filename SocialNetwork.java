package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.text.StyledEditorKit;


public class SocialNetwork extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        BorderPane bp = new BorderPane();                                  //main pane
       // bp.setPadding(new Insets(0,10,10,10));

        HBox hb=new HBox();                                                       //for the top bar
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.setSpacing(8);
        hb.setMinHeight(40);
        hb.setPadding(new Insets(0,10,10,10));
        hb.getStyleClass().add("top_bar");

        Button home_button = new Button("Home");
        Button profile_button = new Button("Profile");
        hb.getChildren().addAll(home_button,profile_button);


        VBox vb2= new VBox();                                    //for the profile picture and user name
        vb2.setPadding(new Insets(10,10,10,10));
        vb2.setSpacing(8);
        vb2.setAlignment(Pos.TOP_CENTER);

        Image image = new Image(new FileInputStream("E:/Mark/mpp.jpg"));      //Profile Picture
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);
        imageView.getStyleClass().add("top_bar");

        vb2.getChildren().addAll(imageView);

        Label user_name= new Label();                   //user name
        user_name.setText("User Name");
        user_name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));

        Label user_bio= new Label();                   //user's bio
        user_bio.setText("User's bio");
        user_bio.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 13));
        user_bio.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 4;");
        user_bio.setPadding(new Insets(10,10,10,10));
        user_bio.setWrapText(true);
        user_bio.setMaxWidth(500);
        user_bio.setAlignment(Pos.CENTER);


        VBox vb1 = new VBox();      // center pane
        vb1.setSpacing(8);
        vb1.setAlignment(Pos.TOP_CENTER);
       // VBox.(user_bio,Pos.CENTER_RIGHT);
        vb1.setPadding(new Insets(10,5,5,5));

        //for writing posts

        TextField tf_posts= new TextField();
        tf_posts.getStyleClass().add("text_field");
        tf_posts.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));

        Button post_button  = new Button("Post");
        post_button.setPrefSize(50,10);

        vb1.getChildren().addAll(user_name,user_bio,tf_posts,post_button);

        bp.setCenter(vb1);
        bp.setTop(hb);
        bp.setLeft(vb2);
        Scene Profile=new Scene(bp,700, 500);
        Profile.getStylesheets().add("Styles.css");
        primaryStage.setTitle("Social Network");
        primaryStage.setScene(Profile);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
