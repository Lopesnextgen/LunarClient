package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.crosshair.Crosshair2;
import com.moonsworth.lunar.client.framework.feature.crosshair.crosshairelytra.Crosshairelytra;
import com.moonsworth.lunar.client.framework.feature.crosshair.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.config.option.CrosshairDrawOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.render.crosshair.CrosshairStyle;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class CrosshairPreviewWidget extends com.moonsworth.lunar.client.ui.widget.OptionWidget<CrosshairDrawOption> {
   private static final ResourceLocationBridge field16 = ResourceLocationBridge.create("lunar", "icons/share-24.png");
   private static final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "icons/reset-settings-24x24.png");
   private static final ResourceLocationBridge field18 = ResourceLocationBridge.create("lunar", "icons/load-24.png");
   private static final CachedFontImpl field19 = FontRegistry.field8;
   private static final CachedFontImpl field20 = FontRegistry.field14;
   private final AnimatedValue field21 = new AnimatedValue(553648127, 1174405119);
   private final AnimatedValue field22 = new AnimatedValue(553648127, 1174405119);
   private final AnimatedValue field23 = new AnimatedValue(553648127, 1174405119);
   private final AnimatedValue field24 = new AnimatedValue(553648127, 1174405119);
   private boolean field25 = false;
   private float field26;
   private float field27;
   private int field28 = -1;
   private boolean field29 = false;
   private CrosshairPatternType field30 = CrosshairPatternType.NONE;
   private boolean field31 = false;
   private boolean field32 = true;
   private final Stack<List<CrosshairPattern>> field33 = new Stack<>();
   private final Stack<List<CrosshairPattern>> field34 = new Stack<>();
   private List<CrosshairPattern> field35 = null;
   private Gui2Extension2 field36 = null;

   public CrosshairPreviewWidget(CrosshairDrawOption var1, GuiWidget var2) {
      super(var1, var2);
      this.method4((var2x, var3) -> {
         float var4 = var2x.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
         float var5 = var2x.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
         this.field25 = true;
         this.field26 = var4;
         this.field27 = var5;
         float var6 = this.getOption().method9();
         float var7 = 16.0F + (this.height - 87.0F) * var6;
         float var8 = 16.0F + 122.5F * var6;
         float var9 = this.x;
         boolean var10 = var4 > var9 && var4 < var9 + var7 && var5 > this.y + 3.0F && var5 < this.y + 3.0F + var8;
         if (var10) {
            this.getOption().method10();
         } else {
            float var11 = this.x + this.width - 272.0F;
            float var12 = this.y + 7.0F + var8 - (var6 == 0.0F ? 19 : 0);
            if (var5 >= var12 && var5 <= var12 + 15.0F) {
               CrosshairStyle var13 = var1.method11();
               if (var4 >= var11 && var4 <= var11 + 15.0F) {
                  var13.method12(Crosshair2.method5(var13.method14()));
                  this.method8();
               } else if (var4 >= var11 + 18.0F && var4 <= var11 + 31.0F) {
                  var13.method23();
               } else if (var4 >= var11 + 36.0F && var4 <= var11 + 47.0F && this.field29) {
                  var1.method11().method24();
                  this.method8();
               }
            }
         }

         return true;
      });
   }

   @Override
   public void update() {
      if (this.field31) {
         this.field31 = false;
         String var1 = ThreadModuleDump68.getClipboardString().trim();
         this.field29 = var1.startsWith("LCCH-");
      }
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
      float var5 = var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
      this.field31 = true;
      String var6 = null;
      Crosshair2 var7 = this.option.method11().method35();
      if (var7.method12() != this.field36) {
         this.method8();
         this.field36 = var7.method12();
      }

      int var8 = var7.method12().size();
      float var9 = (this.height - 5.5F) / var8;
      int var10 = (int)Math.floor((var4 - (this.x + (this.width - (this.height - 6.0F)) / 2.0F + 20.0F)) / var9);
      int var11 = (int)Math.floor((var5 - this.y - 4.0F) / var9);
      boolean var12 = Bridge.method20().method1(0);
      boolean var13 = Bridge.method20().method1(1);
      if ((var12 || var13) && LcuiScreen.isShiftKeyDown()) {
         int var14 = (int)Math.floor((this.field26 - (this.x + (this.width - (this.height - 6.0F)) / 2.0F + 20.0F)) / var9);
         int var15 = (int)Math.floor((this.field27 - this.y - 4.0F) / var9);
         if (this.field28 == -1) {
            if (var14 != var10) {
               this.field28 = 1;
            } else if (var15 != var11) {
               this.field28 = 0;
            }
         }

         if (this.field28 == 0) {
            var4 = this.field26;
            var10 = var14;
         } else if (this.field28 == 1) {
            var5 = this.field27;
            var11 = var15;
         }
      } else if (!var12 && !var13 || !LcuiScreen.isShiftKeyDown()) {
         this.field28 = -1;
      }

      int[] var40 = new int[]{-1, -1, -1, -1};
      if (var10 >= 0 && var10 < var8 && var11 >= 0 && var11 < var8) {
         int var41 = 0;
         var40[var41++] = var10 + var11 * var8;
         boolean var16 = this.field30 == CrosshairPatternType.QUADRANT;
         if (this.field30 == CrosshairPatternType.HORIZONTAL || var16) {
            var40[var41++] = var8 - 1 - var10 + var11 * var8;
         }

         if (this.field30 == CrosshairPatternType.VERTICAL || var16) {
            var40[var41++] = var10 + (var8 - 1 - var11) * var8;
         }

         if (var16) {
            var40[var41] = var8 - 1 - var10 + (var8 - 1 - var11) * var8;
         }
      }

      if (this.field25) {
         if (!var12 && !var13) {
            this.field25 = false;
            this.method6(this.field33, this.field35);
            this.field35 = null;
         } else {
            if (this.field35 == null) {
               this.field35 = new ArrayList<>();
            }

            boolean var43 = false;

            for (int var19 : var40) {
               if (var19 != -1) {
                  boolean var20 = var7.method13()[var19];
                  if (var20 != var12) {
                     var7.method13()[var19] = var12;
                     if (this.field35 != null) {
                        this.field35.add(new CrosshairPattern(var19, var20, var12));
                     }

                     var43 = true;
                  }
               }
            }

            if (var43) {
               this.option.method11().method30().reload();
            }
         }
      }

      float var44 = this.getOption().method9();
      float var46 = 16.0F + (this.height - 87.0F) * var44;
      float var47 = 16.0F + 122.5F * var44;
      float var48 = this.x;
      boolean var49 = var4 > var48 && var4 < var48 + var46 && var5 > this.y + 3.0F && var5 < this.y + 3.0F + var47;
      LcuiScreen.method117(var1, var48, this.y + 3.0F, var46, var47, 5.0F, this.field21.method2(var49));
      LcuiScreen.method56(var1, var48, this.y + 3.0F, var46, var47, 5.0F, 1076176165);
      if (var44 > 0.1F) {
         String var50 = this.method1("help", new Object[0]).toUpperCase(Locale.ROOT).replace("", " ").trim();
         field19.method14(var1, var50, var48 + var46 / 2.0F, this.y + 5.0F, -4275267);
         if (var44 > 0.9F) {
            field20.method13(var1, this.method1("description1", new Object[0]), this.x + 5.0F, this.y + 14.0F, -4275267);
            field20.method13(var1, this.method1("description2", new Object[0]), this.x + 5.0F, this.y + 22.0F, -4275267);
            this.method2(var1, this.method1("mouse1", new Object[0]), this.x + 5.0F, this.y + 34.0F);
            field20.method13(var1, this.method1("clickToSet", new Object[0]), this.x + 40.0F, this.y + 34.0F, -4275267);
            this.method2(var1, this.method1("mouse2", new Object[0]), this.x + 5.0F, this.y + 45.0F);
            field20.method13(var1, this.method1("clickToErase", new Object[0]), this.x + 40.0F, this.y + 45.0F, -4275267);
            this.method2(var1, this.method1("del", new Object[0]), this.x + 5.0F, this.y + 56.0F);
            field20.method13(var1, this.method1("clearAll", new Object[0]), this.x + 40.0F, this.y + 56.0F, -4275267);
            this.method2(var1, this.method1("tab", new Object[0]), this.x + 5.0F, this.y + 67.0F);
            field20.method13(var1, this.method1("showPreview", new Object[0]), this.x + 40.0F, this.y + 67.0F, -4275267);
            this.method2(var1, this.method1("s", new Object[0]), this.x + 5.0F, this.y + 78.0F);
            field20.method13(var1, this.method1("toggleGuides", new Object[0]), this.x + 40.0F, this.y + 78.0F, -4275267);
            this.method2(var1, this.method1("shift", new Object[0]), this.x + 5.0F, this.y + 89.0F);
            field20.method13(var1, this.method1("lockMode", new Object[0]), this.x + 40.0F, this.y + 89.0F, -4275267);
            this.method2(var1, this.method1("z", new Object[0]), this.x + 5.0F, this.y + 100.0F);
            field20.method13(var1, this.method1("undo", new Object[0]), this.x + 40.0F, this.y + 100.0F, -4275267);
            this.method2(var1, this.method1("y", new Object[0]), this.x + 5.0F, this.y + 111.0F);
            field20.method13(var1, this.method1("redo", new Object[0]), this.x + 40.0F, this.y + 111.0F, -4275267);
            this.method2(var1, this.method1("w", new Object[0]), this.x + 5.0F, this.y + 122.0F);
            String var21 = this.method1("mirrorMode", new Object[0]);
            String var22 = "(" + this.method1(this.field30.id, new Object[0]) + ")";
            float var23 = field20.method4(var21) - field20.method4(var22);
            field20.method13(var1, var21, this.x + 40.0F, this.y + 122.0F, -4275267);
            field20.method13(var1, var22, this.x + 40.0F + var23 / 2.0F - 1.0F, this.y + 122.0F + 9.0F, -9966615);
         }
      } else {
         FontRegistry.field12.method14(var1, "?", var48 + var46 / 2.0F, this.y + 5.5F, -4275267);
      }

      float var51 = this.y + 7.0F + var47 - (var44 == 0.0F ? 19 : 0);
      float var52 = 16.0F;
      boolean var53 = var5 >= var51 && var5 <= var51 + var52 - 1.0F;
      float var54 = this.x + this.width - 272.0F;
      boolean var24 = var53 && var4 >= var54 && var4 <= var54 + 15.0F;
      if (var24) {
         var6 = this.method1("resetDescription", new Object[0]);
      }

      LcuiScreen.method117(var1, var54, var51, var52, var52, 5.0F, this.field22.method2(var24));
      LcuiScreen.method56(var1, var54, var51, var52, var52, 5.0F, 1076176165);
      LcuiScreen.method31(var1, field17, var54 + 2.0F, var51 + 2.0F, 12.0F, 12.0F, -4275267);
      float var25 = var54 + var52 + 2.0F;
      boolean var26 = var53 && var4 >= var25 && var4 <= var25 + 15.0F;
      if (var26) {
         var6 = this.method1("saveDescription", new Object[0]);
      }

      LcuiScreen.method117(var1, var25, var51, var52, var52, 5.0F, this.field23.method2(var26));
      LcuiScreen.method56(var1, var25, var51, var52, var52, 5.0F, 1076176165);
      LcuiScreen.method31(var1, field16, var25 + 2.0F, var51 + 2.0F, 12.0F, 12.0F, -4275267);
      float var27 = var25 + var52 + 2.0F;
      boolean var28 = var53 && var4 >= var27 && var4 <= var27 + 15.0F;
      if (var28) {
         var6 = this.method1("loadDescription", new Object[0]);
      }

      LcuiScreen.method117(var1, var27, var51, var52, var52, 5.0F, this.field29 ? this.field24.method2(var28) : 536870912);
      if (this.field29) {
         LcuiScreen.method56(var1, var27, var51, var52, var52, 5.0F, 1076176165);
      }

      LcuiScreen.method31(var1, field18, var27 + 2.0F, var51 + 2.0F, 12.0F, 12.0F, this.field29 ? -4275267 : -10591395);
      float var29 = this.x + this.width / 2.0F - (this.height - 6.0F) / 2.0F + 20.0F;
      LcuiScreen.method117(var1, var29, this.y + 3.0F, this.height - 5.5F, this.height - 5.5F, 5.0F, 553648127);

      for (int var30 = 0; var30 < var8; var30++) {
         for (int var31 = 0; var31 < var8; var31++) {
            int var32 = var30 + var31 * var8;
            boolean var33 = var7.method13()[var32];
            boolean var34 = false;

            for (int var35 = 0; var35 < var40.length; var35++) {
               if (var40[var35] == var32) {
                  var34 = true;
                  break;
               }
            }

            if (var33 || var34) {
               int var66 = var33 ? -5592406 : 1437248170;
               if (var34 && var33) {
                  var66 = -3355444;
               }

               boolean var36 = var30 == 0 && var31 == 0;
               boolean var37 = var30 == var8 - 1 && var31 == 0;
               boolean var38 = var30 == 0 && var31 == var8 - 1;
               boolean var39 = var30 == var8 - 1 && var31 == var8 - 1;
               if (!var36 && !var37 && !var38 && !var39) {
                  LcuiScreen.method94(var1, var29 + var30 * var9, this.y + 3.0F + var31 * var9, var9, var9, var66);
               } else {
                  LcuiScreen.method101(var1, var29 + var30 * var9, this.y + 3.0F + var31 * var9, var9, var9, 5.0F, var66, var36, var37, var38, var39);
               }
            }
         }
      }

      for (int var55 = 1; var55 <= var8; var55++) {
         LcuiScreen.method94(var1, var29 + var55 * var9, this.y + 3.0F, 0.5F, this.height - 5.0F, 1076176165);
         LcuiScreen.method94(var1, var29, this.y + 3.0F + var55 * var9, this.height - 5.0F, 0.5F, 1076176165);
      }

      LcuiScreen.method56(var1, var29, this.y + 3.0F, this.height - 5.5F, this.height - 5.5F, 5.0F, 1076176165);
      if (var10 >= 0 && var10 < var8 && var11 >= 0 && var11 < var8) {
         boolean var56 = var10 == var8 / 2 && var11 == var8 / 2;
         String var58 = var10 == var8 / 2 ? "§b" + var10 + "§r" : String.valueOf(var10);
         String var60 = var11 == var8 / 2 ? "§b" + var11 + "§r" : String.valueOf(var11);
         field20.method13(
            var1, "(" + var58 + "," + var60 + ")" + (var56 ? " (§bcenter§r)" : ""), this.x + this.width - 55.0F, this.y + this.height - 11.0F, -4275267
         );
         if (this.field32) {
            float var62 = var29 + var10 * var9 + var9 / 2.0F - 0.25F;
            float var64 = this.y + 3.0F + var11 * var9 + var9 / 2.0F - 0.25F;
            LcuiScreen.method68(var1, var29, var29 + this.height - 6.5F, var64, 862448617);
            LcuiScreen.method87(var1, var62, this.y + 2.0F, this.y + this.height - 3.0F, 862448617);
         }
      }

      if (this.field32) {
         LcuiScreen.method68(var1, var29 - 3.0F, var29 - 1.0F, this.y + 3.0F + var8 / 2.0F * var9, -9966615);
         LcuiScreen.method68(var1, var29 + this.height - 5.5F, var29 + this.height - 5.5F + 2.0F, this.y + 3.0F + var8 / 2.0F * var9, -9966615);
         LcuiScreen.method87(var1, var29 + var8 / 2.0F * var9, this.y - 1.0F, this.y + 3.0F, -9966615);
         LcuiScreen.method87(var1, var29 + var8 / 2.0F * var9, this.y + this.height - 3.5F, this.y + this.height + 0.5F, -9966615);
      }

      (new Crosshairelytra(null, false, false) {
         public int method1(float var1, float var2x) {
            return 862448617;
         }
      }).method6(var1, var29 + var8 / 2.0F * var9, this.y + 3.0F + var8 / 2.0F * var9, 2.5F);
      if (var6 != null) {
         var1.method38(0.0F, 0.0F, 100.0F);
         List var57 = FontRegistry.method9().method25(var6, 150.0);
         float var59 = var57.size() > 1 ? 150.0F : FontRegistry.method8().method4(var6);
         LcuiScreen.method54(var1, var4 + 8.0F, var5 + 6.0F, var59 + 10.0F, 8 * var57.size() + 5, 4.0F, -1879048192);
         int var61 = 0;

         for (String var65 : var57) {
            FontRegistry.method8().method13(var1, var65.trim(), var4 + 12.5F, var5 + 9.0F + var61 * 8, -1);
            var61++;
         }

         var1.method38(0.0F, 0.0F, -100.0F);
      }
   }

   private void method2(MixinHelper_4 var1, String var2, float var3, float var4) {
      LcuiScreen.method117(var1, var3, var4, 31.0F, 9.0F, 2.0F, -3355444);
      float var5 = field20.method4(var2);
      if (var5 >= 30.0F) {
         FontRegistry.method6().method14(var1, var2, var3 + 15.5F, var4 + 1.0F, -14540254);
      } else {
         field20.method14(var1, var2, var3 + 15.5F, var4, -14540254);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (!this.field31) {
         this.method8();
      } else {
         boolean var3 = false;
         switch (var2) {
            case KEY_DELETE:
               var3 |= this.method4();
               break;
            case KEY_W:
               int var4 = this.field30.ordinal();
               CrosshairPatternType[] var5 = CrosshairPatternType.values();
               this.field30 = var5[(var4 + 1) % var5.length];
               break;
            case KEY_S:
               this.field32 = !this.field32;
            default:
               if (Character.toLowerCase(var1) == 'z') {
                  var3 |= this.method5(this.field33, this.field34);
               } else if (Character.toLowerCase(var1) == 'y' && !this.field34.isEmpty()) {
                  var3 |= this.method5(this.field34, this.field33);
               }
         }

         if (var3) {
            this.option.method11().method30().reload();
         }
      }
   }

   private boolean method4() {
      boolean[] var1 = this.option.method11().method35().method13();
      ArrayList var2 = new ArrayList();

      for (int var3 = 0; var3 < var1.length; var3++) {
         if (var1[var3]) {
            var2.add(new CrosshairPattern(var3, var1[var3], false));
         }
      }

      if (!var2.isEmpty()) {
         Arrays.fill(var1, false);
         this.method6(this.field33, var2);
         return true;
      } else {
         return false;
      }
   }

   private boolean method5(Stack<List<CrosshairPattern>> var1, Stack<List<CrosshairPattern>> var2) {
      if (var1.isEmpty()) {
         return false;
      }

      List var3 = (List)var1.pop();
      ArrayList var4 = new ArrayList();
      boolean[] var5 = this.option.method11().method35().method13();

      for (CrosshairPattern var7 : var3) {
         if (var5[var7.field1] != var7.field2) {
            var4.add(new CrosshairPattern(var7.field1, var5[var7.field1], var7.field2));
            var5[var7.field1] = var7.field2;
         }
      }

      if (var4.isEmpty()) {
         return false;
      }

      this.method6(var2, var4);
      return true;
   }

   private void method6(Stack<List<CrosshairPattern>> var1, List<CrosshairPattern> var2) {
      if (var2 != null && !var2.isEmpty()) {
         var1.push(var2);
         if (var1.size() > 10) {
            var1.remove(0);
         }
      }
   }

   private void method8() {
      this.field33.clear();
      this.field34.clear();
      this.field35 = null;
   }

   @Override
   public void close() {
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 164.0F;
   }

   @Override
   public boolean method3() {
      return false;
   }

   @Override
   public String getLanguagePath() {
      return "gui.crosshair_edit";
   }
}
