/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core


import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch._



object app extends Repository {
  def main(args: Array[String]) {



    val port = Option(System.getProperty("http.port")) getOrElse "8080"

    val api: Endpoint[String] = get("/hello") {
      Ok(hello)
    }

    Await.ready(Http.server.serve(s":$port", api.toServiceAs[Text.Plain]))
  }
}




trait Repository {

  def hello() = "Hello, Word!"

}