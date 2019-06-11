package com.parinherm.kernai.ui

import org.eclipse.swt.widgets.Display

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class FuturesDocument (val display: Display) extends ReferenceDoc {

  override def run(): Unit = {
    addMessage("Futures")
    future1()
    future2()
  }

  def future1() : Unit = {
    val f = Future {

      "hello futures"
    }
    f.onComplete {
      case Success(value) => addMyMessage(value)
      case Failure(e) => addMyMessage(s"Future failed ${e.getMessage}")
    }

  }

  def future2() : Unit = {
    val f = Future {
      "OnSuccess and OnFailure example "
    }

    f onSuccess {
      case result => addMyMessage(result)
    }
  }

  def addMyMessage(message: String): Unit = {
    display.asyncExec(new Runnable {
      override def run(): Unit = addMessage(message)
    })

  }


}
