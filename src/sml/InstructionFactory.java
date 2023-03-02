package sml;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Map;

/**
 * A factory class for the creation of instruction objects to be used in a machine
 * @author Krysten Lawrence
 */
public class InstructionFactory {
    /**
     * Returns an instruction created by the translator
     *
     * @param opcode The opcode for the instruction to be used.
     * @param arguments The subsequent arguments needed for the creation of particular Instruction
     *                  These include the label, registers, integer and string values
     * @return The created instruction of a particular type based on the opcode
     */
    public Instruction createInstruction(String opcode, ArrayList<String> arguments) throws Exception {
        // Create the class name by capitalising the first letter then adding the package and
        // Instruction
        opcode = opcode.substring(0, 1).toUpperCase() + opcode.substring(1);
        Class<?> unknownClass = Class.forName("sml.instruction." + opcode + "Instruction");

        for (Constructor<?> constructor : unknownClass.getConstructors()) {

            Object[] classConstructors = new Object[arguments.size()];
            Class<?>[] constructorParamTypes = constructor.getParameterTypes();
            // Find the constructors, parse them and marry them with the variables read from the
            // .sml file
            for (int i = 0; i < arguments.size(); i++) {

                Class<?> newClass = toWrapper(constructorParamTypes[i]);
                // Assume only constructors of type Integer, Register and String will be used
                if (newClass.getName().contains("Register")) {
                    classConstructors[i] = Registers.Register.valueOf(arguments.get(i));
                } else if (newClass.getName().contains("Integer")) {
                    classConstructors[i] = Integer.parseInt(arguments.get(i));
                } else if (newClass.getName().contains("String")) {
                    classConstructors[i] = (arguments.get(i).contains("null"))
                            ? null
                            : arguments.get(i);
                } else{
                    throw new NoSuchFieldException(newClass.getName() + " is not currently used");
                }
            }

            return (Instruction) constructor.newInstance(classConstructors);

        }
        return null;
    }

    // Only Integer is used at the moment, more can be added if needs be
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPE_WRAPPERS = Map.of(
            int.class, Integer.class);

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
