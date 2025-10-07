package lab2;

import java.util.Arrays;

public class StringForLab2 {
    /* c3 = 1 c17 = 7 
    String
    Відсортувати слова заданого тексту,
    що починаються з голосних літер, за другою літерою
    */

    public static void main(String[] args) {
       try {
        StringForLab2 proc = new StringForLab2();
        proc.execute();
       } catch (Exception e) {
        System.err.println(e.getMessage());
       }
    }

    public void execute() {
        // всі слова на голосну сортуємо по алфавіту за другою буквою

        String a = "Hello, my name is Ivan and i wona tell you my story for all of us";
        sortString(a);
    }

    private void sortString(String str) {
        if (str == null) {
            throw new NullPointerException("Масиви не можуть бути null");
        }

        String vowels = "AEIOUYaeiouy";
        String[] words = str.split("\\s+");
        String[] vowelWords = new String[words.length];
        int count = 0; 

        for (String word : words) {
            if (word.isEmpty()) continue;
            word = word.replaceAll("[^a-zA-Z]", ""); // прибираємо розділові знаки
            if (word.isEmpty()) continue;

            char first = word.charAt(0);
            if (vowels.indexOf(first) != -1) {
                vowelWords[count] = word;
                count++;
            }
        }
        String[] filtered = Arrays.copyOf(vowelWords, count);

        for (int i = 0; i < filtered.length; i++) {
           for (int j = 0; j < filtered.length; j++) {
                if (filtered[i].length() < 2 || filtered[j].length() < 2) 
                {

                    continue;
                }

                if (filtered[i].charAt(1) < filtered[j].charAt(1)) {
                    String temp = filtered[i];
                    filtered[i] = filtered[j];
                    filtered[j] = temp;
                }
           } 
        }

        System.out.println(Arrays.toString(filtered));
    }
}
