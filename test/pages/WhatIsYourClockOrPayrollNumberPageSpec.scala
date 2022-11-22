package pages

import pages.behaviours.PageBehaviours


class WhatIsYourClockOrPayrollNumberPageSpec extends PageBehaviours {

  "WhatIsYourClockOrPayrollNumberPage" - {

    beRetrievable[String](WhatIsYourClockOrPayrollNumberPage)

    beSettable[String](WhatIsYourClockOrPayrollNumberPage)

    beRemovable[String](WhatIsYourClockOrPayrollNumberPage)
  }
}
