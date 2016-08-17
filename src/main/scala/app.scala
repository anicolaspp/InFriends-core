/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core


import com.nico.infriends.core.endpoints.{Env, TokenApi, HelloApi, MathApi}
import com.nico.infriends.core.models.Person
import com.nico.infriends.core.repositories._
import com.twitter.finagle.Http
import com.twitter.util.Await
import io.circe.{Json, Encoder}
import io.finch._
import io.finch.circe._

import com.nico.infriends.core.models.Person._


object app extends HelloApi
  with MathApi
  with Repository
  with TokenApi {

  def main(args: Array[String]) {
    val port = Option(System.getProperty("http.port")) getOrElse "9080"
    Env(System.getenv("CLIENT_ID"), System.getenv("CLIENT_SECRET"), System.getenv("URL"))

    val api = helloApi :+: sum :+: getPerson :+: pushToken

    Await.ready(Http.server.serve(s":$port", api.toService))
  }
}
