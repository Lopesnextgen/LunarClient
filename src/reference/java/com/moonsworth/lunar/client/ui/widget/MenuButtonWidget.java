package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.SystemClipboardBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.widget.EditState;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump49;
import com.moonsworth.lunar.client.util.ThreadModuleDump58;
import com.moonsworth.lunar.client.util.ThreadModuleDump49.Type;
import java.util.function.Function;
import lombok.Generated;
import org.apache.commons.lang3.StringUtils;

public abstract class MenuButtonWidget extends GuiWidget implements EditState {
   private final CachedFontImpl field16;
   private String text = "";
   private final String field17;
   private int color;
   private int field18;
   private int field19;
   private int field20 = 256;
   private int field21;
   private int field22;
   private int field23;
   private int field24;
   private boolean field25;
   private boolean field26 = true;
   private boolean visible = true;
   private boolean field27 = true;
   private boolean field28 = true;
   private boolean isEnabled = true;
   private boolean field29 = false;
   private Function<String, String> field30 = var0 -> var0;
   private boolean field31;
   private Runnable field32;
   private Runnable field33;
   public Runnable field34;
   private ResourceLocationBridge field35 = null;

   public MenuButtonWidget(GuiWidget var1, ResourceLocationBridge var2, CachedFontImpl var3, String var4, int var5, int var6) {
      this(var1, var3, var4, var5, var6);
      this.field35 = var2;
   }

   public MenuButtonWidget(GuiWidget var1, CachedFontImpl var2, String var3, int var4, int var5) {
      super(var1);
      this.field16 = var2;
      this.field17 = var3;
      this.color = var4;
      this.field19 = 0;
      this.field18 = var5;
      this.method4((var1x, var2x) -> {
         boolean var3x = this.method3(var1x);
         if (this.field26 && var2x == 1 && var3x) {
            this.setText("");
         }

         if (this.field28) {
            this.method17(var3x);
         }

         if (this.field31 && var2x == 0) {
            float var4x = var1x.method12() - this.x;
            if (this.field27) {
               var4x -= 4.0F;
            }

            if (this.field35 != null) {
               var4x -= 12.0F;
            }

            String var5x = this.field16.method21(this.text.substring(this.field23), this.method19());
            this.method10(this.field16.method21(var5x, var4x).length() + this.field23);
         }

         return true;
      });
      this.method3((var1x, var2x) -> {
         if (this.method18()) {
            this.method17(false);
         }

         return false;
      });
   }

   public void method1() {
      this.field24++;
   }

   public void setText(String var1) {
      if (var1.length() > this.field20) {
         this.text = var1.substring(0, this.field20);
      } else {
         this.text = var1;
      }

      this.method15();
   }

   public String getText() {
      return this.text;
   }

   public String method2() {
      int var1 = Math.min(this.field21, this.field22);
      int var2 = Math.max(this.field21, this.field22);
      return this.text.substring(var1, var2);
   }

   public void method3(String var1) {
      String var2 = "";
      String var3 = ThreadModuleDump58.method2(var1);
      var3 = this.field30.apply(var3);
      int var4 = Math.min(this.field21, this.field22);
      int var5 = Math.max(this.field21, this.field22);
      int var6 = this.field20 - this.text.length() - (var4 - this.field22);
      if (this.text.length() > 0) {
         var2 = var2 + this.text.substring(0, var4);
      }

      int var7;
      if (var6 < var3.length()) {
         var2 = var2 + var3.substring(0, var6);
         var7 = var6;
      } else {
         var2 = var2 + var3;
         var7 = var3.length();
      }

      if (this.text.length() > 0 && var5 <= this.text.length()) {
         var2 = var2 + this.text.substring(var5);
      }

      this.text = var2;
      this.method9(var4 - this.field22 + var7);
   }

   public void method4(int var1) {
      if (!this.text.isEmpty()) {
         if (this.field22 != this.field21) {
            this.method3("");
         } else {
            this.method6(this.method7(var1) - this.field21);
         }
      }
   }

