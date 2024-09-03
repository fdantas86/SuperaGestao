package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.LinhaProduto;
import model.services.LinhaProdutoService;

public class LinhaProdutoController implements Initializable, DataChangeListener {
	
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
	public void onBtnCadastrarAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		LinhaProduto obj = new LinhaProduto();
		createDialogForm(obj, "/gui/LinhaProdutoForm.fxml", parentStage);
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
	public void initialize(URL url, ResourceBundle rb) {
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
	
	private void createDialogForm(LinhaProduto obj, String absoluteName, Stage parentStage) {
		
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			LinhaProdutoFormController controller = loader.getController();
			controller.setLinhaProduto(obj);
			controller.setLinhaProdutoService(new LinhaProdutoService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Dados da Linha do Produto");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}catch(IOException e) {
			Alerts.showAlert("Erro de Inserção", "Erro ao carregar página", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}

}
