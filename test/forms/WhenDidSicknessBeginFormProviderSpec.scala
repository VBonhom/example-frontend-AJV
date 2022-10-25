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

import java.time.{Clock, LocalDate, ZoneId, ZoneOffset}
import forms.behaviours.DateBehaviours
import play.api.data.FormError

import java.time.format.DateTimeFormatter

class WhenDidSicknessBeginFormProviderSpec extends DateBehaviours {

  val fixedInstant = LocalDate.now.atStartOfDay(ZoneId.systemDefault).toInstant
  val clock = Clock.fixed(fixedInstant, ZoneId.systemDefault)
  val maxDate = LocalDate.now(clock)
  val minDate = LocalDate.now(clock).minusYears(1)

  def dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy")


  val form = new WhenDidSicknessBeginFormProvider(clock)()

  ".value" - {

    val validData = datesBetween(
      min = LocalDate.now().minusYears(1),
      max = LocalDate.now(ZoneOffset.UTC)
    )

    behave like dateField(form, "value", validData)

    behave like mandatoryDateField(form, "value", "whenDidSicknessBegin.error.required.all")

    behave like dateFieldWithMin(
      form = form,
      key = "value",
      min = minDate,
      formError = FormError("value", "whenDidSicknessBegin.error.beforeMin", Seq(minDate.format(dateFormatter)))
    )

    behave like dateFieldWithMax(
      form = form,
      key = "value",
      max = maxDate,
      formError = FormError("value", "whenDidSicknessBegin.error.afterMax", Seq(maxDate.format(dateFormatter)))
    )
  }
}