   public void method6(int var1) {
      if (!this.text.isEmpty()) {
         if (this.field22 != this.field21) {
            this.method3("");
         } else {
            boolean var2 = var1 < 0;
            int var3 = var2 ? this.field21 + var1 : this.field21;
            int var4 = var2 ? this.field21 : this.field21 + var1;
            String var5 = "";
            if (var3 >= 0) {
               var5 = this.text.substring(0, var3);
            }

            if (var4 < this.text.length()) {
               var5 = var5 + this.text.substring(var4);
            }

            this.text = var5;
            if (var2) {
               this.method9(var1);
            }
         }
      }
   }

   public int method7(int var1) {
      return this.method7(var1, this.method29());
   }

   public int method7(int var1, int var2) {
      return this.method8(var1, var2, true);
   }

   public int method8(int var1, int var2, boolean var3) {
      boolean var4 = var1 < 0;
      int var5 = Math.abs(var1);

      for (int var6 = 0; var6 < var5; var6++) {
         if (!var4) {
            int var7 = this.text.length();
            var2 = this.text.indexOf(32, var2);
            if (var2 == -1) {
               var2 = var7;
            } else {
               while (var3 && var2 < var7 && this.text.charAt(var2) == ' ') {
                  var2++;
               }
            }
         } else {
            while (var3 && var2 > 0 && this.text.charAt(var2 - 1) == ' ') {
               var2--;
            }

            while (var2 > 0 && this.text.charAt(var2 - 1) != ' ') {
               var2--;
            }
         }
      }

      return var2;
   }

   public void method9(int var1) {
      this.method10(this.field22 + var1);
   }

   public void method10(int var1) {
      this.field21 = var1;
      int var2 = this.text.length();
      if (this.field21 < 0) {
         this.field21 = 0;
      }

      if (this.field21 > var2) {
         this.field21 = var2;
      }

      this.method20(this.field21);
      if (this.field34 != null) {
         this.field34.run();
      }
   }

   public void method14() {
      this.method10(0);
   }

   public void method15() {
      this.method10(this.text.length());
   }

   public boolean method13(char var1, KeyCode var2) {
      if (!this.field31) {
         return false;
      }

      SystemClipboardBridge var3 = Bridge.method47();
      switch (var1) {
         case '\u0001':
            this.method15();
            this.method20(0);
            return true;
         case '\u0003':
            var3.method2(this.method2());
            return true;
         case '\u0016':
            if (this.isEnabled) {
               this.method3(var3.method1());
            }

            return true;
         case '\u0018':
            var3.method2(this.method2());
            if (this.isEnabled) {
               this.method3("");
            }

            return true;
         default:
            switch (var2) {
               case KEY_BACK:
                  if (LcuiScreen.isCtrlKeyDown()) {
                     if (this.isEnabled) {
                        this.method4(-1);
                     }
                  } else if (this.isEnabled) {
                     this.method6(-1);
                  }

                  return true;
               case KEY_HOME:
                  if (Bridge.method18().method1(KeyCode.KEY_LSHIFT)) {
                     this.method20(0);
                  } else {
                     this.method14();
                  }

                  return true;
               case KEY_LEFT:
                  if (Bridge.method18().method1(KeyCode.KEY_LSHIFT)) {
                     if (LcuiScreen.isCtrlKeyDown()) {
                        this.method20(this.method7(-1, this.method30()));
                     } else {
                        this.method20(this.method30() - 1);
                     }
                  } else if (LcuiScreen.isCtrlKeyDown()) {
                     this.method10(this.method7(-1));
                  } else {
                     this.method9(-1);
                  }

                  return true;
               case KEY_RIGHT:
                  if (Bridge.method18().method1(KeyCode.KEY_LSHIFT)) {
                     if (LcuiScreen.isCtrlKeyDown()) {
                        this.method20(this.method7(1, this.method30()));
                     } else {
                        this.method20(this.method30() + 1);
                     }
                  } else if (LcuiScreen.isCtrlKeyDown()) {
                     this.method10(this.method7(1));
                  } else {
                     this.method9(1);
                  }

                  return true;
               case KEY_END:
                  if (Bridge.method18().method1(KeyCode.KEY_LSHIFT)) {
                     this.method20(this.text.length());
                  } else {
                     this.method15();
                  }

                  return true;
               case KEY_DELETE:
                  if (LcuiScreen.isCtrlKeyDown()) {
                     if (this.isEnabled) {
                        this.method4(1);
                     }
                  } else if (this.isEnabled) {
                     this.method6(1);
                  }

                  return true;
               case KEY_RETURN:
                  if (this.field33 != null) {
                     this.field33.run();
                  }

                  return true;
               default:
                  boolean var4 = Bridge.method18().method1(KeyCode.KEY_LMENU) || Bridge.method18().method1(KeyCode.KEY_RMENU);
                  if (LcuiScreen.isCtrlKeyDown() && !var4 && !LcuiScreen.isShiftKeyDown()) {
                     switch (var2) {
                        case KEY_A:
                           this.method15();
                           this.method20(0);
                           return true;
                        case KEY_C:
                           var3.method2(this.method2());
                           return true;
                        case KEY_X:
                           var3.method2(this.method2());
                           if (this.isEnabled) {
                              this.method3("");
                           }

                           return true;
                        case KEY_V:
                           if (this.isEnabled) {
                              this.method3(var3.method1());
                           }

                           return true;
                     }
                  }

                  String var5 = String.valueOf(var1);
                  String var6 = this.field30.apply(var5);
                  if (!var6.equals(var5) && !ThreadModuleDump58.method1(var1)) {
                     return false;
                  } else {
                     if (this.isEnabled) {
                        this.method3(Character.toString(var1));
                     }

                     return true;
                  }
            }
      }
   }

