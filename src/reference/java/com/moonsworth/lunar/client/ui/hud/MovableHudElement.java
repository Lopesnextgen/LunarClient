package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.ui.hud.HudAnchor;

public interface MovableHudElement {
   String id();

   void method1(float var1, float var2);

   float method2();

   float method3();

   float method4();

   float method5();

   float getScale();

   void setScale(float var1);

   float method6();

   float method7();

   HudAnchor method8();

   void method9(HudAnchor var1);

   float getWidth();

   float getHeight();

   default float method10() {
      return (float)HudAnchor.scalePivotX(this.method8(), this.method4(), this.getWidth(), this.getScale());
   }

   default float method11() {
      return (float)HudAnchor.scalePivotY(this.method8(), this.method5(), this.getHeight(), this.getScale());
   }

   default float method12() {
      return this.getWidth() * this.getScale();
   }

   default float method13() {
      return this.getHeight() * this.getScale();
   }

   boolean method14();

   float[] method15();

   default boolean method16() {
      return true;
   }

   default boolean method17() {
      return true;
   }

   default boolean isEnabled() {
      return true;
   }

   default void setEnabled(boolean var1) {
   }
}
