package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;
import org.apache.commons.lang3.Range;

public class RewindEditorContext {
   private static RewindIterator<?> field1 = null;
   private static Set<RewindIterator<?>> field2 = new HashSet<>();
   private static boolean field3 = true;
   private static boolean field4 = false;
   private static RewindIterator<?> field5 = null;
   private static Range<Integer> field6 = Range.is(0);
   private static Track<?> field7 = null;
   private static SelectedKeyframe field8 = null;
   private static Set<SelectedKeyframe> field9 = new HashSet<>();
   private static float field10 = 20.0F;
   private static boolean field11 = false;
   private static int field12 = -1;

   public RewindEditorContext() {
   }

   public static void setSelectedLayer(RewindIterator<?> rewinditerator0) {
      field1 = rewinditerator0;
      field2.clear();
   }

   public static void setSelectedKeyframe(SelectedKeyframe coordinates40) {
      field8 = coordinates40;
      field9.clear();
   }

   public static boolean isKeyframeSelected(KeyframeProperty<?, ?> fishing2loader0, int number1) {
      if (field8 != null && field8.method2() == fishing2loader0 && field8.method3() == number1) {
         return true;
      }

      for (SelectedKeyframe coordinates43 : field9) {
         if (coordinates43.method2() == fishing2loader0 && coordinates43.method3() == number1) {
            return true;
         }
      }

      return false;
   }

   public static void applyFrameRemap(Map<KeyframeProperty<?, ?>, Map<Integer, Integer>> map0) {
      if (map0 != null && !map0.isEmpty()) {
         if (field8 != null) {
            Integer number1 = lookupRemappedFrame(map0, field8.method2(), field8.method3());
            if (number1 != null) {
               field8 = new SelectedKeyframe(field8.method1(), field8.method2(), number1);
            }
         }

         if (!field9.isEmpty()) {
            HashSet set5 = new HashSet<>(field9);
            field9.clear();

            for (SelectedKeyframe coordinates43 : set5) {
               Integer index4 = lookupRemappedFrame(map0, coordinates43.method2(), coordinates43.method3());
               field9.add(index4 == null ? coordinates43 : new SelectedKeyframe(coordinates43.method1(), coordinates43.method2(), index4));
            }
         }
      }
   }

   private static Integer lookupRemappedFrame(Map<KeyframeProperty<?, ?>, Map<Integer, Integer>> map0, KeyframeProperty<?, ?> fishing2loader1, int index2) {
      Map map3 = (Map)map0.get(fishing2loader1);
      return map3 == null ? null : (Integer)map3.get(index2);
   }

