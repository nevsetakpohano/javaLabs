import java.util.*;
import java.io.*;

public class Lab2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("Enter your text (press Enter twice to finish):");
            StringBuilder inputText = new StringBuilder();
            String line;
            
            while (!(line = scanner.nextLine()).isEmpty()) {
                inputText.append(line).append(" ");
            }
            
            if (inputText.length() == 0) {
                System.out.println("No text entered. Using default text.");
                inputText = new StringBuilder("Hello world! Java is great.");
            }
            
            List<StringBuilder> sortedWords = sortWordsByVowelCount(inputText);
            
            System.out.println("\nOriginal text: " + inputText.toString());
            System.out.println("\nWords sorted by vowel count (ascending):");
            System.out.println("Word -> Vowel count");
            System.out.println("-".repeat(30));
            
            for (StringBuilder wordSB : sortedWords) {
                String word = wordSB.toString();
                System.out.printf("%-15s -> %d\n", word, countVowels(wordSB));
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static List<StringBuilder> sortWordsByVowelCount(StringBuilder text) {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("Text cannot be null or empty");
        }
        
        List<StringBuilder> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                currentWord.append(c);
            } else {
                if (currentWord.length() > 0) {
                    words.add(currentWord);
                    currentWord = new StringBuilder();
                }
            }
        }
        
        if (currentWord.length() > 0) {
            words.add(currentWord);
        }
        
        words.sort(Comparator.comparingInt(Lab2::countVowels));
        
        return words;
    }
    
    public static int countVowels(StringBuilder word) {
        if (word == null || word.length() == 0) return 0;
        int count = 0;
        String vowels = "aeiouAEIOU";
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}