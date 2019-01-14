package com.hello.assignment.auth.ui.view.model;

public interface IUser {
    String getEmail();
    int checkUserValidity(String email);
}
