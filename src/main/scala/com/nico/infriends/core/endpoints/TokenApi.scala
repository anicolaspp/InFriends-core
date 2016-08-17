/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.endpoints

import io.finch._


trait TokenApi {



  def pushToken: Endpoint[String] = get("push" :: param("code")) { code: String =>

    println(code)

    val res = scalaj.http.Http("https://api.instagram.com/oauth/access_token").postForm(Seq(
      "client_id"     ->  "3e4dff94fc1e42c99544d271113a3773",
      "client_secret" ->  "4140e98fb6ab4f31bca333b1ab63cf64",
      "grant_type"    ->  "authorization_code",
      "redirect_uri"  ->  "http://infriends-core.herokuapp.com/push",
      "code"          ->  code
    )).asString


    Ok(res.body)
  }
}


