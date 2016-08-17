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

trait TokenApi { this: Env =>

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


trait Env {

  val env: EnvImp = EnvImp.apply()

  class EnvImp(val clientId: String, val clientSecret: String, val redirectURL: String)

  object EnvImp {
    def apply(): EnvImp = {

      val clientId =  sys.env.getOrElse("client_id", "")
      val clientSecret = sys.env.getOrElse("client_secret", "")
      val url = sys.env.getOrElse("redirect_uri", "")

      new EnvImp(clientId , clientSecret, url)
    }
  }

}