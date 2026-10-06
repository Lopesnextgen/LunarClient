package com.moonsworth.lunar.client.mod.misc.rewind;

import com.google.gson.JsonObject;
import com.google.protobuf.Any;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator2;
import com.moonsworth.lunar.client.replay.recording.ReplayHandlerImpl;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.network.ProtobufMessagePacket;
import com.moonsworth.lunar.client.replay.network.HudPositionPacket;
import com.moonsworth.lunar.client.replay.network.DisconnectPacket;
import com.moonsworth.lunar.client.replay.network.MouseWheelPacket;
import com.moonsworth.lunar.client.replay.network.SettingValuePacket;
import com.moonsworth.lunar.client.replay.network.ModuleConfigPacket;
import com.moonsworth.lunar.client.replay.network.DisconnectPacket.DisconnectMode;
import com.moonsworth.lunar.client.replay.network.SnapshotEndPacket;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Iterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Iterator3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Task;
import com.moonsworth.lunar.client.replay.recording.ContainerCapture;
import com.moonsworth.lunar.client.replay.recording.PlayerStateCapture;
import com.moonsworth.lunar.client.replay.recording.ApolloSettingsCapture;
import com.moonsworth.lunar.client.replay.recording.ScoreboardCapture;
import com.moonsworth.lunar.client.replay.recording.WorldStateCapture;
import com.moonsworth.lunar.client.replay.recording.EntityStateCapture;
import com.moonsworth.lunar.client.replay.recording.PlayerListCapture;
import com.moonsworth.lunar.client.replay.recording.RecorderState;
import com.moonsworth.lunar.client.replay.recording.RecorderCapture;
import com.moonsworth.lunar.client.replay.recording.RecorderEventListener;
import com.moonsworth.lunar.client.replay.recording.RecorderEventAdapter;
import com.moonsworth.lunar.client.replay.recording.ResourcePackRecorder;
import com.moonsworth.lunar.client.replay.recording.MessageSignatureRecorder;
import com.moonsworth.lunar.client.replay.recording.LocationRecorder;
import com.moonsworth.lunar.client.replay.recording.PacketRecorder;
import com.moonsworth.lunar.client.replay.recording.KeyframeRecorder;
import com.moonsworth.lunar.client.replay.recording.GuiStateRecorder;
import com.moonsworth.lunar.client.replay.recording.CosmeticsRecorder;
import com.moonsworth.lunar.client.replay.recording.ScoreboardRecorder;
import com.moonsworth.lunar.client.replay.recording.PlayerStateRecorder;
import com.moonsworth.lunar.client.replay.recording.KeybindRecorder;
import com.moonsworth.lunar.client.replay.recording.WorldEffectRecorder;
import com.moonsworth.lunar.client.replay.recording.HudStateRecorder;
import com.moonsworth.lunar.client.replay.recording.SettingRecorder;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.resourcepack.EventResourcePackUpdate;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPre;
import com.moonsworth.lunar.client.event.combat.EventPreAttackEntity;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPickBlock;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldEffect;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventMousePosition;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakProgress;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlock;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventPacket;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventTeleportBase.EventTeleportPost;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheel;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.util.concurrent.ConsumerExtension;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.OperatingSystem;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import lombok.Generated;

public class RewindRecorder extends AbstractFeature {
   private final RewindMod field8;
   private final ReplayHandler field9;
   private RecorderState field10 = RecorderState.RECORDING;
   private final Executor field11 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-rewind-recorder-thread", true));
   private int tick = 0;
   private boolean field12 = false;
   private boolean field13 = false;
   private final boolean field14;
   private RewindhandlersNameplateCore4Task field15;
   private RewindhandlersNameplateCore4 field16;
   private final Map<String, Map<String, Object>> field17 = new HashMap<>();
   private final Map<String, JsonObject> field18 = new HashMap<>();
   private final Map<String, HudPositionPacket> field19 = new HashMap<>();
   private final List<RecorderEventListener> field20 = List.of(
      new KeyframeRecorder(),
      new PacketRecorder(),
      new RecorderEventAdapter(),
      new PlayerStateRecorder(),
      new GuiStateRecorder(),
      new WorldEffectRecorder(),
      new ResourcePackRecorder(),
      new CosmeticsRecorder(),
      new SettingRecorder(),
      new KeybindRecorder(),
      new MessageSignatureRecorder(),
      new HudStateRecorder(),
      new LocationRecorder(),
      new ScoreboardRecorder()
   );
   private final List<RecorderCapture> field21 = List.of(
      new EntityStateCapture(),
      new WorldStateCapture(),
      new PlayerStateCapture(),
      new PlayerListCapture(),
      new com.moonsworth.lunar.client.replay.recording.EntityPairingCapture(),
      new ScoreboardCapture(),
      new ContainerCapture(),
      new ApolloSettingsCapture()
   );

