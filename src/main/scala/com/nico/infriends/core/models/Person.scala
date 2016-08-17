/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.models

import io.circe.{Json, Encoder}

case class Person(id: Int, name: String, age: Int)

object Person {
  implicit val personToJson = new Encoder[Person] {
    override def apply(a: Person): Json = Json.obj(
      "id"    ->  Json.fromInt(a.id),
      "name"  ->  Json.fromString(a.name),
      "age"   ->  Json.fromInt(a.age)
    )
  }
}



