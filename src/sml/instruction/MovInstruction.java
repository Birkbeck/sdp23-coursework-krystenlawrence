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

	@Override
	public int execute(Machine m) {
		m.getRegisters().set(result, integer);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + integer;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MovInstruction m) {
			return this.getClass().equals(m.getClass())
					&& this.result.equals(m.result)
					&& this.integer == m.integer;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(result, integer);
	}
}
