package io.github.aglushkovsky.common;

import io.github.aglushkovsky.common.dialog.ConsoleDialog;
import io.github.aglushkovsky.common.dialog.Dialog;
import io.github.aglushkovsky.common.input_validator.InputValidator;
import io.github.aglushkovsky.common.input_validator.RangeValidator;

import java.util.HashMap;
import java.util.Map;

public class Launcher {
    public interface Action {
        void execute();
    }

    private static final Builder builder = new Builder();
    private static final int START_COMMAND_ID = 1;
    private static final String COMMAND_MESSAGE_TEMPLATE = "[%d] %s";

    private final Map<Integer, Action> actions = new HashMap<>();
    private final StringBuilder message = new StringBuilder();
    private Dialog dialog;
    private int commandIdSequence = START_COMMAND_ID;
    private boolean isRunning;

    private Launcher() {
        addExitAction();
    }

    public void addAction(String description, Action action) {
        message.append(commandIdSequence != START_COMMAND_ID ? "\n" : "");
        message.append(String.format(COMMAND_MESSAGE_TEMPLATE, commandIdSequence, description));
        actions.put(commandIdSequence, action);
        commandIdSequence++;
    }

    public void start() {
        isRunning = true;
        while (isRunning) {
            displayStartMessage();
            String userInput = dialog.getInput();
            actions.get(Integer.parseInt(userInput)).execute();
        }
    }

    private void displayStartMessage() {
        System.out.println(message);
    }

    private void addExitAction() {
        addAction("Выход", this::stop);
    }

    private void stop() {
        System.out.println("Выходим...");
        isRunning = false;
    }

    private void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    private int getCommandIdSequence() {
        return commandIdSequence;
    }

    private int getStartCommandId() {
        return START_COMMAND_ID;
    }

    public static Builder builder() {
        return builder;
    }

    public static class Builder {
        private final Launcher launcher = new Launcher();

        public Builder addAction(String description, Action action) {
            launcher.addAction(description, action);
            return this;
        }

        private void setDialog() {
            InputValidator inputValidator = createInputValidator();
            ConsoleDialog consoleDialog = new ConsoleDialog(inputValidator);
            launcher.setDialog(consoleDialog);
        }

        private InputValidator createInputValidator() {
            return new RangeValidator(launcher.getStartCommandId(), launcher.getCommandIdSequence() - 1);
        }

        public Launcher build() {
            setDialog();
            return launcher;
        }
    }
}
