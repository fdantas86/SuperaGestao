package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.LinhaProdutoService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemCadProduto;
	
	@FXML	
	private MenuItem menuItemCadSetor;
	
	@FXML
	private MenuItem menuItemCadLinha;
	
	@FXML
	private MenuItem menuItemCnsVendas;
	
	@FXML
	private MenuItem menuItemCnsVersao;
	
	@FXML
	public void onMenuItemCadProdutoAction() {
		System.out.println("Cadastrar Produto!");
	}
	
	@FXML
	public void onMenuItemCadSetorAction() {
		System.out.println("Cadastrar Setor!");
	}
	
	@FXML
	public void onMenuItemCadLinhaAction() {
		loadView("/gui/LinhaProduto.fxml");
	}
	
	@FXML
	public void onMenuItemCnsVendasAction() {
		System.out.println("Consultar Vendas!");
	}
	
	@FXML
	public void onMenuItemVersaoAction() {
		loadView("/gui/Sobre.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
				
	}
	
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			LinhaProdutoController controller = loader.getController();
			controller.setLinhaProdutoService(new LinhaProdutoService());
			controller.updateTableView();
			
		} catch (IOException e) {
			e.printStackTrace();
			//Alerts.showAlert("IO Exception", "Erro ao Carregar a página", e.getMessage(), AlertType.ERROR);
		}
		
		
		
	}
	
	
	}	


