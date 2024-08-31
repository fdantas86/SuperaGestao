package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.LinhaProduto;
import model.services.LinhaProdutoService;

public class LinhaProdutoController implements Initializable {
	
	private LinhaProdutoService service;
	
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
	
	private ObservableList<LinhaProduto> obsList;
	
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

	public void setLinhaProdutoService (LinhaProdutoService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumnIdlinha.setCellValueFactory(new PropertyValueFactory<>("idLinha"));
		tableColumnIdSetor.setCellValueFactory(new PropertyValueFactory<>("idSetor"));
		tableColumnLinha.setCellValueFactory(new PropertyValueFactory<>("linha"));
		tableColumnDescLinha.setCellValueFactory(new PropertyValueFactory<>("descLinha"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewLinhaProduto.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("O Serviço está vazio.");
		}
		List<LinhaProduto> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewLinhaProduto.setItems(obsList);
	}

}
