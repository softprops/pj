package pj 

object App {
  def main(args: Array[String]) {
    val exit = run(args)
    System.exit(exit)
  }
  def run(args: Array[String]): Int = {
    println("parse it!")
    0
  }
}

class App extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) =
    new Exit(App.run(config.arguments))
}
class Exit(val code: Int) extends xsbti.Exit
