package exception;



@SuppressWarnings("serial")
public class SampleMailException extends RuntimeException {

	public SampleMailException(Exception e) {
		super(e);
	}

	public SampleMailException(String s) {
		super(s);
	}

}
