package daos.file;

import java.io.File;
import java.util.List;

import exceptions.SampleFileException;


public interface Filer {

	public void save(String id, File file) throws SampleFileException;

	public File find(String id) throws SampleFileException;
	
	public List<String> list() throws SampleFileException;

}
