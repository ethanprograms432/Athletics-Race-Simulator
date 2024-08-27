import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Participant {

   public String name;
   private double time;
   private int kickScore;
   private int experienceScore;
   public int xPos = 100;
   public int yPos = 10000;
   public int heat;
   private int lane;
   private boolean finished = false;
   private double timeElapsedSinceMove = 0;
   private double timeOfMove = 0;
   public int numRotations = 0;
   public double distanceTravelled = 0;
   public double extraStartDistance;
   
   public static int totalFinished = 0;
   public static double totalCreated = 0;
   public static int currHeat = 1;

   public static double[][] rotationMatrix = {{Math.cos(1),-Math.sin(1)},{Math.sin(1),Math.cos(1)}};
   public Participant(String name,String time,int kickScore,int experienceScore) {
   
      this.name = name;
      convertToDecimal(time);
      this.kickScore = kickScore;
      this.experienceScore = experienceScore;
      
      totalCreated++;
      
      if(AthleticsRaceSimulator.raceDistance == 100) {
      
         assignSprintHeat();
         
      } else {
      
         this.xPos = 500;
         this.yPos = 375;
      
      }

   
   }
   
   public void assignSprintHeat() {
   
      this.heat = (int)((totalCreated/8) + 0.97);
      
      
      this.lane = (int)totalCreated - ((this.heat - 1)*8);
      
      this.yPos = (120 + (37*(this.lane-2)));
      
   
   }
   
   public double convertToDecimal(String time) {
   
      double overallTime = 0;
      
      if(time.contains(":")) {
      
         int minutes = Integer.valueOf(time.substring(0,time.indexOf(":")));
         
         overallTime += 60*minutes;
         
         double seconds = Double.valueOf(time.substring(time.indexOf(":") + 1));
         
         overallTime += seconds;
         this.time = overallTime;
         
      
      } else {
      
         double seconds = Double.valueOf(time.substring(time.indexOf(":") + 1));
         
         overallTime += seconds;
         this.time = overallTime;
      
      }
      return overallTime;
   
   }
   
   public int getNumRotations(int totalRotations) {
   
      double total = (double)totalRotations;
      
      double timeForFirstPortion = (this.time*0.3);
      double timeForLastPortion = timeForFirstPortion;

      double rotations = 0;
      
      double factor = (1.25 - (this.experienceScore*0.017));
      
      factor -= (0.01*this.kickScore);
      
      timeForFirstPortion /= factor;
      timeForLastPortion *= factor;
      
      double timeForMiddlePortion = (this.time - (timeForFirstPortion + timeForLastPortion));
      
      
      double rotationSegmentOne = (0.3*total);
      double rotationSegmentTwo = (0.4*total);
      
      extraStartDistance = (double)AthleticsRaceSimulator.raceDistance;
      
      while(extraStartDistance > 400) {
      
         extraStartDistance -= 400;
      
      }
      extraStartDistance = 400 - extraStartDistance;
      
      rotations += 360*(extraStartDistance/400);
      
      
      if(RaceTrack.time < timeForFirstPortion) {
      
         double portionDone = (RaceTrack.time/timeForFirstPortion);
         rotations += (rotationSegmentOne*portionDone);
      
      } else if(RaceTrack.time < (timeForFirstPortion + timeForMiddlePortion)) {
      
         double segmentTime = RaceTrack.time - timeForFirstPortion;
         double portionDone = (segmentTime/timeForMiddlePortion);
         rotations += ((rotationSegmentOne + (rotationSegmentTwo*portionDone)));
      
      } else {
         
         double segmentTime = RaceTrack.time - (timeForFirstPortion + timeForMiddlePortion);
         double portionDone = (segmentTime/timeForLastPortion);
         rotations += (rotationSegmentOne + rotationSegmentTwo + (rotationSegmentOne*portionDone));
      
      }
      
      
      int ROTATIONS = (int)rotations;
      
      return ROTATIONS;
   
   }
   
   public void race() {
   
      double portionDone = (RaceTrack.time/this.time);
      
      double laps = ((double)AthleticsRaceSimulator.raceDistance)/400;
      
      int rotations = (int)(laps*(360));
      
      numRotations = getNumRotations(rotations);
      
      double distancePortion = ((double)numRotations/(double)rotations);
      
      this.distanceTravelled = (distancePortion)*(AthleticsRaceSimulator.raceDistance) - extraStartDistance;
      
      if(this.distanceTravelled > (AthleticsRaceSimulator.raceDistance)) {
      
         this.distanceTravelled = (AthleticsRaceSimulator.raceDistance);
         RaceTrack.participants.remove(this);
         
      }
      
      double currX = 0;
      double currY = 0;
      
      if(this.finished == false) {
      
         while(numRotations > 0) {
         
            currX = 0;
            currY = 150;
            
            for (int i = 0; i < numRotations; i++) {
            
               double initialX = currX;
               currX = (currX*Math.cos(Math.PI/90)) + (currY*Math.sin(Math.PI/90));
               currY = (currY*Math.cos(Math.PI/90)) - (initialX*Math.sin(Math.PI/90));
               
               if(i == 89) {
               
                  i = numRotations;
                  
               }
   
            }
            
            
            currX += 650;
            currY += 230;
            
            for (int j = 90; j < numRotations; j++) {
            
               currX -= 3;
               
               if(j == 134) {
               
                  j = numRotations;
                  
               }
            
            }
            
            for (int k = 135; k < numRotations; k++) {
            
               currX -= 4;
               
               if(k == 179) {
               
                  k = numRotations;
                  
               }
            
            }
            
            if(numRotations > 180) {
            
               currX = 0;
               currY = -150;
              
            
            }
            
            for (int l = 180; l < numRotations; l++) {
            
               double initialX = currX;
               currX = (currX*Math.cos(Math.PI/90)) + (currY*Math.sin(Math.PI/90));
               currY = (currY*Math.cos(Math.PI/90)) - (initialX*Math.sin(Math.PI/90));
                  
               if(l == 269) {
                  
                  l = numRotations;
                     
               }
   
            }
            
            if(numRotations > 180) {
            
               currX += 335;
               currY += 225;
               
            }
            
            for (int m = 270; m < numRotations; m++) {
            
               currX += 3;
               
               if(m == 314) {
               
                  m = numRotations;
                  
               }
            
            }
            
            for (int n = 315; n < numRotations; n++) {
            
               currX += 4;
               
               if(n == 360) {
               
                  n = numRotations;
                  
               }
            
            }
            
            numRotations -= 360;
  
         }
         
         this.xPos = (int)currX;
         this.yPos = (int)currY;

         
      }
      
      if(portionDone > 1) {
      
         totalFinished++;
         this.finished = true;
      
      }
   
   }
   
   
   public void sprint() {
   
      double timeForTheFirstPortion = (this.time*0.3);
      double scaleFactor = (1.55 - (0.05*this.kickScore));
      timeForTheFirstPortion *= scaleFactor;
      double timeForTheSecondPortion = this.time - timeForTheFirstPortion;
      double xPosition = 0;
      

      if(RaceTrack.time < timeForTheFirstPortion) {
      
         double portionDone = (RaceTrack.time/timeForTheFirstPortion);
         xPosition = 100 + (portionDone * 240);
         this.distanceTravelled = (portionDone)*(100*0.3);
         
         if(this.distanceTravelled > 100) {
      
            this.distanceTravelled = 100;
            
         }
         
         
      
      } else {
      
         double timeInSecond = RaceTrack.time - timeForTheFirstPortion;
         double portionDone = (timeInSecond/timeForTheSecondPortion);
         this.distanceTravelled = ((portionDone)*70) + 30;
         
         xPosition = 340 + (portionDone * 560);
         
         if(this.distanceTravelled > 100) {
      
            this.distanceTravelled = 100;
            
         }
         
      }
      
      int actualX = (int)xPosition;
      
      
      if(this.finished == false) {
      
         this.xPos = actualX;
      
      }
      
      if(this.xPos > 900 && this.finished == false) {
      
         this.finished = true;
         totalFinished++;
                 
      }
   
   }

}