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

class DivInstructionTest {
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
    registers.set(EAX, 20);
    registers.set(EBX, 5);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(4, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, 18);
    registers.set(EBX, 5);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(3, machine.getRegisters().get(EAX));
  }

  @Test
  void assertObjectsTrue() {
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    Instruction secondInstruction = new AddInstruction(null, EAX, EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertTrue(isEquals);
  }

  @Test
  void assertObjectsFalse() {
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    Instruction secondInstruction = new AddInstruction(null, EBX, EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertFalse(isEquals);
  }
}
