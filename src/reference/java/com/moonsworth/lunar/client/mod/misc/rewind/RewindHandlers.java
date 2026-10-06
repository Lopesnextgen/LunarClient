package com.moonsworth.lunar.client.mod.misc.rewind;

import com.lunarclient.websocket.heartbeat.v1.GameHeartbeatRequest;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.NetworkManagerBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.external.RecordingExternalLink.Data;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.gui.ReplayKeybindHandler;
import com.moonsworth.lunar.client.replay.timeline.RewindIterator;
import com.moonsworth.lunar.client.replay.timeline.GameplaySegment;
import com.moonsworth.lunar.client.replay.timeline.GameplayTrack;
import com.moonsworth.lunar.client.replay.timeline.Track;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.framework.feature.rewind.mixin.Rewind2;
import com.moonsworth.lunar.client.replay.project.RewindFileReader;
import com.moonsworth.lunar.client.replay.network.TickMarkerPacket;
import com.moonsworth.lunar.client.replay.recording.ReplayClock;
import com.moonsworth.lunar.client.replay.gui.InputTimelinePanel;
import com.moonsworth.lunar.client.replay.export.ExportSettingsPanel;
import com.moonsworth.lunar.client.replay.gui.EntityOptionOverrides;
import com.moonsworth.lunar.client.replay.gui.EffectsPanel;
import com.moonsworth.lunar.client.replay.gui.RewindTimelinePanel;
import com.moonsworth.lunar.client.replay.gui.RewindTimelinesListPanel;
import com.moonsworth.lunar.client.replay.gui.RewindPropertiesPanel;
import com.moonsworth.lunar.client.replay.gui.RewindEditorContext;
import com.moonsworth.lunar.client.replay.gui.RewindPropertyProvider;
import com.moonsworth.lunar.client.replay.export.MediaExporter;
import com.moonsworth.lunar.client.replay.render.PlaybackStateProvider;
import com.moonsworth.lunar.client.replay.gui.RewindEditorBridge;
import com.moonsworth.lunar.client.replay.gui.LocalPlayerContext;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.gui.RewindingContext;
import com.moonsworth.lunar.client.replay.gui.EntityContextMenu;
import com.moonsworth.lunar.client.replay.render.ReplayDriverHandler;
import com.moonsworth.lunar.client.replay.render.ReplaySoundHandler;
import com.moonsworth.lunar.client.replay.render.SelectionHighlightHandler;
import com.moonsworth.lunar.client.replay.render.EntityOverrideRenderer;
import com.moonsworth.lunar.client.replay.render.HudVisibilityHandler;
import com.moonsworth.lunar.client.replay.render.ScreenRenderHandler;
import com.moonsworth.lunar.client.replay.render.WorldRenderHandler;
import com.moonsworth.lunar.client.replay.render.ZoomKeyHandler;
import com.moonsworth.lunar.client.replay.render.ExportTargetHandler;
import com.moonsworth.lunar.client.replay.render.ResourcePackHandler;
import com.moonsworth.lunar.client.replay.render.CameraUpdateHandler;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindFrame;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRewindUpdate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickStart;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickEnd;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionUpdateListeners;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.ui.CursorManager;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.concurrent.AsyncResourceManager;
import com.moonsworth.lunar.ichor.util.FlawlessFrames;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;

