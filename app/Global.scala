import play.api._

object Global extends GlobalSettings{
  
    override def onStart(app: Application){
    actions.databaseFunction.connectToDatabase()
    actions.dataLoader.loadIntoDatabase()
    Logger.info("Database Loaded")
  }
}
