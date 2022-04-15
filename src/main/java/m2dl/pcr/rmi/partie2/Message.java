package m2dl.pcr.rmi.partie2;

import java.io.Serializable;

public class Message implements Serializable {
    private String text;
    private String sender;

    public Message() {

    }

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return this.text;
    }

    public String getSender() {
        return this.sender;
    }

    @Override
    public String toString() {
        return "Sender: " + this.sender + " --- " + this.text;
    }
}
