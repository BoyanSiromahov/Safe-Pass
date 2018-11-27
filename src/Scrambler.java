public class Scrambler {

	public Scrambler() {
		
	}
	
	public String read(String pw, int code) {
		String npw = "";
		char [] chars = pw.toCharArray();
		for(char letter: chars) {
			npw += (char) ((int) letter + code);
		}
		return npw; 
	}
	
	public String scramble(String pw, int code) {
		String npw = "";
		char [] chars = pw.toCharArray();
		for(char letter: chars) {
			npw += (char) ((int) letter - code);
		}
		return npw;
	}
}
