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