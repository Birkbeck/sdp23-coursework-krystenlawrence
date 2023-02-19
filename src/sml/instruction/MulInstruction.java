package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * An instruction subclass that takes the values of two registers - r1 and r2 - multiplies them and stores the
 * result in the first register - r1.
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

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 * value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MulInstruction m) {
			return this.getClass().equals(m.getClass())
					&& this.result.equals(m.result)
					&& this.source.equals(m.source);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(result, source);
	}
}
