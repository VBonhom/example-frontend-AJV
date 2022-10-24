/*
 * Copyright 2022 HM Revenue & Customs
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

package forms

import java.time.{LocalDate, ZoneOffset, ZoneId, Clock}
import java.time.format.DateTimeFormatter

import forms.behaviours.DateBehaviours
import play.api.data.FormError

class WhatIsYourDOBFormProviderSpec extends DateBehaviours {


  private val fixedInstant = LocalDate.now.atStartOfDay(ZoneId.systemDefault).toInstant
  private val clock = Clock.fixed(fixedInstant, ZoneId.systemDefault)
  private val maxDate = LocalDate.now(clock).minusYears(16)
  private def dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")

  val form = new WhatIsYourDOBFormProvider(clock)()

  ".value" - {

    val validData = datesBetween(
      min = LocalDate.of(2000, 1, 1),
      max = LocalDate.now(ZoneOffset.UTC)
    )

    behave like dateField(form, "value", validData)

    behave like mandatoryDateField(form, "value", "whatIsYourDOB.error.required.all")

    behave like dateFieldWithMax(
      form = form,
      key = "value",
      max = maxDate,
      formError = FormError("Value", "whatisYourDOB.error.beforeMax", Seq(maxDate.format(dateFormatter)))
    )
  }
}
