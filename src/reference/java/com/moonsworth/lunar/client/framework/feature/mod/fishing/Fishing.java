package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.util.ThreadModuleDump5;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Generated;
import toxi.util.datatypes.UndirectedGraph;

public class Fishing {
   private final UndirectedGraph<Fishing2> field1;
   private final Map<ThreadModuleDump5<Fishing2>, Float> field2;

   public Fishing(UndirectedGraph<Fishing2> var1) {
      this.field1 = var1;
      this.field2 = this.method2();
   }

   public float method1(Fishing2 var1, Fishing2 fishing2) {
      return this.field2.get(new ThreadModuleDump5(var1, fishing2));
   }

   private Map<ThreadModuleDump5<Fishing2>, Float> method2() {
      HashMap var1 = new HashMap();

      for (ThreadModuleDump5 var3 : this.method3()) {
         var1.put(var3, (float)((Fishing2)var3.method1()).method1((Fishing2)var3.method2()));
      }

      return var1;
   }

   public Set<ThreadModuleDump5<Fishing2>> method3() {
      HashSet var1 = new HashSet();

      for (Fishing2 var3 : this.field1.getNodes()) {
         for (Fishing2 var5 : this.field1.getConnectedNodesFor(var3)) {
            var1.add(new ThreadModuleDump5(var3, var5));
         }
      }

      return var1;
   }

   @Generated
   public UndirectedGraph<Fishing2> method4() {
      return this.field1;
   }
}
