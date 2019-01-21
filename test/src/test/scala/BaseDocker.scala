import com.whisk.docker.{DockerContainer, DockerKit, DockerReadyChecker}

import scala.io.Source
import scala.concurrent.duration._
import org.json4s.jackson.JsonMethods.parse

trait BaseDocker extends DockerKit {

  val baseImageName = "techhublisbon/base-image"

  val baseImageTag = {
    implicit val formats = org.json4s.DefaultFormats

    val jsonStr = Source.fromFile("../version.json").mkString
    (parse(jsonStr) \\ "version").extract[String]
  }

  val baseContainerName = "base"

  val magicStr = "Container started"

  // Last command makes docker alive forever
  val command =
    Seq("/bin/bash", "-c", s"echo $magicStr && tail -f /dev/null")

  lazy val baseContainer =
    DockerContainer(s"$baseImageName:$baseImageTag", Some(baseContainerName))
      .withCommand(command: _*)
      .withReadyChecker(
        DockerReadyChecker
          .LogLineContains(magicStr)
          .within(500 millis)
          .looped(30, 1 second))

  abstract override def dockerContainers: List[DockerContainer] =
    baseContainer :: super.dockerContainers
}
