package com.example.p1sqliteindianrestaurant;

import java.util.regex.Pattern;

public class FunctionCollection {
    static boolean checkNumber(String num)
    {
        if(num.length()!=10)
            return false;
        for(char i:num.toCharArray())
        {
            if(i<'0'|| i>'9')
                return false;
        }
        return true;
    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +  //part before @
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
