package gui;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.TxtRestricao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {

	@FXML
	private Button btSalvar;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtValor;
	@FXML
	private Label lblVal;

	@FXML
	private ComboBox<Person> cbxPessoa;

	private ObservableList<Person> lista;
	
	public void oncbxPessoaAction() {
		Person ps = cbxPessoa.getSelectionModel().getSelectedItem();
		System.out.println(ps);
	}

	public void btSalvarCliente() {
		Locale.setDefault(Locale.US);
		Double texto = Double.parseDouble(txtNome.getText());
		Double txtval = Double.parseDouble(txtValor.getText());
		lblVal.setText("R$ " + String.format("%.2f", txtval + texto));
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		List<Person> pessoa = new ArrayList<>();

		pessoa.add(new Person(1, "Sergio", "sergio@gmail.com"));
		pessoa.add(new Person(2, "Walter", "walter@gmail.com"));
		pessoa.add(new Person(3, "Maryah", "mariah@gmail.com"));
		pessoa.add(new Person(4, "Fabiana", "fabiana@gmail.com"));
		pessoa.add(new Person(5, "Vanda", "vanda@gmail.com"));

		lista = FXCollections.observableArrayList(pessoa);
		cbxPessoa.setItems(lista);

		TxtRestricao.setTextInteger(txtNome);
		TxtRestricao.setTextDouble(txtValor);
		TxtRestricao.setTextMaxLength(txtNome, 8);
		TxtRestricao.setTextMaxLength(txtValor, 10);
				
		
		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getNome());
			}
		};
		cbxPessoa.setCellFactory(factory);
		cbxPessoa.setButtonCell(factory.call(null));
	}
}
