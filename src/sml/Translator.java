package sml;


import java.io.File;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    private Class<?> unknownClass;

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
     */
    private Instruction getInstruction(String label) throws Exception {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        // A list of values that have been read from the sml file that will be used to construct
        // the type of Instruction. Opcode has been removed and label is added whether it is null
        // or not, hence the String.valueOf(). Subsequent commands are added
        List<String> lineVariables = new ArrayList<>();
        lineVariables.add(String.valueOf(label));
        while (line.contains(" ")) {
            lineVariables.add(scan());
        }

        opcode = opcode.substring(0, 1).toUpperCase() + opcode.substring(1);
        this.unknownClass = Class.forName("sml.instruction." + opcode + "Instruction");

        for (Constructor<?> constructor : unknownClass.getConstructors()) {
            Object[] classConstructors = new Object[lineVariables.size()];
            Class<?>[] constructorParamTypes = constructor.getParameterTypes();
            for (int i = 0; i < lineVariables.size(); i++) {
                Class<?> newClass = toWrapper(constructorParamTypes[i]);
                // Assume only constructors of type Integer, Register and String will be used
                if (newClass.getName().contains("Register")) {
                    classConstructors[i] = Register.valueOf(lineVariables.get(i));
                } else if (newClass.getName().contains("Integer")) {
                    classConstructors[i] = Integer.parseInt(lineVariables.get(i));
                } else if (newClass.getName().contains("String")) {
                    classConstructors[i] = (lineVariables.get(i).contains("null"))
                            ? null
                            : lineVariables.get(i);
                } else{
                    throw new NoSuchFieldException(newClass.getName() + " is not currently used");
                }
            }
            return (Instruction) constructor.newInstance(classConstructors);

        }

        // TODO: Next, use dependency injection to allow this machine class
        //       to work with different sets of opcodes (different CPUs)

        return null;
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

    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPE_WRAPPERS = Map.of(
            int.class, Integer.class,
            long.class, Long.class,
            boolean.class, Boolean.class,
            byte.class, Byte.class,
            char.class, Character.class,
            float.class, Float.class,
            double.class, Double.class,
            short.class, Short.class,
            void.class, Void.class);

    /**
     * Return the correct Wrapper class if testClass is primitive
     *
     * @param testClass class being tested
     * @return Object class or testClass
     */
    private static Class<?> toWrapper(Class<?> testClass) {
        return PRIMITIVE_TYPE_WRAPPERS.getOrDefault(testClass, testClass);
    }
}
