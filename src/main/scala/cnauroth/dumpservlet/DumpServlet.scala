package cnauroth.dumpservlet

import scala.collection.JavaConversions._

import java.io._
import javax.servlet._
import javax.servlet.http._
import java.util._

import org.slf4j.{Logger, LoggerFactory}

import DumpCookie._
import DumpRequest._
import DumpResponse._
import DumpSession._

/**
 * Provides implementation utilities for [[cnauroth.dumpservlet.DumpServlet]].
 */
private object DumpServlet {

  /** It's the logger. */
  private val LOG = LoggerFactory.getLogger(classOf[DumpServlet])
}

/**
 * Provides the main entry point for the servlet.
 * 
 * @define requestInfo @param request HTTP request.
 * @define responseInfo @param response HTTP response.
 */
final class DumpServlet extends HttpServlet {

  import DumpServlet._

  /**
   * Handles requests.
   *
   * $requestInfo
   * $responseInfo
   */
  override def service(request: Request, response: Response) {
    LOG.info("Begin service.")
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/plain");

    try {
      this.writeProperties(request, response)
      this.writeHeaders(request, response)
      this.writeLocales(request, response)
      this.writeParameters(request, response)
      this.writeCookies(request, response)
      this.writeAttributes(request, response)
      this.writeSession(request, response)
      this.writeBody(request, response)
    }
    catch {
      case t: Throwable => LOG.error("Unexpected Throwable.", t)
    }
    finally {
      LOG.info("End service.")
    }
  }

  /**
   * Writes request attributes to the response.
   *
   * $requestInfo
   * $responseInfo
   */
  private def writeAttributes(request: Request, response: Response) {
    response.writeKeyValuePairs("Attributes",
                                () => request.getAttributeNames(),
                                (key: Any) => request.getAttribute(key.toString()))
  }

  /**
   * Writes request body to the response.
   *
   * $requestInfo
   * $responseInfo
   */
  private def writeBody(request: Request, response: Response) {
    response.writeHex("Body (first 1K)", request.getInputStream())
  }

  /**
   * Writes request cookies to the response.
   *
   * $requestInfo
   * $responseInfo
   */
  private def writeCookies(request: Request, response: Response) {
    response.writeSection("Cookies", wrapper => {
      Option(request.getCookies()) foreach(cookies => {
        response.writeNumberedList(cookies map { asDumpCookie } iterator)
      })
    })
  }

  /**
   * Writes request headers to the response.
   *
   * $requestInfo
   * $responseInfo
   */
  private def writeHeaders(request: Request, response: Response) {
    response.writeKeyValuePairs("Headers",
                                () => request.getHeaderNames(),
                                (key: Any) => request.getHeaders(key.toString()))
  }

  /**
   * Writes request locales to the response.
   *
   * $requestInfo
   * $responseInfo
   */
  private def writeLocales(request: Request, response: Response) {
    response.writeFlatList("Locales", asIterator(request.getLocales()))
  }

  /**
   * Writes request query parameters to the response.
   *
   * $requestInfo
   * $responseInfo
   */
  private def writeParameters(request: Request, response: Response) {
    response.writeKeyValuePairs("Parameters",
                                () => request.getParameterNames(),
                                (key: Any) => request.getParameterValues(key.toString()))
  }

  /**
   * Writes request properties to the response.
   *
   * $requestInfo
   * $responseInfo
   */
  private def writeProperties(request: Request, response: Response) {
    response.writeKeyValuePairs("Properties",
                                () => request.getPropertyNames(),
                                (key: Any) => request.getProperty(key.toString()))
  }

  /**
   * Writes request session to the response.
   *
   * $requestInfo
   * $responseInfo
   */
  private def writeSession(request: Request, response: Response) {
    response.writeSection("Session", wrapper => {
      Option(request.getSession(false)) foreach(session => {
        wrapper
          .writeKeyValuePairs("Attributes",
                              () => session.getAttributeNames(),
                              (key: Any) => session.getAttribute(key.toString()))
          .writeKeyValuePairs("Properties",
                              () => session.getPropertyNames(),
                              (key: Any) => session.getProperty(key.toString()))
      })
    })
  }
}

