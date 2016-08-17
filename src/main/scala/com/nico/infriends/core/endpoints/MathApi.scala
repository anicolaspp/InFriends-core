/**
  * Created by nperez on 8/16/16.
  */


package com.nico.infriends.core.endpoints

import io.finch._


trait MathApi {

  def sum: Endpoint[Int] = get("sum" :: int :: int) { (x: Int, y: Int) =>
    Ok(x + y)
  }

}