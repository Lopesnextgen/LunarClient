package com.moonsworth.lunar.client.network.apollo;

import com.google.protobuf.Message;
import com.lunarclient.apollo.network.NetworkTypes;
import com.lunarclient.apollo.stopwatch.v1.AddStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.AddTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.RemoveStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.RemoveTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetStopwatchesMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.ResetTimersMessage;
import com.lunarclient.apollo.stopwatch.v1.StartStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StartTimerMessage;
import com.lunarclient.apollo.stopwatch.v1.StopStopwatchMessage;
import com.lunarclient.apollo.stopwatch.v1.StopTimerMessage;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.mod.hud.stopwatch.Stopwatch;
import com.moonsworth.lunar.client.mod.hud.stopwatch.StopwatchCounter;
import com.moonsworth.lunar.client.mod.hud.stopwatch.StopwatchTimer;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers3;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.Client;

public class StopwatchApolloHandler extends ApolloModuleHandler {
   private static final String field4 = "STOPWATCH_APOLLO";
   private final Map<String, StopwatchCounter> field5 = new LinkedHashMap<>();
   private final Map<String, StopwatchTimer> field6 = new LinkedHashMap<>();

   public StopwatchApolloHandler() {
      super("stopwatch", "Stopwatch");
   }

   @Override
   public Set<Class<? extends Message>> method2() {
      return Set.of(
         AddStopwatchMessage.class,
         RemoveStopwatchMessage.class,
         StartStopwatchMessage.class,
         StopStopwatchMessage.class,
         ResetStopwatchMessage.class,
         ResetStopwatchesMessage.class,
         AddTimerMessage.class,
         RemoveTimerMessage.class,
         StartTimerMessage.class,
         StopTimerMessage.class,
         ResetTimerMessage.class,
         ResetTimersMessage.class
      );
   }

   @Override
   protected void onEnable() {
      Client.method109().method40().method37().method18(this::method21);
   }

   @Override
   protected void onDisable() {
      this.method6();
      this.method16();
      Client.method109().method40().method37().method18(null);
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
      var1.unpack(AddStopwatchMessage.class)
         .ifPresent(
            var1x -> this.method3(
               var1x.getId(),
               var1x.getName(),
               var1x.getResetOnStart(),
               var1x.getDisplayFormat().isEmpty() ? null : var1x.getDisplayFormat(),
               var1x.hasTextColor() ? var1x.getTextColor().getColor() : null,
               var1x.getPreventModification(),
               var1x.getHideWhenStopped(),
               var1x.hasHudPosition() ? var1x.getHudPosition().getX() : null,
               var1x.hasHudPosition() ? var1x.getHudPosition().getY() : null
            )
         );
      var1.unpack(RemoveStopwatchMessage.class).ifPresent(var1x -> this.method4(var1x.getId()));
      var1.unpack(StartStopwatchMessage.class).ifPresent(var1x -> {
         String var2 = var1x.getId();
         if (var2.isEmpty()) {
            this.method10();
         } else {
            this.method7(var2);
         }
      });
      var1.unpack(StopStopwatchMessage.class).ifPresent(var1x -> {
         String var2 = var1x.getId();
         if (var2.isEmpty()) {
            this.method11();
         } else {
            this.method8(var2);
         }
      });
      var1.unpack(ResetStopwatchMessage.class).ifPresent(var1x -> {
         String var2 = var1x.getId();
         if (var2.isEmpty()) {
            this.method12();
         } else {
            this.method9(var2);
         }
      });
      var1.unpack(ResetStopwatchesMessage.class).ifPresent(var1x -> this.method6());
      var1.unpack(AddTimerMessage.class)
         .ifPresent(
            var1x -> this.method14(
               var1x.getId(),
               var1x.getName(),
               NetworkTypes.fromProtobuf(var1x.getDuration()),
               var1x.getLoop(),
               var1x.getDisplayFormat().isEmpty() ? null : var1x.getDisplayFormat(),
               this.method22(var1x.getTitleTextAdventureJsonLines()),
               var1x.getInGameNotification(),
               var1x.hasTextColor() ? var1x.getTextColor().getColor() : null,
               var1x.getPreventModification(),
               var1x.getHideWhenStopped(),
               var1x.hasHudPosition() ? var1x.getHudPosition().getX() : null,
               var1x.hasHudPosition() ? var1x.getHudPosition().getY() : null
            )
         );
      var1.unpack(RemoveTimerMessage.class).ifPresent(var1x -> this.method15(var1x.getId()));
      var1.unpack(StartTimerMessage.class).ifPresent(var1x -> this.method18(var1x.getId()));
      var1.unpack(StopTimerMessage.class).ifPresent(var1x -> this.method19(var1x.getId()));
      var1.unpack(ResetTimerMessage.class).ifPresent(var1x -> this.method20(var1x.getId()));
      var1.unpack(ResetTimersMessage.class).ifPresent(var1x -> this.method16());
   }

