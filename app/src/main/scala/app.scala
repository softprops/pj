package pj 

object App {
  import java.io.{ InputStream, OutputStream }

  def main(args: Array[String]) {
    val exit = run(args)
    System.exit(exit)
  }

  private def piped(in: InputStream, out: OutputStream): Int = {
    @annotation.tailref
    def check: Int = System.in.available match {
      case n if(n > 0) =>
        PJ(System.in, System.out).fold({ e =>
          Console.err.println(e)
          1
        }, { u: Unit => 0 })
      case _ => check
    }
    check
  }

  private def argumented(args: Seq[String]) = {
    println("todo...")
    0
  }

  def run(args: Array[String]): Int = {
    if(args.contains("-p")) piped(System.in, System.out)
    else argumented(args)
  }
}

class App extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) =
    new Exit(App.run(config.arguments))
}
class Exit(val code: Int) extends xsbti.Exit
