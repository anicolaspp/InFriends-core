/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.endpoints

import com.nico.infriends.core.models.Person
import com.nico.infriends.core.repositories.Repository
import io.finch._


trait HelloApi { this: Repository =>

  def helloApi: Endpoint[String] = get("hello") {
    Ok(this.hello)
  }

  def getPerson: Endpoint[Person] = get("users" :: int) { id: Int =>
    Ok(Person(id, id.hashCode().toString, 20))
  }
}

trait TokenApi {

  def pushToken: Endpoint[String] = get("push" :: param("CODE")) { code: String =>
    println(code)

    Ok(code)
  }
}
