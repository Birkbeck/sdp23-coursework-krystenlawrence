package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

class OutInstructionTest {

  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    System.setOut(new PrintStream(out));
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
    System.setOut(originalOut);
  }

  @Test
  void executeValid() {
    registers.set(EAX, 10);
    Instruction instruction = new OutInstruction(null, EAX);
    instruction.execute(machine);
    Assertions.assertEquals("10\r\n", out.toString());
  }

  @Test
  void assertObjectsTrue() {
    Instruction instruction = new OutInstruction(null, EAX);
    Instruction secondInstruction = new OutInstruction(null, EAX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertTrue(isEquals);
  }

  @Test
  void assertObjectsFalse() {
    Instruction instruction = new OutInstruction(null, EAX);
    Instruction secondInstruction = new OutInstruction(null, EBX);
    boolean isEquals = instruction.equals(secondInstruction);
    Assertions.assertFalse(isEquals);
  }

}
