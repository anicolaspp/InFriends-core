/**
  * Created by anicolaspp on 8/16/16.
  */


package com.nico.infriends.core
package tests

import com.nico.infriends.core.repositories.{Repository, User}
import io.circe.Json
import io.circe.generic.auto._
import io.circe.parser._
import org.scalatest.{FlatSpec, Matchers}


class FirstTest extends FlatSpec with Matchers with Repository {

  it should "say hello" in {

    hello() should be ("Hello, Word!")

  }


  it should "map json" in {


    val input = "{\"access_token\": \"22987840.3e4dff9.531552dc85654e13a6b53ba0962b7e79\", \"user\": {\"username\": \"anicolaspp\", \"bio\": \"A computer science guy living in Florida, US\\nI never think in the future, its coming soon enough\\nIm 25\\nRock music\", \"website\": \"http://facebook.com/anicolaspp\", \"profile_picture\": \"https://scontent.cdninstagram.com/t51.2885-19/11906376_866112826776498_1820068764_a.jpg\", \"full_name\": \"Nicolas A  Perez\", \"id\": \"22987840\"}}"


    val json = parse(input).getOrElse(Json.Null)

    val map = json.asObject.map(_.toMap).getOrElse(Map.empty)


    map.contains("access_token") should be (true)
    map.contains("user") should be (true)


    val userStr = map.getOrElse("user", Json.Null)


    val user = decode[User](userStr.toString()).getOrElse(User.empty)

    user.username should be ("anicolaspp")

  }

}

