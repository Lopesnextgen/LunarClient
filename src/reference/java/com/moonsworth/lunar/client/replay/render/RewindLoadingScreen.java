package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextAreaWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;

public class RewindLoadingScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private TextAreaWidget field19;

   public RewindLoadingScreen() {
      this.field19.HORHROIOIOICIRHIOCOICHHHIHCIIO((arg0, arg1) -> {
         Ref.method4().method40().method85().method16();
         DriverViewportLegacy.method50().method16(DriverRouteRegistry.field12);
         return true;
      });
   }

   protected List<GuiWidget> method25() {
      return List.of(this.field19 = new TextAreaWidget(null, "cancel"));
   }

   public void init() {
      this.field19
         .RIIICIRHRCIHOOOORHOICRIICCCRHR(
            this.method22() / 2.0F - 100.0F, this.method23() / 2.0F + 10.0F, 200.0F, 20.0F
         );
   }

   public void update() {
   }

   public void method10(MixinHelper_4 mixinhelper_41, Data2 data22) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(
         mixinhelper_41, 0.0F, 0.0F, this.method22(), this.method23(), -15526886
      );
      FontRegistry.field13
         .method14(mixinhelper_41, "Loading Rewind...", this.method22() / 2.0F, this.method23() / 2.0F - 20.0F, -218498305);
   }

   public void method11(Data2 data21, int number2) {
   }

   public void method12(Data2 data21, int number2) {
   }

   public void method14(char character1, KeyCode bridgetype_82) {
   }

   public void close() {
   }
}
