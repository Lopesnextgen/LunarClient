package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.TimelineRenderJob;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator.Extension;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RewindTimelinesListBridge extends RewindEditorContext implements DriverGuiExtension, Extension {
   public RewindTimelinesListBridge() {
   }

   public GuiIterator getProvider() {
      return !getRewind().method19() ? null : getRewindHandlers().method34();
   }

   @CallbackJS("selectTimeline")
   public static void selectTimeline(UUID uuid0) {
      ReplayTimeline highlight_31 = findTimeline(uuid0);
      if (highlight_31 != null) {
         getRewindHandlers().method40().method14(highlight_31);
         setSelectedLayer(null);
         setSelectedLayer(null);
         Ref.method4()
            .method69()
            .method6(NotificationType.ERROR, "Rewind", "Switched timeline to " + highlight_31.getName())
            .method9(NotificationAnchor.BOTTOM_RIGHT);
      }
   }

   @CallbackJS("editTimelineName")
   public static void editTimelineName(UUID uuid0, String text1) {
      ReplayTimeline highlight_32 = findTimeline(uuid0);
      if (highlight_32 != null) {
         highlight_32.setName(text1);

         try {
            getProject().method17(false);
         } catch (IOException exception4) {
            throw new RuntimeException(exception4);
         }

         refreshTimelinesList();
      }
   }

   @CallbackJS("duplicateTimeline")
   public static void duplicateTimeline(UUID uuid0) {
      ReplayTimeline highlight_31 = findTimeline(uuid0);
      if (highlight_31 != null) {
         ReplayProjectManager rewind2_32 = getProject();
         File file3 = new File(rewind2_32.method32(), "timelines");
         file3.mkdirs();
         if (!file3.isDirectory()) {
            throw new RuntimeException("Failed to create timelines folder");
         }

         UUID uuid4 = UUID.randomUUID();
         File file5 = rewind2_32.method23(file3, highlight_31, String.valueOf(uuid4));

         TimelineRenderJob highlightimpl6;
         try {
            highlightimpl6 = rewind2_32.method12(file5, TimelineRenderJob.class);
         } catch (IOException exception9) {
            throw new RuntimeException(exception9);
         }

         highlightimpl6.setId(uuid4);
         highlightimpl6.setName(highlight_31.getName() + " (Copy)");
         rewind2_32.method36().add(highlightimpl6);

         try {
            rewind2_32.method17(false);
         } catch (IOException exception8) {
            throw new RuntimeException(exception8);
         }

         refreshTimelinesList();
      }
   }

   @CallbackJS("deleteTimeline")
   public static void deleteTimeline(UUID uuid0) {
      ReplayTimeline highlight_31 = findTimeline(uuid0);
      if (highlight_31 != null) {
         getRewindHandlers().method40().method6(highlight_31);
         getRewindHandlers().method27();
         setSelectedLayer(null);
         setSelectedLayer(null);
      }
   }

   @CallbackJS("newTimeline")
   public static void newTimeline() {
      setSelectedLayer(null);
      setSelectedLayer(null);
      getRewindHandlers().method40().method5(com.moonsworth.lunar.client.replay.gui.CreateProjectBridge.method2());
      getRewindHandlers().method27();
   }
}
