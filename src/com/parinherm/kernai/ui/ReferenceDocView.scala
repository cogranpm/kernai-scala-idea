package com.parinherm.kernai.ui



import org.eclipse.core.databinding.AggregateValidationStatus
import org.eclipse.core.databinding.Binding
import org.eclipse.core.databinding.UpdateValueStrategy
import org.eclipse.core.databinding.beans.BeanProperties
import org.eclipse.core.databinding.observable.list.IObservableList
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.core.databinding.observable.map.IObservableMap
import org.eclipse.core.databinding.observable.set.IObservableSet
import org.eclipse.core.databinding.observable.value.IObservableValue
import org.eclipse.core.databinding.validation.IValidator
import org.eclipse.core.databinding.validation.ValidationStatus
import org.eclipse.core.runtime.IStatus
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport
import org.eclipse.jface.databinding.swt.WidgetProperties
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider
import org.eclipse.jface.databinding.viewers.ViewerProperties
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.jface.layout.TableColumnLayout
import org.eclipse.jface.text.DocumentEvent
import org.eclipse.jface.text.IDocumentListener
import org.eclipse.jface.text.source.AnnotationModel
import org.eclipse.jface.text.source.CompositeRuler
import org.eclipse.jface.text.source.IOverviewRuler
import org.eclipse.jface.text.source.LineNumberRulerColumn
import org.eclipse.jface.text.source.OverviewRuler
import org.eclipse.jface.text.source.SourceViewer
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.CellEditor
import org.eclipse.jface.viewers.ColumnWeightData
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor
import org.eclipse.jface.viewers.ComboViewer
import org.eclipse.jface.viewers.EditingSupport
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.viewers.IStructuredContentProvider
import org.eclipse.jface.viewers.LabelProvider
import org.eclipse.jface.viewers.TableViewerColumn
import org.eclipse.jface.viewers.TextCellEditor
import org.eclipse.jface.viewers.Viewer
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.events.SelectionListener
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Text
import org.eclipse.jface.text.Document
import org.eclipse.jface.text.IDocument
import org.eclipse.jface.text.source.SourceViewerConfiguration
import org.eclipse.text


class ReferenceDocView (parent: Composite, style: Int) extends Composite (parent, style) {

  setLayout(new FillLayout(SWT.VERTICAL))

  val document: IDocument = new Document()
  val VERTICAL_RULER_WIDTH = 12
  val overviewRuler = new OverviewRuler(null, VERTICAL_RULER_WIDTH, null)
  val ruler = new CompositeRuler(VERTICAL_RULER_WIDTH)
  val annotationModel = new AnnotationModel
  annotationModel.connect(document)
  val txtBody = new SourceViewer(this, ruler, overviewRuler, false, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL)
  txtBody.configure(new SourceViewerConfiguration)
  txtBody.setDocument(document, annotationModel)
  ruler.addDecorator(0, new LineNumberRulerColumn)

  document.set("This is from the scala book here")
}
