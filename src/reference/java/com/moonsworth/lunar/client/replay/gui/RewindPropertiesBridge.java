package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.ButtonOption;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import org.apache.commons.lang3.Range;

public class RewindPropertiesBridge extends RewindEditorContext implements DriverGuiExtension, Extension {
   public RewindPropertiesBridge() {
   }

   public GuiIterator getProvider() {
      return !getRewind().method19() ? null : getRewindHandlers().method35();
   }

   @CallbackJS("addProperty")
   public static void addProperty(String text0, String text1) {
      if (getSelectedLayer() != null) {
         UndoRedoManager nameplate22 = getRewindHandlers().method40().method40();
         nameplate22.method1();
         if (text0 != null && !text0.isEmpty()) {
            String[] items3 = text0.split("/");
            PropertyGroup fishing2iterator4 = setPropertyExtended(getSelectedLayer().method18().values(), items3, 0);
            if (fishing2iterator4 != null) {
               if (fishing2iterator4.method16() == PropertyGroup.NodeType.CHILD_PROPERTIES) {
                  fishing2iterator4.method11().put(text1, getRewindHandlers().method40().method38().method2(nameplate22, text1));
               }

               if (fishing2iterator4.method16() == PropertyGroup.NodeType.KEYFRAMES) {
                  fishing2iterator4.method12().put(text1, getRewindHandlers().method40().method39().method2(text1));
               }
            }
         } else {
            getSelectedLayer().method18().put(text1, getRewindHandlers().method40().method38().method2(nameplate22, text1));
         }

         nameplate22.endBatch();
         refreshProperties();
      }
   }

   @CallbackJS("removeProperty")
   public static void removeProperty(String text0) {
      beginUndoBatch();
      if (getSelectedLayer() != null) {
         if (text0.contains("/")) {
            String text1 = text0.substring(0, text0.lastIndexOf("/"));
            String text2 = text0.substring(text1.length() + "/".length());
            String[] items3 = text1.split("/");
            PropertyGroup fishing2iterator4 = setPropertyExtended(getSelectedLayer().method18().values(), items3, 0);
            if (fishing2iterator4 != null) {
               fishing2iterator4.method11().remove(text2);
            }
         } else {
            getSelectedLayer().method18().remove(text0);
         }
      } else if (getSelectedKeyframe() != null) {
         getSelectedKeyframe().method2().method27().remove(getSelectedKeyframe().method3());
         setPropertyExtended(null);
      }

      endUndoBatch();
      refreshProperties();
      refreshTimeline();
   }

   @CallbackJS("setPropertyExtended")
   public static void setPropertyExtended(String text0, Boolean flag1) {
      if (getSelectedLayer() != null) {
         beginUndoBatch();
         String[] items2 = text0.split("/");
         PropertyGroup fishing2iterator3 = setPropertyExtended(getSelectedLayer().method18().values(), items2, 0);
         if (fishing2iterator3 != null) {
            fishing2iterator3.method19(flag1);
         }

         endUndoBatch();
         refreshProperties();
      }
   }

