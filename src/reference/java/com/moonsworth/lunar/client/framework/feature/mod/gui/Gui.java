package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.hud.HudEditorWidget;
import com.moonsworth.lunar.client.ui.hud.HudEditorOverlay;
import com.moonsworth.lunar.client.ui.hud.MovableHudElement;
import com.moonsworth.lunar.client.ui.hud.EditorShortcut;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.Highlight4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.Highlight5;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.misc.guiscale.GuiScale;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;

@Annotation2(min = 33)
public class Gui extends com.moonsworth.lunar.client.ui.LcuiScreen implements HudEditorWidget {
   private static final ResourceLocationBridge field19 = ResourceLocationBridge.create("minecraft", "textures/gui/container/inventory.png");
   private static final List<EditorShortcut> field20 = List.of(
      EditorShortcut.method1("Mouse1", "hold", "addButtonsToRegion"),
      EditorShortcut.method1("Mouse1", "hold", "selectDragButtons"),
      EditorShortcut.method1("Mouse2", "click", "resetToClosest"),
      EditorShortcut.method2("CTRL", "Mouse1", null, "toggleButtonSelection"),
      EditorShortcut.method2("CTRL", "Z", null, "undoMovements"),
      EditorShortcut.method2("CTRL", "Y", null, "redoMovements"),
      EditorShortcut.method2("CTRL", "C", null, "copyButtons"),
      EditorShortcut.method2("CTRL", "V", null, "pasteButtons")
   );
   private final HudEditorOverlay field21;
   private final Bridge7Iterator2 field22;
   private final TextLabelWidget field23;
   private final List<Highlight5> field24 = new ArrayList<>();

   public Gui() {
      this.field22 = new Bridge7Iterator2();
      this.field21 = new HudEditorOverlay(this, this.field22, new Bridge7Iterator22(this, this.field22));
      this.field23 = new Gui.Data3();
      this.field23.setTextColor(-1);
      this.field23.method12(new AnimatedValue(1879048192, -1879048192));
      this.field23.method11(new AnimatedValue(1076176165, -1711276033));
      this.field23.method18(2.0F);
      this.field23.setText("addNewInventoryButton");
      this.field23.method4((var1, var2) -> {
         if (this.field21.method43() == null && var2 == 0) {
            this.field21.method10();
            method10().add(Highlight4.method2(new JsonObject()));
            return true;
         } else {
            return false;
         }
      });
      this.field11.addAll(this.field21.method1());
      this.field11.add(this.field23);
   }

   public boolean method3(Data2 var1) {
      return this.field23.method3(var1);
   }

   public List<EditorShortcut> method5() {
      return field20;
   }

   public String getLanguagePath() {
      return super.getLanguagePath() + ".skyblockInventoryButtonsMovement";
   }

   protected List<GuiWidget> method25() {
      return List.of();
   }

   public void init() {
      this.field23
         .method2(
            this.method22() / 2.0F - 50.0F, this.method23() / 2.0F - 14.0F, 100.0F, 28.0F
         );
      this.field21.init();
   }

   public void update() {
      this.field21.update();
   }

   public void method10(MixinHelper_4 var1, Data2 var2) {
      this.method10(var1);
      if (!Bridge7Iterator222.method18()) {
         for (Highlight5 var4 : method10()) {
            var4.method11(var1, false, com.moonsworth.lunar.client.ui.LcuiScreen.getScale());
         }

         for (Highlight5 var6 : method10()) {
            if (var6.method14(var2)) {
               var6.method13(var1, false, com.moonsworth.lunar.client.ui.LcuiScreen.getScale());
            }
         }
      }

      this.field21.method2(var1, var2);
   }

   public void method11(Data2 var1, int var2) {
      this.field21.method3(var1, var2);
   }

   public void method12(Data2 var1, int var2) {
      this.field21.method4(var1, var2);
   }

   public void method14(char var1, KeyCode var2) {
      if (com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown()) {
         if (var2 == KeyCode.KEY_C && !this.field21.getSelected().isEmpty()) {
            this.field24.clear();

            for (MovableHudElement var4 : this.field21.getSelected()) {
               this.field22.method3(var4.id()).ifPresent(this.field24::add);
            }
         }

         if (var2 == KeyCode.KEY_V && !this.field24.isEmpty()) {
            ArrayList var7 = new ArrayList();

            for (Highlight5 var5 : this.field24) {
               Highlight5 var6 = Highlight5.method1(var5);
               method10().add(var6);
               var7.add(this.field22.method4(var6));
            }

            this.field21.method8(var7);
            ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         }
      }

      this.field21.method5(var1, var2);
   }

   public void close() {
      ThreadModuleDump63.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   public void method9() {
      this.field21.method10();
   }

   private static List<Highlight5> method10() {
      return Bridge7Iterator2.method7();
   }

   private void method10(MixinHelper_4 var1) {
      var1.push();
      float var2 = 1.0F / com.moonsworth.lunar.client.ui.LcuiScreen.getScale();
      float var3 = this.method22();
      float var4 = this.method23();
      GuiScale var5 = ThreadModuleDump63.method4().method40().method96();
      if (var5.isEnabled()) {
         int var6 = com.moonsworth.lunar.client.ui.LcuiScreen.method151().method3();
         float var7 = (float)((Integer)var5.method15().get()).intValue() / var6;
         var1.scale(var7, var7, 1.0F);
         var3 /= var7;
         var4 /= var7;
      }

      short var11 = 176;
      short var12 = 166;
      float var8 = (var3 - var11 * var2) / 2.0F;
      float var9 = (var4 - var12 * var2) / 2.0F;
      float var10 = com.moonsworth.lunar.client.ui.LcuiScreen.getScale();
      var8 *= var10;
      var9 *= var10;
      var1.method38(var8, var9, 0.0F);
      com.moonsworth.lunar.client.ui.LcuiScreen.method37(var1, field19, 0.0F, 0.0F, 256.0F, 256.0F, 0.0F, 0.0F, var11, var12, -1);
      var1.pop();
   }

   private final class Data3 extends TextLabelWidget {
      private Data3() {
         super(null, "", FontRegistry.field16);
      }

      public boolean method1(Data2 var1) {
         return Gui.this.field21.method43() == null && super.method3(var1);
      }
   }
}
