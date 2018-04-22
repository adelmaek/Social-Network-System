package sample;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.io.FileInputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SocialNetwork extends Application {

    static user Sama = new user() ;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Social Network");
        primaryStage.setScene(Profile(Sama));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static Scene Profile (user x)
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
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        vb2.getStyleClass().add("image_view");
        vb2.getChildren().addAll(imageView);
        vb2.setMaxHeight(imageView.getFitHeight());

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
        vb3.getChildren().addAll(vb2,vb4);

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
        vb1.setPadding(new Insets(20,100,20,0));

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
        vb_posts.setPadding(new Insets(20,20,20,20));
        vb_posts.setSpacing(10);
        vb_posts.setMaxWidth(600);
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
        vb_posts.getChildren().addAll(new Post("hello", "sama",345).getAll_post(),new Post(test, "sama",345).getAll_post(),new Post(test2, "sama",345).getAll_post());
        sp_posts.setContent(vb_posts);
        sp_posts.setMaxWidth(630);

        vb1.getChildren().addAll(user_name,user_bio,tf_posts,post_button,sp_posts);

        bp.setPadding(new Insets(0,0,100,0));
        bp.setCenter(vb1);
        bp.setTop(hb);
        bp.setLeft(vb3);
        Scene Profile=new Scene(bp,700, 500);
        Profile.getStylesheets().add("Styles.css");

        return Profile;
    }


    public static void main(String[] args) {
        Sama.setUsername("sama");
        Sama.setPassword("***");
        user friend = new user("Perry","ll",1);
        Sama.addFriend(friend);
        System.out.println(Sama.getNumberOfFriends());
        Informations Her = new Informations() ;
        Her.setGender(Informations.Gender.female);
        Her.setStatus(Informations.MaritalStatus.single);
        Her.addLanguage("french");
        Her.addLanguage("English");
        System.out.println(Her.getNumberOfLanguages());
        Her.setBio("I LOVE FOOD ");
        Her.setCity("CAIRO");
        Her.setSchool("NDA");
        Her.setCollege("ENG ASU");
        Her.setBirth_place("Egypt");
        Her.setWork("General Manager");
        Date_Of_Birth date_of_birth = new Date_Of_Birth(13,9,1996);
        Her.setDateOfBirth(date_of_birth);
        Sama.setInfo(Her);
        System.out.println(Sama.toString());
        launch(args);
    }
}
