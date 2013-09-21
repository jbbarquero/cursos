package com.malsolo.autentia.cursos.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;

import com.malsolo.autentia.cursos.domain.Profesor;
import com.malsolo.autentia.cursos.persistence.ProfesorMapper;

@Component
public class ProfesorFormatterRegistrar implements FormatterRegistrar {

	@Autowired
	private ProfesorMapper profesorMapper;
	
	@Override
	public void registerFormatters(FormatterRegistry registry) {
		registry.addConverter(new IdAsLongToProfesorConverter());
		registry.addConverter(new IdAsStringToProfesorConverter());
	}

	private class IdAsLongToProfesorConverter implements Converter<Long, Profesor> {

		@Override
		public Profesor convert(Long id) {
			return profesorMapper.findById(id);
		}
	}
	
	private class IdAsStringToProfesorConverter implements Converter<String, Profesor> {

		@Override
		public Profesor convert(String id) {
			return profesorMapper.findById(Long.valueOf(id));
		}
		
	}
}
