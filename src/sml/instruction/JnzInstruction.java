package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * An instruction subclass that checks the contents of a register r. If the contents are not equal
 * to 0, then statement of the given label l is to be the next statement to execute.
 * @author Krysten Lawrence
 */

public class JnzInstruction extends Instruction {
	private final String givenLabel;
	private final RegisterName source;

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName source, String givenLabel) {
		super(label, OP_CODE);
		this.givenLabel = givenLabel;
		this.source = source;
	}

	/**
	 * Executes the jump instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter to indicate that
	 * the instruction with the given label is to be executed
	 */
	@Override
	public int execute(Machine machine) {
		int value = machine.getRegisters().get(source);
		if(value != 0){
			return machine.getLabels().getAddress(givenLabel);
		}
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * String representation of the values of the jnzInstruction object
	 * in the form "label opcode result source"
	 *
	 * @return the string representation of the jnz instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source + " " + givenLabel;
	}

	/**
	 * Asserts whether a given object is the same as this object
	 *
	 * @param obj the given object to assert
	 * @return a boolean depending on whether the objects are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof JnzInstruction jnzInstruction) {
			return this.getClass().equals(jnzInstruction.getClass())
					&& this.source.equals(jnzInstruction.source)
					&& this.givenLabel.equals(jnzInstruction.givenLabel);
		}
		return false;
	}

	/**
	 * Returns the hashCode of the object
	 *
	 * @return an int of the given object
	 */
	@Override
	public int hashCode() {
		return Objects.hash(source, givenLabel);
	}
}
