package pj

object Printer {
  import java.io.{ InputStream => IN, OutputStream => OUT,
                  Reader, Writer, StringWriter }
  import org.codehaus.jackson.{
    JsonFactory, JsonGenerator, JsonParser,
    JsonParseException }
  import org.codehaus.jackson.util.DefaultPrettyPrinter

  /** Prints formated json from a String */
  def apply(in: String): Either[String, String]  =
    factory { f =>
      val writer = new StringWriter
      pretty(f.createJsonParser(in), f.createJsonGenerator(writer)) {
        writer.toString
      }
    }

  /** Reads json from reader, printing formated json to writer */
  def apply(in: Reader, out: Writer): Option[String] =
    factory { f =>
      pretty(f.createJsonParser(in), f.createJsonGenerator(out)) {
        ()
      }.fold({ Some(_)}, { u => None })
    }

  /** Reads json from input stream, writing formatted json to output stream */
  def apply(in: IN, out: OUT): Option[String] =
    factory { f =>
      pretty(f.createJsonParser(in), f.createJsonGenerator(out)) {
        ()
      }.fold({ Some(_) }, { u => None })
    }

  private def pretty[T](
    parser: JsonParser, gen: JsonGenerator)(
    f: => T): Either[String, T] =
    try {
      parser.nextToken // one step forward
      gen.setPrettyPrinter(new DefaultPrettyPrinter)
      gen.copyCurrentStructure(parser)
      gen.flush
      Right(f)
    } catch {
      case jpe: JsonParseException =>
        Left(jpe.getMessage)
    } finally {
      gen.close
      parser.close
    }
  
  private def factory[T](f: JsonFactory => T): T =
    f(new JsonFactory)

}
