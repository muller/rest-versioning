package api

import javax.ws.rs.*
import javax.ws.rs.core.*

@Path('/rest-api')
class RestApi {

  static MESSAGES = [
    'pt': 'Oi!!!',
    'en': 'Hello!!!'
  ]

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

  @GET
  @Produces('application/json')
  def helloJsonRoot(@QueryParam('language') String language) {
    [ message: MESSAGES[language ?: 'en'] ]
  }
}
