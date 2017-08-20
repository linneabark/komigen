package edu.chl.leep.model;

/**
 * Created by Eli on 2017-05-24.
 A model class which contains some methods used in LoginActivityController*/

public class LoginActivityModel {

    //TODO kan göras till service


    public boolean compareUserInfo(String userName, String passWord) {

        if(userName.equals("") || passWord.equals("")){
            return false;
        }
        if ((userName.equals(LeepModel.getUsername())) &&
                (passWord.equals(LeepModel.getPassword()))) {
            return true;
        }
        return false;
    }

    public boolean userWasLoggedIn(){
        if(LeepModel.getKeepLoginState() == 0){
            LeepModel.checkUser(LeepModel.getPreviousUser());
            return true;
        }
        return false;
    }

    public void rememberUser(Boolean checked){
        LeepModel.setKeepLoginState(checked);
        LeepModel.setPreviousUser();
    }


}
