package core;

import java.util.ArrayList;
import java.util.List;


public class WordValidator {

    public boolean wordContainsLettersInOrder(final LetterGen letGen, final String word) {
	final char[] a = word.toCharArray();
	final char[] test = new char[a.length];

	for(int i=0; i < a.length; i++){
	    test[i] = Character.toLowerCase(a[i]);
	}

	int i = 0;
	char temp = Character.toLowerCase(letGen.getLetter1());
	while(i < test.length){
	    if(temp==test[i]){
		i++;
		break;
	    }
	    i++;
	}

	temp = Character.toLowerCase(letGen.getLetter2());
	while(i < test.length){
	    if(temp==test[i]){
		i++;
		break;
	    }
	    i++;
	}

	temp= Character.toLowerCase(letGen.getLetter3());
	while(i < test.length){
	    if(temp==test[i]){
		i++;
		return true;
	    }
	    i++;
	}

	return false;
    }

    /**
     * TODO: Basically a custom contains check to avoid casing differences.
     *	     Couldn't we just use a set in the beginning and add the words in lowercased format?
     */
    public boolean wordExists(final String word, final List<String> words){
	for (final String current : words) {
	    if( word.equalsIgnoreCase(current) ) {
		return true;
	    }
	}
	return false;
    }

    public List<String> findSolutions(final LetterGen letGen, final List<String> words) {
	final List<String> solutions = new ArrayList<String>();

	for (final String word : words) {
	    if( wordContainsLettersInOrder(letGen, word) ) {
		solutions.add(word);
	    }
	}

	return solutions;
    }
}