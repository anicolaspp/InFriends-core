/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core


import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch._



object app {
  def main(args: Array[String]) {

    val api: Endpoint[String] = get("hello") {
      Ok("Hello, Word!")
    }

    Await.ready(Http.server.serve(":9090", api.toServiceAs[Text.Plain]))
  }
}
