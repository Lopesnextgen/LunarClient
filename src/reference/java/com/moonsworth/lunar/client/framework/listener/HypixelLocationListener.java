package com.moonsworth.lunar.client.framework.listener;

import com.lunarclient.common.v1.ServerRichStatus;
import com.lunarclient.common.v1.ServerRichStatus.Builder;
import com.lunarclient.common.v1.ServerRichStatus.Source;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.LocationEvent.Data;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.LocationEvent.LocationEnterEvent;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.framework.listener.LocrawResponse;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage;
import com.moonsworth.lunar.client.event.mixin.EventCommand;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerJoin;
import com.moonsworth.lunar.client.event.mixin.gui.EventLocationChange;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class HypixelLocationListener extends DynamicListener {
   public static final HypixelLocationListener field7 = DynamicListener.method4(HypixelLocationListener.class);
   private static final int field8 = 10;
   private static final int field9 = 4;
   private final TrackedValue<HypixelLocation> field10 = TrackedValue.method1(
      this, new HypixelLocation(LocrawResponse.field1, SkyblockIsland.NONE)
   );
   private long field11;
   private long field12;
   private int field13 = 0;
   private int field14 = 0;
   private int field15 = 50;

   public HypixelLocationListener() {
      this.handle(EventServerJoin.class, this::method2);
      this.handle(EventCommand.class, this::method3);
      this.handle(EventTick.class, this::method4);
      this.handle(EventChatMessage.EventTypedMessage.class, arg1 -> this.method5(arg1.method1(), null));
      this.handle(EventChatMessage.TypedChatMessage.class, arg1 -> this.method5(arg1.OROIIOCCOORRCRCIIHHOCCCRHICRCC(), arg1));
      this.handle(EventLocationChange.class, this::method6);
   }

   private void method5() {
      if (this.field13 > 0 && this.field13 < 4) {
         this.field13 = 4;
      }
   }

   @Override
   protected boolean isEnabled() {
      return ServerBrandWatcher.method8(KeystrokesType.HYPIXEL);
   }

   private void method2(EventServerJoin highlightimpl161) {
      this.onEnable();
   }

   @Override
   protected void onEnable() {
      if (!this.field10.get().method2()) {
         HypixelLocation rewindhandlers21 = this.field10.get();
         HypixelLocation rewindhandlers22 = new HypixelLocation(LocrawResponse.field1, SkyblockIsland.NONE);
         this.field10.set(rewindhandlers22);
         LunarEventBus.method29().method12(EventLocationChange.class, () -> new EventLocationChange(rewindhandlers21, rewindhandlers22));
      }

      this.field14 = 3;
      this.field13 = Math.max(this.field15, 20);
   }

   @Override
   protected void onDisable() {
      if (!this.field10.get().method2()) {
         HypixelLocation rewindhandlers21 = this.field10.get();
         HypixelLocation rewindhandlers22 = new HypixelLocation(LocrawResponse.field1, SkyblockIsland.NONE);
         this.field10.set(rewindhandlers22);
         LunarEventBus.method29().method12(EventLocationChange.class, () -> new EventLocationChange(rewindhandlers21, rewindhandlers22));
      }
   }

   private void method3(EventCommand highlightimpl41) {
      if (highlightimpl41.method1("/locraw")) {
         this.field12 = System.currentTimeMillis();
         if (this.field13 > 0) {
            if (this.field14 > 0) {
               this.field13 = 10 * (4 - this.field14);
               this.field14--;
            } else {
               this.field13 = 0;
            }
         }
      } else {
         this.method5();
      }
   }

   private void method4(EventTick highlightimpl21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         if (this.field15 > 0) {
            this.field15--;
         }

         if (this.field13 > 0 && --this.field13 == 0) {
            bridge5extension_52.bridge$sendCommand("/locraw");
            this.field12 = -1L;
            this.field11 = System.currentTimeMillis();
            if (this.field14 > 0) {
               this.field13 = 10 * (4 - this.field14);
               this.field14--;
            }
         }
      }
   }

   private void method5(String text1, @Nullable EventChatMessage highlightimpl2) {
      if (text1 != null) {
         long number3 = System.currentTimeMillis();
         if (number3 - this.field11 < 1000L && text1.equalsIgnoreCase("You are sending commands too fast! Please slow down.") && highlightimpl2 != null) {
            highlightimpl2.setCancelled(true);
         }

         if (text1.startsWith("{\"")) {
            try {
               LocrawResponse rewindhandlers5 = (LocrawResponse)LunarConstants.field22.fromJson(text1, LocrawResponse.class);
               if ((this.field12 < 0L || number3 - this.field12 > 5000L) && highlightimpl2 != null) {
                  highlightimpl2.setCancelled(true);
               }

               if (rewindhandlers5.field2 != null && rewindhandlers5.server != null) {
                  this.field13 = 0;
                  this.field14 = 0;
               } else {
                  if (this.field10.get().method2()) {
                     return;
                  }

                  rewindhandlers5 = LocrawResponse.field1;
               }

               SkyblockIsland gui2extension36;
               if (rewindhandlers5.field2.equals("SKYBLOCK")) {
                  SkyblockIsland gui2extension37 = SkyblockIsland.getByMapValue(rewindhandlers5.field3);
                  if (gui2extension37 == SkyblockIsland.NONE) {
                     gui2extension36 = SkyblockIsland.UNKNOWN;
                  } else {
                     gui2extension36 = gui2extension37;
                  }
               } else {
                  gui2extension36 = SkyblockIsland.NONE;
               }

               HypixelLocation rewindhandlers29 = new HypixelLocation(rewindhandlers5, gui2extension36);
               this.method8(rewindhandlers29);
            } catch (Exception exception8) {
            }
         }
      }
   }

   private void method6(EventLocationChange highlightimpl201) {
      HypixelLocation rewindhandlers22 = highlightimpl201.method2();
      if (!rewindhandlers22.method2()) {
         Ref.method5().ifPresent(arg1x -> {
            Builder builder2x = ServerRichStatus.newBuilder().setSource(Source.SOURCE_LUNAR_CLIENT_PARSED).setSubServer(rewindhandlers22.field1).setGameName(rewindhandlers22.field2);
            if (rewindhandlers22.field3 != null) {
               builder2x.setGameVariantName(rewindhandlers22.field3);
            }

            if (rewindhandlers22.field4 != null) {
               builder2x.setMapName(rewindhandlers22.field4);
            }

            arg1x.method16(builder2x.build());
         });
      }
   }

   public HypixelLocation method7() {
      return this.field10.get();
   }

   public void method8(HypixelLocation rewindhandlers21) {
      HypixelLocation rewindhandlers22 = this.field10.get();
      if (rewindhandlers21.field6 != SkyblockIsland.NONE && rewindhandlers22.field6 == SkyblockIsland.NONE) {
         LunarEventBus.method29().method12(LocationEnterEvent.class, LocationEnterEvent::new);
      } else if (rewindhandlers21.field6 == SkyblockIsland.NONE && rewindhandlers22.field6 != SkyblockIsland.NONE) {
         LunarEventBus.method29().method12(Data.class, Data::new);
      }

      this.field10.set(rewindhandlers21);
      Ref.method4().method40().method82().method14().method3("locationId", rewindhandlers21.field6.name());
      LunarEventBus.method29().method12(EventLocationChange.class, () -> new EventLocationChange(rewindhandlers22, rewindhandlers21));
   }

   public SkyblockIsland method9() {
      return this.method7().field6;
   }
}
