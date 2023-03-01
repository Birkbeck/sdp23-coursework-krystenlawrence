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

class MulInstructionTest {
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
    registers.set(EAX, 8);
    registers.set(EBX, 4);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(32, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -6);
    registers.set(EBX, 7);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-42, machine.getRegisters().get(EAX));
  }

  @Test
  void assertObjectsTrue() {
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    Instruction secondInstruction = new MulInstruction(null, EAX, EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertTrue(isEquals);
  }

  @Test
  void assertObjectsFalse() {
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    Instruction secondInstruction = new MulInstruction(null, EBX, EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertFalse(isEquals);
  }
}
