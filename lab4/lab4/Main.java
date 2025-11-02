package lab4;

import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        /*
         * Відсортувати слова заданого тексту, що починаються з 
         * голосних літер, за другою літерою. 
         */

        String input = "Elephants are amazing animals.   Also, apple and orange are fruits.";

        String normalized = TextParser.normalize(input);

        Text text = TextParser.parse(normalized);

        List<Word> vowelStartWords = text.getWords().stream()
            .filter(word -> word.startsWithVowel())
            .collect(Collectors.toList());

        // cортуємо за другою літерою
        vowelStartWords.sort(Comparator.comparingInt((Word word) -> word.secondLetterOrDefault('z')));

        System.out.println("Нормалізований текст: " + normalized);
        System.out.println("відсортовані за другою літерою:");
        vowelStartWords.forEach(word -> System.out.println(word));
    }
}
