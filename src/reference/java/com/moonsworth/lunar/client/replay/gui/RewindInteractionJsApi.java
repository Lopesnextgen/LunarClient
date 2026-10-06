package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import com.moonsworth.lunar.client.replay.timeline.EffectSegment;
import com.moonsworth.lunar.client.replay.timeline.RewindSettingKeys;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.render.SelectionHighlightHandler;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.apache.commons.lang3.Range;

public class RewindInteractionJsApi extends RewindEditorContext implements DriverGuiExtension, Extension {
   public RewindInteractionJsApi() {
   }

   public GuiIterator getProvider() {
      return !getRewind().method19() ? null : getRewindHandlers().method38();
   }

   private static void withSelectedElement(Consumer<SelectedElement> consumer0) {
      RewindHandlers rewindhandlers1 = getRewindHandlers();
      SelectionHighlightHandler rewindhandlers3impl22 = rewindhandlers1.method50();
      rewindhandlers3impl22.method5(false);
      refreshEntityContextMenu();
      RewindIterator rewinditerator3 = findHoveredLayer();
      if (rewinditerator3 != null) {
         BridgeExtension bridgeextension4 = rewindhandlers3impl22.method14();
         if (bridgeextension4 != null) {
            String text5 = bridgeextension4 instanceof Bridge6_10 ? bridgeextension4.bridge$getUniqueID().toString() : String.valueOf(bridgeextension4.bridge$getEntityId());
            consumer0.accept(new SelectedElement(rewindhandlers1, rewinditerator3, rewindhandlers3impl22, text5));
            refreshTimeline();
            refreshProperties();
         }
      }
   }

   @CallbackJS("setAsCameraTarget")
   public static void setAsCameraTarget() {
      withSelectedElement(arg0 -> {
         BridgeExtension bridgeextension1 = arg0.field3.method14();
         if (bridgeextension1 != null) {
            setEntityOverride(arg0.field1.method40().method40(), arg0.field2, "camera", arg0.field1.method45().method22().getId(), arg0.field4);
         }
      });
   }

   private static void applyEntityOverride(String text0, String text1) {
      withSelectedElement(arg2 -> {
         String text3 = arg2.field3.method14() instanceof Bridge6_10 ? "player" : "entity";
         setEntityOverride(arg2.field1.method40().method40(), arg2.field2, RewindSettingKeys.method1("entityOverrides", RewindSettingKeys.method2(text3, arg2.field4)), text0, text1);
      });
   }

   @CallbackJS("hide")
   public static void hide() {
      applyEntityOverride("hide", Boolean.TRUE.toString());
   }

   @CallbackJS("nametag")
   public static void nametag() {
      applyEntityOverride("name", "Text");
   }

   @CallbackJS("skin")
   public static void skin() {
      applyEntityOverride("skin", "jeb_");
   }

   private static RewindIterator<?> findHoveredLayer() {
      ReplayTimeline highlight_30 = getRewindHandlers().method40().method37();
      if (highlight_30 == null) {
         return null;
      }

      int number1 = highlight_30.method15();
      RewindIterator rewinditerator2 = getSelectedLayer();
      if (rewinditerator2 instanceof GameplaySegment || rewinditerator2 instanceof EffectSegment) {
         Range range3 = getLayerRange(rewinditerator2.getId());
         if (range3 != null && range3.contains(number1)) {
            return rewinditerator2;
         }
      }

      for (Track gui_24 : highlight_30.method11()) {
         Entry entry5 = gui_24.method1(number1);
         if (entry5 != null && (entry5.getValue() instanceof GameplaySegment || entry5.getValue() instanceof EffectSegment)) {
            setEntityOverride((RewindIterator)entry5.getValue());
            setEntityOverride(null);
            return (RewindIterator<?>)entry5.getValue();
         }
      }

      return null;
   }

   private static void setEntityOverride(
      com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate20, RewindIterator<?> rewinditerator1, String text2, String text3, String text4
   ) {
      nameplate20.method1();
      PropertyGroup fishing2iterator5 = null;
      String[] items6 = text2.split("/");

      for (int index7 = 0; index7 < items6.length; index7++) {
         String text8 = items6[index7];
         String text9 = index7 < items6.length - 1 ? text2.substring(0, text2.indexOf(items6[index7 + 1]) - 1) : text2;
         PropertyGroup fishing2iterator10 = setEntityOverride(rewinditerator1.method18().values(), text9.split("/"), 0);
         if (fishing2iterator10 == null) {
            fishing2iterator10 = getRewindHandlers().method40().method38().method2(nameplate20, text8);
            if (fishing2iterator5 == null) {
               rewinditerator1.method18().put(text8, fishing2iterator10);
            } else {
               fishing2iterator5.method11().put(text8, fishing2iterator10);
            }
         }

         fishing2iterator5 = fishing2iterator10;
      }

      if (fishing2iterator5 == null) {
         nameplate20.endBatch();
      } else {
         KeyframeProperty fishing2loader11 = setEntityOverride(rewinditerator1, fishing2iterator5, text3, true);
         if (fishing2loader11 == null) {
            if (!fishing2iterator5.method18()) {
               nameplate20.endBatch();
               return;
            }

            fishing2loader11 = getRewindHandlers().method40().method39().method2(text3);
            fishing2iterator5.method12().put(text3, fishing2loader11);
            fishing2loader11 = setEntityOverride(rewinditerator1, fishing2iterator5, text3, true);
         }

         setOptionValue(fishing2loader11.getOption(), text4);
         nameplate20.endBatch();
      }
   }

   private static void setOptionValue(ClientOption<?> lightingextension0, String text1) {
      if (lightingextension0.get() instanceof String) {
         lightingextension0.method10(text1);
      } else {
         lightingextension0.method21(text1);
      }
   }
}
