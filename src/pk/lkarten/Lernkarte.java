package pk.lkarten;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.Objects;

public abstract class Lernkarte implements Comparable<Lernkarte>, ValidierbareKarte, CsvExportable, Serializable {
	private static final long serialVersionUID = 1L;
	protected static int counter = 1;
	protected final int id;
	protected String kategorie;
	protected String titel;
	protected String frage;

	public Lernkarte() {
		this.id = counter++;
	}

	public Lernkarte(String kategorie, String titel, String frage) {
		this.id = counter++;
		this.kategorie = kategorie;
		this.titel = titel;
		this.frage = frage;
	}

	public int getID() {
		return this.id;
	}

	public String getKategorie() {
		return this.kategorie;
	}

	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.kategorie = titel;
	}

	public String getFrage() {
		return this.frage;
	}

	public void setFrage(String frage) {
		this.frage = frage;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Lernkarte.counter = counter;
	}

	public abstract String zeigeVorderseite() throws IOException;
	
	public void zeigeVorderseite(OutputStream stream) throws IOException {
		String s = "[" + this.id + ", " + this.kategorie + "] " + this.titel + "\n" + this.frage + "\n";
		OutputStreamWriter sw = new OutputStreamWriter(stream);
		sw.write(s);
		sw.flush();
	}

	public abstract void zeigeRueckseite(OutputStream stream) throws IOException;

	public abstract String zeigeRueckseite() throws IOException;
	
	public void druckeKarte(OutputStream stream) throws IOException {
		this.zeigeVorderseite(System.out);
		System.out.print("\n");
		this.zeigeRueckseite(System.out);
		System.out.print("\n");
	}

	@Override
	public int compareTo(Lernkarte o) {
		if (o != null) {
			return (this.id < o.getID()) ? -1 : ((this.id == o.getID()) ? 0 : 1);
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(frage, kategorie, titel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lernkarte other = (Lernkarte) obj;
		return Objects.equals(frage, other.frage) && Objects.equals(kategorie, other.kategorie)
				&& Objects.equals(titel, other.titel);
	}

	@Override
	public void validiere() throws UngueltigeKarteException {
		if (kategorie == null || kategorie == "") {
			throw new UngueltigeKarteException("Kategorie wurde nicht hinterlegt");
		}
		if (titel == null || titel == "") {
			throw new UngueltigeKarteException("Titel wurde nicht hinterlegt");
		}
		if (frage == null || frage == "") {
			throw new UngueltigeKarteException("Frage wurde nicht hinterlegt");
		}
	}

	@Override
	public String exportiereAlsCsv() throws FileNotFoundException {
		return id + "," + kategorie + "," + titel + "," + frage;
	}

	@Override
	public String toString() {
		return "Lernkarte [id=" + id + ", kategorie=" + kategorie + ", titel=" + titel + ", frage=" + frage + " ";
	}


}
