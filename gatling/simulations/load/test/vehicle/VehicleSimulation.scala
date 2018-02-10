package test.vehicle

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class
VehicleSimulation extends Simulation {
  var feeder = csv("vehicle.csv").random

  val httpConf = http
    .baseURL("http://localhost:8764")
    .acceptHeader("text/html,application/xhtml+xml,application/json;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("VehicleSimulation")
    .feed(feeder)
    .exec(http("request_2")
      .get("/vehicles?shopId=${shopId}")
      .check(status.is(200))
      .check(regex("data").exists))

  setUp(
    scn.inject(
      atOnceUsers(500),
      rampUsers(20000) over (1 minutes),
      constantUsersPerSec(350) during (1 minutes)
    )
  ).protocols(httpConf)

}
