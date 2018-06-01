package com.softwaremill.akkaSimpleCluster

import akka.actor.ActorSystem
import akka.cluster.Cluster
import akka.management.AkkaManagement
import akka.management.cluster.bootstrap.ClusterBootstrap
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory


object Main extends App {

  lazy val config = ConfigFactory.load()
  implicit val system = ActorSystem("akka-simple-cluster")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val cluster = Cluster(system)

  AkkaManagement(system).start()
  ClusterBootstrap(system).start()

  Http().bindAndHandle(complete(config.getString("application.api.hello-message")), config.getString("application.api.host"), config.getInt("application.api.port"))
}
