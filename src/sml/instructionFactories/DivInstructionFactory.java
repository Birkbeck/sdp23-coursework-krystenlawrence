package sml.instructionFactories;

import sml.Instruction;
import sml.InstructionFactory;
import sml.Registers;
import sml.instruction.DivInstruction;

/**
 * A factory interface for the creation of an DivInstruction
 * @author Krysten Lawrence
 */
public class DivInstructionFactory implements InstructionFactory {

    @Override
    public Instruction createInstruction(String label, String[] arguments) {
        return new DivInstruction(label,
                Registers.Register.valueOf(arguments[0]),
                Registers.Register.valueOf(arguments[1]));
    }
}
