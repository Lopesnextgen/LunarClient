package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Bridge_65;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.TitledWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindToggleWidget;
import com.moonsworth.lunar.client.ui.widget.HoverInfoOptionWidget;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;

public class Gui2 extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private final Bridge5Extension6 field19;
   private final Fishing_2 field20;
   private final GuiWidget field21;
   private final KeybindToggleWidget field22;
   private final HoverInfoOptionWidget field23;
   private TitledWidget field24;

   public Gui2(Bridge5Extension6 var1, Fishing_2 var2) {
      this.field19 = var1;
      this.field20 = var2;
      Skyblock var3 = Client.method109().method40().method82();
      ModifierKeybindOption var4 = var3.method222().method5(this.field20);
      ToggleOption var5 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("favorite")
            .method4(var3.method222().method16().contains(var2.key())))
         .method31();
      this.field24.setTitle(var2.getPrettyName() + " Options");
      this.field24.method8(-1090519040);
      this.field24
         .method14(
            ImmutableList.of(
               this.field21 = new TextLabelWidget(this.field24, "done"),
               this.field22 = (KeybindToggleWidget)var4.method18(this.field24),
               this.field23 = (HoverInfoOptionWidget)var5.method18(this.field24)
            )
         );
      this.field21.method25((var1x, var2x) -> {
         Bridge7_8 var3x = ThreadModuleDump63.method31(var1);
         if (var3x instanceof Gui3) {
            ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Gui3()));
         } else {
            ThreadModuleDump63.method3().bridge$displayScreen(var1);
         }

         return true;
      });
      this.field22.setName("Keybind");
      var5.CICORRHIOIIOORRRICCORIOIOCIHII(
         var4x -> {
            if (var4x) {
               var3.method222().method16().add(var2.key());
            } else {
               var3.method222().method16().remove(var2.key());
               Bridge7_8 var5x = ThreadModuleDump63.method31(var1);
               if (var5x instanceof Gui3) {
                  ((Gui3)var5x)
                     .ROICIOIRHHCOHHCHCCORHRCROORRRC()
                     .removeIf(var1xx -> var1xx instanceof Calculator2Updater2 && ((Calculator2Updater2)var1xx).method3() == this.field20);
               }
            }
         }
      );
   }

   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field24 = new TitledWidget(null, "") {
         public void method3(float var1, float var2, float var3, float var4) {
            Gui2.this.field21.method2(var1 + var3 - 45.0F, var2 + var4 - 23.0F, 40.0F, 15.0F);
            Gui2.this.field22.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 8.0F, var2 + 30.0F, this.width - 16.0F, 26.0F);
            Gui2.this.field23.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 8.0F, var2 + 45.0F, this.width - 16.0F, 26.0F);
         }
      });
   }

   public void init() {
      this.field19.bridge$setWorldAndResolution(this.width, this.height);
      float var1 = 98.0F;
      float var2 = 200.0F;
      this.field24.method2(this.method22() / 2.0F - var2 / 2.0F, this.method23() / 2.0F - var1 / 2.0F, var2, var1);
   }

   public void update() {
   }

   public void method10(MixinHelper_4 var1, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var2) {
   }

   public void method1(MixinHelper_4 var1, Bridge_65 var2, float var3) {
      super.method1(var1, var2, var3);
   }

   public void method11(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var1, int var2) {
   }

   public void method12(com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 var1, int var2) {
   }

   public void method14(char var1, KeyCode var2) {
   }

   public void close() {
   }

   public String method133() {
      return "SkyBlock Commands Overlay";
   }
}
