package exception;


@SuppressWarnings("serial")
public class SampleException extends RuntimeException {

	public SampleException(Exception e) {
		super(e);
	}

	public SampleException() {
		super();
	}

	public SampleException(String s) {
		super(s);
	}

}
