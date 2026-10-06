package com.moonsworth.lunar.client.mod.misc.rewind;

import com.google.gson.JsonObject;
import com.google.protobuf.Any;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator_2;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl5;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl_2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Iterator5;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2Impl4.Type;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Iterator;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Iterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Iterator3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore4Task;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreHandler;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreHandler2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreIterator2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreIterator3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreIterator4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreIterator5;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreIterator6;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreType;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCore_2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCore;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl10;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl11;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl12;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl13;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl5;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl6;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl7;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl8;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreImpl9;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin.RewindhandlersNameplateCoreIterator;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.resourcepack.ResourcePackUpdateEvent;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent.ContainerSlotPreEvent;
import com.moonsworth.lunar.client.event.combat.PreAttackEntityEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockPick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldEffectRecord;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventCursorPosition;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakingProgress;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlockLegacy;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.gui.PacketEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerResourcePackUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.TeleportEvent.TeleportPostEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheelLegacy;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.concurrent.ConsumerExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump11;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import lombok.Generated;

public class RewindHandlers5 extends AbstractFeature {
   private final Rewind field8;
   private final Rewind_4 field9;
   private RewindhandlersNameplateCoreType field10 = RewindhandlersNameplateCoreType.RECORDING;
   private final Executor field11 = Executors.newSingleThreadExecutor(new DefaultThreadFactory("lunar-rewind-recorder-thread", true));
   private int tick = 0;
   private boolean field12 = false;
   private boolean field13 = false;
   private final boolean field14;
   private RewindhandlersNameplateCore4Task field15;
   private RewindhandlersNameplateCore4 field16;
   private final Map<String, Map<String, Object>> field17 = new HashMap<>();
   private final Map<String, JsonObject> field18 = new HashMap<>();
   private final Map<String, Nameplate2Impl3> field19 = new HashMap<>();
   private final List<RewindhandlersNameplateCore> field20 = List.of(
      new RewindhandlersNameplateCoreImpl2(),
      new RewindhandlersNameplateCoreImpl13(),
      new RewindhandlersNameplateCoreImpl(),
      new RewindhandlersNameplateCoreImpl6(),
      new RewindhandlersNameplateCoreImpl3(),
      new RewindhandlersNameplateCoreImpl8(),
      new RewindhandlersNameplateCoreImpl10(),
      new RewindhandlersNameplateCoreImpl4(),
      new RewindhandlersNameplateCoreIterator(),
      new RewindhandlersNameplateCoreImpl7(),
      new RewindhandlersNameplateCoreImpl11(),
      new RewindhandlersNameplateCoreImpl9(),
      new RewindhandlersNameplateCoreImpl12(),
      new RewindhandlersNameplateCoreImpl5()
   );
   private final List<RewindhandlersNameplateCore_2> field21 = List.of(
      new RewindhandlersNameplateCoreIterator5(),
      new RewindhandlersNameplateCoreIterator4(),
      new RewindhandlersNameplateCoreHandler2(),
      new RewindhandlersNameplateCoreIterator6(),
      new com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.RewindhandlersNameplateCoreIterator(),
      new RewindhandlersNameplateCoreIterator3(),
      new RewindhandlersNameplateCoreHandler(),
      new RewindhandlersNameplateCoreIterator2()
   );

