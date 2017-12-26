package example
import scala.scalajs.js.annotation._
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.Random
import scala.math
import scala.scalajs.js


case class Point(x: Int, y: Int){
  def +(p: Point) = Point(x + p.x, y + p.y)
  def /(d: Int) = Point(x / d, y / d)
}

@JSExportTopLevel("ScalaJSExample")
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val renderer = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = 50 
    canvas.height = 70

    



    //vals
    var xspeed = 3.3
    var yspeed = 3.3




    var playerY = canvas.height / 2.0
    var playerX = canvas.width / 2.0

    def run() = {
      playerX += xspeed

      playerY += yspeed


      


      if(playerX > canvas.width || playerX < 0) xspeed *= -1
      if(playerY > canvas.height || playerY < 0) yspeed *= -1


      
     

      //animate
      renderer.fillRect(playerX, playerY, 10, 10)
      
      
    }

    def trueRun() = {
      
      renderer.clearRect(0, 0, canvas.width, canvas.height)
      run()
    }

    js.timers.setInterval(60) {
      trueRun()
    }
    




  }





}
