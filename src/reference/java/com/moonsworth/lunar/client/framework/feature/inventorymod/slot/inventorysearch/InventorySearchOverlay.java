package com.moonsworth.lunar.client.framework.feature.inventorymod.slot.inventorysearch;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.KeyEventBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.GuiRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.GuiTheme;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.CalculatorParser;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.function.Supplier;
import lombok.Generated;
import org.joml.Vector2i;

@VersionGate(min = 1)
public class InventorySearchOverlay extends com.moonsworth.lunar.client.framework.feature.mod.OverlayModule {
   private final Supplier<GuiTheme> field2;
   private final Supplier<Double> field3;
   private final Supplier<Gui2Extension> field4;
   private final Supplier<String> field5;
   private final Supplier<Boolean> field6;
   private final AnimatedValue field7 = new AnimatedValue(0.0, AnimatedValue.Type.SIN_OUT);
   private final AnimatedValue field8 = new AnimatedValue(0.0, AnimatedValue.Type.SIN_OUT);
   private String field9 = "";
   private boolean field10;
   private boolean field11;

   @Override
   public boolean method1(GuiScreenBridge bridge5extension61) {
      return bridge5extension61 instanceof GuiContainerBridge;
   }

   @Override
   public void method2(GuiScreenBridge bridge5extension61, MixinHelper_4 mixinhelper_42, int number3, int number4, float value5) {
      double value6 = this.field3.get();
      mixinhelper_42.push();
      mixinhelper_42.method40((float)value6, (float)value6);
      number3 = (int)(number3 / value6);
      number4 = (int)(number4 / value6);
      GuiRenderer.field1.setTheme(this.field2.get());
      GuiRenderer.field1.method1("InvSearchOverlay", bridge5extension61, mixinhelper_42, number3, number4, false);
      GuiRenderer.field1.method3();
      GuiRenderer.field1.method20(value6, value6, 1.0);
      double value8 = LcuiScreen.method151().getScaledWidth() / value6;
      double value10 = LcuiScreen.method151().getScaledHeight() / value6;

      Vector2i vector2i12 = switch ((Gui2Extension)this.field4.get()) {
         case TOP_LEFT -> new Vector2i(6, 6);
         case TOP_MIDDLE -> new Vector2i((int)(value8 / 2.0 - 50.0), 6);
         case BOTTOM_LEFT -> new Vector2i(6, (int)(value10 - 20.0));
         default -> new Vector2i((int)(value8 / 2.0 - 50.0), (int)(value10 - 20.0));
      };
      if (this.field6.get()) {
         int number13 = (int)(25.0 * (1.0 - this.field8.getValue()));
         if (this.field4.get() == Gui2Extension.TOP_LEFT || this.field4.get() == Gui2Extension.TOP_MIDDLE) {
            number13 *= -1;
         }

         vector2i12.y += number13;
      }

      GuiRenderer.field1.method37(vector2i12.x - 2, vector2i12.y - 2, 104 + (int)this.field7.getValue(), 14);
      this.field9 = GuiRenderer.field1.method45("InvSearchOverlay-search", vector2i12.x, vector2i12.y, 100, 10, this.field5.get(), false);
      String text17 = CalculatorParser.formatResult(this.field9, true);
      if (text17 != null) {
         this.field9 = "";
         text17 = " = " + text17;
         int number14 = GuiRenderer.field1.getStringWidth(text17);
         GuiRenderer.field1.method3();
         GuiRenderer.field1.method18(vector2i12.x - 2, vector2i12.y - 2, 104 + (int)this.field7.getValue(), 14);
         GuiRenderer.field1.method26(text17, vector2i12.x + 100, vector2i12.y + 1, true);
         GuiRenderer.field1.method4();
         this.field7.animateTo(number14, 100L);
      } else {
         this.field7.animateTo(0.0, 100L);
      }

      GuiRenderer.field1.method4();
      GuiRenderer.field1.end();
      mixinhelper_42.pop();
   }

   @Override
   public boolean method3(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      double value5 = this.field3.get() / Ref.method4().method40().method96().method3(bridge5extension61);
      return GuiRenderer.field1.method27("InvSearchOverlay", (int)(number2 / value5), (int)(number3 / value5), number4);
   }

   @Override
   public boolean method4(GuiScreenBridge bridge5extension61, int number2, int number3, int number4) {
      double value5 = this.field3.get() / Ref.method4().method40().method96().method3(bridge5extension61);
      return GuiRenderer.field1.method28("InvSearchOverlay", (int)(number2 / value5), (int)(number3 / value5), number4);
   }

   @Override
   public boolean method10(GuiScreenBridge bridge5extension61, KeyEventBridge bridge_72) {
      if (!this.isOpen()) {
         return false;
      } else if (this.field11) {
         this.field11 = false;
         return true;
      } else {
         return GuiRenderer.field1.method29("InvSearchOverlay", bridge_72);
      }
   }

   @Override
   public void onClose() {
      super.onClose();
      if (this.field10 && this.field9.isBlank()) {
         this.field8.animateTo(0.0, 0L);
         this.field10 = false;
      }
   }

   public boolean isOpen() {
      return !this.field6.get() || this.field10;
   }

   public void open() {
      this.field10 = true;
      this.field8.animateTo(1.0, 100L);
      GuiRenderer.field1.method10("textinput", "InvSearchOverlay", "InvSearchOverlay-search");
   }

   public void close() {
      this.field10 = false;
      this.field8.animateTo(0.0, 100L);
   }

   public void method6() {
      this.field9 = "";
      GuiRenderer.field1.method5("InvSearchOverlay");
   }

   public void method10() {
      this.field11 = true;
   }

   @Generated
   public InventorySearchOverlay(Supplier<GuiTheme> supplier1, Supplier<Double> supplier2, Supplier<Gui2Extension> supplier3, Supplier<String> supplier4, Supplier<Boolean> supplier5) {
      this.field2 = supplier1;
      this.field3 = supplier2;
      this.field4 = supplier3;
      this.field5 = supplier4;
      this.field6 = supplier5;
   }

   @Generated
   public String method11() {
      return this.field9;
   }
}
