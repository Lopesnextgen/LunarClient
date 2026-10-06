package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.chest;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindImpl2;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator22;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator23;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator2_2;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl2;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl3;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_4;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers2Impl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.highlight.Highlight2_2;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import org.apache.commons.lang3.Range;

public class Chest2 extends Coordinates implements DriverGuiExtensionLegacy, Extension {
   public GuiIterator getProvider() {
      return !selectKeyframe().method19() ? null : selectKeyframes().method29();
   }

   public static boolean canPropagateEdit(UUID var0) {
      boolean var1 = var0 != null && getSelectedLayer() != null && var0.equals(getSelectedLayer().getId());
      return var1 && isLinkSelectionEnabled() || !var1 && !LcuiScreen.method124();
   }

   @CallbackJS("cut")
   public static void cut(String var0) {
      Highlight_3 var1 = moveKeyframe();
      if (var1 != null) {
         Nameplate2 var2 = method18().method40();
         int var3 = var1.method15();
         if (var0.isBlank()) {
            var1.method5(var2, var3);
         } else {
            UUID var4 = UUID.fromString(var0);
            var2.method1();
            Gui_2 var5 = findTrackOfLayer(var4);
            HashSet var6 = new HashSet();
            if (var5 != null) {
               Set var7 = collectLinkedLayers(var4);
               var5.method5().method5(var3);
               var6.add(var5.getId());
               if (canPropagateEdit(var4)) {
                  for (UUID var9 : var7) {
                     Gui_2 var10 = findTrackOfLayer(var9);
                     if (var10 != null && !var6.contains(var10.getId())) {
                        var10.method5().method5(var3);
                        var6.add(var10.getId());
                     }
                  }
               }
            }

            var2.endBatch();
         }

         refreshTimeline();
      }
   }

   @CallbackJS("addTrack")
   public static void addTrack(String var0) {
      Highlight_3 var1 = moveKeyframe();
      if (var1 != null) {
         Nameplate2 var2 = method18().method40();
         switch (var0) {
            case "effect":
               GuiImpl var5 = new GuiImpl(var2, var1.method1());
               var1.method11().method2().add(var5);
               break;
            case "gameplay":
               GuiImpl3 var6 = new GuiImpl3(var2, var1.method1());
               var1.method11().method3().add(var6);
               break;
            case "audio":
               GuiImpl2 var7 = new GuiImpl2(var2, var1.method1());
               var1.method11().method4().add(var7);
         }

         refreshTimeline();
      }
   }

   @CallbackJS("addLayer")
   public static void addLayer(UUID var0, String var1) {
      Highlight_3 var2 = moveKeyframe();
      if (var2 != null) {
         Nameplate2 var3 = method18().method40();
         var3.method1();

         try {
            Highlight_4 var4 = method18().method43().method5();
            Gui_2 var5 = findTrack(var0);
            RewindIterator var6 = null;
            long var7 = 10000L;
            switch (var1) {
               case "text":
                  var6 = new RewindImpl2(var3);
                  break;
               case "effect":
                  var6 = new Rewind_2(var3);
                  break;
               case "image":
                  var6 = new RewindImpl(var3);
                  File var18 = Gui4.method7(null, method18().method32(), null, Highlight_4.field3);
                  if (var18 == null) {
                     var3.method2();
                     return;
                  }

                  UUID var20 = var4.method2(var18);
                  ((RewindImpl)var6).method5(var20);
                  break;
               case "audio":
                  var6 = new RewindIterator22(var3);
                  File var17 = Gui4.method7(null, method18().method32(), null, Highlight_4.field1);
                  if (var17 == null) {
                     var3.method2();
                     return;
                  }

                  UUID var19 = var4.method2(var17);
                  Rewindhandlers2Impl var13 = new Rewindhandlers2Impl(
                     method18().method32(),
                     "media://" + var19,
                     method18().method42(),
                     var4,
                     method18().method44()
                  );
                  var13.play();
                  ((RewindIterator22)var6).method9(var13);
                  var7 = var13.getDuration();
                  break;
               case "gameplay":
                  var6 = new RewindIterator23(method18().method41(), var3);
                  File var9 = Gui4.method7(null, Gui.field8, null, Highlight_4.field2);
                  if (var9 == null) {
                     var3.method2();
                     return;
                  }

                  UUID var10 = var4.method2(var9);
                  Rewind3 var14 = method18().method43().method6().method4(var10);
                  int var15 = var14.method13().method5();
                  if (ThreadModuleDump63.method3().bridge$getProtocolVersion() != var15) {
                     ThreadModuleDump63.method4()
                        .method69()
                        .method6(NotificationType.ERROR, "Rewind", "The rewind file was created in a different version of Minecraft.")
                        .method9(NotificationAnchor.BOTTOM_RIGHT);
                     var3.method2();
                     return;
                  }

                  ((RewindIterator23)var6).method13(var14.method13().getId());
                  var7 = var14.method13().method1();
            }

            if (var6 == null) {
               var3.method2();
               return;
            }

            Range var21 = Range.between(var2.method15(), var2.method15() + (int)(var7 / var2.method9()));
            var5.method5().method1(var21, var6);
            var6.method1(var21, method18().method38(), var3);
            method18().method45().method6(var21, var6, var2.method9());
            var3.endBatch();
         } catch (IOException var16) {
            var3.method2();
         }

         refreshMediaExporter();
         refreshTimeline();
      }
   }

