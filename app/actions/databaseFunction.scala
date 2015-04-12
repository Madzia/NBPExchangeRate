package actions

import org.squeryl.{Session, SessionFactory}
import org.squeryl.PrimitiveTypeMode._
import play.api.db.DB
import org.squeryl.adapters.PostgreSqlAdapter
import play.api.Play.current
import play.Logger

object databaseFunction {
 
    def connectToDatabase() = {
      try{
        Class.forName("org.postgresql.Driver")
        SessionFactory.concreteFactory = Some(() => 
          Session.create(DB.getConnection(), new PostgreSqlAdapter))
        Logger.info("Connect to database")  
      }catch {case e:Exception => Logger.info(e.toString())}
    }
    
    def insertDataToDatabase(model: Any) {
      model match{
        case x: models.ExchangeRateSummary => {
          try{
            inTransaction{
              models.playDatabase.ExchangeRateSummaryTable.insert(x)
            }
          }catch {case e:Exception => Logger.info(e.toString())}
        }
        case x: models.ExchangeRateAll => {
          try{
            inTransaction{
              models.playDatabase.ExchangeRateAllTable.insert(x)
            }
          }catch {case e:Exception => Logger.info(e.toString())}
        }
        case _ => Logger.info("I got problem with model: "+ model.toString() + " in function insertDataToDatabase")
      }
    }
    
    def updateMinValueDataToDatabase(model: Any, minvalue: Double) {
     model match{
      case x: models.ExchangeRateSummary =>  update(models.playDatabase.ExchangeRateSummaryTable){p => where(p.id === x.id) set(p.minvalue := minvalue, p.avgvalue := ((minvalue+ p.maxvalue.~) /2.0))}
      case _ => Logger.info("Error in function(FillZeros) with type: I got type: " + model.toString)  
      }
    }  
    
    def updateMaxValueDataToDatabase(model: Any, maxvalue: Double) {
     model match{
      case x: models.ExchangeRateSummary =>  update(models.playDatabase.ExchangeRateSummaryTable){p => where(p.id === x.id) set(p.maxvalue := maxvalue, p.avgvalue := ((maxvalue+ p.minvalue.~) /2.0))}
      case _ => Logger.info("Error in function(FillZeros) with type: I got type: " + model.toString)  
      }
    }
    
 
}