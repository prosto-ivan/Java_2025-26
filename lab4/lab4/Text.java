package lab4;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private final List<Sentence> sentences;

    public Text(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (Sentence sentence : sentences) {
            words.addAll(sentence.getWords());
        }
        return words;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentences) {
            sb.append(sentence).append(". ");
        }
        return sb.toString().trim();
    }
}
