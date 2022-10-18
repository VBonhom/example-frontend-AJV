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

import forms.behaviours.FieldBehaviours
import play.api.data.FormError


class WhatIsYourNINOFormProviderSpec extends FieldBehaviours {

  val requiredKey = "whatIsYourNINO.error.required"
  val lengthKey = "whatIsYourNINO.error.length"
  val maxLength = 13

  val form = new WhatIsYourNINOFormProvider()()

  ".value" - {

    val fieldName = "value"gi

    behave like fieldThatBindsValidData(
      form,
      fieldName,
      arbitraryNino.arbitrary.map(_.value)
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
    "not bind values in the wrong format" in {
      val result = form.bind(Map(fieldName -> "GB123456A")).apply(fieldName)
      result.errors.head mustBe FormError(fieldName, "whatIsYourNINO.error.invalid")
    }
  }
}
