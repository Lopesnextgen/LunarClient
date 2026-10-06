package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.client.render.turbo.BatchEntityType;
import com.moonsworth.lunar.client.render.turbo.RenderGroupRegistry;
import com.moonsworth.lunar.client.util.ThreadModuleDump65;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Annotation2(min = 8)
public class SpriteTask implements SpriteSource {
   private final RenderGroupRegistry field1 = new RenderGroupRegistry();
   private final Map<BatchEntityType, Map<RenderLayerBridge, List<Runnable>>> field2 = (Map<BatchEntityType, Map<RenderLayerBridge, List<Runnable>>>)ThreadModuleDump65.make(
      new EnumMap<>(BatchEntityType.class), var0 -> {
         for (BatchEntityType var4 : BatchEntityType.values()) {
            var0.put(var4, new HashMap());
         }
      }
   );

   @Override
   public void method3(BatchEntityType var1, RenderLayerBridge var2, Runnable var3, boolean var4) {
      if (var4) {
         this.field1.method1(var2);
      }

      this.field2.get(var1).computeIfAbsent(var2, var0 -> new LinkedList<>()).add(var3);
   }

   @Override
   public void method4(BatchEntityType var1) {
      Map var2 = this.field2.get(var1);
      if (!var2.isEmpty()) {
         for (RenderLayerBridge var4 : this.field1.method3()) {
            List var5 = (List)var2.remove(var4);
            if (var5 != null && !var5.isEmpty()) {
               var4.bridge$setupRenderState();
               var5.forEach(Runnable::run);
               var4.bridge$clearRenderState();
            }
         }

         var2.clear();
      }
   }
}
