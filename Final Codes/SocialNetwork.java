
package socialnetwork;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;


import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import socialnetwork.Post;
 import socialnetwork.LoginPage;
import socialnetwork.user;

public class SocialNetwork extends Application {

     public static Stage window;
     public static user currentUser = new user();
     public static Image image ;

     public static Vector <user> UsersInSystem = new Vector<user> ();
     public static Map < String , Map< String, Integer >> adjencyMatrix;
     public static final int hashTableSize =1000;
    public static LinkedList<user>[] usersHashTable = new LinkedList[hashTableSize];
    public static LinkedList<socialnetwork.Group>[] groupHashTable = new LinkedList[hashTableSize];
   
        //************************Hash Function*******************************************************************
    public static long  hashFunc(String user_name,int storageSize)
    {
        long  sum = 0;
        int intLength = user_name.length() / 4;
        for (int j = 0; j < intLength; j++) 
        {
            char c[] = user_name.substring(j * 4, (j * 4) + 4).toCharArray();
             long mult = 1;
            for (int k = 0; k < c.length; k++) 
            {
             sum += c[k] * mult;
             mult *= 256;
            }
        }
        char c[] = user_name.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) 
        {
            sum += c[k] * mult;
            mult *= 256;
        }
        
     return(Math.abs(sum) % storageSize);
    }
    static int count=0;
    public static user searchUsersHashTable(String user_name)
    {
        user found = null ;
       int hashNumber = (int)(hashFunc(user_name,hashTableSize));
       //System.out.println(hashNumber);
       if(usersHashTable[hashNumber]==null)return found;
       else{
       
       for (user myUser:usersHashTable[hashNumber])
       {
           if(myUser.getUsername().equals(user_name) )
           {
               found = myUser;
           }
       }
       }
       
       return found;    
    }  
    public static boolean addToHashTable (user user_name)
    {
       boolean added =false;
        if(searchUsersHashTable(user_name.getUsername())!=null)
            added = false;
        else
        {
            int hashNumber = (int)(hashFunc(user_name.getUsername(),hashTableSize));
            //.out.print(hashNumber);
            //System.out.print(user_name +"\n");
            if(usersHashTable[hashNumber]==null)
            {
                
                usersHashTable[hashNumber] = new LinkedList<user>();
                //System.out.println("putting: "+user_name.getUsername());
                //System.out.println(hashNumber);
                usersHashTable[hashNumber].add(user_name);
            }
            else{
                usersHashTable[hashNumber].add(user_name);}
            added =true;
        }
        return added;
    }
    public static socialnetwork.Group searchGroupsHashTable(String group_name)
    {
        socialnetwork.Group found = null ;
       int hashNumber = (int)(hashFunc(group_name,hashTableSize));
       if(groupHashTable[hashNumber]==null) return found;
       else{
       
       for (socialnetwork.Group myGroup:groupHashTable[hashNumber])
       {
           if(myGroup.getName()== group_name)
           {
               found = myGroup;
           }
       }
       }
       return found;    
    }  
    public static boolean addToGroupsHashTable (socialnetwork.Group group_name)
    {
       boolean added =false;
        if(searchGroupsHashTable(group_name.getName())!=null)
            added = false;
        else
        {
            int hashNumber = (int)(hashFunc(group_name.getName(),hashTableSize));
            if(groupHashTable[hashNumber]==null)
            {
                groupHashTable[hashNumber] = new LinkedList();
                groupHashTable[hashNumber].add(group_name);
            }
            else
                groupHashTable[hashNumber].add(group_name);
            added =true;
        }
        return added;
    }
