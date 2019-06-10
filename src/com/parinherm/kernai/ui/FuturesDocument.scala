package com.parinherm.kernai.ui

import  scala.concurrent.{Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class FuturesDocument extends ReferenceDoc {

  override def run(): Unit = {
    addMessage("Futures")
    future1()
  }

  def future1() : Unit = {
    val f = Future {

      "hello futures"
    }
    f.onComplete {
      case Success(value) => addMessage(value)
      case Failure(e) => addMessage(s"Future failed ${e.getMessage}")
    }

  }


}
