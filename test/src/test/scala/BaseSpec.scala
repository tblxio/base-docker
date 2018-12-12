import com.spotify.docker.client.{DefaultDockerClient, DockerClient}
import com.whisk.docker.impl.spotify.SpotifyDockerFactory
import com.whisk.docker.scalatest.DockerTestKit
import com.whisk.docker.{DockerFactory, DockerKit}
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

trait BaseSpec
    extends FlatSpec
    with Matchers
    with BeforeAndAfterAll
    with DockerTestKit { self: DockerKit =>

  private val client: DockerClient = DefaultDockerClient.fromEnv().build()

  override implicit val dockerFactory: DockerFactory =
    new SpotifyDockerFactory(client)

  override def beforeAll(): Unit = {
    super.beforeAll()
    dockerImageLoaded()
  }

  private def dockerImageLoaded(): Boolean = {
    dockerContainers.forall { x =>
      Await.result(x.isReady(), 10 seconds)
    }
  }
}
