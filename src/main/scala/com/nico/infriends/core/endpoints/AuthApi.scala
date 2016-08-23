/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.endpoints

import com.nico.infriends.core.repositories.{User, AwsRepository}
import io.circe.Json
import io.finch._
import com.twitter.finagle.http.Status

import io.finch.circe._


trait AuthApi {

  def authEndpoint = login :+: pushToken

  private def login: Endpoint[Unit] = get("login") {
    Output
        .unit(Status.SeeOther)
      .withHeader("Location" ->
        s"https://api.instagram.com/oauth/authorize/?client_id=${Env.getEnv.clientId}&redirect_uri=${Env.getEnv.redirectURL}&response_type=code")
  }

  private def pushToken: Endpoint[String] = get("push" :: param("code")) { code: String =>

    println(code)

    val res = scalaj.http.Http("https://api.instagram.com/oauth/access_token").postForm(Seq(
      "client_id"     ->  Env.getEnv.clientId,
      "client_secret" ->  Env.getEnv.clientSecret,
      "grant_type"    ->  "authorization_code",
      "redirect_uri"  ->  Env.getEnv.redirectURL,
      "code"          ->  code
    )).asParamMap


    val body = res.body

    if (body.contains("access_token")) {

      val userOption = body.get("user").foreach(u => {

        println(u)

        val user = io.circe.jackson.decode[User](u).getOrElse(User.empty)

        val repo = AwsRepository.apply

        repo.saveUser(user)
      })


    }




    Ok("OK")
  }
}


