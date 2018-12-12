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
    lastLine should include("1.2.7")
  }
}
