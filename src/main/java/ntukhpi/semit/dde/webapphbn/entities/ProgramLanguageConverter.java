package ntukhpi.semit.dde.webapphbn.entities;

import javax.persistence.AttributeConverter;

public class ProgramLanguageConverter implements AttributeConverter<ProgramLanguage, Integer>
         {
    @Override
    public Integer convertToDatabaseColumn(ProgramLanguage pl) {
        return switch (pl) {
            case JAVA -> 1;
            case CSHARP -> 2;
            case PYTHON -> 3;
            case JavaScript -> 4;
        };
    }

    @Override
    public ProgramLanguage convertToEntityAttribute(Integer codPL) {
        return switch (codPL) {
            case 1 -> ProgramLanguage.JAVA;
            case 2 -> ProgramLanguage.CSHARP;
            case 3 -> ProgramLanguage.PYTHON;
            case 4 -> ProgramLanguage.JavaScript;
            default -> throw new IllegalStateException("Unexpected value: " + codPL);
        };
    }
}
