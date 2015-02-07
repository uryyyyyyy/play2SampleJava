package daos.utils;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DynamoUtil{

	public static final DynamoDB dynamoDB = init();

	private static DynamoDB init() {
		ProfileCredentialsProvider credentials = new ProfileCredentialsProvider("uryyyyyyy");
		AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentials);
		client.setEndpoint("http://dynamodb.ap-northeast-1.amazonaws.com");
		return new DynamoDB(client);
	}

	public static Table getTable(String tableName) {
		return dynamoDB.getTable(tableName);
	}

}
