dump-servlet
============

dump-servlet is a Scala implementation of a servlet that reads the inbound HTTP request, gets various pieces of information about it, and writes those pieces of information back out to the response.  The easiest way to see it in action is to use Maven to launch it in Jetty:

        mvn clean package jetty:run

Then, try sending it various HTTP requests to see what it does:

        curl -v -i http://localhost:8080/dump-servlet/
        curl -v -i http://localhost:8080/dump-servlet/ -H 'Content-Type: application/octet-stream' -d 'abcdefghijklmnopqrstuvwxyz'

### Why?

This is something that came about while trying to troubleshoot the flow of an HTTP request on its way to a destination service.  Several tiers were involved (a load balancer appliance, an HTTP proxy, another layer of software load balancing, and then finally a Tomcat server).  I wanted to get a clear look at what the destination Tomcat server actually saw compared to what the client sent.

This is also helpful for exploring the functionality of servlet API methods.  The various methods for getting path information can be confusing.  This servlet makes it easier to test and see what those methods actually return.

