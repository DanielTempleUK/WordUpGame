package core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utils.CollectionUtils;

public class WordValidatorTest {

    WordValidator wordValidator;

    @Before
    public void setUp() {
	this.wordValidator = new WordValidator();
    }

    @Test
    public void thatWordContainsAllLettersInOrderIsTrue() {
	assertThat(this.wordValidator.wordContainsLettersInOrder(new LetterGen('a', 'b', 'c'), "abc"), is(true));
    }

    @Test
    public void thatWordContainsAllLettersInOrderIsTrueWithUpperCaseLetters() {
	assertThat(this.wordValidator.wordContainsLettersInOrder(new LetterGen('A', 'B', 'C'), "abc"), is(true));
    }

    @Test
    public void thatWordContainsAllLettersInOrderIsTrueWithUpperCaseWord() {
	assertThat(this.wordValidator.wordContainsLettersInOrder(new LetterGen('a', 'b', 'c'), "ABC"), is(true));
    }

    @Test
    public void thatWordContainsAllLettersOutOfOrderIsFalse() {
	assertThat(this.wordValidator.wordContainsLettersInOrder(new LetterGen('c', 'b', 'a'), "abc"), is(false));
    }

    @Test
    public void thatWordDoesNotContainFinalLetterIsFalse() {
	assertThat(this.wordValidator.wordContainsLettersInOrder(new LetterGen('a', 'b', 'd'), "abc"), is(false));
    }

    @Test
    public void thatWordDoesNotContainMiddleLetterIsFalse() {
	assertThat(this.wordValidator.wordContainsLettersInOrder(new LetterGen('a', 'd', 'c'), "abc"), is(false));
    }

    @Test
    public void thatWordDoesNotContainFirstLetterIsFalse() {
	assertThat(this.wordValidator.wordContainsLettersInOrder(new LetterGen('d', 'b', 'c'), "abc"), is(false));
    }

    @Test
    public void thatWordExistsIsTrueWhenWordExistsFirstWord() {
	final List<String> words = CollectionUtils.listOf("abc", "def", "ghi", "jkl", "mno");
	assertThat(this.wordValidator.wordExists("abc", words), is(true));
    }

    @Test
    public void thatWordExistsIsTrueWhenWordExistsNotFirstWord() {
	final List<String> words = CollectionUtils.listOf("abc", "def", "ghi", "jkl", "mno");
	assertThat(this.wordValidator.wordExists("jkl", words), is(true));
    }

    @Test
    public void thatWordExistsIsFalseWhenWordDoesNotExist() {
	final List<String> words = CollectionUtils.listOf("abc", "def", "ghi", "jkl", "mno");
	assertThat(this.wordValidator.wordExists("zyx", words), is(false));
    }

    @Test
    public void thatWordExistsIgnoresCaseOfWord() {
	final List<String> words = CollectionUtils.listOf("abc", "def", "ghi", "jkl", "mno");
	assertThat(this.wordValidator.wordExists("DEF", words), is(true));
    }

    @Test
    public void thatWordExistsIgnoresCaseOfWordsInList() {
	final List<String> words = CollectionUtils.listOf("ABC", "DEF", "GHI", "JKL", "MNO");
	assertThat(this.wordValidator.wordExists("abc", words), is(true));
    }

    @Test
    public void thatFindSolutionsReturnsAllSolutions() {
	final List<String> words = CollectionUtils.listOf("ananad", "bnbnbd", "cncnc");
	final List<String> solutions = this.wordValidator.findSolutions(new LetterGen('n','n','d'), words);

	assertThat(solutions.size(), is(2));
	assertThat(solutions.get(0), is("ananad"));
	assertThat(solutions.get(1), is("bnbnbd"));
    }

}