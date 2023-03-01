package sml.instructionFactories;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.SubInstruction;

/**
 * A factory interface for the creation of an SubInstruction
 * @author Krysten Lawrence
 */
public class SubInstructionFactory implements InstructionFactory {

    @Override
    public Instruction createInstruction(String label, String[] arguments) {
        return new SubInstruction(label,
                Registers.Register.valueOf(arguments[0]),
                Registers.Register.valueOf(arguments[1]));
    }
}
