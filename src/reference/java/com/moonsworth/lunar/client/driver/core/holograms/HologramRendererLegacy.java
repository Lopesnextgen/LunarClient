package com.moonsworth.lunar.client.driver.core.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.holograms.HologramsIteratorLegacy;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.driver.holograms.MarkerModelRendererLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.holograms.mixin.EmoteHologramLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump54;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;

public class HologramRendererLegacy {
   public static boolean field1 = false;
   public static boolean field2 = false;
   private com.moonsworth.lunar.client.driver.holograms.Holograms field3;
   private final Holograms field4;
   private final ConcurrentHashMap<String, Holograms2<?>> field5 = new ConcurrentHashMap<>();
   private final HologramsIterator2 field6 = new HologramsIterator2();
   private final Map<HologramSubjectLegacy, MarkerModelRendererLegacy<?>> field7 = new HashMap<>(
      Map.of(HologramSubjectLegacy.PLAYER, this.field6, HologramSubjectLegacy.COSMETIC, new HologramsIteratorLegacy())
   );
   private static final int field8 = 3;
   private static final long field9 = 40L;
   private final Queue<Holograms2<?>> field10 = new LinkedList<>();
   private final HashMap<String, Holograms2<?>> field11 = new HashMap<>();
   private long field12 = 0L;
   private boolean field13 = true;
   private boolean field14 = false;
   private String field15 = null;

   public void method1(String var1) {
      if (!Objects.equals(this.field15, var1)) {
         this.field15 = var1;
      }
   }

   public HologramRendererLegacy() {
      this.field4 = new Holograms(this);
      ClientEventBus.method29().method2(EventClientTick.class, this::method7);
   }

   public void method2(AbstractRenderContext var1, MarkerModel.Data5 var2) {
      if (ThreadModuleDump63.method3().bridge$areResourcesLoaded()) {
         if (this.field3 == null) {
            this.field3 = new com.moonsworth.lunar.client.driver.holograms.Holograms();
         }

         if (method12()) {
            var2 = new MarkerModel.Data5(0.0, 0.0);
         }

         this.processQueue();
         field1 = true;

         for (Holograms2 var4 : this.field5.values()) {
            if (var4.method14().getWidth() != 0.0F && var4.method14().getHeight() != 0.0F) {
               MarkerModelRendererLegacy var5 = this.field7.get(var4.method12());
               var4.method6(var2);
               var5.method1(var4);
               if (var4.method9(var1)) {
                  int var6 = (int)var4.method14().getWidth();
                  int var7 = (int)var4.method14().getHeight();
                  boolean var8 = var4.getId().equals(this.field15) && var4.method4(var2);
                  boolean var9 = var4.method7();
                  boolean var10 = this.field13 && var4 instanceof EmoteHologramLegacy var11 && var11.method31() && (this.field3.method13() || var8 && !var9);
                  if (var10) {
                     this.field14 = var8 && !var9;
                     this.field3
                        .method2(
                           var4.method30().method4(),
                           (float)var2.HHHCHORHIHRCOHIOICICICHCRRICCI() - var4.method14().getX(),
                           var7 - ((float)var2.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - var4.method14().getY()),
                           var6,
                           var7,
                           var8
                        );
                     if (this.field14 && var4 instanceof EmoteHologramLegacy var13) {
                        MarkerModel.Data5 var12 = var2;
                        this.field3.method3(var1, var13, () -> var5.method2(var1, var4, var12));
                     }
                  } else if (!this.field13) {
                     this.field3.method26(var8);
                  }

                  Bridge.method14().method2();
                  var5.method2(var1, var4, var2);
                  Bridge.method14().method3();
                  if (var10) {
                     if (ThreadModuleDump63.method1()) {
                        var1.method35(0.0, var6, var7, 0.0, 21000.0, 1000.0);
                     } else {
                        var1.method35(0.0, var6, var7, 0.0, 1000.0, ThreadModuleDump63.MC_VERSION >= 17 ? 21000.0 : 3000.0);
                     }

                     this.field3.method8(var1, var4.method30().method4());
                     var1.method41();
                     this.field14 = false;
                  }

                  var4.method10(var1);
               }
            }
         }

         if (!this.field5.isEmpty()) {
            ThreadModuleDump63.method3().method1(null, false);
         }

         field1 = false;
      }
   }

