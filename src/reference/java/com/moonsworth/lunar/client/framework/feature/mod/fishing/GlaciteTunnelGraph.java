package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.util.collection.UnorderedPair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Generated;
import toxi.util.datatypes.UndirectedGraph;

public class GlaciteTunnelGraph {
   private final UndirectedGraph<Fishing2> field1;
   private final Map<UnorderedPair<Fishing2>, Float> field2;

   public GlaciteTunnelGraph(UndirectedGraph<Fishing2> undirectedgraph1) {
      this.field1 = undirectedgraph1;
      this.field2 = this.method2();
   }

   public float method1(Fishing2 fishing21, Fishing2 fishing22) {
      return this.field2.get(new UnorderedPair(fishing21, fishing22));
   }

   private Map<UnorderedPair<Fishing2>, Float> method2() {
      HashMap map1 = new HashMap();

      for (UnorderedPair threadmoduledump53 : this.method3()) {
         map1.put(threadmoduledump53, (float)((Fishing2)threadmoduledump53.method1()).method1((Fishing2)threadmoduledump53.method2()));
      }

      return map1;
   }

   public Set<UnorderedPair<Fishing2>> method3() {
      HashSet set1 = new HashSet();

      for (Fishing2 fishing23 : this.field1.getNodes()) {
         for (Fishing2 fishing25 : this.field1.getConnectedNodesFor(fishing23)) {
            set1.add(new UnorderedPair(fishing23, fishing25));
         }
      }

      return set1;
   }

   @Generated
   public UndirectedGraph<Fishing2> method4() {
      return this.field1;
   }
}
