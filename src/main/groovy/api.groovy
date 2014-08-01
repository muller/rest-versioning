package api

import javax.ws.rs.*
import javax.ws.rs.core.*

@Path('/rest-api')
class RestApi {

  static MESSAGES = [
    'pt': 'Oi!!!',
    'en': 'Hello!!!'
  ]

  def getMessage = { language -> MESSAGES[language ?: 'en'] }

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
    [ message: getMessage(language) ]
  }

  @GET
  @Produces('application/x.testapp.20140801+json')
  Response helloJsonNewFormat(@QueryParam('language') String language) {
    Response.ok([ msg: getMessage(language) ], 'application/json').build()
  }
}
