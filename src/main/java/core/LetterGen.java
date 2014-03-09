package core;
public class LetterGen{

    private final char letter1;
    private final char letter2;
    private final char letter3;
    private static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F',
	'G', 'H', 'I', 'J', 'K', 'L',
	'M', 'N', 'O', 'P', 'Q', 'R',
	'S', 'T', 'U', 'V', 'W', 'X',
	'Y', 'Z'};

    public LetterGen(){
	final int x = (int)(Math.random()*26);
	final int y = (int)(Math.random()*26);
	final int z = (int)(Math.random()*26);
	this.letter1 = letters[x];
	this.letter2 = letters[y];
	this.letter3 = letters[z];
    }

    /**
     * This constructor allows us to create these objects for tests
     */
    public LetterGen(final char letter1, final char letter2, final char letter3){
	this.letter1 = letter1;
	this.letter2 = letter2;
	this.letter3 = letter3;
    }

    public char getLetter1(){
	return this.letter1;
    }

    public char getLetter2(){
	return this.letter2;
    }

    public char getLetter3(){
	return this.letter3;
    }
}