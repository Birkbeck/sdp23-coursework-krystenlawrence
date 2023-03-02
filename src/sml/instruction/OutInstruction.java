package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * An instruction subclass that prints the contents of the given register to the console
 * @author Krysten Lawrence
 */

public class OutInstruction extends Instruction {

	private final RegisterName source;

	public static final String OP_CODE = "out";

	public OutInstruction(String label, RegisterName source) {
		super(label, OP_CODE);
		this.source = source;
	}

	/**
	 * Executes the print out instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 * the instruction with the next address is to be executed
	 */
	@Override
	public int execute(Machine machine) {
		int value = machine.getRegisters().get(source);
		System.out.println(value);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * String representation of the values of the outInstruction object
	 * in the form "label opcode result source"
	 *
	 * @return the string representation of the out instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source;
	}

	/**
	 * Asserts whether a given object is the same as this object
	 *
	 * @param obj the given object to assert
	 * @return a boolean depending on whether the objects are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OutInstruction outInstruction) {
			return this.getClass().equals(outInstruction.getClass())
					&& this.source.equals(outInstruction.source);
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
		return Objects.hash(source);
	}
}
