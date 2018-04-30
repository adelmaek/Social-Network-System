package socialnetwork;

import java.io.FileInputStream;
import java.util.Vector;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileNotFoundException;

public class Group {
    private String name;
    private Vector<user> members;
    private user admin;
    private Vector<Post> posts;
    private Image group_pic;
    private String group_info;

    public Group() {
        members = new Vector<user>();
        posts = new Vector<Post>();
        try {
            group_pic = new Image(new FileInputStream("E:/Mark/egfo.png")); // directory for default group picture
        } catch (FileNotFoundException ex) {
            // handle exception...
        }
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

    public void add_member(user x) {
        members.add(x);
    }

    public void add_post(Post x) {
        posts.add(x);
    }

    public Vector<Post> getPosts() {
        return posts;
    }

    public Vector<user> getMembers() {
        return members;
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
        SocialNetwork.home_button.setPrefSize(60,30);
        SocialNetwork.profile_button.setPrefSize(60,30);
        HBox hb_sn_name = new HBox();
        Label l_sn_name = new Label("Our Social Network");
        l_sn_name.setFont(Font.font("Serif", FontWeight.NORMAL, FontPosture.ITALIC, 30));
        l_sn_name.setStyle("-fx-text-fill: #FFFFFF;");
        hb_sn_name.getChildren().add(l_sn_name);
        hb_sn_name.setAlignment(Pos.CENTER_LEFT);
        hb_sn_name.setPadding(new Insets(10,10,10,20));
        hb.getChildren().addAll(SocialNetwork.home_button,SocialNetwork.profile_button);
        bp_top.setRight(hb);
        bp_top.setLeft(hb_sn_name);

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
        Label no_members_l2= new Label(String.valueOf(x.getMembers().size()));
        no_members_l1.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 17));
        no_members_l2.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 17));
        hb_no_members.getChildren().setAll(no_members_l2,no_members_l1);
        vb_left.setSpacing(30);
        Label group_info_l = new Label(x.getGroup_info());
        group_info_l.setStyle("-fx-background-colors: #FFFFFF; -fx-background-radius: 4;");
        group_info_l.setMaxWidth(300);
        group_info_l.setWrapText(true);
        group_info_l.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 13));
        group_info_l.setAlignment(Pos.CENTER);
        if(SocialNetwork.currentUser == x.admin)
        {
            ComboBox admin_control = new ComboBox();
            admin_control.getItems().addAll("Remove Post", "Remove Member");
        }
        else if(x.getMembers().indexOf(SocialNetwork.currentUser)==-1)
        {

        }
        vb_left.getChildren().addAll(group_name,group_info_l,hb_no_members);
        vb_left.setAlignment(Pos.TOP_CENTER);



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
            vb_post.getChildren().add(temp_p.getPostArea());
        }
        ScrollPane sp_post = new ScrollPane();
        sp_post.setPadding(new Insets(10,10,10,10));
        sp_post.setContent(vb_post);
        sp_post.setMaxWidth(520);
        vb_center.getChildren().add(sp_post);

        bp.setCenter(vb_center);
        bp.setLeft(vb_left);
        bp.setTop(bp_top);
        Scene scene = new Scene(bp);
        scene.getStylesheets().add("Styles.css");
        SocialNetwork.primaryStage_boundries_set_to_bounds_of_mainScreen(SocialNetwork.window);
        return  scene;
    }

}
