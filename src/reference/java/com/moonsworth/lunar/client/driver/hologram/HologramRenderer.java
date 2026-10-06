package com.moonsworth.lunar.client.driver.hologram;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.cosmetics.emote.RenderContext;
import com.moonsworth.lunar.client.cosmetics.OwnedCosmetic;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistry;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator;
import com.moonsworth.lunar.client.driver.holograms.HologramsIterator2;
import com.moonsworth.lunar.client.driver.hologram.MarkerModelRenderer;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.hologram.EmoteHologram;
import com.moonsworth.lunar.client.cosmetics.DummyPlayer;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;
import com.moonsworth.lunar.client.driver.core.holograms.Holograms2;

public class HologramRenderer {
   public static boolean field1 = false;
   public static boolean field2 = false;
   private com.moonsworth.lunar.client.driver.hologram.CosmeticHighlightRenderer field3;
   private final HologramModelBridge field4;
   private final ConcurrentHashMap<String, Holograms2<?>> field5 = new ConcurrentHashMap<>();
   private final HologramsIterator2 field6 = new HologramsIterator2();
   private final Map<HologramSubject, MarkerModelRenderer<?>> field7 = new HashMap<>(
      Map.of(HologramSubject.PLAYER, this.field6, HologramSubject.COSMETIC, new HologramsIterator())
   );
   private static final int field8 = 3;
   private static final long field9 = 40L;
   private final Queue<Holograms2<?>> field10 = new LinkedList<>();
   private final HashMap<String, Holograms2<?>> field11 = new HashMap<>();
   private long field12 = 0L;
   private boolean field13 = true;
   private boolean field14 = false;
   private String field15 = null;

   public void method1(String text1) {
      if (!Objects.equals(this.field15, text1)) {
         this.field15 = text1;
      }
   }

   public HologramRenderer() {
      this.field4 = new HologramModelBridge(this);
      LunarEventBus.method29().method2(EventTick.class, this::method7);
   }

   public void method2(AbstractRenderContext bridgeextension_91, MarkerModel.Data5 data52) {
      if (Ref.method3().bridge$areResourcesLoaded()) {
         if (this.field3 == null) {
            this.field3 = new com.moonsworth.lunar.client.driver.hologram.CosmeticHighlightRenderer();
         }

         if (method12()) {
            data52 = new MarkerModel.Data5(0.0, 0.0);
         }

         this.processQueue();
         field1 = true;

         for (Holograms2 holograms24 : this.field5.values()) {
            if (holograms24.method14().getWidth() != 0.0F && holograms24.method14().getHeight() != 0.0F) {
               MarkerModelRenderer holograms_25 = this.field7.get(holograms24.method12());
               holograms24.method6(data52);
               holograms_25.method1(holograms24);
               if (holograms24.method9(bridgeextension_91)) {
                  int number6 = (int)holograms24.method14().getWidth();
                  int number7 = (int)holograms24.method14().getHeight();
                  boolean flag8 = holograms24.getId().equals(this.field15) && holograms24.method4(data52);
                  boolean flag9 = holograms24.method7();
                  boolean flag10 = this.field13 && holograms24 instanceof EmoteHologram holograms2iterator11 && holograms2iterator11.method31() && (this.field3.method13() || flag8 && !flag9);
                  if (flag10) {
                     this.field14 = flag8 && !flag9;
                     this.field3
                        .method2(
                           holograms24.method30().method4(),
                           (float)data52.HHHCHORHIHRCOHIOICICICHCRRICCI() - holograms24.method14().getX(),
                           number7 - ((float)data52.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - holograms24.method14().getY()),
                           number6,
                           number7,
                           flag8
                        );
                     if (this.field14 && holograms24 instanceof EmoteHologram holograms2iterator13) {
                        MarkerModel.Data5 data512 = data52;
                        this.field3.method3(bridgeextension_91, holograms2iterator13, () -> holograms_25.method2(bridgeextension_91, holograms24, data512));
                     }
                  } else if (!this.field13) {
                     this.field3.method26(flag8);
                  }

                  Bridge.method14().method2();
                  holograms_25.method2(bridgeextension_91, holograms24, data52);
                  Bridge.method14().method3();
                  if (flag10) {
                     if (Ref.method1()) {
                        bridgeextension_91.method35(0.0, number6, number7, 0.0, 21000.0, 1000.0);
                     } else {
                        bridgeextension_91.method35(0.0, number6, number7, 0.0, 1000.0, Ref.MC_VERSION >= 17 ? 21000.0 : 3000.0);
                     }

                     this.field3.method8(bridgeextension_91, holograms24.method30().method4());
                     bridgeextension_91.method41();
                     this.field14 = false;
                  }

                  holograms24.method10(bridgeextension_91);
               }
            }
         }

         if (!this.field5.isEmpty()) {
            Ref.method3().method1(null, false);
         }

         field1 = false;
      }
   }

