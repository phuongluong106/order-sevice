package vn.order.shared.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.*;
import com.fasterxml.jackson.datatype.jsr310.deser.key.*;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import com.fasterxml.jackson.datatype.jsr310.ser.key.ZonedDateTimeKeySerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.*;

public class ObjectUtils {

	public static class CustomStringFieldSerializer extends StdSerializer<String> {

		public CustomStringFieldSerializer() {
			this(null);
		}

		public CustomStringFieldSerializer(Class<String> t) {
			super(t);
		}

		@Override
		public void serialize(
			String data, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
			if(data.length()>500){
				jsonGenerator.writeString(data.substring(0,500));
			}
			else {
				jsonGenerator.writeString(data);

			}
		}
	}

	public static class CustomNullSerializer<T> extends StdSerializer<T> {

		public CustomNullSerializer() {
			this(null);
		}

		public CustomNullSerializer(Class<T> t) {
			super(t);
		}

		@Override
		public void serialize(
			T data, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
				jsonGenerator.writeString(StringUtils.EMPTY);
		}
	}

	public static class CustomStringMaxlengthModule extends SimpleModule {
		public CustomStringMaxlengthModule () {
			super(PackageVersion.VERSION);
			this.addDeserializer(Instant.class, InstantDeserializer.INSTANT);
			this.addDeserializer(OffsetDateTime.class, InstantDeserializer.OFFSET_DATE_TIME);
			this.addDeserializer(ZonedDateTime.class, InstantDeserializer.ZONED_DATE_TIME);
			this.addDeserializer(Duration.class, DurationDeserializer.INSTANCE);
			this.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
			this.addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE);
			this.addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE);
			this.addDeserializer(MonthDay.class, MonthDayDeserializer.INSTANCE);
			this.addDeserializer(OffsetTime.class, OffsetTimeDeserializer.INSTANCE);
			this.addDeserializer(Period.class, JSR310StringParsableDeserializer.PERIOD);
			this.addDeserializer(Year.class, YearDeserializer.INSTANCE);
			this.addDeserializer(YearMonth.class, YearMonthDeserializer.INSTANCE);
			this.addDeserializer(ZoneId.class, JSR310StringParsableDeserializer.ZONE_ID);
			this.addDeserializer(ZoneOffset.class, JSR310StringParsableDeserializer.ZONE_OFFSET);
			this.addSerializer(Duration.class, DurationSerializer.INSTANCE);
			this.addSerializer(Instant.class, InstantSerializer.INSTANCE);
			this.addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE);
			this.addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE);
			this.addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE);
			this.addSerializer(MonthDay.class, MonthDaySerializer.INSTANCE);
			this.addSerializer(OffsetDateTime.class, OffsetDateTimeSerializer.INSTANCE);
			this.addSerializer(OffsetTime.class, OffsetTimeSerializer.INSTANCE);
			this.addSerializer(Period.class, new ToStringSerializer(Period.class));
			this.addSerializer(Year.class, YearSerializer.INSTANCE);
			this.addSerializer(YearMonth.class, YearMonthSerializer.INSTANCE);
			this.addSerializer(ZonedDateTime.class, ZonedDateTimeSerializer.INSTANCE);
			this.addSerializer(ZoneId.class, new ToStringSerializer(ZoneId.class));
			this.addSerializer(ZoneOffset.class, new ToStringSerializer(ZoneOffset.class));
			this.addKeySerializer(ZonedDateTime.class, ZonedDateTimeKeySerializer.INSTANCE);
			this.addKeyDeserializer(Duration.class, DurationKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(Instant.class, InstantKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(LocalDateTime.class, LocalDateTimeKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(LocalDate.class, LocalDateKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(LocalTime.class, LocalTimeKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(MonthDay.class, MonthDayKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(OffsetDateTime.class, OffsetDateTimeKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(OffsetTime.class, OffsetTimeKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(Period.class, PeriodKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(Year.class, YearKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(YearMonth.class, YearMothKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(ZonedDateTime.class, ZonedDateTimeKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(ZoneId.class, ZoneIdKeyDeserializer.INSTANCE);
			this.addKeyDeserializer(ZoneOffset.class, ZoneOffsetKeyDeserializer.INSTANCE);

			this.addSerializer(String.class,new CustomStringFieldSerializer());
			this.addSerializer(byte[].class, new CustomNullSerializer<>());

		}
	}

	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		.registerModule(new JavaTimeModule());

	private static ObjectMapper OBJECT_MAPPER_SERIALIZE_MAXLENGTH = new ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		.registerModule(new CustomStringMaxlengthModule());

	public static String convertObjectToJson(Object object) {
		if(object == null) {
			return null;
		}

		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	public static String convertObjectToJsonWithMaxLengthField(Object object) {
		if(object == null) {
			return null;
		}

		try {
			return OBJECT_MAPPER_SERIALIZE_MAXLENGTH.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	public static <T> T convertJsonToObject(String json, Class<T> cls) {
		try {
			return OBJECT_MAPPER.readValue(json, cls);
		} catch (IOException e) {
			return null;
		}
	}
}
