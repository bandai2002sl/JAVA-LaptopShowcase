package gui;

import java.util.ArrayList;
import java.util.List;

import core.Category;
import core.HRMDB;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoryController {
	@FXML
	private TableView<Category> table = new TableView<Category>();
	HRMDB hrmDB = new HRMDB("jdbc:ucanaccess://"+System.getProperty("user.dir").replace("\\", "//")+"/database.accdb","", "");
	@FXML
	private TextField idTF;
	@FXML
	private TextField nameTF;
	@FXML
	private TextField createdAtTF;
	@FXML
	private TextField updatedAtTF;
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
		TableColumn<Category, String> categoryIDColumn = (TableColumn<Category, String>) table.getVisibleLeafColumn(0);
			categoryIDColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("id"));
		TableColumn<Category, String> nameColumn = (TableColumn<Category, String>) table.getVisibleLeafColumn(1);
			nameColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
		TableColumn<Category, String> createAtColumn = (TableColumn<Category,String>) table.getVisibleLeafColumn(2);
			createAtColumn.setCellValueFactory(new PropertyValueFactory<Category,String>("created_at"));
		TableColumn<Category, String> updateAtColumn = (TableColumn<Category,String>) table.getVisibleLeafColumn(3);
			updateAtColumn.setCellValueFactory(new PropertyValueFactory<Category,String>("updated_at"));
		List<Category> categoryList = hrmDB.getCategoryList(); // DB
		ObservableList<Category> obsCategoryList = FXCollections.observableArrayList(categoryList);
			table.setItems(obsCategoryList);
		});
	}
	public void onClickAddButton() {
		Category cate = new Category(nameTF.getText());
		boolean addResult = hrmDB.addCategory(cate);
		if (addResult) {
			List<Category> categoryList =hrmDB.getCategoryList();
			ObservableList<Category> obsCategoryList = FXCollections.observableArrayList(categoryList);
			table.setItems(obsCategoryList);
			System.out.println("Add successfull!");
		} else {
			System.out.println("Error add to database!");
		}
	}
	public void onClickUpdateButton() {
		boolean temp = false;
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		Category selectedCategory = table.getItems().get(selectedIndex);
		selectedCategory.setName(nameTF.getText());
		if (selectedCategory.getId()==Integer.parseInt(idTF.getText())&&selectedCategory.getCreated_at().toString().equals(createdAtTF.getText())&&selectedCategory.getUpdated_at().toString().equals(updatedAtTF.getText())) {
			temp = true;
		}
		if (selectedIndex >= 0 && temp) {
			boolean updateResult = hrmDB.updateCategory(selectedCategory);
			if (updateResult) {
				List<Category> categoryList =hrmDB.getCategoryList();
				ObservableList<Category> obsCategoryList = FXCollections.observableArrayList(categoryList);
				table.setItems(obsCategoryList);
				System.out.println("Update successfull!");
			} else {
				System.out.println("Error update to database!");
			}
		}

	}
	public void onClickDeleteButton() {
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		Category cate = table.getItems().get(selectedIndex);
		if (selectedIndex >= 0) {
			boolean deleteResult = hrmDB.deleteCategory(cate.getId());
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
		createdAtTF.setText(table.getSelectionModel().getSelectedItem().getCreated_at().toString());
		updatedAtTF.setText(table.getSelectionModel().getSelectedItem().getUpdated_at().toString());
	}

}
