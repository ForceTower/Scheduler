package com.forcetower.scheduler.core.service

import com.google.gson.JsonDeserializer
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object GsonAdapters {
    @JvmStatic
    val ZONED_TO_STRING: JsonDeserializer<ZonedDateTime> = JsonDeserializer { json, _, _ ->
        val primitive = json.asJsonPrimitive
        if (primitive.isString) {
            val patterns = arrayOf(
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd'T'HH:mmX",
                "yyyy-MM-dd'T'HH:mm:ssX",
                "yyyy-MM-dd'T'HH:mmZ",
                "yyyy-MM-dd'T'HH:mm:ssZ",
                "yyyy-MM-dd'T'HH:mm:ssZ"
            )
            for (pattern in patterns) {
                try {
                    val parser = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("America/Bahia"))
                    return@JsonDeserializer ZonedDateTime.parse(primitive.asString, parser)
                } catch (t: Throwable) { }
            }
        }
        throw IllegalStateException("")
    }
}