/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core


import com.nico.infriends.core.endpoints.{AuthApi, Env, HelloApi, MathApi}
import com.nico.infriends.core.repositories._
import com.twitter.finagle.Http
import com.twitter.util.Await
import io.finch.circe._


object app extends HelloApi
  with MathApi
  with Repository
  with AuthApi {

  def main(args: Array[String]) {
    val port = Option(System.getProperty("http.port")) getOrElse "9080"
    val e = Env(System.getenv("CLIENT_ID"),
      System.getenv("CLIENT_SECRET"),
      System.getenv("URL"),
      System.getenv("AWS_Key"),
      System.getenv("AWS_SECRET")
    )

    println("ENV")
    println(e)

    val api = authEndpoint

    Await.ready(Http.server.serve(s":$port", api.toService))
  }
}


