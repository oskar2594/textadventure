package de.oskar.world.objects.dialogue;

import java.util.LinkedList;
import java.util.Scanner;

import de.oskar.Game;
import de.oskar.utils.Pair;
import de.oskar.world.objects.LocationObject;

public class Dialogue extends LocationObject {

    private boolean autoStart = false;
    private boolean finished = false;

    private Sequence startSequence;

    /**
     * Creates a new dialogue with the given start sequence
     * @param startSequence the start sequence
     * @param autoStart if the dialogue should start automatically
     */
    public Dialogue(Sequence startSequence, boolean autoStart) {
        super("");
        this.startSequence = startSequence;
        this.autoStart = autoStart;
    }

    /**
     * Starts the dialogue
     */
    @Override
    public void execute() {
        if(finished) return;
        Sequence currentSequence = startSequence;
        Scanner scanner = Game.scanner;
        while (currentSequence != null) {
            Game.console.printProcedural(currentSequence.getText());
            if(currentSequence.isEnd()) break;
            LinkedList<Pair<String, Sequence>> options = currentSequence.getOptions();
            for (int i = 0; i < options.size(); i++) {
                Pair<String, Sequence> option = options.get(i);
                Game.console.printProcedural("[" + (i + 1) + "] " + option.getA());
            }
            System.out.print("> ");
            try {
                int input = scanner.nextInt();

                if(input < 1 || input > options.size()) {
                    Game.console.printProcedural("Ungültige Eingabe!");
                    continue;
                }
                currentSequence = options.get(input - 1).getB();
    
                if(currentSequence.isEnd()) {
                    Game.console.printProcedural(currentSequence.getText());
                    currentSequence = null;
                    
                }

            } catch (Exception e) {
                Game.console.printProcedural("Ungültige Eingabe!");
                scanner.nextLine();
            }
        }
        finished = true;
    }

    public boolean isAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    public Sequence getStartSequence() {
        return startSequence;
    }

    public void setStartSequence(Sequence startSequence) {
        this.startSequence = startSequence;
    }
}
