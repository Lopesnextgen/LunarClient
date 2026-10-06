package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.ui.hud.MovableHudElement;
import com.moonsworth.lunar.client.ui.hud.HudPlacement;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import java.util.Objects;
import org.jspecify.annotations.Nullable;

public class HudEditor {
   private final MovableHudElement field1;
   private final HudEditor.Type field2;
   private final HudEditor.@Nullable Data3 field3;

   public HudEditor(MovableHudElement var1, HudEditor.Type var2, HudEditor.@Nullable Data3 var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public static HudEditor method1(MovableHudElement var0, HudEditor.Type var1) {
      return new HudEditor(var0, var1, null);
   }

   public HudEditor method2(HudEditor.Type var1, HudEditor.Data3 var2) {
      return new HudEditor(this.field1, var1, var2);
   }

   public boolean method3() {
      return this.field3 != null;
   }

   public float method4(float var1, float var2) {
      return Objects.requireNonNull(this.field3, "handle not grabbed").method3(var1, var2);
   }

   public MovableHudElement method5() {
      return this.field1;
   }

   public HudEditor.Type method6() {
      return this.field2;
   }

   public HudEditor.@Nullable Data3 method7() {
      return this.field3;
   }

   public class Data3 {
      private final float field1;
      private final float field2;
      private final float field3;
      private final float field4;
      public static final float field5 = 0.5F;

      public Data3(float var1, float var2, float var3, float var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public static HudEditor.Data3 method1(MovableHudElement var0, HudEditor.Type var1) {
         float var2 = var0.method10();
         float var3 = var0.method11();
         float var4 = var0.getScale();
         return new HudEditor.Data3(var2, var3, method4(var1.x(var0) - var2, var4), method4(var1.y(var0) - var3, var4));
      }

      public boolean method2() {
         return this.field3 != 0.0F || this.field4 != 0.0F;
      }

      public float method3(float var1, float var2) {
         float var3 = this.field3 == 0.0F ? Float.NEGATIVE_INFINITY : (var1 - this.field1) / this.field3;
         float var4 = this.field4 == 0.0F ? Float.NEGATIVE_INFINITY : (var2 - this.field2) / this.field4;
         return Math.max(var3, var4);
      }

      private static float method4(float var0, float var1) {
         float var2 = var0 / var1;
         return Math.abs(var2) < 0.5F ? 0.0F : var2;
      }

      public float method5() {
         return this.field1;
      }

      public float method6() {
         return this.field2;
      }

      public float method7() {
         return this.field3;
      }

      public float method8() {
         return this.field4;
      }
   }

   public enum Type {
      TOP_LEFT,
      TOP_RIGHT,
      BOTTOM_LEFT,
      BOTTOM_RIGHT;

      public static HudEditor.Type fromHudPosition(HudAnchor var0) {
         HudPlacement var1 = var0.getHorizontal();
         return var1 == HudPlacement.LEFT
            ? (var0.getVertical() == HudPlacement.BOTTOM ? TOP_RIGHT : BOTTOM_RIGHT)
            : (var0.getVertical() == HudPlacement.TOP ? BOTTOM_LEFT : TOP_LEFT);
      }

      public static HudEditor.Type of(boolean var0, boolean var1) {
         if (var0) {
            return var1 ? TOP_LEFT : TOP_RIGHT;
         } else {
            return var1 ? BOTTOM_LEFT : BOTTOM_RIGHT;
         }
      }

      public boolean top() {
         return this == TOP_LEFT || this == TOP_RIGHT;
      }

      public boolean left() {
         return this == TOP_LEFT || this == BOTTOM_LEFT;
      }

      public float x(MovableHudElement var1) {
         return var1.method4() + (this.left() ? 0.0F : var1.method12());
      }

      public float y(MovableHudElement var1) {
         return var1.method5() + (this.top() ? 0.0F : var1.method13());
      }
   }
}
