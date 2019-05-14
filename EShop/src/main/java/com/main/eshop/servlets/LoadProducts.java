/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.eshop.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gianni
 */
public class LoadProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String prodotto = "<div class=\"col-md-3 col-sm-6\" style=\"padding-right: 0px; box-sizing: content-box;\">\n" +
"                <figure class=\"card card-product\">\n" +
"                    <div class=\"img-wrap\"> <img src=\"images/1.jpg\"></div>\n" +
"                    <figcaption class=\"info-wrap\">\n" +
"                            <a href=\"#\" class=\"title\">Dio euge</a>\n" +
"                            <div class=\"price-wrap\">\n" +
"                                    <span class=\"price-new\">$99999999999999</span>\n" +
"                                    <del class=\"price-old\">$1980</del>"
                                    + "<button name=\"bottone\" style=\"float:left;\">Aggiungi al carrello</button>\n" +
"                            </div>\n" +
"                    </figcaption>\n" +
"                </figure>\n" +
"            </div>";
        
        System.out.println("Ciao come va *************************************************************");
        req.setAttribute("prodotti", prodotto);
    }

    
    
    

}
