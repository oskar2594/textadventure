package de.oskar.world.objects.dialogue;

import java.util.LinkedList;

import de.oskar.utils.Pair;

public class Sequence {

    private boolean isEnd = false;

    private String text;
    private LinkedList<Pair<String, Sequence>> options;


    /**
     * Creates a new sequence with the given text
     * @param text the text
     * @param isEnd if the sequence is the end of the dialogue
     * @param options the options
     */
    public Sequence(String text, boolean isEnd, LinkedList<Pair<String, Sequence>> options) {
        this.text = text;
        this.isEnd = isEnd;
        this.options = options;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LinkedList<Pair<String, Sequence>> getOptions() {
        return options;
    }

    public void setOptions(LinkedList<Pair<String, Sequence>> options) {
        this.options = options;
    }

    public void addOption(String option, Sequence sequence) {
        this.options.add(new Pair<String, Sequence>(option, sequence));
    }

}
