package sml.instructionFactories;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.MovInstruction;

/**
 * A factory interface for the creation of an MovInstruction
 * @author Krysten Lawrence
 */
public class MovInstructionFactory implements InstructionFactory {

    @Override
    public Instruction createInstruction(String label, String[] arguments) {
        return new MovInstruction(label,
                Registers.Register.valueOf(arguments[0]),
                Integer.parseInt(arguments[1]));
    }
}
