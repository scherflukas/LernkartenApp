package pk.lkarten.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pk.lkarten.Lernkarte;
import pk.lkarten.MehrfachantwortKarte;

public class MehrfachantwortKarteErfassungView extends ErfassungView {
	private MehrfachantwortKarte karte;
	private Label antwort1Label = new Label("Antwort 1:");
	private Label antwort2Label = new Label("Antwort 2:");
	private Label antwort3Label = new Label("Antwort 3:");
	private Label antwort4Label = new Label("Antwort 4:");
	private Label antwort5Label = new Label("Antwort 5:");
	private Label richtigLabel1 = new Label("Richtig?");
	private Label richtigLabel2 = new Label("Richtig?");
	private Label richtigLabel3 = new Label("Richtig?");
	private Label richtigLabel4 = new Label("Richtig?");
	private Label richtigLabel5 = new Label("Richtig?");
	private TextArea antwort1TextArea = new TextArea();
	private TextArea antwort2TextArea = new TextArea();
	private TextArea antwort3TextArea = new TextArea();
	private TextArea antwort4TextArea = new TextArea();
	private TextArea antwort5TextArea = new TextArea();
	private CheckBox antwort1CheckBox = new CheckBox();
	private CheckBox antwort2CheckBox = new CheckBox();
	private CheckBox antwort3CheckBox = new CheckBox();
	private CheckBox antwort4CheckBox = new CheckBox();
	private CheckBox antwort5CheckBox = new CheckBox();
	private Button okButton = new Button("OK");
	private Button abbrechenButton = new Button("Abbrechen");
	
