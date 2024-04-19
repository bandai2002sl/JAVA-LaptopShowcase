package service;
import core.HRMDB;
import core.User;

public class AuthenticationServiceImpl implements AuthenticationService{
	@Override
	public boolean login(User user) {
		String path = "jdbc:ucanaccess://"+System.getProperty("user.dir").replace("\\", "/")+"/database.accdb";
		HRMDB hrm = new HRMDB(path,"", "");
		if (hrm.checkHR(user)) {
			if(hrm.getUser(user).getRole()==2) {
				return false;
			}
			return true;
		}
		return false;
	}
}