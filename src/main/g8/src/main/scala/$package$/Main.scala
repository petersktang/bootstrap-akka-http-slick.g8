package $package$

import com.google.inject.Guice
import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import de.innfactory.akka.AuthService
import de.innfactory.akka.jwt.AutoValidator
import $package$.http.HttpService
import $package$.services.DummyService
import $package$.utils.{Configuration, FlywayService, Persistence, GuiceInjector, PrivateGuiceInjector}

import scala.concurrent.ExecutionContext

object Main extends App with Configuration {
  val injector = Guice.createInjector(new GuiceInjector(), new PrivateGuiceInjector)
  // \$COVERAGE-OFF\$Main Application Wrapper
  implicit val actorSystem = ActorSystem()
  implicit val executor: ExecutionContext = actorSystem.dispatcher
  implicit val log: LoggingAdapter = Logging(actorSystem, getClass)
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val flywayService = new FlywayService(jdbcUrl, dbUser, dbPassword)
  flywayService.migrateDatabaseSchema

  implicit val persistence = new Persistence
  val jwtValidator = new AutoValidator
  val authService = new AuthService(jwtValidator)
  val dummyService = new DummyService()

  val httpService = new HttpService(authService, dummyService)

  val httpFlow = akka.http.scaladsl.server.Route.handlerFlow(httpService.routes)
  Http().bindAndHandle(httpFlow, httpHost, httpPort)
  // \$COVERAGE-ON\$
}