   public RewindHandlers5(Rewind var1, boolean var2, boolean var3, boolean var4) {
      super(true);
      if (var3) {
         try {
            this.field15 = new RewindhandlersNameplateCore4Task(var1);
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

      if (var4) {
         try {
            if (ThreadModuleDumpType2.isWindows()) {
               this.field16 = new RewindhandlersNameplateCore4Iterator3();
            } else if (ThreadModuleDumpType2.isLinux()) {
               this.field16 = new RewindhandlersNameplateCore4Iterator2();
            } else if (ThreadModuleDumpType2.isMacos()) {
               this.field16 = new RewindhandlersNameplateCore4Iterator();
            }
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

      this.field8 = var1;
      this.field9 = (Rewind_4)(var2
         ? new RewindIterator_2(this, this.field15, this.field16)
         : new RewindIterator2(this, this.field15, this.field16, (Boolean)var1.method27().get(), false));
      this.field14 = var2;
      this.handle(PacketEvent.class, this::method3);
      this.handle(EventClientTick.class, this::method5);
      this.handle(TeleportPostEvent.class, var1x -> this.method9(var2x -> var2x.method4(var1x, this, this.field9)));
      this.handle(ScreenChangeEvent.class, var1x -> this.method9(var2x -> var2x.method5(var1x, this, this.field9)));
      this.handle(ScreenOpenEvent.class, var1x -> this.method9(var2x -> var2x.method6(var1x, this, this.field9)));
      this.handle(ContainerSlotPreEvent.class, var1x -> this.method9(var2x -> var2x.method7(var1x, this, this.field9)));
      this.handle(MarkerInputEvent.class, var1x -> this.method9(var2x -> var2x.method8(var1x, this, this.field9)));
      this.handle(com.moonsworth.lunar.client.event.input.KeyInputEvent.class, var1x -> this.method9(var2x -> var2x.method9(var1x, this, this.field9)));
      this.handle(EventBlockBreakingProgress.class, var1x -> this.method9(var2x -> var2x.method10(var1x, this, this.field9)));
      this.handle(EventWorldEffectRecord.class, var1x -> this.method9(var2x -> var2x.method11(var1x, this, this.field9)));
      this.handle(PreAttackEntityEvent.class, var1x -> this.method9(var2x -> var2x.method12(var1x, this, this.field9)));
      this.handle(EventUseItemOnBlockLegacy.class, var1x -> this.method9(var2x -> var2x.method13(var1x, this, this.field9)));
      this.handle(
         com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemLegacy.class,
         var1x -> this.method9(var2x -> var2x.method14(var1x, this, this.field9))
      );
      this.handle(ResourcePackUpdateEvent.class, var1x -> this.method9(var2x -> var2x.method15(var1x, this, this.field9)));
      this.handle(ServerResourcePackUpdateEvent.class, var1x -> this.method9(var2x -> var2x.method16(var1x, this, this.field9)));
      this.handle(
         com.moonsworth.lunar.client.event.mixin.gui.ServerResourcePackRemoveEvent.class, var1x -> this.method9(var2x -> var2x.method17(var1x, this, this.field9))
      );
      this.handle(EventBlockPick.class, var1x -> this.method9(var2x -> var2x.method18(var1x, this, this.field9)));
      this.handle(
         com.moonsworth.lunar.client.event.mixin.fishing.RewindFrameEvent.class, var1x -> this.method9(var2x -> var2x.method19(var1x, this, this.field9))
      );
      this.handle(DisconnectEvent.class, this::method6);
      this.handle(EventMouseWheelLegacy.class, this::method7);
      if (this.field15 != null) {
         this.field15.start();
      }

      if (this.field16 != null) {
         this.field16.start();
      }

      this.method2(false);
   }

   public void method1(Any var1) {
      try {
         this.field9.method9(new Nameplate2Impl2(var1.toByteArray()), this.tick);
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }

   public boolean method2(boolean var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      if (var2 != null && var3 != null) {
         this.field9.method9(new com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl2(var1), this.tick);

         for (RewindhandlersNameplateCore_2 var5 : this.field21) {
            var5.method1(this, var2, var3);
         }

         this.field9.method9(new Nameplate2Impl(), this.tick);
         return true;
      } else {
         return false;
      }
   }

   public void method3(PacketEvent var1) {
      if (this.isRecording()) {
         for (RewindhandlersNameplateCore var3 : this.field20) {
            var3.method1(var1, this, this.field9);
         }
      }
   }

   public void method13() {
      if (this.isRecording()) {
         for (RewindhandlersNameplateCore var2 : this.field20) {
            var2.method3(new EventCursorPosition(), this, this.field9);
         }
      }
   }

   private void method5(EventClientTick var1) {
      if (this.field13 && !ThreadModuleDump63.method3().bridge$isGamePaused()) {
         this.method10(false);
      }

      if (this.isRecording()) {
         for (RewindhandlersNameplateCore var3 : this.field20) {
            var3.method2(var1, this, this.field9);
         }

         this.method8(
            this.field17,
            var1x -> {
               for (Entry var3x : ((Map)var1x.getValue()).entrySet()) {
                  Nameplate2Impl_2 var4 = new Nameplate2Impl_2(
                     (String)var1x.getKey(), (String)var3x.getKey(), Nameplate2Impl_2.method3(var3x.getValue()), var3x.getValue()
                  );
                  this.field9.method9(var4, this.tick);
               }
            }
         );
         this.method8(this.field18, var1x -> {
            Nameplate2Iterator5 var2 = new Nameplate2Iterator5((String)var1x.getKey(), (JsonObject)var1x.getValue());
            this.field9.method9(var2, this.tick);
         });
         this.method8(this.field19, var1x -> this.field9.method9((Nameplate2)var1x.getValue(), this.tick));
         this.field9.method6(false);
         this.tick++;
      }
   }

   private void method6(DisconnectEvent var1) {
      if (this.field10 != RewindhandlersNameplateCoreType.STOPPED) {
         this.field9.method9(new Nameplate2Impl4(Type.DISCONNECT), this.tick);
      }
   }

   private void method7(EventMouseWheelLegacy var1) {
      if (this.isRecording()) {
         this.field9.method9(new Nameplate2Impl5(var1.method1()), this.tick);
      }
   }

   private <K, V> void method8(Map<K, V> var1, ConsumerExtension<Entry<K, V>> var2) {
      for (Entry var4 : var1.entrySet()) {
         var2.accept(var4);
      }

      var1.clear();
   }

   private void method9(ConsumerExtension<RewindhandlersNameplateCore> var1) {
      if (this.isRecording()) {
         for (RewindhandlersNameplateCore var3 : this.field20) {
            var1.accept(var3);
         }
      }
   }

   public void method10(boolean var1) {
      if (!var1 || !this.field14 && (Boolean)this.field8.method26().get()) {
         if (var1) {
            this.method11(false);
            this.field13 = true;
         } else if (this.field13) {
            this.resume();
            this.field13 = false;
         }
      }
   }

   public void method11(boolean var1) {
      this.field10 = RewindhandlersNameplateCoreType.PAUSED;
      this.field12 = var1;
      if (this.field15 != null) {
         this.field15.setPaused(true);
      }

      if (this.field16 != null) {
         this.field16.setPaused(true);
      }

      ThreadModuleDump63.method3().bridge$schedule(this.field9::pause);
   }

   public void resume() {
      if (!this.isRecording()) {
         this.field10 = RewindhandlersNameplateCoreType.RECORDING;
         if (this.field15 != null) {
            this.field15.setPaused(false);
         }

         if (this.field16 != null) {
            this.field16.setPaused(false);
         }

         if (this.field12) {
            this.field9.method9(new com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing.Nameplate2Impl4(), this.tick);
         } else if (!this.field13 && !this.method2(false)) {
            this.method11(false);
            ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Cannot resume recording");
         }

         this.field12 = false;
      }
   }

   public void method14() {
      if (this.field14) {
         try {
            long var1 = this.field9.method3();
            String var3 = ThreadModuleDump11.formatDuration(var1);
            if (var3.isEmpty()) {
               var3 = "0s";
            }

            if (this.field9.method11()) {
               ThreadModuleDump63.method4().method69().method6(NotificationType.SUCCESS, "Shadow Rewind", "Saved the last " + var3);
            }

            this.method2(true);
         } catch (Exception var4) {
            ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Cannot save recording: " + var4.getMessage());
            Inventorymod2.method5(var4, "Rewind");
         }
      }
   }

   public void method13(boolean var1, boolean var2) {
      this.field10 = RewindhandlersNameplateCoreType.STOPPED;
      if (this.field15 != null) {
         try {
            this.field15.stop();
         } catch (IOException var5) {
            var5.printStackTrace();
         }
      }

      if (this.field16 != null) {
         try {
            this.field16.stop();
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      }

      Runnable var3 = () -> {
         try {
            if (!this.field14 && !var2) {
               try {
                  if (this.field9.method11()) {
                     ThreadModuleDump63.method4().method69().method6(NotificationType.SUCCESS, "Rewind", "Rewind saved!");
                  }
               } catch (Exception var4x) {
                  if (!var1) {
                     throw var4x;
                  }
               }
            } else {
               this.field9.method10();
            }
         } catch (Exception var5x) {
            ThreadModuleDump63.method4().method69().method6(NotificationType.ERROR, "Rewind", "Cannot save recording: " + var5x.getMessage());
            Inventorymod2.method5(var5x, "Rewind");
         }
      };
      if (var1) {
         var3.run();
      } else {
         this.field8.method36().add(() -> this.field11.execute(var3));
      }
   }

   public boolean isRecording() {
      return this.field10 == RewindhandlersNameplateCoreType.RECORDING;
   }

   public String getId() {
      return "REWIND_HANDLERS";
   }

   @Generated
   public Rewind method15() {
      return this.field8;
   }

   @Generated
   public Rewind_4 method16() {
      return this.field9;
   }

   @Generated
   public RewindhandlersNameplateCoreType method17() {
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
   public Map<String, Nameplate2Impl3> method25() {
      return this.field19;
   }
}
