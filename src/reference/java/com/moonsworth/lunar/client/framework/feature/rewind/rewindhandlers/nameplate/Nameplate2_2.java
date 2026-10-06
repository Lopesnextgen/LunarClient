package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator23;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_2;
import com.moonsworth.lunar.client.framework.feature.rewind.fishing.Fishing4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl2;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.Map.Entry;
import java.util.function.Consumer;
import org.apache.commons.lang3.Range;

public class Nameplate2_2 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public GuiIterator getProvider() {
      return !method16().method19() ? null : method17().method38();
   }

   private static void withSelectedElement(Consumer<Nameplate2$Data4> var0) {
      RewindHandlers var1 = method17();
      RewindHandlers3Impl2 var2 = var1.method50();
      var2.method5(false);
      refreshEntityContextMenu();
      RewindIterator var3 = findHoveredLayer();
      if (var3 != null) {
         BridgeExtension var4 = var2.method14();
         if (var4 != null) {
            String var5 = var4 instanceof Bridge6_10 ? var4.bridge$getUniqueID().toString() : String.valueOf(var4.bridge$getEntityId());
            var0.accept(new Nameplate2$Data4(var1, var3, var2, var5));
            refreshTimeline();
            refreshProperties();
         }
      }
   }

   @CallbackJS("setAsCameraTarget")
   public static void setAsCameraTarget() {
      withSelectedElement(var0 -> {
         BridgeExtension var1 = var0.field3.method14();
         if (var1 != null) {
            setEntityOverride(var0.field1.method40().method40(), var0.field2, "camera", var0.field1.method45().method22().getId(), var0.field4);
         }
      });
   }

   private static void applyEntityOverride(String var0, String var1) {
      withSelectedElement(var2 -> {
         String var3 = var2.field3.method14() instanceof Bridge6_10 ? "player" : "entity";
         setEntityOverride(var2.field1.method40().method40(), var2.field2, Fishing4.method1("entityOverrides", Fishing4.method2(var3, var2.field4)), var0, var1);
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
      Highlight_3 var0 = method17().method40().method37();
      if (var0 == null) {
         return null;
      }

      int var1 = var0.method15();
      RewindIterator var2 = findHoveredLayer();
      if (var2 instanceof RewindIterator23 || var2 instanceof Rewind_2) {
         Range var3 = nametag(var2.getId());
         if (var3 != null && var3.contains(var1)) {
            return var2;
         }
      }

      for (Gui_2 var4 : var0.method11()) {
         Entry var5 = var4.method1(var1);
         if (var5 != null && (var5.getValue() instanceof RewindIterator23 || var5.getValue() instanceof Rewind_2)) {
            setEntityOverride((RewindIterator)var5.getValue());
            setEntityOverride(null);
            return (RewindIterator<?>)var5.getValue();
         }
      }

      return null;
   }

   private static void setEntityOverride(
      com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2 var0, RewindIterator<?> var1, String var2, String var3, String var4
   ) {
      var0.method1();
      Fishing2Iterator var5 = null;
      String[] var6 = var2.split("/");

      for (int var7 = 0; var7 < var6.length; var7++) {
         String var8 = var6[var7];
         String var9 = var7 < var6.length - 1 ? var2.substring(0, var2.indexOf(var6[var7 + 1]) - 1) : var2;
         Fishing2Iterator var10 = setEntityOverride(var1.method18().values(), var9.split("/"), 0);
         if (var10 == null) {
            var10 = method17().method40().method38().method2(var0, var8);
            if (var5 == null) {
               var1.method18().put(var8, var10);
            } else {
               var5.method11().put(var8, var10);
            }
         }

         var5 = var10;
      }

      if (var5 == null) {
         var0.endBatch();
      } else {
         Fishing2Loader var11 = setEntityOverride(var1, var5, var3, true);
         if (var11 == null) {
            if (!var5.method18()) {
               var0.endBatch();
               return;
            }

            var11 = method17().method40().method39().method2(var3);
            var5.method12().put(var3, var11);
            var11 = setEntityOverride(var1, var5, var3, true);
         }

         setOptionValue(var11.getOption(), var4);
         var0.endBatch();
      }
   }

   private static void setOptionValue(ClientOption<?> var0, String var1) {
      if (var0.get() instanceof String) {
         var0.method10(var1);
      } else {
         var0.method21(var1);
      }
   }
}
