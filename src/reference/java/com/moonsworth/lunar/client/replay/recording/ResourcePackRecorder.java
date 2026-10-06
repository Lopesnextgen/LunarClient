package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.project.PathUtils;
import com.moonsworth.lunar.client.replay.network.ServerPackPacket;
import com.moonsworth.lunar.client.replay.network.ClientPackPacket;
import com.moonsworth.lunar.client.event.resourcepack.EventResourcePackUpdate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackRemove;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.util.List;

public class ResourcePackRecorder extends RecorderEventListener {
   public ResourcePackRecorder() {
   }

   private void method1(RewindRecorder rewindhandlers51, ReplayHandler rewind_42) {
      File file3 = new File(Ref.method3().bridge$getMcDataDir(), "resourcepacks");
      List list4 = Ref.method3().bridge$getClientResourcePacksFiles();
      List list5 = list4.stream().map(arg1x -> PathUtils.method2(arg1x, file3)).toList();
      ClientPackPacket nameplate2iterator26 = new ClientPackPacket(list5);
      rewind_42.method9(nameplate2iterator26, rewindhandlers51.getTick());
   }

   @Override
   public void method2(EventTick highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (rewind_43.method5()) {
         List list4 = Ref.method3().bridge$getServerResourcePacksFiles();
         ServerPackPacket nameplate2iterator5 = new ServerPackPacket(rewind_43.method1(list4));
         rewind_43.method9(nameplate2iterator5, rewindhandlers52.getTick());
         this.method1(rewindhandlers52, rewind_43);
      }
   }

   @Override
   public void method15(EventResourcePackUpdate highlightimpl31, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      this.method1(rewindhandlers52, rewind_43);
   }

   @Override
   public void method16(EventServerResourcePackUpdate highlightimpl221, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      rewind_43.method9(new ServerPackPacket(rewind_43.method1(highlightimpl221.getResourcePacks())), rewindhandlers52.getTick());
   }

   @Override
   public void method17(EventServerResourcePackRemove highlightimpl71, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      rewind_43.method9(new ServerPackPacket(List.of()), rewindhandlers52.getTick());
   }
}
