package com.nico.infriends.core.endpoints

/**
  * Created by anicolaspp on 8/17/16.
  */
class Env(val clientId: String, val clientSecret: String, val redirectURL: String)

object Env {

  private var env = Env("", "", "")

  def apply(clientId: String, clientSecret: String, redirectURL: String) = {

    env = new Env(clientId, clientSecret, redirectURL)

    env
  }

  def getEnv: Env = env

}
