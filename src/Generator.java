import java.util.*;
import java.util.ArrayList;
public class Generator {
	private boolean upper;
	private boolean special;
	private boolean nums;
	private boolean minimums = false;
	private int length;
	private int minNums = 0, minSpec = 0, minUp = 0;
	final String numbs = "123456789"; //0
	final String specials = "`~!@#$%^&*()-=_+<>?/\""; //1
	final String uppers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //2
	final String lowers = "abcdefghijklmnopqrstuvyxwz"; //3
	
	/**
	 * creates a generator with default settings for a strong password
	 */
	public Generator() {
		upper = true;
		special = true;
		length = 10;
		nums = true;
	}

	/**
	 * Creates a generator with pre-defined settings
	 * @param upper If uppercase letters are desired
	 * @param special If special characters are to be used
	 * @param length The length of the password
	 */
	public Generator(boolean upper, boolean special,boolean nums, int length) {
		this.upper = upper;
		this.special = special;
		this.length = length;
		this.nums = nums;
	}
	
	/**
	 * creates a generator that only factors in length
	 * @param length the length of the desired password 
	 */
	public Generator(int length) {
		this.length = length;
		upper = true;
		special = true;
		nums = true;
	}
	
	public Generator(boolean upper, boolean special,boolean nums, int length, int minUp, int minNums, int minSpec) {
		this.upper = upper;
		this.special = special;
		this.length = length;
		this.nums = nums; //minNums, minSpec, minUp, minLo;
		minimums = true;
		this.minNums = minNums;
		this.minSpec = minSpec;
		this.minUp = minUp;
		if(minUp + minNums + minSpec > length) {
			throw new IndexOutOfBoundsException("too many requiremnts, lowers the requirements or increase the length");
		}
	}
	
	public String generate() {
		String password = "";
		Random r = new Random();
		for(int i = 0; i < length; i++){
			int	selecter = r.nextInt(4);
		
			if(selecter == 0 && nums) {
				password += numbs.charAt(r.nextInt(numbs.length()));
			}
			else if(selecter == 1 && special) {
				password += specials.charAt(r.nextInt(specials.length()));
			}
			else if(selecter == 2 && upper) {
				password += uppers.charAt(r.nextInt(uppers.length()));
			}
			else {
				password += lowers.charAt(r.nextInt(lowers.length()));
			}
		}
		if(minimums) {
			password = alter(password);
		}
		return password;		
	}

	private String alter(String password) {
		Random r = new Random();
		int minPos = minUp + minNums + minSpec;
		ArrayList<Integer> uNums = uniqueNums(minPos, password.length());
		int i = 0;
		if(i < uNums.size()){
			while(minUp != 0){
				password = swapChar(uNums.get(i),uppers.charAt(r.nextInt(uppers.length())), password);
				minUp --;	
				i++;
			}
			while(minNums != 0){
				password = swapChar(uNums.get(i),numbs.charAt(r.nextInt(numbs.length())), password);
				minNums --;	
				i++;
			}
			while(minSpec != 0){
				password = swapChar(uNums.get(i),specials.charAt(r.nextInt(specials.length())), password);
				minSpec --;	
				i++;
			}
		}
		return password;
	}
	
	private ArrayList<Integer> uniqueNums(int amount,int max){
		Random r = new Random();
		ArrayList<Integer> uNums = new ArrayList<Integer>();
		for(int i = 0; i < amount; i++) {
			int newNum = r.nextInt(max);
			while(uNums.contains(newNum)) {
				newNum = r.nextInt(max);
			}
			uNums.add(newNum);
		}
		return uNums;
	}
	
	public String swapChar(int position, char ch, String str){
	    char[] charArray = str.toCharArray();
	    charArray[position] = ch;
	    return new String(charArray);
	}
}
