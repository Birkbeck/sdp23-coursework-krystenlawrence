package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * An instruction subclass that takes the contents of two registers - r1 and r2 - subtracts the
 * contents of r1 by the contents of r2 and stores the result in r1.
 * @author Krysten Lawrence
 */

public class SubInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "sub";

	public SubInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Executes the subtract instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 * or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 * the instruction with the next address is to be executed
	 */
	@Override
	public int execute(Machine machine) {
		int value1 = machine.getRegisters().get(result);
		int value2 = machine.getRegisters().get(source);
		machine.getRegisters().set(result, value1 - value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	/**
	 * String representation of the values of the subInstruction object
	 * in the form "label opcode result source"
	 *
	 * @return the string representation of the sub instruction
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
		if (obj instanceof SubInstruction subInstruction) {
			return this.getClass().equals(subInstruction.getClass())
					&& this.result.equals(subInstruction.result)
					&& this.source.equals(subInstruction.source);
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
