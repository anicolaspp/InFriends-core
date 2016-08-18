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

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.Service

import com.twitter.finagle.http.filter.Cors

import com.nico.infriends.core.models.Person._


object app extends HelloApi
  with MathApi
  with Repository
  with TokenApi {

  def main(args: Array[String]) {
    val port = Option(System.getProperty("http.port")) getOrElse "9080"
    val e = Env(System.getenv("CLIENT_ID"), System.getenv("CLIENT_SECRET"), System.getenv("URL"))

    println("ENV")
    println(e)

    val api = helloApi :+: sum :+: getPerson :+: pushToken :+: loging

    val policy: Cors.Policy = Cors.Policy(
      allowsOrigin = _ => Some("*"),
      allowsMethods = _ => Some(Seq("GET", "POST")),
      allowsHeaders = _ => Some(Seq("Accept"))
    )

    val corsService = new Cors.HttpFilter(policy).andThen(api.toService)

    Await.ready(Http.server.serve(s":$port", corsService))
  }
}
