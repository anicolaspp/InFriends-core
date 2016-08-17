/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.endpoints

import io.finch._


trait TokenApi { this: Env =>

  println("ENV: " + this.env.redirectURL)

  def pushToken: Endpoint[String] = get("push" :: param("code")) { code: String =>

    println(code)

    val res = scalaj.http.Http("https://api.instagram.com/oauth/access_token").postForm(Seq(
      "client_id"     ->  this.env.clientId,
      "client_secret" ->  this.env.clientSecret,
      "grant_type"    ->  "authorization_code",
      "redirect_uri"  ->  this.env.redirectURL,
      "code"          ->  code
    )).asString


    Ok(res.body)
  }
}


