package cnauroth.dumpservlet

import scala.collection.immutable.ListMap

/**
 * Provides implicit type conversion of requests to a more convenient interface.
 */
private[dumpservlet] object DumpRequest {

  /**
   * Implicitly converts an HttpServletRequest to a DumpRequest.
   *
   * @param response HTTP response.
   * @return DumpRequest wrapping the request.
   */
  implicit def asDumpRequest(request: Request) = new DumpRequest(request)
}

/**
 * Wraps HTTP requests in a more convenient interface.
 *
 * @param request HTTP request.
 */
private[dumpservlet] final class DumpRequest(request: Request)
                                    extends PropertyProvider {

  protected override def properties = ListMap(
    "getAuthType" -> (() => request.getAuthType()),
    "getCharacterEncoding" -> (() => request.getCharacterEncoding()),
    "getContentLength" -> (() => request.getContentLength()),
    "getContentType" -> (() => request.getContentType()),
    "getContextPath" -> (() => request.getContextPath()),
    "getLocale" -> (() => request.getLocale()),
    "getMethod" -> (() => request.getMethod()),
    "getPathInfo" -> (() => request.getPathInfo()),
    "getPathTranslated" -> (() => request.getPathTranslated()),
    "getProtocol" -> (() => request.getProtocol()),
    "getQueryString" -> (() => request.getQueryString()),
    "getRemoteAddr" -> (() => request.getRemoteAddr()),
    "getRemoteHost" -> (() => request.getRemoteHost()),
    "getRequestedSessionId" -> (() => request.getRequestedSessionId()),
    "getRequestURI" -> (() => request.getRequestURI()),
    "getRequestURL" -> (() => request.getRequestURL()),
    "getScheme" -> (() => request.getScheme()),
    "getServerName" -> (() => request.getServerName()),
    "getServerPort" -> (() => request.getServerPort()),
    "getServletPath" -> (() => request.getServletPath()),
    "getUserPrincipal" -> (() => request.getUserPrincipal()),
    "isRequestedSessionIdFromCookie" -> (() => request.isRequestedSessionIdFromCookie()),
    "isRequestedSessionIdFromURL" -> (() => request.isRequestedSessionIdFromURL()),
    "isRequestedSessionIdValid" -> (() => request.isRequestedSessionIdValid()),
    "isSecure" -> (() => request.isSecure())
  )
}

