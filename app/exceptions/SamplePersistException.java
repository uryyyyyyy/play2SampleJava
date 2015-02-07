package exceptions;

@SuppressWarnings("serial")
public class SamplePersistException extends SampleException {

	public SamplePersistException(Exception e) {
		super(e);
	}

	public SamplePersistException(String s) {
		super(s);
	}

}