public class RewindHandlers extends AbstractFeature {
   private final LoadingStageImpl field8 = Ref.method4().method90();
   private final List<RewindPropertyProvider> providers = new ArrayList<>();
   private final RewindPropertyProvider field9 = new PlaybackStateProvider(this.providers);
   private final RewindPropertyProvider field10 = new RewindTimelinePanel(this.providers);
   private final RewindPropertyProvider field11 = new MediaExporter(this.providers);
   private final RewindPropertyProvider field12 = new RewindTimelinesListPanel(this.providers);
   private final RewindPropertyProvider field13 = new RewindPropertiesPanel(this.providers);
   private final RewindPropertyProvider field14 = new ExportSettingsPanel(this.providers);
   private final RewindPropertyProvider field15 = new EffectsPanel(this.providers);
   private final RewindPropertyProvider field16 = new EntityContextMenu(this.providers);
   private final RewindPropertyProvider field17 = new InputTimelinePanel(this.providers);
   private final ReplayProjectManager field18;
   private NetworkManagerBridge field19;
   private final ReplayClock field20 = new ReplayClock();
   private final ValueHolder<ReplayContext> field21 = new ValueHolder(new ReplayContext(this));
   private final EntityOptionOverrides field22 = new EntityOptionOverrides();
   private boolean field23 = true;
   private final CameraUpdateHandler field24 = new CameraUpdateHandler(this.field21);
   private final ScreenRenderHandler field25 = new ScreenRenderHandler(this.field21);
   private final ResourcePackHandler field26 = new ResourcePackHandler(this.field21);
   private final ExportTargetHandler field27 = new ExportTargetHandler(this.field21);
   private final ReplayDriverHandler field28 = new ReplayDriverHandler(this.field21);
   private final SelectionHighlightHandler field29 = new SelectionHighlightHandler(this.field21);
   private final ZoomKeyHandler field30 = new ZoomKeyHandler(this.field21);
   private final WorldRenderHandler field31 = new WorldRenderHandler(this.field21);
   private final HudVisibilityHandler field32 = new HudVisibilityHandler(this.field21);
   private final ReplaySoundHandler field33 = new ReplaySoundHandler(this.field21);
   private final EntityOverrideRenderer field34 = new EntityOverrideRenderer(this.field21);
   private final ReplayKeybindHandler field35 = new ReplayKeybindHandler();
   private final RewindRenderQueue field36;
   private long field37 = 0L;
   private long field38 = 0L;
   private long field39 = 0L;
   private long field40 = 0L;
   private long field41 = System.currentTimeMillis();
   private float field42;
   private float field43;
   private final RewindAudioManager field44 = new RewindAudioManager(this);
   private long field45 = 0L;
   private boolean field46 = false;
   private boolean field47 = false;
   private boolean field48 = false;
   private boolean field49 = false;
   private boolean field50 = false;
   private final UUID field51 = new UUID(Long.MAX_VALUE, Long.MIN_VALUE);
   private final Data field52;
   private final long field53 = System.currentTimeMillis();

   public RewindHandlers(File file1, String text2, ExportSettings rewindhandlersnameplate3, boolean flag4) {
      super(true);
      this.field18 = new ReplayProjectManager(file1, this, text2, rewindhandlersnameplate3, flag4);
      this.field36 = new RewindRenderQueue(this);
      RewindEditorBridge.reset();
      this.method27();
      this.handle(EventRewindUpdate.class, this::method6);
      this.handle(EventTick.class, this::method7);
      this.handle(EventRewindTick.class, this::method9);
      this.handle(EventRenderTickStart.class, this::method10);
      this.handle(EventRenderTickEnd.class, this::method11);
      this.field27.method14();
      UUID uuid5 = Ref.method3().bridge$getSession().bridge$getProfile().getId();
      CosmeticManager holograms126 = Client.method109().method53();
      this.field52 = new Data(new HashSet(holograms126.method13()), new HashMap(holograms126.method58()));
      holograms126.method28(uuid5, this.field51);
      IntegerOption lightingextension4227 = Ref.method4().method41().method7().method29();
      OptionUpdateListeners nameplate28 = (OptionUpdateListeners)lightingextension4227.method7(OptionTraits.field1);
      if (nameplate28 != null) {
         nameplate28.forEach(arg0 -> arg0.accept(Ref.method3().bridge$getGameSettings().bridge$getFrameRateLimit()));
      }
   }

   private void method13() {
      if (this.field19 != null) {
         try {
            this.field19.bridge$channelInactive();
         } catch (Exception exception2) {
         }

         this.field19 = null;
      }
   }

