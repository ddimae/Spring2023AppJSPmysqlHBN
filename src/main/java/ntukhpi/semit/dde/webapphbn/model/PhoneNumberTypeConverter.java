package ntukhpi.semit.dde.webapphbn.model;

import javax.persistence.AttributeConverter;

public class PhoneNumberTypeConverter implements AttributeConverter<PhoneNumberType, Integer>
         {
    @Override
    public Integer convertToDatabaseColumn(PhoneNumberType phoneNumberType) {
        return switch (phoneNumberType) {
            case HOME -> 1;
            case MOBILE -> 2;
            case OFFICE -> 3;
        };
    }

    @Override
    public PhoneNumberType convertToEntityAttribute(Integer typeNumber) {
        return switch (typeNumber) {
            case 1 -> PhoneNumberType.HOME;
            case 2 -> PhoneNumberType.MOBILE;
            case 3 -> PhoneNumberType.OFFICE;
            default -> throw new IllegalStateException("Unexpected value: " + typeNumber);
        };
    }
}
