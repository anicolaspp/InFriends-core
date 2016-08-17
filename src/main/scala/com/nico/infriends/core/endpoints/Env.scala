package com.nico.infriends.core.endpoints

/**
  * Created by anicolaspp on 8/17/16.
  */

trait Env {
  def   clientId: String
  def   clientSecret: String
  def   redirectURL: String
}

object Env {

  private var env: Env = null

  def apply(clientId: String, clientSecret: String, redirectURL: String) = {

    env =  EnvImp(clientId, clientSecret, redirectURL)

    env
  }

  def getEnv: Env = env

  case class EnvImp( clientId: String,  clientSecret: String, redirectURL: String) extends Env

}
