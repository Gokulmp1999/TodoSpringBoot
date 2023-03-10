package com.myTodoApplication.springBoot.MyFirstTodoApplication.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String name,String password){
        boolean validUser=name.equalsIgnoreCase("admin");
        boolean validPassword=password.equalsIgnoreCase("123");
        return validUser && validPassword;
    }
}
