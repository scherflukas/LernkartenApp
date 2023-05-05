package pk.lkarten;

public class UngueltigeKarteException extends Exception {   
	private static final long serialVersionUID = 1L;
	public UngueltigeKarteException(String message) {
		super(message);
	}
}
