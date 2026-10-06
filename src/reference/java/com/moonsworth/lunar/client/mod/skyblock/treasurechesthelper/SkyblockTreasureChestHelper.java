package com.moonsworth.lunar.client.mod.skyblock.treasurechesthelper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.tileentity.TileEntityChestBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPlaySound;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.HashSet;
import java.util.Objects;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockTreasureChestHelper extends AbstractFeature {
   private final HashSet<SkyblockTreasureChestHelper.Data> field8 = new HashSet<>();
   private SkyblockTreasureChestHelper.Data field9;

   public SkyblockTreasureChestHelper(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS));
      this.handle(HudRenderLegacyEvent.class, this::method1);
      this.handle(EventBlockChange.class, this::method2);
      this.handle(EventPlaySound.class, this::method3);
      this.handle(EventSpawnParticle.class, this::method4);
   }

   private void method1(HudRenderLegacyEvent highlightimpl21) {
      AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
      EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
      bridgeextension_92.push();
      bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());

      for (SkyblockTreasureChestHelper.Data data5 : this.field8) {
         if (data5.method1(highlightimpl21.method3(), data5.equals(this.field9))) {
            this.field9 = data5;
         }
      }

      bridgeextension_92.pop();
   }

   private void method2(EventBlockChange highlightimpl91) {
      SkyblockTreasureChestHelper.Data data2 = new SkyblockTreasureChestHelper.Data(highlightimpl91.method1().bridge$toJoml());
      if (highlightimpl91.method3().bridge$getBlock() == Bridge.method34().method23()) {
         if (Ref.method7().method2(highlightimpl91.method1()) <= 150.0) {
            this.field8.add(data2);
         }
      } else if (this.field8.contains(data2)) {
         this.field8.remove(data2);
         if (data2.equals(this.field9)) {
            this.field9 = null;
         }
      }
   }

   private void method3(EventPlaySound highlightimpl131) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (this.field9 != null && bridge5extension_52 != null) {
         Vector3d vector3d3 = highlightimpl131.method1();
         if (vector3d3.distanceSquared(bridge5extension_52.bridge$getPosition()) <= 10000.0) {
            if ((highlightimpl131.method2("random.orb") || highlightimpl131.method2("entity.experience_orb.pickup")) && highlightimpl131.getPitch() == 1.0F && highlightimpl131.getVolume() == 1.0F) {
               this.field9.field1++;
            } else if (highlightimpl131.method2("mob.villager.no")) {
               this.field9.field1 = 0;
            }
         }
      }
   }

   private void method4(EventSpawnParticle highlightimpl151) {
      if (highlightimpl151.method2() == ParticleType.CRIT) {
         Vector3i vector3i2 = null;
         if (MathUtils.method18(highlightimpl151.getPosX() % 1.0, 0.1, 0.001)) {
            vector3i2 = new Vector3i((int)highlightimpl151.getPosX() - 1, (int)highlightimpl151.getPosY(), (int)highlightimpl151.getPosZ());
         }

         if (MathUtils.method18(highlightimpl151.getPosX() % 1.0, 0.9, 0.001)) {
            vector3i2 = new Vector3i((int)highlightimpl151.getPosX() + 1, (int)highlightimpl151.getPosY(), (int)highlightimpl151.getPosZ());
         }

         if (MathUtils.method18(highlightimpl151.getPosZ() % 1.0, 0.1, 0.001)) {
            vector3i2 = new Vector3i((int)highlightimpl151.getPosX(), (int)highlightimpl151.getPosY(), (int)highlightimpl151.getPosZ() - 1);
         }

         if (MathUtils.method18(highlightimpl151.getPosZ() % 1.0, 0.9, 0.001)) {
            vector3i2 = new Vector3i((int)highlightimpl151.getPosX(), (int)highlightimpl151.getPosY(), (int)highlightimpl151.getPosZ() + 1);
         }

         if (vector3i2 != null) {
            SkyblockTreasureChestHelper.Data data3 = new SkyblockTreasureChestHelper.Data(vector3i2);
            boolean flag4 = false;

            for (SkyblockTreasureChestHelper.Data data6 : this.field8) {
               if (data6.equals(data3)) {
                  data6.field3 = highlightimpl151.method1();
                  flag4 = true;
               }
            }

            if (!flag4) {
               this.field8.add(data3);
               data3.field3 = highlightimpl151.method1();
            }
         }
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_TREASURE_CHEST_HELPER";
   }

   private static class Data {
      public int field1 = 0;
      public Vector3ic field2;
      public Vector3dc field3;
      private static final double field4 = 0.005;

      public Data(Vector3ic vector3ic1) {
         this.field2 = vector3ic1;
      }

      @Override
      public int hashCode() {
         return this.field2.hashCode();
      }

      @Override
      public boolean equals(Object obj1) {
         if (obj1 != null && this.getClass() == obj1.getClass()) {
            SkyblockTreasureChestHelper.Data data2 = (SkyblockTreasureChestHelper.Data)obj1;
            return Objects.equals(this.field2, data2.field2);
         } else {
            return false;
         }
      }

      public boolean method1(AbstractRenderContext bridgeextension_91, boolean flag2) {
         BlockStateBridge bridge2_173 = Ref.method8().HRICOROOOCCOCOROCRHHCRRIRCOICO(this.field2);
         if (bridge2_173.bridge$getBlock() != Bridge.method34().method23()) {
            return false;
         } else if (!(Ref.method8().bridge$getBlockEntity(Bridge.method8().method5(this.field2)) instanceof TileEntityChestBridge hitcolor25)) {
            return false;
         } else {
            if (hitcolor25.bridge$isVisuallyOpen()) {
               return false;
            }

            boolean flag6 = this.method2();
            if (this.field3 != null) {
               int number7;
               if (flag6) {
                  number7 = -16711936;
               } else {
                  number7 = -65536;
               }

               WorldRenderUtils.drawFilledBox(bridgeextension_91, AxisAlignedBBBridge.method3(this.field3.x() - 0.1, this.field3.y() - 0.1, this.field3.z() - 0.1, 0.2, 0.2, 0.2), number7, false);
            }

            if (flag2 || flag6) {
               WorldRenderUtils.drawString(bridgeextension_91, "Progress: " + this.field1, this.field2.x() + 0.5, this.field2.y() + 1.2, this.field2.z() + 0.5, -1, true);
            }

            return flag6;
         }
      }

      private boolean method2() {
         if (this.field3 == null) {
            return false;
         }

         Bridge5Extension_5 bridge5extension_51 = Ref.method7();
         Vector3d vector3d2 = bridge5extension_51.method19().method6().normalize(new Vector3d()).mul(0.005);
         Vector3d vector3d3 = Ref.method7().bridge$getLastReportedPos().add(0.0, IslandUtils.getEyeHeight(), 0.0);

         for (double value4 = 0.0; value4 < 5.0; value4 += 0.005) {
            vector3d3.add(vector3d2);
            double value6 = vector3d3.x();
            double value8 = vector3d3.y();
            double value10 = vector3d3.z();
            if (MathUtils.method18(value6, this.field3.x(), 0.1)
               && MathUtils.method18(value8, this.field3.y(), 0.1)
               && MathUtils.method18(value10, this.field3.z(), 0.1)) {
               return true;
            }
         }

         return false;
      }
   }
}
