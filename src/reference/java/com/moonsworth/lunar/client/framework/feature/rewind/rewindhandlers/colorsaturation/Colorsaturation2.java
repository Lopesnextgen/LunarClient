package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.colorsaturation;

import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ButtonOption;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.apache.commons.lang3.Range;

public class Colorsaturation2 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public GuiIterator getProvider() {
      return !method16().method19() ? null : method17().method35();
   }

   @CallbackJS("addProperty")
   public static void addProperty(String var0, String var1) {
      if (getSelectedLayer() != null) {
         Nameplate2 var2 = method17().method40().method40();
         var2.method1();
         if (var0 != null && !var0.isEmpty()) {
            String[] var3 = var0.split("/");
            Fishing2Iterator var4 = setPropertyExtended(getSelectedLayer().method18().values(), var3, 0);
            if (var4 != null) {
               if (var4.method16() == Fishing2Iterator.Type.CHILD_PROPERTIES) {
                  var4.method11().put(var1, method17().method40().method38().method2(var2, var1));
               }

               if (var4.method16() == Fishing2Iterator.Type.KEYFRAMES) {
                  var4.method12().put(var1, method17().method40().method39().method2(var1));
               }
            }
         } else {
            getSelectedLayer().method18().put(var1, method17().method40().method38().method2(var2, var1));
         }

         var2.endBatch();
         refreshProperties();
      }
   }

   @CallbackJS("removeProperty")
   public static void removeProperty(String var0) {
      beginUndoBatch();
      if (getSelectedLayer() != null) {
         if (var0.contains("/")) {
            String var1 = var0.substring(0, var0.lastIndexOf("/"));
            String var2 = var0.substring(var1.length() + "/".length());
            String[] var3 = var1.split("/");
            Fishing2Iterator var4 = setPropertyExtended(getSelectedLayer().method18().values(), var3, 0);
            if (var4 != null) {
               var4.method11().remove(var2);
            }
         } else {
            getSelectedLayer().method18().remove(var0);
         }
      } else if (method41() != null) {
         method41().method2().method27().remove(method41().method3());
         setPropertyExtended(null);
      }

      endUndoBatch();
      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("setPropertyExtended")
   public static void setPropertyExtended(String var0, Boolean var1) {
      if (getSelectedLayer() != null) {
         beginUndoBatch();
         String[] var2 = var0.split("/");
         Fishing2Iterator var3 = setPropertyExtended(getSelectedLayer().method18().values(), var2, 0);
         if (var3 != null) {
            var3.method19(var1);
         }

         endUndoBatch();
         refreshProperties();
      }
   }

   @CallbackJS("setPropertyEnabled")
   public static void setPropertyEnabled(String var0, Boolean var1) {
      if (getSelectedLayer() != null) {
         beginUndoBatch();
         String[] var2 = var0.split("/");
         Fishing2Iterator var3 = setPropertyExtended(getSelectedLayer().method18().values(), var2, 0);
         if (var3 != null) {
            var3.setEnabled(var1);
         }

         endUndoBatch();
         refreshProperties();
      }
   }

   @CallbackJS("startPropertyUpdate")
   public static void startPropertyUpdate() {
      beginUndoBatch();
   }

   @CallbackJS("updatePropertyValue")
   public static void updatePropertyValue(String var0, String var1, String var2, boolean var3) {
      if (!var3) {
         startPropertyUpdate();
      }

      if (var0.equals("keyframe")) {
         Colorsaturation var4 = (Colorsaturation)method17().method35();

         for (ClientOption var6 : var4.getOptions()) {
            if (var6.getId().equals(var1)) {
               var6.method21(var2);
               break;
            }
         }
      } else {
         Fishing2Loader var7;
         if (method41() != null) {
            var7 = method41().method2();
            int var8 = method41().method3();
            var7.method30(null);
            Fishing2Loader.Data var9 = (Fishing2Loader.Data)var7.method27().get(var8);
            if (var9 != null) {
               var7.method22(var7.getOption(), var9.getValue());
            }

            var7.method30(var8);
            var7.method32(Range.between(0, Integer.MAX_VALUE));
         } else {
            var7 = setPropertyExtended(getSelectedLayer(), var0, var1, true);
         }

         if (var7 == null) {
            return;
         }

         var7.getOption().method21(var2);
      }

      if (!var3) {
         endPropertyUpdate();
      }
   }

   @CallbackJS("endPropertyUpdate")
   public static void endPropertyUpdate() {
      endUndoBatch();
      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("handlePropertyButton")
   public static void handlePropertyButton(String var0, String var1) {
      Fishing2Loader var2;
      if (method41() != null) {
         var2 = method41().method2();
         var2.method30(method41().method3());
         var2.method32(Range.between(0, Integer.MAX_VALUE));
      } else {
         var2 = setPropertyExtended(getSelectedLayer(), var0, var1, true);
      }

      if (var2 != null) {
         if (var2.getOption() instanceof ButtonOption var3 && var3.method7() != null) {
            var3.method7().run();
            refreshProperties();
         }
      }
   }

   @CallbackJS("resetPropertyValue")
   public static void resetPropertyValue(String var0, String var1) {
      Fishing2Loader var2 = setPropertyExtended(getSelectedLayer(), var0, var1);
      if (var2 != null) {
         beginUndoBatch();
         var2.reset();
         endUndoBatch();
         refreshProperties();
         refreshTimeline();
      }
   }

   @CallbackJS("deletePropertyValue")
   public static void deletePropertyValue(String var0, String var1) {
      if (getSelectedLayer() != null) {
         Range var2 = endPropertyUpdate(getSelectedLayer().getId());
         if (var2 != null) {
            Fishing2Iterator var3 = setPropertyExtended(getSelectedLayer().method18().values(), var0.split("/"), 0);
            Fishing2Loader var4 = setPropertyExtended(getSelectedLayer(), var3, var1, false);
            if (var4 != null) {
               beginUndoBatch();
               var4.method3();
               var3.method12().remove(var4.type());
               endUndoBatch();
               refreshProperties();
               refreshTimeline();
            }
         }
      }
   }

   @CallbackJS("previousKeyframe")
   public static void previousKeyframe(String var0, String var1) {
      Highlight_3 var2 = method19();
      if (var2 != null) {
         Fishing2Loader var3 = setPropertyExtended(getSelectedLayer(), var0, var1);
         if (var3 != null) {
            Range var4 = endPropertyUpdate(getSelectedLayer().getId());
            if (var4 != null) {
               int var5 = var2.method15() - (Integer)var4.getMinimum();
               Integer var6 = var3.method13(var5);
               if (var6 != null) {
                  var2.method8((Integer)var4.getMinimum() + var6);
                  ThreadModuleDump63.method3().bridge$schedule(Coordinates::refreshProperties);
               }
            }
         }
      }
   }

   @CallbackJS("nextKeyframe")
   public static void nextKeyframe(String var0, String var1) {
      Highlight_3 var2 = method19();
      if (var2 != null) {
         Fishing2Loader var3 = setPropertyExtended(getSelectedLayer(), var0, var1);
         if (var3 != null) {
            Range var4 = endPropertyUpdate(getSelectedLayer().getId());
            if (var4 != null) {
               int var5 = var2.method15() - (Integer)var4.getMinimum();
               Integer var6 = var3.method14(var5);
               if (var6 != null) {
                  var2.method8((Integer)var4.getMinimum() + var6);
                  ThreadModuleDump63.method3().bridge$schedule(Coordinates::refreshProperties);
               }
            }
         }
      }
   }

   @CallbackJS("keyframe")
   public static void keyframe(String var0, String var1) {
      Fishing2Loader var2 = setPropertyExtended(getSelectedLayer(), var0, var1);
      if (var2 != null) {
         Range var3 = endPropertyUpdate(getSelectedLayer().getId());
         if (var3 != null) {
            Highlight_3 var4 = method19();
            if (var4 != null) {
               int var5 = var4.method15() - (Integer)var3.getMinimum();
               var2.method6(var3, var5);
               refreshProperties();
               refreshTimeline();
            }
         }
      }
   }

   @CallbackJS("setShowKeyframesInTimeline")
   public static void setShowKeyframesInTimeline(String var0, String var1, boolean var2) {
      Fishing2Loader var3 = setPropertyExtended(getSelectedLayer(), var0, var1);
      if (var3 != null) {
         var3.method29(var2);
         refreshProperties();
         refreshTimeline();
      }
   }

   public static void beginUndoBatch() {
      Highlight_3 var0 = method19();
      if (var0 != null) {
         Nameplate2 var1 = method17().method40().method40();
         var1.method1();
      }
   }

   public static void endUndoBatch() {
      Highlight_3 var0 = method19();
      if (var0 != null) {
         for (Gui_2 var2 : var0.method11()) {
            var2.method3(method17().method42());
         }
      }

      Nameplate2 var3 = method17().method40().method40();
      var3.endBatch();
   }
}
