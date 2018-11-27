import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class writer {
	FileWriter fileW;
	FileReader fileR;
	Gson gson = new Gson();
	String path = "";
	
	public writer() {
	}

	public void addPath(String path) {
		this.path = path;
	}
	
	public void update(Users user) {
		String jUser = gson.toJson(user);
		try {
			fileW = new FileWriter(path);
			fileW.write(jUser.toString());
			fileW.close();
		} catch (IOException e) {
			System.out.println("error on update W-29");
		}
	}
	
	public Users pull() throws IOException {
		fileR = new FileReader(path);
		String json = "{";
		int i = fileR.read();
		while( (i = fileR.read()) != -1) {  
			json += (char)i;
		}
		fileR.close();
		Users user = gson.fromJson(json, Users.class);
		user.logOutSilent();
		return user;
	}
}

