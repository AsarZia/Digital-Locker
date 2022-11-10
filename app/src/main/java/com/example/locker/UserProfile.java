package com.example.locker;

public class UserProfile {

    public String userBirth;
    public String userCity;
    public String userEmail;
    public String userMobile;
    public String userName;
    public String userPo;
    public String userWork;

    public UserProfile(){

    }

    public UserProfile(String userBirth, String userCity, String userEmail, String userMobile, String userName, String userPo, String userWork){
        this.userBirth = userBirth;
        this.userCity = userCity;
        this.userEmail = userEmail;
        this.userMobile = userMobile;
        this.userName = userName;
        this.userPo = userPo;
        this.userWork = userWork;
    }

    public String getUserBirth() {return userBirth; }
    public void setUserBirth(String userBirth) {this.userBirth = userBirth;}
    public String getUserCity() {return userCity; }
    public void setUserCity(String userCity) {this.userCity = userCity;}
    public String getUserEmail() {return userEmail; }
    public void setUserEmail(String userEmail) {this.userEmail = userEmail;}
    public String getUserMobile() {return userMobile; }
    public void setUserMobile(String userMobile) {this.userMobile = userMobile;}
    public String getUserName() {return userName; }
    public void setUserName(String userName) {this.userName = userName;}
    public String getUserPo() {return userPo; }
    public void setUserPo(String userPo) {this.userPo = userPo;}
    public String getUserWork() {return userWork; }
    public void setUserWork(String userWork) {this.userWork = userWork;}
}
