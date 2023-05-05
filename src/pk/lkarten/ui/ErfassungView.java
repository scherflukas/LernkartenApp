package pk.lkarten.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pk.lkarten.Lernkarte;

public abstract class ErfassungView extends Stage{
	protected GridPane gpane = new GridPane(); 
	protected Label kategorieLbl = new Label("Kategorie: ");
	protected Label titelLbl = new Label("Titel: ");
	protected Label frageLbl = new Label("Frage: ");
	protected TextField kategorieTxtfld = new TextField();
	protected TextField titelTxtfld = new TextField();
	protected TextField frageTxtfld = new TextField();
	

	public ErfassungView(Stage stg) {
		super();
		this.initOwner(stg); 
		this.initModality(Modality.APPLICATION_MODAL);
		gpane.setHgap(5);
		gpane.setVgap(8);
		gpane.setPadding(new Insets(10,50,10,50));
		gpane.add(kategorieLbl, 0, 0);
		GridPane.setHalignment(kategorieLbl, HPos.RIGHT);
		gpane.add(kategorieTxtfld, 1, 0);
		gpane.add(titelLbl, 0, 1);
		GridPane.setHalignment(titelLbl, HPos.RIGHT);
		gpane.add(titelTxtfld, 1, 1);
		gpane.add(frageLbl, 0, 2);
		GridPane.setHalignment(frageLbl, HPos.RIGHT);
		gpane.add(frageTxtfld, 1, 2);		
	}
	
	public ErfassungView(Stage stg, Lernkarte karte) {
		super();
		this.initOwner(stg); 
		this.initModality(Modality.APPLICATION_MODAL);
		gpane.setHgap(5);
		gpane.setVgap(8);
		gpane.setPadding(new Insets(10,50,10,50));
		gpane.add(kategorieLbl, 0, 0);
		GridPane.setHalignment(kategorieLbl, HPos.RIGHT);
		kategorieTxtfld = (!(karte.getKategorie() == null || karte.getKategorie() == "") ? new TextField(karte.getKategorie()) : new TextField(""));
		gpane.add(kategorieTxtfld, 1, 0);
		gpane.add(titelLbl, 0, 1);
		GridPane.setHalignment(titelLbl, HPos.RIGHT);
		titelTxtfld = (!(karte.getTitel() == null || karte.getTitel() == "") ? new TextField(karte.getTitel()) : new TextField(""));
		gpane.add(titelTxtfld, 1, 1);
		gpane.add(frageLbl, 0, 2);
		GridPane.setHalignment(frageLbl, HPos.RIGHT);
		frageTxtfld = (!(karte.getFrage() == null || karte.getFrage() == "") ? new TextField(karte.getFrage()) : new TextField(""));
		gpane.add(frageTxtfld, 1, 2);
	}
	
	/**
	 * @return the gpane
	 */
	public GridPane getGpane() {
		return gpane;
	}

	/**
	 * @param gpane the gpane to set
	 */
	public void setGpane(GridPane gpane) {
		this.gpane = gpane;
	}

	/**
	 * @return the kategorieLbl
	 */
	public Label getKategorieLbl() {
		return kategorieLbl;
	}

	/**
	 * @param kategorieLbl the kategorieLbl to set
	 */
	public void setKategorieLbl(Label kategorieLbl) {
		this.kategorieLbl = kategorieLbl;
	}

	/**
	 * @return the titelLbl
	 */
	public Label getTitelLbl() {
		return titelLbl;
	}

	/**
	 * @param titelLbl the titelLbl to set
	 */
	public void setTitelLbl(Label titelLbl) {
		this.titelLbl = titelLbl;
	}

	/**
	 * @return the frageLbl
	 */
	public Label getFrageLbl() {
		return frageLbl;
	}

	/**
	 * @param frageLbl the frageLbl to set
	 */
	public void setFrageLbl(Label frageLbl) {
		this.frageLbl = frageLbl;
	}

	/**
	 * @return the kategorieTxtfld
	 */
	public TextField getKategorieTxtfld() {
		return kategorieTxtfld;
	}

	/**
	 * @param kategorieTxtfld the kategorieTxtfld to set
	 */
	public void setKategorieTxtfld(TextField kategorieTxtfld) {
		this.kategorieTxtfld = kategorieTxtfld;
	}

	/**
	 * @return the titelTxtfld
	 */
	public TextField getTitelTxtfld() {
		return titelTxtfld;
	}

	/**
	 * @param titelTxtfld the titelTxtfld to set
	 */
	public void setTitelTxtfld(TextField titelTxtfld) {
		this.titelTxtfld = titelTxtfld;
	}

	/**
	 * @return the frageTxtfld
	 */
	public TextField getFrageTxtfld() {
		return frageTxtfld;
	}

	/**
	 * @param frageTxtfld the frageTxtfld to set
	 */
	public void setFrageTxtfld(TextField frageTxtfld) {
		this.frageTxtfld = frageTxtfld;
	}


	

	public void showView() {
	}
	
	
	
}
