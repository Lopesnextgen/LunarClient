package com.moonsworth.lunar.client.mod.combat.knockbacktrainer;

import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import java.time.Duration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title.Times;

public class KnockbackJumpTiming extends AbstractFeature {
   private final ColorOption field8 = (ColorOption)((Data)OptionFactory.method8("perfectColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141291))
      .method31();
   private final ColorOption field9 = (ColorOption)((Data)OptionFactory.method8("earlyColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-22016))
      .method31();
   private final ColorOption field10 = (ColorOption)((Data)OptionFactory.method8("lateColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-43691))
      .method31();
   private final IntegerOption field11 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "titleStayTicks"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(15))
         .method7(5, 60))
      .method31();

   public KnockbackJumpTiming(KnockbackTrainer knockbacktrainer1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(knockbacktrainer1));
   }

   public String getId() {
      return "KNOCKBACK_JUMP_TIMING";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.field11}));
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field8, this.field9, this.field10})
      );
   }

   public void method2(int number1) {
      if (this.isEnabled()) {
         String text2;
         int number3;
         if (number1 == 0) {
            text2 = "PERFECT";
            number3 = this.field8.method14(0.0F);
         } else if (number1 < 0) {
            text2 = "EARLY " + number1;
            number3 = this.field9.method14(0.0F);
         } else {
            text2 = "LATE +" + number1;
            number3 = this.field10.method14(0.0F);
         }

         TextComponent text4 = Component.text(text2, TextColor.color(number3));
         long number5 = ((Integer)this.field11.get()).intValue() * 50L;
         Ref.method3()
            .bridge$getGuiIngame()
            .bridge$displayTitle(text4, null, Times.times(Duration.ofMillis(100L), Duration.ofMillis(number5), Duration.ofMillis(200L)), 1.0F, 0.0F, 0.0F);
      }
   }
}
