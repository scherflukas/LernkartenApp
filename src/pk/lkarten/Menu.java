package pk.lkarten;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Menu {
	private LernkarteiHashSet<Lernkarte> kartei;

	public Menu(LernkarteiHashSet<Lernkarte> lernkartei) {
		this.kartei = lernkartei;
	}

	public LernkarteiHashSet<Lernkarte> getLernkartei() {
		return kartei;
	}

	public void setLernkartei(LernkarteiHashSet<Lernkarte> kartei) {
		this.kartei = kartei;
	}

	public void displayMenu() {
		int i = 0;
		String c = "";
		try (Scanner scan = new Scanner(System.in);) {
			while (i != 8) {
				System.out.print("Lernkarten-Manager   \n\n" + "1. Lernen!   \n"
						+ "2. Einzelantwortkarte hinzufügen   \n" + "3. Drucke alle Karten   \n"
						+ "4. Drucke Karten zu Kategorie   \n" + "5. CSV-Export \n" + "6. Lade aus Datei\n"
						+ "7. Speichere in Datei\n" + "8. Beenden   \nBitte Aktion wählen: \n");
				c = scan.nextLine();
				if (c.length() != 0) {
					if (c.length() > 1 || !Character.isDigit(c.charAt(0))) {
						System.out.print("Ungültiger Eingabewert! Bitte eine Zahl zwischen 1 und 8 eingeben\n");
					} else {
						i = Integer.parseInt(c);
						if (i < 1 || i > 8) {
							System.out.print("Ungültiger Eingabewert! Bitte eine Zahl zwischen 1 und 8 eingeben\n");
						}
						if (i == 1) {
							lernen();
						}
						if (i == 2) {
							einzelAntwortkarteHinzufuegen();
						}
						if (i == 3) {
							try {
								kartei.druckeAlleKarten(System.out);
							} catch (IOException e) {
								displayException(e);
							}
						}
						if (i == 4) {
							kartenZuKategorieDrucken();
						}
						if (i == 5) {
							exportCSV();
						}
						if (i == 6) {
							ladeAusDatei();
						}
						if (i == 7) {
							speichereInDatei();
						}
						if (i == 8) {
							break;
						}
					}
				}
			}
		}
	}

	private void lernen() {
		Lernkarte[] deck = kartei.erzeugeDeck(5);
		for (Lernkarte lk : deck) {
			try {
				lk.zeigeVorderseite(System.out);
			} catch (IOException e) {
				displayException(e);
			}
			System.out.print("<Drücken Sie Enter, um die Rückseite der Karte zu sehen.>\n");
			warteAufEnter();
			try {
				lk.zeigeRueckseite(System.out);
			} catch (IOException e) {
				displayException(e);
			}
			System.out.print("<Drücken Sie Enter, um die nächste Karte zu sehen.>\n");
			warteAufEnter();
		}
		System.out.print("<Alle Karten betrachtet.>\n");
	}

	private void einzelAntwortkarteHinzufuegen() {
		String kategorie = JOptionPane.showInputDialog(null, "Bitte geben Sie eine Kategorie ein:");
		String titel = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Titel ein:");
		String frage = JOptionPane.showInputDialog(null, "Bitte geben Sie eine Frage ein:");
		String antwort = JOptionPane.showInputDialog(null, "Bitte geben Sie eine Antwort ein:");
		EinzelantwortKarte karte = new EinzelantwortKarte(kategorie, titel, frage, antwort);
		try {
			karte.validiere();
			kartei.hinzufuegen(karte);
		} catch (UngueltigeKarteException e) {
			displayException(e);
		}
	}

	private void kartenZuKategorieDrucken() {
		String kategorie = JOptionPane.showInputDialog(null, "Bitte geben Sie eine Kategorie ein:");
		Lernkarte[] karten = kartei.gibKartenZuKategorie(kategorie);
		for (Lernkarte lk : karten) {
			try {
				lk.druckeKarte(System.out);
			} catch (IOException e) {
				displayException(e);
			}
		}
	}

	private void warteAufEnter() {
		try {
			System.in.read(new byte[2]);
		} catch (IOException e) {
			System.err.println("Fehler: " + e.getMessage());
		}
	}

	public void displayException(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
		e.printStackTrace();
	}

	private void exportCSV() {
		String name = "";
		int ow = 0;
		try {
			do {
				name = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Dateinamen ein:");
				if (name != null && name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Keine Eingabe!");
				}
				if (name != null && !name.isEmpty()) {
					File csvFile = new File(name + ".csv");
					if (csvFile.exists() && !csvFile.isDirectory()) {
						// 0=yes, 1=no, 2=cancel
						ow = JOptionPane.showConfirmDialog(null, "Datei existiert bereits, ueberschreiben?");
					}
					if (ow == 0) {
						kartei.exportiereEintraegeAlsCsv(csvFile);
						break;
					}
				}
			} while (name != null && name.isEmpty() || ow != 0);
		} catch (FileNotFoundException e) {
			displayException(e);
		} catch (IOException e) {
			displayException(e);
		}
	}

	private void speichereInDatei() {
		String name = "";
		int c = 1;
		do {
			name = JOptionPane.showInputDialog(null, "Bitte geben Sie den Namen der Datei ein:");
			if (name == null) {
				name = "";
				c = 2;
			}
			if ((new File(name)).exists()) {
				c = JOptionPane.showConfirmDialog(null, "Datei existiert bereits, ueberschreiben?");
			}
			if (!(new File(name)).exists()) {
				c = 0;
			}
		} while (c == 1 || c == 2);
		if (c == 0) {
			try {
				kartei.writeObject(new File(name));
			} catch (FileNotFoundException e) {
				displayException(e);
			} catch (IOException e) {
				displayException(e);
			}
		}
	}

	private void ladeAusDatei() {
		String name = "";
		int c = 0;
		do {
			name = JOptionPane.showInputDialog(null, "Bitte geben Sie den Namen der Datei ein:");
			if (name == null) {
				name = "";
				c = 2;
			}
			if ((new File(name)).exists() && !(new File(name).isDirectory())) {
				c = 1;
			}
		} while (c == 0);
		if (c == 1) {
			try {
				kartei.readObject(new File(name));
			} catch (FileNotFoundException e) {
				displayException(e);
			} catch (ClassNotFoundException e) {
				displayException(e);
			} catch (IOException e) {
				displayException(e);
			}
		}
	}
}
