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

import javax.inject.{Inject, Singleton}
import play.api.mvc.Call
import controllers.routes
import pages._
import models._

@Singleton
class Navigator @Inject()() {

  private val normalRoutes: Page => UserAnswers => Call = {
    case WhatIsYourNamePage => _ => routes.WhatIsYourNINOController.onPageLoad(NormalMode)
    case WhatIsYourNINOPage => _ => routes.WhatIsYourDOBController.onPageLoad(NormalMode)
    case WhatIsYourDOBPage => _ => routes.KnowClockOrPayrollNumberController.onPageLoad(NormalMode)
    case KnowClockOrPayrollNumberPage => KowClockOrPayrollNumberRoutes
    case WhatIsYourClockOrPayrollNumberPage => _ => routes.SicknessDetailsController.onPageLoad(NormalMode)
    case SicknessDetailsPage => _ => routes.WhenDidSicknessBeginController.onPageLoad(NormalMode)
    case _ => _ => routes.IndexController.onPageLoad
  }

  private val checkRouteMap: Page => UserAnswers => Call = {
    case WhatIsYourNamePage => _ => routes.WhatIsYourNINOController.onPageLoad(CheckMode)
    case WhatIsYourNINOPage => _ => routes.WhatIsYourDOBController.onPageLoad(CheckMode)
    case WhatIsYourDOBPage => _ => routes.KnowClockOrPayrollNumberController.onPageLoad(CheckMode)
    case KnowClockOrPayrollNumberPage => KowClockOrPayrollNumberCheckRoutes
    case SicknessDetailsPage => _ => routes.WhenDidSicknessBeginController.onPageLoad(CheckMode)
    case _ => _ => routes.CheckYourAnswersController.onPageLoad
  }

  private def KowClockOrPayrollNumberRoutes(answers: UserAnswers): Call =
    answers.get(KnowClockOrPayrollNumberPage).map {
      case true => routes.WhatIsYourClockOrPayrollNumberController.onPageLoad(NormalMode)
      case false => routes.SicknessDetailsController.onPageLoad(NormalMode)
    }.getOrElse(routes.JourneyRecoveryController.onPageLoad())

  private def KowClockOrPayrollNumberCheckRoutes(answers: UserAnswers): Call =
    answers.get(KnowClockOrPayrollNumberPage).map {
      case true => routes.WhatIsYourClockOrPayrollNumberController.onPageLoad(CheckMode)
      case false => routes.SicknessDetailsController.onPageLoad(CheckMode)
    }.getOrElse(routes.JourneyRecoveryController.onPageLoad())
  def nextPage(page: Page, mode: Mode, userAnswers: UserAnswers): Call = mode match {
    case NormalMode =>
      normalRoutes(page)(userAnswers)
    case CheckMode =>
      checkRouteMap(page)(userAnswers)
  }
}
