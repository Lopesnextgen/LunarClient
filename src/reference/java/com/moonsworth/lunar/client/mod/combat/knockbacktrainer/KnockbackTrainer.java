package com.moonsworth.lunar.client.mod.combat.knockbacktrainer;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.bridge.KeyBindingBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.knockbacktrainer.KnockbackEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldLoad;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.collection.TickQueue;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Optional;
import lombok.Generated;

public class KnockbackTrainer extends AbstractFeature {
   private static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "sound/voice_up.ogg");
   private final IntegerOption field9 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "targetTicks"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(0))
         .method7(0, 6))
      .method31();
   private final IntegerOption field10 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "windowTicks"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(5))
         .method7(0, 10))
      .method31();
   private final ToggleOption field11 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("fallingHitSound").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final FloatOption field12 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                     "fallingHitSoundVolume"
                  )
                  .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
               .method8(0.0F, 1.0F))
            .method6(2))
         .method17(() -> !(Boolean)this.field11.get()))
      .method31();
   private final KnockbackJumpTiming field13 = new KnockbackJumpTiming(this);
   private final KnockbackGroundGraph field14 = new KnockbackGroundGraph(this);
   private final KnockbackStats field15 = new KnockbackStats(this);
   private final KnockbackTrainer.Data field16 = new KnockbackTrainer.Data();
   private final TickQueue<Integer> field17 = new TickQueue();
   private final TickQueue<Integer> field18 = new TickQueue();
   private boolean field19;

   public KnockbackTrainer() {
      super(false);
      this.handle(EventTick.class, this::method4);
      this.handle(KnockbackEvent.class, this::method5);
      this.handle(EventWorldLoad.class, arg1 -> this.method21());
      this.handle(EventDisconnect.class, arg1 -> this.method19());
      this.method18(this::method19);
      this.method51(this::method19);
   }

   public String getId() {
      return "KNOCKBACK_TRAINER";
   }

   protected List<Framework7Extension> method9() {
      return ImmutableList.of(this.field13, this.field14, this.field15);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field9, this.field10})
      );
      lightingextension231.method7(
         SettingsPage.AUDIO, arg1x -> arg1x.method9(new ClientOption[]{this.field11, this.field12})
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field3, ModCategory.field6})
         .method2(new String[]{"jump reset", "jump", "reset"})
         .method11(this);
   }

   private void method4(EventTick highlightimpl21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 != null) {
         int number3 = EventTick.field1;
         if (!this.field18.method4(number3, this.method14()).isEmpty()) {
            this.field16.method3();
         }

         this.field17.method3(number3, this.method15());
         if (this.method16()) {
            this.method6(number3);
         }
      }
   }

   private void method5(KnockbackEvent knockbacktrainer1) {
      this.field16.method1(knockbacktrainer1.method1());
      if (knockbacktrainer1.method1() && (Boolean)this.field11.get()) {
         Ref.method3().bridge$getSoundHandler().bridge$playSound(field8, false, (Float)this.field12.get());
      }

      int number2 = EventTick.field1;
      Optional optional3 = this.field17.method2(number2, this.method15());
      if (optional3.isPresent()) {
         this.method8((Integer)((com.moonsworth.lunar.client.util.collection.TickQueue.Data)optional3.get()).value() - number2);
         this.method13();
      } else {
         this.field18.method1(number2, number2);
      }
   }

   private void method6(int number1) {
      Optional optional2 = this.field18.method2(number1, this.method14());
      if (optional2.isPresent()) {
         this.method8(number1 - (Integer)((com.moonsworth.lunar.client.util.collection.TickQueue.Data)optional2.get()).value());
         this.method13();
      } else {
         this.field17.method1(number1, number1);
      }
   }

   private void method13() {
      this.field17.clear();
      this.field18.clear();
   }

   private void method8(int number1) {
      int number2 = number1 - (Integer)this.field9.get();
      if (Math.abs(number2) <= (Integer)this.field10.get()) {
         this.field16.method2(number2);
         this.field13.method2(number2);
      } else {
         this.field16.method3();
      }
   }

   private int method14() {
      return (Integer)this.field10.get() + (Integer)this.field9.get();
   }

   private int method15() {
      return Math.max(0, (Integer)this.field10.get() - (Integer)this.field9.get());
   }

   private boolean method16() {
      boolean flag1 = this.method17();
      boolean flag2 = flag1 && !this.field19;
      this.field19 = flag1;
      return flag2;
   }

   private boolean method17() {
      GameOptionsBridge mixinhelper2_81 = Ref.method3().bridge$getGameSettings();
      if (mixinhelper2_81 == null) {
         return false;
      }

      KeyBindingBridge mixinhelper_152 = mixinhelper2_81.bridge$keyBindJump();
      return mixinhelper_152 != null && mixinhelper_152.bridge$isKeyDown();
   }

   public void method19() {
      this.field16.reset();
      this.method21();
   }

   private void method21() {
      this.method13();
      this.field19 = false;
   }

   @Generated
   public KnockbackTrainer.Data method22() {
      return this.field16;
   }

   public static final class Data {
      private int field1 = 0;
      private int field2 = 0;
      private int field3 = 0;
      private int field4 = 0;
      private int field5 = 0;
      private int field6 = 0;

      public Data() {
      }

      private void method1(boolean flag1) {
         this.field1++;
         if (flag1) {
            this.field2++;
         }
      }

      private void method2(int number1) {
         this.field3 = this.field3 + Math.abs(number1);
         this.field4++;
         this.field5++;
         if (this.field5 > this.field6) {
            this.field6 = this.field5;
         }
      }

      private void method3() {
         this.field5 = 0;
      }

      public double method4() {
         return this.field1 == 0 ? 0.0 : 100.0 * this.field2 / this.field1;
      }

      public double method5() {
         return this.field4 == 0 ? 0.0 : (double)this.field3 / this.field4;
      }

      private void reset() {
         this.field1 = 0;
         this.field2 = 0;
         this.field3 = 0;
         this.field4 = 0;
         this.field5 = 0;
         this.field6 = 0;
      }

      @Generated
      public int method6() {
         return this.field1;
      }

      @Generated
      public int method7() {
         return this.field5;
      }

      @Generated
      public int method8() {
         return this.field6;
      }
   }
}
