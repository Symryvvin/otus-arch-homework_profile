package ru.aizen.profile.domain.user;

import ru.aizen.profile.domain.user.Phone;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PhoneConverter implements AttributeConverter<Phone, Long> {

	@Override
	public Long convertToDatabaseColumn(Phone phone) {
		return phone.getPhoneNumber();
	}

	@Override
	public Phone convertToEntityAttribute(Long number) {
		return Phone.from(number);
	}

}
