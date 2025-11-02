package lab4;

import java.util.*;

public class TextParser {
    public static String normalize(String text) {
        return text.replaceAll("[\\t ]+", " ").trim();
    }

    public static Text parse(String text) {
        List<Sentence> sentences = new ArrayList<>();
        List<Word> words = new ArrayList<>();

        String[] tokens = text.split("\\s+");

        for (String token : tokens) {
            if (token.matches("[a-zA-Z]+")) {
                List<Letter> letters = new ArrayList<>();
                for (char c : token.toCharArray()) {
                    letters.add(new Letter(c));
                }
                words.add(new Word(letters));
            }
        }

        sentences.add(new Sentence(words));

        return new Text(sentences);
    }
}
