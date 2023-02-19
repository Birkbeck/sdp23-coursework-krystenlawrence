package sml;

import java.util.Objects;

/**
 * Represents an abstract instruction.
 *
 * @author Krysten Lawrence
 */
public abstract class Instruction {
    protected final String label;
    protected final String opcode;

    /**
     * Constructor: an instruction with a label and an opcode
     * (opcode must be an operation of the language)
     *
     * @param label  optional label (can be null)
     * @param opcode operation name
     */
    public Instruction(String label, String opcode) {
        this.label = label;
        this.opcode = opcode;
    }

    public String getLabel() {
        return label;
    }

    public String getOpcode() {
        return opcode;
    }

    public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

    /**
     * Executes the instruction in the given machine.
     *
     * @param machine the machine the instruction runs on
     * @return the new program counter (for jump instructions)
     * or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     * the instruction with the next address is to be executed
     */

    public abstract int execute(Machine machine);

    protected String getLabelString() {
        return (getLabel() == null) ? "" : getLabel() + ": ";
    }

    // TODO: What does abstract in the declaration below mean?
    //       The abstract in the method name means that the method does not need to be implemented to be declared
    //       In the below example, this means that, whilst the method is ordinarilt supposed to return a String, because
    //       of the abstract declaration, it does not need to return anything
    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (o instanceof Instruction i) {
            return this.getClass().equals(i.getClass())
					&& this.label.equals(i.label)
					&& this.opcode.equals(i.opcode);
        }
        return false;
    }

    @Override
    public int hashCode() {
		return Objects.hash(label, opcode);
    }

}