   public void method14() {
      this.method13();
      if (Ref.method8() != null) {
         Ref.method3().bridge$clearLevel();
      }

      Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$clearChatMessages();
      Horsestats horsestats1 = Ref.method3().bridge$getSession();
      UUID uuid2 = horsestats1.bridge$getProfile().getId();
      String text3 = horsestats1.bridge$getUsername();
      String text4 = "localhost";
      RewindFileReader rewind35 = this.field18.method35();
      if (rewind35 != null) {
         Rewind2 rewind26 = rewind35.method13();
         uuid2 = rewind26.method8();
         text3 = rewind26.getPlayerName();
      }

      this.field19 = Bridge.method8().method77(uuid2, text3, text4);
   }

   public long method3(long number1) {
      this.field36.method17();
      this.field46 = false;
      if (Ref.method3().bridge$hasInGameFocus()) {
         Ref.method3().bridge$setInGameFocus(false);
      }

      GuiScreenBridge bridge5extension63 = Ref.method3().bridge$getCurrentScreen();
      if (bridge5extension63 != null) {
         bridge5extension63.bridge$setAllowUserInput(false);
      }

      this.field27.method21(false);
      ReplayTimeline highlight_34 = this.field18.method37();
      if (highlight_34 != null) {
         highlight_34.method23(true);
      }

      long number5 = this.field18.method28(number1);
      this.method14();
      this.field20.reset();
      this.field20.method11(number5);
      com.moonsworth.lunar.client.replay.gui.LayerPropertiesContext nameplate7 = ((ReplayContext)this.field21.get()).method9();
      this.field21.set(new ReplayContext(this));
      ((ReplayContext)this.field21.get()).method24(nameplate7);
      RewindFileReader rewind38 = this.field18.method35();
      if (rewind38 != null) {
         ((ReplayContext)this.field21.get()).method34(rewind38.method13().method2());
      }

      return number5;
   }

   public void method15() {
      this.field36.method12(this.field18.method37());
   }

   public void method16() {
      ReplayTimeline highlight_31 = this.field18.method37();
      if (highlight_31 != null) {
         for (Track gui_23 : highlight_31.method11()) {
            Entry entry4 = gui_23.method1(highlight_31.method15());
            if (entry4 != null) {
               RewindEditorContext.resolveProperty((RewindIterator)entry4.getValue(), "camera", this.field24.method16().getId(), true);
            }
         }
      }
   }

   private void method6(EventRewindUpdate highlightimpl61) {
      if (!this.isReloading()) {
         if (DriverViewportLegacy.method50().method61() != DriverRouteRegistry.field10 || DriverViewportLegacy.method50().method63() == DriverRouteRegistry.field10) {
            ReplayContext nameplate42 = (ReplayContext)this.field21.get();
            ReplayTimeline highlight_33 = this.field18.method37();
            if (highlight_33 != null) {
               if (this.field50) {
                  highlight_33.method23(true);

                  for (int index4 = 0; index4 < 5 && this.field50; index4++) {
                     this.method17();
                  }

                  this.method9(new EventRewindTick());
                  highlight_33.method23(this.field50);
               }

               if (this.field46) {
                  this.method16();
               }

               this.field20.method20(false);
               if (!this.field20.method2() && !highlight_33.isPaused()) {
                  highlight_33.method6();
               }

               if (Ref.method8() != null
                  && !nameplate42.method18()
                  && !highlight_33.isPaused()
                  && !(Boolean)this.field20.method17().get()
                  && this.field18.method35() != null) {
                  if (this.method25()) {
                     highlight_33.method23(false);
                     highlight_33.method8(highlight_33.method3() - 1);
                  }

                  this.field20.update();
               } else {
                  this.field20.method1();
                  com.moonsworth.lunar.client.replay.gui.LayerPropertiesContext nameplate6 = nameplate42.method9();
                  boolean flag5 = !this.field20.method5() || this.field20.method18() <= highlight_33.method9();
                  if (flag5) {
                     nameplate6.method2();
                     this.field27.method21(true);
                     this.field26.apply();
                  }

                  this.field33.method2(RewindEditorContext.isDragging() || highlight_33.isPaused() && !this.field36.method25() || (Boolean)this.field20.method17().get());
                  highlight_33.method7(this.field20, false);
                  highlight_33.method23(!flag5 || this.field26.method34() || !this.field20.method2());
                  highlight_33.method4(this.field21, highlight_33.method15());
                  if (this.field36.method25()) {
                     Ref.method3().bridge$waitOnAllChunksRendering();
                  }

                  this.field15.method1(this);
                  if (flag5 || this.field36.method25()) {
                     this.field24.update();
                     nameplate6.cleanup();
                     this.field26.method16();
                     this.field44.method13();
                  }

                  if (this.field20.method5() && !this.field20.method22()) {
                     this.field20.method4(this);
                  }
               }

               this.field47 = false;
            }
         }
      }
   }

