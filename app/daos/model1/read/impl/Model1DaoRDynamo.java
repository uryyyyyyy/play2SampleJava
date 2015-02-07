package daos.model1.read.impl;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import play.Logger;
import util.converter.DateConverter;
import vos.Model1;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

import daos.model1.read.Model1RDao;
import daos.utils.DynamoUtil;
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
		try {
			Table table = DynamoUtil.getTable("sample1");

			QuerySpec querySpec = new QuerySpec()
			.withHashKey("id", "jjj");//TODO
			ItemCollection<QueryOutcome> items = table.query(querySpec);
			Logger.debug("get dynamoDB : all");
			return toVo(items);
		} catch (AmazonClientException e) {
			Logger.error("dynamoDB find error");
			throw new SamplePersistException(e);
		}
	}

	private List<Model1> toVo(ItemCollection<QueryOutcome> items) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
				items.iterator(), Spliterator.ORDERED), false)
				.map(i -> toVo(i))
				.collect(Collectors.toList());
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
