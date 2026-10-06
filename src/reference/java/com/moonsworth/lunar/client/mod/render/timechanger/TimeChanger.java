package com.moonsworth.lunar.client.mod.render.timechanger;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.ui.widget.DayNightOptionWidget;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.event.render.EventFog.EventFogColor;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldTime;
import com.moonsworth.lunar.client.event.mixin.fishing.EventGetHorizon;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorld.EventWorldChange;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.NumberRule;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Date;
import lombok.Generated;

public class TimeChanger extends AbstractFeature {
   private static final long field8 = 3600L;
   private final IntegerOption field9 = (IntegerOption)((Data)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4(
                  "timeChangerTime"
               )
               .HORHROIOIOICIRHIOCOICHHHIHCIIO(DayNightOptionWidget::new))
            .method4(12000))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 24000))
      .method31();
   private final IntegerOption field10 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("horizonYLevel")
            .method4(63))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 63))
      .method31();
   private final ToggleOption field11 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("useRealTime")
      .method31();
   private final ModifierKeybindOption field12 = (ModifierKeybindOption)com.moonsworth.lunar.client.config.option.OptionFactory.method18("increaseTime")
      .method5(KeyCode.KEY_RBRACKET)
      .method31();
   private final ModifierKeybindOption field13 = (ModifierKeybindOption)com.moonsworth.lunar.client.config.option.OptionFactory.method18("decreaseTime")
      .method5(KeyCode.KEY_LBRACKET)
      .method31();
   private final ToggleOption field14 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("timePassage")
      .method31();
   private final IntegerOption field15 = (IntegerOption)((Data)((Data)com.moonsworth.lunar.client.config.option.OptionFactory.method4("speed")
            .method4(1))
         .OCRRICRIORICCCRHIOHORCICIHHICO(0, 20))
      .method31();
   private final EnumOption<TimeChanger.Type> field16 = (EnumOption<TimeChanger.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "overworldSky", TimeChanger.Type.DEFAULT
      )
      .method31();
   private final Date field17 = new Date();
   private int field18 = 0;
   private long field19 = 0L;

   public TimeChanger() {
      super(false);
      this.handle(EventWorldChange.class, this::method9);
      this.handle(EventGetHorizon.class, this::method8);
      this.handle(EventWorldTime.class, this::method7);
      this.handle(EventTick.class, this::method2);
      if (Ref.MC_VERSION > 5) {
         this.handle(EventFogColor.class, this::method1);
      }
   }

   public String getId() {
      return "TIME_CHANGER";
   }

   private void method1(EventFogColor data21) {
      if (Ref.method8() != null) {
         TimeChanger.Type type2 = (TimeChanger.Type)this.field16.get();
         if (type2 != TimeChanger.Type.DEFAULT) {
            if (Ref.method8().bridge$getDimensionId() == 0) {
               if (type2 == TimeChanger.Type.NETHER) {
                  float value3 = 0.2F;
                  float value4 = 0.03137255F;
                  float value5 = 0.03137255F;
                  data21.method1(value3, value4, value5);
               } else if (type2 == TimeChanger.Type.END) {
                  float value6 = 0.627451F;
                  float value7 = 0.5019608F;
                  float value8 = 0.627451F;
                  data21.method1(value6, value7, value8);
               }
            }
         }
      }
   }

   private void method2(EventTick highlightimpl21) {
      if (this.field12.method19()) {
         this.field9.method1((Integer)this.field9.get() + 100);
      }

      if (this.field13.method19()) {
         this.field9.method1((Integer)this.field9.get() - 100);
      }

      if ((Boolean)this.field14.get() && !(Boolean)this.field11.get()) {
         this.field9.method1((Integer)this.field9.get() + (Integer)this.field15.get());
         NumberRule nameplate2 = (NumberRule)this.field9.RHRHIOOCICIORIOCIHHCIIRCRHHOII(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
         if ((Integer)this.field9.get() >= nameplate2.getMax().intValue()) {
            this.field9.method1(nameplate2.getMin().intValue());
         }
      }

      this.method5(Ref.method8(), false);
   }

   public void method3(boolean flag1) {
      if (Ref.method8() != null) {
         if (flag1) {
            this.method5(Ref.method8(), true);
         } else {
            this.method6(Ref.method8());
         }
      }
   }

   private int method13() {
      int number1 = (Integer)this.field9.get() + 18000;
      NumberRule nameplate2 = (NumberRule)this.field9.RHRHIOOCICIORIOCIHHCIIRCRHHOII(com.moonsworth.lunar.client.config.option.OptionTraits.field7);
      if (number1 >= nameplate2.getMax().intValue()) {
         number1 -= 24000;
      }

      return number1;
   }

   private void method5(Itemcounter6 itemcounter61, boolean flag2) {
      if (itemcounter61 != null) {
         itemcounter61.bridge$setWorldTime(this.field11.get() ? this.method14() : this.method13(), flag2);
      }
   }

   private void method6(Itemcounter6 itemcounter61) {
      if (itemcounter61 != null && Ref.MC_VERSION >= 36) {
         itemcounter61.bridge$resetWorldTime();
      }
   }

   private void method7(EventWorldTime highlightimpl101) {
      highlightimpl101.setCancelled(true);
   }

   private void method8(EventGetHorizon highlightimpl181) {
      highlightimpl181.setValue(((Integer)this.method15().get()).intValue());
   }

   private void method9(EventWorldChange data31) {
      if (data31.method1() != null) {
         this.method5(data31.method1(), false);
      }
   }

   private int method14() {
      if (System.currentTimeMillis() - this.field19 < 3600L) {
         return this.field18;
      }

      this.field17.setTime(System.currentTimeMillis());
      int number1 = this.field17.getHours() - 6;
      if (number1 <= 0) {
         number1 += 24;
      }

      number1 *= 1000;
      number1 = (int)(number1 + (this.field17.getSeconds() + this.field17.getMinutes() * 60) / 3.6);
      this.field18 = number1;
      this.field19 = System.currentTimeMillis();
      return this.field18;
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.method9(new ClientOption[]{this.field9}).method3(this.field11::get);
            arg1x.method9(new ClientOption[]{this.field16, this.field10, this.field11, this.field12, this.field13});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field14, arg1xx -> arg1xx.method9(new ClientOption[]{this.field15}))
               .method3(this.field11::get);
         }
      );
      this.field9.CICORRHIOIIOORRRICCORIOIOCIHII(arg1x -> {
         if (Ref.method8() != null && this.isEnabled()) {
            Ref.method8().bridge$setWorldTime(this.field11.get() ? this.method14() : this.method13(), true);
         }
      });
   }

   @Generated
   public IntegerOption method15() {
      return this.field10;
   }

   @Generated
   public IntegerOption method16() {
      return this.field15;
   }

   @Generated
   public EnumOption<TimeChanger.Type> method17() {
      return this.field16;
   }

   public enum Type implements OptionEnumValue {
      DEFAULT("default"),
      NETHER("nether"),
      END("end");

      private final String id;

      public String id() {
         return this.id;
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
