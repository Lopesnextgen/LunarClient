package com.moonsworth.lunar.client.cosmetics.emote;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.client.cosmetics.CosmeticMetadata;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.vecmath.Matrix4f;
import mchorse.emoticons.morph.MorphConfiguration;

public class MorphTracker {
   private final Map<com.moonsworth.lunar.client.cosmetics.OwnedCosmetic, MorphTimeline> field1 = new HashMap<>();
   private final MorphConfiguration field2;

   public MorphTracker(MorphConfiguration morphconfiguration1) {
      this.field2 = morphconfiguration1;
   }

   public void method1(Consumer<MorphRenderer> consumer1) {
      for (MorphTimeline holograms33 : this.field1.values()) {
         holograms33.method2(consumer1);
      }
   }

   public boolean method2() {
      for (MorphTimeline holograms32 : this.field1.values()) {
         if (holograms32.method3()) {
            return true;
         }
      }

      return false;
   }

   public void update(EntityLivingBridge bridgeextension2_51) {
      if (!(Boolean)Ref.method4().method41().method6().method34().get()) {
         this.field1.clear();
      } else {
         List list2 = Ref.method4().method53().method19(bridgeextension2_51.bridge$getUniqueID());
         if (list2 != null && !list2.isEmpty()) {
            List list3 = list2.stream().map(CosmeticMetadata::method4).toList();
            List list4 = list2.stream().map(CosmeticMetadata::method4).toList();
            this.field1.entrySet().removeIf(arg1x -> {
               com.moonsworth.lunar.client.cosmetics.OwnedCosmetic gui2handler2x = arg1x.getKey();
               if (!gui2handler2x.method10().canShowCosmetic()) {
                  return true;
               }

               for (com.moonsworth.lunar.client.cosmetics.OwnedCosmetic gui2handler4x : list3) {
                  if (gui2handler4x == gui2handler2x) {
                     return false;
                  }
               }

               return true;
            });

            for (com.moonsworth.lunar.client.cosmetics.OwnedCosmetic gui2handler6 : list4) {
               if (gui2handler6.method10().canShowCosmetic()) {
                  int index7 = gui2handler6.method2().getId();
                  MorphTracker.MorphEntry data8 = (MorphTracker.MorphEntry)Ref.method4().method53().method61().get(index7);
                  if (data8 != null) {
                     this.field1.computeIfAbsent(gui2handler6, arg2x -> new MorphTimeline(this.field2, data8.morph, data8.field1)).update(bridgeextension2_51);
                  }
               }
            }
         } else {
            this.field1.clear();
         }
      }
   }

   public void method3(EntityPlayerBridge bridgeextension2221, Matrix4f matrix4f2, IBoneRenderer holograms23, float value4) {
      this.method1(arg4x -> {
         Matrix4f matrix4f5 = new Matrix4f(matrix4f2);
         Ref.method4().method70().method9(matrix4f5, bridgeextension2221, holograms23, arg4x, value4);
      });
   }

   public class MorphEntry {
      private final String morph;
      private final int field1;

      public MorphEntry(String text1, int number2) {
         this.morph = text1;
         this.field1 = number2;
      }

      public String method1() {
         return this.morph;
      }

      public int method2() {
         return this.field1;
      }
   }
}
