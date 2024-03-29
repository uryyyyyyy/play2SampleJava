package domain.database;

import com.fasterxml.jackson.databind.JsonNode;
import daos.DaoFactory;
import dtos.Model1Dto;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http.HeaderNames;
import play.mvc.Result;
import util.converter.DateConverter;
import util.converter.JsonConverter;
import vos.Model1;

public class AjaxApiSample extends Controller {

	public static Result post() {
		JsonNode json = request().body().asJson();
		Logger.info(json.toString());

		Model1Dto d = new Model1Dto("id", 100, true, "dd");

		Model1Dto dto = JsonConverter.toPojo(JsonConverter.toJson(d));
		Logger.info(dto.toString());
		Model1 vo = new Model1(
				"idSample",
				dto.value,
				dto.flag,
				DateConverter.toZonedDateTime(dto.dueDate)
		);
		DaoFactory.model1WDao.create(vo);

		response().setHeader(HeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, " *");
		return ok("posted");
	}

	public static Result get() {
		Model1 vo = DaoFactory.model1RDao.findById("idSample");
		Model1Dto dto = new Model1Dto(
				vo.id, vo.value, vo.flag, DateConverter.toStr(vo.dueDate));
		return ok(JsonConverter.toJson(dto));
	}

}
