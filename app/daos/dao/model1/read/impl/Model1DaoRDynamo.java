package daos.dao.model1.read.impl;

import java.util.List;

import play.Logger;
import util.converter.DateConverter;
import util.daoUtil.DynamoUtil;
import vos.Model1;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import daos.dao.model1.read.Model1RDao;
import exceptions.SamplePersistException;

public class Model1DaoRDynamo implements Model1RDao {

	@Override
	public Model1 findById(String id) throws SamplePersistException {
		try {
			Table table = DynamoUtil.getTable("sample1");
			Item item = table.getItem("id", id);
			Logger.debug("get dynamoDB : " + item.toJSONPretty());
			return toVo(item);
		} catch (AmazonClientException e) {
			Logger.error("dynamoDB find error");
			throw new SamplePersistException(e);
		}
	}

	@Override
	public List<Model1> all() throws SamplePersistException {
		// TODO Auto-generated method stub
		return null;
	}

	private Model1 toVo(Item item) {
		return new Model1(
				item.getString("id"),
				item.getLong("value"),
				item.getBoolean("flag"),
				DateConverter.toZonedDateTime(item.getString("dueDate"))
				);
	}

}
