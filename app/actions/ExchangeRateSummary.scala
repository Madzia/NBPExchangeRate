package actions

import org.squeryl.PrimitiveTypeMode._

object ExchangeRateSummary {
  def Loaded = {
    transaction{
      from(models.playDatabase.ExchangeRateSummaryTable)
      {
        p => where(
               p.name<>""
             )select(p) orderBy(p.name asc)
      }.distinct
    }
  }
}