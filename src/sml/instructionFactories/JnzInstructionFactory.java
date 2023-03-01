package sml.instructionFactories;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.JnzInstruction;

/**
 * A factory interface for the creation of an AddInstruction
 * @author Krysten Lawrence
 */
public class JnzInstructionFactory implements InstructionFactory {

    @Override
    public Instruction createInstruction(String label, String[] arguments) {
        return new JnzInstruction(label,
                Registers.Register.valueOf(arguments[0]),
                arguments[1]);
    }
}
