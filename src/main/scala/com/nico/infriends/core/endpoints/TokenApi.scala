/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.endpoints

import io.finch._
import com.twitter.finagle.http.Status


trait TokenApi {

  def loging: Endpoint[Unit] = get("redirect" :: "from") {
    Output
        .unit(Status.Continue)
      .withHeader("Location" ->
        s"https://api.instagram.com/oauth/authorize/?client_id=${Env.getEnv.clientId}&redirect_uri=${Env.getEnv.redirectURL}&response_type=code")
  }

  def pushToken: Endpoint[String] = get("push" :: param("code")) { code: String =>

    println(code)

    val res = scalaj.http.Http("https://api.instagram.com/oauth/access_token").postForm(Seq(
      "client_id"     ->  Env.getEnv.clientId,
      "client_secret" ->  Env.getEnv.clientSecret,
      "grant_type"    ->  "authorization_code",
      "redirect_uri"  ->  Env.getEnv.redirectURL,
      "code"          ->  code
    )).asString


    Ok(res.body)
  }
}


