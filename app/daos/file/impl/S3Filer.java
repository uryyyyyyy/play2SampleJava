package daos.file.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import play.Logger;
import util.ConfigUtil;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import daos.file.Filer;
import exceptions.SampleFileException;

public class S3Filer implements Filer {

	private static final AmazonS3Client s3Client = init();
	private static final String s3BucketName = ConfigUtil.getConfigString("sample.file.s3Bucket");

	private static AmazonS3Client init() {
		String user = ConfigUtil.getConfigString("sample.file.s3User");
		ProfileCredentialsProvider credentials = new ProfileCredentialsProvider(user);
		AmazonS3Client client = new AmazonS3Client(credentials);
		return client;
	}

	@Override
	public void save(String id, File file) throws SampleFileException {
		try {
			s3Client.putObject(new PutObjectRequest(s3BucketName, id, file));
		} catch (AmazonClientException e) {
			Logger.error("cannot save: " + id);
			throw new SampleFileException(e);
		}
	}

	@Override
	public File find(String id) throws SampleFileException {
		try {
			S3Object obj = s3Client.getObject(new GetObjectRequest(s3BucketName, id));
			File f = toFile(obj, id);
			obj.close();
			return f;
		} catch (IOException | AmazonClientException e) {
			throw new SampleFileException(e);
		}
	}

	private static File toFile(S3Object obj, String id) throws IOException{
		File f = new File("tmp/"+id);
		InputStream objStream = obj.getObjectContent();
		@SuppressWarnings("resource")
		OutputStream out = new FileOutputStream(f);
		byte[] buffer = new byte[1024]; // Adjust if you want
		int bytesRead;
		while ((bytesRead = objStream.read(buffer)) != -1){
			out.write(buffer, 0, bytesRead);
		}
		objStream.close();
		return f;
	}

	/**
	 * 1000件以上ある場合は修正が必要。
	 */
	@Override
	public List<String> list() throws SampleFileException {
		ListObjectsRequest req = new ListObjectsRequest();
		req.setBucketName(s3BucketName);
		List<S3ObjectSummary> list = s3Client.listObjects(req).getObjectSummaries();
		return list.stream().map(v -> v.getKey()).collect(Collectors.toList());
	}

}
