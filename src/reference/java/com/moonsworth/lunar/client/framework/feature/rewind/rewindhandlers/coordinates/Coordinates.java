package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates;

import com.moonsworth.lunar.client.framework.feature.rewind.Fishing2Loader;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui_2;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Fishing2Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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

public class Coordinates {
   private static RewindIterator<?> field1 = null;
   private static Set<RewindIterator<?>> field2 = new HashSet<>();
   private static boolean field3 = true;
   private static boolean field4 = false;
   private static RewindIterator<?> field5 = null;
   private static Range<Integer> field6 = Range.is(0);
   private static Gui_2<?> field7 = null;
   private static Coordinates4 field8 = null;
   private static Set<Coordinates4> field9 = new HashSet<>();
   private static float field10 = 20.0F;
   private static boolean field11 = false;
   private static int field12 = -1;

   public static void setSelectedLayer(RewindIterator<?> var0) {
      field1 = var0;
      field2.clear();
   }

   public static void setSelectedKeyframe(Coordinates4 var0) {
      field8 = var0;
      field9.clear();
   }

   public static boolean isKeyframeSelected(Fishing2Loader<?, ?> var0, int var1) {
      if (field8 != null && field8.method2() == var0 && field8.method3() == var1) {
         return true;
      }

      for (Coordinates4 var3 : field9) {
         if (var3.method2() == var0 && var3.method3() == var1) {
            return true;
         }
      }

      return false;
   }

   public static void applyFrameRemap(Map<Fishing2Loader<?, ?>, Map<Integer, Integer>> var0) {
      if (var0 != null && !var0.isEmpty()) {
         if (field8 != null) {
            Integer var1 = lookupRemappedFrame(var0, field8.method2(), field8.method3());
            if (var1 != null) {
               field8 = new Coordinates4(field8.method1(), field8.method2(), var1);
            }
         }

         if (!field9.isEmpty()) {
            HashSet var5 = new HashSet<>(field9);
            field9.clear();

            for (Coordinates4 var3 : var5) {
               Integer var4 = lookupRemappedFrame(var0, var3.method2(), var3.method3());
               field9.add(var4 == null ? var3 : new Coordinates4(var3.method1(), var3.method2(), var4));
            }
         }
      }
   }

   private static Integer lookupRemappedFrame(Map<Fishing2Loader<?, ?>, Map<Integer, Integer>> var0, Fishing2Loader<?, ?> var1, int var2) {
      Map var3 = (Map)var0.get(var1);
      return var3 == null ? null : (Integer)var3.get(var2);
   }

