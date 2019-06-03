import org.scalatest.concurrent.ScalaFutures

import scala.sys.process.Process

class DockerSpec extends BaseSpec with BaseDocker with ScalaFutures {

  "All containers" should "be ready at the same time" in {
    dockerContainers.map(_.image).foreach(out => println(s"Started: $out"))
    dockerContainers.forall(_.isReady().futureValue) shouldBe true
  }

  "Base docker image" should "have SBT installed" in {
    val output = Process(s"docker exec $baseContainerName sbt sbtVersion").!!
    val lastLine = output.split("\n").last
    lastLine should include regex ("(.*)[info](.*)\\d{1,}\\.\\d{1,}\\.\\d{1,}(.*)") // x.y.z
  }

  "Base docker image" should "have Maven installed" in {
    val output = Process(s"docker exec $baseContainerName mvn --version").!!
    val firstLine = output.split("\n").head
    firstLine should include regex ("(.*)\\d{1,}\\.\\d{1,}\\.\\d{1,}") // x.y.z
  }

  "Base docker image" should "have JSON parser installed" in {
    val output = Process(s"docker exec $baseContainerName jq --version").!!
    val lastLine = output.split("\n").last
    lastLine should include regex ("jq-\\d{1,}\\.\\d{1,}(.*)") // x.y
  }

  "Base docker image" should "have docker installed" in {
    val output = Process(s"docker exec $baseContainerName docker --version").!!
    val lastLine = output.split("\n").last
    lastLine should include regex ("Docker version \\d{1,}\\.\\d{1,}.\\d{1,}(.*)") // x.y.z
  }
}
