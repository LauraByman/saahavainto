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

//testi

/**
 *
 * @author Laura
 */
public class SaahavaintoInsert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

      
        //luo haluttu määrä säähavainto-olioita ja lisää arraylistiin.
        
        List<Saahavainto> saaHavainnot = new ArrayList<>(); //nämä korvataan SQL tableilla
        List<User> users = new ArrayList<>();   //
        users.add(new User(1, "pasiPouta", "Pasi", "Pouta"));
        users.add(new User(2, "sinikkaSumu", "Sinikka", "Sumu"));
        users.add(new User(3, "essiEsimerkki", "Essi", "Esimerkki"));
         
         
        for(int i = 0; i < 30; i++){
            
            int ID = i;
            Calendar currentdateTime = new GregorianCalendar();
            currentdateTime = Utilities.getRandomDateTime();
            Place place = Utilities.getRandomPlace();
            
            Random rand = new Random();
            User user = users.get(rand.nextInt(users.size()));
            double temperature = Utilities.getRandomTemperature(currentdateTime);
            int windSpeed = Utilities.getRandomWindspeed();
            Havainto havainto = Utilities.getRandomHavainto(currentdateTime, temperature, windSpeed);
            
            Saahavainto uusiHavainto = new Saahavainto(ID, currentdateTime, place, temperature, windSpeed, havainto, user);
            saaHavainnot.add(uusiHavainto);
            
            
        }
        
        System.out.println(saaHavainnot);
    }
    
    

    
}
