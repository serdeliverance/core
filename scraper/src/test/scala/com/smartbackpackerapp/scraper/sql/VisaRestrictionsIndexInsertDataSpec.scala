/*
 * Copyright 2017 Smart Backpacker App
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartbackpackerapp.scraper.sql

import cats.effect.IO
import com.smartbackpackerapp.common.IOAssertion
import com.smartbackpackerapp.common.sql.RepositorySpec
import com.smartbackpackerapp.model.{Count, CountryCode, Ranking, Sharing, VisaRestrictionsIndex}

class VisaRestrictionsIndexInsertDataSpec extends RepositorySpec {

  override def testDbName: String = getClass.getSimpleName

  private val countries = List(
    (CountryCode("AR"), VisaRestrictionsIndex(Ranking(1), Count(176), Sharing(1))),
    (CountryCode("DE"), VisaRestrictionsIndex(Ranking(2), Count(175), Sharing(1))),
    (CountryCode("JP"), VisaRestrictionsIndex(Ranking(3), Count(173), Sharing(2)))
  )

  test("insert visa restrictions index data") {
    IOAssertion {
      new VisaRestrictionsIndexInsertData[IO](transactor).run(countries)
    }
  }

  test("insert visa restrictions index statement") {
    check(VisaRestrictionsIndexInsertStatement.insertVisaIndex)
  }

}