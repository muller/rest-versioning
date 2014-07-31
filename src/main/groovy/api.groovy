package api

import javax.ws.rs.*
import javax.ws.rs.core.*

@Path('/rest-api')
class RestApi {

  @GET
  @Produces('text/plain')
  String hello() {
    'Hello!!!\n'
  }

  @GET
  @Path('/json')
  @Produces('application/json')
  def helloJson() {
    [ message: 'Hello!!!' ]
  }
}
