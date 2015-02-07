package exceptions;

@SuppressWarnings("serial")
public class SampleFileException extends SampleException {

	public SampleFileException(Exception e) {
		super(e);
	}

	public SampleFileException(String s) {
		super(s);
	}

	public SampleFileException() {
		super();
	}

}