   public abstract void method14(MixinHelper_4 var1);

   public void method15(MixinHelper_4 var1) {
      if (this.isVisible()) {
         if (this.method36()) {
            this.method14(var1);
         }

         int var2 = this.field21 - this.field23;
         int var3 = this.field22 - this.field23;
         String var4 = this.field16.method21(this.text.substring(this.field23), this.method19());
         boolean var5 = var2 >= 0 && var2 <= var4.length();
         boolean var6 = this.field31 && this.field24 / 6 % 2 == 0 && var5;
         float var7 = this.field27 ? this.x + 4.0F + (this.field35 != null ? 12 : 0) : this.x;
         float var8 = this.field27 ? this.y + (this.height - 8.0F) / 2.0F : this.y;
         float var9 = var7;
         if (var3 > var4.length()) {
            var3 = var4.length();
         }

         if (this.field35 != null) {
            LcuiScreen.method39(var1, this.field35, 2.75F, this.x + 7.0F, this.y + 4.5F, 872415231);
         }

         if (var4.length() > 0) {
            String var10 = var5 ? var4.substring(0, var2) : var4;
            var9 = this.field16.method13(var1, this.field29 ? StringUtils.repeat('*', var10.length()) : var10, var7, var8, -1862270977);
         } else if (!this.method18()) {
            this.field16.method13(var1, this.method1(this.field17, new Object[0]), var7, var8, 822083583);
         }

         boolean var13 = this.field21 < this.text.length() || this.text.length() >= this.getMaxStringLength();
         float var11 = var9;
         if (!var5) {
            var11 = var2 > 0 ? var7 + this.width - 8.0F : var7;
         } else if (var13) {
            var11 = var9 - 1.0F;
            var9--;
         }

         if (var4.length() > 0 && var5 && var2 < var4.length()) {
            String var12 = var4.substring(var2);
            this.field16.method13(var1, this.field29 ? StringUtils.repeat('*', var12.length()) : var12, var9 + 2.0F, var8, -1862270977);
         }

         if (var6) {
            if (var13) {
               LcuiScreen.method66(var1, var11 + 1.5F, var8 + 1.0F, var11 + 2.0F, var8 + 3.0F + this.field16.getHeight(), -3092272);
            } else {
               this.field16.method13(var1, "_", var11, var8, -1862270977);
            }
         }

         if (var3 != var2) {
            float var14 = var7 + this.field16.method4(var4.substring(0, var3));
            this.method16(var1, var11, var8 - 1.0F + 2.0F, var14 - 1.0F, var8 + 1.0F + this.field16.getHeight() + 2.0F);
         }
      }
   }

