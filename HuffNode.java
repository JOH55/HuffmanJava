
public class HuffNode implements Comparable<HuffNode> {

	private char ch;
	private int freq;
	private HuffNode left;
	private HuffNode right;
	private String bitpattern;

	/**
	 * @param ch
	 * @param freq
	 * @param left
	 * @param right
	 */
	public HuffNode(char ch, int freq, HuffNode left, HuffNode right) {
		this.ch = ch;
		this.freq = freq;
		this.left = left;
		this.right = right;
		this.bitpattern = null;
	}

	/**
	 * @return
	 */
	public String getBitpattern() {
		return this.bitpattern;
	}

	/**
	 * @param bitpattern
	 */
	public void setBitpattern(String bitpattern) {
		this.bitpattern = bitpattern;
	}

	/**
	 * @return
	 */
	public char getCh() {
		return ch;
	}

	/**
	 * @return
	 */
	public int getFreq() {
		return freq;
	}

	/**
	 * @return
	 */
	public HuffNode getRight() {
		return right;
	}

	/**
	 * @return
	 */
	public HuffNode getLeft() {
		return left;
	}

	/**
	 * 
	 */
	public void setLeftNull() {
		this.left = null;
	}

	/**
	 * 
	 */
	public void setRightNull() {
		this.right = null;
	}

	/**
	 * @return
	 */
	public boolean isLeaf() {
		assert ((left == null) && (right == null)) || ((left != null) && (right != null));
		return (left == null) && (right == null);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(HuffNode that) {
		return this.freq - that.freq;
	}

	/**
	 * @param n
	 * @param dashes
	 */
	public static void drawTree(HuffNode n, String dashes) {
		if ((int) n.getCh() > 0) {
			if (n.getCh() == ' ') {
				System.out.println(dashes + n.getFreq() + ":" + "Space");
			} else {
				if (n.getCh() == '\n') {
					System.out.println(dashes + n.getFreq() + ":" + "Cartridge Return");
				} else {
					System.out.println(dashes + n.getFreq() + ":" + n.getCh());
				}
			}
		} else {
			System.out.println(dashes + n.getFreq());
		}
		if (n.left != null) {
			drawTree(n.getLeft(), dashes + "-");
		}
		if (n.right != null) {
			drawTree(n.getRight(), dashes + "-");
		}
	}

}
