package sml.instructionFactories;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.MulInstruction;

/**
 * A factory interface for the creation of an MulInstruction
 * @author Krysten Lawrence
 */
public class MulInstructionFactory implements InstructionFactory {

    @Override
    public Instruction createInstruction(String label, String[] arguments) {
        return new MulInstruction(label,
                Registers.Register.valueOf(arguments[0]),
                Registers.Register.valueOf(arguments[1]));
    }
}
