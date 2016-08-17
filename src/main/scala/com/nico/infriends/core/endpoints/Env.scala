package com.nico.infriends.core.endpoints

/**
  * Created by anicolaspp on 8/17/16.
  */
trait Env {

  val env: EnvImp = EnvImp.apply()

  class EnvImp(val clientId: String, val clientSecret: String, val redirectURL: String)

  object EnvImp {
    def apply(): EnvImp = {

      val clientId =  System.getenv("CLIENT_ID")
      val clientSecret = System.getenv("CLIENT_SECRET")
      val url = System.getenv("URL")

      new EnvImp(clientId , clientSecret, url)
    }
  }

}
