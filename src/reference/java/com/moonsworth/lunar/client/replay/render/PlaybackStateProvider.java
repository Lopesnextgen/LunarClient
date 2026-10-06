package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.recording.ReplayClock;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;

public class PlaybackStateProvider extends RewindPropertyProvider {
   public PlaybackStateProvider(List<RewindPropertyProvider> list) {
      super(list);
   }

   @Override
   public void method1(RewindHandlers rewindhandlers1) {
      ReplayProjectManager rewind2_32 = rewindhandlers1.method40();
      ReplayTimeline highlight_33 = rewind2_32.method37();
      if (highlight_33 != null) {
         this.method3("playhead", RewindEditorContext.isDragging() && RewindEditorContext.getScrubFrame() >= 0 ? RewindEditorContext.getScrubFrame() : highlight_33.method15());
         this.method3("paused", highlight_33.isPaused());
         this.method3("dragging", RewindEditorContext.isDragging());
         this.method3("ruleOfThirdsOverlay", highlight_33.method18());
         this.method3("quartersOverlay", highlight_33.method20());
      }

      this.method3("previewMode", rewindhandlers1.method44());
      this.method3("rendering", rewindhandlers1.method57().method25());
      this.method3("quickView", rewind2_32.method31());
      LoadingStageImpl fogloader24 = Ref.method4().method90();
      this.method3("panelX", fogloader24.method16().get());
      this.method3("panelY", fogloader24.method17().get());
      ReplayClock rewindhandlers5 = rewindhandlers1.method41();
      if (rewindhandlers5.method5() && rewindhandlers5.method19() > 20000L) {
         this.method3("progress", rewindhandlers5.method13());
      } else {
         this.method3("progress", 1.0F);
      }
   }
}
