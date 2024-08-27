import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AthleticsRaceSimulator {

   public static ArrayList<String> participantNames = new ArrayList<String>();
   public static ArrayList<String> participantTimes = new ArrayList<String>();
   public static ArrayList<Integer> participantKickScores = new ArrayList<Integer>();
   public static ArrayList<Integer> participantExperience = new ArrayList<Integer>();
   
   public static int speedFactor = 0;
   public static int raceDistance = 0;
   
   static JFrame frame;
   
   public static void main(String[] args) {
   
      frame = new JFrame("Athletics Race Simulator");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      MainGUI mainGUI = new MainGUI();
      frame.getContentPane().add(mainGUI);
      
      frame.pack();
      frame.setVisible(true);
       
   }
   
   public static void setUpRace() {

      frame.setVisible(false);
      JFrame trackFrame = new JFrame("Race Simulation");
      trackFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      RaceTrack rT = new RaceTrack();
      
      trackFrame.getContentPane().add(rT);
      
      trackFrame.pack();
      trackFrame.setVisible(true);
      
   
   }
   

}