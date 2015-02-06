package exception;


@SuppressWarnings("serial")
public class SampleJsonException extends SampleException {

	public SampleJsonException(Exception e) {
		super(e);
	}

	public SampleJsonException() {
		super();
	}

}
