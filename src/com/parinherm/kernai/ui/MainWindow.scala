package com.parinherm.kernai.ui

import org.eclipse.core.databinding.observable.Realm
import org.eclipse.jface.action.Action
import org.eclipse.jface.action.ActionContributionItem
import org.eclipse.jface.action.IAction
import org.eclipse.jface.action.MenuManager
import org.eclipse.jface.action.Separator
import org.eclipse.jface.action.StatusLineManager
import org.eclipse.jface.action.ToolBarManager
import org.eclipse.jface.window.ApplicationWindow
import org.eclipse.swt.SWT
import org.eclipse.swt.custom.CTabFolder
import org.eclipse.swt.custom.CTabItem
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.layout.{FillLayout, GridData, GridLayout, RowLayout}
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Event
import org.eclipse.swt.widgets.Listener
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.ToolBar
import org.eclipse.swt.widgets.Text
import org.eclipse.swt.graphics.Point
import org.eclipse.swt.custom.SashForm
import org.eclipse.swt.widgets.Button
import org.eclipse.nebula.widgets.pshelf._

class MainWindow extends ApplicationWindow(null){

  addToolBar(SWT.FLAT | SWT.WRAP)
  addMenuBar()
  addStatusLine()

  override def createContents(parent: Composite): Control = {
    val container = new Composite(parent, SWT.NONE)
    container.setLayout(new FillLayout)

    val sashForm: SashForm = new SashForm(container, SWT.HORIZONTAL)
    val weights: Array[Int] = Array[Int](1, 2)
    //sashForm.setWeights(weights)
    val navContainer: Composite = new Composite(sashForm, SWT.NONE)
    val mainContainer: Composite = new Composite(sashForm, SWT.NONE)

    navContainer.setLayout(new FillLayout(SWT.VERTICAL))
    mainContainer.setLayout(new FillLayout(SWT.VERTICAL))

    val navShelf: PShelf = new PShelf(navContainer, SWT.NONE)
    navShelf.setRenderer(new RedmondShelfRenderer)
    val navReference: PShelfItem = new PShelfItem(navShelf, SWT.NONE)
    navReference.setText("Reference")
    navReference.getBody.setLayout(new RowLayout(SWT.VERTICAL))
    val btnScala = SWTHelper.NavButton(navReference.getBody, mainContainer,  "First Steps")
    val btnFutures = SWTHelper.NavButton(navReference.getBody, mainContainer, "Futures")

    val navPlaceHolder = new PShelfItem(navShelf, SWT.NONE)
    navPlaceHolder.setText("PlaceHolder")
    navPlaceHolder.getBody.setLayout(new FillLayout(SWT.VERTICAL))


    val folder = new CTabFolder(mainContainer, SWT.TOP | SWT.BORDER)
    val item = new CTabItem(folder, SWT.NONE)
    item.setText("&Getting Started")
    val masterPropertyTabItem = new CTabItem(folder, SWT.NONE)
    masterPropertyTabItem.setText("&Master Properties")

    container
  }

  override def createMenuManager(): MenuManager = {
    val menuManager = new MenuManager("Menu")
    val fileMenu = new MenuManager("&File")
    fileMenu.add(new Separator())
    menuManager.add(fileMenu)
    menuManager
  }

  override def createToolBarManager(style: Int): ToolBarManager = {
    val toolBarManager = new ToolBarManager(SWT.NONE);
    toolBarManager.update(true)
    toolBarManager
  }

  override def createStatusLineManager(): StatusLineManager = new StatusLineManager

  override def configureShell(shell: Shell): Unit = {
    super.configureShell(shell)
    shell.setText("Kernai")

  }

  override def getInitialSize: Point = new Point(900, 900)



}


object SWTHelper {

  def NavButton(container: Composite, targetContainer: Composite,  label: String): Button = {

    val btn: Button = new Button(container, SWT.PUSH)
    btn.setText(label)

    btn.addSelectionListener(new SelectionListener {
      override def widgetSelected(selectionEvent: SelectionEvent): Unit = {

        //overwrite whatever is current in the main view with our new composite
        val docView: ReferenceDocView = new ReferenceDocView(clearComposite(targetContainer), SWT.BORDER)
        //this must be called in order to draw properly
        targetContainer.layout()
      }

      override def widgetDefaultSelected(selectionEvent: SelectionEvent): Unit = ???
    })


    btn
  }

  private def clearComposite(composite: Composite) = {
    for (control <- composite.getChildren) {
      control.dispose
    }
    composite
  }

}

object KernaiApp extends App {

  import org.eclipse.core.databinding.observable.Realm
  import org.eclipse.jface.databinding.swt.DisplayRealm
  import org.eclipse.swt.widgets.Display

  val display: Display = Display.getDefault

  Realm.runWithDefault(DisplayRealm.getRealm(display), new Runnable() {
    override def run(): Unit = {
      try {
        val window = new MainWindow
        window.setBlockOnOpen(true)
        window.open
        Display.getCurrent.dispose()
      } catch {
        case e: Exception =>
          e.printStackTrace()
      }
    }
  })
}