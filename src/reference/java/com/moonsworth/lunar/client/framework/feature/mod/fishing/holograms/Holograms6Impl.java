package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 33)
public class Holograms6Impl extends Holograms6_3 {
   public Holograms6Impl(Holograms3_2 var1) {
      super("Score", var1);
   }

   @Override
   public void method1(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      if (ThreadModuleDump63.method7() != null) {
         ItemStackBridge var7 = (ItemStackBridge)ThreadModuleDump63.method7().bridge$getInventory().bridge$getMainInventory().get(8);
         if (var7 != null && !var7.bridge$isEmpty() && var7.bridge$getItem() == Bridge.method28().method30() && var7.bridge$hasDisplayName()) {
            if (var3.method48()) {
               if (var7.bridge$getItem() instanceof Bridge5_4 var8) {
                  Itemcounter2_3 var13 = var8.bridge$getMapData(var7, ThreadModuleDump63.method8());
                  Integer var10 = var8.bridge$getMapId(var7, ThreadModuleDump63.method8());
                  if (var13 != null && var10 != null) {
                     com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 var11 = new com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2(
                        var13, var10
                     );
                     float var12 = var2.method35();
                     this.method4(var1, var11, var4 + var12, var5 + var12, 100.0F - var12 * 2.0F, 100.0F - var12 * 2.0F);
                  }
               }
            }
         }
      }
   }

   @Override
   public void method2(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
   }

   @Override
   public boolean method3(Holograms_9 var1, Holograms2_5 var2) {
      if (ThreadModuleDump63.method7() == null) {
         return false;
      } else {
         ItemStackBridge var3 = (ItemStackBridge)ThreadModuleDump63.method7().bridge$getInventory().bridge$getMainInventory().get(8);
         if (var3 == null || var3.bridge$isEmpty() || var3.bridge$getItem() != Bridge.method28().method30() || !var3.bridge$hasDisplayName()) {
            return false;
         } else {
            return this.field2.method24() ? false : var2.method48();
         }
      }
   }

   private void method4(
      MixinHelper_4 var1,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms2 var2,
      float var3,
      float var4,
      float var5,
      float var6
   ) {
      ResourceLocationBridge var7 = ThreadModuleDump63.method3()
         .bridge$getGameRenderer()
         .bridge$getMapItemRenderer()
         .bridge$getMapTexture(var2.method1(), var2.method2());
      if (var7 != null) {
         this.field2.method19(var1, var7, var3, var4, var5, var6, 0.0F, 0.0F, var5, var6);
      }
   }
}
