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

	@Override
	public int execute(Machine m) {
		int value = m.getRegisters().get(source);
		System.out.println(value);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof OutInstruction out) {
			return this.getClass().equals(out.getClass())
					&& this.source.equals(out.source);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(source);
	}
}
