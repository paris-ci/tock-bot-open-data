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

package fr.vsct.tock.bot.open.data.story

import fr.vsct.tock.bot.open.data.SecondaryIntent.indicate_location
import fr.vsct.tock.bot.open.data.client.sncf.model.Coordinates
import fr.vsct.tock.bot.open.data.client.sncf.model.Place
import fr.vsct.tock.bot.open.data.openBot
import fr.vsct.tock.bot.test.startMock
import org.junit.Test

/**
 *
 */
class SearchTest {

    val mockedDestination = Place(
            "stop_area",
            90,
            "Lille Europe",
            "Lille Europe (Lille)",
            "stop_area:OCE:SA:87223263",
            Coordinates(50.638861, 3.075774))

    val mockedOrigin = Place(
            "stop_area",
            90,
            "Lille Europe",
            "Lille Europe (Lille)",
            "stop_area:OCE:SA:87223263",
            Coordinates(50.638861, 3.075774))

    @Test
    fun search_shouldAskForDestination_WhenNoDestinationInContext() {
        val bus = openBot.startMock(search)

        bus.firstAnswer.assertText("Pour quelle destination?")
    }

    @Test
    fun search_shouldAskForOrigin_WhenThereIsDestinationButNoOriginInContext() {
        val bus = openBot.startMock(search)

        with(bus) {
            firstAnswer.assertText("Pour quelle destination?")

            destination = mockedDestination

            run()

            secondAnswer.assertText("Pour quelle origine?")
        }
    }

    @Test
    fun search_shouldAskForDepartureDate_WhenThereIsDestinationAndOriginButNoDepartureDateInContext() {
        val bus = openBot.startMock(search)

        with(bus) {
            firstAnswer.assertText("Pour quelle destination?")

            destination = mockedDestination

            run()

            secondAnswer.assertText("Pour quelle origine?")

            intent = indicate_location
            location = mockedOrigin

            run()

            thirdAnswer.assertText("Quand souhaitez-vous partir?")
        }
    }

}