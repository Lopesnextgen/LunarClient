package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.Bridge_65;
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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_2;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Gui4 extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private final Bridge5Extension6 field19;
   private final Fishing_2 field20;
   private final GuiWidget field21;
   private final Map<KeybindToggleWidget, GuiWidget> field22;
   private final GuiWidget field23;
   private final TextOption field24 = (TextOption)OptionFactory.method12("subCommandInputField").method31();
   private final CommandOptionWidget field25;
   private final ResourceLocationBridge field26 = ResourceLocationBridge.create("lunar", "icons/assets/deny-16x16.png");
   private TitledWidget field27;

   public Gui4(Bridge5Extension6 var1, Fishing_2 var2) {
      this.field19 = var1;
      this.field20 = var2;
      ArrayList var3 = new ArrayList();
      Fishing4 var4 = Client.method109().method40().method82().method222();
      ToggleOption var5 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("favorite")
            .method4(var4.method16().contains(var2.key())))
         .method31();
      this.field22 = new LinkedHashMap<>();
      if (var4.method18().containsKey(var2.key())) {
         for (String var8 : var4.method18().get(var2.key())) {
            this.method1(var4, var8);
         }
      }

      var3.add(this.field21 = new TextLabelWidget(this.field27, "done"));
      var3.add(this.field23 = new TextLabelWidget(this.field27, "addCommand"));
      var3.add(this.field25 = (CommandOptionWidget)this.field24.method18(this.field27));
      var3.addAll(this.field22.keySet());
      var3.addAll(this.field22.values());
      this.field27.setTitle(var2.getPrettyName() + " Options");
      this.field27.method8(-1090519040);
      this.field27.method14(var3);
      this.field21.method25((var1x, var2x) -> {
         Bridge7_8 var3x = ThreadModuleDump63.method31(var1);
         if (var3x instanceof Gui3) {
            ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Gui3()));
         } else {
            ThreadModuleDump63.method3().bridge$displayScreen(var1);
         }

         return true;
      });
      this.field23.method25((var2x, var3x) -> {
         if (this.field20.method6().size() > 10) {
            return true;
         }

         var4.method1(this.field20, (String)this.field24.get());
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Gui4(this.field19, this.field20)));
         return true;
      });
      var5.CICORRHIOIIOORRRICCORIOIOCIHII(
         var4x -> {
            if (var4x) {
               var4.method16().add(var2.key());
            } else {
               var4.method16().remove(var2.key());
               Bridge7_8 var5x = ThreadModuleDump63.method31(var1);
               if (var5x instanceof Gui3) {
                  ((Gui3)var5x)
                     .ROICIOIRHHCOHHCHCCORHRCROORRRC()
                     .removeIf(var1xx -> var1xx instanceof Calculator2Updater && ((Calculator2Updater)var1xx).method3() == this.field20);
               }
            }
         }
      );
   }

   private void method1(Fishing4 var1, String var2) {
      ModifierKeybindOption var3 = var1.method6(this.field20, var2);
      ProgressBarWidget var4 = new ProgressBarWidget(this.field27, this.field26);
      KeybindToggleWidget var5 = (KeybindToggleWidget)var3.HIOROOCORHICHIRIHIRCCORCIIICCH(this.field27);
      this.field22.put(var5, var4);
      var5.setName(var3.getName() + " KeyBind");
      var4.method18(new AnimatedValue(-4644318, -2530718));
      var4.method4((var3x, var4x) -> {
         var1.method2(this.field20, var2);
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Gui4(this.field19, this.field20)));
         return true;
      });
   }

   protected List<GuiWidget> method25() {
      return ImmutableList.of(this.field27 = new TitledWidget(null, "") {
         public void method3(float var1, float var2, float var3, float var4) {
            Gui4.this.field21.method2(var1 + var3 - 45.0F, var2 + var4 - 23.0F, 40.0F, 15.0F);
            float var5 = var2 + 30.0F;

            for (Entry var7 : Gui4.this.field22.entrySet()) {
               ((KeybindToggleWidget)var7.getKey()).RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 8.0F, var5, this.width - 36.0F, 26.0F);
               ((GuiWidget)var7.getValue()).method2(var1 + this.width - 20.0F, var5, 15.0F, 15.0F);
               var5 += 20.0F;
            }

            Gui4.this.field25.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + 8.0F, var5 + 20.0F, this.width - 16.0F, 15.0F);
            Gui4.this.field23.method2(var1 + 8.0F, var5 + 40.0F, this.width - 16.0F, 15.0F);
         }
      });
   }

   public void init() {
      this.field19.bridge$setWorldAndResolution(this.width, this.height);
      float var1 = 300.0F;
      float var2 = 300.0F;
      this.field27.method2(this.method22() / 2.0F - var2 / 2.0F, this.method23() / 2.0F - var1 / 2.0F, var2, var1);
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
      return "SkyBlock User Defined Sub Commands Overlay";
   }
}
