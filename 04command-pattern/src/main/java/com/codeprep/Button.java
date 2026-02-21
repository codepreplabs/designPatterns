package com.codeprep;

/**
 * Invoker - Asks the command to carry out the request
 * The invoker holds a command and at some point asks the command to carry out a request
 */
public class Button {

    private Command command;

    public Button(Command command) {
        this.command = command;
    }

    /**
     * Trigger the command execution
     */
    public void onClick() {
        if (command != null) {
            command.execute();
        }
    }

    /**
     * Allows dynamic command assignment (useful for changing button behavior at runtime)
     */
    public void setCommand(Command command) {
        this.command = command;
    }
}
