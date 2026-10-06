package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockModel;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockGeometryFile;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelDescription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

public class BoneHierarchyBuilder {
   public HashMap<String, BoneHierarchyNode> field1 = new HashMap<>();
   public ModelDescription field2;

   public BoneHierarchyBuilder() {
   }

   public static BoneHierarchyBuilder method1(BedrockGeometryFile rewindhandlers3_20) {
      BoneHierarchyBuilder rewindhandlers21 = new BoneHierarchyBuilder();
      BedrockModel rewindhandlers132 = rewindhandlers3_20.method3()[0];
      rewindhandlers21.field2 = rewindhandlers132.method4();
      ArrayList list3 = new ArrayList<>(Arrays.asList(rewindhandlers132.method1()));
      HashMap map4 = new HashMap();
      DefaultDirectedGraph defaultdirectedgraph5 = new DefaultDirectedGraph(DefaultEdgeImpl.class);
      list3.forEach(arg2x -> {
         defaultdirectedgraph5.addVertex(arg2x.getName());
         map4.put(arg2x.getName(), new BoneHierarchyNode(arg2x));
      });
      list3.forEach(arg1x -> {
         if (arg1x.getParent() != null) {
            defaultdirectedgraph5.addEdge(arg1x.getParent(), arg1x.getName());
         }
      });
      CycleDetector cycledetector6 = new CycleDetector(defaultdirectedgraph5);
      if (cycledetector6.detectCycles()) {
         LunarLogger.method7("Invalid parenting cycle in geo.json", new Object[0]);
         Set set12 = cycledetector6.findCycles();
         LunarLogger.method7("The following geo bones are in a cycle", new Object[0]);
         set12.forEach(System.out::println);
         return rewindhandlers21;
      }

      TopologicalOrderIterator topologicalorderiterator7 = new TopologicalOrderIterator(defaultdirectedgraph5);

      while (topologicalorderiterator7.hasNext()) {
         String text8 = (String)topologicalorderiterator7.next();
         BoneHierarchyNode rewindhandlers9 = (BoneHierarchyNode)map4.get(text8);
         ArrayList list10 = new ArrayList(defaultdirectedgraph5.incomingEdgesOf(text8));
         if (list10.size() == 0) {
            rewindhandlers21.field1.put(text8, rewindhandlers9);
         } else {
            String text11 = ((DefaultEdgeImpl)list10.get(0)).getSource();
            ((BoneHierarchyNode)map4.get(text11)).field1.put(text8, rewindhandlers9);
         }
      }

      return rewindhandlers21;
   }
}
