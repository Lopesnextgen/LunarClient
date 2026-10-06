package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.debug.HologramsHandler;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockChat;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonRoomRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.SpiritLeapMap;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStats;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapSettings;
import com.moonsworth.lunar.client.framework.feature.mod.gui.mixin.SpiritLeapGuiContainer;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.mod.skyblock.spiritleapoverlay.SpiritLeapOverlay;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class SkyblockSpiritLeapDebug extends AbstractFeature {
   private final ModifierKeybindOption field8 = (ModifierKeybindOption)((Data)((Data)OptionFactory.method18("openSpiritLeapMenuKeybind")
            .method18(this))
         .method5(KeyCode.KEY_NONE)
         .OCIRRCIOIORIIRCOORRIROOROOHCOI(false))
      .method31();
   private final DungeonStats field9 = new HologramsHandler(DungeonRoomRegistry.method1(false));
   private final SpiritLeapMap field10 = new SpiritLeapMap(this::method6, this.field9);

   public SkyblockSpiritLeapDebug(SkyblockDebugMod skyblockdebugmod1) {
      super(true);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
      this.field8.method3(this::method13);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_SPIRIT_LEAP_DEBUG";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      super.method45(lightingextension231);
      lightingextension231.method9(
         new ClientOption[]{OptionFactory.method14("openSpiritLeapMenu").method4(this::method13).method31(), this.field8}
      );
   }

   public void method3(AbstractRenderContext bridgeextension_91, Data5 data52) {
      BettermapSettings holograms_93 = method14().method16();
      holograms_93.method54(true);
      this.field10.method1(bridgeextension_91, holograms_93, data52);
      holograms_93.method54(false);
   }

   private boolean method13() {
      GuiContainerBridge bridge5extension_31 = null;
      if (Ref.method3().bridge$getCurrentScreen() instanceof GuiContainerBridge bridge5extension_32) {
         bridge5extension_31 = bridge5extension_32;
      }

      Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SpiritLeapGuiContainer(DriverRouteRegistry.field16, bridge5extension_31, this)));
      return true;
   }

   public void method5(String text1) {
      SkyBlockChat.method1("[Spirit Leap Debug] leap to: " + text1);
   }

   private void method6(String text1) {
      this.method5(text1);
   }

   private static SpiritLeapOverlay method14() {
      return Ref.method4().method40().method82().method95();
   }

   @Generated
   public DungeonStats method15() {
      return this.field9;
   }

   @Generated
   public SpiritLeapMap method16() {
      return this.field10;
   }
}
