package example
import scala.scalajs.js.annotation._
import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.document
import scala.util.Random
import scala.math
import scala.scalajs.js
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.raw.HTMLCanvasElement
import org.scalajs.dom.ext.KeyCode
import scala.scalajs.js.Dynamic.global
import scala.collection.mutable.HashMap
import scala.collection._


case class Point(x: Double, y: Double){
  def +(other: Point) = Point(x + other.x, y + other.y)
  def -(other: Point) = Point(x - other.x, y - other.y)
  def %(other: Point) = Point(x % other.x, y % other.y)
  def <(other: Point) = x < other.x && y < other.y
  def >(other: Point) = x > other.x && y > other.y
  def /(value: Double) = Point(x / value, y / value)
  def *(value: Double) = Point(x * value, y * value)
  def *(other: Point) = x * other.x + y * other.y
  def length = Math.sqrt(lengthSquared)
  def lengthSquared = x * x + y * y
  def within(a: Point, b: Point, extra: Point = Point(0, 0)) = {
    import math.{min, max}
    x >= min(a.x, b.x) - extra.x &&
    x < max(a.x, b.x) + extra.y &&
    y >= min(a.y, b.y) - extra.x &&
    y < max(a.y, b.y) + extra.y
  }
  def rotate(theta: Double) = {
    val (cos, sin) = (Math.cos(theta), math.sin(theta))
    Point(cos * x - sin * y, sin * x + cos * y)
  }
}




@JSExportTopLevel("ScalaJSExample")
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val renderer = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = 400
    canvas.height = 400

    
    
    

    val canvas2 = document.createElement("canvas").asInstanceOf[Canvas]
    document.body.appendChild(canvas2);
   

    canvas2.width = 400
    canvas2.height = 400
    canvas.style.position = "absolute"

    val ctx2 = canvas2.getContext("2d")

    val keysDown = HashMap[Int, Boolean]()


     dom.window.addEventListener("keydown", (e: dom.KeyboardEvent) => {
      keysDown += e.keyCode -> true
    }, false)

    dom.window.addEventListener("keyup", (e: dom.KeyboardEvent) => {
      keysDown -= e.keyCode
    }, false)


    

   




    //vals
    val BallVelocity = Point(3.3,3.3)


    val BoardLocation = Point(canvas2.height/2.0,canvas2.width/2.0)

    val BoardVelocity = Point(5,5)

    val BallLocation = Point(canvas.height/2.0,canvas.width/2.0)

    val BallSize = 10

    val BoardLength = 30
    val BoardWidth = 10

    val Walls = Point(canvas.width, canvas.height)

    val Origin = Point(0,0)

    def run() = {
   

      val BallNewLocation = BallVelocity + BallLocation

      
      

      val BoardNewLocation =  if (keysDown.contains(65))  BoardLocation - BoardVelocity
                              else if (keysDown.contains(68)) BoardLocation + BoardVelocity
                              else BoardLocation
      


      def collided(v1: Point, v2: Point) = {
        val BoardLengthRange = List( BoardNewLocation.x to (BoardNewLocation.x + BoardLength))
        val BoardWidthRange = List( BoardNewLocation.y to (BoardNewLocation.y + BoardWidth))
        val CompleteRange = BoardLengthRange ++ BoardWidthRange

        for(i <- CompleteRange){
          if(BallNewLocation < Walls || BallNewLocation < Origin || BallNewLocation.x == i && BallNewLocation.y == i)
            BallVelocity * -1

        }

        
      }

      
    
     

      //animate
    
      renderer.fillRect(BallNewLocation.x, BallNewLocation.y, BallSize, BallSize)
     
      ctx2.fillRect(BoardNewLocation.x, BoardNewLocation.y, BoardLength, BoardWidth)


    
    }

    def trueRun() = {
      ctx2.clearRect(0, 0, canvas2.width,canvas2.height)
      renderer.clearRect(0, 0, canvas.width, canvas.height)
     

      
      run()
    }

    js.timers.setInterval(60) {
      trueRun()
    }


    


  }


}
