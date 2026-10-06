package com.moonsworth.lunar.client.config.option;

import it.unimi.dsi.fastutil.Pair;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface OptionTreeMapper<IN extends OptionHierarchyNode<IN>, OUT extends OptionHierarchyNode<?>, Child> {
   default Map<ClientOption<?>, OUT> method1(Map<ClientOption<?>, IN> var1) {
      LinkedHashMap var2 = new LinkedHashMap();

      for (Entry var4 : var1.entrySet()) {
         var2.put((ClientOption)var4.getKey(), this.method2((IN)var4.getValue()));
      }

      return var2;
   }

   OUT method2(IN var1);

   Child method3(IN var1);

   Pair<Map<ClientOption<?>, IN>, Collection<IN>> method4(Map<ClientOption<?>, IN> var1);

   default List<Child> method5(IN var1) {
      LinkedList var2 = new LinkedList();

      for (OptionHierarchyNode var4 : var1.getChildren()) {
         var2.add(this.method3((IN)var4));
      }

      return var2;
   }
}
