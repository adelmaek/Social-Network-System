/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import java.io.File;
import java.io.FileInputStream;
import java.sql.BatchUpdateException;
import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Group {
    private String name;
    private Vector<String> members_names;
    private String admin;
    private Vector<Post> posts;
    private Image group_pic;
    private String group_info;

    public Group() {
        members_names = new Vector<String>();
        posts = new Vector<Post>();
        try {
            group_pic = new Image(new FileInputStream("E:/Mark/gpp.png")); // directory for default group picture
        } catch (FileNotFoundException ex) {
            // handle exception...
        }
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAdmin() {
        return admin;
    }

    public void setPosts(Vector<Post> posts) {
        this.posts = posts;
    }

    public void setMembers_names(Vector<String> members_names) {
        this.members_names = members_names;
    }

    public void setGroup_info(String group_info) {
        this.group_info = group_info;
    }

    public String getGroup_info() {
        return group_info;
    }

    public Image getGroup_pic() {
        return group_pic;
    }

    public void setGroup_pic(Image group_pic) {
        this.group_pic = group_pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add_member(String x) {
        members_names.add(x);
    }

    public void add_post(Post x) {
        posts.add(x);
    }

    public Vector<Post> getPosts() {
        return posts;
    }

    public Vector<String> getMembers_names() {
        return members_names;
    }

    public static Scene Groups (Group x)
    {
        BorderPane bp = new BorderPane();

        BorderPane bp_top=new BorderPane();
        HBox hb=new HBox();                                                       //for the top bar
        hb.setAlignment(Pos.CENTER_RIGHT);
        hb.setSpacing(10);
        hb.setMinHeight(60);
        hb.setPadding(new Insets(10,20,10,10));
        bp_top.getStyleClass().add("top_bar");
        bp_top.setPrefHeight(100);
        Button home_button=new Button("Home");
        Button profile_button=new Button("Profile");
        home_button.setPrefSize(60,30);
        profile_button.setPrefSize(60,30);
        HBox hb_sn_name = new HBox();
        Label l_sn_name = new Label("Our Social Network");
        l_sn_name.setFont(Font.font("Serif", FontWeight.NORMAL, /*FontPosture.ITALIC,*/ 40));
        l_sn_name.setStyle("-fx-text-fill: #FFFFFF;");
        hb_sn_name.getChildren().add(l_sn_name);
        hb_sn_name.setAlignment(Pos.CENTER_LEFT);
        hb_sn_name.setPadding(new Insets(10,10,10,20));
        Button b_logout = new Button("Log Out");
        hb.getChildren().addAll(home_button,profile_button,b_logout);
        bp_top.setRight(hb);
        bp_top.setLeft(hb_sn_name);
        home_button.setOnAction(e->{
            SocialNetwork.window.setScene(new HomePage().homePage(SocialNetwork.currentUser));
            SocialNetwork.window.setMaximized(true);
        });
        profile_button.setOnAction(e->{
            SocialNetwork.window.setScene(Profile.Profile(SocialNetwork.currentUser));
            SocialNetwork.window.setMaximized(true);
        });
        LoginPage login = new LoginPage();
        b_logout.setOnAction(e->{
            SocialNetwork.window.setScene(login.loginPage(SocialNetwork.window));
            SocialNetwork.window.setMaximized(true);
        });

        VBox vb_left = new VBox();
        vb_left.setPadding(new Insets(40,10,10,40));
        Label group_name= new Label();                   //group name
        group_name.setText(x.getName());
        group_name.setWrapText(true);
        group_name.setMaxWidth(450);
        group_name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        HBox hb_no_members = new HBox();
        hb_no_members.setAlignment(Pos.CENTER);
        Label no_members_l1 = new Label(" Members");
        Label no_members_l2= new Label(String.valueOf(x.getMembers_names().size()));
        no_members_l1.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 17));
        no_members_l2.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 17));
        hb_no_members.getChildren().setAll(no_members_l2,no_members_l1);
        vb_left.setSpacing(30);
        VBox vb_grp_info= new VBox();
        vb_grp_info.getStyleClass().add("info_area");
        vb_grp_info.setPadding(new Insets(10));
        vb_grp_info.setMaxWidth(320);
        Label group_info_l = new Label(x.getGroup_info());
        group_info_l.setStyle("-fx-background-colors: #FFFFFF; -fx-background-radius: 4;");
        group_info_l.setMaxWidth(300);
        group_info_l.setWrapText(true);
        group_info_l.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13));
        vb_grp_info.setAlignment(Pos.CENTER);
        vb_grp_info.getChildren().add(group_info_l);

        Button b_view_members = new Button("View Members");
        TableView<String> members_list = new TableView<>();
        TableColumn<String,String> c_friend_list = new TableColumn<>("Name");
        c_friend_list.setCellValueFactory(data->new SimpleStringProperty(data.getValue()));
        c_friend_list.setStyle("-fx-alignment : center;");
        c_friend_list.setMaxWidth(300);
        members_list.setMaxHeight(300);
        members_list.setMaxWidth(250);
        members_list.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        members_list.getColumns().add(c_friend_list);
        members_list.setItems(FXCollections.observableArrayList(x.getMembers_names()));
        Stage s_view_members = new Stage();
        b_view_members.setOnAction(e->{

            VBox vb=new VBox();
            vb.setAlignment(Pos.CENTER);
            vb.setPadding(new Insets(10));
            members_list.refresh();
            vb.getChildren().add(members_list);
            s_view_members.setScene(new Scene(vb,270,320));
            s_view_members.showAndWait();

        });
        members_list.setOnMouseClicked(e2->{

            if(members_list.getSelectionModel().getSelectedItem()!=null){
            SocialNetwork.window.setScene(Profile.Profile(SocialNetwork.searchUsersHashTable(members_list.getSelectionModel().getSelectedItem())));
            s_view_members.close();}

        });
        hb_no_members.getChildren().addAll(new Label("  "),b_view_members);
        vb_left.getChildren().addAll(group_name,vb_grp_info,hb_no_members);
        vb_left.setAlignment(Pos.TOP_CENTER);

        Button b_join_group = new Button("Join Group");

        //for writing posts

        TextArea tf_posts= new TextArea();
        tf_posts.getStyleClass().add("text_field");
        tf_posts.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15));
        tf_posts.setMaxWidth(300);
        tf_posts.setPromptText("Write a Post....");
        tf_posts.setPrefHeight(70);
        tf_posts.setWrapText(true);

        Button post_button  = new Button("Post");
        post_button.setPrefSize(50,10);


        if(x.getMembers_names().indexOf(SocialNetwork.currentUser.getUsername())==-1)
        {
             vb_left.getChildren().add(b_join_group);
        }
        else
        {
            vb_left.getChildren().addAll(tf_posts,post_button);
        }


        VBox vb_center= new VBox();
        VBox vb_pic= new VBox();                           ///// group pic
        ImageView group_pic = new ImageView(x.getGroup_pic());
        group_pic.setFitHeight(180);
        group_pic.setFitWidth(450);
        group_pic.setPreserveRatio(true);
        vb_pic.getChildren().addAll(group_pic);
        vb_pic.setStyle("-fx-border-width: 3;-fx-border-color:#000000;-fx-background-color:#ffffff;");
        vb_pic.setAlignment(Pos.CENTER);
        vb_pic.setMaxWidth(700);
        vb_center.getChildren().addAll(vb_pic);
        vb_center.setAlignment(Pos.TOP_CENTER);
        vb_center.setPadding(new Insets(20,10,20,10));
        vb_center.setSpacing(10);
        VBox vb_post = new VBox();
        vb_post.setAlignment(Pos.TOP_CENTER);
        vb_post.setPadding(new Insets(10,10,10,10));
        vb_post.setSpacing(10);
        vb_post.setStyle("-fx-background-color: #FFFFFF;");
        vb_post.setMaxWidth(500);

        for(Post temp_p : x.getPosts())
        {
            temp_p.init_post();
            vb_post.getChildren().add(temp_p.getPostArea());
        }
        ScrollPane sp_post = new ScrollPane();
        sp_post.setPadding(new Insets(10,10,10,10));
        sp_post.setContent(vb_post);
        sp_post.setMaxWidth(520);
        vb_center.getChildren().add(sp_post);

        post_button.setOnAction(e->{
            if(!tf_posts.getText().isEmpty())
            {
                Post temp = new Post(tf_posts.getText(),SocialNetwork.currentUser.getUsername());
                x.getPosts().add(0,temp);
               vb_post.getChildren().add(0,temp.getPostArea());
               tf_posts.setText("");
               tf_posts.setPromptText("Write a Post....");
            }
        });
        b_join_group.setOnAction(event -> {
            x.add_member(SocialNetwork.currentUser.getUsername());
            SocialNetwork.currentUser.add_group(x.getName());
            vb_left.getChildren().remove(b_join_group);
            vb_left.getChildren().addAll(tf_posts,post_button);
            no_members_l2.setText(String.valueOf(x.getMembers_names().size()));
        });
        Button b_add_members = new Button("Add Members");
        Button b_remove_members = new Button("Remove Members");
        Button b_remove_Post = new Button("Remove Post");
        Button b_change_info = new Button("Change Group Description");
        Button b_change_pic = new Button("Change Group Picture");
        VBox vb_admin = new VBox();
        vb_admin.setSpacing(7);
        vb_admin.setAlignment(Pos.CENTER);
        HBox hba1 = new HBox();
        hba1.setSpacing(7);
        hba1.setAlignment(Pos.CENTER);
        hba1.getChildren().addAll(b_add_members,b_remove_members,b_remove_Post);
        HBox hba2 = new HBox();
        hba2.setSpacing(7);
        hba2.setAlignment(Pos.CENTER);
        hba2.getChildren().addAll(b_change_info,b_change_pic);
        vb_admin.getChildren().addAll(hba1,hba2);
        if(SocialNetwork.currentUser.getUsername()==x.getAdmin())
        {
            vb_left.getChildren().addAll(vb_admin);
        }
        b_add_members.setOnAction(e->{
            Stage stage_create_group= new Stage();
            VBox vb_create_group = new VBox();
            vb_create_group.setAlignment(Pos.TOP_CENTER);
            vb_create_group.setSpacing(7);
            vb_create_group.setPadding(new Insets(10));
            Label add_to_group = new Label("Select People from your Friends to add to the Group");
            user admin = SocialNetwork.searchUsersHashTable(x.getAdmin());
            ListView<String> friends_list = new ListView<>(FXCollections.observableArrayList(admin.getFriends_names()));
            friends_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            friends_list.setMaxHeight(300);
            friends_list.setMaxWidth(250);
            Button add_member_done = new Button("Done");
            add_member_done.setOnAction(e2->{
                if(friends_list.getSelectionModel().getSelectedItems()!=null)
                {
                    ObservableList<String> friends_list2= friends_list.getSelectionModel().getSelectedItems();
                    for(String s:friends_list2)
                    {
                        x.add_member(s);
                        members_list.getItems().add(s);
                        SocialNetwork.searchUsersHashTable(s).add_group(x.getName());
                    }
                    no_members_l2.setText(String.valueOf(x.getMembers_names().size()));
                    stage_create_group.close();

                }
            });
            vb_create_group.getChildren().addAll(add_to_group,friends_list,add_member_done);
            stage_create_group.setScene(new Scene(vb_create_group,350,320));
            stage_create_group.showAndWait();
        });
        b_remove_members.setOnAction(e->{
            Stage stage_create_group= new Stage();
            VBox vb_create_group = new VBox();
            vb_create_group.setAlignment(Pos.TOP_CENTER);
            vb_create_group.setSpacing(7);
            vb_create_group.setPadding(new Insets(10));
            Label add_to_group = new Label("Select People from your Group to remove");
            Group grp = SocialNetwork.searchGroupsHashTable(x.getName());
            ListView<String> friends_list = new ListView<>(FXCollections.observableArrayList(grp.getMembers_names()));
            friends_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            friends_list.setMaxHeight(300);
            friends_list.setMaxWidth(250);
            Button remove_member_done = new Button("Done");
            remove_member_done.setOnAction(e2->{
                if(friends_list.getSelectionModel().getSelectedItems()!=null)
                {
                    ObservableList<String> friends_list2= friends_list.getSelectionModel().getSelectedItems();
                    for(String s:friends_list2)
                    {
                        if(s==x.getAdmin())
                        {
                            Stage error = new Stage();
                            VBox vb_error = new VBox();
                            vb_error.setPadding(new Insets(10));
                            vb_error.setAlignment(Pos.CENTER);
                            vb_error.setSpacing(7);
                            Button ok =new Button("ok");
                            vb_error.getChildren().addAll(new Label ("Can't remove yourself from the group"),ok);
                            ok.setOnAction(e3->error.close());
                            error.setScene(new Scene(vb_error,300,100));
                            error.showAndWait();
                            continue;
                        }
                        x.getMembers_names().remove(s);
                        members_list.getItems().remove(s);
                        SocialNetwork.searchUsersHashTable(s).getGroups_names().remove(x.getName());
                    }
                    no_members_l2.setText(String.valueOf(x.getMembers_names().size()));
                    stage_create_group.close();

                }
            });
            vb_create_group.getChildren().addAll(add_to_group,friends_list,remove_member_done);
            stage_create_group.setScene(new Scene(vb_create_group,350,320));
            stage_create_group.showAndWait();
        });
        b_remove_Post.setOnAction(e->{
            Stage stage_create_group= new Stage();
            VBox vb_create_group = new VBox();
            vb_create_group.setAlignment(Pos.TOP_CENTER);
            vb_create_group.setSpacing(7);
            vb_create_group.setPadding(new Insets(10));
            Label add_to_group = new Label("Select Post from your Group to remove");
            Group grp = SocialNetwork.searchGroupsHashTable(x.getName());
            ObservableList<String> posts_list_s =FXCollections.observableArrayList();
            for(Post p:x.getPosts())
                posts_list_s.add(p.getPostContent());
            ListView<String> posts_list = new ListView<>(posts_list_s);
            posts_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            posts_list.setMaxHeight(300);
            posts_list.setMaxWidth(250);
            Button remove_post_done = new Button("Done");
            remove_post_done.setOnAction(e2->{
                if(posts_list.getSelectionModel().getSelectedItems()!=null)
                {
                    ObservableList<String> posts_list2= posts_list.getSelectionModel().getSelectedItems();
                    for(String s:posts_list2)
                    {
                        for(Post p:x.getPosts())
                        {
                            if(p.getPostContent()==s)
                            {    x.getPosts().remove(p);
                            for(Node n:vb_post.getChildren())
                            {
                                if(((Label)(((VBox)n).getChildren().get(1))).getText()==s)
                                    vb_post.getChildren().remove(n);
                            }}
                        }

                    }
                    no_members_l2.setText(String.valueOf(x.getMembers_names().size()));
                    stage_create_group.close();

                }
            });
            vb_create_group.getChildren().addAll(add_to_group,posts_list,remove_post_done);
            stage_create_group.setScene(new Scene(vb_create_group,350,320));
            stage_create_group.showAndWait();
        });

        b_change_info.setOnAction(e->{
            Stage stage_change_info= new Stage();
            VBox vb_change_info = new VBox();
            vb_change_info.setAlignment(Pos.TOP_CENTER);
            vb_change_info.setPadding(new Insets(10));
            vb_change_info.setSpacing(10);
            Label lhb2 = new Label("Group Description  ");
            TextArea thb2 = new TextArea();
            thb2.setWrapText(true);
            thb2.setMaxWidth(280);
            thb2.setMaxHeight(100);
            Button b_change_info_done = new Button("Done");
            b_change_info_done.setOnAction(e2->{
                if(!thb2.getText().isEmpty())
                {
                    x.setGroup_info(thb2.getText());
                    group_info_l.setText(thb2.getText());
                    stage_change_info.close();
                }
            });
            vb_change_info.getChildren().addAll(lhb2,thb2,b_change_info_done);
            stage_change_info.setScene(new Scene(vb_change_info,300,170));
            stage_change_info.showAndWait();
        });
        b_change_pic.setOnAction(e->{
            Stage fc_stage = new Stage();
            FileChooser fc = new FileChooser();
            File f =fc.showOpenDialog(fc_stage);
            String pic_path;
            if(f!= null)
            {
                pic_path = f.getAbsolutePath();
                try {
                    Image new_pic= new Image(new FileInputStream(pic_path));
                    x.setGroup_pic(new_pic);
                    group_pic.setImage(new_pic);
                }
                catch (FileNotFoundException ex) {
                    // handle exception...
                }
            }
        });

        bp.setCenter(vb_center);
        bp.setLeft(vb_left);
        bp.setTop(bp_top);
        Scene scene = new Scene(bp);
        scene.getStylesheets().add("Styles.css");
        SocialNetwork.primaryStage_boundries_set_to_bounds_of_mainScreen(SocialNetwork.window);
        return  scene;
    }

}