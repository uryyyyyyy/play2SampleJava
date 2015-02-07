package daos.file.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import play.Logger;
import util.ConfigUtil;
import daos.file.Filer;
import exceptions.SampleFileException;

public class LocalFiler implements Filer {

	public static final String localDir = ConfigUtil.getConfigString("sample.file.localDir");

	@Override
	public void save(String id, File file) throws SampleFileException {
		try {
			Files.copy(file.toPath(),
					new File(localDir + id).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			Logger.error("cannot save: " + localDir + id);
			throw new SampleFileException(e);
		}
	}

	@Override
	public File find(String id) throws SampleFileException {
		File f = new File(localDir + id);
		if(f.isFile()){
			return f;
		}else{
			Logger.error("cannot get: " + localDir + id);
			throw new SampleFileException();
		}
	}

	@Override
	public List<String> list() throws SampleFileException {
		File dir = new File(localDir);
		return Arrays.asList(dir.list());
	}

}
