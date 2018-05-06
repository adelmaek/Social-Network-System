/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.stage.FileChooser;

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

    public static Scene Profile (user x)
    {

        BorderPane bp = new BorderPane();                   //main pane

        BorderPane bp_top=new BorderPane();
        HBox hb=new HBox();                                                       //for the top bar
        hb.setAlignment(Pos.CENTER_RIGHT);
        hb.setSpacing(10);
        hb.setMinHeight(60);
        hb.setPadding(new Insets(10,20,10,10));
        bp_top.getStyleClass().add("top_bar");
        Button home_button=new Button("Home");
        Button profile_button=new Button("Profile");
        home_button.setPrefSize(60,30);
        profile_button.setPrefSize(60,30);
        HBox hb_sn_name = new HBox();
        Label l_sn_name = new Label("Our Social Network");
        l_sn_name.setFont(Font.font("Serif", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        l_sn_name.setStyle("-fx-text-fill: #FFFFFF;");
        hb_sn_name.getChildren().add(l_sn_name);
        hb_sn_name.setAlignment(Pos.CENTER_LEFT);
        hb_sn_name.setPadding(new Insets(10,10,10,20));
        hb.getChildren().addAll(home_button,profile_button);
        bp_top.setRight(hb);
        bp_top.setLeft(hb_sn_name);
        home_button.setOnAction(e->{
            SocialNetwork.window.setScene(new HomePage().homePage(SocialNetwork.currentUser));
            SocialNetwork.window.setMaximized(true);
        });
        profile_button.setOnAction(e->{
            SocialNetwork.window.setScene(Profile(SocialNetwork.currentUser));
            SocialNetwork.window.setMaximized(true);
        });




        VBox vb3 = new VBox(); // for profile pic and info

        VBox vb2= new VBox();                                    //for the profile picture
        vb2.setPadding(new Insets(10,10,10,10));
        vb2.setAlignment(Pos.CENTER);
        Image image = x.getProfilePicture();      //Profile Picture
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(240);
        imageView.setFitWidth(240);
        imageView.setPreserveRatio(true);
        vb2.getStyleClass().add("image_view");
        vb2.getChildren().addAll(imageView);
        vb2.setMaxHeight(imageView.getFitHeight());

        VBox vb4 = new VBox(); // for info
        vb4.getStyleClass().add("info_area");
        vb4.setPadding(new Insets(10,5,5,10));
        vb4.setMaxWidth(270);
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
        label_no_friends.setMaxWidth(260);
        Label label_birthday = new Label("Birthday: "+x.getInfo().getDateOfBirth().print());///
        label_birthday.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_birthday.setAlignment(Pos.CENTER);
        label_birthday.setWrapText(true);
        label_birthday.setMaxWidth(260);
        Label label_status = new Label("status: "+x.getInfo().getStatus().toString());///
        label_status.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_status.setAlignment(Pos.CENTER);
        label_status.setWrapText(true);
        label_status.setMaxWidth(260);
        Label label_gender = new Label("Gender: "+x.getInfo().getGender().toString());///
        label_gender.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_gender.setAlignment(Pos.CENTER);
        label_gender.setMaxWidth(260);
        Label label_city= new Label("City: "+x.getInfo().getCity());///
        label_city.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_city.setAlignment(Pos.CENTER);
        label_city.setWrapText(true);
        label_city.setMaxWidth(260);
        Label label_birth_place= new Label("Birth Place: "+x.getInfo().getBirth_place());///
        label_birth_place.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_birth_place.setAlignment(Pos.CENTER);
        label_birth_place.setMaxWidth(260);
        label_birth_place.setWrapText(true);
        Label label_work= new Label("Work: "+x.getInfo().getWork());///
        label_work.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_work.setAlignment(Pos.CENTER);
        label_work.setMaxWidth(260);
        label_work.setWrapText(true);
        Label label_college= new Label("College: "+x.getInfo().getCollege());///
        label_college.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_college.setAlignment(Pos.CENTER);
        label_college.setMaxWidth(260);
        label_college.setWrapText(true);
        Label label_school= new Label("School: "+x.getInfo().getSchool());///
        label_school.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));
        label_school.setAlignment(Pos.CENTER);
        label_school.setMaxWidth(260);
        label_school.setWrapText(true);
        String lang=" ";
        for(String l : x.getInfo().getLanguages())
            lang+=l+", ";
        if(lang.length()>2)
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
        vb3.setMaxWidth(340);
        vb3.setPadding(new Insets(20,0,20,20));
        vb3.setSpacing(20);

        Button change_info_button = new Button("Change Info");
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
                    x.getInfo().setDateOfBirth(new Date_Of_Birth(Integer.valueOf(day.getValue()),Integer.valueOf(months.getValue().toString())
                            ,Integer.valueOf(years.getValue().toString())));
                   label_birthday.setText("Birthday: "+x.getInfo().getDateOfBirth().print());
                }
                if(!(cb_status.getSelectionModel().isEmpty()))
                {
                    x.getInfo().setStatus(cb_status.getValue());
                    label_status.setText("Status: "+cb_status.getValue().toString());
                }
                if(!(cb_gender.getSelectionModel().isEmpty()))
                {
                    x.getInfo().setGender(cb_gender.getValue());
                    label_gender.setText("Gender: "+cb_gender.getValue().toString());
                }
                if(!(ta_city.getText().isEmpty()))
                {
                    x.getInfo().setCity(ta_city.getText());
                    label_city.setText("City: "+ta_city.getText());
                    if(vb4.getChildren().indexOf(label_city)==-1)
                        vb4.getChildren().add(label_city);
                }
                if(!(ta_bp.getText().isEmpty()))
                {
                    x.getInfo().setBirth_place(ta_bp.getText());
                    label_birth_place.setText("Birth Place: "+ta_bp.getText());
                    if(vb4.getChildren().indexOf(label_birth_place)==-1)
                        vb4.getChildren().add(label_birth_place);
                }
                if(!(ta_work.getText().isEmpty()))
                {
                    x.getInfo().setWork(ta_work.getText());
                    label_work.setText("Work: "+ta_work.getText());
                    if(vb4.getChildren().indexOf(label_work)==-1)
                        vb4.getChildren().add(label_work);
                }
                if(!(ta_college.getText().isEmpty()))
                {
                    x.getInfo().setCollege(ta_college.getText());
                    label_college.setText("College: "+ta_college.getText());
                    if(vb4.getChildren().indexOf(label_college)==-1)
                        vb4.getChildren().add(label_college);
                }
                if(!(ta_school.getText().isEmpty()))
                {
                    x.getInfo().setSchool(ta_school.getText());
                    label_school.setText("School: "+ta_school.getText());
                    if(vb4.getChildren().indexOf(label_school)==-1)
                        vb4.getChildren().add(label_school);
                }
                if(!(ta_lang.getText().isEmpty()))
                {
                    x.getInfo().addLanguage(ta_lang.getText());
                    label_lang.setText(label_lang.getText()+", "+ta_lang.getText());
                    if(vb4.getChildren().indexOf(label_lang)==-1)
                        vb4.getChildren().add(label_lang);
                }

                change_info_window.close();
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

        Button change_pic = new Button("Change Profile Picture");

        change_pic.setOnAction(e->{
            Stage fc_stage = new Stage();
            FileChooser fc = new FileChooser();
            File f =fc.showOpenDialog(fc_stage);
            String pic_path;
            if(f!= null)
            {
               pic_path = f.getAbsolutePath();
                try {
                   Image new_pic= new Image(new FileInputStream(pic_path));
                    x.setProfilePicture(new_pic);
                    imageView.setImage(new_pic);
                }
                catch (FileNotFoundException ex) {
                    // handle exception...
                }
            }
        });

        HBox hb_change = new HBox();
        hb_change.setAlignment(Pos.CENTER);
        hb_change.setSpacing(7);
        hb_change.getChildren().addAll(change_info_button,change_pic);
        vb3.getChildren().addAll(vb2,vb4);
        if(x.getUsername()==SocialNetwork.currentUser.getUsername())
        {
            vb3.getChildren().add(hb_change);
        }

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
        vb1.setPadding(new Insets(10,0,20,0));

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
        for(Post p:x.getPosts())
        {
            vb_posts.getChildren().add(p.getPostArea());
        }
        vb_posts.setStyle("-fx-background-radius: 3;");
        sp_posts.setContent(vb_posts);
        sp_posts.setMaxWidth(630);
        sp_posts.setPadding(new Insets(10,10,10,10));
        sp_posts.setStyle("-fx-background-radius: 3;");

        vb1.getChildren().addAll(user_name,user_bio);
        if(x.getUsername()==SocialNetwork.currentUser.getUsername())
        {
            vb1.getChildren().addAll(tf_posts,post_button);
        }

        post_button.setOnAction(e->{
            if(!tf_posts.getText().isEmpty())
            {
                Post temp = new Post(tf_posts.getText(),SocialNetwork.currentUser.getUsername());
                x.getPosts().add(0,temp);
                vb_posts.getChildren().add(0,temp.getPostArea());
                tf_posts.setText("");
                tf_posts.setPromptText("Write a Post....");
            }
        });

        vb1.getChildren().addAll(sp_posts);
        // bp.setPadding(new Insets(0,0,30,0));
        VBox vb_right = new VBox();                   ////right section
        vb_right.setPadding(new Insets(60,60,0,0));
        vb_right.setAlignment(Pos.TOP_CENTER);
        vb_right.setSpacing(10);
        Button b_add_friend = new Button("Add Friend");
        Button b_create_group= new Button("Create a Group");
        if(x.getUsername()!=SocialNetwork.currentUser.getUsername() &&
                SocialNetwork.currentUser.getFriends_names().indexOf(x.getUsername())==-1)
        {
            vb_right.getChildren().addAll(b_add_friend);
        }
        TableColumn<String,String>  groups_c= new TableColumn<>("Groups");    /////groups
        groups_c.setMaxWidth(150);
        groups_c.setCellValueFactory(data->new SimpleStringProperty(data.getValue()));
        groups_c.setStyle("-fx-alignment: center;");
        TableView<String> group_t = new TableView<>();
        group_t.getColumns().addAll(groups_c);
        group_t.setItems(FXCollections.observableArrayList(x.getGroups_names()));
        group_t.setMaxHeight(400);
        group_t.setPadding(new Insets(10));
        group_t.setMaxWidth(170);
        group_t.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        group_t.setOnMouseClicked(e->{

        if(group_t.getSelectionModel().getSelectedItem()!=null) {
        SocialNetwork.window.setScene(Group.Groups(SocialNetwork.searchGroupsHashTable(group_t.getSelectionModel().getSelectedItem())));
        SocialNetwork.window.setMaximized(true);
        }
        });
        Button b_view_friends = new Button("View Friends");
        vb_right.getChildren().add(group_t);
        if(x.getUsername()==SocialNetwork.currentUser.getUsername())
        {
            vb_right.getChildren().addAll(b_create_group);
        }
        vb_right.getChildren().add(b_view_friends);



        b_create_group.setOnAction(e->{
            Stage stage_create_group= new Stage();
            VBox vb_create_group = new VBox();
            vb_create_group.setAlignment(Pos.TOP_CENTER);
            vb_create_group.setPadding(new Insets(10));
            vb_create_group.setSpacing(10);
            HBox hb1 = new HBox();
            hb1.setAlignment(Pos.CENTER_LEFT);
            Label lhb1 = new Label("Group Name  ");
            TextField thb1 = new TextField();
            thb1.setMaxWidth(150);
            hb1.getChildren().addAll(lhb1,thb1);
            HBox hb2 = new HBox();
            hb2.setAlignment(Pos.CENTER_LEFT);
            Label lhb2 = new Label("Group Description  ");
            TextArea thb2 = new TextArea();
            thb2.setWrapText(true);
            thb2.setMaxWidth(280);
            thb2.setMaxHeight(100);
            hb2.getChildren().addAll(lhb2);
            Label add_to_group = new Label("Select People from your Friends to add to the Group");
            ListView<String> friends_list = new ListView<>(FXCollections.observableArrayList(x.getFriends_names()));
            friends_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            friends_list.setMaxHeight(150);
            friends_list.setMaxWidth(250);
            Button create_group_done = new Button("Done");

            create_group_done.setOnAction(e2->{
                socialnetwork.Group created_group =new socialnetwork.Group();
                if(thb1.getText().isEmpty())
                {
                    Stage error =new Stage();
                    VBox vb_error =new VBox();
                    Label l_error = new Label ("Please Enter Group Name!");
                    vb_error.getChildren().add(l_error);
                    vb_error.setSpacing(10);
                    vb_error.setPadding(new Insets(10));
                    Button b_error = new Button("Ok");
                    vb_error.getChildren().add(b_error);
                    vb_error.setAlignment(Pos.CENTER);
                    b_error.setOnAction(e3->error.close());
                    error.setScene(new Scene(vb_error,200,80));
                    error.showAndWait();
                }
                else
                {
                    created_group.setName(thb1.getText());
                }
                if(!thb2.getText().isEmpty())
                {
                    created_group.setGroup_info(thb2.getText());
                }
                if(friends_list.getSelectionModel().getSelectedItems()!=null)
                {
                    ObservableList<String> friends_list2= friends_list.getSelectionModel().getSelectedItems();
                    for(String s:friends_list2)
                    {
                        user u = SocialNetwork.searchUsersHashTable(s) ;
                        created_group.add_member(u.getUsername());
                        u.add_group(created_group.getName());

                    }

                }
                if(!thb1.getText().isEmpty()) {
                    x.add_group(created_group.getName());
                    created_group.setAdmin(x.getUsername());
                    created_group.add_member(x.getUsername());
                    SocialNetwork.addToGroupsHashTable(created_group);
                    group_t.getItems().add(created_group.getName());
                    group_t.refresh();
                    stage_create_group.close();
                }
            });

            vb_create_group.getChildren().addAll(hb1,hb2,thb2,add_to_group,friends_list,create_group_done);
            stage_create_group.setScene(new Scene(vb_create_group,350,400));
            stage_create_group.showAndWait();
        });

        TableView<String> friends_list = new TableView<>();
        TableColumn<String,String> c_friend_list = new TableColumn<>("Name");
        c_friend_list.setCellValueFactory(data->new SimpleStringProperty(data.getValue()));
        c_friend_list.setStyle("-fx-alignment : center;");
        c_friend_list.setMaxWidth(300);
        friends_list.setMaxHeight(300);
        friends_list.setMaxWidth(250);
        friends_list.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        friends_list.setItems(FXCollections.observableArrayList(x.getFriends_names()));
        friends_list.getColumns().add(c_friend_list);
        Stage s_view_friends = new Stage();
        b_view_friends.setOnAction(e->{

            VBox vb=new VBox();
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(10));
            friends_list.refresh();
            vb.getChildren().add(friends_list);
            s_view_friends.setScene(new Scene(vb,270,320));
            s_view_friends.showAndWait();

        });
        friends_list.setOnMouseClicked(e2->{

            if(friends_list.getSelectionModel().getSelectedItem()!=null){
            SocialNetwork.window.setScene(Profile(SocialNetwork.searchUsersHashTable(friends_list.getSelectionModel().getSelectedItem())));
            s_view_friends.close();}

        });

        b_add_friend.setOnAction(e->{
            x.addFriend(SocialNetwork.currentUser.getUsername());
            SocialNetwork.currentUser.addFriend(x.getUsername());
            label_no_friends.setText("Number of Friends: "+x.getNumberOfFriends());
            vb_right.getChildren().remove(b_add_friend);
            friends_list.getItems().add(SocialNetwork.currentUser.getUsername());
            friends_list.refresh();

        });

        bp.setRight(vb_right);
        bp.setCenter(vb1);
        bp.setTop(bp_top);
        bp.setLeft(vb3);
        Scene Profile=new Scene(bp);
        Profile.getStylesheets().add("Styles.css");
        SocialNetwork.primaryStage_boundries_set_to_bounds_of_mainScreen(SocialNetwork.window);
        return Profile;
    }


}