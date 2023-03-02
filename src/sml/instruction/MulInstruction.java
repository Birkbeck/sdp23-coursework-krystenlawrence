package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * An instruction subclass that takes the values of two registers - r1 and r2 - multiplies them and
 * stores the result in the first register - r1.
 * @author Krysten Lawrence
 */

public class MulInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "mul";

	public MulInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Executes the mulitply instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 * the instruction with the next address is to be executed
	 */
	@Override
	public int execute(Machine machine) {
		int value1 = machine.getRegisters().get(result);
		int value2 = machine.getRegisters().get(source);
		machine.getRegisters().set(result, value1 * value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * String representation of the values of the mulInstruction object
	 * in the form "label opcode result source"
	 *
	 * @return the string representation of the mul instruction
	 */
	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}

	/**
	 * Asserts whether a given object is the same as this object
	 *
	 * @param obj the given object to assert
	 * @return a boolean depending on whether the objects are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MulInstruction mulInstruction) {
			return this.getClass().equals(mulInstruction.getClass())
					&& this.result.equals(mulInstruction.result)
					&& this.source.equals(mulInstruction.source);
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
		return Objects.hash(result, source);
	}
}
