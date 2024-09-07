package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.LinhaProduto;
import model.execptions.ValidationException;
import model.services.LinhaProdutoService;

public class LinhaProdutoFormController implements Initializable {
	
	private LinhaProduto entidade;
	
	private LinhaProdutoService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtIdLinha;
	
	@FXML
	private TextField txtLinha;
	
	@FXML
	private TextField txtDescLinha;

	@FXML
	private TextField txtIdSetor;
	
	@FXML
	private Label labelErroLinha;
	
	@FXML
	private Label labelErroDescLinha;
	
	@FXML
	private Label labelErroIdSetor;
	
	@FXML
	private Button btSalvar;
	
	@FXML 
	private Button btCancelar;
	
	public void setLinhaProduto (LinhaProduto entidade) {
		this.entidade = entidade;
	}
	
	public void setLinhaProdutoService(LinhaProdutoService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	@FXML
	public void onBtSalvarAction(ActionEvent event) {
		if(entidade == null) {
			throw new IllegalStateException("Entidade não Instanciada.");
		}
		if(service == null) {
			throw new IllegalStateException("Serviço não Instanciado.");
		}
		
		try {
			entidade = getFormData();
			service.salvarOuAtualizar(entidade);
			Utils.currentStage(event).close();
			notifyDataChangeListeners();
			
			
		}catch (ValidationException e) {
			setErrorMessages(e.getErrors());
		}
		
		
		catch (DbException e){
			Alerts.showAlert("Erro ao Salvar Registro", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	
	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private LinhaProduto getFormData() {
		LinhaProduto obj = new LinhaProduto();
		
		ValidationException exception = new ValidationException("Erro ao inserir dados.");
		
		obj.setIdLinha(Utils.tryParseToInt(txtIdLinha.getText()));
		
		if(txtLinha.getText()==null ||txtLinha.getText().trim().equals("")) {
			exception.addError("Linha", "O Campo linha não pode ser vazio");
		}
		
		obj.setLinha(txtLinha.getText());
		
		if(txtDescLinha.getText()==null ||txtDescLinha.getText().trim().equals("")) {
			exception.addError("DescLinha", "O Campo Descrição da Linha não pode ser vazio");
		}
		
		obj.setDescLinha(txtDescLinha.getText());
		
		if(txtIdSetor.getText()==null ||txtIdSetor.getText().trim().equals("")) {
			exception.addError("IdSetor", "O Campo Id do Setor não pode ser vazio");
		}
		
		obj.setIdSetor(Utils.tryParseToInt(txtIdSetor.getText()));
		
		if (exception.getErrors().size() > 0) {
			throw exception;
		}
		
		return obj;
	}

	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtIdLinha);
		Constraints.setTextFieldInteger(txtIdSetor);
		Constraints.setTextFieldMaxLength(txtLinha, 5);
		Constraints.setTextFieldMaxLength(txtDescLinha, 25);
	}
	
	public void updateFormData() {
		
		if(entidade==null) {
			throw new IllegalStateException("Entidade Não Instanciada.");
		}
		txtIdLinha.setText(String.valueOf(entidade.getIdLinha()));
		txtLinha.setText(entidade.getLinha());
		txtDescLinha.setText(entidade.getDescLinha());
		txtIdSetor.setText(String.valueOf(entidade.getIdSetor()));
	}
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("DescLinha")) {
			labelErroLinha.setText(errors.get("Linha"));
			labelErroDescLinha.setText(errors.get("DescLinha"));
			labelErroIdSetor.setText(errors.get("IdSetor"));
		}
	}

}
