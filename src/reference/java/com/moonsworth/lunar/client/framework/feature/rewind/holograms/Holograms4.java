package com.moonsworth.lunar.client.framework.feature.rewind.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.HashMapImpl;
import java.util.HashMap;
import java.util.Map;

public class Holograms4 {
   private static final Map<String, Class<?>> field1 = new HashMap<>();

   private static void method1(Gui_2<?> var0) {
      field1.put(var0.type(), var0.getClass());
   }

   public static Class<?> getType(String var0) {
      return field1.get(var0);
   }

   static {
      method1(new GuiImpl(null, new HashMapImpl()));
   }
}
