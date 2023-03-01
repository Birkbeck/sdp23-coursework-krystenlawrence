package sml.instructionFactories;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.OutInstruction;

/**
 * A factory interface for the creation of an OutInstruction
 * @author Krysten Lawrence
 */
public class OutInstructionFactory implements InstructionFactory {

    @Override
    public Instruction createInstruction(String label, String[] arguments) {
        return new OutInstruction(label, Registers.Register.valueOf(arguments[0]));
    }
}
