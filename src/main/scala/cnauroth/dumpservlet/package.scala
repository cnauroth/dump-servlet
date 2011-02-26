/**
 * This package provides a servlet that responds by writing a text
 * representation of the request that it received.  This includes:
 *
 * Properties - information that the Servlet API attaches to the request object
 *   as properties, such as the protocol, HTTP method, and URI, represented as
 *   key-value pairs.
 * 
 * Headers - all HTTP request headers, represented as unique keys mapped to a
 *   list of one or more values.
 * 
 * Locales - locale information provided by the Servlet API, represented as a
 *   list of one or more values.
 * 
 * Parameters - query string parameters, represented as unique keys mapped to a
 *   list of one or more values.
 * 
 * Cookies - cookies present in the HTTP request, represented as unique keys
 *   mapped to a list of one or more values, with each value showing a set of
 *   properties that describe the cookie.
 * 
 * Attributes - attributes programmatically attached to the request object using
 *   the Servlet API, represented as key-value pairs.
 * 
 * Session - session information provided by the Servlet API.  This includes a
 *   set of core properties, represented as key-value pairs, and a set of
 *   attributes programmatically attached to the session object using the
 *   Servlet API, also represented as key-value pairs.
 * 
 * Body - the HTTP request body.
 */

package cnauroth {
  package object dumpservlet {

    /** Alias for servlet API HttpServletRequest. */
    private[dumpservlet] type Request = javax.servlet.http.HttpServletRequest

    /** Alias for servlet API HttpServletResponse. */
    private[dumpservlet] type Response = javax.servlet.http.HttpServletResponse
  }
}