   public RewindRecorder(RewindMod rewind1, boolean flag2, boolean flag3, boolean flag4) {
      super(true);
      if (flag3) {
         try {
            this.field15 = new RewindhandlersNameplateCore4Task(rewind1);
         } catch (Exception exception7) {
            exception7.printStackTrace();
         }
      }

      if (flag4) {
         try {
            if (OperatingSystem.isWindows()) {
               this.field16 = new RewindhandlersNameplateCore4Iterator3();
            } else if (OperatingSystem.isLinux()) {
               this.field16 = new RewindhandlersNameplateCore4Iterator2();
            } else if (OperatingSystem.isMacos()) {
               this.field16 = new RewindhandlersNameplateCore4Iterator();
            }
         } catch (Exception exception6) {
            exception6.printStackTrace();
         }
      }

      this.field8 = rewind1;
      this.field9 = (ReplayHandler)(flag2
         ? new ReplayHandlerImpl(this, this.field15, this.field16)
         : new RewindIterator2(this, this.field15, this.field16, (Boolean)rewind1.method27().get(), false));
      this.field14 = flag2;
      this.handle(EventPacket.class, this::method3);
      this.handle(EventTick.class, this::method5);
      this.handle(EventTeleportPost.class, arg1x -> this.method9(arg2x -> arg2x.method4(arg1x, this, this.field9)));
      this.handle(EventScreenChange.class, arg1x -> this.method9(arg2x -> arg2x.method5(arg1x, this, this.field9)));
      this.handle(EventScreenOpen.class, arg1x -> this.method9(arg2x -> arg2x.method6(arg1x, this, this.field9)));
      this.handle(EventRenderContainerSlotPre.class, arg1x -> this.method9(arg2x -> arg2x.method7(arg1x, this, this.field9)));
      this.handle(EventMarkerInput.class, arg1x -> this.method9(arg2x -> arg2x.method8(arg1x, this, this.field9)));
      this.handle(com.moonsworth.lunar.client.event.input.EventKeyInput.class, arg1x -> this.method9(arg2x -> arg2x.method9(arg1x, this, this.field9)));
      this.handle(EventBlockBreakProgress.class, arg1x -> this.method9(arg2x -> arg2x.method10(arg1x, this, this.field9)));
      this.handle(EventWorldEffect.class, arg1x -> this.method9(arg2x -> arg2x.method11(arg1x, this, this.field9)));
      this.handle(EventPreAttackEntity.class, arg1x -> this.method9(arg2x -> arg2x.method12(arg1x, this, this.field9)));
      this.handle(EventUseItemOnBlock.class, arg1x -> this.method9(arg2x -> arg2x.method13(arg1x, this, this.field9)));
      this.handle(
         com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItem.class,
         arg1x -> this.method9(arg2x -> arg2x.method14(arg1x, this, this.field9))
      );
      this.handle(EventResourcePackUpdate.class, arg1x -> this.method9(arg2x -> arg2x.method15(arg1x, this, this.field9)));
      this.handle(EventServerResourcePackUpdate.class, arg1x -> this.method9(arg2x -> arg2x.method16(arg1x, this, this.field9)));
      this.handle(
         com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackRemove.class, arg1x -> this.method9(arg2x -> arg2x.method17(arg1x, this, this.field9))
      );
      this.handle(EventPickBlock.class, arg1x -> this.method9(arg2x -> arg2x.method18(arg1x, this, this.field9)));
      this.handle(
         com.moonsworth.lunar.client.event.mixin.fishing.EventDropItem.class, arg1x -> this.method9(arg2x -> arg2x.method19(arg1x, this, this.field9))
      );
      this.handle(EventDisconnect.class, this::method6);
      this.handle(EventMouseWheel.class, this::method7);
      if (this.field15 != null) {
         this.field15.start();
      }

      if (this.field16 != null) {
         this.field16.start();
      }

      this.method2(false);
   }

