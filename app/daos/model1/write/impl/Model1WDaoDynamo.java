package daos.model1.write.impl;

import play.Logger;
import util.converter.DateConverter;
import vos.Model1;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import daos.model1.write.Model1WDao;
import daos.utils.DynamoUtil;
import exceptions.SamplePersistException;

public class Model1WDaoDynamo implements Model1WDao {

	@Override
	public void create(Model1 vo) throws SamplePersistException {
		try {
			Table table = DynamoUtil.getTable("sample1");
			Item item = new Item()
			.withPrimaryKey("id", vo.id)
			.withLong("value", vo.value)
			.withBoolean("flag", vo.flag)
			.withString("dueDate", DateConverter.toStr(vo.dueDate));
			table.putItem(item);
			Logger.debug("add dynamoDB : " + vo.toString());
		} catch (AmazonClientException e) {
			Logger.error("dynamoDB create error");
			throw new SamplePersistException(e);
		}
	}

	@Override
	public void delete(String id) throws SamplePersistException {
		try {
			Table table = DynamoUtil.getTable("sample1");
			table.deleteItem("id", id);
			Logger.debug("delete dynamoDB id: " + id);
		} catch (AmazonClientException e) {
			Logger.error("dynamoDB delete error");
			throw new SamplePersistException(e);
		}
	}

	@Override
	public void update(Model1 vo) throws SamplePersistException {
		create(vo);
	}

}
