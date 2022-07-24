package team.avengers.chamomile.domain.common

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class YnConverter : AttributeConverter<Boolean, String> {
    override fun convertToDatabaseColumn(attribute: Boolean): String = if (attribute) "Y" else "N"

    override fun convertToEntityAttribute(dbData: String): Boolean = dbData == "Y"
}
