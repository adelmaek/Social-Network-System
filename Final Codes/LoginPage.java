/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import socialnetwork.SocialNetwork;

import java.io.IOException;

import static socialnetwork.SocialNetwork.window;

/**
 *
 * @author Adel Mahmoud
 */
public class LoginPage {
    Button loginButton;
    Button registerButton;
    Button VisualizeButton;
    BorderPane page = new BorderPane();

    Label registerLabel;
    Label loginLabel;
    Label NameOfApp;
    Stage window;

    VBox register;
    HBox login;

    ObservableList<String> gender = FXCollections.observableArrayList("Female","Male");
    ObservableList<String> marritalstatus = FXCollections.observableArrayList("Single","In a Relationship","Engaged","Married");

    Label RegisterGenderLabel;
    ComboBox GenderComboBox;
    Label RegisterMarritalStatusLabel;
    ComboBox MarritalStatusComboBox;
    Label RegisterDateOfBirthLabel;
    DatePicker RegisterDateOfBirth;

    TextField loginUsername;
    PasswordField loginPassword;


    Label RegisterUsernameLabel;
    TextField registerUsername;
    Label RegisterPasswordLabel;
    TextField registerPassword;
    Label RegisterCityLabel;
    TextField RegisterCity;
    Label RegisterPlaceOfBirthLabel;
    TextField RegisterPlaceOfBirth;
   
