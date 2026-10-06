package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.TransparencyMode;
import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.fishing.Fishing2Extension;
import com.moonsworth.lunar.client.render.turbo.TransparencyLayerMap;
import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.EnumMap;
import java.util.Map;

public class RenderGroup {
   private final Fishing2Extension field1;
   private final TransparencyLayerMap field2;
   private final EnumMap<TransparencyMode, RenderLayerBridge> field3;
   private boolean field4 = false;

   public RenderGroup() {
      this.field1 = (Fishing2Extension)Fishing.method2(Fishing2Extension.class).orElse(null);
      this.field2 = new TransparencyLayerMap();
      this.field3 = new EnumMap<>(TransparencyMode.class);

      for (TransparencyMode var4 : TransparencyMode.values()) {
         this.field2.put(var4, new Object2IntOpenHashMap());
      }
   }

   public TransparencyLayerMap method1() {
      return new TransparencyLayerMap(this.field2);
   }

   private TransparencyMode method2(RenderLayerBridge var1) {
      if (this.field1 != null) {
         TransparencyMode var2 = this.field1.lunar$getTransparencyType(var1);
         if (var2 != null) {
            return var2;
         }

         var1 = this.field1.lunar$unwrapRenderType(var1);
      }

      return var1.bridge$getTransparencyType();
   }

   public void method3(RenderLayerBridge var1) {
      if (this.field4) {
         TransparencyMode var2 = this.method2(var1);
         RenderLayerBridge var3 = this.field3.put(var2, var1);
         Map var4 = (Map)this.field2.get(var2);
         if (var3 == null) {
            var4.merge(Pair.of(var1, var1), 1, Integer::sum);
            return;
         }

         var4.merge(Pair.of(var3, var1), 1, Integer::sum);
      }
   }

   public void method4() {
      if (this.field4) {
         throw new IllegalStateException("Already in a group");
      }

      this.field3.clear();
      this.field4 = true;
   }

   public void method5() {
      if (!this.field4) {
         throw new IllegalStateException("Not in a group");
      }

      this.field3.clear();
      this.field4 = false;
   }

   public void reset() {
      this.field2.clear();

      for (TransparencyMode var4 : TransparencyMode.values()) {
         this.field2.put(var4, new Object2IntOpenHashMap());
      }
   }
}
