package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.framework.feature.minimap.mixin.Minimap2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventChunkLifecycle.EventChunkLoaded;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class GuiRewindhandlersHandler24 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   public static final int field7 = -283;
   public static final int field8 = 200;
   public static final int field9 = -230;
   public static final int field10 = 200;
   public static final int field11 = 70;
   public static final int field12 = 100;
   private final Map<Long, GuiRewindhandlersHandler24.Data> field13 = new HashMap<>();
   private final Deque<Itemcounter2> field14 = new ArrayDeque<>();

   public GuiRewindhandlersHandler24() {
      this.handle(EventChunkLoaded.class, this::method1);
      this.handle(EventWorldChanged.class, this::method2);
      this.handle(EventClientTick.class, this::method3);
   }

   protected boolean isEnabled() {
      return Click3.getIsland() == Gui2Extension3.HUB;
   }

   protected void onDisable() {
      this.field13.clear();
      this.field14.clear();
   }

   protected void onEnable() {
      int var1 = ThreadModuleDump63.method7().bridge$getBlockX() >> 4;
      int var2 = ThreadModuleDump63.method7().bridge$getBlockZ() >> 4;

      for (int var3 = var1 - 8; var3 <= var1 + 8; var3++) {
         for (int var4 = var2 - 8; var4 <= var2 + 8; var4++) {
            this.field14.add(ThreadModuleDump63.method8().bridge$getChunk(var3, var4));
         }
      }
   }

   private void method1(EventChunkLoaded var1) {
      for (Itemcounter2 var3 : this.field14) {
         if (var3.bridge$getX() == var1.method1().bridge$getX()
            && var3.bridge$getZ() == var1.method1().bridge$getZ()) {
            return;
         }
      }

      long var5 = Minimap2.method2(var1.method1().bridge$getX(), var1.method1().bridge$getZ());
      GuiRewindhandlersHandler24.Data var4 = this.field13.get(var5);
      if (var4 == null || var4.method2() <= ThreadModuleDump63.method3().bridge$getSystemTime() - 300000L) {
         this.field14.add(var1.method1());
      }
   }

   private void method2(EventWorldChanged var1) {
      this.field13.clear();
      this.field14.clear();
   }

   private void method3(EventClientTick var1) {
      this.method5();
   }

   private void method5() {
      if (!this.field14.isEmpty()) {
         Itemcounter2 var1 = this.field14.poll();
         if (var1.bridge$isLoaded()) {
            this.field13.put(method6(var1), new GuiRewindhandlersHandler24.Data(var1));
         } else {
            this.method5();
         }
      }
   }

   public boolean method5(Vector3ic var1) {
      if (!method7(var1.x(), var1.z())) {
         return false;
      }

      if (var1.y() >= 70 && var1.y() <= 100) {
         if (!ThreadModuleDump63.method8().bridge$isChunkLoaded(var1.x() >> 4, var1.z() >> 4)) {
            long var2 = Minimap2.method3(var1);
            GuiRewindhandlersHandler24.Data var4 = this.field13.get(var2);
            return var4 == null ? true : var4.method1(var1);
         } else {
            return ThreadModuleDump63.method8().method5(var1) == Bridge.method34().method7()
               && ThreadModuleDump63.method8().bridge$getBlockAt(var1.x(), var1.y() + 1, var1.z()).bridge$isAir();
         }
      } else {
         return false;
      }
   }

   private static long method6(Itemcounter2 var0) {
      return Minimap2.method2(var0.bridge$getX(), var0.bridge$getZ());
   }

   public static boolean method7(double var0, double var2) {
      return var0 >= -283.0 && var0 <= 200.0 && var2 >= -230.0 && var2 <= 200.0 && var0 + var2 >= -380.0 && var2 - var0 >= -300.0;
   }

   private static class Data {
      private final long field1 = ThreadModuleDump63.method3().bridge$getSystemTime();
      private final Set<Vector3ic> field2 = new HashSet<>();

      public Data(Itemcounter2 var1) {
         int var2 = var1.bridge$getX() << 4;
         int var3 = var1.bridge$getZ() << 4;
         Bridge3_23 var4 = Bridge.method34().method7();

         for (int var5 = 0; var5 < 16; var5++) {
            for (int var6 = 0; var6 < 16; var6++) {
               for (int var7 = 70; var7 <= 100; var7++) {
                  if (GuiRewindhandlersHandler24.method7(var5 + var2, var6 + var3)) {
                     Bridge3_23 var8 = var1.bridge$getBlockState(var5, var7, var6).bridge$getBlock();
                     if (var8 == var4) {
                        Bridge3_23 var9 = var1.bridge$getBlockState(var5, var7 + 1, var6).bridge$getBlock();
                        if (var9.bridge$isAir()) {
                           this.field2.add(new Vector3i(var5 + var2, var7, var6 + var3));
                        }
                     }
                  }
               }
            }
         }
      }

      public boolean method1(Vector3ic var1) {
         return this.field2.contains(var1);
      }

      public long method2() {
         return this.field1;
      }
   }
}
