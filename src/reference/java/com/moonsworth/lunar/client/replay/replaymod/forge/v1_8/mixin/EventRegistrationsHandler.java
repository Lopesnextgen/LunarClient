package com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin;

import com.google.protobuf.Any;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink.Data;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPost;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPre;
import com.moonsworth.lunar.client.event.mixin.holograms.EventFeatureToggle;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheel;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.replaymod.core.ReplayMod;
import com.replaymod.core.versions.MCVer;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.EventRegistrations;
import com.replaymod.recording.ReplayModRecording;
import com.replaymod.recording.ServerInfoExt;
import com.replaymod.recording.Setting;
import com.replaymod.recording.gui.GuiRecordingControls;
import com.replaymod.recording.handler.ConnectionEventHandler;
import com.replaymod.recording.packet.PacketListener;
import com.replaymod.render.hooks.EntityRendererHandler;
import com.replaymod.render.hooks.EntityRendererHandler.IEntityRenderer;
import com.replaymod.replay.InputReplayTimer;
import com.replaymod.replay.ReplayModReplay;
import com.replaymod.replay.events.ReplayClosedCallback;
import com.replaymod.replay.events.ReplayOpenedCallback;
import com.replaymod.replay.gui.overlay.GuiReplayOverlay;
import com.replaymod.replay.gui.screen.GuiReplayViewer;
import com.replaymod.replaystudio.lib.guava.collect.ImmutableSet;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Post;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Pre;
import net.minecraftforge.common.MinecraftForge;

public class EventRegistrationsHandler extends EventRegistrations implements RecordingExternalLink {
   public static boolean field2;
   public static boolean field3;
   public static boolean field4;
   public static boolean field5;
   public static int field6 = 0;
   public static Map<String, ClientOption> field7 = new HashMap<>();
   public static Data field8 = null;
   private static final Set<Class> field9 = ImmutableSet.of(GuiReplayViewer.class, GuiReplayOverlay.class);

   public EventRegistrationsHandler() {
      LunarEventBus.method29()
         .method2(
            EventRenderContainerSlotPre.class,
            arg0 -> MinecraftForge.EVENT_BUS
               .post(
                  new Pre(
                     (GuiScreen)arg0.method3(),
                     arg0.method1().xi(),
                     arg0.method1().RROCOHICOORRHCIHHHCHRCICHIIHCO(),
                     arg0.method2()
                  )
               )
         );
      LunarEventBus.method29()
         .method2(
            EventRenderContainerSlotPost.class,
            arg0 -> MinecraftForge.EVENT_BUS
               .post(
                  new Post(
                     (GuiScreen)arg0.method3(),
                     arg0.method1().xi(),
                     arg0.method1().RROCOHICOORRHCIHHHCHRCICHIIHCO(),
                     arg0.method2()
                  )
               )
         );
      LunarEventBus.method29()
         .method2(
            EventFeatureToggle.class,
            arg1 -> {
               if (arg1.method1() instanceof ReplayMod) {
                  if (arg1.method2()) {
                     if (ReplayModRecording.instance.getConnectionEventHandler() == null
                        || ReplayModRecording.instance.getConnectionEventHandler().getPacketListener() == null) {
                        return;
                     }

                     boolean flag2 = (Boolean)ReplayMod.instance.getSettingsRegistry().get(Setting.AUTO_START_RECORDING);
                     if (Minecraft.getMinecraft().currentServerData != null) {
                        ServerData serverdata3 = Minecraft.getMinecraft().currentServerData;
                        Boolean flag4 = ServerInfoExt.from(serverdata3).getAutoRecording();
                        if (flag4 != null) {
                           flag2 = flag4;
                        }
                     } else if (this.method1()) {
                        LunarLogger.method3("Cannot start recording in Replay Viewer");
                        return;
                     }

                     if (!flag2) {
                        return;
                     }

                     ReplayModRecording.instance.getConnectionEventHandler().getPacketListener().addMarker("_RM_END_CUT");
                     ReplayMod.instance.printInfoToChat("replaymod.chat.recordingstarted", new Object[0]);
                     ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$setStopped(false);
                     ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$setPaused(false);
                     ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$updateState();
                  } else {
                     ConnectionEventHandler connectioneventhandler5 = ReplayModRecording.instance.getConnectionEventHandler();
                     if (connectioneventhandler5 == null || connectioneventhandler5.getPacketListener() == null) {
                        return;
                     }

                     int number6 = (int)connectioneventhandler5.getPacketListener().getCurrentDuration();
                     if (!((ConnectionEventHandlerV1_8Accessor)connectioneventhandler5).brige$getGuiControls().isPaused()) {
                        connectioneventhandler5.getPacketListener().addMarker("_RM_START_CUT", number6);
                     }

                     connectioneventhandler5.getPacketListener().addMarker("_RM_SPLIT", number6 + 1);
                     ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$setStopped(true);
                  }
               }
            }
         );
      LunarEventBus.method29().method2(EventMouseWheel.class, arg1 -> {
         if (this.method1()) {
            InputReplayTimer.handleScroll((int)arg1.method1());
            arg1.setCancelled(true);
         }
      });
      this.on(ReplayOpenedCallback.EVENT, (ReplayOpenedCallback)arg0 -> {
         UUID uuid1 = Ref.method3().bridge$getSession().bridge$getProfile().getId();
         CosmeticManager holograms122 = Client.method109().method53();
         field8 = new Data(new HashSet(holograms122.method13()), new HashMap(holograms122.method58()));
         holograms122.method28(uuid1, field1);
      });
      this.on(ReplayClosedCallback.EVENT, (ReplayClosedCallback)arg0 -> {
         if (field8 != null) {
            UUID uuid1 = Ref.method3().bridge$getSession().bridge$getProfile().getId();
            CosmeticManager holograms122 = Client.method109().method53();
            holograms122.method13().clear();
            holograms122.method13().addAll(field8.method1());
            holograms122.method58().clear();
            holograms122.method58().putAll(field8.method2());
            holograms122.method28(field1, uuid1);
            holograms122.method24();
         }
      });
      this.register();
   }

