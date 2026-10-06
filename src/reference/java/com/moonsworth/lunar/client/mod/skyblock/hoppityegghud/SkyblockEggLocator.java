package com.moonsworth.lunar.client.mod.skyblock.hoppityegghud;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.SkyblockCalendar;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AverageInterval;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.mod.skyblock.hoppityegghud.SkyblockHoppityEggHud;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatList;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.Generated;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class SkyblockEggLocator extends AbstractFeature {
   private static final String field8 = "block.sand.step";
   private static final String field9 = "entity.cat.purreow";
   private String field10;
   private String field11;
   private final ArrayList<SkyblockEggLocator.Data> field12 = new ArrayList<>();
   private final FloatList field13 = new FloatArrayList();
   private EventPlaySound field14 = null;
   private long field15 = -1L;

   public SkyblockEggLocator(SkyblockHoppityEggHud skyblockhoppityegghud1, ToggleOption lightingextension4432) {
      super(true);
      this.method3(ModTraits.field16, ChildModBinding.method4(false, skyblockhoppityegghud1));
      this.method3(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventSpawnParticle.class, this::method3);
      this.handle(EventPlaySound.class, this::method4);
      this.handle(EventTick.class, this::method5);
      this.handle(HudRenderLegacyEventAlt.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method7);
      this.handle(EventWorldChange.class, this::method8);
   }

   public String getId() {
      return "SKYBLOCK_EGG_LOCATOR";
   }

   protected void method1(boolean flag1) {
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.method14();
      }
   }

   private void method3(EventSpawnParticle highlightimpl151) {
      if (SkyblockCalendar.isSpring()) {
         if (this.method9(highlightimpl151)) {
            Vector3d vector3d2 = new Vector3d(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ());
            this.field12.add(new SkyblockEggLocator.Data(vector3d2, this.field14.getPitch()));
            this.method13();
         }
      }
   }

   private void method4(EventPlaySound highlightimpl131) {
      if (SkyblockCalendar.isSpring()) {
         String text2 = highlightimpl131.getPath();
         if ("block.sand.step".equals(text2)) {
            float value3 = highlightimpl131.getPitch();
            if (value3 == 1.0F && highlightimpl131.getVolume() == 0.15F) {
               return;
            }

            if (this.field14 != null && this.field14.getPitch() > highlightimpl131.getPitch()) {
               this.clearCaches();
            }

            this.field14 = highlightimpl131;
            this.field13.add(highlightimpl131.getPitch());
            this.field15 = Ref.method3().bridge$getSystemTime();
         } else if ("entity.cat.purreow".equals(text2)) {
            this.clearCaches();
            this.method14();
         }
      }
   }

   private void method5(EventTick highlightimpl21) {
      if (SkyblockCalendar.isSpring()) {
         if (Ref.method3().bridge$getSystemTime() - this.field15 > 1000L) {
            this.clearCaches();
         }
      }
   }

   private void method6(HudRenderLegacyEventAlt highlightimpl41) {
      if (this.field10 != null) {
         if (SkyblockCalendar.isSpring()) {
            SkyblockHoppityEggHud skyblockhoppityegghud2 = (SkyblockHoppityEggHud)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            if (skyblockhoppityegghud2.method15() != null) {
               Bridge5Extension_5 bridge5extension_53 = Ref.method7();
               if (bridge5extension_53 != null) {
                  ChocolateEggLocations fishing24 = skyblockhoppityegghud2.method15();
                  String text5 = IslandUtils.getIsland().name();
                  HashMap map6 = (HashMap)fishing24.method1().get(text5);
                  if (map6 != null) {
                     com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations.Data data7 = (com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations.Data)map6.get(
                        this.field10
                     );
                     Vector3i vector3i8 = data7.method1();
                     AbstractRenderContext bridgeextension_99 = highlightimpl41.method3();
                     EntityRenderDispatcherBridge bridge2_4310 = Ref.method13();
                     bridgeextension_99.push();
                     bridgeextension_99.translate(-bridge2_4310.bridge$renderPosX(), -bridge2_4310.bridge$renderPosY(), -bridge2_4310.bridge$renderPosZ());
                     if (skyblockhoppityegghud2.method7(text5, this.field10)) {
                        WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_99, vector3i8, skyblockhoppityegghud2.method27().method1(0.0F), true);
                     } else {
                        WorldRenderUtils.drawBoxAtCoordinate(bridgeextension_99, vector3i8, skyblockhoppityegghud2.method25().method1(0.0F), true);
                     }

                     double value11 = bridge2_4310.bridge$renderPosX();
                     double value13 = bridge2_4310.bridge$renderPosY();
                     double value15 = bridge2_4310.bridge$renderPosZ();
                     double value17 = vector3i8.x() + 0.5;
                     double value19 = vector3i8.y() + 1.5;
                     double value21 = vector3i8.z() + 0.5;
                     double value23 = bridge5extension_53.method15(value17, value19, value21);
                     if (value23 > 100.0) {
                        double value25 = Math.sqrt(value23);
                        value17 = (value17 - value11) * (10.0 / value25) + value11;
                        value19 = (value19 - value13) * (10.0 / value25) + value13;
                        value21 = (value21 - value15) * (10.0 / value25) + value15;
                     }

                     if (skyblockhoppityegghud2.method7(text5, this.field10)) {
                        WorldRenderUtils.drawString(
                           bridgeextension_99, "Egglocator Estimate (Duplicate)", value17, value19, value21, ColorUtils.method31(skyblockhoppityegghud2.method27().method1(0.0F)), true
                        );
                     } else {
                        WorldRenderUtils.drawString(bridgeextension_99, "Egglocator Estimate", value17, value19, value21, ColorUtils.method31(skyblockhoppityegghud2.method25().method1(0.0F)), true);
                     }

                     bridgeextension_99.pop();
                  }
               }
            }
         }
      }
   }

   private void method7(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (IslandUtils.isOnIsland()) {
         if (SkyblockCalendar.isSpring()) {
            if (this.field11 != null) {
               String text2 = ChatFormatting.getTextWithoutFormattingCodes(data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC());
               text2 = text2.replace("’", "'");
               if (text2.startsWith("HOPPITY'S HUNT You found a Chocolate ") && text2.endsWith(this.field11)) {
                  this.method14();
               }
            }
         }
      }
   }

   private void method8(EventWorldChange data31) {
      this.clearCaches();
      this.method14();
   }

   private boolean method9(EventSpawnParticle highlightimpl151) {
      if (highlightimpl151.method2() != ParticleType.HAPPY_VILLAGER) {
         return false;
      }

      if (highlightimpl151.method6() != 0.0F || highlightimpl151.method7() != 0.0F || highlightimpl151.method8() != 0.0F) {
         return false;
      }

      if (this.field14 == null) {
         return false;
      }

      double value2 = this.field14.method5() - highlightimpl151.getPosX();
      double value4 = this.field14.method6() - highlightimpl151.getPosY();
      double value6 = this.field14.method7() - highlightimpl151.getPosZ();
      double value8 = value2 * value2 + value4 * value4 + value6 * value6;
      return value8 < 0.5;
   }

   private void method13() {
      if (this.field12.size() >= 5) {
         Vector3d vector3d1 = this.field12.get(0).method1();
         Vector3d vector3d2 = new Vector3d(this.field12.get(this.field12.size() - 1).method1());
         double value3 = Math.E / AverageInterval.averageInterval(this.field13) - vector3d1.distance(vector3d2);
         Vector3d vector3d5 = new Vector3d(vector3d2).sub(this.field12.get(this.field12.size() - 4).method1()).normalize();
         Vector3d vector3d6 = vector3d2.add(vector3d5.mul(value3));
         Vector3i vector3i7 = new Vector3i((int)Math.floor(vector3d6.x()), (int)Math.floor(vector3d6.y()), (int)Math.floor(vector3d6.z()));
         SkyblockHoppityEggHud skyblockhoppityegghud8 = (SkyblockHoppityEggHud)((ChildModBinding)this.method7(ModTraits.field16)).method1();
         if (skyblockhoppityegghud8.method15() != null) {
            ChocolateEggLocations fishing29 = skyblockhoppityegghud8.method15();
            String text10 = IslandUtils.getIsland().name();
            HashMap map11 = (HashMap)fishing29.method1().get(text10);
            if (map11 != null) {
               String text12 = null;
               String text13 = null;
               double value14 = 0.0;

               for (String text17 : map11.keySet()) {
                  com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations.Data data18 = (com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.ChocolateEggLocations.Data)map11.get(
                     text17
                  );
                  Vector3i vector3i19 = data18.method1();
                  double value20 = vector3i7.distanceSquared(vector3i19);
                  if (text12 == null || value20 <= value14) {
                     text12 = text17;
                     text13 = data18.getMessage();
                     value14 = value20;
                  }
               }

               this.field10 = text12;
               this.field11 = text13;
            }
         }
      }
   }

   private void clearCaches() {
      this.field12.clear();
      this.field13.clear();
      this.field14 = null;
   }

   private void method14() {
      this.field10 = null;
      this.field11 = null;
   }

   @Generated
   public String method15() {
      return this.field10;
   }

   private static class Data {
      private final Vector3d field1;
      private final float field2;

      @Generated
      public Vector3d method1() {
         return this.field1;
      }

      @Generated
      public float getPitch() {
         return this.field2;
      }

      @Generated
      public Data(Vector3d vector3d1, float value2) {
         this.field1 = vector3d1;
         this.field2 = value2;
      }
   }
}