   private void method7(EventTick highlightimpl21) {
      if (!this.field50) {
         LocalPlayerContext nameplate32 = ((ReplayContext)this.field21.get()).method7();
         nameplate32.method1(nameplate32.getYaw(), nameplate32.getPitch());
         nameplate32.setPos(nameplate32.getX(), nameplate32.getY(), nameplate32.getZ());
         this.method17();
      }
   }

   private void method17() {
      if (!this.isReloading() && !this.field49 && !this.field48) {
         boolean flag1 = false;
         int index2 = 0;

         do {
            if (flag1) {
               if (index2 % 20 == 0) {
                  DriverViewportLegacy.method52().invokeIteration();
               }

               this.field49 = true;

               try {
                  Ref.method3().bridge$processRewindTick();
               } catch (Exception exception4) {
                  exception4.printStackTrace();
               }

               this.field49 = false;
               index2++;
            }

            LunarEventBus.method29().method12(EventRewindTick.class, EventRewindTick::new);
            if (this.method23()) {
               ((ReplayContext)this.field21.get()).method1(this.field20.method7());
            } else if (this.field50) {
               break;
            }

            this.field20.method10();
            this.field20.method4(this);
            flag1 = true;
         } while (this.field20.method5());

         GuiScreenBridge bridge5extension63 = Ref.method3().bridge$getCurrentScreen();
         if (bridge5extension63 != null) {
            bridge5extension63.bridge$setAllowUserInput(this.field46 && this.method26());
         }
      }
   }

   private void method9(EventRewindTick highlightimpl31) {
      if (!this.field50) {
         ReplayContext nameplate42 = (ReplayContext)this.field21.get();

         while (!nameplate42.method23().isEmpty()) {
            try {
               ((Runnable)nameplate42.method23().poll()).run();
            } catch (Exception exception4) {
               CrashReporter.method5(exception4, "RewindMod");
            }
         }
      }
   }

   private void method10(EventRenderTickStart data51) {
      long number2 = System.currentTimeMillis();
      long number4 = number2 - this.field37;
      if (Ref.method7() != null && Ref.method3().bridge$getPlayerController() != null) {
         LunarEventBus.method29().method12(EventRewindFrame.class, () -> new EventRewindFrame((float)number4 / 50.0F));
      }
   }

   private void method11(EventRenderTickEnd data61) {
      GuiScreenBridge bridge5extension62 = Ref.method3().bridge$getCurrentScreen();
      if (!this.field48 && (bridge5extension62 == null || DriverViewportLegacy.method50().method61() != DriverRouteRegistry.field10)) {
         this.method22();
      }

      Bridge5Extension_5 bridge5extension_53 = Ref.method7();
      if (bridge5extension_53 != null) {
         bridge5extension_53.bridge$resetFallDistance();
      }

      if (this.field20.method2()
         && !this.isReloading()
         && !this.method25()
         && ((ReplayContext)this.field21.get()).method18()
         && !this.field27.method28()
         && this.field27.method24().method11() != null
         && this.field20.method8() > 250L) {
         this.field18.method41().method6(this, this.field18.method37().method15(), this.field27.method24());
      }

      if (this.field18.method34() + 300000L < System.currentTimeMillis()) {
         try {
            this.field18.method17(true);
         } catch (IOException exception6) {
            exception6.printStackTrace();
         }
      }

      if (RewindEditorContext.isDragging() && !Bridge.method20().method1(0) && !Bridge.method20().method1(1) && !Bridge.method20().method1(2)) {
         DriverViewportLegacy.method50().method55().method6();
         RewindEditorBridge.stopPlayheadDrag();
      }

      if (this.field41 + 60000L < System.currentTimeMillis()) {
         this.field41 = System.currentTimeMillis();

         try {
            this.field18.method41().method7(this.field27.method24());
         } catch (Throwable exception5) {
         }
      }

      this.method19();
   }