//***********************************************************Salma's part****************************************************************

    private static Scanner x;
    public static JSONArray USERS = new JSONArray();

    public static JSONArray USERPOST = new JSONArray();

    public static void SaveUserInFile(user u) {
        JSONObject UserObject = new JSONObject();
        JSONObject UserObj = new JSONObject();
        UserObject.put("City", u.getInfo().getCity());
        UserObject.put("School", u.getInfo().getSchool());
        UserObject.put("Username", u.getUsername());
        UserObject.put("College", u.getInfo().getCollege());
        UserObject.put("Birth_Place", u.getInfo().getBirth_place());
        UserObject.put("Work", u.getInfo().getWork());
        UserObject.put("ID", u.getID());
        UserObject.put("Photo Path", u.getPath());
        JSONObject datajson = new JSONObject();
        datajson.put("Day", u.getInfo().getDateOfBirth().getDay());
        datajson.put("Month", u.getInfo().getDateOfBirth().getMonth());
        datajson.put("Year", u.getInfo().getDateOfBirth().getYear());
        UserObject.put("Date of Birth", datajson);
        JSONObject tbp = (JSONObject) UserObject.get("Date Of Birth");

        UserObject.put("Password", u.getPassword());
        UserObject.put("Gender", u.getInfo().getGender().toString());
        UserObject.put("Status", u.getInfo().getStatus().toString());
        UserObject.put("Number Of Languages", u.getInfo().getNumberOfLanguages());
        UserObject.put("Bio", u.getInfo().getBio());

        JSONArray friendsarrobj = new JSONArray();
        for (int j = 0; j < u.getNumberOfFriends(); j++) {
            friendsarrobj.add(u.getFriends().get(j));
        }
        UserObject.put("Friends", friendsarrobj);
        //post

        JSONArray postsarrobj = new JSONArray();
        JSONArray POSTS = new JSONArray();
        for (int j = 0; j < u.getPosts().size(); j++) {

            JSONObject Posts = new JSONObject();
            JSONObject PostObject = new JSONObject();
            Post p = u.getPosts().get(j);
            PostObject.put("Post Owner", p.getPostOwner());
            PostObject.put("Post Content", p.getPostContent());
            JSONArray JsonComments = new JSONArray();
            for (int i = 0; i < p.getPostComments().size(); i++) {
                JSONObject Commentobj = new JSONObject();
                Commentobj.put("Comment Content", p.getPostComment(i).getComment());
                Commentobj.put("Comment Owner", p.getPostComment(i).getCommentOwner());

                JsonComments.add(Commentobj);
            }
            PostObject.put("Comments", JsonComments);
            JSONArray JsonLikes = new JSONArray();
            JSONArray JsonDislikes = new JSONArray();
            for (int i = 0; i < p.getNumbOfLikes(); i++) {
                JsonLikes.add(p.getLike(i));
            }
            for (int i = 0; i < p.getNumbOfDislikes(); i++) {
                JsonDislikes.add(p.getDislike(i));
            }
            PostObject.put("Likes", JsonLikes);
            PostObject.put("Dislikes", JsonDislikes);

            Posts.put("Post", PostObject);
            POSTS.add(Posts);

        }
        UserObject.put("Posts", POSTS);


        UserObj.put("Users", UserObj);
        UserObj.put("Users", UserObject);
        USERS.add(UserObj);
    }

    public static user ReadUserInfoFromFile(String usernamestr)  //returns 1 user and checks if it exists or no
    {
        JSONParser parser = new JSONParser();
        user parsed = new user();
        try {

            Object obj = parser.parse(new FileReader("C:/Users/Mark/IdeaProjects/SN/src/socialnetwork/UsersFileKolena.json"));
            // JSONObject jsonObject =  (JSONObject) obj;
            JSONArray arrayofusers = (JSONArray) obj;
            int index = 0;
            boolean found = false;
            for (int i = 0; i < arrayofusers.size(); i++) {
                JSONObject Userobj1 = (JSONObject) arrayofusers.get(i);
                JSONObject Userobjgowa1 = (JSONObject) Userobj1.get("Users");
                if (Userobjgowa1.get("Username").toString().equals(usernamestr)) {
                    index = i;
                    found = true;
                    break;
                }
            }
            if (found == false) {
                System.out.println("User not Found");
                return null;

            }

            JSONObject Userobj = (JSONObject) arrayofusers.get(index);
            JSONObject Userobjgowa = (JSONObject) Userobj.get("Users");
            parsed.setUsername(Userobjgowa.get("Username").toString());
            parsed.setPassword(Userobjgowa.get("Password").toString());
            parsed.setID(Integer.parseInt(Userobjgowa.get("ID").toString()));
            parsed.setpp(Userobjgowa.get("Photo Path").toString());
            // adding vector of friends as string and adding
            JSONArray UserArrgowa = (JSONArray) Userobjgowa.get("Posts");
            Vector<Post> Posts = new Vector<Post>(10);
            for (int j = 0; j < UserArrgowa.size(); j++) {
                JSONObject Postobjgowa = (JSONObject) UserArrgowa.get(j);
                JSONObject Postobjinfo = (JSONObject) Postobjgowa.get("Post");
                Post pp = new Post();
                pp.setPostOwner(Postobjinfo.get("Post Owner").toString());
                pp.setPostContent(Postobjinfo.get("Post Content").toString());
                Vector<String> Likes = new Vector<String>(10);
                Vector<String> Dislikes = new Vector<String>(10);
                Vector<Comment> MyComments = new Vector<Comment>(10);
                JSONArray CommentsObj = (JSONArray) Postobjinfo.get("Comments");
                JSONArray LikesObj = (JSONArray) Postobjinfo.get("Likes");
                JSONArray DislikesObj = (JSONArray) Postobjinfo.get("Dislikes");
                for (int k = 0; k < CommentsObj.size(); k++) {
                    Comment c = new Comment();
                    JSONObject Comm = (JSONObject) CommentsObj.get(k);
                    c.setCommentOwner(Comm.get("Comment Owner").toString());
                    c.setComment(Comm.get("Comment Content").toString());
                    MyComments.add(c);
                }
                for (int k = 0; k < DislikesObj.size(); k++) {
                    Dislikes.add(DislikesObj.get(k).toString());
                }
                for (int k = 0; k < LikesObj.size(); k++) {
                    Likes.add(LikesObj.get(k).toString());
                }
                pp.setPostComments(MyComments);
                pp.setDislike(Dislikes);
                pp.setLikes(Likes);

                Posts.add(pp);
            }

            parsed.setPosts(Posts);

            //friends
            JSONArray FriendsObj = (JSONArray) Userobjgowa.get("Friends");
            Vector<String> friendslist = new Vector<String>(10);
            for (int k = 0; k < FriendsObj.size(); k++) {
                friendslist.add(FriendsObj.get(k).toString());
            }
            parsed.setFriends(friendslist);


            //
            Informations info = new Informations();
            info.setBio(Userobjgowa.get("Bio").toString());
            info.setBirth_place(Userobjgowa.get("Birth_Place").toString());
            info.setCity(Userobjgowa.get("City").toString());
            info.setCollege(Userobjgowa.get("College").toString());
            //date
            Date_Of_Birth date = new Date_Of_Birth();
            JSONObject Jsondate = (JSONObject) (Userobjgowa.get("Date of Birth"));

            date.setDay(Integer.parseInt(Jsondate.get("Day").toString()));
            date.setMonth(Integer.parseInt(Jsondate.get("Month").toString()));
            date.setYear(Integer.parseInt(Jsondate.get("Year").toString()));
            info.setDateOfBirth(date);
            //khalsana el date
            info.setGender(Informations.Gender.valueOf(Userobjgowa.get("Gender").toString()));
            info.setStatus(Informations.MaritalStatus.valueOf(Userobjgowa.get("Status").toString()));
            info.setSchool(Userobjgowa.get("School").toString());
            info.setWork(Userobjgowa.get("Work").toString());
            info.setNumberOfLanguages(Integer.parseInt(Userobjgowa.get("Number Of Languages").toString()));


            parsed.setInfo(info);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsed;
    }

    public static void ReadUsersFromFile() {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("C:/Users/Mark/IdeaProjects/SN/src/socialnetwork/UsersFileKolena.json"));
            // JSONObject jsonObject =  (JSONObject) obj;
            JSONArray arrayofusers = (JSONArray) obj;

            int index = 0;
            for (int i = 0; i < arrayofusers.size(); i++) {
                user parsed = new user();
                JSONObject Userobj1 = (JSONObject) arrayofusers.get(i);
                JSONObject Userobjgowa1 = (JSONObject) Userobj1.get("Users");
                JSONObject Userobj = (JSONObject) arrayofusers.get(i);
                JSONObject Userobjgowa = (JSONObject) Userobj.get("Users");
                parsed.setUsername(Userobjgowa.get("Username").toString());
                parsed.setPassword(Userobjgowa.get("Password").toString());
                parsed.setID(Integer.parseInt(Userobjgowa.get("ID").toString()));
                parsed.setpp(Userobjgowa.get("Photo Path").toString());
                // adding vector of friends as string and adding
                JSONArray UserArrgowa = (JSONArray) Userobjgowa.get("Posts");
                Vector<Post> Posts = new Vector<Post>(10);
                for (int j = 0; j < UserArrgowa.size(); j++) {
                    JSONObject Postobjgowa = (JSONObject) UserArrgowa.get(j);
                    JSONObject Postobjinfo = (JSONObject) Postobjgowa.get("Post");
                    Post pp = new Post();
                    pp.setPostOwner(Postobjinfo.get("Post Owner").toString());
                    pp.setPostContent(Postobjinfo.get("Post Content").toString());
                    Vector<String> Likes = new Vector<String>(10);
                    Vector<String> Dislikes = new Vector<String>(10);
                    Vector<Comment> MyComments = new Vector<Comment>(10);
                    JSONArray CommentsObj = (JSONArray) Postobjinfo.get("Comments");
                    JSONArray LikesObj = (JSONArray) Postobjinfo.get("Likes");
                    JSONArray DislikesObj = (JSONArray) Postobjinfo.get("Dislikes");
                    for (int k = 0; k < CommentsObj.size(); k++) {
                        Comment c = new Comment();
                        JSONObject Comm = (JSONObject) CommentsObj.get(k);
                        c.setCommentOwner(Comm.get("Comment Owner").toString());
                        c.setComment(Comm.get("Comment Content").toString());
                        MyComments.add(c);
                    }
                    for (int k = 0; k < DislikesObj.size(); k++) {
                        Dislikes.add(DislikesObj.get(k).toString());
                    }
                    for (int k = 0; k < LikesObj.size(); k++) {
                        Likes.add(LikesObj.get(k).toString());
                    }
                    pp.setPostComments(MyComments);
                    pp.setDislike(Dislikes);
                    pp.setLikes(Likes);

                    Posts.add(pp);
                }

                parsed.setPosts(Posts);

                //friends
                JSONArray FriendsObj = (JSONArray) Userobjgowa.get("Friends");
                Vector<String> friendslist = new Vector<String>(10);
                for (int k = 0; k < FriendsObj.size(); k++) {
                    friendslist.add(FriendsObj.get(k).toString());
                }
                parsed.setFriends(friendslist);


                //
                Informations info = new Informations();
                info.setBio(Userobjgowa.get("Bio").toString());
                info.setBirth_place(Userobjgowa.get("Birth_Place").toString());
                info.setCity(Userobjgowa.get("City").toString());
                info.setCollege(Userobjgowa.get("College").toString());
                //date
                Date_Of_Birth date = new Date_Of_Birth();
                JSONObject Jsondate = (JSONObject) (Userobjgowa.get("Date of Birth"));

                date.setDay(Integer.parseInt(Jsondate.get("Day").toString()));
                date.setMonth(Integer.parseInt(Jsondate.get("Month").toString()));
                date.setYear(Integer.parseInt(Jsondate.get("Year").toString()));
                info.setDateOfBirth(date);
                //khalsana el date
                info.setGender(Informations.Gender.valueOf(Userobjgowa.get("Gender").toString()));
                info.setStatus(Informations.MaritalStatus.valueOf(Userobjgowa.get("Status").toString()));
                info.setSchool(Userobjgowa.get("School").toString());
                info.setWork(Userobjgowa.get("Work").toString());
                info.setNumberOfLanguages(Integer.parseInt(Userobjgowa.get("Number Of Languages").toString()));


                parsed.setInfo(info);
               // System.out.println(parsed);
                UsersInSystem.add(parsed);
                addToHashTable(parsed);


            }
             adjencyMatrix = new HashMap<String, Map<String, Integer>>();

            for (user i : UsersInSystem )
            {
                Map<String,Integer> in = new HashMap<String,Integer>();
                for (user j: UsersInSystem)
                {

                    in.put(j.getUsername(),0);

                }
                adjencyMatrix.put(i.getUsername(), in );
            }

            for (user i : UsersInSystem ) {
                Map<String,Integer> in = new HashMap<String,Integer>();
                for (String j : i.getFriends())
                {
                    Map x = adjencyMatrix.get(i.getUsername());
                    x.put(j,1);
                    adjencyMatrix.put(i.getUsername(),x);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
     
     
    
    public static void main(String[] args) throws IOException {
        ReadUsersFromFile();
         launch(args);
       /* for(int i=0; i<hashTableSize;i++)
        {
            LinkedList<user> l = usersHashTable[i];
            if(l!=null)
            {
                for (user u : l)
                {
                    SaveUserInFile(u);
                }

            }
        }

        File file=new File("UsersFileKolena.json");
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        try {
            fileWriter.write(USERS.toJSONString());
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
   
  
    
      
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        //primaryStage.setTitle("Social Network");
        primaryStage_boundries_set_to_bounds_of_mainScreen(primaryStage);//set primaryStage boundaries to visible bounds of the main screen
       // window.setScene(homePage(getCurrentUser()));
       LoginPage login = new LoginPage();
       window.setScene(login.loginPage(window));
        window.show();    
    }
    public static void primaryStage_boundries_set_to_bounds_of_mainScreen(Stage primaryStage)
    {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
    }
  
}

