import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Users {
	private String name;
	private String password;
	private boolean loggedIn;
	private HashMap<String,String> pswds = new HashMap<String,String>();
	private int code;
	private Scrambler scram = new Scrambler();
	
	public Users(String name, String password) {
		Random r = new Random();
		code = r.nextInt(32);
		this.name = scram.scramble(name, code);
		this.password = scram.scramble(password, code); 
	}
	
	public boolean logIn(String password) {
		if(loggedIn) {
			System.out.println("You are already logged in as: " + name);
		}
		if(this.password.equals(scram.scramble(password, code)) && !loggedIn) {
			loggedIn = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void logOut() {
		if(loggedIn) {
			loggedIn = false;
			System.out.println("you have logged out");
		}
		else {
			System.out.println("you are not logged in");
		}
	}
	
	public void logOutSilent() {
		if(loggedIn) {
			loggedIn = false;
		}
	}
	
	public String Generate() {
		if(!loggedIn) {
			System.out.println("you are not log in please log in");
			return null;
		}
		return new Generator().generate();
	}
	
	public String Generate(int length) {
		if(!loggedIn) {
			System.out.println("you are not log in please log in");
			return null;
		}
		return new Generator(length).generate();
	}
	
	public String Generate(boolean upper, boolean special,boolean nums, int length) {
		if(!loggedIn) {
			System.out.println("you are not log in please log in");
			return null;
		}
		return new Generator(upper, special, nums, length).generate();
	}
	
	public String Generate(boolean upper, boolean special,boolean nums, int length, int minUp, int minNums, int minSpec) {
		if(!loggedIn) {
			System.out.println("you are not log in please log in");
			return null;
		}
		return new Generator(upper, special, nums, length, minUp, minNums, minSpec).generate();
	}
	
	public String retrieve(String service) {
		if(loggedIn) {
			String pw = (String) pswds.get(service);
			if(pw != null) {
				return scram.read(pw, code);
			}
			else{
				return null;
			}
		}else {
			System.out.println("you are not logged in");
			return null;
		}
		
	}
	
	public void store(String service, String password) throws IOException {
		if(loggedIn) {
			if(pswds.containsKey(service)) {
				throw new IOException("Service already exists");
			}
			else {
				Scrambler scram = new Scrambler();
				pswds.put(service, scram.scramble(password, code));
			}
		}else {
			System.out.println("you are not logged in");
		}
	}
	
	public String getUser() {
		return scram.read(name, code);
		}
	public String getpw() {
		return password;
		}
	public boolean getState() {
		return loggedIn;
		}
	public HashMap<String, String> getPws(){
		return pswds;
	}
	
	public void save(writer wr) {
		if(wr != null) {
			wr.update(this);
		}else {
			System.out.println("WR is null U-130");
		}
	}
	
	public void remove(String service) {
		System.out.println(pswds.size());
		pswds.remove(service);
		System.out.println(pswds.size());
	}
}