   public void method1(Any any1) {
      try {
         this.field9.method9(new ProtobufMessagePacket(any1.toByteArray()), this.tick);
      } catch (Exception exception3) {
         throw new RuntimeException(exception3);
      }
   }

   public boolean method2(boolean flag1) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (bridge5extension_52 != null && itemcounter6extension3 != null) {
         this.field9.method9(new com.moonsworth.lunar.client.replay.network.SnapshotStartPacket(flag1), this.tick);

         for (RecorderCapture rewindhandlersnameplatecore_25 : this.field21) {
            rewindhandlersnameplatecore_25.method1(this, bridge5extension_52, itemcounter6extension3);
         }

         this.field9.method9(new SnapshotEndPacket(), this.tick);
         return true;
      } else {
         return false;
      }
   }

   public void method3(EventPacket highlightimpl131) {
      if (this.isRecording()) {
         for (RecorderEventListener rewindhandlersnameplatecore3 : this.field20) {
            rewindhandlersnameplatecore3.method1(highlightimpl131, this, this.field9);
         }
      }
   }

   public void method13() {
      if (this.isRecording()) {
         for (RecorderEventListener rewindhandlersnameplatecore2 : this.field20) {
            rewindhandlersnameplatecore2.method3(new EventMousePosition(), this, this.field9);
         }
      }
   }

   private void method5(EventTick highlightimpl21) {
      if (this.field13 && !Ref.method3().bridge$isGamePaused()) {
         this.method10(false);
      }

      if (this.isRecording()) {
         for (RecorderEventListener rewindhandlersnameplatecore3 : this.field20) {
            rewindhandlersnameplatecore3.method2(highlightimpl21, this, this.field9);
         }

         this.method8(
            this.field17,
            arg1x -> {
               for (Entry entry3x : ((Map)arg1x.getValue()).entrySet()) {
                  SettingValuePacket nameplate2impl_24 = new SettingValuePacket(
                     (String)arg1x.getKey(), (String)entry3x.getKey(), SettingValuePacket.method3(entry3x.getValue()), entry3x.getValue()
                  );
                  this.field9.method9(nameplate2impl_24, this.tick);
               }
            }
         );
         this.method8(this.field18, arg1x -> {
            ModuleConfigPacket nameplate2iterator52 = new ModuleConfigPacket((String)arg1x.getKey(), (JsonObject)arg1x.getValue());
            this.field9.method9(nameplate2iterator52, this.tick);
         });
         this.method8(this.field19, arg1x -> this.field9.method9((ReplayPacket)arg1x.getValue(), this.tick));
         this.field9.method6(false);
         this.tick++;
      }
   }

   private void method6(EventDisconnect highlightimpl111) {
      if (this.field10 != RecorderState.STOPPED) {
         this.field9.method9(new DisconnectPacket(DisconnectMode.DISCONNECT), this.tick);
      }
   }

   private void method7(EventMouseWheel highlightimpl61) {
      if (this.isRecording()) {
         this.field9.method9(new MouseWheelPacket(highlightimpl61.method1()), this.tick);
      }
   }

   private <K, V> void method8(Map<K, V> map1, ConsumerExtension<Entry<K, V>> consumerextension2) {
      for (Entry entry4 : map1.entrySet()) {
         consumerextension2.accept(entry4);
      }

      map1.clear();
   }

   private void method9(ConsumerExtension<RecorderEventListener> consumerextension1) {
      if (this.isRecording()) {
         for (RecorderEventListener rewindhandlersnameplatecore3 : this.field20) {
            consumerextension1.accept(rewindhandlersnameplatecore3);
         }
      }
   }

   public void method10(boolean flag1) {
      if (!flag1 || !this.field14 && (Boolean)this.field8.method26().get()) {
         if (flag1) {
            this.method11(false);
            this.field13 = true;
         } else if (this.field13) {
            this.resume();
            this.field13 = false;
         }
      }
   }

   public void method11(boolean flag1) {
      this.field10 = RecorderState.PAUSED;
      this.field12 = flag1;
      if (this.field15 != null) {
         this.field15.setPaused(true);
      }

      if (this.field16 != null) {
         this.field16.setPaused(true);
      }

      Ref.method3().bridge$schedule(this.field9::pause);
   }

   public void resume() {
      if (!this.isRecording()) {
         this.field10 = RecorderState.RECORDING;
         if (this.field15 != null) {
            this.field15.setPaused(false);
         }

         if (this.field16 != null) {
            this.field16.setPaused(false);
         }

         if (this.field12) {
            this.field9.method9(new com.moonsworth.lunar.client.replay.network.ResetLevelPacket(), this.tick);
         } else if (!this.field13 && !this.method2(false)) {
            this.method11(false);
            Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Cannot resume recording");
         }

         this.field12 = false;
      }
   }

   public void method14() {
      if (this.field14) {
         try {
            long number1 = this.field9.method3();
            String text3 = TimeFormatting.method1(number1);
            if (text3.isEmpty()) {
               text3 = "0s";
            }

            if (this.field9.method11()) {
               Ref.method4().method69().method6(NotificationType.SUCCESS, "Shadow RewindMod", "Saved the last " + text3);
            }

            this.method2(true);
         } catch (Exception exception4) {
            Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Cannot save recording: " + exception4.getMessage());
            CrashReporter.method5(exception4, "RewindMod");
         }
      }
   }

   public void method13(boolean flag1, boolean flag2) {
      this.field10 = RecorderState.STOPPED;
      if (this.field15 != null) {
         try {
            this.field15.stop();
         } catch (IOException exception5) {
            exception5.printStackTrace();
         }
      }

      if (this.field16 != null) {
         try {
            this.field16.stop();
         } catch (IOException exception4) {
            exception4.printStackTrace();
         }
      }

      Runnable runnable3 = () -> {
         try {
            if (!this.field14 && !flag2) {
               try {
                  if (this.field9.method11()) {
                     Ref.method4().method69().method6(NotificationType.SUCCESS, "RewindMod", "RewindMod saved!");
                  }
               } catch (Exception exception4x) {
                  if (!flag1) {
                     throw exception4x;
                  }
               }
            } else {
               this.field9.method10();
            }
         } catch (Exception exception5x) {
            Ref.method4().method69().method6(NotificationType.ERROR, "RewindMod", "Cannot save recording: " + exception5x.getMessage());
            CrashReporter.method5(exception5x, "RewindMod");
         }
      };
      if (flag1) {
         runnable3.run();
      } else {
         this.field8.method36().add(() -> this.field11.execute(runnable3));
      }
   }

   public boolean isRecording() {
      return this.field10 == RecorderState.RECORDING;
   }

   public String getId() {
      return "REWIND_HANDLERS";
   }

   @Generated
   public RewindMod method15() {
      return this.field8;
   }

   @Generated
   public ReplayHandler method16() {
      return this.field9;
   }

   @Generated
   public RecorderState method17() {
      return this.field10;
   }

   @Generated
   public int getTick() {
      return this.tick;
   }

   @Generated
   public boolean method19() {
      return this.field12;
   }

   @Generated
   public boolean method21() {
      return this.field13;
   }

   @Generated
   public boolean method22() {
      return this.field14;
   }

   @Generated
   public Map<String, Map<String, Object>> method23() {
      return this.field17;
   }

   @Generated
   public Map<String, JsonObject> method24() {
      return this.field18;
   }

   @Generated
   public Map<String, HudPositionPacket> method25() {
      return this.field19;
   }
}
