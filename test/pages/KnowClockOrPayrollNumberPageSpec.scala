package pages

import pages.behaviours.PageBehaviours

class KnowClockOrPayrollNumberPageSpec extends PageBehaviours {

  "KnowClockOrPayrollNumberPage" - {

    beRetrievable[Boolean](KnowClockOrPayrollNumberPage)

    beSettable[Boolean](KnowClockOrPayrollNumberPage)

    beRemovable[Boolean](KnowClockOrPayrollNumberPage)
  }
}
