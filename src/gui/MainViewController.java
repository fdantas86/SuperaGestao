package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
		System.out.println("Cadastrar Linha!");
	}
	
	@FXML
	public void onMenuItemCnsVendasAction() {
		System.out.println("Consultar Vendas!");
	}
	
	@FXML
	public void onMenuItemVersaoAction() {
		System.out.println("Consultar Versão!");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
				
	}

}
