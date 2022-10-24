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

import java.time.{LocalDate, Clock}
import forms.mappings.Mappings
import javax.inject.Inject
import play.api.data.Form
import java.time.format.DateTimeFormatter

class WhatIsYourDOBFormProvider @Inject()(clock:Clock) extends Mappings {

  val maxDate: LocalDate = LocalDate.now(clock).minusYears(16)
  def dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy")

  def apply(): Form[LocalDate] =
    Form(
      "value" -> localDate(
        invalidKey     = "whatIsYourDOB.error.invalid",
        allRequiredKey = "whatIsYourDOB.error.required.all",
        twoRequiredKey = "whatIsYourDOB.error.required.two",
        requiredKey    = "whatIsYourDOB.error.required"
      ).verifying(maxDate(maxDate, "whatIsYourDOB.error.beforeMax", maxDate.format(dateFormatter)))
    )
}
