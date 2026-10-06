package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.bridge.TextFieldStateBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.KeyEventBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@VersionGate(min = 1)
public class TextFieldRenderer extends GuiComponent {
   private final Map<String, Map<String, TextFieldStateBridge>> field2 = new HashMap<>();
   private long field3 = 0L;

   public TextFieldRenderer(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   public void method17() {
      Map map1 = this.field2.get(this.method15());
      if (map1 != null) {
         for (TextFieldStateBridge bridge2_453 : map1.values()) {
            if (bridge2_453.selected) {
               bridge2_453.field1 = true;
            }
         }
      }
   }

   public boolean method2(String text1, String text2) {
      return this.method12(text1, this.method15(), text2).selected;
   }

   public void method3(String text1, String text2) {
      this.method12(text1, this.method15(), text2).selected = true;
   }

   public void method4(String text1, String text2, String text3) {
      this.method12(text1, text2, text3).selected = true;
   }

   public boolean method5(String text1) {
      Map map2 = this.field2.get(text1);
      if (map2 != null) {
         for (TextFieldStateBridge bridge2_454 : map2.values()) {
            if (bridge2_454.selected) {
               return true;
            }
         }
      }

      return false;
   }

   public void method6(String text1) {
      this.field2.remove(text1);
   }

   public String method7(String text1, int number2, int number3, int number4, int number5, String text6, boolean flag7) {
      TextFieldStateBridge bridge2_458 = this.method12("textinput", this.method15(), text1);
      bridge2_458.width = number4;
      bridge2_458.height = number5;
      int number9 = flag7 ? 2 : 1;
      this.method11(number2, number3, number4, number5, bridge2_458, number9);
      if (bridge2_458.selected) {
         this.method10(bridge2_458);
      }

      boolean flag10 = (Ref.method3().bridge$getSystemTime() - this.field3) % 650L < 325L;
      boolean flag11 = (bridge2_458.field5 < bridge2_458.text.length() || bridge2_458.text.length() >= bridge2_458.field2) && bridge2_458.selected;
      String text12 = this.method9(text6, bridge2_458, flag10, flag11);
      if (this.method1().field7) {
         this.method8(number2, number3, number4, number5, flag7, bridge2_458, flag10, flag11, text12);
      }

      if (bridge2_458.field1) {
         bridge2_458.selected = false;
         bridge2_458.field1 = false;
      }

      if (this.HRROORRCRHHHCCIORROORCIHOHRIHH.method42(number2 - 4 * number9, number3 - 4 * number9, number4 + 8 * number9, number5 + 8 * number9)) {
         this.method4().method20();
      }

      return bridge2_458.text;
   }

   private void method8(int number1, int number2, int number3, int number4, boolean flag5, TextFieldStateBridge bridge2_456, boolean flag7, boolean flag8, String text9) {
      if (flag5) {
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method37(number1 - 2, number2 - 2, number3 + 4, number4 + 4);
      }

      this.HRROORRCRHHHCCIORROORCIHOHRIHH.method38(number1, number2, number3, number4);
      double value10 = number4 / 10.0;
      this.HRROORRCRHHHCCIORROORCIHOHRIHH.method3();
      this.HRROORRCRHHHCCIORROORCIHOHRIHH.method18(number1, number2, number3, number4);
      int number12 = (int)(number1 + this.HRROORRCRHHHCCIORROORCIHOHRIHH.getStringWidth(bridge2_456.text.substring(bridge2_456.field3, Math.max(bridge2_456.field3, bridge2_456.field5))) * value10);
      if (bridge2_456.selected && bridge2_456.field4 != bridge2_456.field5) {
         int number13 = (int)(
            number1 + this.HRROORRCRHHHCCIORROORCIHOHRIHH.getStringWidth(bridge2_456.text.substring(bridge2_456.field3, Math.max(bridge2_456.field3, bridge2_456.field4))) * value10
         );
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number13 + 1, number2, number12 - number13, number4, -865704193);
      }

      this.HRROORRCRHHHCCIORROORCIHOHRIHH.method25(text9, number1 + 1, number2 + 1, !bridge2_456.text.isEmpty() || bridge2_456.selected, (float)value10);
      this.HRROORRCRHHHCCIORROORCIHOHRIHH.method4();
      if (flag7 && flag8) {
         this.HRROORRCRHHHCCIORROORCIHOHRIHH.method43(number12, number2, 1, number4, -3750202);
      }
   }

   @NotNull
   private String method9(String text1, TextFieldStateBridge bridge2_452, boolean flag3, boolean flag4) {
      String text5 = bridge2_452.text;
      if (text5.isEmpty() && !bridge2_452.selected) {
         text5 = ChatFormatting.GRAY + text1;
      } else if (bridge2_452.selected) {
         if (bridge2_452.field4 != bridge2_452.field5) {
            int index6 = Math.min(bridge2_452.field5, bridge2_452.field4);
            int index7 = Math.max(bridge2_452.field5, bridge2_452.field4);
            text5 = bridge2_452.text.substring(bridge2_452.field3, Math.max(bridge2_452.field3, index6))
               + ChatFormatting.BLUE
               + bridge2_452.text.substring(index6, index7)
               + ChatFormatting.RESET
               + bridge2_452.text.substring(index7);
         } else {
            text5 = bridge2_452.text.substring(bridge2_452.field3);
         }

         if (flag3 && !flag4) {
            text5 = text5 + "_";
         }
      }

      return text5;
   }

   private void method10(TextFieldStateBridge bridge2_451) {
      for (KeyEventBridge bridge_73 : this.method13().method18()) {
         if (bridge_73.method2() && bridge_73.method3()) {
            bridge2_451.selected = false;
         }

         if (this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_34) {
            bridge5extension_34.lunar$handleKeyEventOnState(bridge2_451, bridge_73);
         }

         this.field3 = Ref.method3().bridge$getSystemTime();
      }

      this.method13().method18().clear();
   }

   private void method11(int number1, int number2, int number3, int number4, TextFieldStateBridge bridge2_455, int number6) {
      MouseClick mixinhelper_27 = this.HRROORRCRHHHCCIORROORCIHOHRIHH.method40(number1 - 4 * number6, number2 - 4 * number6, number3 + 8 * number6, number4 + 8 * number6);
      if (mixinhelper_27 != null) {
         if (bridge2_455.selected && this.HRROORRCRHHHCCIORROORCIHOHRIHH.method44() instanceof GuiContainerBridge bridge5extension_38) {
            bridge5extension_38.lunar$handleMousePressOnState(bridge2_455, mixinhelper_27.method2(), mixinhelper_27.x() - number1 + 6, mixinhelper_27.y() - number2);
         }

         bridge2_455.selected = true;
         bridge2_455.field1 = false;
         this.field3 = Ref.method3().bridge$getSystemTime();
      }
   }

   private TextFieldStateBridge method12(String text1, String text2, String text3) {
      Map map4 = this.field2.computeIfAbsent(text2, arg0 -> new HashMap<>());
      return map4.computeIfAbsent(text1 + "-" + text3, arg0 -> new TextFieldStateBridge(0, 0, 1000));
   }
}
