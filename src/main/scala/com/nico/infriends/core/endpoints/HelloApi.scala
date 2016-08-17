/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.endpoints

import com.nico.infriends.core.models.Person
import com.nico.infriends.core.repositories.Repository
import com.twitter.finagle.http.Http
import io.finch._


trait HelloApi { this: Repository =>

  def helloApi: Endpoint[String] = get("hello") {
    Ok(this.hello)
  }

  def getPerson: Endpoint[Person] = get("users" :: int) { id: Int =>
    Ok(Person(id, id.hashCode().toString, 20))
  }
}

trait TokenApi {

  def pushToken: Endpoint[Option[String]] = get("push" :: paramOption("code")) { code: Option[String] =>

    println(code)

    scalaj.http.Http("https://api.instagram.com/oauth/access_token").postForm(Seq(
      "client_id"     ->  "3e4dff94fc1e42c99544d271113a3773",
      "client_secret" ->  "4140e98fb6ab4f31bca333b1ab63cf64",
      "grant_type"    ->  "authorization_code",
      "redirect_uri"  ->  "http://infriends-core.herokuapp.com/push",
      "code"          ->  code.get
    )).asString

    Ok(code)
  }
}
