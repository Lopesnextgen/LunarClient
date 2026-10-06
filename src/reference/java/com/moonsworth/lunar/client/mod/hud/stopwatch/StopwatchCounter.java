package com.moonsworth.lunar.client.mod.hud.stopwatch;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.config.override.OverrideSource;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
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
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.TextOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.text.TimeFormatting.TimeFormat;
import java.util.List;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StopwatchCounter extends AbstractFeature {
   protected final TextOption stopwatchName = (TextOption)((Data)OptionFactory.method12("stopwatchName")
         .method2("New Stopwatch"))
      .method31();
   protected final ToggleOption useCustomFormat = (ToggleOption)OptionFactory.method7("useCustomFormat").method31();
   protected final EnumOption<TimeFormat> timeDisplayOption = (EnumOption<TimeFormat>)OptionFactory.method10("timeDisplayOption", TimeFormat.DEFAULT)
      .method31();
   protected final TextOption customFormat = (TextOption)((Data)OptionFactory.method12("customFormat").method2("HH:mm:ss"))
      .method3(256)
      .method31();
   private final ModifierKeybindOption stopwatchKeybind = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
            "stopwatchKeybind"
         )
         .method18(this))
      .method31();
   protected final ToggleOption resetEveryStart = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("resetEveryStart").method4(true))
      .method31();
   protected final boolean userCreated;
   private boolean running;
   private long startTime = -1L;
   private long elapsedTime = 0L;
   private boolean hideWhenStopped = false;

   protected StopwatchCounter(Stopwatch stopwatch1, boolean flag2, BooleanSupplier booleansupplier3, boolean flag4) {
      super(flag2);
      this.userCreated = flag4;
      final byte number5 = 56;
      final byte number6 = 18;
      this.method12(ModTraits.field16, ChildModBinding.method5(booleansupplier3, stopwatch1));
      this.method12(ModTraits.field17, ModCategories.method2(SettingsPage.STOPWATCHES));
      this.method12(ModTraits.field1, new TypedHudRenderer<Object>(0.0F, 0.0F, HudAnchor.TOP_RIGHT) {
         public HudSize method15() {
            return HudSize.method1(10, number6, 22, 44, number5, 120);
         }

         public List<String> method2(boolean flag1) {
            return StopwatchCounter.this.getDisplayLines(flag1);
         }

         protected boolean method20() {
            return false;
         }
      });
      this.stopwatchKeybind.method3(() -> {
         SettingIntercept alert21x = (SettingIntercept)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
         if (alert21x == null || !alert21x.method3().isPresent()) {
            ModEnabledState framework32x = (ModEnabledState)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field6);
            if (framework32x == null || framework32x.isEnabled()) {
               if (this.running) {
                  this.stop();
               } else {
                  this.method15();
               }
            }
         }
      });
   }

   public void method3(boolean flag1) {
      if (!flag1) {
         this.reset();
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
               this.method14();
               ((Stopwatch)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1()).method8(this);
            })});
         }

         lightingextension231.method1(
            "stopwatchSetup", arg1x -> arg1x.method9(new ClientOption[]{this.stopwatchName, this.stopwatchKeybind, this.resetEveryStart})
         );
         lightingextension231.method1("format", arg1x -> {
            arg1x.method9(new ClientOption[]{this.useCustomFormat});
            arg1x.method9(new ClientOption[]{this.timeDisplayOption}).method3(this.useCustomFormat::get);
            arg1x.method9(new ClientOption[]{this.customFormat}).method3(() -> !(Boolean)this.useCustomFormat.get());
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
      data1.method4(this.stopwatchName::get);
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
      if (!this.running && this.hideWhenStopped && !flag1) {
         return null;
      }

      long number2;
      if (this.running) {
         number2 = this.elapsedTime + Ref.method3().bridge$getSystemTime() - this.startTime;
      } else {
         number2 = this.elapsedTime;
      }

      String text4 = this.format(number2);
      return flag1 ? List.of(text4, (String)this.stopwatchName.get()) : List.of(text4);
   }

   public void method14() {
      this.stopwatchKeybind.remove();
      KeybindOptionListener.method2(this.stopwatchKeybind);
   }

   public void applyRemoteSettings(String text1, boolean flag2, @Nullable String text3, boolean flag4, boolean flag5) {
      this.stopwatchName.method10(text1);
      this.resetEveryStart.method10(flag2);
      if (text3 != null && !text3.isEmpty()) {
         this.useCustomFormat.method10(true);
         this.customFormat.method10(text3);
      }

      this.hideWhenStopped = flag5;
      if (flag4) {
         SettingIntercept alert26 = (SettingIntercept)this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
         if (alert26 != null) {
            alert26.method1(this, OverrideSource.SERVER, true);
         }

         Framework7Extension framework7extension7 = ((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         this.method12(ModTraits.field16, ChildModBinding.method4(false, framework7extension7));
      }
   }

   public void method15() {
      this.running = true;
      this.startTime = Ref.method3().bridge$getSystemTime();
      if ((Boolean)this.resetEveryStart.get()) {
         this.elapsedTime = 0L;
      }
   }

   public void stop() {
      this.running = false;
      this.elapsedTime = this.elapsedTime + (Ref.method3().bridge$getSystemTime() - this.startTime);
   }

   public void reset() {
      this.running = false;
      this.startTime = -1L;
      this.elapsedTime = 0L;
   }

   protected static StopwatchCounter method12(@NotNull Stopwatch stopwatch0, String text1) {
      return method14(stopwatch0, true, true, true, text1);
   }

   public static StopwatchCounter method13(@NotNull Stopwatch stopwatch0, String text1) {
      return method14(stopwatch0, true, true, true, "STOPWATCH_" + text1);
   }

   public static StopwatchCounter method14(@NotNull Stopwatch stopwatch0, boolean flag1, boolean flag2, final boolean flag3, @NotNull final String text4) {
      return new StopwatchCounter(stopwatch0, flag1, null, flag2) {
         @Override
         public String getId() {
            return text4;
         }

         @Override
         protected boolean method13() {
            return flag3;
         }
      };
   }

   @Generated
   public ModifierKeybindOption getStopwatchKeybind() {
      return this.stopwatchKeybind;
   }
}
