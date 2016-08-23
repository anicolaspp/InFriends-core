/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.endpoints

import com.nico.infriends.core.repositories.{AwsRepository, User}
import com.twitter.finagle.http.Status
import com.twitter.util.Future
import io.circe.Json
import io.circe.generic.auto._
import io.circe.parser._
import io.finch._

trait AuthApi {

  def authEndpoint = login :+: pushToken

  private def login: Endpoint[Unit] = get("login") {
    Output
        .unit(Status.SeeOther)
      .withHeader("Location" ->
        s"https://api.instagram.com/oauth/authorize/?client_id=${Env.getEnv.clientId}&redirect_uri=${Env.getEnv.redirectURL}&response_type=code")
  }

  private def pushToken: Endpoint[String] = get("push" :: param("code")) { code: String =>

    println(code)

    val res = scalaj.http.Http("https://api.instagram.com/oauth/access_token").postForm(Seq(
      "client_id"     ->  Env.getEnv.clientId,
      "client_secret" ->  Env.getEnv.clientSecret,
      "grant_type"    ->  "authorization_code",
      "redirect_uri"  ->  Env.getEnv.redirectURL,
      "code"          ->  code
    )).asString


    val json = parse(res.body).getOrElse(Json.Null)
    val map = json.asObject.map(_.toMap).getOrElse(Map.empty)

    if (map.contains("access_token")) {
      val userStr = map.getOrElse("user", Json.Null)
      val user = decode[User](userStr.toString()).getOrElse(User.empty)

      println(user)

      Future {
        AwsRepository.apply.saveUser(user)
      }
    }

//    val body = res.body
//
//    if (body.contains("access_token")) {
//
//      val userOption = body.get("user").foreach(u => {
//
//        println(u)
//
//
//        val user = decode[User](u).getOrElse(User.empty)
//
//        val repo = AwsRepository.apply
//
//        repo.saveUser(user)
//      })
//
//
//    }
//
//    println(body)



    Ok(res.body)
  }
}


