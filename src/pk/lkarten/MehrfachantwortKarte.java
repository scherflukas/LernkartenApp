package pk.lkarten;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class MehrfachantwortKarte extends Lernkarte implements CsvExportable {
	private static final long serialVersionUID = 1L;
	private String[] moeglicheAntworten;
	private int[] richtigeAntworten;

	public MehrfachantwortKarte(String kategorie, String titel, String frage, String[] moeglicheAntworten,
			int[] richtigeAntworten) {
		super();
		super.kategorie = kategorie;
		super.titel = titel;
		super.frage = frage;
		this.moeglicheAntworten = moeglicheAntworten;
		this.richtigeAntworten = richtigeAntworten;
	}

	public String[] getMoeglicheAntworten() {
		return moeglicheAntworten;
	}

	public int[] getRichtigeAntworten() {
		return richtigeAntworten;
	}

	public void setAnworten(String[] ma, int[] ra) {
		this.moeglicheAntworten = ma;
		this.richtigeAntworten = ra;
	}

	public void zeigeVorderseite(OutputStream stream) throws IOException {
		super.zeigeVorderseite(stream);
		OutputStreamWriter sw = new OutputStreamWriter(stream);
		for (int i = 0; i < moeglicheAntworten.length; i++) {
			sw.write((i + 1) + ": " + moeglicheAntworten[i] + "\n");
		}
		if (richtigeAntworten.length > 1) {
			sw.write("mehrere Antworten möglich");
		}
		sw.flush();
	}

	@Override
	public void zeigeRueckseite(OutputStream stream) throws IOException {
		OutputStreamWriter sw = new OutputStreamWriter(stream);
		sw.write("Die richtigen Antworten sind: \n");
		for (int i = 0; i < richtigeAntworten.length; i++) {
			sw.write((richtigeAntworten[i] + 1) + ": " + moeglicheAntworten[richtigeAntworten[i]] + "\n");
		}
		sw.flush();
	}

	@Override
	public String zeigeVorderseite() throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < moeglicheAntworten.length; i++) {
			sb.append((i + 1) + ": " + moeglicheAntworten[i] + "\n");
		}
		if (richtigeAntworten.length > 1) {
			sb.append("mehrere Antworten möglich");
		}
		return sb.toString();
	}

	@Override
	public String zeigeRueckseite() throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < richtigeAntworten.length; i++) {
			sb.append((richtigeAntworten[i] + 1) + ": " + moeglicheAntworten[richtigeAntworten[i]] + "\n");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(moeglicheAntworten);
		result = prime * result + Arrays.hashCode(richtigeAntworten);
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
		MehrfachantwortKarte other = (MehrfachantwortKarte) obj;
		return Arrays.equals(moeglicheAntworten, other.moeglicheAntworten)
				&& Arrays.equals(richtigeAntworten, other.richtigeAntworten);
	}

	@Override
	public void validiere() throws UngueltigeKarteException {
		super.validiere();
		if (moeglicheAntworten.length < 1) {
			throw new UngueltigeKarteException("Zu wenige Antworten hinterlegt");
		}
		for (int i = 0; i < moeglicheAntworten.length; i++) {
			if (moeglicheAntworten[i] == null || moeglicheAntworten[i] == "") {
				throw new UngueltigeKarteException("Antwort " + (i + 1) + " wurde nicht hinterlegt");
			}
		}
	}

	@Override
	public String exportiereAlsCsv() throws FileNotFoundException {
		StringBuilder sb = new StringBuilder();
		sb.append(super.exportiereAlsCsv()).append(",\"").append(Arrays.toString(moeglicheAntworten)).append("\"").append(",")
				.append("\"").append(Arrays.toString(richtigeAntworten)).append("\"");
		return sb.toString();
	}

	@Override
	public String toString() {
		return super.toString() + "moeglicheAntworten=" + Arrays.toString(moeglicheAntworten)
				+ ", richtigeAntworten=" + Arrays.toString(richtigeAntworten) + "]";
	}

}