   public void method3(AbstractRenderContext bridgeextension_91, OwnedCosmetic gui2handler2, Runnable runnable3) {
      if (this.field13) {
         field2 = true;
         this.field3.method4(bridgeextension_91, gui2handler2, runnable3, this.field14);
         field2 = false;
      }
   }

   public void method4(int number1, int number2, MarkerModel.Data5 data53) {
      if (!method12()) {
         for (Holograms2 holograms25 : this.field5.values()) {
            if (holograms25.getId().equals(this.field15)) {
               holograms25.method5(number1, number2, data53);
            }
         }

         if (this.field3 != null) {
            this.field3.method11(number1, number2, data53);
         }
      }
   }

   public boolean method5(double value1, double value3, MarkerModel.Data5 data55) {
      if (method12()) {
         return false;
      }

      boolean flag6 = false;

      for (Holograms2 holograms28 : this.field5.values()) {
         if (holograms28.method18() && holograms28.getId().equals(this.field15) && holograms28.method4(data55)) {
            double value9 = Math.abs(value3);
            double value11;
            if (value3 > 0.0) {
               value11 = holograms28.method26() + value9 * 0.05;
            } else {
               value11 = holograms28.method26() - value9 * 0.05;
            }

            if (holograms28.method3() + value11 >= 0.05) {
               holograms28.method48((float)value11);
            }

            flag6 = true;
         }
      }

      return flag6;
   }

   public RenderContext method6(UUID uuid1, ModelPlayerBridge bridgeextension2_72) {
      Bridge5Extension_5 bridge5extension_53 = HologramsIterator2.method14();

      for (Holograms2 holograms25 : this.field5.values()) {
         if (holograms25 instanceof EmoteHologram holograms2iterator6) {
            if (holograms2iterator6.getUuid() == null) {
               return null;
            }

            if (holograms2iterator6.getUuid().equals(uuid1)) {
               switch (holograms2iterator6.method32()) {
                  case IN_WORLD:
                     return RenderContext.method3();
                  case IN_WORLD_PLAYER_MODEL:
                     return RenderContext.method7(bridge5extension_53, bridgeextension2_72);
                  case IN_COSMETIC_PLAYER_GUI_MODEL:
                     return RenderContext.method9(bridge5extension_53, bridgeextension2_72);
                  case IN_COSMETIC_PLAYER_MODEL:
                     return RenderContext.method8(bridge5extension_53, bridgeextension2_72);
                  case IN_COSMETIC_PLAYER_GUI:
                     return RenderContext.method10();
               }
            }

            return RenderContext.method8(bridge5extension_53, bridgeextension2_72);
         }
      }

      return null;
   }

   private void method7(EventTick highlightimpl21) {
      if (HologramsIterator2.method14() != null && HologramsIterator2.method14() instanceof DummyPlayer) {
         ((DummyPlayer)HologramsIterator2.method14()).incrementTicksExisted();

         for (Holograms2 holograms23 : this.field5.values()) {
            holograms23.tick();
         }
      }
   }

   public void method8(Holograms2<?> holograms21) {
      if (holograms21 != null && !this.field5.containsKey(holograms21.getId())) {
         DriverViewportLegacy.method50().method46(holograms21.getId());
         holograms21.method53(this);
         this.field5.put(holograms21.getId(), holograms21);
         this.field11.remove(holograms21.getId());
         this.field12 = System.currentTimeMillis();
      }
   }

   public void processQueue() {
      if (!this.field10.isEmpty()) {
         long number1 = System.currentTimeMillis();
         long number3 = number1 - this.field12;
         if (number3 >= 40L) {
            for (int index5 = 0; index5 < 3; index5++) {
               Holograms2 holograms26 = this.field10.poll();
               if (holograms26 != null) {
                  this.field11.remove(holograms26.getId());
                  this.method8(holograms26);
               }
            }
         }
      }
   }

   public void method9(Holograms2<?> holograms21) {
      if (holograms21 != null && !this.field11.containsKey(holograms21.getId())) {
         this.field11.put(holograms21.getId(), holograms21);
         this.field10.offer(holograms21);
      }
   }

   public Holograms2<?> method10(String text1) {
      return this.field11.get(text1);
   }

   public void method11(String text1) {
      this.field11.remove(text1);
      this.field10.removeIf(arg1x -> arg1x.getId().equals(text1));
   }

   public static boolean method12() {
      DriverOverlayRegistry markers2handler0 = DriverViewportLegacy.method50().method64();
      return markers2handler0 != null && !markers2handler0.method13();
   }

   @Generated
   public com.moonsworth.lunar.client.driver.hologram.CosmeticHighlightRenderer method13() {
      return this.field3;
   }

   @Generated
   public HologramModelBridge method14() {
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
   public void method17(boolean flag1) {
      this.field13 = flag1;
   }
}
