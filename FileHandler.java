import java.io.*;
import java.util.*;
	
public class FileHandler {

	private int[] ascii = new int[256];
	private long uncompSize;
	private String inputFile;

	public FileHandler() throws IOException {
		this.setInputFile(fileIn());
		this.setAscii(readFile(this.getInputFile()));
		this.setUncompSize(inputFile.length() * fileSize());
	}

	/**
	 * @return
	 */
	public int[] getAscii() {
		return ascii;
	}

	/**
	 * @return
	 */
	public String getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile
	 */
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * @param ascii
	 */
	public void setAscii(int[] ascii) {
		this.ascii = ascii;
	}

	/**
	 * @return
	 */
	public long getUncompSize() {
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
	 * @throws IOException
	 */
	public String fileIn() throws IOException {

		System.out.println("What is the name of the file you would like to process");
		Scanner in = new Scanner(System.in);
		String fileName = in.next();
		in.close();

		InputStream is = new FileInputStream(fileName);
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			sb.append(line).append("\n");
			line = buf.readLine();
		}
		sb.deleteCharAt(sb.length() - 1);
		String fileAsString = sb.toString();
		buf.close();
		return fileAsString;
	}

	/**
	 * @param fileIN
	 * @return
	 * @throws FileNotFoundException
	 */
	public int[] readFile(String fileIN) throws FileNotFoundException {
		Arrays.fill(this.ascii, 0);
		for (char c : fileIN.toCharArray()) {
			this.ascii[(int) c]++;
		}
		return this.ascii;
	}

	/**
	 * @return
	 */
	public int fileSize() {
		int fbit= 0;
		for (long l : this.ascii) {
			if (l > 0) {
				fbit++;
			}
		}
		double power = Math.log(fbit) / Math.log(2);
		int fixedBitLen = (int) power;
		if (fixedBitLen < power) {
			fixedBitLen++;
		}
		return fixedBitLen;
	}

}
