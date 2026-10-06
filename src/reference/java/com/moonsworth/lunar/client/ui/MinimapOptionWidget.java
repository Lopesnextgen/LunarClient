package com.moonsworth.lunar.client.ui;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextFieldWidget;
import com.moonsworth.lunar.client.ui.widget.AttachedPanel;
import com.moonsworth.lunar.client.framework.feature.minimap.Minimap2_2;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.hud.bossbar.Bossbar;
import com.moonsworth.lunar.client.mod.render.crosshair.Crosshair;
import com.moonsworth.lunar.client.mod.render.minimap.Minimap;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import lombok.Generated;
import org.joml.Vector2f;

public class MinimapOptionWidget extends com.moonsworth.lunar.client.ui.widget.WidgetPanel {
   private Minimap field19;
   private float field20;
   private boolean field21 = false;
   private float field22;
   private float field23;
   private float field24;
   private float field25;
   private boolean field26 = false;
   private boolean field27 = false;
   private boolean field28 = false;
   private Minimap2_2<?> field29 = null;
   private int field30 = -1;
   private float field31 = -1.0F;
   private float field32 = -1.0F;
   private final MinimapScreen field33;

   public MinimapOptionWidget(MinimapScreen var1) {
      super(null);
      this.field33 = var1;
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      this.method4((var1x, var2x) -> {
         if (this.HRORICORIHHHRICRIRCIIOHCRIRRHI(var1x, var2x)) {
            this.method4(null);
            return false;
         }

         this.field30 = var2x;
         this.field31 = var1x.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
         this.field32 = var1x.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
         if (this.field29 != null && var2x == 1) {
            this.field29.method3();
         }

         this.field21 = this.method3(var1x);
         this.field22 = var1x.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
         this.field23 = var1x.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
         return false;
      });
      this.method17(
         (var1x, var2x) -> {
            if (this.method1(var1x, var2x)) {
               return false;
            }

            if (this.field29 != null) {
               if (this.field30 == 0) {
                  if (Math.abs(this.field31 - var1x.HHHCHORHIHRCOHIOICICICHCRRICCI()) < 2.0
                     && Math.abs(this.field32 - var1x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO()) < 2.0) {
                     final LinkedHashMap var3x = this.field29.method6();
                     if (var3x != null) {
                        this.method4(
                           new AttachedPanel(this) {
                              @Override
                              protected List<GuiWidget> method5() {
                                 ArrayList var1x = new ArrayList();

                                 for (Entry var3xx : var3x.entrySet()) {
                                    TextFieldWidget var4x;
                                    var1x.add(
                                       var4x = new TextFieldWidget(
                                          this.field18, this.OHROCHICOIOICHOCRROORRCIIICIHO((String)var3xx.getKey(), new Object[0]), -1
                                       )
                                    );
                                    var4x.method4((var1xx, var2x) -> {
                                       ((Runnable)var3.getValue()).run();
                                       return true;
                                    });
                                 }

                                 return var1x;
                              }
                           }
                        );
                        this.method16().method2(var1x);
                     }
                  }
               } else if (this.field30 == 1) {
                  this.field29.method5();
               }
            }

            this.field30 = -1;
            this.field31 = -1.0F;
            this.field32 = -1.0F;
            this.field21 = false;
            return false;
         }
      );
      this.method5(0);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      com.moonsworth.lunar.client.framework.feature.minimap.Minimap var4 = this.field19.getMinimapManager();
      this.method17();
      BridgeExtension var5 = this.field14.bridge$getRenderViewEntity();
      float var6 = var4.getTextureWidth() * this.field20;
      float var7 = var4.getTextureHeight() * this.field20;
      if (this.field21 && !this.field26) {
         float var8 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() - this.field22;
         float var9 = var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - this.field23;
         this.field22 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
         this.field23 = var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
         if (this.field29 != null && this.field30 == 1) {
            this.field29.method4(var8 / this.field20, var9 / this.field20);
         } else {
            this.field24 += var8;
            this.field25 += var9;
         }
      }

      float var23 = 8.0F * this.field20;
      boolean var24 = this.field24 < -var23 || this.field24 > var23;
      boolean var10 = this.field25 < -var23 || this.field25 > var23;
      int var11 = (int)Math.ceil(this.field24 / this.field20 / 16.0F) * 16;
      int var12 = (int)Math.ceil(this.field25 / this.field20 / 16.0F) * 16;
      if (!this.field26) {
         if (var24 || var10) {
            int var13 = var24 ? -var11 : 0;
            int var14 = var10 ? -var12 : 0;
            this.field27 = var24;
            this.field28 = var10;
            var4.method8(var4.method17() + var13, var4.method18() + var14, false);
            this.field26 = true;
         }
      } else if (var4.method17() == var4.method19() && var4.method18() == var4.method20()) {
         this.field26 = false;
         if (this.field27) {
            this.field27 = false;
            this.field24 = this.field24 - var11 * this.field20;
         }

         if (this.field28) {
            this.field28 = false;
            this.field25 = this.field25 - var12 * this.field20;
         }
      }

      var1.push();
      if (!this.field33.method6()) {
         int var25 = Math.max(2, Math.min(ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getRenderDistance(), 16));
         float var27 = this.method10(var25);
         float var15 = (this.width - var4.getTextureWidth() * var27) / 2.0F + 8.0F * var27;
         float var16 = (this.height - var4.getTextureHeight() * var27) / 2.0F + 8.0F * var27;
         float var17 = (var4.getTextureWidth() - 16) * var27;
         float var18 = (var4.getTextureHeight() - 16) * var27;
         com.moonsworth.lunar.client.ui.LcuiScreen.method111(var1, var15, var16, var17, var18, 1.0F);
      }

      com.moonsworth.lunar.client.ui.LcuiScreen.method66(var1, 0.0F, 0.0F, this.width, this.height, -16777216);
      var1.method38((float)((this.width - var6) / 2.0), (float)((this.height - var7) / 2.0), 0.0F);
      var1.method38(this.field24, this.field25, 0.0F);
      var1.scale(this.field20, this.field20, 1.0F);
      var4.method3(var1, var5);
      var4.method4(var1, var5, false);
      var1.method38((float)(var6 / 2.0 / this.field20), (float)(var7 / 2.0 / this.field20), 0.0F);
      float var26 = this.method6() + (var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() - this.width / 2.0F) / this.field20;
      float var28 = this.method7() + (var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - this.height / 2.0F) / this.field20;
      if (var5 != null) {
         double var29 = ThreadModuleDump67.method15(
            var5.method3(), var5.bridge$getPosX(), ThreadModuleDump63.method3().bridge$isGamePaused() ? 1.0F : var1.method43()
         );
         double var32 = ThreadModuleDump67.method15(
            var5.method5(), var5.bridge$getPosZ(), ThreadModuleDump63.method3().bridge$isGamePaused() ? 1.0F : var1.method43()
         );
         double var19 = (ThreadModuleDump67.method9(var29) & -16) - var4.method19();
         double var21 = (ThreadModuleDump67.method9(var32) & -16) - var4.method20();
         var1.method38((float)(this.method4(var29) + var19), (float)(this.method4(var32) + var21), 0.0F);
         var26 -= (float)var29;
         var28 -= (float)var32;
      }

      if (this.field14.bridge$getPlayer() != null) {
         this.field19.renderPlayerMarker(var1, var5 == null ? 0.0F : (float)var5.bridge$getRotationYaw(), false);
      }

      Minimap2_2 var30 = this.field19.renderOverlays(var1, 0.0F, this.method3(var2) ? new Vector2f(var26, var28) : null);
      var1.pop();
      if (!this.field33.method6()) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
      }

