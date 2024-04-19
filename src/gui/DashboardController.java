package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import core.Category;
import core.HRMDB;
import core.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.ucanaccess.commands.DeleteCommand;

public class DashboardController {

	@FXML
	private BorderPane bp;
	@FXML
	private AnchorPane ap;
	@FXML
	private Label usernameTF;
	@FXML
	private Label roleTF;
	private User user;
	public void initialize(URL url, ResourceBundle rb) {  

	} 
	@FXML
	public void initialize() {  
		Platform.runLater(() -> {
			System.out.println(getUser());
			usernameTF.setText(" Username: "+getUser().getUsername());
			roleTF.setText(" Vai trò: "+getUser().getRoleString());
		});
	}     
	@FXML    
	private void homeClick(MouseEvent event) {        
			bp.setCenter(ap);    
	}     
	@FXML    
	private void categoryClick(MouseEvent event) {  
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Category.fxml"));
		Parent root;
		try {
			root = (Parent)fxmlLoader.load();
			bp.setCenter(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	
	} 
	@FXML
	private void logoutClick(MouseEvent event) {  
		((Node)(event.getSource())).getScene().getWindow().hide();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Stage primaryStage = new Stage();
			primaryStage.setTitle("Quản lý Laptop");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@FXML    
	private void userClick(MouseEvent event) { 
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("User.fxml"));
			Parent root = (Parent)fxmlLoader.load();			
			UserController controller = fxmlLoader.getController();
			controller.setUser(user);
			bp.setCenter(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}     
	@FXML    
	private void productClick(MouseEvent event) {        
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Product.fxml"));
			Parent root = (Parent)fxmlLoader.load();
			ProductController controller = fxmlLoader.getController();
			controller.setUser(user);
			bp.setCenter(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}     
	
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
}
	
