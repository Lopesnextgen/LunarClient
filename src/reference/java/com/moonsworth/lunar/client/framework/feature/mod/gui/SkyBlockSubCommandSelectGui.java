package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommand;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class SkyBlockSubCommandSelectGui extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private final SkyBlockCommand field19;
   private final ResourceLocationBridge field20;

   public SkyBlockSubCommandSelectGui(Bridge7_8 bridge7_81, SkyBlockCommand fishing_22, @NotNull ResourceLocationBridge horsestats143) {
      this.field19 = fishing_22;
      this.field20 = horsestats143;
      this.field11.add(new SkyBlockSubCommandEntry(bridge7_81, null, null));
      if (!(bridge7_81 instanceof SkyBlockCommandsGui)) {
         this.field11.add(new SkyBlockSubCommandEntry(bridge7_81, null, this.field19));
      }

      for (SkyBlockCommand fishing_25 : this.field19.method5()) {
         this.field11.add(new SkyBlockSubCommandEntry(this, this.field19, fishing_25));
      }
   }

   protected List<GuiWidget> method25() {
      return new ArrayList<>();
   }

   public void init() {
      byte number1 = 4;
      float value2 = 100.0F;
      float value3 = 16.0F;
      float value4 = 5.0F;
      float value5 = this.method22() / 2.0F - (value2 + value4) * number1 / 2.0F;
      float value6 = this.method23() / 2.0F - 60.0F;
      int index7 = 0;

      for (GuiWidget calculator2handler9 : this.field11) {
         if (calculator2handler9 instanceof SkyBlockSubCommandEntry) {
            calculator2handler9.method2(value5 + index7 * (value2 + value4), value6, value2, value3);
            if (index7 == number1 - 1) {
               index7 = 0;
               value6 += value3 + value4;
            } else {
               index7++;
            }
         }
      }
   }

   public void update() {
   }

   public void method10(MixinHelper_4 mixinhelper_41, Data2 data22) {
      float value3 = this.method22() / 2.0F;
      float value4 = this.method23() / 2.0F - 100.0F;
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(mixinhelper_41, this.field20, value3 - 16.0F, value4 - 46.0F, 32.0F, 32.0F, -1);
      FontRegistry.method22().method15(mixinhelper_41, this.field19.getPrettyName(), value3, value4 - 10.0F, -1);
      FontRegistry.method16().method15(mixinhelper_41, this.method105("chooseCommand", new Object[0]), value3, value4 + 15.0F, -1);
      FontRegistry.method17().method15(mixinhelper_41, this.method105("keyBindHint", new Object[0]), value3, value4 + 25.0F, -536870913);
   }

   public void method11(Data2 data21, int number2) {
   }

   public void method12(Data2 data21, int number2) {
   }

   public void method14(char character1, KeyCode bridgetype_82) {
   }

   public void close() {
   }

   public String getLanguagePath() {
      return super.getLanguagePath() + ".skyBlockCommands";
   }
}
