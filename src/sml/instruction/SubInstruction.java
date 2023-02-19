package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * An instruction subclass that takes the contents of two registers - r1 and r2 - subtracts the contents of r1 by the
 * contents of r2 and stores the result in r1.
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

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 - value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof SubInstruction s) {
			return this.getClass().equals(s.getClass())
					&& this.result.equals(s.result)
					&& this.source.equals(s.source);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(result, source);
	}
}
