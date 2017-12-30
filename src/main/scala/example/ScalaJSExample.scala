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

abstract class Component{
  val width: Int
  val height: Int
  val color: String
  var x: Int
  var y: Int
  def createContext(width: Int,  height: Int) {
      val canvas =  document.createElement("canvas").asInstanceOf[Canvas]
      canvas.width = width
      canvas.height = height
      val retval = canvas
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

     import scala.collection.mutable.HashMap
      val keysDown = HashMap[Int, Boolean]()

     dom.window.addEventListener("keydown", (e: dom.KeyboardEvent) => {
      keysDown += e.keyCode -> true
    }, false)

    dom.window.addEventListener("keyup", (e: dom.KeyboardEvent) => {
      keysDown -= e.keyCode
    }, false)

    

   




    //vals
    var xspeed = 3.3
    var yspeed = 3.3


    var player2x = canvas2.height / 2.0
    var player2y = canvas2.width / 2.0

    var playerY = canvas.height / 2.0
    var playerX = canvas.width / 2.0

    def run() = {
      playerX += xspeed

      playerY += yspeed



      
      if (keysDown.contains(KeyCode.Left))  player2x -= 5
      if(keysDown.contains(KeyCode.Right))   player2x += 5

     


    
      

      for(i <- 0 to 30)
      {
        
        if(playerX > canvas.width || playerX < 0 || playerX == player2x + i) {
          xspeed *= -1
         
        }

        
      }
      for(j <- 0 to 10){

         if(playerY > canvas.height || playerY < 0 || playerY == player2y + j && playerX == player2x + j) yspeed *= -1
    
      }
     
      //animate
    
      renderer.fillRect(playerX, playerY, 10, 10)
     
      ctx2.fillRect(player2x, player2y, 30, 10)


    
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