   public static Set<UUID> collectLinkedLayers(UUID uuid0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 != null && (getSelectedLayer() != null || uuid0 != null)) {
         if (uuid0 == null) {
            uuid0 = getSelectedLayer().getId();
         }

         UUID uuid2 = uuid0;
         boolean flag3 = uuid0.equals(getSelectedLayer() == null ? null : getSelectedLayer().getId()) || getAdditionalSelectedLayers().stream().anyMatch(arg1x -> arg1x.getId().equals(uuid2));
         HashSet set4 = new HashSet();
         set4.add(uuid0);
         if (flag3) {
            if (getSelectedLayer() != null && !getSelectedLayer().getId().equals(uuid0)) {
               set4.add(getSelectedLayer().getId());
            }

            for (RewindIterator rewinditerator6 : getAdditionalSelectedLayers()) {
               set4.add(rewinditerator6.getId());
            }
         }

         if (isLinkSelectionEnabled()) {
            set4.addAll(highlight_31.method11().method6().method3(uuid0));
            if (flag3 && getSelectedLayer() != null && !getSelectedLayer().getId().equals(uuid0)) {
               set4.addAll(highlight_31.method11().method6().method3(getSelectedLayer().getId()));
            }

            if (flag3) {
               for (RewindIterator rewinditerator8 : getAdditionalSelectedLayers()) {
                  set4.addAll(highlight_31.method11().method6().method4(rewinditerator8));
               }
            }
         }

         return set4;
      } else {
         return Collections.emptySet();
      }
   }

   public static void refreshPlaybackState() {
      getRewindHandlers().method28().method1(getRewindHandlers());
   }

   public static void refreshTimeline() {
      if (getRewindHandlers() != null) {
         getRewindHandlers().method29().method1(getRewindHandlers());
      }
   }

   public static void refreshProperties() {
      getRewindHandlers().method35().method1(getRewindHandlers());
   }

   public static void refreshMediaExporter() {
      if (getRewindHandlers() != null) {
         getRewindHandlers().method30().method1(getRewindHandlers());
      }
   }

   public static void refreshTimelinesList() {
      getRewindHandlers().method34().method1(getRewindHandlers());
   }

   public static void refreshExportSettings() {
      if (getRewindHandlers() != null) {
         getRewindHandlers().method36().method1(getRewindHandlers());
      }
   }

   public static void refreshEffects() {
      getRewindHandlers().method37().method1(getRewindHandlers());
   }

   public static void refreshEntityContextMenu() {
      getRewindHandlers().method38().method1(getRewindHandlers());
   }

   public static ReplayTimeline findTimeline(UUID uuid0) {
      for (ReplayTimeline highlight_32 : getRewindHandlers().method40().method36()) {
         if (highlight_32.getId().equals(uuid0)) {
            return highlight_32;
         }
      }

      return null;
   }

   protected static RewindMod getRewind() {
      return Ref.method4().method40().method85();
   }

   protected static RewindHandlers getRewindHandlers() {
      return getRewind().method35();
   }

   public static ReplayProjectManager getProject() {
      RewindHandlers rewindhandlers0 = getRewindHandlers();
      return rewindhandlers0 == null ? null : rewindhandlers0.method40();
   }

   public static ReplayTimeline getTimeline() {
      return getRewindHandlers() == null ? null : getRewindHandlers().method40().method37();
   }

   public static Range<Integer> getLayerRange(UUID uuid0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 == null) {
         return null;
      }

      Entry entry2 = highlight_31.method1().get(uuid0);
      return entry2 == null ? null : (Range)entry2.getKey();
   }

   public static PropertyGroup findPropertyByPath(Collection<PropertyGroup> list0, String[] items1, int index2) {
      for (PropertyGroup fishing2iterator4 : list0) {
         if (Objects.equals(fishing2iterator4.type(), items1[index2])) {
            if (index2 == items1.length - 1) {
               return fishing2iterator4;
            }

            return findPropertyByPath(fishing2iterator4.method11().values(), items1, index2 + 1);
         }
      }

      return null;
   }

   public static KeyframeProperty<?, ?> findProperty(RewindIterator<?> rewinditerator0, String text1, String text2) {
      return resolveProperty(rewinditerator0, text1, text2, false);
   }

   public static KeyframeProperty<?, ?> resolveProperty(RewindIterator<?> rewinditerator0, String text1, String text2, boolean flag3) {
      if (rewinditerator0 == null) {
         return null;
      }

      Range range4 = getLayerRange(rewinditerator0.getId());
      if (range4 == null) {
         return null;
      }

      PropertyGroup fishing2iterator5 = findPropertyByPath(rewinditerator0.method18().values(), text1.split("/"), 0);
      return resolvePropertyInGroup(rewinditerator0, fishing2iterator5, text2, flag3);
   }

   public static KeyframeProperty<?, ?> resolvePropertyInGroup(RewindIterator<?> rewinditerator0, PropertyGroup fishing2iterator1, String text2, boolean flag3) {
      if (rewinditerator0 != null && fishing2iterator1 != null) {
         Range range4 = getLayerRange(rewinditerator0.getId());
         if (range4 == null) {
            return null;
         }

         for (KeyframeProperty fishing2loader6 : fishing2iterator1.method12().values()) {
            ClientOption lightingextension7 = fishing2loader6.getOption();
            if (lightingextension7.getId().equals(text2)) {
               if (flag3) {
                  int number8 = ((ReplayContext)getRewindHandlers().method42().get()).method4().method15();
                  int number9 = number8 - (Integer)range4.getMinimum();
                  if (field8 != null) {
                     number9 = field8.method3();
                  }

                  fishing2loader6.method30(number9);
                  fishing2loader6.method32(range4);
               }

               return fishing2loader6;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static Track<?> findTrackOfLayer(UUID uuid0) {
      for (Track gui_22 : getRewindHandlers().method40().method37().method11()) {
         for (Range range4 : gui_22.method5().method11().keySet()) {
            RewindIterator rewinditerator5 = (RewindIterator)gui_22.method5().method11().get(range4);
            if (rewinditerator5.getId().equals(uuid0)) {
               return gui_22;
            }
         }
      }

      return null;
   }

   public static Track<?> findTrack(UUID uuid0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 == null) {
         return null;
      }

      for (Track gui_23 : highlight_31.method11()) {
         if (gui_23.getId().equals(uuid0)) {
            return gui_23;
         }
      }

      return null;
   }

   public static Track<?> getTrackByReversedIndex(int number0) {
      ReplayTimeline highlight_31 = getTimeline();
      if (highlight_31 == null) {
         return null;
      }

      int index2 = highlight_31.method11().size() - 1;

      for (Track gui_24 : highlight_31.method11()) {
         if (index2 == number0) {
            return gui_24;
         }

         index2--;
      }

      return null;
   }

   public static boolean method28(RewindIterator<?> rewinditerator0) {
      if (rewinditerator0 == null) {
         return false;
      }

      ReplayTimeline highlight_31 = getTimeline();
      return highlight_31 == null ? false : highlight_31.method1().containsKey(rewinditerator0.getId2());
   }

   @Generated
   public static RewindIterator<?> getSelectedLayer() {
      return field1;
   }

   @Generated
   public static Set<RewindIterator<?>> getAdditionalSelectedLayers() {
      return field2;
   }

   @Generated
   public static void setLinkSelectionEnabled(boolean flag0) {
      field3 = flag0;
   }

   @Generated
   public static boolean isLinkSelectionEnabled() {
      return field3;
   }

   @Generated
   public static void setMultiSelectEnabled(boolean flag0) {
      field4 = flag0;
   }

   @Generated
   public static boolean isMultiSelectEnabled() {
      return field4;
   }

   @Generated
   public static void setCopiedLayer(RewindIterator<?> rewinditerator0) {
      field5 = rewinditerator0;
   }

   @Generated
   public static RewindIterator<?> getCopiedLayer() {
      return field5;
   }

   @Generated
   public static void setCopiedRange(Range<Integer> range0) {
      field6 = range0;
   }

   @Generated
   public static Range<Integer> getCopiedRange() {
      return field6;
   }

   @Generated
   public static void method39(Track<?> gui_20) {
      field7 = gui_20;
   }

   @Generated
   public static Track<?> method40() {
      return field7;
   }

   @Generated
   public static SelectedKeyframe getSelectedKeyframe() {
      return field8;
   }

   @Generated
   public static Set<SelectedKeyframe> getAdditionalSelectedKeyframes() {
      return field9;
   }

   @Generated
   public static void setZoom(float value0) {
      field10 = value0;
   }

   @Generated
   public static float getZoom() {
      return field10;
   }

   @Generated
   public static void setDragging(boolean flag0) {
      field11 = flag0;
   }

   @Generated
   public static boolean isDragging() {
      return field11;
   }

   @Generated
   public static void setScrubFrame(int number0) {
      field12 = number0;
   }

   @Generated
   public static int getScrubFrame() {
      return field12;
   }
}
