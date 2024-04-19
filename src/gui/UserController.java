package gui;

import java.util.ArrayList;
import java.util.List;

import core.Category;
import core.HRMDB;
import core.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserController {
	private User user;
	final String[] ROLENAME = {"Admin","Nhân viên","Khách hàng"};
	HRMDB hrmDB = new HRMDB("jdbc:ucanaccess://"+System.getProperty("user.dir").replace("\\", "//")+"/database.accdb","", "");
	@FXML
	private TableView<User> table = new TableView<User>();
	@FXML
	private TextField idTF;
	@FXML
	private TextField usernameTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private TextField emailTF;
	@FXML
	private TextField phoneTF;
	@FXML
	private TextField nameTF;
	@FXML
	private TextField addressTF;
	@FXML
	private ComboBox<String> roleCB;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {
		Platform.runLater(() -> {
			TableColumn<User, Integer> categoryIDColumn = (TableColumn<User, Integer>) table.getVisibleLeafColumn(0);
				categoryIDColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
			TableColumn<User, String> nameColumn = (TableColumn<User, String>) table.getVisibleLeafColumn(1);
				nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
			TableColumn<User, String> userNameColumn = (TableColumn<User,String>) table.getVisibleLeafColumn(2);
				userNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
			TableColumn<User, String> emailColumn = (TableColumn<User,String>) table.getVisibleLeafColumn(3);
				emailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
			TableColumn<User, String> phoneColumn = (TableColumn<User,String>) table.getVisibleLeafColumn(4);
				phoneColumn.setCellValueFactory(new PropertyValueFactory<User,String>("phone"));
			TableColumn<User, String> addressColumn = (TableColumn<User,String>) table.getVisibleLeafColumn(5);
				addressColumn.setCellValueFactory(new PropertyValueFactory<User,String>("address"));
			TableColumn<User, String> roleColumn = (TableColumn<User,String>) table.getVisibleLeafColumn(6);
				roleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("roleString"));
			List<User> userList =new ArrayList<User>();
			int a =0;
			if (getUser().getRole()==1) {
				a=1;
				for (User user : hrmDB.getUserList()) {
					if (user.getRole()!=0) {
						userList.add(user);
					}
				}
			}else {
				userList = hrmDB.getUserList();
			}
			ObservableList<User> obsUserList = FXCollections.observableArrayList(userList);
				table.setItems(obsUserList);
			
			for (int i=a; i < ROLENAME.length; i++) {
				roleCB.getItems().add(ROLENAME[i]);
			}
		});
	}
	public void onClickAddButton() {
		int role = 0;
		for (int i = 0;i<ROLENAME.length;i++) {
			if (ROLENAME[i].equals(roleCB.getSelectionModel().getSelectedItem())) {
				role = i;
			}
		}
		User user = new User(usernameTF.getText(), passwordTF.getText(), emailTF.getText(), nameTF.getText(), phoneTF.getText(), addressTF.getText(), role);
		boolean addResult = hrmDB.addUser(user);
		if (addResult) {
			List<User> userList =hrmDB.getUserList();
			ObservableList<User> obsUserList = FXCollections.observableArrayList(userList);
			table.setItems(obsUserList);
			System.out.println("Add successfull!");
		} else {
			System.out.println("Error add to database!");
		}
	}
	public void onClickUpdateButton() {
		int role = 0;
		for (int i = 0;i<ROLENAME.length;i++) {
			if (ROLENAME[i].equals(roleCB.getSelectionModel().getSelectedItem())) {
				role = i;
			}
		}
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		User selectedUser = table.getItems().get(selectedIndex);
		selectedUser.setName(nameTF.getText());
		selectedUser.setEmail(emailTF.getText());
		selectedUser.setAddress(addressTF.getText());
		selectedUser.setPassword(passwordTF.getText());
		selectedUser.setPhone(phoneTF.getText());
		selectedUser.setUsername(usernameTF.getText());
		selectedUser.setRole(role);
		
		if (selectedIndex >= 0) {
			boolean updateResult = hrmDB.updateUser(selectedUser);
			if (updateResult) {
				table.getItems().set(selectedIndex, selectedUser);
				System.out.println("Update successfull!");
			} else {
				System.out.println("Error update to database!");
			}
		}

	}
	public void onClickDeleteButton() {
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		User user = table.getItems().get(selectedIndex);
		if (selectedIndex >= 0) {
			boolean deleteResult = hrmDB.deleteUser(user.getId());
			if (deleteResult) {
				table.getItems().remove(selectedIndex);
				System.out.println("Delete successfull!");
			} else {
				System.out.println("Error delete to database!");
			}
		}
	}
	
	public void onClickRow() {
		idTF.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getId()));
		nameTF.setText(table.getSelectionModel().getSelectedItem().getName());
		phoneTF.setText(table.getSelectionModel().getSelectedItem().getPhone());
		emailTF.setText(table.getSelectionModel().getSelectedItem().getEmail());
		addressTF.setText(table.getSelectionModel().getSelectedItem().getAddress());
		usernameTF.setText(table.getSelectionModel().getSelectedItem().getUsername());
		roleCB.setValue(table.getSelectionModel().getSelectedItem().getRoleString());
		passwordTF.setText(table.getSelectionModel().getSelectedItem().getPassword());
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return hrmDB.getUser(user);
	}
}
