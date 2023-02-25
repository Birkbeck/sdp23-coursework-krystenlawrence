package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class MovInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    Instruction instruction = new MovInstruction(null, EAX, 7);
    instruction.execute(machine);
    Assertions.assertEquals(7, machine.getRegisters().get(EAX));
  }

  @Test
  void assertObjectsTrue() {
    Instruction instruction = new MovInstruction(null, 4, EBX);
    Instruction secondInstruction = new MovInstruction(null, 4, EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertTrue(isEquals);
  }

  @Test
  void assertObjectsFalse() {
    Instruction instruction = new MovInstruction(null, 4, EBX);
    Instruction secondInstruction = new MovInstruction(null, 5, EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertFalse(isEquals);
  }

}
