/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialnetwork;

/**
 *
 * @author Adel Mahmoud
 */
import java.util.Vector;

class Date_Of_Birth
{
    private int day;
    private int month;
    private int year;



    public Date_Of_Birth() {
        this.day =0 ;this.month =0 ; this.year =0 ;
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

    public void setYear(int year) {
        this.year = year;

    }
}
class Informations
{
    public enum  Gender { male , female };
    public enum  MaritalStatus { single , inarelationship ,engaged , married  };
    private Date_Of_Birth DateOfBirth;
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


    public Informations () { }

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
    
    public static final int hashTableSize =1000;
    public static LinkedList<String>[] usersHashTable = new LinkedList[hashTableSize];
       
    

    public user() {
        username=" ";
        Password= " ";
        ID=0;
        try {
            ProfilePicture = new Image(new FileInputStream("E:/Mark/mpp.jpg")); // directory for default profile picture
        } catch (FileNotFoundException ex) {
            // handle exception...
        }
    }

    public user(String username, String password , int id ) {
        this.username = username;
        Password = password;
        ID = id ;
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
    
    public static boolean searchUsersHashTable(String user_name)
    {
        boolean found = false;
       int hashNumber = (int)(hashFunc(user_name,hashTableSize));
       if(usersHashTable[hashNumber]==null) found = false;
       else
        found=usersHashTable[hashNumber].contains(user_name);
       return found;    
    }  
    public static boolean addToHashTable (String user_name)
    {
       boolean added =false;
        if(searchUsersHashTable(user_name))
            added = false;
        else
        {
            int hashNumber = (int)(hashFunc(user_name,hashTableSize));
            if(usersHashTable[hashNumber]==null)
            {
                usersHashTable[hashNumber] = new LinkedList();
                usersHashTable[hashNumber].add(user_name);
            }
            else
                usersHashTable[hashNumber].add(user_name);
            added =true;
        }
        return added;
    }
}
class Comment
{
    private String comment;
    private String commentOwner;
    
   public Comment()
   {
       
   }
   public Comment(String comment,String commentOwner)
   {
       this.comment = comment;
       this.commentOwner = commentOwner;
   }
}

