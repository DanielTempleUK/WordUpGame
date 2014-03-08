package core;
import java.util.*;
import java.io.*;

public class Scores
{
    public static String[][] topScores = new String[5][2];
    private BufferedReader reader;
    private FileWriter f;
    
    public Scores()
    {
    }

    public void loadScores(){
        try{
            reader = new BufferedReader(new FileReader("HighScore.txt"));
            new Scanner(reader);
        }
        
        catch(FileNotFoundException e){
            System.out.println("waah!");
            return;
        }
        
        for(int x = 0; x<5; x++){
            try{
                String tmp = reader.readLine();
                if(tmp==null){
                    return;
                }
                int i = 0;
                char[] tmparray = tmp.toCharArray();
                char current = tmparray[0];
                String player = new String();
                while(current != ','){
                    player = player.concat("" + current);
                    i++;
                    current = tmparray[i];
                }
                i=i+2;
                current = tmparray[i];
                String score = new String();
                while(i<tmparray.length){
                    score = score.concat("" + current);
                    if(i == tmparray.length-1){
                        break;
                    }
                    i++;
                    current = tmparray[i];
                }
                topScores[x][0] = player;
                topScores[x][1] = score;
            }
            catch(IOException e){
                System.out.println("wah!");
                return;
            }
        }
        sort();
    }
    
    
    
    public static boolean isHighScore(int score){
        Integer scorez = 0;
        for(int i = 0; i < 5; i++){
            scorez = Integer.parseInt(topScores[i][1]);
            if(score > scorez){
                return true;
            }
        }
        return false;
    }
    
    
    
    public void saveHighScore(int score, String name){
        try{
            f= new FileWriter("HighScore.txt");
        }catch(IOException e){
            System.out.println("fail");
        }
        sort();
        Integer lowest = 1000;
        Integer lowestKey = 0;
        for(int i = 0; i<5; i++){
            if(Integer.parseInt(topScores[i][1]) < lowest){
                lowest = Integer.parseInt(topScores[i][1]);
                lowestKey = i;
            }
        }
        String s = new String();
        s = String.valueOf(score);
        topScores[lowestKey][0] = name;
        topScores[lowestKey][1] = s;
        
        
        for(int i = 0; i<5; i++){
            String toWrite = (topScores[i][0] + ", " + topScores[i][1]);
            char[] write = toWrite.toCharArray();
            
            try{
            f.write(write);
            f.write('\r');
            f.write('\n');
            }catch(IOException e){
                System.out.println("Fail - "+e.getMessage()+"\n\n"); e.printStackTrace();
            }
        }
        
        try{
            f.close();
        }catch(IOException e){
            System.out.println("fail");
        }
    }


    public void sort(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<4; j++){
                if(Integer.parseInt(topScores[j][1]) < Integer.parseInt(topScores[j+1][1])){
                    
                    String score = topScores[j+1][1];
                    String name = topScores[j+1][0];
                    
                    topScores[j+1][0] = topScores[j][0];
                    topScores[j+1][1] = topScores[j][1];
                    
                    topScores[j][0] = name;
                    topScores[j][1] = score;
                }
            }
        }
    }
                
                
                
                
                
                
                
                
                
                
                
                
                
    
}