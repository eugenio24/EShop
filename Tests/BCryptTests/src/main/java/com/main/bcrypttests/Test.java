/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.bcrypttests;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Eugenio
 */
public class Test {
    public static void main(String[] args) {
        String psw = "Progettto123#";
        
        String salt = BCrypt.gensalt();
        
        String hash = BCrypt.hashpw(psw, salt);
        
        System.out.println("Psw: "+psw);
        System.out.println("Salt: "+salt);
        System.out.println("Hash: "+hash);
    }
}
