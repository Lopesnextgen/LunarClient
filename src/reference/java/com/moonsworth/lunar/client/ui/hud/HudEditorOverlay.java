package com.moonsworth.lunar.client.ui.hud;

import com.google.common.util.concurrent.AtomicDouble;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.HudElementAnimation;
import com.moonsworth.lunar.client.ui.ColorAnimation;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.framework.feature.debug.Gui2Extension;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump49;
import com.moonsworth.lunar.client.util.ThreadModuleDump60;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.ThreadModuleDump95;
import com.moonsworth.lunar.client.util.ThreadModuleDump49.Type;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class HudEditorOverlay {
   private static final float field1 = 8.0F;
   private static final float field2 = 5.0F;
   private static final float field3 = 2.0F;
   private final HudEditorWidget field4;
   private final HudElementRegistry field5;
   private final HudEditorState field6;
   private final ResourceLocationBridge field7 = ResourceLocationBridge.create("lunar", "icons/exit-17x17-small.png");
   private final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "icons/mainmenu/cog-20x20.png");
   private final ColorAnimation field9 = new ColorAnimation(500L);
   private final Color field10 = new Color(-2540289, true);
   private final HudElementAnimation field11 = new HudElementAnimation(1000L);
   private final HudElementAnimation field12 = new HudElementAnimation(1000L);
   private final List<MovableHudElement> field13 = new ArrayList<>();
   private final List<MovableHudElement> selected = new ArrayList<>();
   private final Map<MovableHudElement, HudEditorOverlay.Data2> field14 = new HashMap<>();
   private final List<HudEditorOverlay.Data> field15 = new ArrayList<>();
   private final List<HudEditorOverlay.Data> field16 = new ArrayList<>();
   private final List<HudEditorOverlay.Data> field17 = new ArrayList<>();
   private TextLabelWidget field18;
   @Nullable
   private MovableHudElement field19;
   @Nullable
   private MovableHudElement field20;
   @Nullable
   private HudEditorOverlay field21;
   private float field22;
   private float field23;
   private long field24;
   private boolean field25;
   private boolean field26;
   private int field27;
   private long field28;

   public List<GuiWidget> method1() {
      this.field18 = new TextLabelWidget(null, "help", FontRegistry.field16);
      this.field18.setTextColor(-1);
      this.field18.method12(new AnimatedValue(805306368, 1342177280));
      this.field18.method11(new AnimatedValue(1076176165, -1711276033));
      this.field18.method18(2.0F);
      this.field18.method1("help", true);
      return List.of(this.field18);
   }

   public void init() {
      this.field9.start();
      this.field18.method2(4.0F, this.field4.method2() - 28.0F, 24.0F, 24.0F);
      this.method10();
   }

   public void update() {
      if (this.field15.size() > 50) {
         this.field15.remove(0);
      }

      if (!this.selected.isEmpty()) {
         int var1 = 0;
         int var2 = 0;
         if (Bridge.method18().method1(KeyCode.KEY_LEFT)) {
            var1--;
         }

         if (Bridge.method18().method1(KeyCode.KEY_RIGHT)) {
            var1++;
         }

         if (Bridge.method18().method1(KeyCode.KEY_UP)) {
            var2--;
         }

         if (Bridge.method18().method1(KeyCode.KEY_DOWN)) {
            var2++;
         }

         if (var1 == 0 && var2 == 0) {
            this.field27 = 0;
            if (this.field26) {
               this.field26 = false;
               this.selected.stream().filter(Objects::nonNull).findFirst().ifPresent(var1x -> this.field6.method2(var1x.id()));
            }
         } else {
            this.field27++;
         }

         if (this.field27 > 10) {
            if (this.field27 > 20) {
               var1 *= 2;
               var2 *= 2;
            }

            this.method6(var1, var2);
         }
      } else {
         this.field27 = 0;
         this.field26 = false;
      }
   }

   public void method2(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      float var3 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
      float var4 = var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
      var1.push();
      this.method16(var1);
      HudAnchor var5 = HudAnchor.getMousePosition(var2);
      if (ThreadModuleDump63.method34(Gui2Extension.MOVEMENT_UI)) {
         var1.method28(ThreadModuleDump63.method10(), var5.name(), (int)var3, (int)var4, -1, true);
      }

      if (ThreadModuleDump63.method3().bridge$getWorld() == null) {
         ClientEventBus.method29()
            .method12(
               EventRenderHudLegacy.Data.class, () -> new EventRenderHudLegacy.Data(var1.method48(), var1, new MarkerModel.Data2(this.field4.method1(), this.field4.method2()))
            );
      }

      if (this.field11.method7()) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method66(
            var1,
            0.0F,
            this.field4.method2() / 2.0F - 0.25F,
            this.field4.method1(),
            this.field4.method2() / 2.0F + 0.25F,
            ThreadModuleDump23.method11(
               this.field10.getRed() / 255.0F,
               this.field10.getGreen() / 255.0F,
               this.field10.getBlue() / 255.0F,
               (int)(this.field11.method9() * 255.0F + 0.5F)
            )
         );
      }

      if (this.field12.method7()) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method66(
            var1,
            this.field4.method1() / 2.0F - 0.25F,
            0.0F,
            this.field4.method1() / 2.0F + 0.25F,
            this.field4.method2(),
            ThreadModuleDump23.method11(
               this.field10.getRed() / 255.0F,
               this.field10.getGreen() / 255.0F,
               this.field10.getBlue() / 255.0F,
               (int)(this.field12.method9() * 255.0F + 0.5F)
            )
         );
      }

      if ((this.field23 != 0.0F || this.field22 != 0.0F) && System.currentTimeMillis() - this.field24 >= 100L) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method94(
            var1,
            Math.min(var3, this.field22),
            Math.min(var4, this.field23),
            var3 > this.field22 ? var3 - this.field22 : this.field22 - var3,
            var4 > this.field23 ? var4 - this.field23 : this.field23 - var4,
            1350565844
         );
         com.moonsworth.lunar.client.ui.LcuiScreen.method100(
            var1,
            Math.min(var3, this.field22),
            Math.min(var4, this.field23),
            var3 > this.field22 ? var3 - this.field22 : this.field22 - var3,
            var4 > this.field23 ? var4 - this.field23 : this.field23 - var4,
            0.5F,
            -8388652,
            0
         );
      }

      this.field13.clear();
      boolean var6 = false;
      boolean var7 = Bridge.method20().method1(0);
      boolean var8 = Bridge.method20().method1(1);

      for (MovableHudElement var10 : this.field5.method1()) {
         if (this.method17(var10, var1, var2, var7, var8, var5)) {
            var6 = true;
         }
      }

      if (this.field20 != null) {
         ThreadModuleDump49.method2(Type.CROSSHAIR);
      }

      if (var6) {
         MovableHudElement var15 = this.field19;
         float[] var17 = this.method26(var15);
         boolean var11 = this.method24(var3, var4, var17);
         float[] var12 = this.method27(var15);
         boolean var13 = this.method25(var3, var4, var12);
         int var14 = var11 ? -16777216 : -1291845632;
         com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, this.field8, var17[0] + 1.0F, var17[1] + 1.0F, 8.0F, 8.0F, var14);
         var14 = var13 ? -16777216 : -1291845632;
         com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, this.field7, var12[0] + 1.0F, var12[1] + 1.0F, 8.0F, 8.0F, var14);
         var14 = var11 ? -1 : -2130706433;
         com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, this.field8, var17[0], var17[1], 8.0F, 8.0F, var14);
         var14 = var13 ? -52429 : -2130758861;
         com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, this.field7, var12[0], var12[1], 8.0F, 8.0F, var14);
         var1.method44(var0 -> var0.method29().method25(1.0F, 1.0F, 1.0F, 1.0F));
      } else {
         this.field19 = null;
      }

      com.moonsworth.lunar.client.ui.hud.HudEditor var16 = this.method21();
      if (var16 != null) {
         MovableHudElement var18 = var16.method5();
         float var19 = (float)(Math.round(var16.method4(var3, var4) * 100.0) / 100.0);
         var18.setScale(ThreadModuleDump67.method1(var19, var18.method6(), var18.method7()));
         this.field6.method1(var18.id());
      }

      var1.pop();
      if (this.field18.method3(var2)) {
         this.method35(var1);
      }
   }

   public void method3(MarkerModel.Data2 var1, int var2) {
      float var3 = var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
      float var4 = var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
      if (!this.method37() && this.field19 != null) {
         if (this.method24(var3, var4, this.method26(this.field19))) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method15();
            this.field6.method3(this.field19.id());
            return;
         }

         if (this.method25(var3, var4, this.method27(this.field19))) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method15();
            this.method7(
               new HudEditorOverlay.Data(
                  this.field19.id(),
                  this.field19.method8(),
                  this.field19.getScale(),
                  this.field19.method2(),
                  this.field19.method3(),
                  this.field19.isEnabled(),
                  System.currentTimeMillis()
               )
            );
            this.field6.method4(this.field19.id());
            return;
         }
      } else {
         this.selected.clear();
      }

      if ((var2 == 0 || var2 == 1) && this.field21 != null && !this.field21.method3()) {
         MovableHudElement var5 = this.field21.method5();
         com.moonsworth.lunar.client.ui.hud.HudEditor.Type var6 = this.field21.method6();
         com.moonsworth.lunar.client.ui.hud.HudEditor.Data3 var7 = com.moonsworth.lunar.client.ui.hud.HudEditor.Data3.method1(var5, var6);
         if (method39(var3, var4, this.method20(var5, var6)) && var7.method2()) {
            this.field17
               .add(
                  new HudEditorOverlay.Data(var5.id(), var5.method8(), var5.getScale(), var5.method2(), var5.method3(), var5.isEnabled(), System.currentTimeMillis())
               );
            this.field21 = this.field21.method2(var6, var7);
            return;
         }
      }

      if (!this.method37() && (var2 == 0 || var2 == 1)) {
         if (this.field19 != null) {
            long var9 = System.currentTimeMillis();
            if (var2 == 1) {
               this.field28 = var9;
            }

            for (MovableHudElement var8 : this.field5.method1()) {
               if (var8 != null) {
                  this.field17.add(new HudEditorOverlay.Data(var8.id(), var8.method8(), var8.getScale(), var8.method2(), var8.method3(), var8.isEnabled(), var9));
               }
            }

            this.field20 = this.field19;
            if (!this.selected.contains(this.field19)) {
               if (!com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown()) {
                  this.selected.clear();
                  this.field14.clear();
               }

               this.selected.add(this.field19);
               this.field14.put(this.field19, new HudEditorOverlay.Data2(var3 - this.field19.method2(), var4 - this.field19.method3()));
            }

            this.field14.forEach((var2x, var3x) -> {
               var3x.method4(var4 - var2x.method3());
               var3x.method3(var3 - var2x.method2());
            });
            this.field25 = false;
         } else {
            this.field23 = var4;
            this.field22 = var3;
            this.field24 = System.currentTimeMillis();
         }
      }
   }

   public void method4(MarkerModel.Data2 var1, int var2) {
      float var3 = var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
      float var4 = var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
      if (this.field20 != null && this.field25) {
         this.selected
            .forEach(
               var1x -> {
                  HudAnchor var2x = HudAnchor.getMousePosition(
                     new MarkerModel.Data2(var1x.method4() + var1x.method12() / 2.0F, var1x.method5() + var1x.method13() / 2.0F)
                  );
                  if (var2x != var1x.method8()) {
                     float var3x = var1x.method4();
                     float var4x = var1x.method5();
                     var1x.method9(var2x);
                     var1x.method1(var1x.method2() + (var3x - var1x.method4()), var1x.method3() + (var4x - var1x.method5()));
                     this.field6.method1(var1x.id());
                  }
               }
            );
      }

      com.moonsworth.lunar.client.ui.hud.HudEditor var5 = this.method21();
      if (var5 != null) {
         this.field17.forEach(this::method7);
         this.field17.clear();
         this.field6.method2(var5.method5().id());
         this.field21 = null;
      }

      if (this.field20 != null && var2 == 1 && System.currentTimeMillis() - this.field28 <= 250L) {
         if (!com.moonsworth.lunar.client.ui.LcuiScreen.isShiftKeyDown()) {
            if (this.field20.method8() == HudAnchor.TOP_CENTER) {
               this.field12.start();
               this.field20.method1(0.0F, this.field20.method3());
            } else if (this.field20.method8() == HudAnchor.MIDDLE_LEFT || this.field20.method8() == HudAnchor.MIDDLE_RIGHT) {
               this.field11.start();
               this.field20.method1(this.field20.method2(), 0.0F);
            } else if (this.field20.method8() != HudAnchor.BOTTOM_CENTER_L && this.field20.method8() != HudAnchor.BOTTOM_CENTER_R) {
               if (this.field20.method8().getVertical() == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE) {
                  this.field11.start();
               }

               if (this.field20.method8().getHorizontal() == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE) {
                  this.field12.start();
               }

               this.field20.method1(0.0F, 0.0F);
            } else {
               this.field12.start();
               this.field20.method1(0.0F, this.field20.method3());
            }
         } else {
            if (this.field20.method8().getVertical() == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE) {
               this.field11.start();
            }

            if (this.field20.method8().getHorizontal() == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE) {
               this.field12.start();
            }

            this.field20.method1(0.0F, 0.0F);
         }

         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
      }

      if (var2 == 0 || var2 == 1) {
         if (this.field23 != 0.0F || this.field22 != 0.0F) {
            this.field23 = 0.0F;
            this.field22 = 0.0F;
            this.selected.addAll(this.field13);
            this.field14.clear();
            this.field13.forEach(var3x -> this.field14.put(var3x, new HudEditorOverlay.Data2(var3 - var3x.method2(), var4 - var3x.method3())));
            this.field20 = null;
         }

         if (this.field20 != null) {
            this.field17.forEach(var1x -> {
               MovableHudElement var2x = this.field5.method2(var1x.method1()).orElseThrow();
               if (var2x.method2() != var1x.field4 || var2x.method3() != var1x.field5) {
                  this.method7(var1x);
               }
            });
            this.field17.clear();
            this.field6.method2(this.field20.id());
            this.field20 = null;
            com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         }
      }
   }

   public void method5(char var1, KeyCode var2) {
      if (com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown()) {
         if (var2 == KeyCode.KEY_Z && !this.field15.isEmpty()) {
            HudEditorOverlay.Data var3 = this.field15.get(this.field15.size() - 1);
            ArrayList var4 = new ArrayList();

            for (HudEditorOverlay.Data var6 : this.field15) {
               if (var3.method3() == var6.method3()) {
                  var4.add(var6);
                  MovableHudElement var7 = this.field5.method2(var6.method1()).orElseThrow();
                  this.field16
                     .add(new HudEditorOverlay.Data(var7.id(), var7.method8(), var7.getScale(), var7.method2(), var7.method3(), var7.isEnabled(), var3.method3()));
                  this.method9(var6, var7);
               }
            }

            this.field15.removeAll(var4);
            this.field6.method2(var3.method1());
         }

         if (var2 == KeyCode.KEY_Y && !this.field16.isEmpty()) {
            HudEditorOverlay.Data var8 = this.field16.get(this.field16.size() - 1);
            ArrayList var11 = new ArrayList();

            for (HudEditorOverlay.Data var15 : this.field16) {
               if (var8.method3() == var15.method3()) {
                  MovableHudElement var17 = this.field5.method2(var15.method1()).orElseThrow();
                  var11.add(var15);
                  this.field15
                     .add(
                        new HudEditorOverlay.Data(var17.id(), var17.method8(), var17.getScale(), var17.method2(), var17.method3(), var17.isEnabled(), var8.method3())
                     );
                  this.method9(var15, var17);
               }
            }

            this.field16.removeAll(var11);
            this.field6.method2(var8.method1());
         }

         if (var2 == KeyCode.KEY_X && !this.selected.isEmpty()) {
            long var9 = System.currentTimeMillis();
            String var14 = null;

            for (MovableHudElement var18 : this.selected) {
               if (var18 != null) {
                  this.method7(new HudEditorOverlay.Data(var18.id(), var18.method8(), var18.getScale(), var18.method2(), var18.method3(), var18.isEnabled(), var9));
                  var18.setEnabled(false);
                  var14 = var18.id();
               }
            }

            this.selected.clear();
            if (var14 != null) {
               this.field6.method2(var14);
            }
         }
      }

      if (!this.selected.isEmpty()) {
         int var10 = 0;
         int var12 = 0;
         if (var2 == KeyCode.KEY_LEFT) {
            var10--;
         }

         if (var2 == KeyCode.KEY_RIGHT) {
            var10++;
         }

         if (var2 == KeyCode.KEY_UP) {
            var12--;
         }

         if (var2 == KeyCode.KEY_DOWN) {
            var12++;
         }

         this.method6(var10, var12);
      }
   }

   private void method6(int var1, int var2) {
      if (var1 != 0 || var2 != 0) {
         float var3 = var1;
         float var4 = var2;
         if (this.selected.size() > 1) {
            float[] var5 = this.method13(var1, var2);
            var3 = var5[0];
            var4 = var5[1];
            if (var3 == 0.0F && var4 == 0.0F) {
               return;
            }
         }

         long var12 = System.currentTimeMillis();
         boolean var7 = false;

         for (MovableHudElement var9 : this.selected) {
            if (var9 != null) {
               float var10;
               float var11;
               if (this.selected.size() > 1) {
                  var10 = var9.method2() + var3;
                  var11 = var9.method3() + var4;
               } else {
                  var10 = this.method11(var9, var9.method2() + var1);
                  var11 = this.method15(var9, var9.method3() + var2);
               }

               if (var10 != var9.method2() || var11 != var9.method3()) {
                  if (!this.field26) {
                     this.method7(new HudEditorOverlay.Data(var9.id(), var9.method8(), var9.getScale(), var9.method2(), var9.method3(), var9.isEnabled(), var12));
                  }

                  var9.method1(var10, var11);
                  this.field6.method1(var9.id());
                  var7 = true;
               }
            }
         }

         if (var7) {
            this.field26 = true;
         }
      }
   }

   private void method7(HudEditorOverlay.Data var1) {
      this.field16.clear();
      this.field15.add(var1);
   }

   public void method8(List<MovableHudElement> var1) {
      if (!var1.isEmpty()) {
         long var2 = System.currentTimeMillis();

         for (MovableHudElement var5 : var1) {
            this.method7(new HudEditorOverlay.Data(var5.id(), var5.method8(), var5.getScale(), var5.method2(), var5.method3(), false, var2));
         }
      }
   }

   private void method9(HudEditorOverlay.Data var1, MovableHudElement var2) {
      if (var2.isEnabled() != var1.field6) {
         var2.setEnabled(var1.field6);
      }

      var2.setScale(var1.field3);
      var2.method9(var1.method2());
      var2.method1(var1.field4, var1.field5);
      this.field6.method1(var2.id());
   }

   public void method10() {
      this.field22 = 0.0F;
      this.field23 = 0.0F;
   }

   private float method11(MovableHudElement var1, float var2) {
      float var3 = var2 - var1.method2() + var1.method4();
      float var4 = this.field4.method1() - 2.0F - var1.method12();
      if (var4 < 2.0F) {
         return var2 - var3;
      } else if (var3 < 2.0F) {
         return var2 + 2.0F - var3;
      } else {
         return var3 > var4 ? var2 + var4 - var3 : var2;
      }
   }

   private void method12(float var1, float var2) {
      float[] var3 = this.method13(var1, var2);

      for (MovableHudElement var5 : this.selected) {
         if (var5 != null) {
            var5.method1(var5.method2() + var3[0], var5.method3() + var3[1]);
            this.field6.method1(var5.id());
         }
      }
   }

   private float[] method13(float var1, float var2) {
      for (MovableHudElement var4 : this.selected) {
         if (var4 != null) {
            var1 = this.method14(var1, var4.method4(), var4.method12(), this.field4.method1());
            var2 = this.method14(var2, var4.method5(), var4.method13(), this.field4.method2());
         }
      }

      return new float[]{var1, var2};
   }

   private float method14(float var1, float var2, float var3, float var4) {
      float var5 = Math.min(2.0F - var2, 0.0F);
      float var6 = Math.max(var4 - 2.0F - var3 - var2, 0.0F);
      return Math.min(Math.max(var1, var5), var6);
   }

   private float method15(MovableHudElement var1, float var2) {
      float var3 = var2 - var1.method3() + var1.method5();
      float var4 = this.field4.method2() - 2.0F - var1.method13();
      if (var4 < 2.0F) {
         return var2 - var3;
      } else if (var3 < 2.0F) {
         return var2 + 2.0F - var3;
      } else {
         return var3 > var4 ? var2 + var4 - var3 : var2;
      }
   }

   private void method16(MixinHelper_4 var1) {
      float var2 = 0.5F;
      com.moonsworth.lunar.client.ui.LcuiScreen.method100(
         var1,
         2.0F - var2,
         2.0F - var2,
         this.field4.method1() - 4.0F + var2 * 2.0F,
         this.field4.method2() - 4.0F + var2 * 2.0F,
         var2,
         ThreadModuleDump23.method11(0.0F, 1.0F, 1.0F, 0.8F * this.field9.method9()),
         0
      );
   }

   private boolean method17(MovableHudElement var1, MixinHelper_4 var2, MarkerModel.Data2 var3, boolean var4, boolean var5, HudAnchor var6) {
      if (!var1.method16()) {
         this.method22(var1);
         return false;
      }

      float var7 = var3.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
      float var8 = var3.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
      boolean var9 = false;
      double var10 = com.moonsworth.lunar.client.ui.LcuiScreen.method135(var1.method4());
      double var12 = com.moonsworth.lunar.client.ui.LcuiScreen.method135(var1.method5());
      boolean var14 = (this.field19 == null || this.field19 == var1)
         && var7 >= var10
         && var7 <= var10 + var1.method12()
         && var8 >= var12
         && var8 <= var12 + var1.method13();
      MovableHudElement var15 = this.field19;
      this.field19 = null;
      boolean var16 = this.field4.method3(var3) && var1.method14();
      this.field19 = var15;
      var14 &= !var16;
      com.moonsworth.lunar.client.ui.hud.HudEditor var17 = this.method21();
      if (var17 != null && var17.method5().equals(var1)) {
         var14 = true;
      }

      float var18 = Math.min(this.field22, var7);
      float var19 = Math.min(this.field23, var8);
      float var20 = Math.max(this.field22, var7);
      float var21 = Math.max(this.field23, var8);
      boolean var22 = false;
      if (System.currentTimeMillis() - this.field24 >= 100L
         && (this.field23 != 0.0F || this.field22 != 0.0F)
         && this.method18(var1, var18, var20, var19, var21)) {
         this.field13.add(var1);
         var22 = true;
      }

      com.moonsworth.lunar.client.ui.LcuiScreen.method94(
         var2,
         (float)var10,
         (float)var12,
         com.moonsworth.lunar.client.ui.LcuiScreen.method135(var1.method12()),
         com.moonsworth.lunar.client.ui.LcuiScreen.method135(var1.method13()),
         !this.selected.contains(var1) && !var22 ? (var14 ? -2130706433 : 553648127) : 1350565844
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method100(
         var2,
         (float)var10,
         (float)var12,
         com.moonsworth.lunar.client.ui.LcuiScreen.method135(var1.method12() - 0.5F),
         com.moonsworth.lunar.client.ui.LcuiScreen.method135(var1.method13() - 0.5F),
         0.5F,
         Integer.MIN_VALUE,
         0
      );
      com.moonsworth.lunar.client.ui.hud.HudEditor.Type var23 = this.method19(var1);
      ThreadModuleDump60 var24 = this.method20(var1, var23);
      boolean var25 = (this.field19 == null || this.field19 == var1) && method39(var7, var8, var24);
      boolean var26 = this.method24(var7, var8, this.method26(var1));
      boolean var27 = this.method25(var7, var8, this.method27(var1));
      if (!var14 && !var25 && !var26 && !var27) {
         this.method22(var1);
      }

      if (this.method23(var1, var25, var14)) {
         if (!this.method37()) {
            this.field21 = com.moonsworth.lunar.client.ui.hud.HudEditor.method1(var1, var23);
         }

         com.moonsworth.lunar.client.ui.LcuiScreen.method94(var2, var24.x, var24.y, 5.0F, 5.0F, -8388652);
      }

      if (!var16 && (var14 || var26 || var27 || var25)) {
         this.field19 = var1;
         var9 = true;
      }

      if ((var4 || var5) && this.field20 != null && this.field20 == var1 && this.selected.contains(var1)) {
         HudEditorOverlay.Data2 var28 = this.field14.get(var1);
         if (var28 == null) {
            return var9;
         }

         float var29 = var7 - var28.method1();
         float var30 = var8 - var28.method2();
         if (!this.field25) {
            if (var29 == var1.method2() && var30 == var1.method3()) {
               return var9;
            }

            this.field25 = true;
         }

         boolean var31 = true;
         boolean var32 = true;
         float var33 = this.field4.method1();
         float var34 = this.field4.method2();
         float var35 = var29 - var1.method2() + var1.method4();
         float var36 = var30 - var1.method3() + var1.method5();
         if (var35 <= 4.0F) {
            var29 += 2.0F - var35;
            var31 = false;
            this.method32(var2, 1.5F, this.field10.getRGB());
         } else if (var35 + var1.method12() >= var33 - 2.0F - 2.0F) {
            var29 += var33 - 2.0F - var35 - var1.method12() + 0.5F;
            var31 = false;
            this.method32(var2, var33 - 2.0F + 1.0F, this.field10.getRGB());
         }

         if (var36 <= 4.0F) {
            var30 += 2.0F - var36;
            var32 = false;
            this.method31(var2, 1.0F, this.field10.getRGB());
         } else if (var36 + var1.method13() >= var34 - 2.0F - 2.0F) {
            var30 += var34 - 2.0F - var36 - var1.method13();
            var32 = false;
            this.method31(var2, var34 - 2.0F + 0.5F, this.field10.getRGB());
         }

         if (this.selected.size() > 1) {
            this.method12(var29 - var1.method2(), var30 - var1.method3());
         } else {
            var1.method1(var29, var30);
            if (var4) {
               AtomicDouble var37 = new AtomicDouble(var29);
               AtomicDouble var38 = new AtomicDouble(var30);
               this.method33(var2, var31, var32, var1, var6, var37, var38);
               var1.method1((float)var37.get(), (float)var38.get());
            }

            this.field6.method1(var1.id());
         }
      }

      return var9;
   }

   private boolean method18(MovableHudElement var1, float var2, float var3, float var4, float var5) {
      return new ThreadModuleDump95(var2, var4, var3 - var2, var5 - var4)
         .method5(new ThreadModuleDump95(var1.method4(), var1.method5(), var1.method12(), var1.method13()));
   }

   private com.moonsworth.lunar.client.ui.hud.HudEditor.Type method19(MovableHudElement var1) {
      com.moonsworth.lunar.client.ui.hud.HudEditor var2 = this.method21();
      if (var2 != null && var2.method5().equals(var1)) {
         return var2.method6();
      }

      com.moonsworth.lunar.client.ui.hud.HudEditor.Type var3 = com.moonsworth.lunar.client.ui.hud.HudEditor.Type.fromHudPosition(
         var1.method8()
      );
      float var4 = var1.getScale();
      boolean var5 = method38(var1.method4(), var1.method12(), var1.method10(), var4, var3.left());
      boolean var6 = method38(var1.method5(), var1.method13(), var1.method11(), var4, var3.top());
      return com.moonsworth.lunar.client.ui.hud.HudEditor.Type.of(var6, var5);
   }

   private ThreadModuleDump60 method20(MovableHudElement var1, com.moonsworth.lunar.client.ui.hud.HudEditor.Type var2) {
      return new ThreadModuleDump60(
         com.moonsworth.lunar.client.ui.LcuiScreen.method135(var2.x(var1)) - 2.5F,
         com.moonsworth.lunar.client.ui.LcuiScreen.method135(var2.y(var1)) - 2.5F
      );
   }

   @Nullable
   private HudEditorOverlay method21() {
      return this.field21 != null && this.field21.method3() ? this.field21 : null;
   }

   private void method22(MovableHudElement var1) {
      if (this.field21 != null && !this.field21.method3() && var1.equals(this.field21.method5())) {
         this.field21 = null;
      }
   }

   private boolean method23(MovableHudElement var1, boolean var2, boolean var3) {
      if (!var1.method17()) {
         return false;
      }

      boolean var4 = this.field21 != null && var1.equals(this.field21.method5());
      return var4 || var2 || var3;
   }

   private boolean method24(float var1, float var2, float[] var3) {
      return var1 > var3[0] - 2.0F && var2 > var3[1] - 2.0F && var1 < var3[0] + 8.0F + 2.0F && var2 < var3[1] + 8.0F + 2.25F;
   }

   private boolean method25(float var1, float var2, float[] var3) {
      return var1 > var3[0] - 2.0F && var2 > var3[1] - 2.0F && var1 < var3[0] + 8.0F + 2.0F && var2 < var3[1] + 8.0F + 2.25F;
   }

   private float[] method26(MovableHudElement var1) {
      float var2 = method40(var1) ? var1.method4() + 1.0F : var1.method4() + var1.method12() / 2.0F - 8.0F - 2.0F;
      return new float[]{var2, this.method28(var1)};
   }

   private float[] method27(MovableHudElement var1) {
      float var2 = method40(var1) ? var1.method4() + var1.method12() - 8.0F - 2.0F : var1.method4() + var1.method12() / 2.0F + 2.0F;
      return new float[]{var2, this.method28(var1)};
   }

   private float method28(MovableHudElement var1) {
      float var2 = var1.method5();
      if (method40(var1)) {
         return this.method29(var1) ? var2 + 2.0F : var2 + var1.method13() - 8.0F - 2.0F;
      } else {
         float var3 = var2 - 8.0F - 2.0F;
         float var4 = var2 + var1.method13() * 2.0F - 8.0F - 2.0F;
         if (this.method30(var1)) {
            return var3 - 2.0F >= 0.0F ? var3 : var4;
         } else {
            return var4 + 8.0F + 2.25F <= this.field4.method2() ? var4 : var3;
         }
      }
   }

   private boolean method29(MovableHudElement var1) {
      return var1.method17() && !this.method19(var1).top();
   }

   private boolean method30(MovableHudElement var1) {
      return var1.method17() ? !this.method19(var1).top() : !var1.method8().getVertical().equals(com.moonsworth.lunar.client.ui.hud.HudPlacement.TOP);
   }

   private void method31(MixinHelper_4 var1, float var2, int var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method66(var1, 0.0F, var2 - 0.25F, this.field4.method1(), var2 + 0.25F, var3);
   }

   private void method32(MixinHelper_4 var1, float var2, int var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method66(var1, var2 - 0.25F, 0.0F, var2 + 0.25F, this.field4.method2(), var3);
   }

   private void method33(MixinHelper_4 var1, boolean var2, boolean var3, MovableHudElement var4, HudAnchor var5, AtomicDouble var6, AtomicDouble var7) {
      if (ThreadModuleDump63.method34(Gui2Extension.MOVEMENT_UI)) {
         var1.method28(ThreadModuleDump63.method10(), var4.method4() + " " + var4.method5(), (int)var4.method4(), (int)var4.method5(), -1, true);
      }

      float[] var8 = var4.method15();
      float var9 = var8[0] * var4.getScale() + var4.method12() / 2.0F;
      float var10 = var8[1] * var4.getScale() + var4.method13() / 2.0F;
      if (var5.getVertical() == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE) {
         float var11 = this.field4.method2() / 2.0F - var10;
         if (Math.abs(var11) < 5.0F) {
            var7.set(var7.get() + var11);
            this.method31(var1, this.field4.method2() / 2.0F, this.field10.getRGB());
            var3 = false;
         }
      }

      if (var5.getHorizontal() == com.moonsworth.lunar.client.ui.hud.HudPlacement.MIDDLE
         || var5 == HudAnchor.BOTTOM_CENTER_L
         || var5 == HudAnchor.BOTTOM_CENTER_R) {
         float var27 = this.field4.method1() / 2.0F - var9;
         if (Math.abs(var27) < 5.0F) {
            var6.set(var6.get() + var27);
            this.method32(var1, this.field4.method1() / 2.0F, this.field10.getRGB());
            var2 = false;
         }
      }

      LinkedList var28 = new LinkedList<>(this.field5.method1());

      for (MovableHudElement var13 : (LinkedList)var28.stream().sorted((var2x, var3x) -> {
         if (var2x != null && var3x != null) {
            float[] var4x = var2x.method15();
            float[] var5x = var3x.method15();
            float[] var6x = var4.method15();
            Rectangle var7x = new Rectangle((int)var4x[0], (int)var4x[1], (int)var2x.method12(), (int)var2x.method13());
            Rectangle var8x = new Rectangle((int)var5x[0], (int)var5x[1], (int)var3x.method12(), (int)var3x.method13());
            Rectangle var9x = new Rectangle((int)var6x[0], (int)var6x[1], (int)var4.method12(), (int)var4.method13());

            try {
               return Double.compare(this.method34(var7x, var9x), this.method34(var8x, var9x));
            } catch (Exception var11x) {
               return 0;
            }
         } else {
            return 0;
         }
      }).collect(Collectors.toCollection(LinkedList::new))) {
         if (!var2 && !var3) {
            break;
         }

         if (var4 != var13 && !(var13.getWidth() < 1.0F) && !(var13.getHeight() < 1.0F)) {
            float[] var14 = var13.method15();
            float var15 = var14[0] * var13.getScale();
            float var16 = var14[1] * var13.getScale();
            float var17 = var8[0] * var4.getScale();
            float var18 = var8[1] * var4.getScale();
            float var19 = var15 - var17;
            float var20 = var15 + var13.method12() - (var17 + var4.method12());
            float var21 = var15 + var13.method12() - var17;
            float var22 = var15 - (var17 + var4.method12());
            float var23 = var16 - var18;
            float var24 = var16 + var13.method13() - (var18 + var4.method13());
            float var25 = var16 + var13.method13() - var18;
            float var26 = var16 - (var18 + var4.method13());
            if (var2 && var19 >= -2.0F && var19 <= 2.0F) {
               var2 = false;
               var6.set(var6.get() + var19);
               this.method32(var1, var13.method4(), this.field10.getRGB());
            }

            if (var2 && var20 >= -2.0F && var20 <= 2.0F) {
               var2 = false;
               var6.set(var6.get() + var20);
               this.method32(var1, var13.method4() + var13.method12(), this.field10.getRGB());
            }

            if (var2 && var22 >= -2.0F && var22 <= 2.0F) {
               var2 = false;
               var6.set(var6.get() + var22);
               this.method32(var1, var13.method4(), this.field10.getRGB());
            }

            if (var2 && var21 >= -2.0F && var21 <= 2.0F) {
               var2 = false;
               var6.set(var6.get() + var21);
               this.method32(var1, var13.method4() + var13.method12(), this.field10.getRGB());
            }

            if (var3 && var23 >= -2.0F && var23 <= 2.0F) {
               var3 = false;
               var7.set(var7.get() + var23);
               this.method31(var1, var13.method5() - 0.5F, this.field10.getRGB());
            }

            if (var3 && var24 >= -2.0F && var24 <= 2.0F) {
               var3 = false;
               var7.set(var7.get() + var24);
               this.method31(var1, var13.method5() + var13.method13(), this.field10.getRGB());
            }

            if (var3 && var26 >= -2.0F && var26 <= 2.0F) {
               var3 = false;
               var7.set(var7.get() + var26);
               this.method31(var1, var13.method5(), this.field10.getRGB());
            }

            if (var3 && var25 >= -2.0F && var25 <= 2.0F) {
               var3 = false;
               var7.set(var7.get() + var25);
               this.method31(var1, var13.method5() + var13.method13(), this.field10.getRGB());
            }
         }
      }
   }

   private float method34(Rectangle var1, Rectangle var2) {
      float var3 = Math.max(Math.abs(var1.x - var2.x) - var2.width / 2, 0);
      float var4 = Math.max(Math.abs(var1.y - var2.y) - var2.height / 2, 0);
      return var3 * var3 + var4 * var4;
   }

   private void method35(MixinHelper_4 var1) {
      List var2 = this.field4.method5();
      int var3 = 16 + var2.size() * 12 + 12;
      int var4 = var3 + 12 + 16;
      var1.push();
      var1.method38(0.0F, this.field4.method2() - 185.0F, 0.0F);
      com.moonsworth.lunar.client.ui.LcuiScreen.method66(var1, 0.0F, 0.0F, 240.0F, var4, Integer.MIN_VALUE);
      FontRegistry.method12().method13(var1, this.field4.method1("shortcutsMovement", new Object[0]), 4.0F, 2.0F, -1);
      com.moonsworth.lunar.client.ui.LcuiScreen.method66(var1, 4.0F, 12.0F, 234.0F, 12.5F, -2130706433);
      int var5 = 16;

      for (EditorShortcut var7 : var2) {
         this.method36(var1, var7.getKeys().get(0), 6, var5);
         if (var7.getKeys().size() > 1) {
            FontRegistry.method17().method13(var1, "+", 30.0F, var5, -1);
            this.method36(var1, var7.getKeys().get(1), 36, var5);
         }

         String var8 = "| ";
         if (var7.method4() != null) {
            var8 = var8 + AdventureChatFormatting.AQUA + this.field4.method1(var7.method4(), new Object[0]) + AdventureChatFormatting.RESET + " ";
         }

         var8 = var8 + this.field4.method1(var7.method5(), new Object[0]);
         FontRegistry.method17().method13(var1, var8, 80.0F, var5, -1);
         var5 += 12;
      }

      var5 = var3;
      this.method36(var1, "Up", 31, var5);
      var5 += 12;
      this.method36(var1, "Left", 6, var5);
      this.method36(var1, "Down", 26, var5);
      this.method36(var1, "Right", 51, var5);
      FontRegistry.method17().method13(var1, "| " + this.field4.method1("moveWithPrecision", new Object[0]), 80.0F, var5, -1);
      var1.pop();
   }

   private void method36(MixinHelper_4 var1, String var2, int var3, int var4) {
      CachedFontImpl var5 = FontRegistry.method17();
      float var6 = var5.method4(var2);
      com.moonsworth.lunar.client.ui.LcuiScreen.method117(var1, var3, var4, var6 + 4.0F, 10.0F, 2.0F, -1);
      var5.method13(var1, var2, var3 + 2, var4, -16777216);
   }

   private boolean method37() {
      return this.method21() != null;
   }

   private static boolean method38(float var0, float var1, float var2, float var3, boolean var4) {
      float var5 = Math.abs(var0 - var2) / var3;
      float var6 = Math.abs(var0 + var1 - var2) / var3;
      return Math.abs(var5 - var6) < 0.5F ? var4 : var5 > var6;
   }

   private static boolean method39(float var0, float var1, ThreadModuleDump60 var2) {
      return var0 >= var2.x - 2.0F && var0 <= var2.x + 7.0F && var1 >= var2.y - 2.0F && var1 <= var2.y + 7.0F;
   }

   private static boolean method40(MovableHudElement var0) {
      return var0.method12() > 16.0F && var0.method13() > 10.0F;
   }

   @Generated
   public HudEditorOverlay(HudEditorWidget var1, HudElementRegistry var2, HudEditorState var3) {
      this.field4 = var1;
      this.field5 = var2;
      this.field6 = var3;
   }

   @Generated
   public ColorAnimation method41() {
      return this.field9;
   }

   @Generated
   public List<MovableHudElement> getSelected() {
      return this.selected;
   }

   @Nullable
   @Generated
   public MovableHudElement method43() {
      return this.field19;
   }

   @Nullable
   @Generated
   public MovableHudElement method44() {
      return this.field20;
   }

   public static class Data {
      private final String field1;
      private final HudAnchor field2;
      private final float field3;
      private final float field4;
      private final float field5;
      private final boolean field6;
      private final long field7;

      @Generated
      public String method1() {
         return this.field1;
      }

      @Generated
      public HudAnchor method2() {
         return this.field2;
      }

      @Generated
      public float getScale() {
         return this.field3;
      }

      @Generated
      public float getX() {
         return this.field4;
      }

      @Generated
      public float getY() {
         return this.field5;
      }

      @Generated
      public boolean isEnabled() {
         return this.field6;
      }

      @Generated
      public long method3() {
         return this.field7;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof HudEditorOverlay.Data var2)) {
            return false;
         } else if (!var2.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getScale(), var2.getScale()) != 0) {
            return false;
         } else if (Float.compare(this.getX(), var2.getX()) != 0) {
            return false;
         } else if (Float.compare(this.getY(), var2.getY()) != 0) {
            return false;
         } else if (this.isEnabled() != var2.isEnabled()) {
            return false;
         } else if (this.method3() != var2.method3()) {
            return false;
         } else {
            String var3 = this.method1();
            String var4 = var2.method1();
            if (var3 == null ? var4 == null : var3.equals(var4)) {
               HudAnchor var5 = this.method2();
               HudAnchor var6 = var2.method2();
               return var5 == null ? var6 == null : var5.equals(var6);
            } else {
               return false;
            }
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof HudEditorOverlay.Data;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         var2 = var2 * 59 + Float.floatToIntBits(this.getScale());
         var2 = var2 * 59 + Float.floatToIntBits(this.getX());
         var2 = var2 * 59 + Float.floatToIntBits(this.getY());
         var2 = var2 * 59 + (this.isEnabled() ? 79 : 97);
         long var3 = this.method3();
         var2 = var2 * 59 + (int)(var3 >>> 32 ^ var3);
         String var5 = this.method1();
         var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
         HudAnchor var6 = this.method2();
         return var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      }

      @Generated
      @Override
      public String toString() {
         return "MovableEditor.LayoutSnapshot(movableId="
            + this.method1()
            + ", position="
            + this.method2()
            + ", scale="
            + this.getScale()
            + ", x="
            + this.getX()
            + ", y="
            + this.getY()
            + ", enabled="
            + this.isEnabled()
            + ", stepId="
            + this.method3()
            + ")";
      }

      @Generated
      public Data(String var1, HudAnchor var2, float var3, float var4, float var5, boolean var6, long var7) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
         this.field5 = var5;
         this.field6 = var6;
         this.field7 = var7;
      }
   }

   private static class Data2 {
      private float field1;
      private float field2;

      @Generated
      public float method1() {
         return this.field1;
      }

      @Generated
      public float method2() {
         return this.field2;
      }

      @Generated
      public void method3(float var1) {
         this.field1 = var1;
      }

      @Generated
      public void method4(float var1) {
         this.field2 = var1;
      }

      @Generated
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (!(var1 instanceof HudEditorOverlay.Data2 var2)) {
            return false;
         } else if (!var2.canEqual(this)) {
            return false;
         } else {
            return Float.compare(this.method1(), var2.method1()) != 0 ? false : Float.compare(this.method2(), var2.method2()) == 0;
         }
      }

      @Generated
      protected boolean canEqual(Object var1) {
         return var1 instanceof HudEditorOverlay.Data2;
      }

      @Generated
      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         var2 = var2 * 59 + Float.floatToIntBits(this.method1());
         return var2 * 59 + Float.floatToIntBits(this.method2());
      }

      @Generated
      @Override
      public String toString() {
         return "MovableEditor.DragData(dragX=" + this.method1() + ", dragY=" + this.method2() + ")";
      }

      @Generated
      public Data2(float var1, float var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }
}
