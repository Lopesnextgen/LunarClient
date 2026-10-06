package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType;
import java.util.HashMap;
import java.util.Map;
import lombok.Generated;

public class Nameplate2 {
   private Bridge5Extension6 field1;
   private int field2;
   private int x;
   private int field3;
   private int y;
   private GuiType field4 = GuiType.CENTER;
   private GuiType field5 = GuiType.CENTER;
   private boolean field6;
   private boolean field7;
   private final Map<Integer, Boolean> field8 = new HashMap<>();

   public void method1(Bridge5Extension6 var1) {
      this.field1 = var1;
      this.field8.clear();
   }

   public void method2(int var1, int var2) {
      this.field2 = this.x;
      this.field3 = this.y;
      this.x = var1;
      this.y = var2;
   }

   public int method3(float var1, int var2) {
      int var3 = this.field4.apply(this.field2, var2);
      int var4 = this.field4.apply(this.x, var2);
      return Math.round(var3 + (var4 - var3) * var1);
   }

   public int method4(float var1, int var2) {
      int var3 = this.field5.apply(this.field3, var2);
      int var4 = this.field5.apply(this.y, var2);
      return Math.round(var3 + (var4 - var3) * var1);
   }

   @Generated
   public Bridge5Extension6 method5() {
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
   public void method13(int var1) {
      this.field2 = var1;
   }

   @Generated
   public void setX(int var1) {
      this.x = var1;
   }

   @Generated
   public void method15(int var1) {
      this.field3 = var1;
   }

   @Generated
   public void setY(int var1) {
      this.y = var1;
   }

   @Generated
   public void method17(GuiType var1) {
      this.field4 = var1;
   }

   @Generated
   public void method18(GuiType var1) {
      this.field5 = var1;
   }

   @Generated
   public void method19(boolean var1) {
      this.field6 = var1;
   }

   @Generated
   public void method20(boolean var1) {
      this.field7 = var1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Nameplate2 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         if (this.method6() != var2.method6()) {
            return false;
         }

         if (this.getX() != var2.getX()) {
            return false;
         }

         if (this.method7() != var2.method7()) {
            return false;
         }

         if (this.getY() != var2.getY()) {
            return false;
         }

         if (this.method10() != var2.method10()) {
            return false;
         }

         if (this.method11() != var2.method11()) {
            return false;
         }

         Bridge5Extension6 var3 = this.method5();
         Bridge5Extension6 var4 = var2.method5();
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            GuiType var5 = this.method8();
            GuiType var6 = var2.method8();
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               GuiType var7 = this.method9();
               GuiType var8 = var2.method9();
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  Map var9 = this.method12();
                  Map var10 = var2.method12();
                  return var9 == null ? var10 == null : var9.equals(var10);
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
   protected boolean canEqual(Object var1) {
      return var1 instanceof Nameplate2;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.method6();
      var2 = var2 * 59 + this.getX();
      var2 = var2 * 59 + this.method7();
      var2 = var2 * 59 + this.getY();
      var2 = var2 * 59 + (this.method10() ? 79 : 97);
      var2 = var2 * 59 + (this.method11() ? 79 : 97);
      Bridge5Extension6 var3 = this.method5();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      GuiType var4 = this.method8();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      GuiType var5 = this.method9();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      Map var6 = this.method12();
      return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
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
