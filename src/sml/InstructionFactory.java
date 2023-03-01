package sml;

/**
 * A factory interface for the creation of instruction objects to be used in a machine
 * @author Krysten Lawrence
 */
public interface InstructionFactory {
    /**
     * Returns an instruction created by the translator
     *
     * @param label     The label belonging to the instruction
     * @param arguments The subsequent arguments needed for the creation of particular Instruction
     * @return The created instruction of a particular type
     */
    Instruction createInstruction(String label, String[] arguments);
}
