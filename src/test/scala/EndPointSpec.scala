/**
  * Created by anicolaspp on 8/16/16.
  */


package com.nico.infriends.core
package tests

import org.scalatest.{Matchers, FlatSpec}


class FirstTest extends FlatSpec with Matchers with Repository {

  it should "say hello" in {

    hello() should be ("Hello, Word!")

  }

}

