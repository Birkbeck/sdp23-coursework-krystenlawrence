package sml;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * This class reads the given .sml file and translates it into a form that the machine can use by
 * creating the correct Instruction
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Krysten Lawrence
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws Exception {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     * Exception is used as this method throws multiple types of Exception
     */
    private Instruction getInstruction(String label) throws Exception {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        // A list of values that have been read from the sml file that will be used to construct
        // the type of Instruction. Opcode has been removed and label is added whether it is null
        // or not, hence the String.valueOf(). Subsequent commands are added.
        ArrayList<String> lineVariables = new ArrayList<>();
        lineVariables.add(String.valueOf(label));
        while (line.contains(" ")) {
            lineVariables.add(scan());
        }

        InstructionFactory instructionFactory = new InstructionFactory();

        return instructionFactory.createInstruction(opcode, lineVariables);
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}
