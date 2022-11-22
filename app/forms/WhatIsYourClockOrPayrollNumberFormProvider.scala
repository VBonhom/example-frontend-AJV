package forms

import javax.inject.Inject

import forms.mappings.Mappings
import play.api.data.Form

class WhatIsYourClockOrPayrollNumberFormProvider @Inject() extends Mappings {

  def apply(): Form[String] =
    Form(
      "value" -> text("whatIsYourClockOrPayrollNumber.error.required")
        .verifying(maxLength(100, "whatIsYourClockOrPayrollNumber.error.length"))
    )
}
