package gui;

import java.io.IOException;

import core.HRMDB;
import core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;

public class LoginController {
	@FXML
	private TextField userNameTF;
	@FXML
	private PasswordField passwordTF;
	@FXML
	private Label invalidLogin;
	@FXML
	private Button loginButton;
	private AuthenticationService authService = new AuthenticationServiceImpl();
	String path = "jdbc:ucanaccess://"+System.getProperty("user.dir").replace("\\", "/")+"/database.accdb";
	HRMDB hrm = new HRMDB(path,"", "");
	public void onClickLogin(ActionEvent event) {
		if (validateForm()) {
			User user = new User(userNameTF.getText(), passwordTF.getText());
			if (authService.login(user)) {
				((Node)(event.getSource())).getScene().getWindow().hide();
				showHomeGUI(user);
			} else { 
				invalidLogin.setText("Username hoặc password không đúng!");
			}
		}
	}
	
	public boolean validateForm() {
		boolean result = true;
		resetMessage();
		if ("".equals(userNameTF.getText()) || "".equals(passwordTF.getText())) {
			invalidLogin.setText("Username và password không được để trống!");
			result = false;
		}
		return result;
	}
	
	public void resetMessage() {
		invalidLogin.setText("");
	}
	
	public void showHomeGUI(User user)  {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
			Parent root = (Parent)fxmlLoader.load();			
			DashboardController controller = fxmlLoader.getController();
			controller.setUser(hrm.getUser(user));
			Stage homeStage = new Stage();
			homeStage.setTitle("Home");
			homeStage.setScene(new Scene(root));
			homeStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                
	}
}
