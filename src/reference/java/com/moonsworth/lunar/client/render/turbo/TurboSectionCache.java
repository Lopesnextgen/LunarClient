package com.moonsworth.lunar.client.render.turbo;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.render.turbo.TurboEngineManager;
import com.moonsworth.lunar.client.render.turbo.PathSearchContext;
import com.moonsworth.lunar.client.event.render.EventFogSetup.FogSource;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

@Annotation2(min = 8)
public class TurboSectionCache {
   private static final int field1 = 20;
   private final Map<Horsestats20Extension, Set<BridgeExtension>> field2 = new HashMap<>();
   private final WeakHashMap<BridgeExtension, Integer> field3 = new WeakHashMap<>();

   public void clear() {
      this.field3.clear();
      this.field2.clear();
   }

   public void method1(Itemcounter2 var1) {
      for (int var2 = var1.bridge$getMinSection(); var2 < var1.bridge$getMaxSection(); var2++) {
         Horsestats20Extension var3 = Horsestats20Extension.method2(var1.bridge$getX(), var2, var1.bridge$getZ());
         this.field2.remove(var3);
      }
   }

   public void method2(BridgeExtension var1) {
      this.field3.remove(var1);
      Set var2 = this.field2.get(var1.method1());
      if (var2 != null) {
         var2.remove(var1);
      }
   }

   public void method3(BridgeExtension var1, int var2) {
      this.field3.put(var1, var1.method2() + var2);
   }

   public boolean method4(TurboEntityRecorder var1, BridgeExtension var2) {
      Integer var3 = this.field3.get(var2);
      if (var3 != null) {
         if (var2.method2() - var3 <= 20) {
            return false;
         }

         int var4 = var2.bridge$canTurbo();
         if (var4 >= 0) {
            this.method3(var2, var4);
            return false;
         }

         float var5 = var2.bridge$minimumTurboDistance();
         if (var5 > 0.0F && var2.method13(ThreadModuleDump63.method7()) <= var5) {
            return false;
         }

         Vec3Bridge var6 = ThreadModuleDump63.method13().bridge$getCameraPos();
         FogSource var7 = var1.method12();
         if (var7 == FogSource.WATER) {
            if (var2.bridge$isInWaterOrBubble()) {
               this.method3(var2, var4);
               return false;
            }
         } else {
            if (!TurboEngineManager.method22(var7) || PathSearchContext.method7(Bridge.method42())) {
               this.method3(var2, var4);
               return false;
            }

            float var8 = PathSearchContext.method9(Bridge.method42());
            if (var6.method4(var2.bridge$getPosX(), var2.bridge$getPosY(), var2.bridge$getPosZ()) > var8 * var8) {
               this.method3(var2, var4);
               return false;
            }
         }

         if (!var2.bridge$shouldRender(var6.bridge$xCoord(), var6.bridge$yCoord(), var6.bridge$zCoord())) {
            this.method3(var2, var4);
            return false;
         } else {
            return !(var2 instanceof Bridge6_10 var9 && ThreadModuleDump63.method4().method45().method9(var9));
         }
      } else {
         this.method3(var2, 0);
         return false;
      }
   }

   public void method5(TurboEntityRecorder var1, Horsestats20Extension var2, BridgeExtension var3, boolean var4) {
      this.method6(var1, var2, var3, var4, false);
   }

   public void method6(TurboEntityRecorder var1, Horsestats20Extension var2, BridgeExtension var3, boolean var4, boolean var5) {
      if (var4) {
         Set var6 = this.field2.get(var2);
         if (var6 == null) {
            LinkedHashSet var7 = new LinkedHashSet();
            var7.add(var3);
            this.field2.put(var2, var7);
            var1.method13(var2, var5);
         } else if (var6.add(var3)) {
            var1.method13(var2, var5);
         }
      } else {
         var3.bridge$setTurbo(false);
         var1.method13(var2, var5);
      }
   }

   public void method7(Horsestats20Extension var1, boolean var2) {
      Set var3 = this.field2.remove(var1);
      if (var3 != null) {
         for (BridgeExtension var5 : var3) {
            var5.bridge$setTurbo(var2);
         }
      }
   }
}
