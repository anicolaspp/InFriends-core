package com.nico.infriends.core.endpoints

/**
  * Created by anicolaspp on 8/17/16.
  */

trait Env {
  def   clientId: String
  def   clientSecret: String
  def   redirectURL: String
  def   awsKey: String
  def   awsSecret: String
}

object Env {

  private var env: Env = null

  def apply(clientId: String, clientSecret: String, redirectURL: String, awsKey: String,
  awsSecret: String) = {

    env =  EnvImp(clientId, clientSecret, redirectURL, awsKey, awsSecret)

    env
  }

  def getEnv: Env = env

  case class EnvImp(clientId: String,
                    clientSecret: String,
                    redirectURL: String,
                    awsKey: String,
                    awsSecret: String) extends Env

}
