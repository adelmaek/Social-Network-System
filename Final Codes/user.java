/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import static java.lang.Math.toIntExact;

class Date_Of_Birth
{
    private int day;
    private int month;
    private int year;



    public Date_Of_Birth() {
        this.day =1 ;this.month =1 ; this.year =1990 ;
    }
    public Date_Of_Birth(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;

    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Date_Of_Birth{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public String print(){
        return day+"/"+month+"/"+year;
    }

    public void setYear(int year) {
        this.year = year;

    }
}
class Informations
{
    public enum  Gender { male , female };
    public enum  MaritalStatus { non ,single , inarelationship ,engaged , married  };
    private Date_Of_Birth DateOfBirth= new Date_Of_Birth();
    private String school;
    private String college;
    private String work ;
    private String birth_place;
    private String city;
    private String Bio;
    private Vector<String> languages = new Vector <String> (15);
    private int NumberOfLanguages = 0 ;
    private Gender gender;
    private MaritalStatus status;


    public Informations () {
        gender=Gender.male;
        status=MaritalStatus.non;
        school=" "; college= " "; work= " "; birth_place=" "; city=" "; Bio=" ";

    }

    public Date_Of_Birth getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date_Of_Birth dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public int getNumberOfLanguages() {
        return NumberOfLanguages;
    }

    public void setNumberOfLanguages(int numberOfLanguages) {
        NumberOfLanguages = numberOfLanguages;
    }
    public void addLanguage (String lang)
    {
        NumberOfLanguages++;
        languages.add(lang);
    }

    public Vector<String> getLanguages() {
        return languages;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public MaritalStatus getStatus() {
        return status;
    }

    public void setStatus(MaritalStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Informations{" +
                "DateOfBirth=" + DateOfBirth.toString() +
                ", school='" + school + '\'' +
                ", college='" + college + '\'' +
                ", work='" + work + '\'' +
                ", birth_place='" + birth_place + '\'' +
                ", city='" + city + '\'' +
                ", Bio='" + Bio + '\'' +
                ", NumberOfLanguages=" + NumberOfLanguages +
                ", gender=" + gender +
                ", status=" + status +
                '}';
    }
}

public class user {
    private String username;
    private String Password;
    private int NumberOfFriends = 0 ;
    private Vector<String> friends_names = new Vector<>();
    private Informations info= new Informations();
    private Image ProfilePicture;
    private String path;
    private Vector<String> groups_names= new Vector<>();
    private Vector<Post> posts = new Vector<>();
    private int ID;
    public user() {
        username=" ";
        Password= " ";
        try {
            ProfilePicture = new Image(new FileInputStream("E:/Mark/mpp.jpg")); // directory for default profile picture
             path ="E:/emptying/Emptying 20/DCIM/101MEDIA/IMAG4412.jpg";
        } catch (FileNotFoundException ex) {
            // handle exception...
        }
    }


    public user(String username, String password) {
        try {
            ProfilePicture = new Image(new FileInputStream("E:/Mark/mpp.jpg")); // directory for default profile picture
        } catch (FileNotFoundException ex) {
            // handle exception...
        }
        this.username = username;
        Password = password;
    }
    
    public Vector<String> getFriends() {
        return friends_names;
    }

    public void setFriends(Vector<String> friends) {
        this.friends_names = friends;
    }

    public void add_post (Post p )
    {
        posts.add(p);
    }

    public void setPosts(Vector<Post> posts) {
        this.posts = posts;
    }

    public Vector<Post> getPosts() {
        return posts;
    }


    public void setFriends_names(Vector<String> friends_names) {
        this.friends_names = friends_names;
    }

    public Vector<String> getFriends_names() {
        return friends_names;
    }

    public void setInfo(Informations info) {
        this.info = info;
    }


    public void add_group(String g)
    {
        groups_names.add(g);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumberOfFriends() {
        return NumberOfFriends;
    }

    public void setNumberOfFriends(int numberOfFriends) {
        NumberOfFriends = numberOfFriends;
    }

    public Image getProfilePicture() {return ProfilePicture;}

    public String getPath() {return path;}
    public void setpp(String pathh)
    {

        try {
            path=pathh;
            Image im = new Image(new FileInputStream("pathh"));
            setProfilePicture(im);
        }
        catch (FileNotFoundException ex) {
            //
        }
    }

    public  void setProfilePicture(Image profilePicture) {ProfilePicture = profilePicture;}

    public void addFriend (String friend )
    {

        friends_names.add(friend);
        this.NumberOfFriends++ ;

    }

    public void setGroups_names(Vector<String> groups_names) {
        this.groups_names = groups_names;
    }

    public Vector<String> getGroups_names() {
        return groups_names;
    }

    public Informations getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "user{" +
                "username='" + username + '\'' +
                ", Password='" + Password + '\'' +
                ", NumberOfFriends=" + NumberOfFriends +
                ", info=" + info.toString()+
                '}';
    }
    
    
}

