package Authentication;


//package org.zkoss.essentials.chapter7;

//import org.zkoss.essentials.chapter3.AuthenticationServiceChapter3Impl;
//import org.zkoss.essentials.chapter3.UserInfoServiceChapter3Impl;
//import org.zkoss.essentials.entity.User;
//import org.zkoss.essentials.services.UserCredential;
//import org.zkoss.essentials.services.UserInfoService;

import Authentication.UserInfoServiceImpl;
import Authentication.LoginController;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

public class AuthenticationServiceImpl {
	
private static final long serialVersionUID = 1L;

UserInfoServiceImpl userInfoService = new UserInfoServiceImpl();

public boolean login(String nm, String pd) {
	User user = userInfoService.findUser(nm, pd);
	//a simple plan text password verification
	if(user==null){
		return false;
	}
		
	//TODO handle the role here for authorization
	return true;
}

public void logout() {
	Session sess = Sessions.getCurrent();
	sess.removeAttribute("userCredential");
}
}