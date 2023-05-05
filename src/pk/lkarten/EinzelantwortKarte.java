package pk.lkarten;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Objects;

public class EinzelantwortKarte extends Lernkarte implements CsvExportable {

	private static final long serialVersionUID = 1L;
	private String antwort;

	public EinzelantwortKarte(String kategorie, String titel, String frage, String antwort) {
		super();
		super.kategorie = kategorie;
		super.titel = titel;
		super.frage = frage;
		this.antwort = antwort;
	}

	public String getAntwort() {
		return this.antwort;
	}

	public void setAntwort(String antwort) {
		this.antwort = antwort;
	}

	@Override
	public String zeigeVorderseite() throws IOException {
		return "[" + this.id + ", " + this.kategorie + "] " + this.titel + "\n" + this.frage + "\n";
	}
	
	@Override
	public String zeigeRueckseite() throws IOException {
		return this.antwort;
	}

	@Override
	public void zeigeRueckseite(OutputStream stream) throws IOException {
		String s = this.antwort + "\n";
		OutputStreamWriter sw = new OutputStreamWriter(stream);
		sw.write(s);
		sw.flush();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(antwort);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EinzelantwortKarte other = (EinzelantwortKarte) obj;
		return Objects.equals(antwort, other.antwort);
	}

	@Override
	public void validiere() throws UngueltigeKarteException {
		super.validiere();
		if (antwort == null || antwort == "") {
			throw new UngueltigeKarteException("Antwort wurde nicht hinterlegt");
		}
	}

	@Override
	public String exportiereAlsCsv() throws FileNotFoundException {
		return super.exportiereAlsCsv() + "," + antwort;
	}

	@Override
	public String toString() {
		return super.toString() + "antwort=" + antwort + "]";
	}

}
