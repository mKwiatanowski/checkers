package com.kodilla.checkers.GUI;


import javafx.scene.control.TextArea;

public class TextAreaManager {

    private final TextArea textArea = new TextArea();

    public TextArea setUpGameOutputFeed() {
        textArea.setPrefWidth(450);
        textArea.setMaxWidth(TextArea.USE_PREF_SIZE);
        textArea.setMinWidth(TextArea.USE_PREF_SIZE);
        textArea.setEditable(false);
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(20);
        textArea.setWrapText(true);
        textArea.getStylesheets().add("file:resources/text-area.css");
        return textArea;
    }

    public void display(String text) {
        textArea.appendText(text);
    }

    public String getTextAreaContent() {
        return textArea.getText();
    }

    public void setTextAreaContent(String text) {
        textArea.setText(text);
    }
}