   private void method3(
      @NotNull String var1,
      @NotNull String var2,
      boolean var3,
      @Nullable String var4,
      @Nullable Integer var5,
      boolean var6,
      boolean var7,
      @Nullable Float var8,
      @Nullable Float var9
   ) {
      if (!this.field5.containsKey(var1)) {
         Stopwatch var10 = Client.method109().method40().method37();
         StopwatchCounter var11 = StopwatchCounter.method14(var10, true, false, true, "APOLLO_STOPWATCH_" + var1);
         var11.applyRemoteSettings(var2, var3, var4, var6, var7);
         method23(var11, var5);
         method24(var11, var8, var9);
         this.field5.put(var1, var11);
         var10.addStopwatch(var11);
      }
   }

   private void method4(String var1) {
      StopwatchCounter var2 = this.field5.remove(var1);
      if (var2 != null) {
         Client.method109().method40().method37().method8(var2);
      }
   }

   private void method6() {
      new ArrayList<>(this.field5.keySet()).forEach(this::method4);
   }

   private void method6(String var1, Consumer<StopwatchCounter> var2) {
      StopwatchCounter var3 = this.field5.get(var1);
      if (var3 != null) {
         var2.accept(var3);
      }
   }

   private void method7(String var1) {
      this.method6(var1, StopwatchCounter::method15);
   }

   private void method8(String var1) {
      this.method6(var1, StopwatchCounter::stop);
   }

   private void method9(String var1) {
      this.method6(var1, StopwatchCounter::reset);
   }

   private void method10() {
      this.method13();
      this.method7("STOPWATCH_APOLLO");
   }

   private void method11() {
      this.method13();
      this.method8("STOPWATCH_APOLLO");
   }

   private void method12() {
      this.method13();
      this.method9("STOPWATCH_APOLLO");
   }

   private void method13() {
      this.method3("STOPWATCH_APOLLO", "Server Stopwatch", true, null, null, false, false, null, null);
   }

   private void method14(
      @NotNull String var1,
      @NotNull String var2,
      @NotNull Duration var3,
      boolean var4,
      @Nullable String var5,
      @Nullable String var6,
      boolean var7,
      @Nullable Integer var8,
      boolean var9,
      boolean var10,
      @Nullable Float var11,
      @Nullable Float var12
   ) {
      if (!this.field6.containsKey(var1)) {
         long var13 = var3.getSeconds();
         int var15 = (int)Math.min(var13 / 3600L, 23L);
         int var16 = (int)Math.min(var13 % 3600L / 60L, 59L);
         int var17 = (int)(var13 % 60L);
         Stopwatch var18 = Client.method109().method40().method37();
         StopwatchTimer var19 = StopwatchTimer.method22(var18, true, false, true, "APOLLO_TIMER_" + var1);
         var19.applyRemoteSettings(var2, var15, var16, var17, var4, var5, var6, var7, var9, var10);
         method23(var19, var8);
         method24(var19, var11, var12);
         this.field6.put(var1, var19);
         var18.method5(var19);
      }
   }

   private void method15(String var1) {
      StopwatchTimer var2 = this.field6.remove(var1);
      if (var2 != null) {
         Client.method109().method40().method37().method6(var2);
      }
   }

   private void method16() {
      new ArrayList<>(this.field6.keySet()).forEach(this::method15);
   }

   private void method17(String var1, Consumer<StopwatchTimer> var2) {
      StopwatchTimer var3 = this.field6.get(var1);
      if (var3 != null) {
         var2.accept(var3);
      }
   }

   private void method18(String var1) {
      this.method17(var1, StopwatchTimer::startOrResume);
   }

   private void method19(String var1) {
      this.method17(var1, StopwatchTimer::stop);
   }

   private void method20(String var1) {
      this.method17(var1, StopwatchTimer::reset);
   }

   private void method21() {
      Stopwatch var1 = Client.method109().method40().method37();
      this.field5.values().forEach(var1::addStopwatch);
      this.field6.values().forEach(var1::method5);
   }

   @Nullable
   private String method22(String var1) {
      if (var1.isEmpty()) {
         return null;
      }

      Component var2 = Rewindhandlers3.method4(var1);
      return var2 == null ? null : AdventureTextBridge.asLegacyString(var2);
   }

   private static void method23(AbstractFeature var0, @Nullable Integer var1) {
      if (var1 != null) {
         MixinCore9Extension var2 = (MixinCore9Extension)var0.method7(Framework.field1);
         if (var2 instanceof TypedHudRenderer var3) {
            var3.method35().method1(var1);
         }
      }
   }

   private static void method24(AbstractFeature var0, @Nullable Float var1, @Nullable Float var2) {
      if (var1 != null || var2 != null) {
         MixinCore9Extension var3 = (MixinCore9Extension)var0.method7(Framework.field1);
         if (var3 instanceof HudElementBase var4) {
            var4.method17(var1 != null ? var1 : var4.getX(), var2 != null ? var2 : var4.getY());
         }
      }
   }
}
