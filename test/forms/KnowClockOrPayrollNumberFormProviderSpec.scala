package forms

import forms.behaviours.BooleanFieldBehaviours
import play.api.data.FormError

class KnowClockOrPayrollNumberFormProviderSpec extends BooleanFieldBehaviours {

  val requiredKey = "knowClockOrPayrollNumber.error.required"
  val invalidKey = "error.boolean"

  val form = new KnowClockOrPayrollNumberFormProvider()()

  ".value" - {

    val fieldName = "value"

    behave like booleanField(
      form,
      fieldName,
      invalidError = FormError(fieldName, invalidKey)
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}
