package com.parinherm.kernai.ui


import org.eclipse.jface.text.Document
import org.eclipse.jface.text.IDocument


abstract class ReferenceDoc  {
  val document: IDocument = new Document()

  def getDocument(): IDocument = document
  def addMessage(message: String): Unit = document.set(document.get() + message + "\n")
  def run(): Unit
}
