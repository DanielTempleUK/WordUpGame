package core;
public class LetterGen{
    
    private char letter1;
    private char letter2;
    private char letter3;
    private static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 
	                             'G', 'H', 'I', 'J', 'K', 'L', 
	                             'M', 'N', 'O', 'P', 'Q', 'R', 
	                             'S', 'T', 'U', 'V', 'W', 'X', 
	                             'Y', 'Z'};
    
    public LetterGen(){
        int x = (int)((Math.random()*26));
        int y = (int)((Math.random()*26));
        int z = (int)((Math.random()*26));
        letter1 = letters[x];
        letter2 = letters[y];
        letter3 = letters[z];
    }
        
    public char getLetter1(){
        return letter1;
    }
    
    public char getLetter2(){
        return letter2;
    }
    
    public char getLetter3(){
        return letter3;
    }
}