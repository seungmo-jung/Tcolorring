package com.wizontech.kct.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

public class Json {
	public final static ObjectMapper mapper = new ObjectMapper();
	public final static MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
	public final static CollectionType mapCollectionType = mapper.getTypeFactory().constructCollectionType(List.class, mapType);

	public static String stringify(Object o) {
		String result = null;
		try {
			result = mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	public static String stringify(InputStream stream) {
		StringWriter writer = new StringWriter();
		String result = null;

		try {
			IOUtils.copy(stream, writer, "utf-8");
			String str = writer.toString();
			result = mapper.writeValueAsString(str);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	public static <T> T parse(String json, Class<T> clazz) {
		T result = null;

		if (StringUtils.isEmpty(json))
			return null;
		try {
			result = mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	public static Map<String, Object> parse(String json) {
		if (StringUtils.isEmpty(json))
			return null;

		Map<String, Object> result = null;

		try {
			result = mapper.readValue(json, mapType);
		} catch (JsonParseException e) {

			throw new RuntimeException(e);
		} catch (JsonMappingException e) {

			throw new RuntimeException(e);
		} catch (IOException e) {

			throw new RuntimeException(e);
		}

		return result;
	}

	public static JsonNode parseAsTree(String json) {
		if (StringUtils.isEmpty(json))
			return null;

		JsonNode result = null;

		try {
			result = mapper.readTree(json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	@Deprecated
	public static <T> List<T> parseArrayList(String json, Class<T> dataClazz) {
		return parseList(json, dataClazz);
	}

	public static <T> List<T> parseList(String json, Class<T> dataClazz) {
		if (StringUtils.isEmpty(json))
			return null;

		List<T> result = null;

		try {
			result = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, dataClazz));
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	public static List<Map<String, Object>> parseMapList(String json) {
		if (StringUtils.isEmpty(json))
			return null;

		List<Map<String, Object>> result = null;

		try {
			result = mapper.readValue(json, mapCollectionType);
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return result;
	}
}