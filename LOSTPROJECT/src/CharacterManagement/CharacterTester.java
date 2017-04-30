package CharacterManagement;

import java.util.*;
import java.io.*;

public class CharacterTester {

	private static String readFile(String filePath){
        String line = null;
        String text = "";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
                text = text + line + "\n";
            
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "!");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filePath + "!");
        }

        System.out.println(text);
        return null;
	}
	
	public static void main(String[] args){
		
		
	}
}
