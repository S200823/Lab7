package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	Model model = new Model();

	List<String> parole;

	public void setModel(Model model) {
		this.model = model;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtNumero;

	@FXML
	private TextField txtParola;

	@FXML
	private Button btnGeneraGrafo;

	@FXML
	private Button btnTrovaVicini;

	@FXML
	private Button btnTrovaConnessi;

	@FXML
	private TextArea txtRisultato;

	@FXML
	private Button btnReset;

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		txtRisultato.clear();
		txtParola.clear();
		if (txtNumero.getText() != null && txtNumero.getText().compareTo("") != 0) {
			try {
				parole = model.creaGrafo(Integer.parseInt(txtNumero.getText()));
				if (!parole.isEmpty()) {
					txtRisultato.setText("Grafo generato.");
					btnTrovaVicini.setDisable(false);
					btnTrovaConnessi.setDisable(false);
				} else
					txtRisultato.setText("Grafo non generato.");
			} catch (NumberFormatException nfe) {
				txtRisultato.setText("Inserire un valore numerico.");
			}
		} else
			txtRisultato.setText("Inserire un numero.");
	}

	@FXML
	void doReset(ActionEvent event) {
		txtNumero.clear();
		txtParola.clear();
		btnTrovaVicini.setDisable(true);
		btnTrovaConnessi.setDisable(true);
		txtRisultato.clear();
	}

	@FXML
	void doTrovaConnessi(ActionEvent event) {
		txtRisultato.clear();
		if (txtParola.getText() != null && txtParola.getText().compareTo("") != 0) {
			List<String> connessi = null;
			String parola = txtParola.getText();
			if (parole.contains(parola)) {
				connessi = model.trovaConnessi(parola);
				for (String s : connessi)
					txtRisultato.appendText(s + " ");
				txtRisultato.appendText("\nTrovate " + connessi.size() + " parole.");
			} else
				txtRisultato.setText("Parola non trovata.");
		} else
			txtRisultato.setText("Scrivere una parola.");
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		txtRisultato.clear();
		if (txtParola.getText() != null && txtParola.getText().compareTo("") != 0) {
			List<String> vicini = null;
			String parola = txtParola.getText();
			if (parole.contains(parola)) {
				vicini = model.trovaVicini(parola);
				for (String s : vicini)
					txtRisultato.appendText(s + " ");
				txtRisultato.appendText("\nTrovate " + vicini.size() + " parole.");
			} else
				txtRisultato.setText("Parola non trovata.");
		} else
			txtRisultato.setText("Scrivere una parola.");
	}

	@FXML
	void initialize() {
		assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaConnessi != null : "fx:id=\"btnTrovaConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
}
