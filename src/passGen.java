import java.io.File;
import java.io.IOException;

public class passGen {
	private static mainFrame mf = new mainFrame();
	private Users user;
	public writer wr = new writer();

	public passGen() {

	}

	public void newUser(String username, String pw) throws IOException {
		wr.addPath(username + ".json");
		Users user = new Users(username, pw);
		File file = new File(username + ".json");
		file.createNewFile();
		wr.update(user);

	}

	public boolean login(String username, String pw) throws IOException {
		wr.addPath(username + ".json");
		user = wr.pull();
		if (user.getUser().equals(username)) {
			if (user.logIn(pw)) {
				mf.close();
				return true;
			}
				return false;
		} 
			return false;
	}

	public void logOut() {
		user.logOutSilent();
	}

	public void errorlogging() {
		mf.msg("Wrong User or password. Try again");
	}

	public Users user() {
		return user;
	}

	public void loginScreen() {
		mf.open();
	}

	public static void main(String[] args) throws IOException {
		mf.start();
	}

}
