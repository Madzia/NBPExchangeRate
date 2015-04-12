package actions

import sys.process._
import scala.io.Source
import scala.collection.mutable.ListBuffer
import org.squeryl.PrimitiveTypeMode._
import play.Logger
import java.lang.Boolean
import actions.databaseFunction._
import models.playDatabase._
import java.net.URL
import java.net.URI
import scala.xml.XML
import scala.io.Codec
import org.apache.commons.lang3.CharSet
import java.io.{File=>_, _}

object dataLoader {
   def SelectRowFromExchangeRateSummary(temp: models.ExchangeRateSummary) = from(models.playDatabase.ExchangeRateSummaryTable){p => where(p.name === temp.name and p.coderate === temp.coderate)select(p)}
   val fileUrl = """http://www.nbp.pl/kursy/xml/"""
  
  def loadIntoDatabase() {
    
    val holmesUrl = """http://www.nbp.pl/kursy/xml/dir.txt"""
      try{
        transaction{
           for (line <- Source.fromURL(holmesUrl).getLines) {
            //   line match { case "[a-b][0-9a-z]{4}[0(7-9)|1(0-4)][0-9]{2}31" => add(fileUrl.replaceAll("code", line)) case _ => 0 }
              line match { case "a069z020409" => 
                {
                  val string = fileUrl+line +".xml"
                  add(string)
                }
                case _ => 0 }
           }
        }
     }catch {
        case ex: Exception => Logger.info(ex.toString())
     }
  }
           
           
     def add (file: String) = {
        try{
          transaction{
            val xml = XML.load(file)
            val date = (xml \\ "data_publikacji").text
            for(entry <- xml \\ "pozycja") {
                val country = (entry \\ "nazwa_kraju").text
                val counter = (((entry \\ "przelicznik").text).replace(',', '.')).toDouble
                val codeRate = (entry \\ "kod_waluty").text
                val avgRate = (((entry \\ "kurs_sredni").text).replace(',', '.')).toDouble
                val avg = (avgRate / counter).toDouble
                
                val listOfSummary = models.ExchangeRateSummary(1, country, codeRate, avg, avg, avg)
                val listOfAll = models.ExchangeRateAll(1, country, codeRate, date, avg, listOfSummary)
                actions.databaseFunction.insertDataToDatabase(listOfAll)
                val temp = SelectRowFromExchangeRateSummary(listOfSummary)
                if (temp.toList.length == 0) {
                  val x = actions.databaseFunction.insertDataToDatabase(listOfSummary)
                }
                actions.databaseFunction.updateMinValueDataToDatabase(listOfSummary, 0.42)
           
//                else {
//                  temp
//                }
            }
      }
     } catch {
    case ex: Exception => Logger.info(ex.toString())
     }
     }
     
}