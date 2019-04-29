package com.parinherm.kernai.ui

class ScalableLanguageDocument extends ReferenceDoc {

  override def run(): Unit = {
    addMessage("A Scalable Language")
    grows()
  }

  private def grows(): Unit =
  {
    var capital = Map("US" -> "Washington", "France" -> "Paris")
    addMessage("Map")
    addMessage("Map(\"US\" -> \"Washington\", \"France\" -> \"Paris\")")
    addMessage("capital += (\"Japan\" -> \"Tokyo\")")
    addMessage("println(capital(\"France\")")
    addMessage("Maps are not built into the language, are a library feature, this is hallmark of Scala")
    addMessage("BigInt")
  }
}
