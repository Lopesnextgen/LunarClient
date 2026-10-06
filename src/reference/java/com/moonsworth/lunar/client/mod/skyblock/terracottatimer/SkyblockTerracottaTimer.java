package com.moonsworth.lunar.client.mod.skyblock.terracottatimer;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.DungeonFloor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEventAlt;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.joml.Vector3i;

public class SkyblockTerracottaTimer extends AbstractFeature {
   private final DungeonFloorListener dungeonFloorListener = (DungeonFloorListener)this.method63(DungeonFloorListener.class);
   private final DungeonScoreListener dungeonScoreListener = (DungeonScoreListener)this.method63(DungeonScoreListener.class);
   private final EnumOption<NamedColorOption> skyblockTerracottaTimerColor = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockTerracottaTimerColor", NamedColorOption.YELLOW
      )
      .method31();
   private final IntegerOption skyblockTerracottaTimerDecimals = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "skyblockTerracottaTimerDecimals"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(0, 3))
      .method31();
   private final ObjectOpenHashSet<SkyblockTerracottaTimer.Data> activeTerracottas = new ObjectOpenHashSet();

   public SkyblockTerracottaTimer(Skyblock skyblock1) {
      super(false);
      this.method8(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method8(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method8(
         ModTraits.field19,
         DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && this.dungeonFloorListener.method6().getNumber() == 6 && this.dungeonScoreListener.method10())
      );
      this.handle(EventBlockChange.class, this::method1);
      this.handle(HudRenderLegacyEventAlt.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::onChatMessage);
      this.handle(EventWorldChange.class, this::onWorldChange);
      this.method51(this::onDisable);
   }

   private void onDisable() {
      this.activeTerracottas.clear();
   }

   private void method1(EventBlockChange highlightimpl91) {
      boolean flag2 = this.dungeonFloorListener.method6() == DungeonFloor.M6;
      Vector3i vector3i3 = highlightimpl91.method1().bridge$toJoml();
      Bridge3_23 bridge3_234 = highlightimpl91.method2().bridge$getBlock();
      Bridge3_23 bridge3_235 = highlightimpl91.method3().bridge$getBlock();
      BlocksBridge bridge_566 = Bridge.method34();
      if (bridge3_234.bridge$isAir() && bridge3_235.bridge$isFlowerPot()) {
         this.activeTerracottas.add(new SkyblockTerracottaTimer.Data(vector3i3, flag2));
      } else if (bridge3_234 == bridge_566.method9() && bridge3_235.bridge$isAir()) {
         this.activeTerracottas.remove(new SkyblockTerracottaTimer.Data(vector3i3.add(0, -1, 0), flag2));
      }
   }

   private void method2(HudRenderLegacyEventAlt highlightimpl41) {
      if (!this.activeTerracottas.isEmpty()) {
         this.activeTerracottas.removeIf(arg0 -> arg0.field2.get() <= 0L);
         EntityRenderDispatcherBridge bridge2_432 = Ref.method13();
         AbstractRenderContext bridgeextension_93 = highlightimpl41.method3();
         bridgeextension_93.push();
         bridgeextension_93.translate(-bridge2_432.bridge$renderPosX(), -bridge2_432.bridge$renderPosY(), -bridge2_432.bridge$renderPosZ());
         ObjectIterator objectiterator4 = this.activeTerracottas.iterator();

         while (objectiterator4.hasNext()) {
            SkyblockTerracottaTimer.Data data5 = (SkyblockTerracottaTimer.Data)objectiterator4.next();
            Vector3i vector3i6 = data5.method1();
            double value7 = data5.method2().get() / 1000.0;
            String text9 = String.format("%." + this.skyblockTerracottaTimerDecimals.get() + "f", value7) + "s";
            TextComponent text10 = Component.text(text9, TextComponentFactory.styleOf(this.skyblockTerracottaTimerColor));
            WorldRenderUtils.drawComponent(bridgeextension_93, text10, vector3i6.x() + 0.5, vector3i6.y() + 1.9, vector3i6.z() + 0.5, true, 1.0F, true);
         }

         bridgeextension_93.pop();
      }
   }

   private void onChatMessage(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (!this.activeTerracottas.isEmpty()) {
         if (data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH().equals("[BOSS] Sadan: ENOUGH!")) {
            this.activeTerracottas.clear();
         }
      }
   }

   private void onWorldChange(EventWorldChange data31) {
      this.activeTerracottas.clear();
   }

   public String getId() {
      return "SKYBLOCK_TERRACOTTA_TIMER";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.skyblockTerracottaTimerColor, this.skyblockTerracottaTimerDecimals})
      );
   }

   private static class Data {
      private final Vector3i position;
      private final transient HudTimer timer;

      public Data(Vector3i vector3i1, boolean flag2) {
         this.position = vector3i1;
         this.timer = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1()
            .method2()
            .method4()
            .method5(flag2 ? 12000L : 15000L)
            .method7()
            .method2();
      }

      @Generated
      public Vector3i method1() {
         return this.position;
      }

      @Generated
      public HudTimer method2() {
         return this.timer;
      }

      @Generated
      @Override
      public boolean equals(Object obj1) {
         if (obj1 == this) {
            return true;
         } else if (!(obj1 instanceof SkyblockTerracottaTimer.Data data2)) {
            return false;
         } else {
            if (!data2.canEqual(this)) {
               return false;
            }

            Vector3i vector3i3 = this.method1();
            Vector3i vector3i4 = data2.method1();
            return vector3i3 == null ? vector3i4 == null : vector3i3.equals(vector3i4);
         }
      }

      @Generated
      protected boolean canEqual(Object obj1) {
         return obj1 instanceof SkyblockTerracottaTimer.Data;
      }

      @Generated
      @Override
      public int hashCode() {
         byte number1 = 59;
         byte number2 = 1;
         Vector3i vector3i3 = this.method1();
         return number2 * 59 + (vector3i3 == null ? 43 : vector3i3.hashCode());
      }
   }
}
