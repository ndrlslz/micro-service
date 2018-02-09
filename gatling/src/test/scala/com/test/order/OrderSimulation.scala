package com.test.order

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class OrderSimulation extends Simulation {
  val httpConf = http // 4
    .baseURL("http://localhost:8763") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/json;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("OrderSimulation") // 7
    .exec(http("request_1")  // 8
    .get("/shops/1/orders")) // 9
    .pause(5) // 10

  setUp( // 11
    scn.inject(atOnceUsers(1)) // 12
  ).protocols(httpConf) // 13
}
