package pk.lkarten;

import java.io.FileNotFoundException;

public interface CsvExportable {
	public abstract String exportiereAlsCsv() throws FileNotFoundException;
}
