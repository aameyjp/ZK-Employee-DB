package Authentication;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class LoginController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	//wire components
	@Wire
	Textbox account;
	@Wire
	Textbox password;
	@Wire
	Label message;
	
	//services
	AuthenticationServiceImpl authService = new AuthenticationServiceImpl();
	
	
	@Listen("onClick=#login; onOK=#loginWin")
	public void doLogin(){
		String nm = account.getValue();
		String pd = password.getValue();
		
		if(!authService.login(nm,pd)){
			message.setValue("account or password are not correct.");
			return;
		}
		
		message.setValue("Welcome, to Zk Employee db");
		message.setSclass("");
		
		Executions.sendRedirect("http://localhost:8080/ZKEmployee/ZKEmpData.zul");
		
	}
}