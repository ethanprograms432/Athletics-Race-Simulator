import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MainGUI extends JPanel {

   JLabel title,question;
   JButton start,instructions,confirm,back;
   JTextField answer;
   JPanel mainScreen,instructionScreen,infoEntryScreen,participantEntryScreen;
   
   JLabel[] participants = {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),
   new JLabel(),new JLabel(),new JLabel(),new JLabel()};
   static JTextField[] names = {new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5)};
   static JTextField[] times = {new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5)};
   static JTextField[] kickScores = {new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5)};
   static JTextField[] experienceScores = {new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5),new JTextField(5)};
   
   public static int stage = 1;
   public static boolean isOkk = true;
   public static int subStage = 1;
   public static int numParticipants = 0;
   public static int currParticipant = 1;
   public ButtonListener bL = new ButtonListener();

   public MainGUI() {
   
      this.setPreferredSize(new Dimension(800,800));
      this.setBackground(Color.white);
      
      title = new JLabel("Athletics Race Simulator");
      title.setHorizontalAlignment(SwingConstants.CENTER);
      title.setPreferredSize(new Dimension(400,150));
      question = new JLabel("");
      
      answer = new JTextField(5);
      
      start = new JButton("Start");
      start.setBackground(Color.green);
      start.setPreferredSize(new Dimension(400,225));
      start.setOpaque(true);
      start.addActionListener(bL);
      
      instructions = new JButton("Instructions");
      instructions.setBackground(Color.yellow);
      instructions.setPreferredSize(new Dimension(400,225));
      instructions.setOpaque(true);
      instructions.addActionListener(bL);
      
      back = new JButton("Back");
      back.setBackground(Color.red);
      back.setOpaque(true);
      back.addActionListener(bL);
      
      confirm = new JButton("Confirm");
      confirm.setBackground(Color.green);
      confirm.setOpaque(true);
      confirm.addActionListener(bL);
      
      mainScreen = new JPanel();
      mainScreen.setLayout(new GridLayout(3,1));
      
      instructionScreen = new JPanel();
      instructionScreen.setLayout(new GridLayout(20,1));
      setUpInstructions();
      instructionScreen.add(back);
      
      infoEntryScreen = new JPanel();
      infoEntryScreen.setLayout(new GridLayout(3,1));
      infoEntryScreen.add(question);
      infoEntryScreen.add(answer);
      infoEntryScreen.add(confirm);
      
      participantEntryScreen = new JPanel();
      participantEntryScreen.setLayout(new GridLayout(12,5));
      
      add(mainScreen);
      mainScreen.add(title);
      mainScreen.add(start);
      mainScreen.add(instructions);
      
      add(instructionScreen);
      add(infoEntryScreen);
      add(participantEntryScreen);
      
      infoEntryScreen.setVisible(false);
      participantEntryScreen.setVisible(false);
      
      manageStages();
      
   
   }
   
   public void setUpInstructions() {
   
      try {
      
         Scanner instructionScanner = new Scanner(new File("instructions"));
         
         while(instructionScanner.hasNextLine()) {
         
            String currLine = instructionScanner.nextLine();
            JLabel instruction = new JLabel(currLine);
            instructionScreen.add(instruction);
         
         }
         
      } catch(FileNotFoundException fnfe) {}
   
   }
   
   public void setUpParticipantScreen() {
   
      if(numParticipants >= 10) {
      
         for (int i = 0; i < 10; i++) {
         
            (participants[i]).setText("Participant " + currParticipant);
            participantEntryScreen.add(participants[i]);
            participantEntryScreen.add(names[i]);
            participantEntryScreen.add(times[i]);
            participantEntryScreen.add(kickScores[i]);
            
            if(AthleticsRaceSimulator.raceDistance != 100) {
            
               participantEntryScreen.add(experienceScores[i]);
               
            } else {
            
               participantEntryScreen.add(new JLabel(" "));
            
            }
            
            currParticipant++;
         
         }
         
         
      } else {
      
         for (int i = 0; i < numParticipants; i++) {
         
            (participants[i]).setText("Participant " + currParticipant);
            participantEntryScreen.add(participants[i]);
            participantEntryScreen.add(names[i]);
            participantEntryScreen.add(times[i]);
            participantEntryScreen.add(kickScores[i]);
            
            
            if(AthleticsRaceSimulator.raceDistance != 100) {
            
               participantEntryScreen.add(experienceScores[i]);
               
            } else {
            
               participantEntryScreen.add(new JLabel(" "));
            
            }
            
            currParticipant++;
         
         }
         
         for (int j = numParticipants; j < 10; j++) {
         
            participantEntryScreen.add(new JLabel(""));
            participantEntryScreen.add(new JLabel(""));
            participantEntryScreen.add(new JLabel(""));
            participantEntryScreen.add(new JLabel(""));
            participantEntryScreen.add(new JLabel(""));
         
         }
         
      
      }
   
   }
   
   public void manageStages() {
   
      switch(stage) {
      
         case(1):
         
            if(subStage == 1) {
            
               mainScreen.setVisible(true);
               instructionScreen.setVisible(false);
               
            } else if(subStage == 2) {
            
               instructionScreen.setVisible(true);
               mainScreen.setVisible(false);
            
            }
            break;
            
          case(2):
          
            if(subStage == 1) {
            
               mainScreen.setVisible(false);
               infoEntryScreen.setVisible(true);
               question.setText("Race Distance");
            
            } else if(subStage == 2) {
            
               question.setText("How many participants?");
               
            } else if(subStage == 3) {
            
               question.setText("How much would you like the race sped up?");
               
            }
            break;
            
          case(3):
          
            infoEntryScreen.setVisible(false);
            participantEntryScreen.setVisible(true);
            
            participantEntryScreen.removeAll();
            participantEntryScreen.repaint();
            participantEntryScreen.revalidate();
            
            if(AthleticsRaceSimulator.raceDistance != 100) {
            
               participantEntryScreen.add(new JLabel("INFO"));
               participantEntryScreen.add(new JLabel("Name"));
               participantEntryScreen.add(new JLabel("Time"));
               participantEntryScreen.add(new JLabel("Kick Rating"));
               participantEntryScreen.add(new JLabel("Experience Rating"));
               
            } else {
            
               participantEntryScreen.add(new JLabel("INFO"));
               participantEntryScreen.add(new JLabel("Name"));
               participantEntryScreen.add(new JLabel("Time"));
               participantEntryScreen.add(new JLabel("Start Score"));
               participantEntryScreen.add(new JLabel(""));
            
            }
            
            setUpParticipantScreen();
            
            participantEntryScreen.add(confirm);
            break;
          
            
      
      }
   
   }
   
   private class ButtonListener implements ActionListener {
   
      public void actionPerformed(ActionEvent ae) {
      
         JButton b = (JButton)ae.getSource();
         
         
         if(b == confirm) {
         
            switch(stage) {
            
               case(2):
               
                  if(subStage == 1) {

                     try {
                     
                        AthleticsRaceSimulator.raceDistance = Integer.valueOf(answer.getText());
                        
                        if(AthleticsRaceSimulator.raceDistance > 0) {
                        
                           subStage++;
                           
                        }
                           
                     } catch(InputMismatchException e) {
                     
                     
                     } catch(NoSuchElementException e) {
                     
                     
                     } catch(NumberFormatException n) {}
                     
                     answer.setText("");
                     //subStage++;
                     
                  }
                  
                  else if(subStage == 2) {
                  
                     try {
                     
                        numParticipants = Integer.valueOf(answer.getText());
                        
                        if(numParticipants > 0) {
                        
                           subStage++;
                           
                        }
                        
                     } catch(InputMismatchException e) {
                     
                     } catch(NoSuchElementException e) {
                     
                     } catch(NumberFormatException n) {}
                                         
                     answer.setText("");
                     
                     //subStage++;
                     
                  }
                  
                  else if(subStage == 3) {
                  
                     try {
                     
                        AthleticsRaceSimulator.speedFactor = Integer.valueOf(answer.getText());
                        
                        if(AthleticsRaceSimulator.speedFactor > 0) {
                        
                           stage = 3;
                           subStage = 1;
                           
                        }
                        
                     } catch(InputMismatchException e) {
                     
                     } catch(NoSuchElementException e) {
                     
                     } catch(NumberFormatException n) {}
                     
                     answer.setText("");
                     
                     
                  
                  }
                  break;
                  
                case(3):
                
                  
                     if(numParticipants <= 0) {
                     
                           isOkk = checkInputs();
                           
                           if(isOkk == true) {
                           
                              AthleticsRaceSimulator.setUpRace();
                              
                           }
                        
                        
                     } else {
                     
                        isOkk = checkInputs();
                        
                        if(isOkk == true) {
                        
                           if(numParticipants <= 10) {
                           
      
                                 for (int i = 0; i < numParticipants; i++) {
                                 
                                    AthleticsRaceSimulator.participantNames.add(names[i].getText());
                                    names[i].setText("");
                                    AthleticsRaceSimulator.participantTimes.add(times[i].getText());
                                    times[i].setText("");
                                    
                                    AthleticsRaceSimulator.participantKickScores.add(Integer.valueOf(kickScores[i].getText()));
                                    kickScores[i].setText("");
                                    
                                    if(AthleticsRaceSimulator.raceDistance != 100) {
                                    
                                       AthleticsRaceSimulator.participantExperience.add(Integer.valueOf(experienceScores[i].getText()));
                                       experienceScores[i].setText("");
                                       
                                    }
                                    
                                    
                                 
                                 }
                              
                           } else {
                           
                                 for (int i = 0; i < 10; i++) {
                                 
                                    AthleticsRaceSimulator.participantNames.add(names[i].getText());
                                    names[i].setText("");
                                    AthleticsRaceSimulator.participantTimes.add(times[i].getText());
                                    times[i].setText("");
                                    
                                    AthleticsRaceSimulator.participantKickScores.add(Integer.valueOf(kickScores[i].getText()));
                                    kickScores[i].setText("");
                                    if(AthleticsRaceSimulator.raceDistance != 100) {
                                    
                                       AthleticsRaceSimulator.participantExperience.add(Integer.valueOf(experienceScores[i].getText()));
                                       experienceScores[i].setText("");
                                       
                                    }
                                    
                                 
                                 }
                                 
                           
                           }
                           numParticipants -= 10;
                           
                           if(numParticipants <= 0) {
                        
                              AthleticsRaceSimulator.setUpRace();
                           
                           }  
                           
                        }
              
                     
                     }

            
            }
         
         } else if(b == instructions) {
         
            subStage = 2;
            
         
         } else if(b == start) {
         
            stage = 2;
            subStage = 1;
            
         
         } else if(b == back) {
         
            if(stage == 1) {
            
               subStage = 1;
               
            
            }
         
         }
         
         if(isOkk == true) {
         
            manageStages();
            
         }
      
      }
   
   }
   
   public boolean checkTime(String time) {
   
      boolean valid = true;
      int colonCounter = 0;
      int pointCounter = 0;
      int startIndex = 0;
      int endIndex = 0;
      
      for (int i = 0; i < time.length(); i++) {
      
         char c = time.charAt(i);
         
         if(Character.isLetter(c)) {
         
            valid = false;
            
         }
         
         if(c == ':') {
         
            colonCounter++;
            startIndex = i;
         
         }
         
         if(c == '.') {
         
            endIndex = i;
            pointCounter++;
         
         }
      
      }
      
      if(colonCounter > 1 || pointCounter > 1) {
      
         valid = false;
      
      }
      
      if(endIndex != 0 && startIndex != 0) {
      
         String seconds = "";
         
         if(colonCounter == 1) {
         
           seconds = time.substring(startIndex + 1,endIndex);
            
         } else {
         
            seconds = time.substring(startIndex + 1);
         
         }
         
         if(seconds.length() != 2) {
         
            valid = false;
         
         }
      
      } else if(endIndex == 0) {
      
         valid = false;
      } else {
      
         String seconds = time.substring(endIndex + 1);
         
         for (int q = 0; q < seconds.length(); q++) {
         
            if(!Character.isDigit(seconds.charAt(q))) {
            
               valid = false;
            }
         
         }
      }
      
      return valid;
   
   }
   
   public boolean checkInputs() {
   
      boolean isOk = true;
      
      if(numParticipants >= 10) {
       
         for (int i = 0; i < names.length; i++) {
         
            if(AthleticsRaceSimulator.raceDistance != 100) {
            
               try {
                  
                  boolean validTime = checkTime(times[i].getText());
                  int experienceScore = Integer.valueOf(experienceScores[i].getText());
                  int kickScore = Integer.valueOf(kickScores[i].getText());
                  
                  if(validTime == false) {
                  
                     isOk = false;
                     
                  }
                  
                  if(experienceScore < 1 || kickScore < 1 || experienceScore > 10 || kickScore > 10) {
                  
                     isOk = false;
                  
                  }
               
               } catch(InputMismatchException e) {
               
                  isOk = false;
               
               } catch(NoSuchElementException e) {
               
                  isOk = false;
               
               } catch(NumberFormatException n) {
               
                  isOk = false;
                  
               }
               
            } else {
            
               try {
               
                  boolean validTime = checkTime(times[i].getText());
                  int startScore = Integer.valueOf(kickScores[i].getText());
                  
                  
                  if(validTime == false) {
                  
                     isOk = false;
                     
                  }
                  
                  if(startScore < 1 || startScore > 10) {
                  
                     isOk = false;
                  
                  }
               
               } catch(InputMismatchException e) {
               
                  isOk = false;
               
               } catch(NoSuchElementException e) {
               
                  isOk = false;
               
               } catch(NumberFormatException n) {
               
                  isOk = false;
                  
               }
            
            }
            
            if(isOk == false) {
            
               break;
               
            }
            
            
            
         
         }
         
      } else {
      
         for (int i = 0; i < numParticipants; i++) {
         
            if(AthleticsRaceSimulator.raceDistance != 100) {
            
               try {
               
                  int experienceScore = Integer.valueOf(experienceScores[i].getText());
                  int kickScore = Integer.valueOf(kickScores[i].getText());
                  
                  boolean validTime = checkTime(times[i].getText());
                  if(validTime == false) {
                  
                     isOk = false;
                     
                  }
                  
                  if(experienceScore < 1 || kickScore < 1 || experienceScore > 10 || kickScore > 10) {
                  
                     isOk = false;
                  
                  }
               
               } catch(InputMismatchException e) {
               
                  isOk = false;
               
               } catch(NoSuchElementException e) {
               
                  isOk = false;
               
               } catch(NumberFormatException n) {
               
                  isOk = false;
                  
               }
               
            } else {
            
               try {
               
                  int startScore = Integer.valueOf(kickScores[i].getText());
                  
                  boolean validTime = checkTime(times[i].getText());
                  
                  if(validTime == false) {
                  
                     isOk = false;
                     
                  }
                  
                  if(startScore < 1 || startScore > 10) {
                  
                     isOk = false;
                  
                  }
               
               } catch(InputMismatchException e) {
               
                  isOk = false;
               
               } catch(NoSuchElementException e) {
               
                  isOk = false;
               
               } catch(NumberFormatException n) {
               
                  isOk = false;
                  
               }
            
            }
            
            if(isOk == false) {
            
               break;
               
            }
            
            
            
         
         }

      
      }
      
      return isOk;
   
   }

}