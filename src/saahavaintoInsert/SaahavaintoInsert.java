/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//ohjelma generoi halutun määrän säähavaint-olioita
package saahavaintoInsert;

import com.mysql.jdbc.Connection;
import static java.lang.Math.random;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import saahavaintoInsert.domain.Saahavainto;
import java.util.List;
import java.util.Random;
import saahavaintoInsert.domain.Havainto;
import saahavaintoInsert.domain.Place;
import saahavaintoInsert.domain.User;
import saahavaintoInsert.domain.Utilities;
import saahavaintoInsert.domain.SqlInterace;

//testataan vielä

/**
 *
 * @author Laura
 */
public class SaahavaintoInsert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                  

        //yhdistetään sql:ään saahavainto ja users tableihin
        
        com.mysql.jdbc.Connection connToSaahavainto = (com.mysql.jdbc.Connection) SqlInterace.connectToSqlTable("saahavainto");
        com.mysql.jdbc.Connection connToUsers = (com.mysql.jdbc.Connection) SqlInterace.connectToSqlTable("users");
        
         
        for(int i = 0; i < 30; i++){
            
            int ID = i;
            Calendar currentdateTime = new GregorianCalendar();
            currentdateTime = Utilities.getRandomDateTime();
            Place place = Utilities.getRandomPlace();
            
            Random rand = new Random();
            User user = SqlInterace.getRandomUser(connToUsers);  //haetaan random user users sql tablesta
            double temperature = Utilities.getRandomTemperature(currentdateTime);
            int windSpeed = Utilities.getRandomWindspeed();
            Havainto havainto = Utilities.getRandomHavainto(currentdateTime, temperature, windSpeed);
            
            Saahavainto currentHavainto = new Saahavainto(ID, currentdateTime, place, user, temperature, windSpeed, havainto);
            
            //Säähavaintojen lisäys sql:ään
            
            SqlInterace.insertRandomDataToSaahavainto(currentHavainto, connToSaahavainto);
            
            
        }
        
        
        
      
        }
    }
    
    

    

