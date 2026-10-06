package com.moonsworth.lunar.client.cosmetics.gecko;

import org.jgrapht.graph.DefaultEdge;

public class DefaultEdgeImpl extends DefaultEdge {
   public DefaultEdgeImpl() {
   }

   public String getSource() {
      return (String)super.getSource();
   }

   public String getTarget() {
      return (String)super.getTarget();
   }
}
