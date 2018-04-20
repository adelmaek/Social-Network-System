/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;


/**
 *
 * @author Adel Mahmoud
 */
public class PostTemplate {
    
    static int getPostTemplateHeight()
    {
        return 200;
    }
    static int getPostTemplateWidth()
    {
        return 1000;
    }
   static ScrollPane printAPost(String postText,String user_name)
   {
       
       ScrollPane postArea = new ScrollPane();
       Label userNameLabel = new Label(user_name);
       userNameLabel.autosize();
       userNameLabel.setLayoutX(10);
       userNameLabel.setLayoutY(10);
       
       Label postLabel = new Label(postText);
       postLabel.autosize();
       postLabel.setLayoutX(30);
       postLabel.setLayoutY(30);
       
       postArea.setPrefSize(getPostTemplateWidth(),getPostTemplateHeight());
               
       postArea.setStyle("-fx-padding:5;" + "-fx-border-style:solid;"
        + "-fx-border-width: 5;" + "-fx-border-insets: 0;"
        + "-fx-border-radius: 1;" + "-fx-border-color: #BDC3C7;"+"-fx-background-color: #ECF0F1;"+"flex-direction: column;");
       Group g = new Group();
       g.getChildren().addAll(userNameLabel,postLabel);
       postArea.setContent(g);
      
       
       
       return postArea;
   }
}
