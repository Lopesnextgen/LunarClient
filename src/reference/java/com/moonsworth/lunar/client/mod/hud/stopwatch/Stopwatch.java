package com.moonsworth.lunar.client.mod.hud.stopwatch;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModChildren;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.PageState;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHud.Focused;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.ui.notification.DesktopNotifier;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.TrayIcon.MessageType;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import net.kyori.adventure.util.Ticks;

public class Stopwatch extends AbstractFeature {
   protected final Map<String, StopwatchTimer> timers = new LinkedHashMap<>();
   protected final Map<String, StopwatchCounter> stopwatches = new LinkedHashMap<>();
   private final Set<String> pendingTitles = new LinkedHashSet<>();
   protected boolean migrated = false;
   private int titleTicks = -1;
   private Title currentTitle = null;
   private Runnable onResetCallback;

   public Stopwatch() {
      super(false);
      this.handle(Focused.class, this::renderTitle);
      this.handle(
         EventTick.class,
         arg1 -> {
            this.method14();
            if (this.titleTicks >= 0) {
               this.titleTicks--;
            }

            if (!this.pendingTitles.isEmpty()) {
               if (this.mc.bridge$getPlayer() == null) {
                  this.pendingTitles.clear();
                  this.currentTitle = null;
                  this.titleTicks = -1;
               } else {
                  if (this.titleTicks < 0) {
                     byte number2 = 10;
                     byte number3 = 40;
                     byte number4 = 10;
                     String text5 = this.pendingTitles.iterator().next();
                     this.pendingTitles.remove(text5);
                     this.titleTicks = number2 + number3 + number4;
                     this.currentTitle = Title.title(
                        TextBridge.asAdventure(ChatFormatting.getTextWithFormattingCodesFromAmpersand(text5)),
                        Component.empty(),
                        Times.times(Ticks.duration(number2), Ticks.duration(number3), Ticks.duration(number4))
                     );
                  }
               }
            }
         }
      );
      this.method13(ModTraits.field11, PageState.method3().method2(false));
   }

   public void method3(boolean flag1) {
      for (StopwatchTimer timerchildmod3 : this.timers.values()) {
         if (!flag1) {
            timerchildmod3.method15();
         }
      }
   }