   private void method19() {
      long number1 = System.currentTimeMillis();
      long number3 = number1 - this.field37;
      if (Ref.MC_VERSION <= 5 || Ref.method7() != null) {
         try {
            Ref.method3().bridge$processKeyboardAndMouse();
         } catch (Exception exception7) {
         }
      }

      if (Ref.method7() != null && Ref.method3().bridge$getPlayerController() != null) {
         LunarEventBus.method29().method12(EventRewindFrame.class, () -> new EventRewindFrame((float)number3 / 50.0F));
      }

      this.field37 = number1;
      if (this.field46 && !Bridge.method20().method1(0) && !Bridge.method20().method1(2)) {
         com.moonsworth.lunar.client.replay.timeline.UndoRedoManager nameplate25 = this.field18.method40();
         nameplate25.endBatch();
         DriverViewportLegacy.method50().method55().method6();
         this.method54(false);
         Ref.method3().bridge$setInGameFocus(false);
         GuiScreenBridge bridge5extension66 = Ref.method3().bridge$getCurrentScreen();
         if (bridge5extension66 != null) {
            bridge5extension66.bridge$setAllowUserInput(false);
         }
      }

      this.method21();
   }

   private void method21() {
      ReplayTimeline highlight_31 = this.field18.method37();
      if (highlight_31 != null) {
         long number2 = System.currentTimeMillis();
         if (number2 - this.field38 >= 50L) {
            this.field38 = number2;
            if (!this.field36.method25()) {
               this.field9.method1(this);
               FlawlessFrames.set((Boolean)this.field8.method22().get());
            }

            if (number2 - this.field39 >= 500L) {
               this.field39 = number2;
               if (this.field36.method25()) {
                  this.field9.method1(this);
               }

               if (!this.field18.method40().method3() || this.method62()) {
                  this.field13.method1(this);
               }

               if (RewindEditorContext.isDragging() && RewindEditorContext.getScrubFrame() >= 0) {
                  highlight_31.method8(RewindEditorContext.getScrubFrame());
               }

               this.field17.method1(this);
            }

            if (!highlight_31.isPaused() && !this.method25() && !(Boolean)this.field20.method17().get()) {
               for (GameplayTrack guiimpl35 : highlight_31.method11().method3()) {
                  GameplaySegment rewinditerator236 = (GameplaySegment)guiimpl35.method5().method6(highlight_31.method15());
                  if (guiimpl35.isEnabled() && rewinditerator236 != null) {
                     return;
                  }
               }
            }

            DriverViewportLegacy.method50().tick();
            if (!this.field36.method25()) {
               CursorManager.tick();
            }

            AsyncResourceManager.field1.method7(false);
            if (this.field40 + 60000L <= number2) {
               Ref.method5().ifPresent(arg0 -> arg0.method94().gameHeartbeat(null, GameHeartbeatRequest.getDefaultInstance(), arg0x -> {}));
               this.field40 = number2;
            }
         }
      }
   }

   public void method22() {
      DriverViewportLegacy.method50().method18(DriverRouteRegistry.field10, true);
   }

