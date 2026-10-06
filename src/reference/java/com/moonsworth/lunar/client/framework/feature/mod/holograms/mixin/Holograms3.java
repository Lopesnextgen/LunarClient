package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.GuiRewindhandlersHandler29;
import com.moonsworth.lunar.client.framework.feature.mod.impl.burrow.Burrow;
import com.moonsworth.lunar.client.event.mixin.fishing.EventParticleSpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.ThreadModuleDump78;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3i;

class Holograms3 {
   private static final int field1 = 10;
   private final GuiRewindhandlersHandler23 field2;
   private final GuiRewindhandlersHandler24 field3;
   private final GuiRewindhandlersHandler29 field4;
   private final Map<Vector3i, Burrow> field5 = new HashMap<>();
   private final Map<Vector3i, List<Integer>> field6 = new HashMap<>();
   @Nullable
   private Burrow field7;
   private long field8;
   private int field9;

   Holograms3(GuiRewindhandlersHandler23 var1, GuiRewindhandlersHandler24 var2, GuiRewindhandlersHandler29 var3) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
   }

   void clear() {
      this.field5.clear();
      this.field6.clear();
   }

   void method1() {
      for (Entry var2 : this.field6.entrySet()) {
         ((List)var2.getValue()).removeIf(var0 -> EventClientTick.field1 >= var0);
      }

      ThreadModuleDump78.removeIfValue(this.field6, List::isEmpty);
   }

   void method2(EventParticleSpawn var1) {
      this.method3(var1);
      this.method4(var1);
   }

   private void method3(EventParticleSpawn var1) {
      if (this.field4.method7()) {
         if (var1.method2() == HorsestatsType2.LARGE_SMOKE) {
            Vector3i var2 = new Vector3i((int)Math.floor(var1.getPosX()), (int)Math.floor(var1.getPosY()) - 1, (int)Math.floor(var1.getPosZ()));
            this.field5.remove(var2);
         }
      }
   }

   private void method4(EventParticleSpawn var1) {
      HorsestatsType2 var2 = var1.method2();
      if (var2 != null) {
         if (!(var1.getPosY() % 1.0 < 0.05) && !(var1.getPosY() % 1.0 > 0.07)) {
            Vector3i var3 = new Vector3i((int)Math.floor(var1.getPosX()), (int)Math.floor(var1.getPosY()) - 1, (int)Math.floor(var1.getPosZ()));
            Burrow var4 = this.field5.get(var3);
            if (var4 != null) {
               var4.method4(var2);
            } else {
               if (this.method5(var1) && this.field3.method5(var3)) {
                  if (!this.method8(var1)) {
                     return;
                  }

                  this.method7(var3);
                  if (ThreadModuleDump63.method3().bridge$getSystemTime() <= this.field8) {
                     return;
                  }

                  int var5 = this.method6(var3);
                  if (var5 > 3) {
                     this.field5.put(var3, new Burrow(var3, var2));
                     this.field2.method3(var3);
                  }
               }
            }
         }
      }
   }

   private boolean method5(EventParticleSpawn var1) {
      HorsestatsType2 var2 = var1.method2();
      return var2 == HorsestatsType2.CRIT
         ? var1.getColorR() > 0.5F && var1.getColorG() > 0.5F && var1.getColorB() > 0.5F
         : var2 == HorsestatsType2.DRIPPING_LAVA || var2 == HorsestatsType2.ENCHANTED_HIT;
   }

   private int method6(Vector3i var1) {
      return !this.field6.containsKey(var1) ? 0 : this.field6.get(var1).size();
   }

   private void method7(Vector3i var1) {
      if (!this.field6.containsKey(var1)) {
         this.field6.put(var1, new ArrayList<>());
      }

      this.field6.get(var1).add(EventClientTick.field1 + 10);
   }

   private boolean method8(EventParticleSpawn var1) {
      double var2 = 0.3;
      return ThreadModuleDump67.method18(Math.abs(var1.getPosX()) % 1.0, 0.5, var2) && ThreadModuleDump67.method18(Math.abs(var1.getPosZ()) % 1.0, 0.5, var2);
   }

   void method9() {
      if (this.field7 != null) {
         this.field5.remove(this.field7.method5());
      }
   }

   void method10() {
      this.field8 = ThreadModuleDump63.method3().bridge$getSystemTime() + 1000L;
   }

   List<Vector3i> method11(int var1) {
      Vector3i var2 = new Vector3i(
         (int)ThreadModuleDump63.method7().bridge$getPosX(),
         (int)ThreadModuleDump63.method7().bridge$getPosY(),
         (int)ThreadModuleDump63.method7().bridge$getPosZ()
      );
      List var3 = this.field5.keySet().stream().filter(var2x -> var2x.distanceSquared(var2) < var1 * var1).toList();
      var3.forEach(this.field5::remove);
      return var3;
   }

   void method12() {
      this.field7 = this.method13();
   }

   private Burrow method13() {
      double var1 = Double.MAX_VALUE;
      Burrow var3 = null;
      Vector3i var4 = new Vector3i(
         (int)ThreadModuleDump63.method7().bridge$getPosX(),
         (int)ThreadModuleDump63.method7().bridge$getPosY(),
         (int)ThreadModuleDump63.method7().bridge$getPosZ()
      );

      for (Burrow var6 : this.field5.values()) {
         double var7 = var6.method2(var4);
         if (var7 < var1) {
            var1 = var7;
            var3 = var6;
         }
      }

      return var3;
   }

   void method14() {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 != null) {
         HashSet var2 = new HashSet();

         for (Vector3i var4 : this.field5.keySet()) {
            if (var1.ORICHRORRORHORHOIHCRHOORCRRHOI(var4.x(), var4.y(), var4.z()) > 25.0 && !this.field3.method5(var4)) {
               var2.add(var4);
            }
         }

         if (this.field4.method7()) {
            this.field9++;
            if (this.field9 > 3) {
               for (Burrow var8 : this.field5.values()) {
                  Vector3i var5 = var8.method5();
                  if (ThreadModuleDump63.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(var5.x, var5.y, var5.z) < 225.0 && var8.method1()) {
                     var2.add(var5);
                  }
               }
            }
         } else {
            this.field9 = 0;
         }

         for (Vector3i var9 : var2) {
            this.field5.remove(var9);
         }
      }
   }

   @Generated
   public Map<Vector3i, Burrow> method15() {
      return this.field5;
   }
}