   public void method3(AbstractRenderContext var1, OwnedCosmetic var2, Runnable var3) {
      if (this.field13) {
         field2 = true;
         this.field3.method4(var1, var2, var3, this.field14);
         field2 = false;
      }
   }

   public void method4(int var1, int var2, MarkerModel.Data5 var3) {
      if (!method12()) {
         for (Holograms2 var5 : this.field5.values()) {
            if (var5.getId().equals(this.field15)) {
               var5.method5(var1, var2, var3);
            }
         }

         if (this.field3 != null) {
            this.field3.method11(var1, var2, var3);
         }
      }
   }

   public boolean method5(double var1, double var3, MarkerModel.Data5 var5) {
      if (method12()) {
         return false;
      }

      boolean var6 = false;

      for (Holograms2 var8 : this.field5.values()) {
         if (var8.method18() && var8.getId().equals(this.field15) && var8.method4(var5)) {
            double var9 = Math.abs(var3);
            double var11;
            if (var3 > 0.0) {
               var11 = var8.method26() + var9 * 0.05;
            } else {
               var11 = var8.method26() - var9 * 0.05;
            }

            if (var8.method3() + var11 >= 0.05) {
               var8.method48((float)var11);
            }

            var6 = true;
         }
      }

      return var6;
   }

   public RenderContext method6(UUID var1, BridgeExtension2_7 var2) {
      Bridge5Extension_5 var3 = HologramsIterator2.method14();

      for (Holograms2 var5 : this.field5.values()) {
         if (var5 instanceof EmoteHologramLegacy var6) {
            if (var6.getUuid() == null) {
               return null;
            }

            if (var6.getUuid().equals(var1)) {
               switch (var6.method32()) {
                  case IN_WORLD:
                     return RenderContext.method3();
                  case IN_WORLD_PLAYER_MODEL:
                     return RenderContext.method7(var3, var2);
                  case IN_COSMETIC_PLAYER_GUI_MODEL:
                     return RenderContext.method9(var3, var2);
                  case IN_COSMETIC_PLAYER_MODEL:
                     return RenderContext.method8(var3, var2);
                  case IN_COSMETIC_PLAYER_GUI:
                     return RenderContext.method10();
               }
            }

            return RenderContext.method8(var3, var2);
         }
      }

      return null;
   }

   private void method7(EventClientTick var1) {
      if (HologramsIterator2.method14() != null && HologramsIterator2.method14() instanceof ThreadModuleDump54) {
         ((ThreadModuleDump54)HologramsIterator2.method14()).incrementTicksExisted();

         for (Holograms2 var3 : this.field5.values()) {
            var3.tick();
         }
      }
   }

   public void method8(Holograms2<?> var1) {
      if (var1 != null && !this.field5.containsKey(var1.getId())) {
         DriverViewportLegacy.method50().method46(var1.getId());
         var1.method53(this);
         this.field5.put(var1.getId(), var1);
         this.field11.remove(var1.getId());
         this.field12 = System.currentTimeMillis();
      }
   }

   public void processQueue() {
      if (!this.field10.isEmpty()) {
         long var1 = System.currentTimeMillis();
         long var3 = var1 - this.field12;
         if (var3 >= 40L) {
            for (int var5 = 0; var5 < 3; var5++) {
               Holograms2 var6 = this.field10.poll();
               if (var6 != null) {
                  this.field11.remove(var6.getId());
                  this.method8(var6);
               }
            }
         }
      }
   }

   public void method9(Holograms2<?> var1) {
      if (var1 != null && !this.field11.containsKey(var1.getId())) {
         this.field11.put(var1.getId(), var1);
         this.field10.offer(var1);
      }
   }

   public Holograms2<?> method10(String var1) {
      return this.field11.get(var1);
   }

   public void method11(String var1) {
      this.field11.remove(var1);
      this.field10.removeIf(var1x -> var1x.getId().equals(var1));
   }

   public static boolean method12() {
      DriverOverlayRegistryLegacy var0 = DriverViewportLegacy.method50().method64();
      return var0 != null && !var0.method13();
   }

   @Generated
   public com.moonsworth.lunar.client.driver.holograms.Holograms method13() {
      return this.field3;
   }

   @Generated
   public Holograms method14() {
      return this.field4;
   }

   @Generated
   public ConcurrentHashMap<String, Holograms2<?>> method15() {
      return this.field5;
   }

   @Generated
   public HologramsIterator2 method16() {
      return this.field6;
   }

   @Generated
   public void method17(boolean var1) {
      this.field13 = var1;
   }
}
