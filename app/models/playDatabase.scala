package models
import org.squeryl.KeyedEntity
import org.squeryl.Schema
import org.squeryl.PrimitiveTypeMode._
import org.squeryl._
import dsl.CompositeKey2

case class ExchangeRateAll (
    id: Long,
    name: String,
    coderate: String,
    date: String,
    value: Double,
    listall: ExchangeRateSummary
  )extends KeyedEntity[Long]

case class ExchangeRateSummary (
    id: Long,
    name: String,
    coderate: String,
    minvalue: Double,
    maxvalue: Double,
    avgvalue: Double
  )extends KeyedEntity[Long]
  
object playDatabase extends Schema {
  val ExchangeRateAllTable = table[ExchangeRateAll]("exchangerateall")
  val ExchangeRateSummaryTable = table[ExchangeRateSummary]("exchangeratesummary")
}