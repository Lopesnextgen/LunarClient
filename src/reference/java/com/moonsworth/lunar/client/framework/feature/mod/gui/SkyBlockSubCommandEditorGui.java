package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MarkerPositionBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.ui.widget.TitledWidget;
import com.moonsworth.lunar.client.ui.widget.CommandOptionWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindToggleWidget;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommandKeybinds;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommand;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SkyBlockSubCommandEditorGui extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private final GuiScreenBridge field19;
   private final SkyBlockCommand field20;
   private final GuiWidget field21;
   private final Map<KeybindToggleWidget, GuiWidget> field22;
   private final GuiWidget field23;
   private final TextOption field24 = (TextOption)OptionFactory.method12("subCommandInputField").RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
   private final CommandOptionWidget field25;
   private final ResourceLocationBridge field26 = ResourceLocationBridge.create("lunar", "icons/assets/deny-16x16.png");
   private TitledWidget field27;

   public SkyBlockSubCommandEditorGui(GuiScreenBridge bridge5extension61, SkyBlockCommand fishing_22) {
      this.field19 = bridge5extension61;
      this.field20 = fishing_22;
      ArrayList list3 = new ArrayList();
      SkyBlockCommandKeybinds fishing44 = Client.method109().method40().method82().method222();
      ToggleOption lightingextension4435 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("favorite")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(fishing44.method16().contains(fishing_22.key())))
         .RIRRHIRCCHCCHOICRHRHHRIHOIHHRH();
      this.field22 = new LinkedHashMap<>();
      if (fishing44.method18().containsKey(fishing_22.key())) {
         for (String text8 : fishing44.method18().get(fishing_22.key())) {
            this.method1(fishing44, text8);
         }
      }

      list3.add(this.field21 = new TextLabelWidget(this.field27, "done"));
      list3.add(this.field23 = new TextLabelWidget(this.field27, "addCommand"));
      list3.add(this.field25 = (CommandOptionWidget)this.field24.method18(this.field27));
      list3.addAll(this.field22.keySet());
      list3.addAll(this.field22.values());
      this.field27.setTitle(fishing_22.getPrettyName() + " Options");
      this.field27.method8(-1090519040);
      this.field27.method14(list3);
      this.field21.method25((arg1x, arg2x) -> {
         Bridge7_8 bridge7_83x = Ref.method31(bridge5extension61);
         if (bridge7_83x instanceof SkyBlockCommandsGui) {
            Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SkyBlockCommandsGui()));
         } else {
            Ref.method3().bridge$displayScreen(bridge5extension61);
         }

         return true;
      });
      this.field23.method25((arg2x, arg3x) -> {
         if (this.field20.method6().size() > 10) {
            return true;
         }

         fishing44.method1(this.field20, (String)this.field24.get());
         Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SkyBlockSubCommandEditorGui(this.field19, this.field20)));
         return true;
      });
      lightingextension4435.CICORRHIOIIOORRRICCORIOIOCIHII(
         arg4x -> {
            if (arg4x) {
               fishing44.method16().add(fishing_22.key());
            } else {
               fishing44.method16().remove(fishing_22.key());
               Bridge7_8 bridge7_85x = Ref.method31(bridge5extension61);
               if (bridge7_85x instanceof SkyBlockCommandsGui) {
                  ((SkyBlockCommandsGui)bridge7_85x)
                     .ROICIOIRHHCOHHCHCCORHRCROORRRC()
                     .removeIf(arg1xx -> arg1xx instanceof SkyBlockCommandEntry && ((SkyBlockCommandEntry)arg1xx).method3() == this.field20);
               }
            }
         }
      );
   }

   private void method1(SkyBlockCommandKeybinds fishing41, String text2) {
      ModifierKeybindOption lightingextension491333 = fishing41.method6(this.field20, text2);
      ProgressBarWidget calculator2impl74 = new ProgressBarWidget(this.field27, this.field26);
      KeybindToggleWidget calculator2iterator32345 = (KeybindToggleWidget)lightingextension491333.method18(this.field27);
      this.field22.put(calculator2iterator32345, calculator2impl74);
      calculator2iterator32345.setName(lightingextension491333.getName() + " KeyBind");
      calculator2impl74.method18(new AnimatedValue(-4644318, -2530718));
      calculator2impl74.method4((arg3x, arg4x) -> {
         fishing41.method2(this.field20, text2);
         Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SkyBlockSubCommandEditorGui(this.field19, this.field20)));
         return true;
      });
   }

   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field27 = new TitledWidget(null, "") {
         public void method3(float value1, float value2, float value3, float value4) {
            SkyBlockSubCommandEditorGui.this.field21.method2(value1 + value3 - 45.0F, value2 + value4 - 23.0F, 40.0F, 15.0F);
            float value5 = value2 + 30.0F;

            for (Entry entry7 : SkyBlockSubCommandEditorGui.this.field22.entrySet()) {
               ((KeybindToggleWidget)entry7.getKey()).RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + 8.0F, value5, this.width - 36.0F, 26.0F);
               ((GuiWidget)entry7.getValue()).method2(value1 + this.width - 20.0F, value5, 15.0F, 15.0F);
               value5 += 20.0F;
            }

            SkyBlockSubCommandEditorGui.this.field25.RIIICIRHRCIHOOOORHOICRIICCCRHR(value1 + 8.0F, value5 + 20.0F, this.width - 16.0F, 15.0F);
            SkyBlockSubCommandEditorGui.this.field23.method2(value1 + 8.0F, value5 + 40.0F, this.width - 16.0F, 15.0F);
         }
      });
   }

   public void init() {
      this.field19.bridge$setWorldAndResolution(this.width, this.height);
      float value1 = 300.0F;
      float value2 = 300.0F;
      this.field27.method2(this.method22() / 2.0F - value2 / 2.0F, this.method23() / 2.0F - value1 / 2.0F, value2, value1);
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
      return "SkyBlock User Defined Sub Commands Overlay";
   }
}
