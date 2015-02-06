package exceptions;

@SuppressWarnings("serial")
public class SampleMailException extends SampleException {

	public SampleMailException(Exception e) {
		super(e);
	}

	public SampleMailException(String s) {
		super(s);
	}

}
