package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;
import static sml.Registers.Register.ECX;

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
  void executeValid() throws IOException {
    machine.getLabels().addLabel("f3",0);
    registers.set(EAX, 5);
    Instruction jnzInstruction = new JnzInstruction(null, "f3", EAX);
    jnzInstruction.execute(machine);
    Assertions.assertEquals(5, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() throws IOException {
    machine.getLabels().addLabel("f3",0);
    registers.set(EAX, 0);
    Instruction jnzInstruction = new JnzInstruction(null, "f3", EAX);
    jnzInstruction.execute(machine);
    Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
  }

  @Test
  void assertObjectsTrue() {
    registers.set(EAX, 6);
    registers.set(EBX, 10);
    Instruction instruction = new JnzInstruction(null, "ab", EBX);
    Instruction secondInstruction = new JnzInstruction(null, "ab", EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertTrue(isEquals);
  }

  @Test
  void assertObjectsFalse() {
    registers.set(EAX, 2);
    registers.set(EBX, 10);
    Instruction instruction = new JnzInstruction(null, "ab", EBX);
    Instruction secondInstruction = new JnzInstruction(null, "ba", EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertFalse(isEquals);
  }
}
