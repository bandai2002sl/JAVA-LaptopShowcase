package gui;

import java.awt.Desktop;
import java.awt.image.ImageFilter;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import core.Category;
import core.HRMDB;
import core.Laptop;
import core.Product;
import core.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductController {
	HRMDB hrmDB = new HRMDB("jdbc:ucanaccess://"+System.getProperty("user.dir").replace("\\", "//")+"/database.accdb","", "");
	@FXML
	private TableView<Product> table = new TableView<Product>();
	@FXML
	private TextField idTF;
	@FXML
	private TextField nameTF;
	@FXML
	private TextField amountTF;
	@FXML
	private TextField originPriceTF;
	@FXML
	private TextField salePriceTF;
	@FXML
	private ComboBox<String> categoryCB;
	@FXML
	private TextField cpuTF;
	@FXML
	private TextField ramTF;
	@FXML
	private TextField cardTF;
	@FXML
	private TextField screenTF;
	@FXML
	private TextField osTF;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	private User user;
	@FXML
	private ImageView previewImg;
	@FXML
	private Button btnImg;
	@FXML
	public void initialize() {
		Platform.runLater(() -> {
		TableColumn<Product, String> productIDColumn = (TableColumn<Product, String>) table.getVisibleLeafColumn(0);
			productIDColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
		TableColumn<Product, String> nameColumn = (TableColumn<Product, String>) table.getVisibleLeafColumn(1);
			nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		TableColumn<Product, String> amountColumn = (TableColumn<Product, String>) table.getVisibleLeafColumn(2);
			amountColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("amount"));
		TableColumn<Product, String> originPriceColumn = (TableColumn<Product, String>) table.getVisibleLeafColumn(3);
			originPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("origin_price"));
		TableColumn<Product, String> salePriceColumn = (TableColumn<Product, String>) table.getVisibleLeafColumn(4);
			salePriceColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("sale_price"));
		TableColumn<Product, String> categoryColumn = (TableColumn<Product, String>) table.getVisibleLeafColumn(5);
			categoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category_name"));
		List<Product> productList = hrmDB.getProductList(); // DB
		for (Product product : productList) {
			product.setCategory_name(hrmDB.getCategory(product.getCategory_id()).getName());
		}
		for (Category category : hrmDB.getCategoryList()) {
			categoryCB.getItems().add(category.getName());
		}
		
		ObservableList<Product> obsProductList = FXCollections.observableArrayList(productList);
			table.setItems(obsProductList);
		});
	}
	@FXML    
	public void onClickAddButton() {
		if (validate("Thêm mới thành công!")) {
			String uriImg = "";
			if(!pathImg.isEmpty()) {
				File file = new File(pathImg);
				System.out.print(file.getName());
				String path = System.getProperty("user.dir").replace("\\", "/")+"/img/";
				File file1 = new File(path + file.getName());
				file.renameTo(file1);
				uriImg = file1.toURI().toString();
			}
			int categoryId = 0;
			for (Category category : hrmDB.getCategoryList()) {
				if (category.getName().equals(categoryCB.getSelectionModel().getSelectedItem())) {
					categoryId = category.getId();
					break;
				}
			}
			Laptop lp = new Laptop(nameTF.getText(), Integer.parseInt(amountTF.getText()) , Float.parseFloat(originPriceTF.getText()), Float.parseFloat(salePriceTF.getText()), 1, getUser().getId(), categoryId, 0, uriImg);
			lp.setCpu(cpuTF.getText());
			lp.setGraphicsCard(cardTF.getText());
			lp.setRam(ramTF.getText());
			lp.setOs(osTF.getText());
			lp.setScreen(screenTF.getText());
			lp.optionToString();
			Product product = (Laptop) lp;
			
			boolean addResult = hrmDB.addProduct(product);
			if (addResult) {
				List<Product> productList =hrmDB.getProductList();
				for (Product product1 : productList) {
					product1.setCategory_name(hrmDB.getCategory(product.getCategory_id()).getName());
				}
				ObservableList<Product> obsProductList = FXCollections.observableArrayList(productList);
				table.setItems(obsProductList);
				System.out.println("Add successfull!");
			} else {
				System.out.println("Error add to database!");
			}
		}
		
	}
	@FXML    
	public void onClickUpdateButton() {
		validate("Cập nhật thành công");
		if (validate("Cập nhật thành công")) {
			int categoryId = 0;
			for (Category category : hrmDB.getCategoryList()) {
				if (category.getName().equals(categoryCB.getSelectionModel().getSelectedItem())) {
					categoryId = category.getId();
					break;
				}
			}
			
			int selectedIndex = table.getSelectionModel().getSelectedIndex();
			Laptop lp = new Laptop();
			Product selectedProduct = table.getItems().get(selectedIndex);
			selectedProduct.setName(nameTF.getText());
			selectedProduct.setStatus(0);
			selectedProduct.setDiscount_percent(0);
			selectedProduct.setUser_id(getUser().getId());
			selectedProduct.setAmount(Integer.parseInt(amountTF.getText()));
			selectedProduct.setCategory_id(categoryId);
			selectedProduct.setCategory_name(categoryCB.getValue());
			String uriImg = selectedProduct.getImg();
			if(!pathImg.isEmpty()) {
				File file = new File(pathImg);
				System.out.print(file.getName());
				String path = System.getProperty("user.dir").replace("\\", "/")+"/img/";
				File file1 = new File(path + file.getName());
				file.renameTo(file1);
				uriImg = file1.toURI().toString();
			}
			selectedProduct.setImg(uriImg);
			selectedProduct.setOrigin_price(Float.parseFloat(originPriceTF.getText()));
			selectedProduct.setSale_price(Float.parseFloat(salePriceTF.getText()));
			lp.setCpu(cpuTF.getText());
			lp.setRam(ramTF.getText());
			lp.setOs(osTF.getText());
			lp.setGraphicsCard(cardTF.getText());
			lp.setScreen(screenTF.getText());
			lp.optionToString();
			selectedProduct.setOption(lp.getOption());
			if (selectedIndex >= 0) {
				boolean updateResult = hrmDB.updateProduct(selectedProduct);
				if (updateResult) {
					table.getItems().set(selectedIndex, selectedProduct);
					System.out.println("Update successfull!");
				} else {
					System.out.println("Error update to database!");
				}
			}

		}
	}
	@FXML    
	public void onClickDeleteButton() throws URISyntaxException {
		
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		Product product = table.getItems().get(selectedIndex);
		
		if (selectedIndex >= 0) {
			boolean deleteResult = hrmDB.deleteProduct(product.getId());
			if(!product.getImg().isEmpty()) {
				URI uri = new URI(product.getImg());
				File file = new File(uri);
				file.delete();
			}
			if (deleteResult) {
				
				table.getItems().remove(selectedIndex);
				System.out.println("Delete successfull!");
			} else {
				System.out.println("Error delete to database!");
			}
		}
	}	
	@FXML    
	public void onClickImgButton() {
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter ImageFilter = new FileChooser.ExtensionFilter("Image Files","*.jpg");
		fc.getExtensionFilters().add(ImageFilter);
		File file = fc.showOpenDialog(null);
		if (file != null) {
			Image image = new Image(file.toURI().toString());
			previewImg.setImage(image);
			pathImg = file.toPath().toString();
		}
	}
	@FXML    
	public void onClickRow() {
		Laptop lp = new Laptop(table.getSelectionModel().getSelectedItem().getOption());
		lp.optionToAttribute();
		idTF.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getId()));
		nameTF.setText(table.getSelectionModel().getSelectedItem().getName());
		amountTF.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getAmount()));
		originPriceTF.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getOrigin_price()));
		salePriceTF.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getSale_price()));
		cpuTF.setText(lp.getCpu());
		ramTF.setText(lp.getRam());
		osTF.setText(lp.getOs());
		screenTF.setText(lp.getScreen());
		cardTF.setText(lp.getGraphicsCard());
		categoryCB.setValue(table.getSelectionModel().getSelectedItem().getCategory_name());
		if(!table.getSelectionModel().getSelectedItem().getImg().isEmpty()) {
			Image image = new Image(table.getSelectionModel().getSelectedItem().getImg());
			previewImg.setImage(image);
		}else {
			previewImg.setImage(null);
		}
		
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	private String pathImg ="";
	public boolean validate(String notification) {
		List<String> errorList = new ArrayList<String>();
		if ("".equals(nameTF.getText())){
			errorList.add("Tên sản phẩm không được để trống");
		}
		if("".equals(originPriceTF.getText())) {
			errorList.add("Giá gốc không được để trống");
		}
		if("".equals(salePriceTF.getText())) {
			errorList.add("Giá bán không được để trống");
		}
		if("".equals(amountTF.getText())) {
			errorList.add("Số lượng không được để trống");
		}
		if("".equals(cpuTF.getText())||"".equals(screenTF.getText())||"".equals(ramTF.getText())||"".equals(cardTF.getText())||"".equals(osTF.getText())) {
			errorList.add("Không được để trống các thuộc tính sản phẩm");
		}
		String errorSting = "";
		for (String string : errorList) {
			errorSting += string+"\n";
		}
		boolean end = false;
		StackPane secondLayout = new StackPane();
		if (errorSting.equals("")) {
			Label mgs = new Label(notification);
			mgs.setStyle("-fx-text-fill: green;");
			secondLayout.getChildren().add(mgs);
			end = true;
		}else {
			Label mgs = new Label(errorSting);
			mgs.setStyle("-fx-text-fill: red;");
			secondLayout.getChildren().add(mgs);
		}
		Scene secondScene = new Scene(secondLayout, 350, 150);
		Stage newStage = new Stage();
		newStage.setTitle("Thông báo");
		newStage.setScene(secondScene);
		newStage.initModality(Modality.WINDOW_MODAL);
		newStage.setX(800);
		newStage.setY(300);
		newStage.show();
		return end;
	}
}
