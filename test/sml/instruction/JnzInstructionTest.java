package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class JnzInstructionTest {
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
    machine.getLabels().addLabel("f3",0);
    registers.set(EAX, 5);
    Instruction jnzInstruction = new JnzInstruction(null, "f3", EAX);
    jnzInstruction.execute(machine);
    Assertions.assertEquals(5, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    machine.getLabels().addLabel("f3",0);
    registers.set(EAX, 0);
    Instruction jnzInstruction = new JnzInstruction(null, "f3", EAX);
    jnzInstruction.execute(machine);
    Assertions.assertEquals(0, machine.getRegisters().get(EAX));
  }

  @Test
  void assertObjectsTrue() {
    Instruction instruction = new JnzInstruction(null, "ab", EBX);
    Instruction secondInstruction = new JnzInstruction(null, "ab", EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertTrue(isEquals);
  }

  @Test
  void assertObjectsFalse() {
    Instruction instruction = new JnzInstruction(null, "ab", EBX);
    Instruction secondInstruction = new JnzInstruction(null, "ba", EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertFalse(isEquals);
  }
}