      var1.method44(var0 -> {
         var0.method29().method33();
         var0.method29().method14();
      });
      Crosshair var31 = ThreadModuleDump63.method4().method40().method33();
      if (var31.isEnabled()) {
         var31.field8.method15(var1, new MarkerModel.Data2(this.width / 2.0F, this.height / 2.0F), false);
      } else {
         com.moonsworth.lunar.client.ui.LcuiScreen.method46(
            var1, Bossbar.field9, this.width / 2.0F - 7.0F, this.height / 2.0F - 7.0F, 0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 256.0F, 256
         );
      }

      if (this.field30 == -1 || this.field29 == null) {
         this.field29 = var30;
         if (this.field29 != null && this.method16() == null) {
            this.field29.method2(this, var1, var2);
         }
      }

      super.method3(var1, var2, var3);
   }

   private boolean method3(MarkerModel.Data2 var1) {
      float var2 = (this.field19.getMinimapManager().getTextureWidth() * this.field20 - 16.0F * this.field20) / 2.0F;
      float var3 = (this.field19.getMinimapManager().getTextureHeight() * this.field20 - 16.0F * this.field20) / 2.0F;
      return var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= this.width / 2.0F - var2
         && var1.IIRCROICCRROCOCOIOIHHOCRHOIHIR() <= this.width / 2.0F + var2
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() >= this.height / 2.0F - var3
         && var1.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() <= this.height / 2.0F + var3;
   }

   public double method4(double var1) {
      return var1 >= 0.0 ? var1 % 16.0 - 8.0 : var1 % 16.0 + 8.0;
   }

   public float method6() {
      return this.field19.getMinimapManager().method19() - this.field24 / this.field20 + 8.0F;
   }

   public float method7() {
      return this.field19.getMinimapManager().method20() - this.field25 / this.field20 + 8.0F;
   }

   @Override
   protected List method5() {
      return List.of();
   }

   @Override
   public void update() {
      super.update();
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 != null && this.field33.method10()) {
         this.field19.getMinimapManager().method8(ThreadModuleDump67.method9(var1.bridge$getPosX()), ThreadModuleDump67.method9(var1.bridge$getPosZ()), false);
      }
   }

   @Override
   public boolean method5(int var1) {
      float var2 = 0.1F * this.field20;
      if (var1 > 0) {
         this.field20 += var2;
         this.method4(null);
      } else if (var1 < 0) {
         this.field20 -= var2;
         this.method4(null);
      }

      if (this.field33.method6()) {
         this.field20 = ThreadModuleDump67.method3(this.field20, 1.1F, 10.0F);
      } else {
         int var3 = Math.max(2, Math.min(ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getRenderDistance(), 16));
         this.field20 = ThreadModuleDump67.method3(this.field20, this.method10(var3) + 0.2F, 10.0F + 5.0F * (1.0F - var3 / 16.0F));
      }

      return true;
   }

   public float method10(int var1) {
      return this.height / 1.5F / (this.field19.getMinimapManager().getTextureHeight() - 16.0F * (1.0F - var1 / 16.0F));
   }

   public void method15() {
      this.field20 = 1.0F;
      this.method17();
      this.method5(0);
   }

   private void method17() {
      if (this.field33.method6()) {
         this.field19.getMinimapManager().method9((int)Math.ceil(this.width / 16.0F) + 4, (int)Math.ceil(this.height / 16.0F) + 4);
      } else {
         int var1 = Math.max(2, Math.min(14, this.field14.bridge$getGameSettings().bridge$getRenderDistance() * 2));
         this.field19.getMinimapManager().method9(var1, var1);
      }
   }

   @Override
   public boolean method13() {
      return false;
   }

   @Override
   public String getLanguagePath() {
      return "gui.minimap";
   }

   @Generated
   public void method14(Minimap var1) {
      this.field19 = var1;
   }

   @Generated
   public float method18() {
      return this.field20;
   }

   @Generated
   public float method19() {
      return this.field24;
   }

   @Generated
   public float method20() {
      return this.field25;
   }
}
