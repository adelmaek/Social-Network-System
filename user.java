package socialnetwork;

import java.io.FileInputStream;
import java.util.Vector;
import javafx.scene.image.Image;
import java.io.FileNotFoundException;

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

class user {
    private String username;
    private String Password;
    private int ID;
    private int NumberOfFriends = 0 ;
    private Vector <user> friends = new Vector <user> (50)  ;
    private Informations info= new Informations();
    private Image ProfilePicture;
    private Vector<Group> groups= new Vector<>();
    private Vector<Post> posts = new Vector<>();

    public user() {
        username=" ";
        Password= " ";
        ID=0;
        try {
            ProfilePicture = new Image(new FileInputStream("E:/Mark/samaprofilepic.jpg")); // directory for default profile picture
        } catch (FileNotFoundException ex) {
            // handle exception...
        }
    }

    public user(String username, String password , int id ) {
        this.username = username;
        Password = password;
        ID = id ;
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

    public void setFriends(Vector<user> friends) {
        this.friends = friends;
    }

    public void setGroups(Vector<Group> groups) {
        this.groups = groups;
    }

    public Vector<Group> getGroups() {
        return groups;
    }

    public Vector<user> getFriends() {
        return friends;
    }

    public void add_group(Group g)
    {
        groups.add(g);
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

    public  void setProfilePicture(Image profilePicture) {ProfilePicture = profilePicture;}

    public void addFriend (user friend )
    {


        friends.add(friend);
        this.NumberOfFriends++ ;

    }

    public Informations getInfo() {
        return info;
    }

    public void setInfo(Informations info) {
        this.info = info;
    }
    public void set_bd(Date_Of_Birth x){info.setDateOfBirth(x);}
    public void set_status(Informations.MaritalStatus x){info.setStatus(x);}
    public void set_gender(Informations.Gender x){info.setGender(x);}
    public void set_city(String x) {info.setCity(x);}
    public void set_bp (String x){info.setBirth_place(x);}
    public void set_work(String x){info.setWork(x);}
    public void set_college (String x){info.setCollege(x);}
    public void set_school (String x){info.setSchool(x);}
    public void add_lang (String x){info.addLanguage(x);}


    @Override
    public String toString() {
        return "user{" +
                "username='" + username + '\'' +
                ", Password='" + Password + '\'' +
                ", ID=" + ID +
                ", NumberOfFriends=" + NumberOfFriends +
                ", info=" + info.toString()+
                '}';
    }
}



