import java.io.IOException;

public class Compress {

	private static String[] st = new String[256];
	private HuffNode root;
	private FileHandler compress;
	private TreeHandler HuffTree;
	private float uncompSize;
	private float compSize;
	private float compRatio;
	private float treeHeight;
	private float numNodes;
	private float avgDepth;

	public Compress() throws IOException {
		compress = new FileHandler();
		HuffTree = new TreeHandler();
		root  = HuffTree.buildTree(compress.getAscii());
		this.buildCode(root, "");
		this.getSize(root, this);
		this.setUncompSize(compress.getUncompSize());
		this.setCompRatio(compress.getUncompSize()/this.getCompSize());
		this.setTreeHeight(this.treeHeight(root)-1);
		this.setNumNodes(1);
		this.countNodes(root);
		this.setAvgDepth(this.avgHNode(root, 0)/this.getNumNodes());
	}
	
	/**
	 * 
	 */
	public void printStats(){
		System.out.println("The file size is " + this.getUncompSize() + " Bits");
		System.out.println("The compressed file size  is " + this.getCompSize() + " Bits");
		System.out.println("The compression ratio is :" + this.getCompRatio() );
		System.out.println("The hieght of the tree is :" + this.getTreeHeight());
		System.out.println("The tree has " + this.getNumNodes() + " nodes.");
		System.out.println("The average depth is :" + this.getAvgDepth());
	}
	
	/**
	 * @param node
	 * @param i
	 * @return
	 */
	public int avgHNode(HuffNode node, int i) {
		if (node == null) {
			return 0;
		} else {
			return i + avgHNode(node.getLeft(), i + 1) + avgHNode(node.getRight(), i + 1);
		}
	}
	
	/**
	 * @param node
	 */
	public void countNodes(HuffNode node) {
		if (!node.isLeaf()) {
			this.numNodes += 1;
			countNodes(node.getLeft());
			this.numNodes += 1;
			countNodes(node.getRight());
		}

	}

	/**
	 * @param n
	 * @return
	 */
	public int treeHeight(HuffNode n) {
		if (n == null) {
			return 0;
		} else {
			return 1 + Math.max(treeHeight(n.getLeft()), treeHeight(n.getRight()));
		}

	}

	/**
	 * @param node
	 * @param s
	 */
	public void getSize(HuffNode node, Compress s) {
		if (!node.isLeaf()) {
			getSize(node.getLeft(), s);
			getSize(node.getRight(), s);
		}
		if (st[node.getCh()] != null) {
			s.compSize += ((node.getBitpattern().length()) * node.getFreq());
		}

	}

	/**
	 * @param node
	 * @param s
	 */
	public void buildCode(HuffNode node, String s) {
		if (!node.isLeaf()) {
			buildCode(node.getLeft(), s + '0');
			buildCode(node.getRight(), s + '1');
		} else {
			st[node.getCh()] = s;
		}
		if (st[node.getCh()] != null) {
			node.setBitpattern(String.valueOf(st[node.getCh()]));
		}

	}

	/**
	 * @return
	 */
	public HuffNode getRoot() {
		return root;
	}

	/**
	 * @param root
	 */
	public void setRoot(HuffNode root) {
		this.root = root;
	}

	/**
	 * @return
	 */
	public float getCompRatio() {
		return compRatio;
	}

	/**
	 * @param compRatio
	 */
	public void setCompRatio(float compRatio) {
		this.compRatio = compRatio;
	}

	/**
	 * @return
	 */
	public float getTreeHeight() {
		return treeHeight;
	}

	/**
	 * @param treeHeight
	 */
	public void setTreeHeight(int treeHeight) {
		this.treeHeight = treeHeight;
	}

	/**
	 * @return
	 */
	public float getUncompSize() {
		return uncompSize;
	}

	/**
	 * @param uncompSize
	 */
	public void setUncompSize(long uncompSize) {
		this.uncompSize = uncompSize;
	}

	/**
	 * @return
	 */
	public float getCompSize() {
		return compSize;
	}

	/**
	 * @param compSize
	 */
	public void setCompSize(long compSize) {
		this.compSize = compSize;
	}

	/**
	 * @return
	 */
	public float getNumNodes() {
		return numNodes;
	}

	/**
	 * @param numNodes
	 */
	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}

	/**
	 * @return
	 */
	public float getAvgDepth() {
		return avgDepth;
	}

	/**
	 * @param f
	 */
	public void setAvgDepth(float f) {
		this.avgDepth = f;
	}

}
