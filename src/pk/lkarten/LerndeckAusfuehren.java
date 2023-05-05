package pk.lkarten;

import java.util.LinkedList;
import java.util.List;

import javafx.stage.Stage;
import pk.lkarten.ui.EinzelAntwortErfassungView;
import pk.lkarten.ui.LernkartenApp;

public class LerndeckAusfuehren {

	public static void main(String[] args) {
		LernkarteiHashSet kartei = new LernkarteiHashSet();
		String[] maJavaM1 = { "public abstract", "protected static", "package", "public default", "private" };
		int[] raJavaM1 = { 0, 3, 4 };
		String[] maJavaM2 = { "Ein Konstruktor ist eine spezielle Methode zur Initialisierung von Objekten.",
				"Ein Konstruktor ist ein Supportmitarbeiter welcher mir bei Abschluss eines Premiumabos hilft meine Javaprogramme richtig aufzubauen." };
		int[] raJavaM2 = { 0 };
		Menu men = new Menu(kartei);
		System.out.print("Anzahl der Karten in der Kartei: " + kartei.gibAnzahlKarten() + "\n");
		men.displayMenu();

	}

}