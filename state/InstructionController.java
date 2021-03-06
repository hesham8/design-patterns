import java.util.Random;

/*
 * A developer-friendly wrapper of an instruction.
 * Allows generation of the next instruction state, provides access to the instruction instance,
 * gets the status of the current instruction, and can define a "reversed" state to allow for
 * reverse input images.
 *
 *
 *
 * @author heshamsalman
 */
public class InstructionController {
	private static Instruction instr;
	private static final InstructionController instance = new InstructionController();
	boolean reverse = false;
	Random r = new Random();

	private InstructionController() {
		InstructionStatus p = InstructionStatus.getRandomStatus();
		instr = new Instruction(p);
	}

	public static synchronized InstructionController getInstance() {
		return instance;
	}

	public Instruction getInstr() {
		return instr;
	}

	public InstructionStatus getStatus() {
		return instr.getStatus();
	}

	public boolean isReversed() {
		reverse = r.nextBoolean();
		return reverse;
	}

	/*
	 * Generates next instruction by switching the instruction state.
	 * Currently does not support getting the same instruction twice.
	 */
	public void nextInstruction() {
		InstructionStatus p = InstructionStatus.getRandomStatus();
		while (p == instr.getStatus()) {
			p = InstructionStatus.getRandomStatus();
		}
		instr.setStatus(p);
		reverse = r.nextBoolean();
	}
}
