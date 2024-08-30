package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.LinhaProduto;

public class LinhaProdutoController implements Initializable {
	
	@FXML
	private TableView<LinhaProduto> tableViewLinhaProduto;
	
	@FXML
	private TableColumn<LinhaProduto, Integer> tableColumnIdlinha;
	
	@FXML 
	private TableColumn<LinhaProduto, Integer> tableColumnIdSetor;
	
	@FXML
	private TableColumn<LinhaProduto, String> tableColumnLinha;
	
	@FXML
	private TableColumn<LinhaProduto, String> tableColumnDescLinha;
	
	@FXML
	private Button btnCadastrar;
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private Button btnExcluir;
	
	@FXML
	public void onBtnCadastrarAction() {
		System.out.println("Cadastrar");
	}
	
	@FXML
	public void onBtnEditarAction() {
		System.out.println("Editar");
	}
	
	@FXML
	public void onBtnExcluirAction() {
		System.out.println("Excluir");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumnIdlinha.setCellValueFactory(new PropertyValueFactory<>("id_linha"));
		tableColumnIdSetor.setCellValueFactory(new PropertyValueFactory<>("id_setor"));
		tableColumnLinha.setCellValueFactory(new PropertyValueFactory<>("linha"));
		tableColumnDescLinha.setCellValueFactory(new PropertyValueFactory<>("desc_linha"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewLinhaProduto.prefHeightProperty().bind(stage.heightProperty());
	}
	

}
