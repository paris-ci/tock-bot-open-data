/*
 *  This file is part of the tock-bot-open-data distribution.
 *  (https://github.com/voyages-sncf-technologies/tock-bot-open-data)
 *  Copyright (c) 2017 VSCT.
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.vsct.tock.bot.open.data.client.sncf.model.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalTime

/**
 *
 */
class LocalTimeDeserializer : JsonDeserializer<LocalTime>() {

    override fun deserialize(parser: JsonParser, p1: DeserializationContext): LocalTime? {
        val s = parser.valueAsString
        return if (s == null) {
            null
        } else {
            LocalTime.of(s.substring(0, 2).toInt(), s.substring(2, 4).toInt(), s.substring(4, 6).toInt())
        }
    }
}