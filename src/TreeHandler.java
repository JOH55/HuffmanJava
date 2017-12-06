import java.util.*;

public class TreeHandler {

	private PriorityQueue<HuffNode> HuffQ;


	public TreeHandler() {
		this.setHuffQ(new PriorityQueue<HuffNode>());
	}

	/**
	 * @param freq
	 * @return
	 */
	public HuffNode buildTree(int[] freq) {
		for (char i = 0; i < 256; i++)
			if (freq[i] > 0) {
				HuffNode adding = new HuffNode(i, freq[i], null, null);
				this.getHuffQ().add(adding);
			}
		while (this.getHuffQ().size() > 1) {
			HuffNode left = this.getHuffQ().poll();
			HuffNode right = this.getHuffQ().poll();
			HuffNode parent = new HuffNode('\0', left.getFreq() + right.getFreq(), left, right);
			this.getHuffQ().add(parent);
		}
		return this.getHuffQ().poll();

	}

	/**
	 * @return
	 */
	public PriorityQueue<HuffNode> getHuffQ() {
		return HuffQ;
	}

	/**
	 * @param huffQ
	 */
	public void setHuffQ(PriorityQueue<HuffNode> huffQ) {
		HuffQ = huffQ;
	}

}
