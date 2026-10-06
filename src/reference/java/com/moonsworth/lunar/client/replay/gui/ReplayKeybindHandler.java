package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.SegmentTimeline;
import com.moonsworth.lunar.client.replay.timeline.SegmentLinkManager;
import com.moonsworth.lunar.client.replay.export.ExportSettingsJsApi;
import com.moonsworth.lunar.client.replay.gui.RewindTimelineBridge;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.SelectedKeyframe;
import com.moonsworth.lunar.client.replay.gui.RewindEditorBridge;
import com.moonsworth.lunar.client.config.option.KeyBind;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;

public class ReplayKeybindHandler {
   private final Map<ModifierKeybindOption, ReplayAction> field1 = new HashMap<>();
   private final Map<KeybindCombination, ReplayAction> field2 = new HashMap<>();
   private final Set<ModifierKeybindOption> field3 = new HashSet<>();

   public ReplayKeybindHandler() {
      LoadingStageImpl fogloader21 = Ref.method4().method90();
      this.field1.put(fogloader21.method25(), this::method4);
      this.field1.put(fogloader21.method26(), this::method6);
      this.field1.put(fogloader21.method27(), this::method7);
      this.field1.put(fogloader21.method28(), this::method8);
      this.field1.put(fogloader21.method29(), this::method9);
      this.field1.put(fogloader21.method30(), this::method10);
      this.field1.put(fogloader21.method31(), this::method11);
      this.field1.put(fogloader21.method32(), this::method12);
      this.field1.put(fogloader21.method33(), this::method13);
      this.field1.put(fogloader21.method34(), this::method14);
      this.field1.put(fogloader21.method35(), this::method15);
      this.field1.put(fogloader21.method36(), this::method16);
      this.field1.put(fogloader21.method37(), this::method17);
      this.field1.put(fogloader21.method38(), this::method18);
      this.field3.add(fogloader21.method32());
      this.field3.add(fogloader21.method33());
      this.field3.add(fogloader21.method26());
      this.field3.add(fogloader21.method27());
      this.field2.put(this.method1(KeyCode.KEY_ESCAPE), this::method5);
      this.field2.put(this.method2(KeyCode.KEY_W, true, false, false), arg2 -> {
         if (fogloader21.method26().isDefault()) {
            this.method6(arg2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_Z, true, true, false), arg2 -> {
         if (fogloader21.method27().isDefault()) {
            this.method7(arg2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_W, true, true, false), arg2 -> {
         if (fogloader21.method27().isDefault()) {
            this.method7(arg2);
         }
      });
      this.field2.put(this.method1(KeyCode.KEY_DELETE), arg2 -> {
         if (fogloader21.method34().isDefault()) {
            this.method14(arg2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_DELETE, true, false, false), arg2 -> {
         if (fogloader21.method35().isDefault()) {
            this.method15(arg2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_BACK, false, true, false), arg2 -> {
         if (fogloader21.method35().isDefault()) {
            this.method15(arg2);
         }
      });
      this.field2.put(this.method2(KeyCode.KEY_DELETE, false, true, false), arg2 -> {
         if (fogloader21.method35().isDefault()) {
            this.method15(arg2);
         }
      });
   }

   private KeybindCombination method1(KeyCode bridgetype_81) {
      return new KeybindCombination(bridgetype_81, false, false, false);
   }

   private KeybindCombination method2(KeyCode bridgetype_81, boolean flag2, boolean flag3, boolean flag4) {
      return new KeybindCombination(bridgetype_81, flag2, flag3, flag4);
   }

   public void method3(KeybindCombination rewind21, TimelineSelection rewind3_32, boolean flag3) {
      for (Entry entry5 : this.field1.entrySet()) {
         if ((!flag3 || this.field3.contains(entry5.getKey()))
            && ((ModifierKeybindOption)entry5.getKey()).method8().equals(rewind21.method1())
            && ((KeyBind)((ModifierKeybindOption)entry5.getKey()).get()).method7() == rewind21.method2()
            && ((KeyBind)((ModifierKeybindOption)entry5.getKey()).get()).method6() == rewind21.method3()
            && ((KeyBind)((ModifierKeybindOption)entry5.getKey()).get()).method5() == rewind21.method4()) {
            ((ReplayAction)entry5.getValue()).run(rewind3_32);
         }
      }

      ReplayAction rewind6 = this.field2.get(rewind21);
      if (rewind6 != null) {
         rewind6.run(rewind3_32);
      }
   }

   private void method4(TimelineSelection rewind3_31) {
      if (!rewind3_31.method1().method62()) {
         DriverViewportLegacy.method50().method14("rewind-toggle-fullscreen");
      }
   }

   private void method5(TimelineSelection rewind3_31) {
      if (rewind3_31.method1().method62()) {
         rewind3_31.method3().endBatch();
         DriverViewportLegacy.method50().method55().method6();
         rewind3_31.method1().method54(false);
         Ref.method3().bridge$setInGameFocus(false);
         GuiScreenBridge bridge5extension62 = Ref.method3().bridge$getCurrentScreen();
         if (bridge5extension62 != null) {
            bridge5extension62.bridge$setAllowUserInput(false);
         }
      }
   }

   private void method6(TimelineSelection rewind3_31) {
      rewind3_31.method3().undo();
      rewind3_31.method1().method56(true);
      if (!RewindEditorContext.setSelectedKeyframe8(RewindEditorContext.getSelectedLayer())) {
         RewindEditorContext.setSelectedLayer(null);
      }

      rewind3_31.method1().method27();
      DriverViewportLegacy.method50().tick();
   }

   private void method7(TimelineSelection rewind3_31) {
      rewind3_31.method3().redo();
      rewind3_31.method1().method56(true);
      if (!RewindEditorContext.setSelectedKeyframe8(RewindEditorContext.getSelectedLayer())) {
         RewindEditorContext.setSelectedLayer(null);
      }

      rewind3_31.method1().method27();
      DriverViewportLegacy.method50().tick();
   }

   private void method8(TimelineSelection rewind3_31) {
      RewindEditorBridge.save();
   }

   private void method9(TimelineSelection rewind3_31) {
      if (RewindEditorContext.getSelectedLayer() != null && rewind3_31.method5() != null && rewind3_31.method4() != null) {
         rewind3_31.method3().method1();
         RewindEditorContext.setCopiedLayer(RewindEditorContext.getSelectedLayer());
         rewind3_31.method3().method2();
         RewindEditorContext.setCopiedRange(rewind3_31.method5().getKey());
         RewindEditorContext.method39(rewind3_31.method4());
      }
   }

   private void method10(TimelineSelection rewind3_31) {
      if (RewindEditorContext.getCopiedLayer() != null) {
         RewindIterator rewinditerator2 = (RewindIterator)RewindEditorContext.getCopiedLayer().method9(rewind3_31.method3(), RewindEditorContext.getCopiedRange(), RewindEditorContext.getCopiedRange());
         rewinditerator2.method23(null);
         ((SegmentTimeline<RewindIterator>)RewindEditorContext.method40()
            .method5())
            .method1(
               Range.between(
                  rewind3_31.method2().method15(),
                  rewind3_31.method2().method15() + (Integer)RewindEditorContext.getCopiedRange().getMaximum() - (Integer)RewindEditorContext.getCopiedRange().getMinimum()
               ),
               rewinditerator2
            );
         RewindEditorContext.refreshTimeline();
      }
   }

   private void method11(TimelineSelection rewind3_31) {
      rewind3_31.method2().setPaused(!rewind3_31.method2().isPaused());
      if (!rewind3_31.method2().isPaused()) {
         RewindEditorContext.setSelectedKeyframe(null);
         RewindEditorContext.refreshTimeline();
      }

      RewindEditorContext.refreshPlaybackState();
   }

   private void method12(TimelineSelection rewind3_31) {
      rewind3_31.method2().setPaused(true);
      rewind3_31.method2().method8(Math.max(0, rewind3_31.method2().method15() - 1));
      RewindEditorContext.refreshPlaybackState();
   }

   private void method13(TimelineSelection rewind3_31) {
      rewind3_31.method2().method8(rewind3_31.method2().method15() + 1);
      RewindEditorContext.refreshPlaybackState();
   }

   private void method14(TimelineSelection rewind3_31) {
      if (rewind3_31.method5() != null) {
         rewind3_31.method3().method1();
         SegmentLinkManager highlight22 = rewind3_31.method2().method11().method6();
         HashSet set3 = new HashSet();

         for (UUID uuid5 : RewindEditorContext.collectLinkedLayers(null)) {
            Entry entry6 = rewind3_31.method2().method1().get(uuid5);
            if (entry6 != null) {
               for (Track gui_28 : rewind3_31.method2().method11()) {
                  if (gui_28.method5().method11().containsValue(entry6.getValue())) {
                     gui_28.method5().method8((Range<Integer>)entry6.getKey());
                     set3.add(uuid5);
                     break;
                  }
               }
            }
         }

         highlight22.method10(rewind3_31.method3(), set3);
         RewindEditorContext.setSelectedLayer(null);
         rewind3_31.method3().endBatch();
      } else if (RewindEditorContext.getSelectedKeyframe() != null) {
         rewind3_31.method3().method1();
         RewindEditorContext.getSelectedKeyframe().method2().method27().remove(RewindEditorContext.getSelectedKeyframe().method3());

         for (SelectedKeyframe coordinates410 : RewindEditorContext.getAdditionalSelectedKeyframes()) {
            coordinates410.method2().method27().remove(coordinates410.method3());
         }

         if (RewindEditorContext.getSelectedKeyframe().method2().method27().isEmpty()) {
            RewindEditorContext.getSelectedKeyframe().method2().reset();
         }

         rewind3_31.method3().endBatch();
         RewindEditorContext.setSelectedKeyframe(null);
         RewindEditorContext.refreshProperties();
      }

      RewindEditorContext.refreshTimeline();
   }

   private void method15(TimelineSelection rewind3_31) {
      rewind3_31.method3().method1();
      Range range2 = rewind3_31.method5().getKey();

      for (Track gui_24 : rewind3_31.method2().method11()) {
         gui_24.method5().method9(range2);
      }

      rewind3_31.method3().endBatch();
      rewind3_31.method2().method8(rewind3_31.method2().method15() - (Math.min(rewind3_31.method2().method15(), (Integer)range2.getMaximum()) - (Integer)range2.getMinimum()));
      RewindEditorContext.setSelectedLayer(null);
      RewindEditorContext.refreshTimeline();
   }

   private void method16(TimelineSelection rewind3_31) {
      RewindTimelineBridge.cut(rewind3_31.method5() != null ? rewind3_31.method5().getValue().getId().toString() : "");
   }

   private void method17(TimelineSelection rewind3_31) {
      RewindHandlers rewindhandlers2 = rewind3_31.method1();
      if (!rewindhandlers2.method57().method25()) {
         rewindhandlers2.method34(!rewindhandlers2.method44());
      }
   }

   private void method18(TimelineSelection rewind3_31) {
      RewindHandlers rewindhandlers2 = rewind3_31.method1();
      if (!rewindhandlers2.method57().method25()) {
         ExportSettingsJsApi.exportScreenshot();
      }
   }
}
