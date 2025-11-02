package lab4;

import java.util.List;

public class Sentence {
    private final List<Word> words;

    public Sentence(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Word word : words) {
            sb.append(word).append(" ");
        }
        return sb.toString().trim();
    }
}