   @CallbackJS("selectLayer")
   public static void selectLayer(UUID var0) {
      Set var1 = getAdditionalSelectedLayers();

      for (Gui_2 var3 : method18().method37().method11()) {
         for (Range var5 : var3.method5().method11().keySet()) {
            RewindIterator var6 = (RewindIterator)var3.method5().method11().get(var5);
            if (var6.getId().equals(var0)) {
               if ((LcuiScreen.isShiftKeyDown() || isMultiSelectEnabled()) && getSelectedLayer() != null) {
                  if (getSelectedLayer() != var6) {
                     if (var1.contains(var6)) {
                        var1.remove(var6);
                     } else {
                        var1.add(var6);
                     }
                  }
               } else {
                  method30(var6);
                  Coordinates.setLinkSelectionEnabled(!LcuiScreen.method124());
               }

               method30(null);
               break;
            }
         }
      }

      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("selectLayers")
   public static void selectLayer(UUID var0, UUID[] var1) {
      Highlight_3 var2 = moveKeyframe();
      if (var2 != null) {
         Entry var3 = var2.method1().get(var0);
         if (var3 != null) {
            method30((RewindIterator)var3.getValue());
            Coordinates.setLinkSelectionEnabled(!LcuiScreen.method124());
            method30(null);
            if (var1 != null) {
               Set var4 = getAdditionalSelectedLayers();

               for (UUID var8 : var1) {
                  if (var8 != null && !var8.equals(var0)) {
                     Entry var9 = var2.method1().get(var8);
                     if (var9 != null) {
                        var4.add((RewindIterator)var9.getValue());
                     }
                  }
               }
            }

            refreshProperties();
            refreshTimeline();
         }
      }
   }

   @CallbackJS("unselectLayer")
   public static void unselectLayer() {
      method30(null);
      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("moveLayer")
   public static void moveLayer(UUID var0, UUID var1, int var2) {
      Highlight_3 var3 = moveKeyframe();
      if (var3 != null) {
         Nameplate2 var4 = method18().method40();
         var4.method1();
         RewindIterator var5 = null;

         for (Gui_2 var7 : var3.method11()) {
            for (Range var9 : var7.method5().method11().keySet()) {
               RewindIterator var10 = (RewindIterator)var7.method5().method11().get(var9);
               if (var10.getId().equals(var1)) {
                  var5 = var10;
                  if (var0 == null) {
                     var0 = var7.getId();
                  }
               }
            }
         }

         if (var5 != null) {
            applyLayerMove(var0, var1, var2, canPropagateEdit(var1));
         }

         int var11 = Bridge.method20().getY();
         if (Bridge.getMinecraftVersion().method21()) {
            var11 = ThreadModuleDump63.method3().bridge$logicalHeight() - var11;
         }

         if (var5 == null && (var0 != null || var11 > ThreadModuleDump63.method3().bridge$logicalHeight() / 1.5)) {
            Highlight2_2.isKeyframeSelected(var1, var0, Math.max(0, var2));
            refreshMediaExporter();
         }

         var4.endBatch();
         refreshTimeline();
      }
   }

   private static void applyLayerMove(UUID var0, UUID var1, int var2, boolean var3) {
      Highlight_3 var4 = moveKeyframe();
      if (var4 != null) {
         Entry var5 = var4.method1().get(var1);
         if (var5 != null) {
            Range var6 = (Range)var5.getKey();
            RewindIterator var7 = (RewindIterator)var5.getValue();
            int var8 = var2 - (Integer)var6.getMinimum();
            if (var3) {
               ArrayList var9 = new ArrayList();

               for (UUID var11 : collectLinkedLayers(var1)) {
                  Entry var12 = var4.method1().get(var11);
                  if (var12 != null) {
                     var9.add(var12);
                  }
               }

               var9.sort(Comparator.comparingInt(var1x -> (int)(Math.signum(-var8) * ((Integer)((Range)var1x.getKey()).getMinimum()).intValue())));

               for (Entry var21 : var9) {
                  RewindIterator var23 = (RewindIterator)var21.getValue();
                  Gui_2 var13 = null;

                  for (Gui_2 var15 : method18().method37().method11()) {
                     if (var15.method5().method11().containsValue(var21.getValue())) {
                        var13 = var15;
                        break;
                     }
                  }

                  if (var13 != null) {
                     applyLayerMove(var23.getId().equals(var1) ? var0 : var13.getId(), var23.getId(), (Integer)((Range)var21.getKey()).getMinimum() + var8, false);
                  }
               }
            } else {
               for (Gui_2 var19 : method18().method37().method11()) {
                  RewindIterator var22 = (RewindIterator)var19.method5().method11().get(var6);
                  if (var22 != null && var22.getId().equals(var1)) {
                     var19.method5().method8(var6);
                  }
               }

               Range var17 = Range.between(var2, var2 + ((Integer)var6.getMaximum() - (Integer)var6.getMinimum()));
               Gui_2 var20 = findTrack(var0);
               var20.method5().method1(var17, var7);
            }
         }
      }
   }

   @CallbackJS("moveLayers")
   public static void moveLayers(Coordinates3.Data3[] var0) {
      Highlight_3 var1 = moveKeyframe();
      if (var1 != null && var0 != null && var0.length != 0) {
         ArrayList var2 = new ArrayList();

         for (Coordinates3.Data3 var6 : var0) {
            if (var6 != null && var6.field1 != null && var6.field2 != null && var1.method1().get(var6.field1) != null) {
               var2.add(var6);
            }
         }

         if (!var2.isEmpty()) {
            var2.sort(Comparator.comparingInt(var1x -> {
               Entry var2x = var1.method1().get(var1x.field1);
               int var3 = (Integer)((Range)var2x.getKey()).getMinimum();
               int var4 = var1x.frame - var3;
               return (int)(Math.signum(-var4) * var3);
            }));
            Nameplate2 var7 = method18().method40();
            var7.method1();

            for (Coordinates3.Data3 var9 : var2) {
               applyLayerMove(var9.field2, var9.field1, Math.max(0, var9.frame), false);
            }

            var7.endBatch();
            refreshTimeline();
         }
      }
   }

   @CallbackJS("trimLayer")
   public static void trimLayer(UUID var0, int var1, int var2) {
      Highlight_3 var3 = moveKeyframe();
      if (var3 != null) {
         Set var4;
         if (canPropagateEdit(var0)) {
            var4 = collectLinkedLayers(var0);
         } else {
            var4 = new HashSet();
            var4.add(var0);
            var4.addAll(getAdditionalSelectedLayers().stream().map(RewindIterator::getId).toList());
         }

         Entry var5 = var3.method1().get(var0);
         if (var5 != null) {
            Range var6 = (Range)var5.getKey();
            Nameplate2 var7 = method18().method40();
            var7.method1();

            for (UUID var9 : var4) {
               Entry var10 = var3.method1().get(var9);
               if (var10 != null) {
                  Range var11 = (Range)var10.getKey();
                  RewindIterator var12 = (RewindIterator)var10.getValue();
                  if (Objects.equals(var11.getMinimum(), var6.getMinimum()) && Objects.equals(var11.getMaximum(), var6.getMaximum())) {
                     Gui_2 var13 = null;

                     for (Gui_2 var15 : method18().method37().method11()) {
                        if (var15.method5().method11().containsValue(var12)) {
                           var13 = var15;
                           break;
                        }
                     }

                     if (var13 != null) {
                        Range var23 = Range.between(var1, var2);
                        if (!var23.equals(var11)) {
                           var13.method5().method8(var11);
                           if ((Integer)var23.getMaximum() - (Integer)var23.getMinimum() > 0) {
                              var13.method5().method1(var23, var12);
                              if (!Objects.equals(var23.getMinimum(), var11.getMinimum())) {
                                 int var24 = (Integer)var23.getMinimum() - (Integer)var11.getMinimum();

                                 for (Fishing2Iterator var17 : var12.method18().values()) {
                                    for (Fishing2Loader var19 : var17.method12().values()) {
                                       HashMap var20 = new HashMap();

                                       for (Object var22 : new HashMap(var19.method27()).keySet()) {
                                          if ((Integer)var22 != Integer.MIN_VALUE) {
                                             var20.put((Integer)var22 - var24, (Fishing2Loader.Data)var19.method27().remove(var22));
                                          }
                                       }

                                       for (Entry var31 : var20.entrySet()) {
                                          var19.method27().put(var31.getKey(), var31.getValue());
                                       }
                                    }
                                 }
                              }

                              int var25 = var12.method19();
                              int var26 = (int)(((Integer)var23.getMaximum() - (Integer)var23.getMinimum()) * var12.method7());
                              var7.method4(() -> var12.method25(var25), () -> var12.method25(var26));
                              var12.method25(var26);
                              if (var12 instanceof RewindIterator2_2 var27) {
                                 int var28 = var27.method8();
                                 int var29 = var1 - (Integer)var11.getMinimum();
                                 var7.method4(() -> var27.method9(var28), () -> var27.method9(var28 + var29));
                                 var27.method9(var28 + var29);
                              }
                           }
                        }
                     }
                  }
               }
            }

            var7.endBatch();
            refreshTimeline();
         }
      }
   }

   @CallbackJS("setShowKeyframes")
   public static void setShowKeyframes(int var0, boolean var1) {
      Gui_2 var2 = method27(var0);
      if (var2 != null) {
         var2.method7(var1);
         refreshTimeline();
      }
   }

   @CallbackJS("setTrackEnabled")
   public static void setTrackEnabled(int var0, boolean var1) {
      Gui_2 var2 = method27(var0);
      if (var2 != null) {
         var2.setEnabled(var1);
         refreshTimeline();
      }
   }

   @CallbackJS("deleteTrack")
   public static void getProvider(int var0) {
      Gui_2 var1 = method27(var0);
      if (var1 != null) {
         Highlight_3 var2 = moveKeyframe();
         if (var2 != null) {
            Set var3 = var2.method11().method2();
            if (var3.contains(var1)) {
               var3.remove(var1);
            }

            Set var4 = var2.method11().method3();
            if (var4.contains(var1)) {
               var4.remove(var1);
            }

            Set var5 = var2.method11().method4();
            if (var5.contains(var1)) {
               var5.remove(var1);
            }

            refreshTimeline();
         }
      }
   }

   @CallbackJS("selectKeyframe")
   public static void selectKeyframe(UUID var0, String var1, String var2, int var3, boolean var4) {
      RewindIterator var5 = null;
      Range var6 = null;

      for (Gui_2 var8 : method18().method37().method11()) {
         for (Range var10 : var8.method5().method11().keySet()) {
            RewindIterator var11 = (RewindIterator)var8.method5().method11().get(var10);
            if (var11.getId().equals(var0)) {
               var5 = var11;
               var6 = var10;
               break;
            }
         }
      }

      if (var5 != null) {
         Fishing2Iterator var12 = method30(var5.method18().values(), var1.split("/"), 0);
         Fishing2Loader var13 = method30(var5, var1, var2);
         if (var13 != null) {
            Highlight_3 var14 = moveKeyframe();
            if (var14 != null) {
               method30(new Coordinates4(var12, var13, var3));
               method30(null);
               if (var4) {
                  var14.method8(var3 + (Integer)var6.getMinimum());
               }

               refreshProperties();
               refreshTimeline();
            }
         }
      }
   }

   @CallbackJS("selectKeyframes")
   public static void selectKeyframes(Coordinates3.Data[] var0) {
      if (var0 != null && var0.length != 0) {
         Highlight_3 var1 = moveKeyframe();
         if (var1 != null) {
            Coordinates4 var2 = null;
            LinkedHashSet var3 = new LinkedHashSet();

            for (Coordinates3.Data var7 : var0) {
               if (var7 != null && var7.field1 != null && var7.field2 != null && var7.field3 != null) {
                  Entry var8 = var1.method1().get(var7.field1);
                  if (var8 != null) {
                     RewindIterator var9 = (RewindIterator)var8.getValue();
                     Fishing2Iterator var10 = method30(var9.method18().values(), var7.field2.split("/"), 0);
                     Fishing2Loader var11 = method30(var9, var7.field2, var7.field3);
                     if (var10 != null && var11 != null) {
                        Coordinates4 var12 = new Coordinates4(var10, var11, var7.frame);
                        if (var2 == null) {
                           var2 = var12;
                           var1.method8(var2.method3() + (Integer)((Range)var8.getKey()).getMinimum());
                        } else {
                           var3.add(var12);
                        }
                     }
                  }
               }
            }

            if (var2 != null) {
               method30(null);
               method30(var2);
               getAdditionalSelectedKeyframes().addAll(var3);
               refreshProperties();
               refreshTimeline();
            }
         }
      }
   }

   @CallbackJS("unselectKeyframe")
   public static void unselectKeyframe() {
      method30(null);
      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("moveKeyframe")
   public static void moveKeyframe(int var0) {
      if (method41() != null) {
         Nameplate2 var1 = method18().method40();
         Fishing2Loader var2 = method41().method2();
         int var3 = method41().method3();
         var1.method1();
         Fishing2Loader.Data var4 = method41().method2().method27().remove(var3);
         if (var4 != null) {
            var2.method27().put(var0, var4);
            method30(new Coordinates4(method41().method1(), var2, var0));
         }

         var1.endBatch();
         refreshTimeline();
         refreshProperties();
         method41().method2().method8();
      }
   }

   @CallbackJS("moveKeyframes")
   public static void unselectLayer(Coordinates3.Data2[] var0) {
      Highlight_3 var1 = moveKeyframe();
      if (var1 != null && var0 != null && var0.length != 0) {
         Nameplate2 var2 = method18().method40();
         var2.method1();
         ArrayList var3 = new ArrayList();
         HashSet var4 = new HashSet();
         HashMap var5 = new HashMap();

         for (Coordinates3.Data2 var9 : var0) {
            if (var9 != null && var9.field1 != null && var9.field3 != null && var9.field4 != null) {
               Entry var10 = var1.method1().get(var9.field1);
               if (var10 != null) {
                  Fishing2Loader var11 = method30((RewindIterator)var10.getValue(), var9.field3, var9.field4);
                  if (var11 != null) {
                     Fishing2Loader.Data var12 = (Fishing2Loader.Data)var11.method27().remove(var9.field5);
                     if (var12 != null) {
                        var3.add(new Coordinates3.Data4(var11, var9.field6, var12));
                        var4.add(var11);
                        var5.computeIfAbsent(var11, var0x -> new HashMap<>()).put(var9.field5, var9.field6);
                     }
                  }
               }
            }
         }

         for (Coordinates3.Data4 var15 : var3) {
            var15.method1().method27().put(var15.method2(), var15.method3());
         }

         var2.endBatch();

         for (Fishing2Loader var16 : var4) {
            var16.method8();
         }

         addTrack(var5);
         refreshTimeline();
         refreshProperties();
      }
   }

   @CallbackJS("linkLayers")
   public static void unselectKeyframe(boolean var0) {
      if (getSelectedLayer() != null) {
         Nameplate2 var1 = method18().method40();
         Highlight_3 var2 = moveKeyframe();
         if (var2 != null) {
            Set var3 = collectLinkedLayers(null);
            if (var0) {
               var2.method11().method6().method8(var1, var3);
            } else {
               var2.method11().method6().method10(var1, var3);
            }

            getAdditionalSelectedLayers().clear();
            refreshTimeline();
            refreshProperties();
         }
      }
   }

   @CallbackJS("startLayersSelection")
   public static void startLayersSelection() {
      unselectKeyframe(true);
   }

   @CallbackJS("stopLayersSelection")
   public static void stopLayersSelection() {
      unselectKeyframe(false);
      refreshProperties();
   }
}
