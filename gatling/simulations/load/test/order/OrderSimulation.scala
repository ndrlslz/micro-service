package test.order

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class OrderSimulation extends Simulation {
  var feeder = csv("order.csv").random

  val httpConf = http // 4
    .baseURL("http://localhost:8763") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/json;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("OrderSimulation")
    .feed(feeder)
    .exec(http("request_1") // 8
      .get("/shops/${shopId}/orders")
      .check(status.is(200))
      .check(regex("data").exists)) // 9

  setUp(// 11
    scn.inject(
      atOnceUsers(500),
      rampUsersPerSec(3000) to 5000 during (30 seconds),
      constantUsersPerSec(350) during (30 seconds)
    ) // 12
  ).protocols(httpConf) // 13
}