   public static Set<UUID> collectLinkedLayers(UUID var0) {
      Highlight_3 var1 = getTimeline();
      if (var1 != null && (getSelectedLayer() != null || var0 != null)) {
         if (var0 == null) {
            var0 = getSelectedLayer().getId();
         }

         UUID var2 = var0;
         boolean var3 = var0.equals(getSelectedLayer() == null ? null : getSelectedLayer().getId()) || getAdditionalSelectedLayers().stream().anyMatch(var1x -> var1x.getId().equals(var2));
         HashSet var4 = new HashSet();
         var4.add(var0);
         if (var3) {
            if (getSelectedLayer() != null && !getSelectedLayer().getId().equals(var0)) {
               var4.add(getSelectedLayer().getId());
            }

            for (RewindIterator var6 : getAdditionalSelectedLayers()) {
               var4.add(var6.getId());
            }
         }

         if (isLinkSelectionEnabled()) {
            var4.addAll(var1.method11().method6().method3(var0));
            if (var3 && getSelectedLayer() != null && !getSelectedLayer().getId().equals(var0)) {
               var4.addAll(var1.method11().method6().method3(getSelectedLayer().getId()));
            }

            if (var3) {
               for (RewindIterator var8 : getAdditionalSelectedLayers()) {
                  var4.addAll(var1.method11().method6().method4(var8));
               }
            }
         }

         return var4;
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

   public static Highlight_3 findTimeline(UUID var0) {
      for (Highlight_3 var2 : getRewindHandlers().method40().method36()) {
         if (var2.getId().equals(var0)) {
            return var2;
         }
      }

      return null;
   }

   protected static Rewind getRewind() {
      return ThreadModuleDump63.method4().method40().method85();
   }

   protected static RewindHandlers getRewindHandlers() {
      return getRewind().method35();
   }

   public static Rewind2_3 getProject() {
      RewindHandlers var0 = getRewindHandlers();
      return var0 == null ? null : var0.method40();
   }

   public static Highlight_3 getTimeline() {
      return getRewindHandlers() == null ? null : getRewindHandlers().method40().method37();
   }

   public static Range<Integer> getLayerRange(UUID var0) {
      Highlight_3 var1 = getTimeline();
      if (var1 == null) {
         return null;
      }

      Entry var2 = var1.method1().get(var0);
      return var2 == null ? null : (Range)var2.getKey();
   }

   public static Fishing2Iterator findPropertyByPath(Collection<Fishing2Iterator> var0, String[] var1, int var2) {
      for (Fishing2Iterator var4 : var0) {
         if (Objects.equals(var4.type(), var1[var2])) {
            if (var2 == var1.length - 1) {
               return var4;
            }

            return findPropertyByPath(var4.method11().values(), var1, var2 + 1);
         }
      }

      return null;
   }

   public static Fishing2Loader<?, ?> findProperty(RewindIterator<?> var0, String var1, String var2) {
      return resolveProperty(var0, var1, var2, false);
   }

   public static Fishing2Loader<?, ?> resolveProperty(RewindIterator<?> var0, String var1, String var2, boolean var3) {
      if (var0 == null) {
         return null;
      }

      Range var4 = getLayerRange(var0.getId());
      if (var4 == null) {
         return null;
      }

      Fishing2Iterator var5 = findPropertyByPath(var0.method18().values(), var1.split("/"), 0);
      return resolvePropertyInGroup(var0, var5, var2, var3);
   }

   public static Fishing2Loader<?, ?> resolvePropertyInGroup(RewindIterator<?> var0, Fishing2Iterator var1, String var2, boolean var3) {
      if (var0 != null && var1 != null) {
         Range var4 = getLayerRange(var0.getId());
         if (var4 == null) {
            return null;
         }

         for (Fishing2Loader var6 : var1.method12().values()) {
            ClientOption var7 = var6.getOption();
            if (var7.getId().equals(var2)) {
               if (var3) {
                  int var8 = ((Nameplate4)getRewindHandlers().method42().get()).method4().method15();
                  int var9 = var8 - (Integer)var4.getMinimum();
                  if (field8 != null) {
                     var9 = field8.method3();
                  }

                  var6.method30(var9);
                  var6.method32(var4);
               }

               return var6;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public static Gui_2<?> findTrackOfLayer(UUID var0) {
      for (Gui_2 var2 : getRewindHandlers().method40().method37().method11()) {
         for (Range var4 : var2.method5().method11().keySet()) {
            RewindIterator var5 = (RewindIterator)var2.method5().method11().get(var4);
            if (var5.getId().equals(var0)) {
               return var2;
            }
         }
      }

      return null;
   }

   public static Gui_2<?> findTrack(UUID var0) {
      Highlight_3 var1 = getTimeline();
      if (var1 == null) {
         return null;
      }

      for (Gui_2 var3 : var1.method11()) {
         if (var3.getId().equals(var0)) {
            return var3;
         }
      }

      return null;
   }

   public static Gui_2<?> getTrackByReversedIndex(int var0) {
      Highlight_3 var1 = getTimeline();
      if (var1 == null) {
         return null;
      }

      int var2 = var1.method11().size() - 1;

      for (Gui_2 var4 : var1.method11()) {
         if (var2 == var0) {
            return var4;
         }

         var2--;
      }

      return null;
   }

   public static boolean containsLayer(RewindIterator<?> var0) {
      if (var0 == null) {
         return false;
      }

      Highlight_3 var1 = getTimeline();
      return var1 == null ? false : var1.method1().containsKey(var0.getId2());
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
   public static void setLinkSelectionEnabled(boolean var0) {
      field3 = var0;
   }

   @Generated
   public static boolean isLinkSelectionEnabled() {
      return field3;
   }

   @Generated
   public static void setMultiSelectEnabled(boolean var0) {
      field4 = var0;
   }

   @Generated
   public static boolean isMultiSelectEnabled() {
      return field4;
   }

   @Generated
   public static void setCopiedLayer(RewindIterator<?> var0) {
      field5 = var0;
   }

   @Generated
   public static RewindIterator<?> getCopiedLayer() {
      return field5;
   }

   @Generated
   public static void setCopiedRange(Range<Integer> var0) {
      field6 = var0;
   }

   @Generated
   public static Range<Integer> getCopiedRange() {
      return field6;
   }

   @Generated
   public static void setCopiedTrack(Gui_2<?> var0) {
      field7 = var0;
   }

   @Generated
   public static Gui_2<?> getCopiedTrack() {
      return field7;
   }

   @Generated
   public static Coordinates4 getSelectedKeyframe() {
      return field8;
   }

   @Generated
   public static Set<Coordinates4> getAdditionalSelectedKeyframes() {
      return field9;
   }

   @Generated
   public static void setZoom(float var0) {
      field10 = var0;
   }

   @Generated
   public static float getZoom() {
      return field10;
   }

   @Generated
   public static void setDragging(boolean var0) {
      field11 = var0;
   }

   @Generated
   public static boolean isDragging() {
      return field11;
   }

   @Generated
   public static void setScrubFrame(int var0) {
      field12 = var0;
   }

   @Generated
   public static int getScrubFrame() {
      return field12;
   }
}
