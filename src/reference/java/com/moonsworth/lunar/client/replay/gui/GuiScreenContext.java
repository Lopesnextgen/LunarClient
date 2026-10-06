package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.replay.gui.GuiType;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class GuiScreenContext {
   private GuiScreenBridge field1;
   private int field2;
   private int x;
   private int field3;
   private int y;
   private GuiType field4 = GuiType.CENTER;
   private GuiType field5 = GuiType.CENTER;
   private boolean field6;
   private boolean field7;
   private final Map<Integer, Boolean> field8 = new HashMap<>();

   public void method1(GuiScreenBridge guiScreenBridge) {
      this.field1 = guiScreenBridge;
      this.field8.clear();
   }

   public void method2(int number1, int number2) {
      this.field2 = this.x;
      this.field3 = this.y;
      this.x = number1;
      this.y = number2;
   }

   public int method3(float value1, int number2) {
      int number3 = this.field4.apply(this.field2, number2);
      int number4 = this.field4.apply(this.x, number2);
      return Math.round(number3 + (number4 - number3) * value1);
   }

   public int method4(float value1, int number2) {
      int number3 = this.field5.apply(this.field3, number2);
      int number4 = this.field5.apply(this.y, number2);
      return Math.round(number3 + (number4 - number3) * value1);
   }

   @Generated
   public GuiScreenContext() {
   }

   @Generated
   public GuiScreenBridge method5() {
      return this.field1;
   }

   @Generated
   public int method6() {
      return this.field2;
   }

   @Generated
   public int getX() {
      return this.x;
   }

   @Generated
   public int method7() {
      return this.field3;
   }

   @Generated
   public int getY() {
      return this.y;
   }

   @Generated
   public GuiType method8() {
      return this.field4;
   }

   @Generated
   public GuiType method9() {
      return this.field5;
   }

   @Generated
   public boolean method10() {
      return this.field6;
   }

   @Generated
   public boolean method11() {
      return this.field7;
   }

   @Generated
   public Map<Integer, Boolean> method12() {
      return this.field8;
   }

   @Generated
   public void method13(int number1) {
      this.field2 = number1;
   }

   @Generated
   public void setX(int number1) {
      this.x = number1;
   }

   @Generated
   public void method15(int number1) {
      this.field3 = number1;
   }

   @Generated
   public void setY(int number1) {
      this.y = number1;
   }

   @Generated
   public void method17(GuiType guitype1) {
      this.field4 = guitype1;
   }

   @Generated
   public void method18(GuiType guitype1) {
      this.field5 = guitype1;
   }

   @Generated
   public void method19(boolean flag1) {
      this.field6 = flag1;
   }

   @Generated
   public void method20(boolean flag1) {
      this.field7 = flag1;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof GuiScreenContext nameplate22)) {
         return false;
      } else {
         if (!nameplate22.canEqual(this)) {
            return false;
         }

         if (this.method6() != nameplate22.method6()) {
            return false;
         }

         if (this.getX() != nameplate22.getX()) {
            return false;
         }

         if (this.method7() != nameplate22.method7()) {
            return false;
         }

         if (this.getY() != nameplate22.getY()) {
            return false;
         }

         if (this.method10() != nameplate22.method10()) {
            return false;
         }

         if (this.method11() != nameplate22.method11()) {
            return false;
         }

         GuiScreenBridge bridge5extension63 = this.method5();
         GuiScreenBridge bridge5extension64 = nameplate22.method5();
         if (bridge5extension63 == null ? bridge5extension64 == null : bridge5extension63.equals(bridge5extension64)) {
            GuiType guitype5 = this.method8();
            GuiType guitype6 = nameplate22.method8();
            if (guitype5 == null ? guitype6 == null : guitype5.equals(guitype6)) {
               GuiType guitype7 = this.method9();
               GuiType guitype8 = nameplate22.method9();
               if (guitype7 == null ? guitype8 == null : guitype7.equals(guitype8)) {
                  Map map9 = this.method12();
                  Map map10 = nameplate22.method12();
                  return map9 == null ? map10 == null : map9.equals(map10);
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   @Generated
   protected boolean canEqual(Object obj1) {
      return obj1 instanceof GuiScreenContext;
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      int number2 = 1;
      number2 = number2 * 59 + this.method6();
      number2 = number2 * 59 + this.getX();
      number2 = number2 * 59 + this.method7();
      number2 = number2 * 59 + this.getY();
      number2 = number2 * 59 + (this.method10() ? 79 : 97);
      number2 = number2 * 59 + (this.method11() ? 79 : 97);
      GuiScreenBridge bridge5extension63 = this.method5();
      number2 = number2 * 59 + (bridge5extension63 == null ? 43 : bridge5extension63.hashCode());
      GuiType guitype4 = this.method8();
      number2 = number2 * 59 + (guitype4 == null ? 43 : guitype4.hashCode());
      GuiType guitype5 = this.method9();
      number2 = number2 * 59 + (guitype5 == null ? 43 : guitype5.hashCode());
      Map map6 = this.method12();
      return number2 * 59 + (map6 == null ? 43 : map6.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "GuiScreenContext(guiScreen="
         + this.method5()
         + ", prevX="
         + this.method6()
         + ", x="
         + this.getX()
         + ", prevY="
         + this.method7()
         + ", y="
         + this.getY()
         + ", xAnchor="
         + this.method8()
         + ", yAnchor="
         + this.method9()
         + ", isShiftDown="
         + this.method10()
         + ", isCtrlDown="
         + this.method11()
         + ", mouseButtons="
         + this.method12()
         + ")";
   }
}
