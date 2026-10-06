package com.moonsworth.lunar.client.config.option;

import it.unimi.dsi.fastutil.Pair;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.moonsworth.lunar.client.config.option.ClientOption;

public interface SettingsTreeMapper<IN extends OptionTreeNode<IN>, OUT extends OptionTreeNode<?>, Child> {
   default Map<ClientOption<?>, OUT> method1(Map<ClientOption<?>, IN> map1) {
      LinkedHashMap map2 = new LinkedHashMap();

      for (Entry entry4 : map1.entrySet()) {
         map2.put((ClientOption)entry4.getKey(), this.method2((IN)entry4.getValue()));
      }

      return map2;
   }

   OUT method2(IN in1);

   Child method3(IN in1);

   Pair<Map<ClientOption<?>, IN>, Collection<IN>> method4(Map<ClientOption<?>, IN> map1);

   default List<Child> method5(IN in1) {
      LinkedList list2 = new LinkedList();

      for (OptionTreeNode lighting_44 : in1.getChildren()) {
         list2.add(this.method3((IN)lighting_44));
      }

      return list2;
   }
}
