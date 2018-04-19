package com.company;
import java.util.List;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        //User Class Test Case

        user Sama = new user () ;
        Sama.setUsername("sama");
        Sama.setPassword("***");
        user friend = new user ("Perry","ll",1);
        Sama.addFriend(friend);
        System.out.println(Sama.getNumberOfFriends());
        Informations Her = new Informations() ;
        Her.setGender(Informations.Gender.female);
        Her.setStatus(Informations.MaritalStatus.single);
        Her.addLanguage("french");
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

    }
}


