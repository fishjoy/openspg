package com.antgroup.openspg.reasoner.lube.common.graph

import java.util.concurrent.atomic.AtomicInteger

/**
 * A graph defined in query, which has a graph name only. Usually the name is KG
 */
trait IRGraph {
  def graphName: String
}

final case class KG() extends IRGraph {
  override def graphName: String = IRGraph.defaultGraphName
}

final case class View(graphName: String) extends IRGraph

object IRGraph {
  val defaultGraphName = "KG"
  private val graphId: AtomicInteger = new AtomicInteger(0)

  def generate: IRGraph = {
    if (graphId.get() == 0) {
      graphId.incrementAndGet()
      KG()
    } else {
      View(defaultGraphName + "_" + graphId.getAndAdd(1))
    }
  }
}
