package controllers

import play.api.data._
import play.api.data.Forms._
import play.api._
import play.api.mvc._
import play.api.templates.HtmlFormat._
import org.squeryl.PrimitiveTypeMode._
import actions.ExchangeRateSummary._
import play.api.libs.json._

object Application extends Controller {

  def index = Action {
    val values = transaction { actions.ExchangeRateSummary.Loaded.toList }
    Ok(views.html.index(values))
  }
}