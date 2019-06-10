package com.parinherm.kernai.ui

import  scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global

class FuturesDocument extends ReferenceDoc {

  override def run(): Unit = {
    addMessage("Futures")
  }
}
