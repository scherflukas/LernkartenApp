package pk.lkarten.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Iterator;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pk.lkarten.EinzelantwortKarte;
import pk.lkarten.Lernkarte;
import pk.lkarten.LernkarteiHashSet;
import pk.lkarten.MehrfachantwortKarte;
import pk.lkarten.UngueltigeKarteException;

public class LernkartenApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		LernkarteiHashSet<Lernkarte> kartei = new LernkarteiHashSet<Lernkarte>();
		BorderPane bpane = new BorderPane();
		var list = new ListView<String>();
		bpane.setCenter(list);
		MenuBar mb = new MenuBar();		
		Menu datei = new Menu("Datei");
		MenuItem ladenItem = new MenuItem("Laden");
		ladenItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				File f = DialogUtil.showFileChooser("Lernkartei laden", primaryStage);
				try {
					list.getItems().clear();
					kartei.readObject(f);
					for (Iterator it = kartei.getIterator(); it.hasNext();) {
						list.getItems().add(it.next().toString());
					}
				} catch (FileNotFoundException e) {
					displayException(e);
				} catch (ClassNotFoundException e) {
					displayException(e);
				} catch (IOException e) {
					displayException(e);
				}
			}
		});
		MenuItem speichernItem = new MenuItem("Speichern");
		speichernItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				while (true) {
					String name = "";
					name = DialogUtil.showInputDialog("Lernkartei in Datei speichern", "Dateinamen eingeben");
					if (name != null && !name.isBlank()) {
						if (new File(name).exists()) {
							if (DialogUtil.showConfirmDialog("Überschreiben?", "Datei ist bereits vorhanden") == true) {
								try {
									kartei.writeObject(new File(name));
									break;
								} catch (FileNotFoundException e) {
									displayException(e);
								} catch (IOException e) {
									displayException(e);
								}
							}
						}
						else if (!name.isBlank()) {
							try {
								kartei.writeObject(new File(name));
								break;
							} catch (FileNotFoundException e) {
								displayException(e);
							} catch (IOException e) {
								displayException(e);
							}
						}
					}					
				}
			}
		});
		MenuItem csvExportItem = new MenuItem("CSV-Export");
		csvExportItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				while (true) {
					String name = "";
					name = DialogUtil.showInputDialog("Lernkartei in CSV-Datei exportieren", "Dateinamen eingeben");
					if (name != null && !name.isBlank()) {
						if (new File(name).exists()) {
							if (DialogUtil.showConfirmDialog("Überschreiben?", "Datei ist bereits vorhanden") == true) {
								try {
									kartei.exportiereEintraegeAlsCsv(new File(name + ".csv"));
									break;
								} catch (FileNotFoundException e) {
									displayException(e);
								} catch (IOException e) {
									displayException(e);
								}
							}
						}
						else if (!name.isBlank()) {
							try {
								kartei.exportiereEintraegeAlsCsv(new File(name + ".csv"));
								break;
							} catch (FileNotFoundException e) {
								displayException(e);
							} catch (IOException e) {
								displayException(e);
							}
						}
					}					
				}
			}
		});
		MenuItem beendenItem = new MenuItem("Beenden");
		beendenItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.close();
			}
		});
		datei.getItems().addAll(ladenItem, speichernItem, new SeparatorMenuItem(), csvExportItem, new SeparatorMenuItem(), beendenItem);
		Menu lkartei = new Menu("Lernkartei");
		MenuItem einzelKarteHinzuItem = new MenuItem("Einzelantwortkarte hinzufügen");
		EinzelAntwortErfassungView eaev = new EinzelAntwortErfassungView(primaryStage);
		
		eaev.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
					String kategorie = eaev.getKarte().getKategorie();
					String titel = eaev.getKarte().getTitel();
					String frage = eaev.getKarte().getFrage();
					String antwort = eaev.getKarte().getAntwort();
					EinzelantwortKarte karte = new EinzelantwortKarte(kategorie, titel, frage, antwort);
					try {
						karte.validiere();
						kartei.hinzufuegen(karte);
					} catch (UngueltigeKarteException e) {
						displayException(e);
					}
					list.getItems().clear();
					list.getItems().add(karte.toString());
			}
		});
		
		einzelKarteHinzuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				eaev.showView();
			}
		});
		
		MenuItem mehrfachKarteHinzuItem = new MenuItem("Mehrfachantwortkarte hinzufügen");
		mehrfachKarteHinzuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		lkartei.getItems().addAll(einzelKarteHinzuItem, mehrfachKarteHinzuItem);
		mb.getMenus().addAll(datei, lkartei);
		bpane.setTop(mb);
		Spinner<Integer> spin = new Spinner<Integer>(1, 50, 1);
		spin.setEditable(true);
		Button lernen = new Button("Lernen!");
		HBox spinnerHBox = new HBox(lernen, spin);
		BorderPane.setAlignment(spinnerHBox, Pos.CENTER);
		BorderPane.setAlignment(spin, Pos.CENTER);
		BorderPane.setAlignment(lernen, Pos.CENTER);
		bpane.setBottom(spinnerHBox);
		lernen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				int size = (int) spin.getValue();
				Lernkarte[] deck = kartei.erzeugeDeck(size);
				for (Lernkarte lk : deck) {
					try {
						if (lk instanceof EinzelantwortKarte) {
							DialogUtil.showTextDialog(lk.getTitel(), lk.getKategorie(), lk.zeigeVorderseite(), "Rückseite zeigen");
							DialogUtil.showTextDialog(lk.getTitel(), lk.getKategorie(), "Die richtige Antwort ist:\n" + lk.zeigeRueckseite(), "Nächste Karte zeigen");							
						}
						if (lk instanceof MehrfachantwortKarte) {
							DialogUtil.showTextDialog(lk.getTitel(), lk.getKategorie(), lk.zeigeVorderseite(), "Rückseite zeigen");
							DialogUtil.showTextDialog(lk.getTitel(), lk.getKategorie(), "Die richtigen Antworten sind:\n" + lk.zeigeRueckseite(), "Nächste Karte zeigen");
						}
					} catch (IOException e) {
						displayException(e);
					}
				}
			}
		});
		
		primaryStage.show();
		primaryStage.setScene(new Scene(bpane));
		primaryStage.setTitle("Lernkarten-App");
		
	}
	
	public void displayException(Exception e) {
		DialogUtil.showErrorDialog("Fehler", e.getMessage());
		e.printStackTrace();
	}

	public static void main(String[] args) {
		launch(args);
	}

	
}	
