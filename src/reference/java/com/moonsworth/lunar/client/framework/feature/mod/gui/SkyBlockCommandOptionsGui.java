package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MarkerPositionBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.TitledWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindToggleWidget;
import com.moonsworth.lunar.client.ui.widget.HoverInfoOptionWidget;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommand;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;

public class SkyBlockCommandOptionsGui extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private final GuiScreenBridge field19;
   private final SkyBlockCommand field20;
   private final GuiWidget field21;
   private final KeybindToggleWidget field22;
   private final HoverInfoOptionWidget field23;
   private TitledWidget field24;

   public SkyBlockCommandOptionsGui(GuiScreenBridge bridge5extension61, SkyBlockCommand fishing_22) {
      this.field19 = bridge5extension61;
      this.field20 = fishing_22;
      Skyblock skyblock3 = Client.method109().method40().method82();
      ModifierKeybindOption lightingextension491334 = skyblock3.method222().method5(this.field20);
      ToggleOption lightingextension4435 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("favorite")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(skyblock3.method222().method16().contains(fishing_22.key())))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field24.setTitle(fishing_22.getPrettyName() + " Options");
      this.field24.method8(-1090519040);
      this.field24
         .RCRHIIIOHIRHRCORCCCIRRRICHIHRC(
            ImmutableList.of(
               this.field21 = new TextLabelWidget(this.field24, "done"),
               this.field22 = (KeybindToggleWidget)lightingextension491334.method18(this.field24),
               this.field23 = (HoverInfoOptionWidget)lightingextension4435.method18(this.field24)
            )
         );
      this.field21.method25((arg1x, arg2x) -> {
         Bridge7_8 bridge7_83x = Ref.method31(bridge5extension61);
         if (bridge7_83x instanceof SkyBlockCommandsGui) {
            Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SkyBlockCommandsGui()));
         } else {
            Ref.method3().bridge$displayScreen(bridge5extension61);
         }

         return true;
      });
      this.field22.setName("Keybind");
      lightingextension4435.CICORRHIOIIOORRRICCORIOIOCIHII(
         arg4x -> {
            if (arg4x) {
               skyblock3.method222().method16().add(fishing_22.key());
            } else {
               skyblock3.method222().method16().remove(fishing_22.key());
               Bridge7_8 bridge7_85x = Ref.method31(bridge5extension61);
               if (bridge7_85x instanceof SkyBlockCommandsGui) {
                  ((SkyBlockCommandsGui)bridge7_85x)
                     .ROICIOIRHHCOHHCHCCORHRCROORRRC()
                     .removeIf(arg1xx -> arg1xx instanceof SkyBlockSubCommandEntry && ((SkyBlockSubCommandEntry)arg1xx).method3() == this.field20);
               }
            }
         }
      );
   }

   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field24 = new TitledWidget(null, "") {
         public void method3(float value1, float value2, float value3, float value4) {
            SkyBlockCommandOptionsGui.this.field21.method2(value1 + value3 - 45.0F, value2 + value4 - 23.0F, 40.0F, 15.0F);
            SkyBlockCommandOptionsGui.this.field22.RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + 8.0F, value2 + 30.0F, this.width - 16.0F, 26.0F);
            SkyBlockCommandOptionsGui.this.field23.RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + 8.0F, value2 + 45.0F, this.width - 16.0F, 26.0F);
         }
      });
   }

   public void init() {
      this.field19.bridge$setWorldAndResolution(this.width, this.height);
      float value1 = 98.0F;
      float value2 = 200.0F;
      this.field24.method2(this.method22() / 2.0F - value2 / 2.0F, this.method23() / 2.0F - value1 / 2.0F, value2, value1);
   }

   public void update() {
   }

   public void method10(MixinHelper_4 mixinhelper_41, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data22) {
   }

   public void method1(MixinHelper_4 mixinhelper_41, MarkerPositionBridge bridge_652, float value3) {
      super.method1(mixinhelper_41, bridge_652, value3);
   }

   public void method11(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data21, int number2) {
   }

   public void method12(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data21, int number2) {
   }

   public void method14(char character1, KeyCode bridgetype_82) {
   }

   public void close() {
   }

   public String method133() {
      return "SkyBlock Commands Overlay";
   }
}
