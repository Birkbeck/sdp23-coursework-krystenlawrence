package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * An instruction subclass that checks the contents of a register r. If the contents are not equal to 0, then
 * statement of the given label l is to be the next statement to execute.
 * @author
 */

public class JnzInstruction extends Instruction {
	private final String givenLabel;
	private final RegisterName source;

	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, String givenLabel, RegisterName source) {
		super(label, OP_CODE);
		this.givenLabel = givenLabel;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value = m.getRegisters().get(source);
		if(value != 0){
			Labels labels = new Labels();
			return labels.getAddress(givenLabel);
		}
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + source + " " + givenLabel;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof JnzInstruction j) {
			return this.getClass().equals(j.getClass())
					&& this.source.equals(j.source)
					&& this.givenLabel.equals(j.givenLabel);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(source, givenLabel);
	}
}
