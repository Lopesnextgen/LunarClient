package com.moonsworth.lunar.client.mod.hud.stopwatch;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.config.override.OverrideSource;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.listener.KeybindOptionListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.ui.notification.DesktopNotifier;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.TimeFormatting.TimeFormat;
import java.util.List;
import java.util.function.BooleanSupplier;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StopwatchTimer extends AbstractFeature {
   protected final TextOption timerName = (TextOption)((Data)OptionFactory.method12("timerName").method2("New Timer"))
      .method31();
   protected final ModifierKeybindOption startStopTimerKey = (ModifierKeybindOption)OptionFactory.method18("startStopTimerKey").method31();
   protected final ModifierKeybindOption pauseTimerKey = (ModifierKeybindOption)OptionFactory.method18("pauseTimerKey").method31();
   protected final IntegerOption hours = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "hours"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 23))
      .method31();
   protected final IntegerOption minutes = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "minutes"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 59))
      .method31();
   protected final IntegerOption seconds = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "seconds"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 59))
      .method31();
   protected final ToggleOption loopOnEnd = (ToggleOption)OptionFactory.method7("loopOnEnd").method31();
   protected final ToggleOption useCustomFormat = (ToggleOption)OptionFactory.method7("useCustomFormat").method31();
   protected final EnumOption<TimeFormat> timeDisplayOption = (EnumOption<TimeFormat>)OptionFactory.method10("timeDisplayOption", TimeFormat.DEFAULT)
      .method31();
   protected final TextOption customFormat = (TextOption)((Data)OptionFactory.method12("customFormat").method2("HH:mm:ss"))
      .method31();
   protected final ToggleOption showTitleAction = (ToggleOption)OptionFactory.method7("showTitleAction").method31();
   protected final TextOption titleText = (TextOption)OptionFactory.method12("titleText").method31();
   protected final ToggleOption desktopNotification = (ToggleOption)OptionFactory.method7("desktopNotification").method31();
   protected final ToggleOption ingameNotification = (ToggleOption)OptionFactory.method7("ingameNotification").method31();
   protected final ToggleOption playSound = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("playSound").OOOIROIIOCOOHICRIRHHHRROHHHHIO(false))
         .RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   protected final boolean userCreated;
   private long endTime = 0L;
   private boolean paused = false;
   private long pauseStartTime = 0L;
   private long pausedRemaining = 0L;
   private boolean hideWhenStopped = true;

   protected StopwatchTimer(Stopwatch stopwatch1, boolean flag2, BooleanSupplier booleansupplier3, boolean flag4) {
      super(flag2);
      this.userCreated = flag4;
      int number5 = stopwatch1.timers.size() % 100;
      final byte number6 = 56;
      final byte number7 = 18;
      float value8 = -10.0F - number5 / 10 * number6;
      float value9 = 30.0F + number5 % 10 * number7;
      this.method2(ModTraits.field16, ChildModBinding.method5(booleansupplier3, stopwatch1));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.TIMERS));
      this.method2(ModTraits.field1, new TypedHudRenderer<Object>(value8, value9, HudAnchor.TOP_RIGHT) {
         public HudSize method15() {
            return HudSize.method1(10, number7, 22, 44, number6, 120);
         }

         public List<String> method2(boolean flag1) {
            return StopwatchTimer.this.getDisplayLines(flag1);
         }

         protected boolean method20() {
            return false;
         }
      });
      this.startStopTimerKey.method3(() -> {
         SettingIntercept alert21x = (SettingIntercept)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
         if ((alert21x == null || !alert21x.method3().isPresent()) && this.isEnabled()) {
            if (!this.isRunning()) {
               this.start();
            } else {
               this.method15();
            }
         }
      });
      this.pauseTimerKey.method3(() -> {
         SettingIntercept alert21x = (SettingIntercept)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
         if (alert21x == null || !alert21x.method3().isPresent()) {
            if (this.isRunning()) {
               this.method21();
            }
         }
      });
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.method15();
      }
   }

   public String getId() {
      throw new IllegalStateException("StopWatchTimerChildMod must be created using StopWatchTimerChildMod.create()!");
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      SettingIntercept alert22 = (SettingIntercept)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
      if (alert22 == null || !alert22.method3().isPresent()) {
         if (this.userCreated) {
            lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("remove").method4(() -> {
               this.dispose();
               ((Stopwatch)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1()).method6(this);
            })});
         }

         lightingextension231.method9(new OptionProvider[]{OptionFactory.method14("stopTimer").method4(this::method15)});
         lightingextension231.method1(
            "timerSetup",
            arg1x -> arg1x.method9(
               new ClientOption[]{this.timerName, this.startStopTimerKey, this.pauseTimerKey, this.hours, this.minutes, this.seconds, this.loopOnEnd}
            )
         );
         lightingextension231.method1("format", arg1x -> {
            arg1x.method9(new ClientOption[]{this.useCustomFormat});
            arg1x.method9(new ClientOption[]{this.timeDisplayOption}).method3(this.useCustomFormat::get);
            arg1x.method9(new ClientOption[]{this.customFormat}).method3(() -> !(Boolean)this.useCustomFormat.get());
         });
         lightingextension231.method1("actionSetup", arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.showTitleAction, arg1xx -> arg1xx.method9(new ClientOption[]{this.titleText}));
            arg1x.method9(new ClientOption[]{this.playSound});
            arg1x.method9(new ClientOption[]{this.ingameNotification, this.desktopNotification}).method3(() -> !DesktopNotifier.method1());
         });
      }
   }

   public void method1(JsonObject json1) {
      if (this.userCreated) {
         super.method1(json1);
      }
   }

   protected ModDetails method20() {
      com.moonsworth.lunar.client.framework.mod.ModDetails.Data data1 = ModDetails.method7();
      data1.method4(this.timerName::get);
      if (this.method13()) {
         data1.method8();
      }

      return data1.method11(this);
   }

   private String format(long number1) {
      if (number1 < 0L) {
         number1 = 0L;
      }

      if ((Boolean)this.useCustomFormat.get()) {
         String text3 = (String)this.customFormat.get();

         try {
            return DurationFormatUtils.formatDuration(number1, text3);
         } catch (Exception exception5) {
            return "Format Error";
         }
      } else {
         return ((TimeFormat)this.timeDisplayOption.get()).format(number1);
      }
   }

   protected boolean method13() {
      return false;
   }

   private List<String> getDisplayLines(boolean flag1) {
      if (this.isRunning()) {
         return List.of(this.format(this.getRemainingMillis()));
      }

      if (flag1) {
         long number4 = this.pausedRemaining > 0L ? this.pausedRemaining : this.getConfiguredDurationMillis();
         return List.of(this.format(number4), (String)this.timerName.get());
      }

      if (this.hideWhenStopped) {
         return null;
      }

      long number2 = this.pausedRemaining > 0L ? this.pausedRemaining : this.getConfiguredDurationMillis();
      return List.of(this.format(number2));
   }

   public void applyRemoteSettings(
      String text1, int number2, int number3, int number4, boolean flag5, @Nullable String text6, @Nullable String text7, boolean flag8, boolean flag9, boolean flag10
   ) {
      this.timerName.method10(text1);
      this.hours.method1(number2);
      this.minutes.method1(number3);
      this.seconds.method1(number4);
      this.loopOnEnd.method10(flag5);
      if (text6 != null && !text6.isEmpty()) {
         this.useCustomFormat.method10(true);
         this.customFormat.method10(text6);
      }

      if (text7 != null && !text7.isEmpty()) {
         this.showTitleAction.method10(true);
         this.titleText.method10(text7);
      }

      this.ingameNotification.method10(flag8);
      this.hideWhenStopped = flag10;
      if (flag9) {
         SettingIntercept alert211 = (SettingIntercept)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
         if (alert211 != null) {
            alert211.method1(this, OverrideSource.SERVER, true);
         }

         Framework7Extension framework7extension12 = ((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         this.method2(ModTraits.field16, ChildModBinding.method4(false, framework7extension12));
      }
   }

   public void start() {
      this.endTime = Ref.method3().bridge$getSystemTime() + this.getConfiguredDurationMillis();
      this.pausedRemaining = 0L;
      this.paused = false;
   }

   public void method15() {
      this.endTime = 0L;
      this.pausedRemaining = 0L;
   }

   public void startOrResume() {
      long number1 = this.pausedRemaining > 0L ? this.pausedRemaining : this.getConfiguredDurationMillis();
      this.endTime = Ref.method3().bridge$getSystemTime() + number1;
      this.pausedRemaining = 0L;
      this.paused = false;
   }

   public void stop() {
      if (this.isRunning()) {
         this.pausedRemaining = this.getRemainingMillis();
      }

      this.endTime = 0L;
      this.paused = false;
      this.pauseStartTime = 0L;
   }

   public void reset() {
      this.endTime = 0L;
      this.pausedRemaining = 0L;
      this.paused = false;
      this.pauseStartTime = 0L;
   }

   public void method21() {
      if (this.paused) {
         this.method22();
      } else {
         this.pause();
      }
   }

   public void pause() {
      this.paused = true;
      this.pauseStartTime = Ref.method3().bridge$getSystemTime();
   }

   public void method22() {
      long number1 = Ref.method3().bridge$getSystemTime() - this.pauseStartTime;
      this.endTime += number1;
      this.paused = false;
      this.pauseStartTime = 0L;
   }

   public long getRemainingMillis() {
      if (!this.isRunning()) {
         return 0L;
      }

      long number1;
      if (this.paused) {
         number1 = this.endTime - this.pauseStartTime;
      } else {
         number1 = this.endTime - Ref.method3().bridge$getSystemTime();
      }

      return Math.max(number1, 0L);
   }

   public boolean isRunning() {
      return this.endTime != 0L;
   }

   public boolean isFinished() {
      return this.isRunning() && !this.paused && this.endTime <= Ref.method3().bridge$getSystemTime();
   }

   public void dispose() {
      this.startStopTimerKey.remove();
      this.pauseTimerKey.remove();
      KeybindOptionListener.method2(this.startStopTimerKey);
      KeybindOptionListener.method2(this.pauseTimerKey);
   }

   public long getConfiguredDurationMillis() {
      long number1 = ((Integer)this.hours.get()).intValue() * 3600L + ((Integer)this.minutes.get()).intValue() * 60L + ((Integer)this.seconds.get()).intValue();
      return Math.max(number1, 1L) * 1000L;
   }

   protected static StopwatchTimer method20(@NotNull Stopwatch stopwatch0, String text1) {
      return method22(stopwatch0, true, true, true, text1);
   }

   public static StopwatchTimer method21(@NotNull Stopwatch stopwatch0, String text1) {
      return method22(stopwatch0, true, true, true, "STOPWATCH_TIMER_" + text1);
   }

   public static StopwatchTimer method22(@NotNull Stopwatch stopwatch0, boolean flag1, boolean flag2, final boolean flag3, @NotNull final String text4) {
      StopwatchTimer timerchildmod5 = new StopwatchTimer(stopwatch0, flag1, null, flag2) {
         @Override
         public String getId() {
            return text4;
         }

         @Override
         protected boolean method13() {
            return flag3;
         }
      };
      timerchildmod5.field12.method1(1);
      return timerchildmod5;
   }
}
