package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LinhaProdutoFormController implements Initializable {
	
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
	
	@FXML
	public void onBtSalvarAction() {
		System.out.println("Botão Salvar");
	}
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("Botão Cancelar");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

}
