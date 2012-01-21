package pj 

object App {
  def main(args: Array[String]) {
    val exit = run(args)
    System.exit(exit)
  }

  def run(args: Array[String]): Int = {
    if(System.in.available > 0) {
      PJ(System.in, System.out).fold({ e =>
        Console.err.println(e)
        1
      }, { u: Unit => 0 })
    } else {
      println("todo...")
      0
    }
  }
}

class App extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) =
    new Exit(App.run(config.arguments))
}
class Exit(val code: Int) extends xsbti.Exit
