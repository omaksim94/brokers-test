package dto;

import java.io.Serializable;

public class MyMessage  implements Serializable {
    private String text;

    public MyMessage(String text) {
        this.text = text;
    }

    public MyMessage() {
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
