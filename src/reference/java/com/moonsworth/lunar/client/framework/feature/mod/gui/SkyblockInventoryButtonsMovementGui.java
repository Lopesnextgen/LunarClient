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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.HighlightSerializer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.HighlightButton;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.misc.guiscale.GuiScale;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;

@VersionGate(min = 33)
public class SkyblockInventoryButtonsMovementGui extends com.moonsworth.lunar.client.ui.LcuiScreen implements HudEditorWidget {
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
   private final InventoryButtonRegistry field22;
   private final TextLabelWidget field23;
   private final List<HighlightButton> field24 = new ArrayList<>();

   public SkyblockInventoryButtonsMovementGui() {
      this.field22 = new InventoryButtonRegistry();
      this.field21 = new HudEditorOverlay(this, this.field22, new ScreenButtonGroup(this, this.field22));
      this.field23 = new SkyblockInventoryButtonsMovementGui.AddInventoryButton();
      this.field23.setTextColor(-1);
      this.field23.method12(new AnimatedValue(1879048192, -1879048192));
      this.field23.method11(new AnimatedValue(1076176165, -1711276033));
      this.field23.method18(2.0F);
      this.field23.setText("addNewInventoryButton");
      this.field23.method4((arg1, arg2) -> {
         if (this.field21.method43() == null && arg2 == 0) {
            this.field21.method10();
            method10().add(HighlightSerializer.method2(new JsonObject()));
            return true;
         } else {
            return false;
         }
      });
      this.field11.addAll(this.field21.method1());
      this.field11.add(this.field23);
   }

   public boolean method3(Data2 data21) {
      return this.field23.method3(data21);
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
         .RIIICIRHRCIHOOOORHOICRIICCCRHR(
            this.method22() / 2.0F - 50.0F, this.method23() / 2.0F - 14.0F, 100.0F, 28.0F
         );
      this.field21.init();
   }

   public void update() {
      this.field21.update();
   }

   public void method10(MixinHelper_4 mixinhelper_41, Data2 data22) {
      this.method10(mixinhelper_41);
      if (!InventoryButtonWidget.method18()) {
         for (HighlightButton highlight54 : method10()) {
            highlight54.method11(mixinhelper_41, false, com.moonsworth.lunar.client.ui.LcuiScreen.getScale());
         }

         for (HighlightButton highlight56 : method10()) {
            if (highlight56.method14(data22)) {
               highlight56.method13(mixinhelper_41, false, com.moonsworth.lunar.client.ui.LcuiScreen.getScale());
            }
         }
      }

      this.field21.method2(mixinhelper_41, data22);
   }

   public void method11(Data2 data21, int number2) {
      this.field21.method3(data21, number2);
   }

   public void method12(Data2 data21, int number2) {
      this.field21.method4(data21, number2);
   }

   public void method14(char character1, KeyCode bridgetype_82) {
      if (com.moonsworth.lunar.client.ui.LcuiScreen.isCtrlKeyDown()) {
         if (bridgetype_82 == KeyCode.KEY_C && !this.field21.getSelected().isEmpty()) {
            this.field24.clear();

            for (MovableHudElement lotusfish24 : this.field21.getSelected()) {
               this.field22.method3(lotusfish24.id()).ifPresent(this.field24::add);
            }
         }

         if (bridgetype_82 == KeyCode.KEY_V && !this.field24.isEmpty()) {
            ArrayList list7 = new ArrayList();

            for (HighlightButton highlight55 : this.field24) {
               HighlightButton highlight56 = HighlightButton.method1(highlight55);
               method10().add(highlight56);
               list7.add(this.field22.method4(highlight56));
            }

            this.field21.method8(list7);
            Ref.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
         }
      }

      this.field21.method5(character1, bridgetype_82);
   }

   public void close() {
      Ref.method4().method40().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   public void method9() {
      this.field21.method10();
   }

   private static List<HighlightButton> method10() {
      return InventoryButtonRegistry.method7();
   }

   private void method10(MixinHelper_4 mixinhelper_41) {
      mixinhelper_41.push();
      float value2 = 1.0F / com.moonsworth.lunar.client.ui.LcuiScreen.getScale();
      float value3 = this.method22();
      float value4 = this.method23();
      GuiScale guiscale5 = Ref.method4().method40().method96();
      if (guiscale5.isEnabled()) {
         int number6 = com.moonsworth.lunar.client.ui.LcuiScreen.method151().method3();
         float value7 = (float)((Integer)guiscale5.method15().get()).intValue() / number6;
         mixinhelper_41.scale(value7, value7, 1.0F);
         value3 /= value7;
         value4 /= value7;
      }

      short number11 = 176;
      short number12 = 166;
      float value8 = (value3 - number11 * value2) / 2.0F;
      float value9 = (value4 - number12 * value2) / 2.0F;
      float value10 = com.moonsworth.lunar.client.ui.LcuiScreen.getScale();
      value8 *= value10;
      value9 *= value10;
      mixinhelper_41.method38(value8, value9, 0.0F);
      com.moonsworth.lunar.client.ui.LcuiScreen.method37(mixinhelper_41, field19, 0.0F, 0.0F, 256.0F, 256.0F, 0.0F, 0.0F, number11, number12, -1);
      mixinhelper_41.pop();
   }

   private final class AddInventoryButton extends TextLabelWidget {
      private AddInventoryButton() {
         super(null, "", FontRegistry.field16);
      }

      public boolean method1(Data2 data21) {
         return SkyblockInventoryButtonsMovementGui.this.field21.method43() == null && super.method3(data21);
      }
   }
}
