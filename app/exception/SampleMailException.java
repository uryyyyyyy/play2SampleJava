package exception;



@SuppressWarnings("serial")
public class SampleMailException extends RuntimeException {

	public SampleMailException(Exception e) {
		super(e);
	}

}