   private void method16(MixinHelper_4 var1, float var2, float var3, float var4, float var5) {
      if (var2 < var4) {
         float var6 = var2;
         var2 = var4;
         var4 = var6;
      }

      if (var3 < var5) {
         float var7 = var3;
         var3 = var5;
         var5 = var7;
      }

      if (var4 > this.x + this.width) {
         var4 = this.x + this.width;
      }

      if (var2 > this.x + this.width) {
         var2 = this.x + this.width;
      }

      LcuiScreen.method98(var1, var2, var3, var4 - var2, var5 - var3, -16776961);
   }

   public void method17(boolean var1) {
      if (var1 && !this.field31) {
         this.field24 = 0;
      }

      if (var1 && this.field32 != null) {
         this.field32.run();
      }

      this.field31 = var1;
   }

   public boolean method18() {
      return this.field31;
   }

   public float method19() {
      return this.method36() ? this.width - 8.0F - (this.field35 != null ? 12 : 0) : this.width - 6.0F - (this.field35 != null ? 18 : 0);
   }

   public void method20(int var1) {
      int var2 = this.text.length();
      if (var1 > var2) {
         var1 = var2;
      }

      if (var1 < 0) {
         var1 = 0;
      }

      this.field22 = var1;
      if (this.field16 != null) {
         if (this.field23 > var2) {
            this.field23 = var2;
         }

         float var3 = this.method19();
         String var4 = this.field16.method21(this.text.substring(this.field23), var3);
         int var5 = var4.length() + this.field23;
         if (var1 == this.field23) {
            this.field23 = this.field23 - this.field16.method22(this.text, var3, true).length();
         }

         if (var1 > var5) {
            this.field23 += var1 - var5;
         } else if (var1 <= this.field23) {
            this.field23 = this.field23 - (this.field23 - var1);
         }

         if (this.field23 < 0) {
            this.field23 = 0;
         }

         if (this.field23 > var2) {
            this.field23 = var2;
         }
      }
   }

   @Override
   public void update() {
      this.method1();
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      this.method15(var1);
      if (this.method3(var2)) {
         ThreadModuleDump49.method2(Type.IBEAM);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (var2 == KeyCode.KEY_ESCAPE) {
         this.method17(false);
      }

      this.method13(var1, var2);
   }

   @Override
   public void close() {
   }

   @Override
   public boolean isEditing() {
      return this.field31;
   }

   @Generated
   public void setColor(int var1) {
      this.color = var1;
   }

   @Generated
   public void method24(int var1) {
      this.field18 = var1;
   }

   @Generated
   public void method25(int var1) {
      this.field19 = var1;
   }

   @Generated
   public int getColor() {
      return this.color;
   }

   @Generated
   public int method26() {
      return this.field18;
   }

   @Generated
   public int method27() {
      return this.field19;
   }

   @Generated
   public void method28(int var1) {
      this.field20 = var1;
   }

   @Generated
   public int getMaxStringLength() {
      return this.field20;
   }

   @Generated
   public int method29() {
      return this.field21;
   }

   @Generated
   public int method30() {
      return this.field22;
   }

   @Generated
   public void method31(int var1) {
      this.field23 = var1;
   }

   @Generated
   public boolean method34() {
      return this.field25;
   }

   @Generated
   public void method33(boolean var1) {
      this.field25 = var1;
   }

   @Generated
   public boolean method35() {
      return this.field26;
   }

   @Generated
   public void method36(boolean var1) {
      this.field26 = var1;
   }

   @Generated
   public boolean isVisible() {
      return this.visible;
   }

   @Generated
   public void setVisible(boolean var1) {
      this.visible = var1;
   }

   @Generated
   public boolean method36() {
      return this.field27;
   }

   @Generated
   public boolean method37() {
      return this.field28;
   }

   @Generated
   public void setEnabled(boolean var1) {
      this.isEnabled = var1;
   }

   @Generated
   public void method38(boolean var1) {
      this.field29 = var1;
   }

   @Generated
   public void method39(Function<String, String> var1) {
      this.field30 = var1;
   }

   @Generated
   public void method40(Runnable var1) {
      this.field32 = var1;
   }

   @Generated
   public void method41(Runnable var1) {
      this.field33 = var1;
   }
}
