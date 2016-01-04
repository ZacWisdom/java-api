//package metova.java.test;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class ToJSON {

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return ToJSON::toJson;
	}
}