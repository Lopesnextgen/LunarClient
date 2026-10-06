package com.moonsworth.lunar.client.mod.skyblock.fishinghotspotlocator;

import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AverageInterval;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.EquippedItemListener;
import com.moonsworth.lunar.client.event.player.EventItemUse;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.floats.FloatList;
import java.util.ArrayList;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.joml.Vector3d;
import org.joml.Vector3i;

public class SkyblockFishingHotspotLocator extends AbstractFeature {
   private final EquippedItemListener field8 = (EquippedItemListener)this.method63(EquippedItemListener.class);
   private static final String field9 = "block.note_block.pling";
   private static final String field10 = "entity.guardian.death";
   private static final Component field11 = Component.text("HOTSPOT", Style.style(NamedTextColor.LIGHT_PURPLE, new TextDecoration[]{TextDecoration.BOLD}));
   private Vector3i field12;
   private final ArrayList<SkyblockFishingHotspotLocator.Data> field13 = new ArrayList<>();
   private final FloatList field14 = new FloatArrayList();
   private EventPlaySound field15 = null;
   private long field16 = -1L;
   private long field17 = -1L;

   public SkyblockFishingHotspotLocator(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.FISHING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.method51(this::onDisable);
      this.handle(EventItemUse.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate.class, this::method3);
      this.handle(EventSpawnParticle.class, this::method4);
      this.handle(EventPlaySound.class, this::method5);
      this.handle(EventTick.class, this::method6);
      this.handle(HudRenderLegacyEventAlt.class, this::method7);
      this.handle(EventWorldChange.class, this::method8);
   }

   public String getId() {
      return "SKYBLOCK_FISHING_HOTSPOT_LOCATOR";
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.method14();
      }
   }

   private void method2(EventItemUse highlightimpl181) {
      if (IslandUtils.isOnIsland()) {
         ItemStackBridge bridgeextension_42 = highlightimpl181.method2();
         if (SkyblockItemUtil.method2(bridgeextension_42).equals("HOTSPOT_RADAR")) {
            this.field17 = Ref.method3().bridge$getSystemTime();
         }
      }
   }

   private void method3(com.moonsworth.lunar.client.event.mixin.fishing.EventBlockUpdate.BlockUpdate data1) {
      if (IslandUtils.isOnIsland()) {
         if (this.field8.method9().equals("HOTSPOT_RADAR")) {
            this.field17 = Ref.method3().bridge$getSystemTime();
         }
      }
   }

   private void method4(EventSpawnParticle highlightimpl151) {
      if (IslandUtils.isOnIsland()) {
         if (this.method9(highlightimpl151)) {
            Vector3d vector3d2 = new Vector3d(highlightimpl151.getPosX(), highlightimpl151.getPosY(), highlightimpl151.getPosZ());
            this.field13.add(new SkyblockFishingHotspotLocator.Data(vector3d2, this.field15.getPitch()));
            this.method13();
         }
      }
   }

   private void method5(EventPlaySound highlightimpl131) {
      if (IslandUtils.isOnIsland()) {
         String text2 = highlightimpl131.getPath();
         if ("block.note_block.pling".equals(text2)) {
            if (this.field15 != null && this.field15.getPitch() > highlightimpl131.getPitch()) {
               this.clearCaches();
            }

            this.field15 = highlightimpl131;
            this.field14.add(highlightimpl131.getPitch());
            this.field16 = Ref.method3().bridge$getSystemTime();
         } else if ("entity.guardian.death".equals(text2)) {
            float value3 = highlightimpl131.getPitch();
            if (value3 >= 0.5F && value3 <= 0.6F && highlightimpl131.getVolume() == 1.0F) {
               this.clearCaches();
               this.method14();
            }
         }
      }
   }

   private void method6(EventTick highlightimpl21) {
      if (IslandUtils.isOnIsland()) {
         if (Ref.method3().bridge$getSystemTime() - this.field16 > 1000L) {
            this.clearCaches();
         }

         if (this.field12 != null) {
            Bridge5Extension_5 bridge5extension_52 = Ref.method7();
            if (bridge5extension_52 != null) {
               if (bridge5extension_52.method15(this.field12.x(), this.field12.y(), this.field12.z()) < 100.0) {
                  this.method14();
               }
            }
         }
      }
   }

   private void method7(HudRenderLegacyEventAlt highlightimpl41) {
      if (this.field12 != null) {
         if (IslandUtils.isOnIsland()) {
            Bridge5Extension_5 bridge5extension_52 = Ref.method7();
            if (bridge5extension_52 != null) {
               AbstractRenderContext bridgeextension_93 = highlightimpl41.method3();
               EntityRenderDispatcherBridge bridge2_434 = Ref.method13();
               bridgeextension_93.push();
               bridgeextension_93.translate(-bridge2_434.bridge$renderPosX(), -bridge2_434.bridge$renderPosY(), -bridge2_434.bridge$renderPosZ());
               double value5 = this.field12.x() + 0.5;
               double value7 = this.field12.y() + 1.5;
               double value9 = this.field12.z() + 0.5;
               float value11 = (float)bridge5extension_52.method18(value5, value7, value9);
               float value12 = value11 / 10.0F;
               WorldRenderUtils.drawComponent(bridgeextension_93, field11, value5, value7, value9, true, value12, true);
               bridgeextension_93.pop();
            }
         }
      }
   }

   private void onDisable() {
      this.clearCaches();
      this.method14();
   }

   private void method8(EventWorldChange data31) {
      this.clearCaches();
      this.method14();
   }

   private boolean method9(EventSpawnParticle highlightimpl151) {
      if (Ref.method3().bridge$getSystemTime() - 5000L > this.field17) {
         return false;
      }

      if (highlightimpl151.method2() != ParticleType.FLAME) {
         return false;
      }

      if (highlightimpl151.method6() != 0.0F || highlightimpl151.method7() != 0.0F || highlightimpl151.method8() != 0.0F) {
         return false;
      }

      if (this.field15 == null) {
         return false;
      }

      double value2 = this.field15.method5() - highlightimpl151.getPosX();
      double value4 = this.field15.method6() - highlightimpl151.getPosY();
      double value6 = this.field15.method7() - highlightimpl151.getPosZ();
      double value8 = value2 * value2 + value4 * value4 + value6 * value6;
      return value8 < 0.5;
   }

   private void method13() {
      if (this.field13.size() >= 5) {
         Vector3d vector3d1 = this.field13.get(0).method1();
         Vector3d vector3d2 = new Vector3d(this.field13.get(this.field13.size() - 1).method1());
         double value3 = Math.E / AverageInterval.averageInterval(this.field14) - vector3d1.distance(vector3d2);
         Vector3d vector3d5 = new Vector3d(vector3d2).sub(this.field13.get(this.field13.size() - 4).method1()).normalize();
         Vector3d vector3d6 = vector3d2.add(vector3d5.mul(value3));
         this.field12 = new Vector3i((int)Math.floor(vector3d6.x()), (int)Math.floor(vector3d6.y()), (int)Math.floor(vector3d6.z()));
      }
   }

   private void clearCaches() {
      this.field13.clear();
      this.field14.clear();
      this.field15 = null;
   }

   private void method14() {
      this.field12 = null;
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
