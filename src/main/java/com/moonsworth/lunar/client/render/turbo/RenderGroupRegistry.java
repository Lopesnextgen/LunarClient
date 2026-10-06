package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.TransparencyMode;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class RenderGroupRegistry {
   private final Map<TransparencyMode, LinkedHashSet<RenderLayerBridge>> field1 = new EnumMap<>(TransparencyMode.class);

   public void method1(RenderLayerBridge var1) {
      this.field1.computeIfAbsent(var1.bridge$getTransparencyType(), var0 -> new LinkedHashSet<>()).add(var1);
   }

   public void reset() {
      this.field1.forEach((var0, var1) -> var1.clear());
   }

   public void method2(TransparencyMode var1) {
      this.field1.get(var1).clear();
   }

   public List<RenderLayerBridge> method3() {
      int var1 = 0;

      for (LinkedHashSet var3 : this.field1.values()) {
         var1 += var3.size();
      }

      ArrayList var5 = new ArrayList(var1);

      for (LinkedHashSet var4 : this.field1.values()) {
         var5.addAll(var4);
      }

      return var5;
   }
}
