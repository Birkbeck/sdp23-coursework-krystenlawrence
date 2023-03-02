package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * An instruction subclass that stores the value of an integer and stores in a particular register
 * @author Krysten Lawrence
 */

public class MovInstruction extends Instruction {
	private final RegisterName result;
	private final int integer;

	public static final String OP_CODE = "mov";

	public MovInstruction(String label, RegisterName result, int integer) {
		super(label, OP_CODE);
		this.integer = integer;
		this.result = result;
	}

	/**
	 * Executes the move instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 * the instruction with the next address is to be executed
	 */
	@Override
	public int execute(Machine machine) {
		machine.getRegisters().set(result, integer);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * String representation of the values of the movInstruction object
	 * in the form "label opcode result source"
	 *
	 * @return the string representation of the jnz instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + integer;
	}

	/**
	 * Asserts whether a given object is the same as this object
	 *
	 * @param obj the given object to assert
	 * @return a boolean depending on whether the objects are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MovInstruction movInstruction) {
			return this.getClass().equals(movInstruction.getClass())
					&& this.result.equals(movInstruction.result)
					&& this.integer == movInstruction.integer;
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
		return Objects.hash(result, integer);
	}
}
