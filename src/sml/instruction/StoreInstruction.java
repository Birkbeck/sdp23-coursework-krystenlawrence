package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * An instruction subclass that stores the value of an integer and stores in a particular register
 * @author
 */

public class StoreInstruction extends Instruction {
	private final RegisterName result;
	private final int integer;

	public static final String OP_CODE = "mov";

	public StoreInstruction(String label, RegisterName result, int integer) {
		super(label, OP_CODE);
		this.result = result;
		this.integer = integer;
	}

	@Override
	public int execute(Machine m) {
		m.getRegisters().set(result, integer);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + integer;
	}
}
