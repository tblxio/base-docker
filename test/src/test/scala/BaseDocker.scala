import com.whisk.docker.{DockerContainer, DockerKit, DockerReadyChecker}

import scala.concurrent.duration._

trait BaseDocker extends DockerKit {

  val baseImageName = "tdh/base-docker"

  //ToDo: Soft-code this value
  val baseImageTag = "0.0.1"

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
