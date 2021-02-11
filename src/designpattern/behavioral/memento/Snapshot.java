package designpattern.behavioral.memento;

public class Snapshot {
    private String text;

    public Snapshot(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
