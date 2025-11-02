package lab4;

import java.util.List;

public class Word {
    private final List<Letter> letters;

    public Word(List<Letter> letters) {
        this.letters = letters;
    }

    public boolean startsWithVowel() {
        char ch = Character.toLowerCase(letters.get(0).getValue());
        return "aeiouAEIOU".indexOf(ch) >= 0;  
    }

    public char secondLetterOrDefault(char def) {
    if (letters.size() > 1) {
        Letter secondLetter = letters.get(1);
        return secondLetter.getValue();
    } 
    
    else {
        return def;
    }
}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter.getValue());
        }
        return sb.toString();
    }
}
