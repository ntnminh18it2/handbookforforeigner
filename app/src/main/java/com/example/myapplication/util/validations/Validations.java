package com.example.myapplication.util.validations;

public class Validations{
    public static boolean isPassAndConfirmPass(String pass,String confirmPass){
        if(isPass(pass) && pass.equals(confirmPass)){
            return true;
        }
        return false;
    }

    public static  boolean isEmail(String email){
        if(email.endsWith("@gmail.com")){
            return  true;
        }
        return false;
    }

    public static  boolean isName(String name){
        if(name != null && name.length()>6){
            return true;
        }
        return false;
    }

    public static  boolean isPass(String pass){
        if(pass != null && pass.length()>6){
            return true;
        }
        return false;
    }
}
