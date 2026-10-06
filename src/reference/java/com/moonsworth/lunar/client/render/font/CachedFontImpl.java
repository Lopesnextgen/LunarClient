package com.moonsworth.lunar.client.render.font;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.moonsworth.lunar.client.ui.widget.ModernRenderStateScope;
import com.moonsworth.lunar.client.ui.widget.GuiRenderState;
import com.moonsworth.lunar.client.ui.widget.LegacyRenderStateScope;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class CachedFontImpl extends CachedFont {
   private static final char field11 = '§';
   private static final Pattern field12 = Pattern.compile("(?i)§[0-9A-FR]");
   private final ResourceLocationBridge field13;
   private boolean field14 = false;
   private final ResourceLocationBridge field15;
   private boolean field16 = false;
   private final ResourceLocationBridge field17;
   private boolean field18 = false;
   private final int[] colorCode = new int[32];
   protected CachedFont.Data[] field19 = new CachedFont.Data[256];
   protected CachedFont.Data[] field20 = new CachedFont.Data[256];
   protected CachedFont.Data[] field21 = new CachedFont.Data[256];
   private Function<Float, Integer> field22 = null;
   private boolean shadow = false;

   public CachedFontImpl(ResourceLocationBridge var1, float var2) {
      super(var1, var2);
      String var3 = var1.bridge$getPath();
      var3 = var3.substring(var3.indexOf(47) + 1, var3.indexOf(46));
      this.field13 = ResourceLocationBridge.create("lunar", var3 + "-cfont_bold-" + (int)var2);
      this.field15 = ResourceLocationBridge.create("lunar", var3 + "-cfont_italic-" + (int)var2);
      this.field17 = ResourceLocationBridge.create("lunar", var3 + "-cfont_italic_bold-" + (int)var2);
   }

   @Override
   public boolean isLoaded() {
      if (this.field7 != null) {
         return true;
      }

      super.isLoaded();
      this.method27();
      this.method24();
      return true;
   }

   public float method2(AbstractRenderContext var1, String var2, double var3, double var5, int var7, int var8) {
      float var9 = this.method9(var1, var2, var3 + 1.0, var5 + 1.0, var8, false);
      return Math.max(var9, this.method9(var1, var2, var3, var5, var7, false));
   }

   public float method3(AbstractRenderContext var1, String var2, double var3, double var5, int var7) {
      return this.method4(var1, var2, var3, var5, var7, false);
   }

   public float method4(AbstractRenderContext var1, String var2, double var3, double var5, int var7, boolean var8) {
      float var9 = this.method10(var1, var2, var3 + 1.0, var5 + 1.0, var7, true, var8);
      return Math.max(var9, this.method10(var1, var2, var3, var5, var7, false, var8));
   }

   public float method5(AbstractRenderContext var1, String var2, float var3, float var4, int var5) {
      return this.method9(var1, var2, var3, var4, var5, false);
   }

   public float method6(AbstractRenderContext var1, String var2, float var3, float var4, int var5) {
      return this.method5(var1, var2, var3 - this.method4(var2) / 2.0F, var4, var5);
   }

   public float method7(AbstractRenderContext var1, String var2, float var3, float var4, int var5) {
      return this.method5(var1, var2, var3 - this.method4(var2) / 2.0F, var4, var5);
   }

   public float method8(AbstractRenderContext var1, String var2, double var3, double var5, Function<Float, Integer> var7, boolean var8) {
      this.field22 = var7;
      int var9 = (Integer)var7.apply((float)(var3 + var5));
      if (var8) {
         this.shadow = true;
         int var10 = ThreadModuleDump23.method32(var9);
         this.method9(var1, var2, var3 + 1.0, var5 + 1.0, var10, false);
      }

      this.shadow = false;
      float var11 = this.method9(var1, var2, var3, var5, var9, false);
      this.field22 = null;
      return var11;
   }

   public float method9(AbstractRenderContext var1, String var2, double var3, double var5, int var7, boolean var8) {
      return this.method10(var1, var2, var3, var5, var7, var8, false);
   }

   public float method10(AbstractRenderContext var1, String var2, double var3, double var5, int var7, boolean var8, boolean var9) {
      GuiRenderState var10;
      if (var9) {
         var10 = new ModernRenderStateScope(var1.method42());
      } else {
         var10 = new LegacyRenderStateScope(var1);
      }

      return this.method18(var10, var2, var3, var5, var7, var8);
   }

   public float method11(MixinHelper_4 var1, String var2, double var3, double var5, int var7, int var8) {
      float var9 = this.method17(var1, var2, var3 + 1.0, var5 + 1.0, var8, false);
      return Math.max(var9, this.method17(var1, var2, var3, var5, var7, false));
   }

   public float method12(MixinHelper_4 var1, String var2, double var3, double var5, int var7) {
      float var8 = this.method17(var1, var2, var3 + 1.0, var5 + 1.0, var7, true);
      return Math.max(var8, this.method17(var1, var2, var3, var5, var7, false));
   }

   public float method13(MixinHelper_4 var1, String var2, float var3, float var4, int var5) {
      return this.method17(var1, var2, var3, var4, var5, false);
   }

   public float method14(MixinHelper_4 var1, String var2, float var3, float var4, int var5) {
      return this.method13(var1, var2, var3 - this.method4(var2) / 2.0F, var4, var5);
   }

   public float method15(MixinHelper_4 var1, String var2, float var3, float var4, int var5) {
      return this.method13(var1, var2, var3 - this.method4(var2) / 2.0F, var4, var5);
   }

   public float method16(MixinHelper_4 var1, String var2, double var3, double var5, Function<Float, Integer> var7, boolean var8) {
      this.field22 = var7;
      int var9 = (Integer)var7.apply((float)(var3 + var5));
      if (var8) {
         this.shadow = true;
         int var10 = ThreadModuleDump23.method32(var9);
         this.method17(var1, var2, var3 + 1.0, var5 + 1.0, var10, false);
      }

      this.shadow = false;
      float var11 = this.method17(var1, var2, var3, var5, var9, false);
      this.field22 = null;
      return var11;
   }

   public float method17(MixinHelper_4 var1, String var2, double var3, double var5, int var7, boolean var8) {
      ModernRenderStateScope var9 = new ModernRenderStateScope(var1);
      return this.method18(var9, var2, var3, var5, var7, var8);
   }

   public float method18(GuiRenderState var1, String var2, double var3, double var5, int var7, boolean var8) {
      var3 = LcuiScreen.method136(--var3);
      var5 = LcuiScreen.method136(var5);
      if (var2 == null) {
         return 0.0F;
      }

      if (var7 == 553648127) {
         var7 = 16777215;
      }

      if ((var7 & -67108864) == 0) {
         var7 |= -16777216;
      }

      if (var8) {
         var7 = (var7 & 16579836) >> 2 | var7 & 0xFF000000;
      }

      int var9 = var7 & 0xFF000000;
      double var10 = var3;
      this.rendered = new boolean[var2.length()];
      this.remaining = var2.length();
      var5 *= 2.0;
      var1.push();
      var1.method1(0.5F, 0.5F);
      ArrayList var12 = new ArrayList();
      HashMap var13 = new HashMap();

      while (this.remaining >= 0) {
         if (this.remaining == 0) {
            this.remaining = -1;
         }

         int var14 = var7 | var9;
         var3 = var10 * 2.0;
         int var15 = var2.length();
         ResourceLocationBridge var16 = this.field4;
         CachedFont.Data[] var17 = this.field6;
         boolean var18 = false;
         boolean var19 = false;
         boolean var20 = false;
         boolean var21 = false;
         boolean var22 = false;

         for (this.index = 0; this.index < var15; this.index++) {
            char var23 = var2.charAt(this.index);
            if (var23 == 167 && this.index < var15) {
               int var34 = 21;

               try {
                  var34 = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(var2.charAt(this.index + 1)));
               } catch (Exception var26) {
                  var26.printStackTrace();
               }

               if (var34 >= 16) {
                  if (var34 == 16) {
                     var18 = true;
                  } else if (var34 == 17) {
                     var19 = true;
                     if (var20) {
                        var16 = this.field18 ? this.field17 : var16;
                        var17 = this.field21;
                     } else {
                        var16 = this.field14 ? this.field13 : var16;
                        var17 = this.field19;
                     }
                  } else if (var34 == 18) {
                     var21 = true;
                  } else if (var34 == 19) {
                     var22 = true;
                  } else if (var34 == 20) {
                     var20 = true;
                     if (var19) {
                        var16 = this.field18 ? this.field17 : var16;
                        var17 = this.field21;
                     } else {
                        var16 = this.field16 ? this.field15 : var16;
                        var17 = this.field20;
                     }
                  } else if (var34 == 21) {
                     var19 = false;
                     var20 = false;
                     var18 = false;
                     var22 = false;
                     var21 = false;
                     var14 = var7 | var9;
                     var16 = this.field4;
                     var17 = this.field6;
                  }
               } else {
                  var19 = false;
                  var20 = false;
                  var18 = false;
                  var22 = false;
                  var21 = false;
                  var16 = this.field4;
                  var17 = this.field6;
                  if (var34 < 0 || var34 > 15) {
                     var34 = 15;
                  }

                  if (var8) {
                     var34 += 16;
                  }

                  var14 = this.colorCode[var34] | var9;
               }

               this.markRendered();
               this.index++;
               this.markRendered();
            } else if (var23 < var17.length && var23 >= 0) {
               int var24 = this.method20(var3, var5);
               if (var24 != -1) {
                  var14 = var24 | var9;
               }

               if (this.remaining == -1) {
                  if (var21) {
                     var12.add(new CachedFontImpl.Data(var3, var5, var17[var23], true, var14));
                  }

                  if (var22) {
                     var12.add(new CachedFontImpl.Data(var3, var5, var17[var23], false, var14));
                  }
               } else if (!this.rendered[this.index]) {
                  var13.computeIfAbsent(var16, var0 -> new ArrayList<>()).add(new CachedFontImpl.Data2((float)var3, (float)var5, var23, var14));
                  this.rendered[this.index] = true;
                  this.remaining--;
               }

               var3 += var17[var23].width - 8 + this.field10;
            } else {
               this.markRendered();
            }
         }
      }

      var1.method2(this, var13, this.field9);
      var1.method3(var12);
      var1.pop();
      return (float)(var10 + (var3 / 2.0 - var10));
   }

   protected CachedFont.Data[] method19(ResourceLocationBridge var1) {
      if (var1 == this.field4) {
         return this.field6;
      } else if (var1 == this.field13) {
         return this.field19;
      } else if (var1 == this.field15) {
         return this.field20;
      } else if (var1 == this.field17) {
         return this.field21;
      } else {
         throw new IllegalArgumentException("Unrecognized resource id: " + var1);
      }
   }

   private int method20(double var1, double var3) {
      if (this.field22 == null) {
         return -1;
      }

      int var5 = this.field22.apply((float)(var1 + var3));
      if (this.shadow) {
         var5 = ThreadModuleDump23.method32(var5);
      }

      return var5;
   }

   public String method21(String var1, double var2) {
      return this.method22(var1, var2, false);
   }

   public String method22(String var1, double var2, boolean var4) {
      StringBuilder var5 = new StringBuilder();
      float var6 = 0.0F;
      int var7 = var4 ? var1.length() - 1 : 0;
      int var8 = var4 ? -1 : 1;
      boolean var9 = false;
      boolean var10 = false;

      for (int var11 = var7; var11 >= 0 && var11 < var1.length() && var6 < (float)var2; var11 += var8) {
         char var12 = var1.charAt(var11);
         double var13 = this.method4(String.valueOf(var12));
         if (var9) {
            var9 = false;
            if (var12 == 'l' || var12 == 'L') {
               var10 = true;
            } else if (var12 == 'r' || var12 == 'R') {
               var10 = false;
            }
         } else if (var13 < 0.0) {
            var9 = true;
         } else {
            var6 = (float)(var6 + var13);
            if (var10) {
               var6++;
            }
         }

         if (var6 > (float)var2) {
            break;
         }

         if (var4) {
            var5.insert(0, var12);
         } else {
            var5.append(var12);
         }
      }

      return var5.toString();
   }

   @Override
   public float method4(String var1) {
      if (var1 == null) {
         return 0.0F;
      }

      int var2 = 0;
      CachedFont.Data[] var3 = this.field6;
      boolean var4 = false;
      boolean var5 = false;
      int var6 = var1.length();

      for (int var7 = 0; var7 < var6; var7++) {
         char var8 = var1.charAt(var7);
         if (var8 == 167 && var7 < var6) {
            int var10 = "0123456789abcdefklmnor".indexOf(var8);
            if (var10 < 16) {
               var4 = false;
               var5 = false;
            } else if (var10 == 17) {
               var4 = true;
               if (var5) {
                  var3 = this.field21;
               } else {
                  var3 = this.field19;
               }
            } else if (var10 == 20) {
               var5 = true;
               if (var4) {
                  var3 = this.field21;
               } else {
                  var3 = this.field20;
               }
            } else if (var10 == 21) {
               var4 = false;
               var5 = false;
               var3 = this.field6;
            }

            var7++;
         } else if (var8 < var3.length && var8 >= 0) {
            CachedFont.Data var9 = var3[var8];
            if (var9 != null) {
               var2 += var9.width - 8 + this.field10;
            }
         }
      }

      return var2 / 2.0F;
   }

   private void method24() {
      this.field14 = false;
      this.field16 = false;
      this.field18 = false;
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field13);
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field15);
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$deleteTexture(this.field17);
      Font var1 = this.field7.deriveFont(1);
      this.method12(this.field13, var1, this.antiAlias, this.field8, this.field19, false).thenAccept(var1x -> this.field14 = true);
      Font var2 = this.field7.deriveFont(2);
      this.method12(this.field15, var2, this.antiAlias, this.field8, this.field20, false).thenAccept(var1x -> this.field16 = true);
      Font var3 = this.field7.deriveFont(3);
      this.method12(this.field17, var3, this.antiAlias, this.field8, this.field21, false).thenAccept(var1x -> this.field18 = true);
   }

   public List<String> method25(String var1, double var2) {
      ArrayList var4 = new ArrayList();
      if (!(this.method4(var1) > var2) && !var1.contains("\n")) {
         var4.add(var1);
      } else {
         String[] var5 = var1.split("\n");
         String var6 = "";
         String var7 = "";

         for (String var12 : var5) {
            if (var12.isEmpty()) {
               var4.add(var12);
            }

            String[] var13 = var12.split(" ");

            for (String var17 : var13) {
               if (this.method4(var6 + var17 + " ") < var2) {
                  var6 = var6 + var17 + " ";
               } else {
                  var4.add(var6);
                  var6 = var7 + var17 + " ";
               }

               Matcher var18 = field12.matcher(var17);
               String var19 = "";

               while (var18.find()) {
                  var19 = var18.group();
               }

               if (!var19.isEmpty()) {
                  var7 = var19;
               }
            }

            float var8;
            if (!var6.isEmpty() && (var8 = this.method4(var6)) > 0.0F) {
               if (var8 < var2) {
                  var4.add(var6);
               } else {
                  var4.addAll(this.method26(var6, var2));
               }

               var6 = "";
            }
         }
      }

      return var4;
   }

   public List<String> method26(String var1, double var2) {
      ArrayList var4 = new ArrayList();
      StringBuilder var5 = new StringBuilder();
      char var6 = 'F';
      char[] var7 = var1.toCharArray();

      for (int var8 = 0; var8 < var7.length; var8++) {
         char var9 = var7[var8];
         if (var9 == 167 && var8 < var7.length - 1) {
            var6 = var7[var8 + 1];
         }

         if (this.method4(var5.toString() + var9) < var2) {
            var5.append(var9);
         } else {
            var4.add(var5.toString());
            var5 = new StringBuilder("§" + var6 + var9);
         }
      }

      if (!var5.isEmpty()) {
         var4.add(var5.toString());
      }

      return var4;
   }

   private void method27() {
      for (int var1 = 0; var1 < 32; var1++) {
         int var2 = (var1 >> 3 & 1) * 85;
         int var3 = (var1 >> 2 & 1) * 170 + var2;
         int var4 = (var1 >> 1 & 1) * 170 + var2;
         int var5 = (var1 & 1) * 170 + var2;
         if (var1 == 6) {
            var3 += 85;
         }

         if (var1 >= 16) {
            var3 /= 4;
            var4 /= 4;
            var5 /= 4;
         }

         this.colorCode[var1] = (var3 & 0xFF) << 16 | (var4 & 0xFF) << 8 | var5 & 0xFF;
      }
   }

   public class Data {
      private final double field1;
      private final double field2;
      private final CachedFont.Data field3;
      private final boolean field4;
      private final int field5;

      public Data(double var1, double var3, CachedFont.Data var5, boolean var6, int var7) {
         this.field1 = var1;
         this.field2 = var3;
         this.field3 = var5;
         this.field4 = var6;
         this.field5 = var7;
      }

      public double method1() {
         return this.field1;
      }

      public double method2() {
         return this.field2;
      }

      public CachedFont.Data method3() {
         return this.field3;
      }

      public boolean method4() {
         return this.field4;
      }

      public int method5() {
         return this.field5;
      }
   }

   public class Data2 {
      private final float field1;
      private final float field2;
      private final char field3;
      private final int field4;

      public Data2(float var1, float var2, char var3, int var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public float x() {
         return this.field1;
      }

      public float y() {
         return this.field2;
      }

      public char character() {
         return this.field3;
      }

      public int method1() {
         return this.field4;
      }
   }
}