   @CallbackJS("setPropertyEnabled")
   public static void setPropertyEnabled(String text0, Boolean flag1) {
      if (getSelectedLayer() != null) {
         beginUndoBatch();
         String[] items2 = text0.split("/");
         PropertyGroup fishing2iterator3 = setPropertyExtended(getSelectedLayer().method18().values(), items2, 0);
         if (fishing2iterator3 != null) {
            fishing2iterator3.setEnabled(flag1);
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
   public static void updatePropertyValue(String text0, String text1, String text2, boolean flag3) {
      if (!flag3) {
         startPropertyUpdate();
      }

      if (text0.equals("keyframe")) {
         RewindPropertiesPanel colorsaturation4 = (RewindPropertiesPanel)getRewindHandlers().method35();

         for (ClientOption lightingextension6 : colorsaturation4.getOptions()) {
            if (lightingextension6.getId().equals(text1)) {
               lightingextension6.method21(text2);
               break;
            }
         }
      } else {
         KeyframeProperty fishing2loader7;
         if (getSelectedKeyframe() != null) {
            fishing2loader7 = getSelectedKeyframe().method2();
            int index8 = getSelectedKeyframe().method3();
            fishing2loader7.method30(null);
            KeyframeProperty.Keyframe data9 = (KeyframeProperty.Keyframe)fishing2loader7.method27().get(index8);
            if (data9 != null) {
               fishing2loader7.method22(fishing2loader7.getOption(), data9.getValue());
            }

            fishing2loader7.method30(index8);
            fishing2loader7.method32(Range.between(0, Integer.MAX_VALUE));
         } else {
            fishing2loader7 = setPropertyExtended(getSelectedLayer(), text0, text1, true);
         }

         if (fishing2loader7 == null) {
            return;
         }

         fishing2loader7.getOption().method21(text2);
      }

      if (!flag3) {
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
   public static void handlePropertyButton(String text0, String text1) {
      KeyframeProperty fishing2loader2;
      if (getSelectedKeyframe() != null) {
         fishing2loader2 = getSelectedKeyframe().method2();
         fishing2loader2.method30(getSelectedKeyframe().method3());
         fishing2loader2.method32(Range.between(0, Integer.MAX_VALUE));
      } else {
         fishing2loader2 = setPropertyExtended(getSelectedLayer(), text0, text1, true);
      }

      if (fishing2loader2 != null) {
         if (fishing2loader2.getOption() instanceof ButtonOption lightingextension49143 && lightingextension49143.method7() != null) {
            lightingextension49143.method7().run();
            refreshProperties();
         }
      }
   }

   @CallbackJS("resetPropertyValue")
   public static void resetPropertyValue(String text0, String text1) {
      KeyframeProperty fishing2loader2 = setPropertyExtended(getSelectedLayer(), text0, text1);
      if (fishing2loader2 != null) {
         beginUndoBatch();
         fishing2loader2.reset();
         endUndoBatch();
         refreshProperties();
         refreshTimeline();
      }
   }

   @CallbackJS("deletePropertyValue")
   public static void deletePropertyValue(String text0, String text1) {
      if (getSelectedLayer() != null) {
         Range range2 = getLayerRange(getSelectedLayer().getId());
         if (range2 != null) {
            PropertyGroup fishing2iterator3 = setPropertyExtended(getSelectedLayer().method18().values(), text0.split("/"), 0);
            KeyframeProperty fishing2loader4 = setPropertyExtended(getSelectedLayer(), fishing2iterator3, text1, false);
            if (fishing2loader4 != null) {
               beginUndoBatch();
               fishing2loader4.method3();
               fishing2iterator3.method12().remove(fishing2loader4.type());
               endUndoBatch();
               refreshProperties();
               refreshTimeline();
            }
         }
      }
   }

   @CallbackJS("previousKeyframe")
   public static void previousKeyframe(String text0, String text1) {
      ReplayTimeline highlight_32 = getTimeline();
      if (highlight_32 != null) {
         KeyframeProperty fishing2loader3 = setPropertyExtended(getSelectedLayer(), text0, text1);
         if (fishing2loader3 != null) {
            Range range4 = getLayerRange(getSelectedLayer().getId());
            if (range4 != null) {
               int number5 = highlight_32.method15() - (Integer)range4.getMinimum();
               Integer number6 = fishing2loader3.method13(number5);
               if (number6 != null) {
                  highlight_32.method8((Integer)range4.getMinimum() + number6);
                  Ref.method3().bridge$schedule(RewindEditorContext::refreshProperties);
               }
            }
         }
      }
   }

   @CallbackJS("nextKeyframe")
   public static void nextKeyframe(String text0, String text1) {
      ReplayTimeline highlight_32 = getTimeline();
      if (highlight_32 != null) {
         KeyframeProperty fishing2loader3 = setPropertyExtended(getSelectedLayer(), text0, text1);
         if (fishing2loader3 != null) {
            Range range4 = getLayerRange(getSelectedLayer().getId());
            if (range4 != null) {
               int number5 = highlight_32.method15() - (Integer)range4.getMinimum();
               Integer number6 = fishing2loader3.method14(number5);
               if (number6 != null) {
                  highlight_32.method8((Integer)range4.getMinimum() + number6);
                  Ref.method3().bridge$schedule(RewindEditorContext::refreshProperties);
               }
            }
         }
      }
   }

   @CallbackJS("keyframe")
   public static void keyframe(String text0, String text1) {
      KeyframeProperty fishing2loader2 = setPropertyExtended(getSelectedLayer(), text0, text1);
      if (fishing2loader2 != null) {
         Range range3 = getLayerRange(getSelectedLayer().getId());
         if (range3 != null) {
            ReplayTimeline highlight_34 = getTimeline();
            if (highlight_34 != null) {
               int number5 = highlight_34.method15() - (Integer)range3.getMinimum();
               fishing2loader2.method6(range3, number5);
               refreshProperties();
               refreshTimeline();
            }
         }
      }
   }

   @CallbackJS("setShowKeyframesInTimeline")
   public static void setShowKeyframesInTimeline(String text0, String text1, boolean flag2) {
      KeyframeProperty fishing2loader3 = setPropertyExtended(getSelectedLayer(), text0, text1);
      if (fishing2loader3 != null) {
         fishing2loader3.method29(flag2);
         refreshProperties();
         refreshTimeline();
      }
   }

   public static void beginUndoBatch() {
      ReplayTimeline highlight_30 = getTimeline();
      if (highlight_30 != null) {
         UndoRedoManager nameplate21 = getRewindHandlers().method40().method40();
         nameplate21.method1();
      }
   }

   public static void endUndoBatch() {
      ReplayTimeline highlight_30 = getTimeline();
      if (highlight_30 != null) {
         for (Track gui_22 : highlight_30.method11()) {
            gui_22.method3(getRewindHandlers().method42());
         }
      }

      UndoRedoManager nameplate23 = getRewindHandlers().method40().method40();
      nameplate23.endBatch();
   }
}
