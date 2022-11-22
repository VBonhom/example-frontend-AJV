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

package generators

import org.scalacheck.Arbitrary
import pages._

trait PageGenerators {

  implicit lazy val arbitraryWhatIsYourClockOrPayrollNumberPage: Arbitrary[WhatIsYourClockOrPayrollNumberPage.type] =
    Arbitrary(WhatIsYourClockOrPayrollNumberPage)

  implicit lazy val arbitraryKnowClockOrPayrollNumberPage: Arbitrary[KnowClockOrPayrollNumberPage.type] =
    Arbitrary(KnowClockOrPayrollNumberPage)

  implicit lazy val arbitrarySicknessDetailsPage: Arbitrary[SicknessDetailsPage.type] =
    Arbitrary(SicknessDetailsPage)

  implicit lazy val arbitraryHasYourSicknessEndedPage: Arbitrary[HasYourSicknessEndedPage.type] =
    Arbitrary(HasYourSicknessEndedPage)

  implicit lazy val arbitraryWhenDidSicknessBeginPage: Arbitrary[WhenDidSicknessBeginPage.type] =
    Arbitrary(WhenDidSicknessBeginPage)

  implicit lazy val arbitraryWhatIsYourDOBPage: Arbitrary[WhatIsYourDOBPage.type] =
    Arbitrary(WhatIsYourDOBPage)

  implicit lazy val arbitraryWhatIsYourNINOPage: Arbitrary[WhatIsYourNINOPage.type] =
    Arbitrary(WhatIsYourNINOPage)

  implicit lazy val arbitraryWhatIsYourNamePage: Arbitrary[WhatIsYourNamePage.type] =
    Arbitrary(WhatIsYourNamePage)
}
