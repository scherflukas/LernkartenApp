package pk.lkarten;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeSet;

public class LernkarteiHashSet<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	private HashSet<Lernkarte> lernkarten;

	public LernkarteiHashSet() {
		this.lernkarten = new HashSet<Lernkarte>();
	}

	public void hinzufuegen(Lernkarte karte) throws UngueltigeKarteException {
		karte.validiere();
		this.lernkarten.add(karte);
	}

//	public void druckeAlleKarten(OutputStream stream) throws IOException {
//		for (Lernkarte lk : new TreeSet<>(lernkarten).descendingSet()) {
//			if (lk != null) {
//				lk.druckeKarte(stream);
//			}
//		}
	// }

	public void druckeAlleKarten(OutputStream stream) throws IOException {
		List<Lernkarte> alleKarten = new LinkedList<Lernkarte>();
		for (Lernkarte lk : lernkarten) {
			alleKarten.add(lk);
		}
		Collections.sort(alleKarten, new LernkartenComparator());
		for (Lernkarte lk : alleKarten) {
			lk.druckeKarte(stream);
		}
	}

	public int gibAnzahlKarten() {
		List<Lernkarte> ll = new LinkedList<Lernkarte>();
		for (Lernkarte lk : lernkarten)
			ll.add(lk);
		return ll.size();
	}

	
	public Lernkarte[] gibKartenZuKategorie(String kategorie) {
		List<Lernkarte> kartenKategorischLL = new LinkedList<Lernkarte>();
		for (Lernkarte lk : lernkarten) {
			if (lk.getKategorie().equals(kategorie)) {
				kartenKategorischLL.add(lk);
			}
		}
		Collections.sort(kartenKategorischLL, new LernkartenComparator());
		Lernkarte[] kartenKategorisch = new Lernkarte[kartenKategorischLL.size()];
		kartenKategorischLL.toArray(kartenKategorisch);
		return kartenKategorisch;
	}

	public Lernkarte[] erzeugeDeck(int anzahlKarten) {
		if (lernkarten.size() > 0) {
			Lernkarte[] deck = new Lernkarte[anzahlKarten];
			Random rand = new Random();
			Lernkarte[] kartenarray = lernkarten.toArray(new Lernkarte[lernkarten.size()]);
			for (int i = 0; i < deck.length; i++)
				deck[i] = kartenarray[rand.nextInt(kartenarray.length)];
			return deck;
		}
		return new Lernkarte[0];
	}

	public void exportiereEintraegeAlsCsvNio(Path datei) throws FileNotFoundException {
		File csvFile = datei.toFile();
		PrintWriter out = new PrintWriter(csvFile);
		out.println("ID,Kategorie,Titel,Frage,Antwort(en),Richtige Antwort(en)");
		// for (Lernkarte lk : descendingOrder(lernkarten)) {
		for (Lernkarte lk : new TreeSet<>(lernkarten)) {
			out.println(lk.exportiereAlsCsv());
			lk.exportiereAlsCsv();
		}
		out.close();
	}

	public void exportiereEintraegeAlsCsv(File f) throws IOException {
		try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
			pw.println("ID,Kategorie,Titel,Frage,Antwort(en),Richtige Antwort(en)");
			for (Lernkarte lk : new TreeSet<>(lernkarten)) {
				pw.println(lk.exportiereAlsCsv());
				lk.exportiereAlsCsv();
			}
			pw.close();
		}
	}

	public void writeObject(File f) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))) {
			oos.writeObject(lernkarten);
		}
	}

	@SuppressWarnings("unchecked")
	public void readObject(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
			lernkarten = (HashSet<Lernkarte>) ois.readObject();
			int counter = 0;
			for (Lernkarte lk : lernkarten) {
				if (lk.getID() > counter) {
					counter = lk.getID();
				}
			}
			Lernkarte.setCounter(++counter); 
		}
	}
	
	public Iterator<Lernkarte> getIterator() {
		Iterator<Lernkarte> it = lernkarten.iterator(); {
			return it; 
		}
	}}
