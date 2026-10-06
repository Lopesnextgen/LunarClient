package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.Burrow;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.util.collection.MapRemoval;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3i;

class BurrowTracker {
   private static final int field1 = 10;
   private final BurrowLocatingListener field2;
   private final BurrowGroundScanner field3;
   private final EquippedItemListener field4;
   private final Map<Vector3i, Burrow> field5 = new HashMap<>();
   private final Map<Vector3i, List<Integer>> field6 = new HashMap<>();
   @Nullable
   private Burrow field7;
   private long field8;
   private int field9;

   BurrowTracker(BurrowLocatingListener guirewindhandlershandler231, BurrowGroundScanner guirewindhandlershandler242, EquippedItemListener guirewindhandlershandler293) {
      this.field2 = guirewindhandlershandler231;
      this.field3 = guirewindhandlershandler242;
      this.field4 = guirewindhandlershandler293;
   }

   void clear() {
      this.field5.clear();
      this.field6.clear();
   }

   void method1() {
      for (Entry entry2 : this.field6.entrySet()) {
         ((List)entry2.getValue()).removeIf(arg0 -> EventTick.field1 >= arg0);
      }

      MapRemoval.method2(this.field6, List::isEmpty);
   }

   void method2(EventSpawnParticle highlightimpl151) {
      this.method3(highlightimpl151);
      this.method4(highlightimpl151);
   }

   private void method3(EventSpawnParticle highlightimpl151) {
      if (this.field4.method7()) {
         if (highlightimpl151.method2() == ParticleType.LARGE_SMOKE) {
            Vector3i vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX()), (int)Math.floor(highlightimpl151.getPosY()) - 1, (int)Math.floor(highlightimpl151.getPosZ()));
            this.field5.remove(vector3i2);
         }
      }
   }

   private void method4(EventSpawnParticle highlightimpl151) {
      ParticleType horsestatstype22 = highlightimpl151.method2();
      if (horsestatstype22 != null) {
         if (!(highlightimpl151.getPosY() % 1.0 < 0.05) && !(highlightimpl151.getPosY() % 1.0 > 0.07)) {
            Vector3i vector3i3 = new Vector3i((int)Math.floor(highlightimpl151.getPosX()), (int)Math.floor(highlightimpl151.getPosY()) - 1, (int)Math.floor(highlightimpl151.getPosZ()));
            Burrow burrow4 = this.field5.get(vector3i3);
            if (burrow4 != null) {
               burrow4.method4(horsestatstype22);
            } else {
               if (this.method5(highlightimpl151) && this.field3.method5(vector3i3)) {
                  if (!this.method8(highlightimpl151)) {
                     return;
                  }

                  this.method7(vector3i3);
                  if (Ref.method3().bridge$getSystemTime() <= this.field8) {
                     return;
                  }

                  int number5 = this.method6(vector3i3);
                  if (number5 > 3) {
                     this.field5.put(vector3i3, new Burrow(vector3i3, horsestatstype22));
                     this.field2.method3(vector3i3);
                  }
               }
            }
         }
      }
   }

   private boolean method5(EventSpawnParticle highlightimpl151) {
      ParticleType horsestatstype22 = highlightimpl151.method2();
      return horsestatstype22 == ParticleType.CRIT
         ? highlightimpl151.getColorR() > 0.5F && highlightimpl151.getColorG() > 0.5F && highlightimpl151.getColorB() > 0.5F
         : horsestatstype22 == ParticleType.DRIPPING_LAVA || horsestatstype22 == ParticleType.ENCHANTED_HIT;
   }

   private int method6(Vector3i vector3i1) {
      return !this.field6.containsKey(vector3i1) ? 0 : this.field6.get(vector3i1).size();
   }

   private void method7(Vector3i vector3i1) {
      if (!this.field6.containsKey(vector3i1)) {
         this.field6.put(vector3i1, new ArrayList<>());
      }

      this.field6.get(vector3i1).add(EventTick.field1 + 10);
   }

   private boolean method8(EventSpawnParticle highlightimpl151) {
      double value2 = 0.3;
      return MathUtils.method18(Math.abs(highlightimpl151.getPosX()) % 1.0, 0.5, value2) && MathUtils.method18(Math.abs(highlightimpl151.getPosZ()) % 1.0, 0.5, value2);
   }

   void method9() {
      if (this.field7 != null) {
         this.field5.remove(this.field7.method5());
      }
   }

   void method10() {
      this.field8 = Ref.method3().bridge$getSystemTime() + 1000L;
   }

   List<Vector3i> method11(int number1) {
      Vector3i vector3i2 = new Vector3i(
         (int)Ref.method7().bridge$getPosX(),
         (int)Ref.method7().bridge$getPosY(),
         (int)Ref.method7().bridge$getPosZ()
      );
      List list3 = this.field5.keySet().stream().filter(arg2x -> arg2x.distanceSquared(vector3i2) < number1 * number1).toList();
      list3.forEach(this.field5::remove);
      return list3;
   }

   void method12() {
      this.field7 = this.method13();
   }

   private Burrow method13() {
      double value1 = Double.MAX_VALUE;
      Burrow burrow3 = null;
      Vector3i vector3i4 = new Vector3i(
         (int)Ref.method7().bridge$getPosX(),
         (int)Ref.method7().bridge$getPosY(),
         (int)Ref.method7().bridge$getPosZ()
      );

      for (Burrow burrow6 : this.field5.values()) {
         double value7 = burrow6.method2(vector3i4);
         if (value7 < value1) {
            value1 = value7;
            burrow3 = burrow6;
         }
      }

      return burrow3;
   }

   void method14() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         HashSet set2 = new HashSet();

         for (Vector3i vector3i4 : this.field5.keySet()) {
            if (bridge5extension_51.method15(vector3i4.x(), vector3i4.y(), vector3i4.z()) > 25.0 && !this.field3.method5(vector3i4)) {
               set2.add(vector3i4);
            }
         }

         if (this.field4.method7()) {
            this.field9++;
            if (this.field9 > 3) {
               for (Burrow burrow8 : this.field5.values()) {
                  Vector3i vector3i5 = burrow8.method5();
                  if (Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(vector3i5.x, vector3i5.y, vector3i5.z) < 225.0 && burrow8.method1()) {
                     set2.add(vector3i5);
                  }
               }
            }
         } else {
            this.field9 = 0;
         }

         for (Vector3i vector3i9 : set2) {
            this.field5.remove(vector3i9);
         }
      }
   }

   @Generated
   public Map<Vector3i, Burrow> method15() {
      return this.field5;
   }
}