   public void handleMainMenuButton() {
      new GuiReplayViewer(ReplayModReplay.instance).display();
   }

   public boolean method1() {
      return ReplayModReplay.instance.getReplayHandler() != null;
   }

   public boolean method2() {
      return field2;
   }

   public boolean method3() {
      return field3;
   }

   public boolean method4() {
      return ReplayModReplay.instance.getReplayHandler().getReplaySender().paused();
   }

   public boolean isRecording() {
      return ReplayModRecording.instance.getConnectionEventHandler().getPacketListener() != null && !this.method2();
   }

   public boolean method5() {
      return (Boolean)ReplayMod.instance.getSettingsRegistry().get(Setting.INDICATOR);
   }

   public ResourceLocationBridge method6() {
      return (ResourceLocationBridge)ReplayMod.TEXTURE;
   }

   public int method7() {
      return 256;
   }

   public void method8(Any any1) {
      if (ReplayModRecording.instance.getConnectionEventHandler().getPacketListener() != null) {
         ReplayModRecording.instance.getConnectionEventHandler().getPacketListener().save(new PacketLoader(any1));
      }
   }

   public boolean method9(GuiScreenBridge bridge5extension61) {
      return field5 ? false : !field9.contains(bridge5extension61.getClass());
   }

   public Set<KeyBindingBridge> method10() {
      return ReplayMod.instance.getKeyBindingRegistry().getBindings().values().stream().map(arg0 -> arg0.keyBinding).collect(Collectors.toSet());
   }

   public void method12() {
      if (ReplayModRecording.instance.getConnectionEventHandler().getPacketListener() != null) {
         ReplayModRecording.instance.getConnectionEventHandler().getPacketListener().addMarker("_RM_END_CUT");
         ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$setPaused(false);
         ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$updateState();
         Client.method109().method53().method24();
      }
   }

   public void startRecording() {
      if (ReplayModRecording.instance.getConnectionEventHandler().getPacketListener() != null) {
         ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$setPaused(false);
         ReplayModRecording.instance.getConnectionEventHandler().getPacketListener().addMarker("_RM_END_CUT");
         ReplayMod.instance.printInfoToChat("replaymod.chat.recordingstarted", new Object[0]);
         ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$setStopped(false);
         ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$updateState();
         Client.method109().method53().method24();
      }
   }

   public void stopRecording() {
      PacketListener packetlistener1 = ReplayModRecording.instance.getConnectionEventHandler().getPacketListener();
      if (packetlistener1 != null) {
         int number2 = (int)packetlistener1.getCurrentDuration();
         GuiRecordingControls guirecordingcontrols3 = ((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls();
         if (!guirecordingcontrols3.isPaused()) {
            packetlistener1.addMarker("_RM_START_CUT", number2);
         }

         packetlistener1.addMarker("_RM_SPLIT", number2 + 1);
         ((ReplayHandlerAccessor)guirecordingcontrols3).bridge$setStopped(true);
         ((ReplayHandlerAccessor)guirecordingcontrols3).bridge$updateState();
      }
   }

   public void method11() {
      if (ReplayModRecording.instance.getConnectionEventHandler().getPacketListener() != null) {
         ReplayModRecording.instance.getConnectionEventHandler().getPacketListener().addMarker("_RM_START_CUT");
         ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$setPaused(true);
         ((ReplayHandlerAccessor)((ConnectionEventHandlerV1_8Accessor)ReplayModRecording.instance.getConnectionEventHandler()).brige$getGuiControls()).bridge$updateState();
      }
   }

   public void method13() {
      PacketListener packetlistener1 = ReplayModRecording.instance.getConnectionEventHandler().getPacketListener();
      if (packetlistener1 != null && ((PacketListenerV1_8Accessor)packetlistener1).bridge$getOutputPath() != null) {
         if (!field4) {
            try {
               Files.createFile(
                  ((PacketListenerV1_8Accessor)packetlistener1).bridge$getOutputPath().resolveSibling(((PacketListenerV1_8Accessor)packetlistener1).bridge$getOutputPath().getFileName() + ".no_recover")
               );
            } catch (IOException exception3) {
               exception3.printStackTrace();
            }
         }
      }
   }

   public Map<String, ClientOption> method14() {
      return field7;
   }

   public boolean method15() {
      EntityRendererHandler entityrendererhandler1 = ((IEntityRenderer)MCVer.getMinecraft().entityRenderer).replayModRender_getHandler();
      return entityrendererhandler1 != null && !entityrendererhandler1.getSettings().isRenderNameTags();
   }
}
