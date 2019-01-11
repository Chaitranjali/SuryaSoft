package com.hello.suryasoftassign.auth.ui.view.model;

public class UserModel implements IUser{
    private String email;


    public UserModel(String emailId) {
        this.email = emailId;
    }

    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public int checkUserValidity(String emailId){
        if (emailId==null||!emailId.equals(getEmail())){
            return -1;
        }
        return 0;
    }
}
