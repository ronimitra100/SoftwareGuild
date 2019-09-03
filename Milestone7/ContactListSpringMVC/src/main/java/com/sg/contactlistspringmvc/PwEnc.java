package com.sg.contactlistspringmvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PwEnc {
    public static void main(String args[]){
        String clearTxtPw = "password";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPw = encoder.encode(clearTxtPw);
        System.out.println(hashedPw);
    }
}
