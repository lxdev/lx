package net.lx.common.convert;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//import org.codehaus.jackson.JsonFactory;
//import org.codehaus.jackson.JsonGenerator;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	// private static ObjectMapper mapper = new ObjectMapper();
	//
	// public static<T> String bean2Json(List<T> list) throws IOException {
	// StringWriter sw = new StringWriter();
	// JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
	// mapper.writeValue(gen, list);
	// gen.close();
	// return sw.toString();
	// }
	//
	// public static <T> T json2Bean(String jsonStr, Class<T> objClass)
	// throws JsonParseException, JsonMappingException, IOException {
	// return mapper.readValue(jsonStr, objClass);
	// }

	public static <T> String array2Json(List<T> list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	public static String bean2Json(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
		return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
	}
}
