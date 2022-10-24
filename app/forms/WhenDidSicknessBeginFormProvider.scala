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

import java.time.LocalDate
import java.time.{Clock, LocalDate}
import forms.mappings.Mappings
import javax.inject.Inject
import play.api.data.Form
import java.time.format.DateTimeFormatter

class WhenDidSicknessBeginFormProvider @Inject()(clock:Clock) extends Mappings {

  val maxDate: LocalDate = LocalDate.now(clock)
  val minDate: LocalDate = LocalDate.now(clock).minusYears(1)
  def dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy")

  def apply(): Form[LocalDate] =
    Form(
      "value" -> localDate(
        invalidKey     = "whenDidSicknessBegin.error.invalid",
        allRequiredKey = "whenDidSicknessBegin.error.required.all",
        twoRequiredKey = "whenDidSicknessBegin.error.required.two",
        requiredKey    = "whenDidSicknessBegin.error.required"
      ).verifying(
        minDate(minDate, "WhenDidSicknessBegin.error.beforeMin", minDate.format(dateFormatter)),
        maxDate(maxDate, "WhenDidSicknessBegin.error.afterMax", maxDate.format(dateFormatter))
      )
    )
}
