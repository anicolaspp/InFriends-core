/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.repositories

import awscala._, s3._
import com.amazonaws.services.s3.model.ObjectMetadata
import com.nico.infriends.core.endpoints.Env

import scala.util.Try

trait Repository {

  def hello() = "Hello, Word!"



}


case class User(id: String, username: String, full_name: String, profile_picture: String)

object User {

  def empty = User("", "", "", "")

}



trait AwsRepository {
  type AWS_KEY = String

  def saveUser(user: User): Option[String]

  def getUserWithKey(key: AWS_KEY): User
}

object AwsRepository {

  def apply: AwsRepository = new Repository


  class Repository extends AwsRepository {
    //    override def getUserWithKey(key: AWS_KEY): User = User.empty
    //
    //    override def saveUser(user: User): Option[String] = {
    //
    //      val s3 = S3(Env.getEnv.awsKey, Env.getEnv.awsSecret)(Region.default())
    //
    //      s3.put(Bucket("infriends"), user.id, user.username.getBytes, new ObjectMetadata())
    //
    //      "100"
    //    }
    override def saveUser(user: User): Option[String] = {

      Try {
        val s3 = S3(Env.getEnv.awsKey, Env.getEnv.awsSecret)(Region.default())

        s3.put(Bucket("infriends"), user.id, user.username.getBytes, new ObjectMetadata())

        "100"
      }.toOption
    }

    override def getUserWithKey(key: AWS_KEY): User = User.empty
  }

}

