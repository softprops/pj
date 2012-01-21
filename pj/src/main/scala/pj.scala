package pj

object PJ {
  import java.io.{ InputStream => IN, OutputStream => OUT,
                  Reader, Writer, StringWriter }
  import org.codehaus.jackson.{
    JsonFactory, JsonGenerator, JsonParser,
    JsonParseException }
  import org.codehaus.jackson.util.DefaultPrettyPrinter

  def apply(in: String): Either[String, String]  =
    factory { f =>
      val writer = new StringWriter
      pretty(f.createJsonParser(in), f.createJsonGenerator(writer)) {
        writer.toString
      }
    }

  def apply(in: Reader, out: Writer): Either[String, Unit] =
    factory { f =>
      pretty(f.createJsonParser(in), f.createJsonGenerator(out)) {
        ()
      }
    }

  def apply(in: IN, out: OUT): Either[String, Unit] =
    factory { f =>
      pretty(f.createJsonParser(in), f.createJsonGenerator(out)) {
        ()
      }
    }

  private def pretty[T](
    parser: JsonParser, gen: JsonGenerator)(
    f: => T): Either[String, T] =
    try {
      parser.nextToken
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
