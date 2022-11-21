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

package navigation

import base.SpecBase
import controllers.routes
import pages._
import models._

class NavigatorSpec extends SpecBase {

  val navigator = new Navigator

  "Navigator" - {

    "in Normal mode" - {

      "must go from a page that doesn't exist in the route map to Index" in {

        case object UnknownPage extends Page
        navigator.nextPage(UnknownPage, NormalMode, UserAnswers("id")) mustBe routes.IndexController.onPageLoad
      }

      "must go from what is your name page to what is your NINO page" in {
        navigator.nextPage(WhatIsYourNamePage, NormalMode, UserAnswers("id")) mustBe routes.WhatIsYourNINOController.onPageLoad(NormalMode)
      }

      "must go from what is your NINO to what is your DOB" in {
        navigator.nextPage(WhatIsYourNINOPage, NormalMode, UserAnswers("id")) mustBe routes.WhatIsYourDOBController.onPageLoad(NormalMode)
      }

      "must go from what is your DOB to do you know your clock or payroll number" in {
        navigator.nextPage(WhatIsYourDOBPage, NormalMode, UserAnswers("id")) mustBe routes.KnowClockOrPayrollNumberController.onPageLoad(NormalMode)
      }
    }

    "in Check mode" - {

      "must go from a page that doesn't exist in the edit route map to CheckYourAnswers" in {

        case object UnknownPage extends Page
        navigator.nextPage(UnknownPage, CheckMode, UserAnswers("id")) mustBe routes.CheckYourAnswersController.onPageLoad
      }

      "must go from what is your name page to what is your NINO page" in {
        navigator.nextPage(WhatIsYourNamePage, CheckMode, UserAnswers("id")) mustBe routes.WhatIsYourNINOController.onPageLoad(CheckMode)
      }

      "must got from what is your NINO to what is your DOB" in {
        navigator.nextPage(WhatIsYourNINOPage, CheckMode, UserAnswers("id")) mustBe routes.WhatIsYourDOBController.onPageLoad(CheckMode)
      }

      "must go from what is your DOB to do you know your clock or payroll number" in {
        navigator.nextPage(WhatIsYourDOBPage, CheckMode, UserAnswers("id")) mustBe routes.KnowClockOrPayrollNumberController.onPageLoad(CheckMode)
      }
    }
  }
}
