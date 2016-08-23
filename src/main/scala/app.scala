/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core


import com.nico.infriends.core.endpoints.{Env, AuthApi, HelloApi, MathApi}
import com.nico.infriends.core.models.Person
import com.nico.infriends.core.repositories._
import com.twitter.finagle.Http
import com.twitter.util.Await
import io.circe.{Json, Encoder}
import io.finch._
import io.finch.circe._

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.Service

import com.twitter.finagle.http.filter.Cors

import com.nico.infriends.core.models.Person._


object app extends HelloApi
  with MathApi
  with Repository
  with AuthApi {

  def main(args: Array[String]) {
    val port = Option(System.getProperty("http.port")) getOrElse "9080"
    val e = Env(System.getenv("CLIENT_ID"),
      System.getenv("CLIENT_SECRET"),
      System.getenv("URL"),
//      System.getenv("AWS_Key"),
//      System.getenv("AWS_SECRET")

      "AKIAJ3UPNH6OTVBIDV5Q",
      "g+X1QMolF9NFu+crjfyX6vslmTsZAMCflhXDSgDA"
    )

    println("ENV")
    println(e)

    val api = authEndpoint

    Await.ready(Http.server.serve(s":$port", api.toService))
  }
}


