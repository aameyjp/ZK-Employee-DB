package Authentication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfoServiceImpl implements Serializable{

	private static final long serialVersionUID = 1L;


	static protected List<User> userList = new ArrayList<User>();  

	static{
		userList.add(new User("anonymous","1234"));
		userList.add(new User("admin","1234"));
		userList.add(new User("zkoss","1234"));
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized User findUser(String name, String password){
		int s = userList.size();
		for(int i=0;i<s;i++){
			User u = userList.get(i);
			if(u.password.equals(password) && u.username.equals(name)) {
				return u;
			}
		}
		return null;
	}


}