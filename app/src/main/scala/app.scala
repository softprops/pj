package pj 

object App {
  import java.io.{ File, InputStream, OutputStream,
                  FileReader, FileWriter, StringReader, StringWriter }
  val Help = """
  |    pj - a pretty printer for json
  | 
  |DESCRIPTION
  |    A utility for transforming arbitrary json into formatted json
  |
  |OPTIONS
  |    --
  |        Flag used to indicate piped input
  |
  |    -h, --help
  |        Displays this help message
  |
  |    -f, --file
  |        Path to input file containing json
  |
  |    -j, --json
  |        A raw json string
  |
  |    -o, --out
  |        Path to target output file
  | 
  | pj 0.1.0""".stripMargin

  case class Arguments(
    in: Option[File] = None,
    out: Option[File] = None,
    json: Option[String] = None,
    help: Boolean = false
  )

  def main(args: Array[String]) {
    val exit = run(args)
    System.exit(exit)
  }
  
  private def onError(e: String) = {
    Console.err.println(e)
    1
  }

  private def onSuccess = {
    0
  }

  private def help = {
    println(Help)
    0
  }

  private def passOrFail: PartialFunction[Option[String], Int] = {
    case Some(e) => onError(e)
    case _ => onSuccess
  }

  private def piped(in: InputStream, out: OutputStream): Int = {
    @annotation.tailrec
    def check: Int = System.in.available match {
      case n if(n > 0) =>
        passOrFail(Printer(System.in, System.out)) 
      case _ => check
    }
    check
  }

  private def argumented(args: Seq[String]): Int = {
    (((Right(Arguments()): Either[Int, Arguments])) /: args.grouped(2))((a, e) => {
      e match {
        case Seq("-f" | "--f", f) =>
          new File(f) match {
            case ne if(!ne.exists) => Left(1)
            case de => a.right.map(_.copy(in = Some(de)))
          }
        case Seq("-j" | "--json", j) =>
          a.right.map(_.copy(json = Some(j)))
        case Seq("-o" | "--out", o) =>
          a.right.map(_.copy(out = Some(new File(o))))
        case Seq("-h" | "--help") =>
          a.right.map(_.copy(help = true))
        case _ =>
          a.right.map(_.copy(help = true))
      }
    }).fold({ s =>
      s
    }, {
      _ match {
        case Arguments(Some(in), o, _, _) =>
          o match {
            case Some(out) =>
              passOrFail(Printer(new FileReader(in), new FileWriter(out)))
            case _ =>
              passOrFail(Printer(new FileReader(in), new StringWriter))
          }
        case Arguments(_, Some(out), Some(json), _) =>
          passOrFail(Printer(new StringReader(json), new FileWriter(out)))
        case Arguments(_, _, Some(json), _) =>
          Printer(json).fold(onError, { js =>
            println(js)
            0
          })
        case Arguments(_, _, _, true) =>
          help
        case _ =>
          help
      }
    })
  }

  def run(args: Array[String]): Int = {
    if(args.contains("--")) piped(System.in, System.out)
    else argumented(args)
  }
}

class App extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) =
    new Exit(App.run(config.arguments))
}
class Exit(val code: Int) extends xsbti.Exit
