package pk.lkarten.ui;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk.lkarten.EinzelantwortKarte;

public class EinzelAntwortErfassungView extends ErfassungView {
	private EinzelantwortKarte karte = new EinzelantwortKarte("test", "test", "test", "test");
	private Label antwortLabel = new Label ("Antwort:");
	private TextArea antwortTextArea = new TextArea();
	private Button okButton = new Button("OK");
	private Button abbrechenButton = new Button("Abbrechen");

	public EinzelAntwortErfassungView(Stage stg) {
		super(stg);
		gpane.add(antwortLabel, 0, 3);
		GridPane.setHalignment(antwortLabel, HPos.RIGHT);
		gpane.add(antwortTextArea, 1, 3);
		HBox buttonHBox = new HBox(okButton, abbrechenButton);
		buttonHBox.setPadding(new Insets(15, 200, 15, 200));
		gpane.add(buttonHBox, 1, 4);
		HBox.setMargin(okButton, new Insets(0,10,0,10));
	}
	
	public EinzelAntwortErfassungView(Stage stg, EinzelantwortKarte karte) {
		super(stg, karte);
		this.karte = karte;
		gpane.add(antwortLabel, 0, 3);
		GridPane.setHalignment(antwortLabel, HPos.RIGHT);
		antwortTextArea = (!(karte.getAntwort() == null || karte.getAntwort() == "") ? new TextArea(karte.getAntwort()) : new TextArea(""));
		gpane.add(antwortTextArea, 1, 3);
		HBox buttonHBox = new HBox(okButton, abbrechenButton);
		buttonHBox.setPadding(new Insets(15, 200, 15, 200));
		gpane.add(buttonHBox, 1, 4);
		HBox.setMargin(okButton, new Insets(0,10,0,10));
	}
	
	/**
	 * @return the antwortLabel
	 */
	public Label getAntwortLabel() {
		return antwortLabel;
	}

	/**
	 * @param antwortLabel the antwortLabel to set
	 */
	public void setAntwortLabel(Label antwortLabel) {
		this.antwortLabel = antwortLabel;
	}

	/**
	 * @return the antwortTextArea
	 */
	public TextArea getAntwortTextArea() {
		return antwortTextArea;
	}

	/**
	 * @param antwortTextArea the antwortTextArea to set
	 */
	public void setAntwortTextArea(TextArea antwortTextArea) {
		this.antwortTextArea = antwortTextArea;
	}

	/**
	 * @return the okButton
	 */
	public Button getOkButton() {
		return okButton;
	}

	/**
	 * @param okButton the okButton to set
	 */
	public void setOkButton(Button okButton) {
		this.okButton = okButton;
	}

	/**
	 * @return the abbrechenButton
	 */
	public Button getAbbrechenButton() {
		return abbrechenButton;
	}

	/**
	 * @param abbrechenButton the abbrechenButton to set
	 */
	public void setAbbrechenButton(Button abbrechenButton) {
		this.abbrechenButton = abbrechenButton;
	}

	public EinzelantwortKarte getKarte() {
		return this.karte;
	}

	public void setKarte(EinzelantwortKarte karte) {
		this.karte = karte;
	}    


	@Override
	public void showView() {
		super.showView();
		okButton.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent arg0) {
				Stage stage = (Stage) okButton.getScene().getWindow();
				EinzelAntwortErfassungView root = (EinzelAntwortErfassungView) okButton.getScene().getWindow();
				root.getKarte().setKategorie(root.getKategorieTxtfld().getText());
				root.getKarte().setTitel(root.getTitelTxtfld().getText());
				root.getKarte().setFrage(root.getFrageTxtfld().getText());
				root.getKarte().setAntwort(root.getAntwortTextArea().getText());
				stage.close();
			}
		});
		okButton.setDefaultButton(true);
		abbrechenButton.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent arg0) {
				karte = null;
				Stage stage = (Stage) abbrechenButton.getScene().getWindow();
				stage.close();
			}
		});
		abbrechenButton.setCancelButton(true);
		this.setScene(new Scene(gpane));
		this.setTitle("Einzelantwortkartenerfassung");
		this.show();
	}
	
	

}