   private void setTop()
    {
        HBox top = new HBox(300);

        Button VisualizeButton = new Button ("  Users graph  ");
        VisualizeButton.setOnAction(e->{

            try {
                Process P = Runtime.getRuntime().exec("python graph.py");
            } catch (IOException e1) {
                //
            }
        });

        NameOfApp = new Label ("    Our Social Network");
        NameOfApp.setStyle("-fx-font:40px \"Serif\";\n" + "-fx-text-fill: white;");
        NameOfApp.setAlignment(Pos.BASELINE_LEFT);


        login = new HBox (20);

        loginLabel = new Label("Login Here");
        loginLabel.setStyle("-fx-font:15px \"Verdana\";\n" + "-fx-text-fill: white;");

        loginButton = new Button();
        loginButton.setText("  Login  ");
        loginButton.setOnAction(e->{
        String username = loginUsername.getText();
            String Password = loginPassword.getText();
            user loggeduser=SocialNetwork.searchUsersHashTable(username);
            
           System.out.println(SocialNetwork.searchUsersHashTable(username));
              // user loggeduser = SocialNetwork.usersHashTable[432].get(0);
               
            if(loggeduser==null)
            {
                MessageBox.display("User name  error","Please sign up first");
            }
            else if(!loggeduser.getPassword().equals(Password))
            {
                //say that password is inccorrect
                MessageBox.display("Password error","Incorrect password,try again.");
            }
            else
            {
                SocialNetwork.currentUser = loggeduser;
                SocialNetwork.window.setScene(new HomePage().homePage(SocialNetwork.currentUser));
                SocialNetwork.window.show();
            }

        });

        loginUsername = new TextField();
        loginUsername.setPromptText("Enter username");
        loginPassword = new PasswordField();
        loginPassword.setPromptText("Enter your password");

        login.getChildren().addAll(loginLabel,loginUsername,loginPassword,loginButton,VisualizeButton);
        login.setAlignment(Pos.CENTER);

        top.getChildren().addAll(NameOfApp,login);
        top.setAlignment(Pos.CENTER_LEFT);
        top.setStyle("-fx-background-color: khaki");
        top.setPrefHeight(100);



        page.setTop(top);

    }
    private void setCenter ()
    {

        registerLabel = new Label("Register First");
        registerLabel.setStyle("-fx-font:20px \"Serif\";\n" + "-fx-text-fill: #6495ED;");
        registerLabel.setAlignment(Pos.TOP_LEFT);
        registerLabel.setPrefWidth(500);

        registerUsername = new TextField();

        registerUsername.setPromptText("ex: SamaELBaroudy , Adel3 ,...");
        RegisterUsernameLabel = new Label("Choose Username");
        HBox UsernameBox = new HBox(20);
        UsernameBox.getChildren().addAll(RegisterUsernameLabel,registerUsername);
        UsernameBox.setPrefWidth(400);

        RegisterPasswordLabel = new Label("Choose password");
        registerPassword = new TextField();
        HBox PasswordBox = new HBox(20);
        PasswordBox.getChildren().addAll(RegisterPasswordLabel,registerPassword);
        PasswordBox.setPrefWidth(400);

        RegisterDateOfBirthLabel = new Label ("Your Date of Birth");
        RegisterDateOfBirth = new DatePicker();
        HBox DateBox = new HBox(20);
        DateBox.getChildren().addAll(RegisterDateOfBirthLabel,RegisterDateOfBirth);
        DateBox.setPrefWidth(400);

        RegisterGenderLabel = new Label ("Gender");
        GenderComboBox = new ComboBox(gender);
        RegisterMarritalStatusLabel = new Label ("Marrital Status");
        MarritalStatusComboBox = new ComboBox(marritalstatus);
        HBox GenderMarritalBox = new HBox(20);
        GenderMarritalBox.getChildren().addAll(RegisterGenderLabel,GenderComboBox,RegisterMarritalStatusLabel,MarritalStatusComboBox);
        GenderMarritalBox.setPrefWidth(400);

        RegisterPlaceOfBirthLabel= new Label ("Place oF Birth");
        RegisterPlaceOfBirth = new TextField();
        HBox PlaceOfBirthBOx = new HBox(50);
        PlaceOfBirthBOx.getChildren().addAll(RegisterPlaceOfBirthLabel,RegisterPlaceOfBirth);
        PlaceOfBirthBOx.setPrefWidth(400);

        RegisterCityLabel = new Label("Where you Live now");
        RegisterCity = new TextField();
        HBox CityBox = new HBox(15);
        CityBox.getChildren().addAll(RegisterCityLabel,RegisterCity);
        CityBox.setPrefWidth(400);

        registerButton= new Button();
        registerButton.setText("  register  ");
        registerButton.setOnAction(e->{
               //new user data
            // we have to check that he filled all
            user NewEntry = new user () ;
            int day = RegisterDateOfBirth.getValue().getDayOfMonth();
            int month = RegisterDateOfBirth.getValue().getMonthValue();
            int year = RegisterDateOfBirth.getValue().getYear();
            Date_Of_Birth dateofbirthnew = new Date_Of_Birth(day,month,year);
            Informations newEntryInfo = new Informations();
            newEntryInfo.setDateOfBirth(dateofbirthnew);
            newEntryInfo.setBirth_place(RegisterPlaceOfBirth.getText());
            newEntryInfo.setCity(RegisterCity.getText());
            
            NewEntry.setPassword(registerPassword.getText());
            NewEntry.setUsername(registerUsername.getText());
            if (GenderComboBox.getSelectionModel().getSelectedItem()=="Female")
            {
                newEntryInfo.setGender(Informations.Gender.female);

            }
            else if (GenderComboBox.getSelectionModel().getSelectedItem()=="Male")
            {
                newEntryInfo.setGender(Informations.Gender.male);
            }
            //"Single","In a Relationship","Engaged","Married"

            if (MarritalStatusComboBox.getSelectionModel().getSelectedItem()=="Single") {
                newEntryInfo.setStatus(Informations.MaritalStatus.single);

            }
            else if (MarritalStatusComboBox.getSelectionModel().getSelectedItem()=="In a Relationship")
            {
                newEntryInfo.setStatus(Informations.MaritalStatus.inarelationship);
            }
            else if (MarritalStatusComboBox.getSelectionModel().getSelectedItem()=="Engaged")
            {
                newEntryInfo.setStatus(Informations.MaritalStatus.engaged);
            }
            else if (MarritalStatusComboBox.getSelectionModel().getSelectedItem()=="Married")
            {
                newEntryInfo.setStatus(Informations.MaritalStatus.married);
            }
            
            NewEntry.setInfo(newEntryInfo);
            SocialNetwork.SaveUserInFile(NewEntry);
           // System.out.println(USERS.toJSONString());         
        });
        registerButton.setAlignment(Pos.CENTER);

        register = new VBox(20);
        register.getChildren().addAll(registerLabel,UsernameBox,PasswordBox,DateBox,GenderMarritalBox,PlaceOfBirthBOx,CityBox,registerButton);
        register.setAlignment(Pos.CENTER);
        register.setMaxWidth(400);


        page.setCenter(register);

    }
    public Scene loginPage (Stage window)
    {
        window.setTitle("Login Page");
        setTop();
        setCenter ();
        Scene login = new Scene(page, 1300, 650);
        return login;
    }
    

}
