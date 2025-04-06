import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import java.util.HashSet;

public class A1Test {
    private A1 game;
    private MyDictionary dictionary;

    @Before
    public void setUp() {
        game = new A1();
    }

    @Test
    public void testBasicBoard() {
        char[][] board = {
                { 'T', 'R', 'A', 'P' },
                { 'S', 'E', 'L', 'M' },
                { 'I', 'D', 'O', 'G' },
                { 'N', 'E', 'T', 'S' }
        };
        dictionary = new MyDictionary();
        dictionary.add("TRAP");
        dictionary.add("DOG");
        dictionary.add("NET");
        dictionary.add("TREE");
        dictionary.add("STARS");

        Set<String> words = new HashSet<>();
        words.add("DOG");
        words.add("NET");

        assertEquals(words, game.getWordsOfLength(board, dictionary, 3));
        words.clear();
        words.add("TRAP");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 4));
        assertTrue(game.getWordsOfLength(board, dictionary, 5).isEmpty());
    }

    @Test
    public void testBoardWithWildcard() {
        char[][] board = {
                { 'T', 'R', 'A', '*' },
                { 'S', 'E', 'L', 'M' },
                { 'I', 'D', 'O', 'G' },
                { 'N', 'E', 'T', 'S' }
        };
        dictionary = new MyDictionary();
        dictionary.add("TRAP");
        dictionary.add("DOG");
        dictionary.add("NET");
        dictionary.add("TREE");
        dictionary.add("STARS");
        dictionary.add("RAT");
        dictionary.add("SOLD");
        dictionary.add("SOLAR");


        Set<String> words = new HashSet<>();
        words.add("DOG");
        words.add("NET");
        words.add("RAT");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 3));
        words.clear();
        words.add("TRAP");
        words.add("SOLD");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 4));
        words.clear();
        words.add("SOLAR");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 5));
        assertTrue(game.getWordsOfLength(board, dictionary, 6).isEmpty());
    }
 
    @Test
    public void testBoardWithBlockedCell() {
        char[][] board = {
                { 'T', 'R', '#', 'P' },
                { 'S', '*', 'L', 'M' },
                { 'I', 'D', 'O', 'G' },
                { 'N', 'E', 'T', 'S' }
        };
        dictionary = new MyDictionary();
        dictionary.add("TRAP");
        dictionary.add("SOLD");
        dictionary.add("TIDE");
        dictionary.add("DIRT");
        dictionary.add("DIR");
        dictionary.add("ROP");
        dictionary.add("TRAIN");

        Set<String> words = new HashSet<>();
        words.add("DIR");

        assertEquals(words, game.getWordsOfLength(board, dictionary, 3));
        words.clear();
        words.add("TIDE");
        words.add("SOLD");
        words.add("DIRT");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 4));
        words.clear();
        words.add("TRAIN");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 5));
        assertTrue(game.getWordsOfLength(board, dictionary, 6).isEmpty());
    }

    @Test
    public void testBoardWithMultipleWildcardsAndBlockedCells() {
        char[][] board = {
                { 'T', '*', 'A', 'P' },
                { 'S', '#', 'L', 'M' },
                { 'I', '*', 'O', 'G' },
                { '#', 'E', 'T', 'S' }
        };
        dictionary = new MyDictionary();
        dictionary.add("MIST");
        dictionary.add("SOLD");
        dictionary.add("ISLE");
        dictionary.add("T*AP");
        dictionary.add("T#OG");
        dictionary.add("SIT");
        dictionary.add("LIT");
        dictionary.add("L*S");
        dictionary.add("TENSE");
        dictionary.add("TE*#*");        

        Set<String> words = new HashSet<>();
        words.add("LIT");
        words.add("SIT");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 3));
        words.clear();
        words.add("MIST");
        words.add("SOLD");
        words.add("ISLE");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 4));
        words.clear();
        words.add("TENSE");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 5));
        assertTrue(game.getWordsOfLength(board, dictionary, 6).isEmpty());
    }

    @Test
    public void testSmallestBoard() {
        char[][] board = {
                { 'A', 'B' },
                { 'C', 'D' }
        };
        dictionary = new MyDictionary();
        dictionary.add("ABCD");

        assertTrue(game.getWordsOfLength(board, dictionary, 2).isEmpty());
        assertTrue(game.getWordsOfLength(board, dictionary, 3).isEmpty());
        assertTrue(game.getWordsOfLength(board, dictionary, 5).isEmpty());
        Set<String> words = new HashSet<>();
        words.add("ABCD");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 4));
    }

    @Test
    public void testLargeRandomBoard() {
        char[][] board = {
                { 'C', 'A', 'T', 'D', 'E' },
                { 'R', '*', 'L', 'M', 'O' },
                { 'F', 'I', '#', 'G', 'H' },
                { 'S', 'A', 'N', 'D', 'Z' },
                { 'X', 'Y', 'P', 'Q', 'W' }
        };
        dictionary = new MyDictionary();
        dictionary.add("CAT");
        dictionary.add("FAT");
        dictionary.add("THIN");        
        dictionary.add("SAND");
        dictionary.add("FILE");
        dictionary.add("SAID");
        dictionary.add("TL#N");
        dictionary.add("G#IF");
        dictionary.add("CA*R");
        dictionary.add("CABIN");
        dictionary.add("THIEF");
        dictionary.add("TARIFF");

        Set<String> words = new HashSet<>();
        words.add("CAT");
        words.add("FAT");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 3));
        words.clear();
        words.add("THIN");        
        words.add("SAND");
        words.add("FILE");
        words.add("SAID");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 4));
        words.clear();
        words.add("CABIN");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 5));
        words.clear();
        words.add("TARIFF");
        assertEquals(words, game.getWordsOfLength(board, dictionary, 6));
    }

}