package com.github.SakuraMatrix.p1lanchipham.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** Method for logging to an external file */
public class logback {
  public void writeToFile() throws IOException{ 
    try{
        File out = new File("budgetLogs.txt"); 
        FileWriter fw = new FileWriter("budgetLogs.txt");    
        out.createNewFile();
        fw.write("Budget changes: " + "\n"); 
        fw.write("=============================================================================" + "\n");  
        fw.close(); 
    }catch (IOException e) {
      System.out.println("An error occurred.");
        e.printStackTrace();
    }
  }
}
