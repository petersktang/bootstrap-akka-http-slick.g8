package de.innfactory.bootstrap.utils

import net.codingwell.scalaguice.{ScalaModule,ScalaPrivateModule}

import com.google.inject.{AbstractModule,PrivateModule}

class GuiceInjector extends AbstractModule with ScalaModule {
  def configure(): Unit = {

  }
}

class PrivateGuiceInjector extends PrivateModule with ScalaPrivateModule {
  def configure(): Unit = {

  }
}