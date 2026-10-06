package com.moonsworth.lunar.client.mod.skyblock.floorfour;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.DrawBufferBridge;
import com.moonsworth.lunar.bridge.EntityRenderDispatcherBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension2;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityArmorStandBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.BlocksBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WorldRenderUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonFloorListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.DungeonScoreListener;
import com.moonsworth.lunar.client.event.entity.EventEntityRemove;
import com.moonsworth.lunar.client.event.entity.EventEntitySpawn;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudRenderLegacyEvent;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockFloorFour extends AbstractFeature {
   private final DungeonFloorListener field8 = (DungeonFloorListener)this.method11(DungeonFloorListener.class);
   private final DungeonScoreListener field9 = (DungeonScoreListener)this.method11(DungeonScoreListener.class);
   private static final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "skyblock/hud/spirit_bear.png");
   private static final Vec3iBridge field11 = Bridge.method8().method4(7, 77, 34);
   private static final String field12 = "skins/3e6b5a597108ef3370efb7122a74673167e8dc54";
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("spiritBearTimerHud").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field14 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "spiritBearTimerColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5635926))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("highlightSpiritBow").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field16 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "spiritBowHighlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(570490879))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("highlightSpiritBear").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ColorOption field18 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "spiritBearHighlightColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-6283024))
      .method31();
   private BridgeExtension field19;
   private long field20;

   public SkyblockFloorFour(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockFloorFour.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(
         ModTraits.field19,
         DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DUNGEON && this.field8.method6().getNumber() == 4 && this.field9.method10())
      );
      this.method51(this::onDisable);
      this.handle(EventBlockChange.class, this::method1);
      this.handle(EventEntitySpawn.class, arg1x -> {
         this.method2(arg1x);
         this.method3(arg1x);
      });
      this.handle(HudRenderLegacyEvent.class, this::method4);
      this.handle(EventEntityRemove.class, this::method5);
      this.handle(EventWorldChange.class, this::method6);
   }

   private void method1(EventBlockChange highlightimpl91) {
      if ((Boolean)this.field13.get()) {
         if (highlightimpl91.method1().equals(field11)) {
            BlocksBridge bridge_562 = Bridge.method34();
            if (highlightimpl91.method2().bridge$getBlock() == bridge_562.method51()) {
               if (highlightimpl91.method3().bridge$getBlock() == bridge_562.method10()) {
                  this.field20 = Ref.method3().bridge$getSystemTime() + 3400L;
               }
            }
         }
      }
   }

   private void method2(EventEntitySpawn highlightimpl6_21) {
      if ((Boolean)this.field15.get()) {
         BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
         if (bridgeextension2 instanceof EntityArmorStandBridge bridgeextension_23) {
            ItemStackBridge bridgeextension_44 = bridgeextension_23.bridge$getMainHand();
            if (bridgeextension_44 == null || bridgeextension_44.bridge$getItem() != Bridge.method28().method8()) {
               return;
            }

            this.field19 = bridgeextension2;
         }
      }
   }

   private void method3(EventEntitySpawn highlightimpl6_21) {
      if ((Boolean)this.field17.get()) {
         BridgeExtension bridgeextension2 = highlightimpl6_21.field1;
         if (bridgeextension2 instanceof Bridge5Extension2 bridge5extension23) {
            String text4 = bridge5extension23.bridge$getLocationSkin().bridge$getPath();
            if ("skins/3e6b5a597108ef3370efb7122a74673167e8dc54".equals(text4)) {
               bridgeextension2.bridge$setGlowing(true);
               bridgeextension2.bridge$setGlowingColor(this.field18.method14(0.0F));
            }
         }
      }
   }

   private void method4(HudRenderLegacyEvent highlightimpl21) {
      if ((Boolean)this.field15.get()) {
         if (this.field19 != null) {
            AbstractRenderContext bridgeextension_92 = highlightimpl21.method3();
            EntityRenderDispatcherBridge bridge2_433 = Ref.method13();
            bridgeextension_92.push();
            bridgeextension_92.translate(-bridge2_433.bridge$renderPosX(), -bridge2_433.bridge$renderPosY(), -bridge2_433.bridge$renderPosZ());
            DrawBufferBridge bridge2_324 = bridgeextension_92.method10(LunarRenderTypes.field52);
            bridge2_324.method1();
            AxisAlignedBBBridge horsestats125 = this.field19.method11(highlightimpl21.method5());
            WorldRenderUtils.fillBox(
               bridge2_324,
               horsestats125.bridge$getMinX() - 1.25,
               horsestats125.bridge$getMinY() + 1.75,
               horsestats125.bridge$getMinZ() - 1.25,
               horsestats125.bridge$getMaxX() + 0.75,
               horsestats125.bridge$getMaxY() + 0.75,
               horsestats125.bridge$getMaxZ() + 0.75,
               this.field16.method14(0.0F)
            );
            bridge2_324.method17(BufferMode.BATCHED);
            bridgeextension_92.pop();
         }
      }
   }

   private void method5(EventEntityRemove highlightimpl121) {
      if ((Boolean)this.field15.get()) {
         if (highlightimpl121.method1() == this.field19) {
            this.field19 = null;
         }
      }
   }

   private void method6(EventWorldChange data31) {
      this.field19 = null;
   }

   private void onDisable() {
      this.field19 = null;
   }

   public String getId() {
      return "SKYBLOCK_FLOOR_FOUR";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, arg1xx -> arg1xx.method9(new ClientOption[]{this.field14}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field15, arg1xx -> arg1xx.method9(new ClientOption[]{this.field16}));
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field17, arg1xx -> arg1xx.method9(new ClientOption[]{this.field18}));
      });
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method2(new String[]{"f4", "m4", "floor four", "master four "})
         .method11(this);
   }

   private class Data extends TypedHudRenderer<HudLine> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_CENTER, false, true);
      }

      public HudSize method15() {
         return HudSize.method1(10, 20, 30, 50, 120, 200);
      }

      @Nullable
      public HudLine method2(boolean flag1) {
         if (flag1) {
            return this.method3(3.4);
         } else {
            return SkyblockFloorFour.this.field20 <= Ref.method3().bridge$getSystemTime()
               ? null
               : this.method3((SkyblockFloorFour.this.field20 - Ref.method3().bridge$getSystemTime()) / 1000.0);
         }
      }

      private HudLine method3(double value1) {
         String text3 = (this.CRRCCHRHOICIHCRIHHIOHCRRHOOIIH.get() ? "Spirit Bear: " : "") + String.format("%.3f", value1) + "s";
         return new HudLine(SkyblockFloorFour.field10, Component.text(text3, TextColor.color(SkyblockFloorFour.this.field14.method14(0.0F))));
      }

      protected boolean method23() {
         return true;
      }

      public boolean method4(boolean flag1) {
         return (Boolean)SkyblockFloorFour.this.field13.get() && super.method4(flag1);
      }

      public boolean method30() {
         return (Boolean)SkyblockFloorFour.this.field13.get() && super.HHRRRCCCHIOCOCRHHHRIHHCCRHORRI();
      }
   }
}