   private boolean method23() {
      this.field50 = false;
      ReplayContext nameplate41 = (ReplayContext)this.field21.get();
      if (!this.field20.method2()) {
         return false;
      }

      boolean flag2 = this.field20.method7();

      com.moonsworth.lunar.client.replay.network.ReplayPacket nameplate23;
      while ((nameplate23 = this.field18.method24(flag2, this.field36.method25())) != null) {
         try {
            nameplate41.method31(this.field19.bridge$getClientPacketListener());
            if (nameplate23 instanceof TickMarkerPacket nameplate2impl4 && (!flag2 && nameplate41.getTick() < nameplate2impl4.getTick() || flag2 && nameplate41.getTick() > nameplate2impl4.getTick())) {
               if (nameplate41.getTick() != 0) {
                  return true;
               }

               nameplate41.setTick(nameplate2impl4.getTick());
            }

            this.field18.consume();
            if (nameplate41.method2()) {
               try {
                  RewindingContext nameplate512 = nameplate41.method10();
                  nameplate512.method3(flag2);
                  if (flag2) {
                     try {
                        com.moonsworth.lunar.client.replay.network.ReplayPacket nameplate25 = nameplate23.method4(nameplate41);
                        if (nameplate25 != null) {
                           nameplate23 = nameplate25;
                        }

                        int number14 = (int)Math.floor(nameplate41.method7().getX() / 16.0);
                        int number7 = (int)Math.floor(nameplate41.method7().getZ() / 16.0);
                        WorldBridgeExtension itemcounter6extension8 = Ref.method8();
                        if (itemcounter6extension8 != null && !itemcounter6extension8.bridge$isChunkLoaded(number14, number7)) {
                           throw new RuntimeException("No chunks loaded");
                        }
                     } catch (Exception exception9) {
                        exception9.printStackTrace();
                        long number6 = this.field20.method9();
                        this.field20.reset();
                        Ref.method3().bridge$schedule(() -> {
                           try {
                              this.field18.method37().method6();
                              long number3x = this.method3(number6);
                              this.field20.method3(number6 - number3x);
                           } catch (IOException exception5x) {
                              throw new RuntimeException(exception5x);
                           }
                        });
                        return false;
                     }
                  }

                  nameplate23.method3(nameplate41);
               } catch (Exception exception10) {
                  exception10.printStackTrace();
               }
            }
         } catch (Exception exception11) {
            exception11.printStackTrace();
         }
      }

      boolean flag13 = this.method25();
      this.field50 = this.field18.method25() || !flag13 && !this.field18.method26(this.field20.method7());
      return false;
   }

