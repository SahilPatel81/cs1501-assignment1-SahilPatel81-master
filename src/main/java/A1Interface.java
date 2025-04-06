import java.util.Set;

public interface A1Interface {
    /**
     * The Boggle Board is a two-dimensional array of English letters with the following special characters:
     * A wildcard character (*) can represent any letter when forming words and a blocked cell (#) cannot 
     * be traversed or included in words. The method returns the set of valid, unique words of a given length 
     * from the Boggle board, considering the rules for wildcards and blocked cells.
     *
     * @param boggleBoard the 2-d character array representing the Boggle board
     * @param dictionary the DictInterface dictionary
     * @param wordLength the length of the words to be found
     * @return an (possibly empty) Set<String> of the found words
     */
    Set<String> getWordsOfLength(char[][] boggleBoard, DictInterface dictionary, int wordLength);




}