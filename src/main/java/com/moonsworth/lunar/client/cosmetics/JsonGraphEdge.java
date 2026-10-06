package com.moonsworth.lunar.client.cosmetics;

import com.google.gson.JsonObject;
import org.jgrapht.graph.DefaultEdge;

public class JsonGraphEdge extends DefaultEdge {
   public JsonObject method1() {
      return (JsonObject)super.getSource();
   }

   public JsonObject method2() {
      return (JsonObject)super.getTarget();
   }
}
