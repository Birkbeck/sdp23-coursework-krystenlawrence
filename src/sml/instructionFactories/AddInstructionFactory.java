package sml.instructionFactories;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.AddInstruction;

/**
 * A factory interface for the creation of an AddInstruction
 * @author Krysten Lawrence
 */
public class AddInstructionFactory implements InstructionFactory {

    @Override
    public Instruction createInstruction(String label, String[] arguments) {
        return new AddInstruction(label,
                Registers.Register.valueOf(arguments[0]),
                Registers.Register.valueOf(arguments[1]));
    }
}