	public MehrfachantwortKarteErfassungView(Stage stg) {
		super(stg);
		richtigLabel1.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel2.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel3.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel4.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel5.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort1Label, 0, 3);
		HBox antwort1HBox = new HBox(antwort1TextArea, antwort1CheckBox, richtigLabel1);
		VBox.setVgrow(antwort1HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort1CheckBox, Priority.ALWAYS);
		antwort1CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort1HBox, 1, 3);
		gpane.add(antwort2Label, 0, 4);
		HBox antwort2HBox = new HBox(antwort2TextArea, antwort2CheckBox, richtigLabel2);
		VBox.setVgrow(antwort2HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort2CheckBox, Priority.ALWAYS);
		antwort2CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort2HBox, 1, 4);
		gpane.add(antwort3Label, 0, 5);
		HBox antwort3HBox = new HBox(antwort3TextArea, antwort3CheckBox, richtigLabel2);
		VBox.setVgrow(antwort3HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort3CheckBox, Priority.ALWAYS);
		antwort3CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort3HBox, 1, 5);
		gpane.add(antwort4Label, 0, 6);
		HBox antwort4HBox = new HBox(antwort4TextArea, antwort4CheckBox, richtigLabel4);
		HBox.setHgrow(antwort4CheckBox, Priority.ALWAYS);
		VBox.setVgrow(antwort4HBox, Priority.ALWAYS);
		antwort4CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort4HBox, 1, 6);
		gpane.add(antwort5Label, 0, 7);
		HBox antwort5HBox = new HBox(antwort5TextArea, antwort5CheckBox, richtigLabel5);
		VBox.setVgrow(antwort5HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort5CheckBox, Priority.ALWAYS);
		antwort5CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort5HBox, 1, 7);
		HBox buttonHBox = new HBox(okButton, abbrechenButton);
		buttonHBox.setPadding(new Insets(15, 200, 15, 200));
		HBox.setMargin(okButton, new Insets(0,10,0,10));
		gpane.add(buttonHBox, 1, 8);
	}
	
	public MehrfachantwortKarteErfassungView(Stage stg, MehrfachantwortKarte karte) {
		super(stg, karte);
		gpane.add(antwort1Label, 0, 3);
		antwort1TextArea = (!(karte.getMoeglicheAntworten()[0] == null || karte.getMoeglicheAntworten()[0] == "") ? new TextArea(karte.getMoeglicheAntworten()[0]) : new TextArea(""));
		gpane.add(antwort1TextArea, 1, 3);
		for (int i : karte.getRichtigeAntworten()) {
			if (i == 0) {
				antwort1CheckBox.setSelected(true);
			}
		}
		HBox antwort1HBox = new HBox(antwort1TextArea, antwort1CheckBox, richtigLabel1);
		VBox.setVgrow(antwort1HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort1CheckBox, Priority.ALWAYS);
		antwort1CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort1HBox, 1, 3);
		gpane.add(antwort2Label, 0, 4);
		antwort2TextArea = (!(karte.getMoeglicheAntworten()[1] == null || karte.getMoeglicheAntworten()[1] == "") ? new TextArea(karte.getMoeglicheAntworten()[1]) : new TextArea(""));
		gpane.add(antwort2TextArea, 1, 4);
		for (int i : karte.getRichtigeAntworten()) {
			if (i == 1) {
				antwort2CheckBox.setSelected(true);
			}
		}
		HBox antwort2HBox = new HBox(antwort2TextArea, antwort2CheckBox, richtigLabel2);
		VBox.setVgrow(antwort2HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort2CheckBox, Priority.ALWAYS);
		antwort2CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort2HBox, 1, 4);
		gpane.add(antwort3Label, 0, 5);
		if (karte.getMoeglicheAntworten().length > 1) {
			antwort3TextArea = (!(karte.getMoeglicheAntworten()[2] == null || karte.getMoeglicheAntworten()[2] == "") ? new TextArea(karte.getMoeglicheAntworten()[2]) : new TextArea(""));
			for (int i : karte.getRichtigeAntworten()) {
				if (i == 2) {
					antwort3CheckBox.setSelected(true);
				}
			}
		}
		HBox antwort3HBox = new HBox(antwort3TextArea, antwort3CheckBox, richtigLabel3);
		VBox.setVgrow(antwort3HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort3CheckBox, Priority.ALWAYS);
		antwort3CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort3HBox, 1, 5);
		gpane.add(antwort4Label, 0, 6);
		if (karte.getMoeglicheAntworten().length > 2) {
			antwort4TextArea = (!(karte.getMoeglicheAntworten()[3] == null || karte.getMoeglicheAntworten()[3] == "") ? new TextArea(karte.getMoeglicheAntworten()[3]) : new TextArea(""));
			for (int i : karte.getRichtigeAntworten()) {
				if (i == 3) {
					antwort4CheckBox.setSelected(true);
				}
			}
		}
		HBox antwort4HBox = new HBox(antwort4TextArea, antwort4CheckBox, richtigLabel4);
		VBox.setVgrow(antwort4HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort4CheckBox, Priority.ALWAYS);
		antwort4CheckBox.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort4HBox, 1, 6);
		gpane.add(antwort5Label, 0, 7);
		if (karte.getMoeglicheAntworten().length > 3) {
			antwort5TextArea.setMaxWidth(20);
			antwort5TextArea = (!(karte.getMoeglicheAntworten()[4] == null || karte.getMoeglicheAntworten()[4] == "") ? new TextArea(karte.getMoeglicheAntworten()[4]) : new TextArea(""));
			for (int i : karte.getRichtigeAntworten()) {
				if (i == 4) {
					antwort5CheckBox.setSelected(true);
				}
			}
		}
		GridPane.setHalignment(antwort5TextArea, HPos.LEFT);
		GridPane.setHalignment(antwort5CheckBox, HPos.RIGHT);
		HBox antwort5HBox = new HBox(antwort5TextArea, antwort5CheckBox, richtigLabel5);
		VBox.setVgrow(antwort5HBox, Priority.ALWAYS);
		HBox.setHgrow(antwort5CheckBox, Priority.ALWAYS);
		antwort5CheckBox.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel1.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel2.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel3.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel4.setPadding(new Insets(50, 0, 0, 5));
		richtigLabel5.setPadding(new Insets(50, 0, 0, 5));
		gpane.add(antwort5HBox, 1, 7);
		HBox buttonHBox = new HBox(okButton, abbrechenButton);
		buttonHBox.setPadding(new Insets(15, 200, 15, 200));
		HBox.setMargin(okButton, new Insets(0,10,0,10));
		gpane.add(buttonHBox, 1, 8);
	}
	
	public MehrfachantwortKarte getKarte() {
		return karte;
	}

	public void setKarte(MehrfachantwortKarte karte) {
		this.karte = karte;
	}


	@Override
	public void showView() {
		super.showView();
		this.setScene(new Scene(gpane));
		this.setTitle("Mehrfachantwortkartenerfassung");
		this.show();
	}

}
