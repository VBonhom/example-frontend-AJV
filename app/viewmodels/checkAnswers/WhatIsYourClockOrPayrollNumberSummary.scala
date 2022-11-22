package viewmodels.checkAnswers

import controllers.routes
import models.{CheckMode, UserAnswers}
import pages.WhatIsYourClockOrPayrollNumberPage
import play.api.i18n.Messages
import play.twirl.api.HtmlFormat
import uk.gov.hmrc.govukfrontend.views.viewmodels.summarylist.SummaryListRow
import viewmodels.govuk.summarylist._
import viewmodels.implicits._

object WhatIsYourClockOrPayrollNumberSummary  {

  def row(answers: UserAnswers)(implicit messages: Messages): Option[SummaryListRow] =
    answers.get(WhatIsYourClockOrPayrollNumberPage).map {
      answer =>

        SummaryListRowViewModel(
          key     = "whatIsYourClockOrPayrollNumber.checkYourAnswersLabel",
          value   = ValueViewModel(HtmlFormat.escape(answer).toString),
          actions = Seq(
            ActionItemViewModel("site.change", routes.WhatIsYourClockOrPayrollNumberController.onPageLoad(CheckMode).url)
              .withVisuallyHiddenText(messages("whatIsYourClockOrPayrollNumber.change.hidden"))
          )
        )
    }
}
