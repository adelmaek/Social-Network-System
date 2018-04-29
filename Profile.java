package socialnetwork;

import java.util.*;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Rectangle;
import java.util.Vector;

import java.awt.*;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Profile {



    public static Scene Profile (user x, boolean account_owner)
    {

        BorderPane bp = new BorderPane();                   //main pane

        HBox hb=new HBox();                                                       //for the top bar
        hb.setAlignment(Pos.CENTER_RIGHT);
        hb.setSpacing(10);
        hb.setMinHeight(60);
        hb.setPadding(new Insets(10,20,10,10));
        hb.getStyleClass().add("top_bar");

        Button home_button = new Button("Home");
        home_button.setPrefSize(60,30);
        Button profile_button = new Button("Profile");
        profile_button.setPrefSize(60,30);
        hb.getChildren().addAll(home_button,profile_button);

        VBox vb3 = new VBox(); // for profile pic and info

        VBox vb2= new VBox();                                    //for the profile picture
        vb2.setPadding(new Insets(10,10,10,10));
        vb2.setAlignment(Pos.CENTER);
        Image image = x.getProfilePicture();      //Profile Picture
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(230);
        imageView.setFitWidth(230);
        imageView.setPreserveRatio(true);
        vb2.getStyleClass().add("image_view");
        vb2.getChildren().addAll(imageView);
        vb2.setMaxHeight(imageView.getFitHeight());

        // Rectangle padding1= new Rectangle(10,100); //
        // padding1.setFill(Color.TRANSPARENT);
        //padding1.setStroke(Color.TRANSPARENT);

        VBox vb4 = new VBox(); // for info
        vb4.getStyleClass().add("info_area");
        vb4.setPadding(new Insets(5,5,5,5));
        vb4.setMaxWidth(250);
        vb4.setSpacing(5);
        Label info = new Label("-------------info------------");
        info.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 15));
        info.setTextFill(Color.GRAY);
        info.setStyle("-fx-color: #D3D3D3;");
        info.setAlignment(Pos.TOP_CENTER);
        Label label_no_friends = new Label("Number of Friends: "+String.valueOf(x.getNumberOfFriends()));///
        label_no_friends.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_no_friends.setAlignment(Pos.CENTER);
        label_no_friends.setWrapText(true);
        label_no_friends.setMaxWidth(240);
        Label label_birthday = new Label("Birthday: "+x.getInfo().getDateOfBirth().print());///
        label_birthday.textProperty().bind(new SimpleStringProperty(x.getInfo().getDateOfBirth().print()));
        label_birthday.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_birthday.setAlignment(Pos.CENTER);
        label_birthday.setWrapText(true);
        label_birthday.setMaxWidth(240);
        Label label_status = new Label("status: "+x.getInfo().getStatus().toString());///
        label_status.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_status.setAlignment(Pos.CENTER);
        label_status.setWrapText(true);
        label_status.setMaxWidth(240);
        Label label_gender = new Label("Gender: "+x.getInfo().getGender().toString());///
        label_gender.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_gender.setAlignment(Pos.CENTER);
        label_gender.setMaxWidth(240);
        Label label_city= new Label("City: "+x.getInfo().getCity());///
        label_city.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_city.setAlignment(Pos.CENTER);
        label_city.setWrapText(true);
        label_city.setMaxWidth(240);
        Label label_birth_place= new Label("Birth Place: "+x.getInfo().getBirth_place());///
        label_birth_place.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_birth_place.setAlignment(Pos.CENTER);
        label_birth_place.setMaxWidth(240);
        label_birth_place.setWrapText(true);
        Label label_work= new Label("Work: "+x.getInfo().getWork());///
        label_work.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_work.setAlignment(Pos.CENTER);
        label_work.setMaxWidth(240);
        label_work.setWrapText(true);
        Label label_college= new Label("College: "+x.getInfo().getCollege());///
        label_college.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_college.setAlignment(Pos.CENTER);
        label_college.setMaxWidth(240);
        label_college.setWrapText(true);
        Label label_school= new Label("School: "+x.getInfo().getSchool());///
        label_school.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_school.setAlignment(Pos.CENTER);
        label_school.setMaxWidth(240);
        label_school.setWrapText(true);
        String lang=" ";
        for(String l : x.getInfo().getLanguages())
            lang+=l+", ";
        lang=lang.substring(0,lang.length()-2);
        Label label_lang= new Label("Languages:"+lang);///
        label_lang.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_lang.setAlignment(Pos.CENTER);
        label_lang.setMaxWidth(240);
        label_lang.setWrapText(true);
        vb4.getChildren().addAll(info,label_no_friends,label_birthday,label_status,label_gender);
        if(x.getInfo().getCity()!=" "){vb4.getChildren().add(label_city);}
        if(x.getInfo().getBirth_place()!=" "){vb4.getChildren().add(label_birth_place);}
        if(x.getInfo().getWork()!=" "){vb4.getChildren().add(label_work);}
        if(x.getInfo().getCollege()!=" "){vb4.getChildren().add(label_college);}
        if(x.getInfo().getSchool()!=" "){vb4.getChildren().add(label_school);}
        if(x.getInfo().getNumberOfLanguages()!=0){vb4.getChildren().add(label_lang);}
        vb4.setAlignment(Pos.TOP_CENTER);

        vb3.setAlignment(Pos.TOP_CENTER);
        vb3.setMaxWidth(300);
        vb3.setPadding(new Insets(20,0,20,20));
        vb3.setSpacing(20);

        Button change_info_button = new Button("Change Info");   // changing info *don't forget to check if it's the account owner*
        change_info_button.setOnAction(e->{
            Stage change_info_window = new Stage();
            change_info_window.initModality(Modality.APPLICATION_MODAL);
            change_info_window.setTitle("Change Info");
            VBox vb_change_info_l = new VBox();
            Label l_bd = new Label("Birthday: ");
            Label l_status = new Label("Status: ");
            Label l_gender = new Label("Gender: ");
            Label l_city = new Label("City: ");
            Label l_bp = new Label("Birth Place: ");
            Label l_work = new Label("Work: ");
            Label l_college = new Label("College: ");
            Label l_school = new Label("School: ");
            Label l_language = new Label("Language: ");
            vb_change_info_l.setSpacing(13);
            vb_change_info_l.setAlignment(Pos.TOP_CENTER);
            vb_change_info_l.setPadding(new Insets(15,10,0,10));
            VBox vb_change_info_l2 = new VBox();
            vb_change_info_l2.setAlignment(Pos.TOP_CENTER);
            vb_change_info_l2.getChildren().addAll(l_city,l_bp,l_work,l_college,l_school,l_language);
            vb_change_info_l2.setSpacing(25);
            vb_change_info_l.getChildren().addAll(l_bd,l_status,l_gender);
            VBox vb_change_info_l1 = new VBox();
            vb_change_info_l1.setSpacing(20);
            vb_change_info_l1.getChildren().addAll(vb_change_info_l,vb_change_info_l2);

            VBox vb_change_info= new VBox();
            vb_change_info.setAlignment(Pos.TOP_CENTER);
            vb_change_info.setPadding(new Insets(10,10,10,10));
            HBox hb_birthday = new HBox();
            ComboBox<String> day = new ComboBox<String>();
            day.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17",
                    "18","19","20","21","22","23","24","25","26","27","28","29","30","31");
            day.setVisibleRowCount(12);
            ComboBox months = new ComboBox();
            months.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12" );
            months.setVisibleRowCount(12);
            String[]i1 = new String[120];
            int i2=0;
            for (int i=2018;i>=1900;i--) { i1[i2]=String.valueOf(i); i2++;}
            ComboBox years = new ComboBox(FXCollections.observableArrayList(i1));
            years.setVisibleRowCount(12);
            hb_birthday.getChildren().addAll(day,months,years);

            ComboBox<Informations.MaritalStatus> cb_status = new ComboBox<Informations.MaritalStatus>();
            cb_status.getItems().addAll(Informations.MaritalStatus.non,Informations.MaritalStatus.single,
                    Informations.MaritalStatus.engaged, Informations.MaritalStatus.inarelationship,
                    Informations.MaritalStatus.married);

            ComboBox<Informations.Gender> cb_gender= new ComboBox<Informations.Gender>();
            cb_gender.getItems().addAll(Informations.Gender.male,Informations.Gender.female);

            TextArea ta_city = new TextArea();
            ta_city.setMaxWidth(200);
            ta_city.setMaxHeight(20);
            ta_city.setWrapText(true);
            TextArea ta_bp = new TextArea();
            ta_bp.setMaxWidth(200);
            ta_bp.setMaxHeight(20);
            ta_bp.setWrapText(true);
            TextArea ta_work = new TextArea();
            ta_work.setMaxWidth(200);
            ta_work.setMaxHeight(20);
            ta_work.setWrapText(true);
            TextArea ta_college = new TextArea();
            ta_college.setMaxWidth(200);
            ta_college.setMaxHeight(20);
            ta_college.setWrapText(true);
            TextArea ta_school = new TextArea();
            ta_school.setMaxWidth(200);
            ta_school.setMaxHeight(20);
            ta_school.setWrapText(true);
            TextArea ta_lang = new TextArea();
            ta_lang.setMaxWidth(200);
            ta_lang.setMaxHeight(20);
            ta_lang.setWrapText(true);

            Button info_done = new Button("Done");
            info_done.setOnAction(e2-> {
                if (!(day.getSelectionModel().isEmpty()) && !(months.getSelectionModel().isEmpty()) && !(years.getSelectionModel().isEmpty()))
                {
                    x.set_bd(new Date_Of_Birth(Integer.valueOf(day.getValue()),Integer.valueOf(months.getValue().toString())
                            ,Integer.valueOf(years.getValue().toString())));
                    label_birthday.setText(x.getInfo().getDateOfBirth().print());
                }
                if(!(cb_status.getSelectionModel().isEmpty()))
                {
                    x.set_status(cb_status.getValue());
                    label_status.setText(cb_status.getValue().toString());
                }
                if(!(cb_gender.getSelectionModel().isEmpty()))
                {
                    x.set_gender(cb_gender.getValue());
                    label_gender.setText(cb_gender.getValue().toString());
                }
                if(!(ta_city.getText().isEmpty()))
                {
                    x.set_city(ta_city.getText());
                    label_city.setText(ta_city.getText());
                }
                if(!(ta_city.getText().isEmpty()))
                {
                    x.set_city(ta_city.getText());
                    label_city.setText(ta_city.getText());
                }
                if(!(ta_bp.getText().isEmpty()))
                {
                    x.set_bp(ta_bp.getText());
                    label_birth_place.setText(ta_bp.getText());
                }
                if(!(ta_work.getText().isEmpty()))
                {
                    x.set_work(ta_work.getText());
                    label_work.setText(ta_work.getText());
                }
                if(!(ta_college.getText().isEmpty()))
                {
                    x.set_college(ta_college.getText());
                    label_college.setText(ta_college.getText());
                }
                if(!(ta_school.getText().isEmpty()))
                {
                    x.set_school(ta_school.getText());
                    label_school.setText(ta_school.getText());
                }
                if(!(ta_lang.getText().isEmpty()))
                {
                    x.add_lang(ta_lang.getText());
                    label_lang.setText(label_lang.getText()+ta_lang.getText());
                }


            });

            vb_change_info.setSpacing(5);
            vb_change_info.getChildren().addAll(hb_birthday,cb_status,cb_gender,ta_city,ta_bp,ta_work,ta_college,
                    ta_school,ta_lang,info_done);

            HBox hb_change_info = new HBox();
            hb_change_info.getChildren().addAll(vb_change_info_l1,vb_change_info);
            hb_change_info.setAlignment(Pos.TOP_CENTER);
            change_info_window.setScene(new Scene(hb_change_info,300,385));
            change_info_window.showAndWait();
        });

        vb3.getChildren().addAll(vb2,vb4,change_info_button);

        Label user_name= new Label();                   //user name
        user_name.setText(x.getUsername());
        user_name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

        Label user_bio= new Label();                   //user's bio
        user_bio.setText(x.getInfo().getBio());
        user_bio.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 13));
        user_bio.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 4;");
        user_bio.setPadding(new Insets(10,10,10,10));
        user_bio.setWrapText(true); //for multiline
        user_bio.setMaxWidth(700);
        user_bio.setAlignment(Pos.CENTER);


        VBox vb1 = new VBox();      // center pane
        vb1.setSpacing(20);
        vb1.setAlignment(Pos.TOP_CENTER);
        // VBox.(user_bio,Pos.CENTER_RIGHT);
        vb1.setPadding(new Insets(10,100,20,0));

        //for writing posts

        TextArea tf_posts= new TextArea();
        tf_posts.getStyleClass().add("text_field");
        tf_posts.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        tf_posts.setMaxWidth(600);
        tf_posts.setPromptText("Write a Post....");
        tf_posts.setPrefHeight(70);
        tf_posts.setWrapText(true);

        Button post_button  = new Button("Post");
        post_button.setPrefSize(50,10);

        ScrollPane sp_posts= new ScrollPane();
        VBox vb_posts = new VBox();
        vb_posts.setPadding(new Insets(10,10,10,10));
        vb_posts.setSpacing(10);
        vb_posts.setMaxWidth(580);
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
        vb_posts.getChildren().addAll(new Post("hello", "sama").getPostArea(),
                new Post(test, "sama").getPostArea(),new Post(test2, "sama").getPostArea());
        vb_posts.setStyle("-fx-background-radius: 3;");
        sp_posts.setContent(vb_posts);
        sp_posts.setMaxWidth(630);
        sp_posts.setPadding(new Insets(10,10,10,10));
        sp_posts.setStyle("-fx-background-radius: 3;");

        vb1.getChildren().addAll(user_name,user_bio,tf_posts,post_button,sp_posts);

        // bp.setPadding(new Insets(0,0,30,0));
        bp.setCenter(vb1);
        bp.setTop(hb);
        bp.setLeft(vb3);
        Scene Profile=new Scene(bp);
        Profile.getStylesheets().add("Styles.css");

        return Profile;
    }


}
