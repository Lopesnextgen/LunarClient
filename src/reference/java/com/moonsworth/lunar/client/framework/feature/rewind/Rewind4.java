package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2_4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.chest.Chest2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.holograms.Holograms2;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class Rewind4 {
   private final Map<ModifierKeybindOption, Rewind> field1 = new HashMap<>();
   private final Map<Rewind2, Rewind> field2 = new HashMap<>();
   private final Set<ModifierKeybindOption> field3 = new HashSet<>();

   public Rewind4() {
      LoadingStageImpl var1 = ThreadModuleDump63.method4().method90();
      this.field1.put(var1.method25(), this::method4);
      this.field1.put(var1.method26(), this::method6);
      this.field1.put(var1.method27(), this::method7);
      this.field1.put(var1.method28(), this::method8);
      this.field1.put(var1.method29(), this::method9);
      this.field1.put(var1.method30(), this::method10);
      this.field1.put(var1.method31(), this::method11);
      this.field1.put(var1.method32(), this::method12);
      this.field1.put(var1.method33(), this::method13);
      this.field1.put(var1.method34(), this::method14);
      this.field1.put(var1.method35(), this::method15);
      this.field1.put(var1.method36(), this::method16);
      this.field1.put(var1.method37(), this::method17);
      this.field1.put(var1.method38(), this::method18);
      this.field3.add(var1.method32());
      this.field3.add(var1.method33());
      this.field3.add(var1.method26());
      this.field3.add(var1.method27());
      this.field2.put(this.method1(KeyCode.KEY_ESCAPE), this::method5);
      this.field2.put(this.method2(KeyCode.KEY_W, true, false, false), var2 -> {
         if (var1.method26().isDefault()) {
            this.method6(var2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_Z, true, true, false), var2 -> {
         if (var1.method27().isDefault()) {
            this.method7(var2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_W, true, true, false), var2 -> {
         if (var1.method27().isDefault()) {
            this.method7(var2);
         }
      });
      this.field2.put(this.method1(KeyCode.KEY_DELETE), var2 -> {
         if (var1.method34().isDefault()) {
            this.method14(var2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_DELETE, true, false, false), var2 -> {
         if (var1.method35().isDefault()) {
            this.method15(var2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_BACK, false, true, false), var2 -> {
         if (var1.method35().isDefault()) {
            this.method15(var2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_DELETE, false, true, false), var2 -> {
         if (var1.method35().isDefault()) {
            this.method15(var2);
         }
      });
   }

   private Rewind2 method1(KeyCode var1) {
      return new Rewind2(var1, false, false, false);
   }

   private Rewind2 method2(KeyCode var1, boolean var2, boolean var3, boolean var4) {
      return new Rewind2(var1, var2, var3, var4);
   }

   public void method3(Rewind2 var1, Rewind3_3 var2, boolean var3) {
      for (Entry var5 : this.field1.entrySet()) {
         if ((!var3 || this.field3.contains(var5.getKey()))
            && ((ModifierKeybindOption)var5.getKey()).method8().equals(var1.method1())
            && ((KeyCombo)((ModifierKeybindOption)var5.getKey()).get()).method7() == var1.method2()
            && ((KeyCombo)((ModifierKeybindOption)var5.getKey()).get()).method6() == var1.method3()
            && ((KeyCombo)((ModifierKeybindOption)var5.getKey()).get()).method5() == var1.method4()) {
            ((Rewind)var5.getValue()).run(var2);
         }
      }

      Rewind var6 = this.field2.get(var1);
      if (var6 != null) {
         var6.run(var2);
      }
   }

   private void method4(Rewind3_3 var1) {
      if (!var1.method1().method62()) {
         DriverViewportLegacy.method50().method14("rewind-toggle-fullscreen");
      }
   }

   private void method5(Rewind3_3 var1) {
      if (var1.method1().method62()) {
         var1.method3().endBatch();
         DriverViewportLegacy.method50().method55().method6();
         var1.method1().method54(false);
         ThreadModuleDump63.method3().bridge$setInGameFocus(false);
         Bridge5Extension6 var2 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         if (var2 != null) {
            var2.bridge$setAllowUserInput(false);
         }
      }
   }

   private void method6(Rewind3_3 var1) {
      var1.method3().undo();
      var1.method1().method56(true);
      if (!Coordinates.containsLayer(Coordinates.getSelectedLayer())) {
         Coordinates.setSelectedLayer(null);
      }

      var1.method1().method27();
      DriverViewportLegacy.method50().tick();
   }

   private void method7(Rewind3_3 var1) {
      var1.method3().redo();
      var1.method1().method56(true);
      if (!Coordinates.containsLayer(Coordinates.getSelectedLayer())) {
         Coordinates.setSelectedLayer(null);
      }

      var1.method1().method27();
      DriverViewportLegacy.method50().tick();
   }

   private void method8(Rewind3_3 var1) {
      Holograms2.save();
   }

   private void method9(Rewind3_3 var1) {
      if (Coordinates.getSelectedLayer() != null && var1.method5() != null && var1.method4() != null) {
         var1.method3().method1();
         Coordinates.setCopiedLayer(Coordinates.getSelectedLayer());
         var1.method3().method2();
         Coordinates.setCopiedRange(var1.method5().getKey());
         Coordinates.setCopiedTrack(var1.method4());
      }
   }

   private void method10(Rewind3_3 var1) {
      if (Coordinates.getCopiedLayer() != null) {
         RewindIterator var2 = (RewindIterator)Coordinates.getCopiedLayer().method9(var1.method3(), Coordinates.getCopiedRange(), Coordinates.getCopiedRange());
         var2.method23(null);
         ((Highlight<RewindIterator>)Coordinates.getCopiedTrack()
            .method5())
            .method1(
               Range.between(
                  var1.method2().method15(),
                  var1.method2().method15() + (Integer)Coordinates.getCopiedRange().getMaximum() - (Integer)Coordinates.getCopiedRange().getMinimum()
               ),
               var2
            );
         Coordinates.refreshTimeline();
      }
   }

   private void method11(Rewind3_3 var1) {
      var1.method2().setPaused(!var1.method2().isPaused());
      if (!var1.method2().isPaused()) {
         Coordinates.setSelectedKeyframe(null);
         Coordinates.refreshTimeline();
      }

      Coordinates.refreshPlaybackState();
   }

   private void method12(Rewind3_3 var1) {
      var1.method2().setPaused(true);
      var1.method2().method8(Math.max(0, var1.method2().method15() - 1));
      Coordinates.refreshPlaybackState();
   }

   private void method13(Rewind3_3 var1) {
      var1.method2().method8(var1.method2().method15() + 1);
      Coordinates.refreshPlaybackState();
   }

   private void method14(Rewind3_3 var1) {
      if (var1.method5() != null) {
         var1.method3().method1();
         Highlight2 var2 = var1.method2().method11().method6();
         HashSet var3 = new HashSet();

         for (UUID var5 : Coordinates.collectLinkedLayers(null)) {
            Entry var6 = var1.method2().method1().get(var5);
            if (var6 != null) {
               for (Gui_2 var8 : var1.method2().method11()) {
                  if (var8.method5().method11().containsValue(var6.getValue())) {
                     var8.method5().method8((Range<Integer>)var6.getKey());
                     var3.add(var5);
                     break;
                  }
               }
            }
         }

         var2.method10(var1.method3(), var3);
         Coordinates.setSelectedLayer(null);
         var1.method3().endBatch();
      } else if (Coordinates.getSelectedKeyframe() != null) {
         var1.method3().method1();
         Coordinates.getSelectedKeyframe().method2().method27().remove(Coordinates.getSelectedKeyframe().method3());

         for (Coordinates4 var10 : Coordinates.getAdditionalSelectedKeyframes()) {
            var10.method2().method27().remove(var10.method3());
         }

         if (Coordinates.getSelectedKeyframe().method2().method27().isEmpty()) {
            Coordinates.getSelectedKeyframe().method2().reset();
         }

         var1.method3().endBatch();
         Coordinates.setSelectedKeyframe(null);
         Coordinates.refreshProperties();
      }

      Coordinates.refreshTimeline();
   }

   private void method15(Rewind3_3 var1) {
      var1.method3().method1();
      Range var2 = var1.method5().getKey();

      for (Gui_2 var4 : var1.method2().method11()) {
         var4.method5().method9(var2);
      }

      var1.method3().endBatch();
      var1.method2().method8(var1.method2().method15() - (Math.min(var1.method2().method15(), (Integer)var2.getMaximum()) - (Integer)var2.getMinimum()));
      Coordinates.setSelectedLayer(null);
      Coordinates.refreshTimeline();
   }

   private void method16(Rewind3_3 var1) {
      Chest2.cut(var1.method5() != null ? var1.method5().getValue().getId().toString() : "");
   }

   private void method17(Rewind3_3 var1) {
      RewindHandlers var2 = var1.method1();
      if (!var2.method57().method25()) {
         var2.method34(!var2.method44());
      }
   }

   private void method18(Rewind3_3 var1) {
      RewindHandlers var2 = var1.method1();
      if (!var2.method57().method25()) {
         Rewindhandlers2_4.exportScreenshot();
      }
   }
}
