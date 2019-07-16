/*
 * Copyright (C) 2018 cdrscnet89
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Modules;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author cdrscnet89
 */
public class OutputSuara {
    public void greeting() {
        try {
            File f = new File("Sounds/login.wav");
            System.out.println("Play: Greeting");
            
            InputStream in = new FileInputStream(f);
            AudioStream as = new AudioStream(in);
            
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void notification() {
        try {
            File f = new File("Sounds/notification.wav");
            System.out.println("Play: Notification");
            
            InputStream in = new FileInputStream(f);
            AudioStream as = new AudioStream(in);
            
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void logout() {
        try {
            File f = new File("Sounds/logout.wav");
            System.out.println("Play: Logout");
            
            InputStream in = new FileInputStream(f);
            AudioStream as = new AudioStream(in);
            
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void connected() {
        try {
            File f = new File("Sounds/connected.wav");
            System.out.println("Play: Connected");
            
            InputStream in = new FileInputStream(f);
            AudioStream as = new AudioStream(in);
            
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void disconnect() {
        try {
            File f = new File("Sounds/disconnect.wav");
            System.out.println("Play: Disconnect");
            
            InputStream in = new FileInputStream(f);
            AudioStream as = new AudioStream(in);
            
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void input_ok() {
        try {
            File f = new File("Sounds/input-ok.wav");
            System.out.println("Play: Input_OK");
            
            InputStream in = new FileInputStream(f);
            AudioStream as = new AudioStream(in);
            
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void error() {
        try {
            File f = new File("Sounds/error.wav");
            System.out.println("Play: Error");
            
            InputStream in = new FileInputStream(f);
            AudioStream as = new AudioStream(in);
            
            AudioPlayer.player.start(as);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OutputSuara.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
