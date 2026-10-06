package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommandKeybinds;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommand;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.SkyBlockCommandConfig;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkyBlockCommandsGui extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private boolean field19;

   public SkyBlockCommandsGui() {
   }

   protected List<GuiWidget> method25() {
      Skyblock skyblock1 = Ref.method4().method40().method82();
      SkyBlockCommandKeybinds fishing42 = skyblock1.method222();
      SkyBlockCommandConfig jsondeserializeriterator$data3 = skyblock1.method15().method11();
      if (jsondeserializeriterator$data3 != null && !jsondeserializeriterator$data3.method1().isEmpty()) {
         ArrayList list4 = new ArrayList();

         for (SkyBlockCommand fishing_26 : jsondeserializeriterator$data3.method1()) {
            list4.add(new SkyBlockCommandEntry(fishing_26));
         }

         for (String text9 : fishing42.method16()) {
            SkyBlockCommand fishing_27 = fishing42.method10(text9);
            if (fishing_27 != null) {
               list4.add(new SkyBlockSubCommandEntry(this, fishing_27.method7(), fishing_27));
            }
         }

         return Collections.unmodifiableList(list4);
      } else {
         this.field19 = true;
         return ImmutableList.of();
      }
   }

   public void init() {
      if (!this.field19) {
         byte number1 = 4;
         float value2 = 100.0F;
         float value3 = 22.0F;
         float value4 = 5.0F;
         float value5 = this.method22() / 2.0F - (value2 + value4) * number1 / 2.0F;
         float value6 = this.method23() / 2.0F - 120.0F;
         int index7 = 0;

         for (GuiWidget calculator2handler9 : this.field11) {
            if (calculator2handler9 instanceof SkyBlockCommandEntry) {
               calculator2handler9.method2(value5 + index7 * (value2 + value4), value6, value2, value3);
               if (index7 == number1 - 1) {
                  index7 = 0;
                  value6 += value3 + value4;
               } else {
                  index7++;
               }
            }
         }

         index7 = 0;
         value6 += 44.0F;
         value3 = 16.0F;

         for (GuiWidget calculator2handler14 : this.field11) {
            if (calculator2handler14 instanceof SkyBlockSubCommandEntry) {
               calculator2handler14.method2(value5 + index7 * (value2 + value4), value6, value2, value3);
               if (index7 == number1 - 1) {
                  index7 = 0;
                  value6 += value3 + value4;
               } else {
                  index7++;
               }
            }
         }
      }
   }

   public void update() {
      if (this.field19 && Client.method109().method40().method82().method15().method8()) {
         Ref.method3().bridge$displayScreen(Bridge.method8().method18(new SkyBlockCommandsGui()));
      }
   }

   public void method10(MixinHelper_4 mixinhelper_41, Data2 data22) {
      if (this.field19) {
         FontRegistry.method16()
            .method15(
               mixinhelper_41,
               this.method105("loadingCommands", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 10.0F,
               -1
            );
      } else {
         FontRegistry.method16()
            .method15(
               mixinhelper_41,
               this.method105("chooseCommand", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 140.0F,
               -1
            );
         FontRegistry.method17()
            .method15(
               mixinhelper_41,
               this.method105("keyBindHint", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 130.0F,
               -536870913
            );
         Skyblock skyblock3 = Client.method109().method40().method82();
         SkyBlockCommandConfig jsondeserializeriterator$data4 = skyblock3.method15().method11();
         if (jsondeserializeriterator$data4 != null && !jsondeserializeriterator$data4.method1().isEmpty() && !skyblock3.method222().method16().isEmpty()) {
            int number5 = jsondeserializeriterator$data4.method1().size();
            float value6 = this.method23() / 2.0F - 120.0F + number5 / 4.0F * 27.0F + 16.0F;
            FontRegistry.method16().method15(mixinhelper_41, "Favorites", this.method22() / 2.0F, value6, -1);
         }
      }
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
