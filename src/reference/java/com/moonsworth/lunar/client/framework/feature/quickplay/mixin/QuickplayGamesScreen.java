package com.moonsworth.lunar.client.framework.feature.quickplay.mixin;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.misc.quickplay.Quickplay;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Generated;

public class QuickplayGamesScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private boolean field19;

   protected List<GuiWidget> method25() {
      Quickplay quickplay1 = Client.method109().method40().method52();
      if (quickplay1.method14() && quickplay1.method15() != null && !quickplay1.method15().isEmpty()) {
         ArrayList list2 = new ArrayList();

         for (com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay4 : Client.method109().method40().method52().method15()) {
            list2.add(new QuickplayGameEntry(quickplay4));
         }

         for (String text8 : Client.method109().method40().method52().method16()) {
            com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay quickplay5 = Client.method109().method40().method52().method10(text8);
            if (quickplay5 != null) {
               String text6 = quickplay5.method3() == null ? quickplay5.getName() : quickplay5.method3().getName() + ": " + quickplay5.getName();
               list2.add(new QuickplayModeEntry(this, quickplay5.method3(), quickplay5, quickplay5.method2(), text6));
            }
         }

         return Collections.unmodifiableList(list2);
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
            if (calculator2handler9 instanceof QuickplayGameEntry) {
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
            if (calculator2handler14 instanceof QuickplayModeEntry) {
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
      if (this.field19 && Client.method109().method40().method52().method14()) {
         Ref.method3().bridge$displayScreen(Bridge.method8().method18(new QuickplayGamesScreen()));
      }
   }

   public void method10(MixinHelper_4 mixinhelper_41, Data2 data22) {
      if (this.field19) {
         FontRegistry.method16()
            .method15(
               mixinhelper_41,
               this.method105("loadingGames", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 10.0F,
               -1
            );
      } else {
         FontRegistry.method16()
            .method15(
               mixinhelper_41,
               this.method105("chooseGame", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 140.0F,
               -1
            );
         Quickplay quickplay3 = Client.method109().method40().method52();
         if (!quickplay3.method16().isEmpty()) {
            int number4 = quickplay3.method15().size();
            float value5 = this.method23() / 2.0F - 120.0F + number4 / 4.0F * 27.0F + 16.0F;
            FontRegistry.method16().method15(mixinhelper_41, "Favorites", this.method22() / 2.0F, value5, -1);
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
      return super.getLanguagePath() + ".quickPlay";
   }

   @Generated
   public QuickplayGamesScreen() {
   }
}
