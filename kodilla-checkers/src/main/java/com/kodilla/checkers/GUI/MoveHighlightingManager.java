package com.kodilla.checkers.gui;


import javafx.scene.control.Button;

public class MoveHighlightingManager {

    private boolean userMoveHighlighting = true;
    private boolean computerMoveHighlighting = true;
    private Button userMoveHighlightingToggleButton;
    private Button computerMoveHighlightingToggleButton;

    public MoveHighlightingManager(Button userMoveHighlightingToggleButton, Button computerMoveHighlightingToggleButton) {
        this.userMoveHighlightingToggleButton = userMoveHighlightingToggleButton;
        this.computerMoveHighlightingToggleButton = computerMoveHighlightingToggleButton;
    }

    public void toggleUserMovesHighlighting() {
        this.userMoveHighlighting = !this.userMoveHighlighting;

        if (this.userMoveHighlighting) {
            userMoveHighlightingToggleButton.setText("Disable: User moves backlight \n");
        } else {
            userMoveHighlightingToggleButton.setText("Enable: User moves backlight \n");
        }
    }

    public void toggleComputerMovesHighlighting() {
        this.computerMoveHighlighting = !this.computerMoveHighlighting;

        if (this.computerMoveHighlighting) {
            computerMoveHighlightingToggleButton.setText("Disable: Computer moves backlight \n");
        } else {
            computerMoveHighlightingToggleButton.setText("Enable: Computer moves backlight \n");
        }
    }

    public boolean isUserMoveHighlighting() {
        return userMoveHighlighting;
    }

    public boolean isComputerMoveHighlighting() {
        return computerMoveHighlighting;
    }

    public void setUserMoveHighlighting(boolean userMoveHighlighting) {
        this.userMoveHighlighting = userMoveHighlighting;
        if (userMoveHighlighting) {
            userMoveHighlightingToggleButton.setText("Disable: User moves backlight \n");
        } else {
            userMoveHighlightingToggleButton.setText("Enable: User moves backlight \n");
        }
    }

    public void setComputerMoveHighlighting(boolean computerMoveHighlighting) {
        this.computerMoveHighlighting = computerMoveHighlighting;
        if (computerMoveHighlighting) {
            computerMoveHighlightingToggleButton.setText("Disable: Computer moves backlight \n");
        } else {
            computerMoveHighlightingToggleButton.setText("Enable: Computer moves backlight \n");
        }
    }
}
