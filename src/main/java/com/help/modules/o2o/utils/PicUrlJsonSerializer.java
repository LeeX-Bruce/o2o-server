package com.help.modules.o2o.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PicUrlJsonSerializer extends JsonSerializer<String>{
	
	@Value("${fileupload.server}")
	String fileServer;

	@Override
	public void serialize(String value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(fileServer + value);
	}

}
