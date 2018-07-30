package ru.gotoqa.JsonGenerateClass;

/**
 * @author Muflikhunov Roman
 */

public class User{
    private Name name;
    private String gender;
    private boolean verified;
    private byte [] userImage;

    public Name getName(){
        return name;
    }
    public void setName(Name input){
        this.name = input;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String input){
        this.gender = input;
    }
    public boolean isVerified(){
        return verified;
    }
    public void setVerified(boolean input){
        this.verified = input;
    }
    public byte [] getUserImage(){
        return userImage;
    }
    public void setUserImage(byte [] input){
        this.userImage = input;
    }
}