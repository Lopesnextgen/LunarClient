package com.moonsworth.lunar.client.guiRewindhandlers;

import com.lunarclient.common.v1.ServerRichStatus;
import com.lunarclient.common.v1.ServerRichStatus.Builder;
import com.lunarclient.common.v1.ServerRichStatus.Source;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.HighlightBase4;
import com.moonsworth.lunar.client.framework.listener.TrackedValue;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy;
import com.moonsworth.lunar.client.event.mixin.command.EventCommandLegacy;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.event.mixin.gui.LocationChangeEvent;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.listener.DynamicListener;

public class GuiRewindhandlersHandler23 extends DynamicListener {
   public static final GuiRewindhandlersHandler23 field7 = DynamicListener.method4(GuiRewindhandlersHandler23.class);
   private static final int field8 = 10;
   private static final int field9 = 4;
   private final TrackedValue<Rewindhandlers2> field10 = TrackedValue.method1(
      this, new Rewindhandlers2(Rewindhandlers.field1, Gui2Extension3.NONE)
   );
   private long field11;
   private long field12;
   private int field13 = 0;
   private int field14 = 0;
   private int field15 = 50;

   public GuiRewindhandlersHandler23() {
      this.handle(ServerJoinEvent.class, this::method2);
      this.handle(EventCommandLegacy.class, this::method3);
      this.handle(EventClientTick.class, this::method4);
      this.handle(EventChatMessageLegacy.Data3.class, var1 -> this.method5(var1.method1(), null));
      this.handle(EventChatMessageLegacy.Data.class, var1 -> this.method5(var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC(), var1));
      this.handle(LocationChangeEvent.class, this::method6);
   }

   private void method5() {
      if (this.field13 > 0 && this.field13 < 4) {
         this.field13 = 4;
      }
   }

   @Override
   protected boolean isEnabled() {
      return Highlight3Iterator.method8(KeystrokesType.HYPIXEL);
   }

   private void method2(ServerJoinEvent var1) {
      this.onEnable();
   }

   @Override
   protected void onEnable() {
      if (!this.field10.get().method2()) {
         Rewindhandlers2 var1 = this.field10.get();
         Rewindhandlers2 var2 = new Rewindhandlers2(Rewindhandlers.field1, Gui2Extension3.NONE);
         this.field10.set(var2);
         ClientEventBus.method29().method12(LocationChangeEvent.class, () -> new LocationChangeEvent(var1, var2));
      }

      this.field14 = 3;
      this.field13 = Math.max(this.field15, 20);
   }

   @Override
   protected void onDisable() {
      if (!this.field10.get().method2()) {
         Rewindhandlers2 var1 = this.field10.get();
         Rewindhandlers2 var2 = new Rewindhandlers2(Rewindhandlers.field1, Gui2Extension3.NONE);
         this.field10.set(var2);
         ClientEventBus.method29().method12(LocationChangeEvent.class, () -> new LocationChangeEvent(var1, var2));
      }
   }

   private void method3(EventCommandLegacy var1) {
      if (var1.method1("/locraw")) {
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

   private void method4(EventClientTick var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         if (this.field15 > 0) {
            this.field15--;
         }

         if (this.field13 > 0 && --this.field13 == 0) {
            var2.bridge$sendCommand("/locraw");
            this.field12 = -1L;
            this.field11 = System.currentTimeMillis();
            if (this.field14 > 0) {
               this.field13 = 10 * (4 - this.field14);
               this.field14--;
            }
         }
      }
   }

   private void method5(String var1, @Nullable EventChatMessageLegacy var2) {
      if (var1 != null) {
         long var3 = System.currentTimeMillis();
         if (var3 - this.field11 < 1000L && var1.equalsIgnoreCase("You are sending commands too fast! Please slow down.") && var2 != null) {
            var2.setCancelled(true);
         }

         if (var1.startsWith("{\"")) {
            try {
               Rewindhandlers var5 = (Rewindhandlers)ThreadModuleDump48.field22.fromJson(var1, Rewindhandlers.class);
               if ((this.field12 < 0L || var3 - this.field12 > 5000L) && var2 != null) {
                  var2.setCancelled(true);
               }

               if (var5.field2 != null && var5.server != null) {
                  this.field13 = 0;
                  this.field14 = 0;
               } else {
                  if (this.field10.get().method2()) {
                     return;
                  }

                  var5 = Rewindhandlers.field1;
               }

               Gui2Extension3 var6;
               if (var5.field2.equals("SKYBLOCK")) {
                  Gui2Extension3 var7 = Gui2Extension3.getByMapValue(var5.field3);
                  if (var7 == Gui2Extension3.NONE) {
                     var6 = Gui2Extension3.UNKNOWN;
                  } else {
                     var6 = var7;
                  }
               } else {
                  var6 = Gui2Extension3.NONE;
               }

               Rewindhandlers2 var9 = new Rewindhandlers2(var5, var6);
               this.method8(var9);
            } catch (Exception var8) {
            }
         }
      }
   }

   private void method6(LocationChangeEvent var1) {
      Rewindhandlers2 var2 = var1.method2();
      if (!var2.method2()) {
         ThreadModuleDump63.method5().ifPresent(var1x -> {
            Builder var2x = ServerRichStatus.newBuilder().setSource(Source.SOURCE_LUNAR_CLIENT_PARSED).setSubServer(var2.field1).setGameName(var2.field2);
            if (var2.field3 != null) {
               var2x.setGameVariantName(var2.field3);
            }

            if (var2.field4 != null) {
               var2x.setMapName(var2.field4);
            }

            var1x.method16(var2x.build());
         });
      }
   }

   public Rewindhandlers2 method7() {
      return this.field10.get();
   }

   public void method8(Rewindhandlers2 var1) {
      Rewindhandlers2 var2 = this.field10.get();
      if (var1.field6 != Gui2Extension3.NONE && var2.field6 == Gui2Extension3.NONE) {
         ClientEventBus.method29().method12(HighlightBase4.Data2.class, HighlightBase4.Data2::new);
      } else if (var1.field6 == Gui2Extension3.NONE && var2.field6 != Gui2Extension3.NONE) {
         ClientEventBus.method29().method12(HighlightBase4.Data.class, HighlightBase4.Data::new);
      }

      this.field10.set(var1);
      ThreadModuleDump63.method4().method40().method82().method14().method3("locationId", var1.field6.name());
      ClientEventBus.method29().method12(LocationChangeEvent.class, () -> new LocationChangeEvent(var2, var1));
   }

   public Gui2Extension3 method9() {
      return this.method7().field6;
   }
}