   public String getId() {
      return "STOPWATCH";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("addTimer").method4(() -> {
         String text1x = Ref.method3().bridge$getSystemTime() + "";
         StopwatchTimer timerchildmod2 = StopwatchTimer.method21(this, text1x);
         this.method5(timerchildmod2);
         LcuiScreen.method144();
      }), OptionFactory.method14("addStopwatch").method4(() -> {
         String text1x = Ref.method3().bridge$getSystemTime() + "";
         StopwatchCounter stopwatchchildmod2 = StopwatchCounter.method13(this, text1x);
         this.addStopwatch(stopwatchchildmod2);
         LcuiScreen.method144();
      })});
   }

   public void method4() {
      super.method4();
      this.method13();
      LcuiScreen.method144();
   }

   private void method13() {
      this.method13(ModTraits.field5);
      this.stopwatches.values().forEach(StopwatchCounter::method14);
      this.stopwatches.clear();
      this.timers.values().forEach(StopwatchTimer::dispose);
      this.timers.clear();
      this.pendingTitles.clear();
      this.currentTitle = null;
      this.titleTicks = -1;
      this.method5();
      if (this.onResetCallback != null) {
         this.onResetCallback.run();
      }
   }

   public void method5(StopwatchTimer timerchildmod1) {
      if (this.timers.putIfAbsent(timerchildmod1.getId(), timerchildmod1) == null) {
         ((ModChildren)this.method8(ModTraits.field5, arg0 -> ModChildren.method3())).HICCRORORCRIHCORCCIORIOROORIHR(timerchildmod1);
      }
   }

   public void removeTimer(StopwatchTimer timerchildmod1) {
      this.timers.remove(timerchildmod1.getId());
      this.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field5).ifPresent(arg2 -> {
         arg2.HRORICORIHHHRICRIRCIIOHCRIRRHI(timerchildmod1);
         if (arg2.getChildren().isEmpty()) {
            this.method13(ModTraits.field5);
         }
      });
      LcuiScreen.method144();
   }

   public void addStopwatch(StopwatchCounter stopwatchchildmod1) {
      if (this.stopwatches.putIfAbsent(stopwatchchildmod1.getId(), stopwatchchildmod1) == null) {
         ((ModChildren)this.method8(ModTraits.field5, arg0 -> ModChildren.method3())).HICCRORORCRIHCORCCIORIOROORIHR(stopwatchchildmod1);
      }
   }

   public void method8(StopwatchCounter stopwatchchildmod1) {
      this.stopwatches.remove(stopwatchchildmod1.getId());
      this.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(ModTraits.field5).ifPresent(arg2 -> {
         arg2.HRORICORIHHHRICRIRCIIOHCRIRRHI(stopwatchchildmod1);
         if (arg2.getChildren().isEmpty()) {
            this.method13(ModTraits.field5);
         }
      });
      LcuiScreen.method144();
   }

   private void method14() {
      for (StopwatchTimer timerchildmod2 : this.timers.values()) {
         if (timerchildmod2.isFinished()) {
            String text3 = this.method8("notificationDescription", new Object[]{timerchildmod2.timerName.get()});
            if ((Boolean)timerchildmod2.playSound.get()) {
               this.mc.bridge$getSoundHandler().method1(Chat.field9);
            }

            if (!timerchildmod2.desktopNotification.isHidden() && (Boolean)timerchildmod2.desktopNotification.get()) {
               CompletableFuture.runAsync(() -> DesktopNotifier.method2(this.method8("notificationTitle", new Object[0]), text3, MessageType.INFO));
            }

            if (!timerchildmod2.ingameNotification.isHidden() && (Boolean)timerchildmod2.ingameNotification.get()) {
               this.field4.method69().method6(NotificationType.INFO, this.method8("notificationTitle", new Object[0]), text3);
            }

            if ((Boolean)timerchildmod2.showTitleAction.get()) {
               String text4 = (String)timerchildmod2.titleText.get();
               if (text4 != null) {
                  text4 = text4.trim();
                  if (!text4.isEmpty()) {
                     this.pendingTitles.add(text4);
                  }
               }
            }

            timerchildmod2.method15();
            if ((Boolean)timerchildmod2.loopOnEnd.get()) {
               timerchildmod2.start();
            }
         }
      }
   }

   private void renderTitle(Focused data1) {
      if (this.currentTitle != null && this.titleTicks >= 0) {
         Ref.method4()
            .method40()
            .method35()
            .method15()
            .method2(
               data1.HHHRIHCIOHCICRCCOCIRRROHIOHRIH(),
               this.currentTitle,
               this.titleTicks,
               0.0F,
               0.0F,
               data1.RHIORIRICRIHIRIOOICCICRHCIOCCI().method12(),
               data1.RHIORIRICRIHIRIOOICCICRHCIOCCI().method13(),
               false,
               true,
               true
            );
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method2(new String[]{"timer"}).method1(new ModCategory[]{ModCategory.field4}).method11(this);
   }

   public void method1(JsonObject json1) {
      super.method1(json1);
      this.method13(json1, "timers", this.timers, arg0 -> arg0.field23);
      this.method13(json1, "stopwatches", this.stopwatches, arg0 -> arg0.field14);
   }

   public void load(JsonObject json1) {
      ConfigMigrator.method3(this, json1);
      if (!this.timers.isEmpty() || !this.stopwatches.isEmpty()) {
         this.method13();
      }

      this.method14(json1, "timers", arg1x -> StopwatchTimer.method20(this, arg1x), this::method5);
      this.method14(json1, "stopwatches", arg1x -> StopwatchCounter.method12(this, arg1x), this::addStopwatch);
      super.load(json1);
   }

   private <T extends Framework7Extension> void method13(JsonObject json1, String text2, Map<String, T> map3, Predicate<T> predicate4) {
      List list5 = map3.values().stream().filter(predicate4).toList();
      if (!list5.isEmpty()) {
         JsonArray array6 = new JsonArray();

         for (Framework7Extension framework7extension8 : list5) {
            array6.add(framework7extension8.getId());
         }

         json1.add(text2, array6);
      }
   }

   private <T extends Framework7Extension> void method14(JsonObject json1, String text2, Function<String, T> function3, Consumer<T> consumer4) {
      JsonElement element5 = json1.get(text2);
      if (element5 != null && element5.isJsonArray()) {
         for (JsonElement element8 : element5.getAsJsonArray()) {
            String text9 = element8.getAsString();
            Framework7Extension framework7extension10 = (Framework7Extension)function3.apply(text9);
            consumer4.accept(framework7extension10);
         }
      }
   }

   @Generated
   public void setMigrated(boolean flag1) {
      this.migrated = flag1;
   }

   @Generated
   public boolean isMigrated() {
      return this.migrated;
   }

   @Generated
   public void setOnReset(Runnable runnable1) {
      this.onResetCallback = runnable1;
   }
}
