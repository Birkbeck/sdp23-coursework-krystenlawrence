package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * An instruction subclass that takes the values of two registers - r1 and r2 - adds them together
 * and stores the result in the first register - r1.
 * @author Krysten Lawrence
 */

public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "add";

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AddInstruction a) {
			return this.getClass().equals(a.getClass())
					&& this.result.equals(a.result)
					&& this.source.equals(a.source);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(result, source);
	}
}