   public void method24() {
      GuiScreenBridge bridge5extension61 = this.mc.bridge$getCurrentScreen();
      if (bridge5extension61 != null) {
         bridge5extension61.bridge$setAllowUserInput(false);
      }

      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         bridge5extension_52.bridge$getMovementInput().bridge$setMoveForward(0.0F);
         bridge5extension_52.bridge$getMovementInput().bridge$setMoveStrafe(0.0F);
         bridge5extension_52.bridge$getMovementInput().bridge$setSneak(false);
         bridge5extension_52.bridge$getMovementInput().bridge$setJump(false);
      }

      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (!this.isReloading()) {
         this.field48 = true;
         DriverViewportLegacy.method50().method16(DriverRouteRegistry.field12);
         DriverViewportLegacy.method50().method75().add(() -> this.mc.bridge$schedule(() -> {
            if (itemcounter6extension3 != null) {
               itemcounter6extension3.bridge$disconnect();
               this.mc.bridge$clearLevel();
            } else {
               Ref.method4().method40().method85().method16();
            }

            this.method13();
            this.mc.bridge$getGuiIngame().bridge$getChatGUI().bridge$clearChatMessages();
            IntegerOption lightingextension4222x = Ref.method4().method41().method7().method29();
            lightingextension4222x.method1((Integer)lightingextension4222x.get());
            this.field26.method14();
            DriverViewportLegacy.method50().method16(DriverRouteRegistry.field12);
         }));
      }
   }

   public void stop() {
      this.field48 = true;
      this.field36.method23();
      FlawlessFrames.set(false);
      this.field44.cleanup();
      this.field18.close(true);
      this.field27.method23();
      this.field33.method14();
      Ref.method3().bridge$updateFramebufferSize();
      Ref.method3().bridge$getGameSettings().bridge$unpressAllKeys();
      if (this.field52 != null) {
         UUID uuid1 = Ref.method3().bridge$getSession().bridge$getProfile().getId();
         CosmeticManager holograms122 = Client.method109().method53();
         holograms122.method13().clear();
         holograms122.method13().addAll(this.field52.method1());
         holograms122.method58().clear();
         holograms122.method58().putAll(this.field52.method2());
         holograms122.method28(this.field51, uuid1);
         holograms122.method24();
      }

      Ref.method4().method90().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
   }

   public String getFileName() {
      return this.field18.getFile().getName();
   }

   public boolean method25() {
      ReplayTimeline highlight_31 = this.field18.method37();
      return highlight_31 == null || highlight_31.method15() >= highlight_31.method3();
   }

   public boolean method26() {
      if (!this.field23) {
         return Ref.method7() != null;
      }

      ReplayTimeline highlight_31 = this.field18.method37();
      return !this.field47 && highlight_31.isPaused() && !this.field20.method6() && Ref.method7() != null;
   }

   public boolean isReloading() {
      return this.field19 == null;
   }

   public void method27() {
      for (RewindPropertyProvider guiiterator2 : this.providers) {
         guiiterator2.method1(this);
      }
   }

   public void method20(float value1, float value2) {
      this.field42 = value1;
      this.field43 = value2;
   }

   public String getId() {
      return "REWIND_HANDLERS";
   }

   @Generated
   public RewindPropertyProvider method28() {
      return this.field9;
   }

   @Generated
   public RewindPropertyProvider method29() {
      return this.field10;
   }

   @Generated
   public RewindPropertyProvider method30() {
      return this.field11;
   }

   @Generated
   public RewindPropertyProvider method34() {
      return this.field12;
   }

   @Generated
   public RewindPropertyProvider method35() {
      return this.field13;
   }

   @Generated
   public RewindPropertyProvider method36() {
      return this.field14;
   }

   @Generated
   public RewindPropertyProvider method37() {
      return this.field15;
   }

   @Generated
   public RewindPropertyProvider method38() {
      return this.field16;
   }

   @Generated
   public RewindPropertyProvider method39() {
      return this.field17;
   }

   @Generated
   public ReplayProjectManager method40() {
      return this.field18;
   }

   @Generated
   public ReplayClock method41() {
      return this.field20;
   }

   @Generated
   public ValueHolder<ReplayContext> method42() {
      return this.field21;
   }

   @Generated
   public EntityOptionOverrides method43() {
      return this.field22;
   }

   @Generated
   public void method34(boolean flag1) {
      this.field23 = flag1;
   }

   @Generated
   public boolean method44() {
      return this.field23;
   }

   @Generated
   public CameraUpdateHandler method45() {
      return this.field24;
   }

   @Generated
   public ScreenRenderHandler method46() {
      return this.field25;
   }

   @Generated
   public ResourcePackHandler method47() {
      return this.field26;
   }

   @Generated
   public ExportTargetHandler method48() {
      return this.field27;
   }

   @Generated
   public ReplayDriverHandler method49() {
      return this.field28;
   }

   @Generated
   public SelectionHighlightHandler method50() {
      return this.field29;
   }

   @Generated
   public ZoomKeyHandler method51() {
      return this.field30;
   }

   @Generated
   public WorldRenderHandler method52() {
      return this.field31;
   }

   @Generated
   public HudVisibilityHandler method53() {
      return this.field32;
   }

   @Generated
   public ReplaySoundHandler method54() {
      return this.field33;
   }

   @Generated
   public EntityOverrideRenderer method55() {
      return this.field34;
   }

   @Generated
   public ReplayKeybindHandler method56() {
      return this.field35;
   }

   @Generated
   public RewindRenderQueue method57() {
      return this.field36;
   }

   @Generated
   public float method58() {
      return this.field42;
   }

   @Generated
   public float method59() {
      return this.field43;
   }

   @Generated
   public RewindAudioManager method60() {
      return this.field44;
   }

   @Generated
   public void method52(long number1) {
      this.field45 = number1;
   }

   @Generated
   public long method61() {
      return this.field45;
   }

   @Generated
   public void method54(boolean flag1) {
      this.field46 = flag1;
   }

   @Generated
   public boolean method62() {
      return this.field46;
   }

   @Generated
   public void method56(boolean flag1) {
      this.field47 = flag1;
   }

   @Generated
   public boolean method63() {
      return this.field47;
   }

   @Generated
   public boolean method64() {
      return this.field49;
   }

   @Generated
   public long method65() {
      return this.field53;
   }
}
