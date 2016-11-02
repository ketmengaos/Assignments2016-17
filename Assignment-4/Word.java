public class Word<K, V extends Comparable> {
	
	private String str;
	private int val;

	public Word(String str, int val) {
		this.str = str;
		this.val = val;
	}

	public String toString() {
		return str + " " + val;	
	}
	
	public int frequency() {
		return val;
	}

	public String word() {
		return str;
	}
}
