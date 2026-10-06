package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click17;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.Annotation6;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import org.jetbrains.annotations.NotNull;

public abstract class TitledHudElement extends MixinCore9Base {
   private final ColorOption field13;
   private final ColorOption field14;
   private final ColorOption field15;
   private boolean field16 = false;
   private boolean field17 = false;
   private boolean field18 = false;

   public TitledHudElement(
      @Annotation6(method1 = Annotation6.Type.X) float var1,
      @Annotation6(method1 = Annotation6.Type.Y) float var2,
      @Annotation6(method1 = Annotation6.Type.POSITION) @NotNull HudAnchor var3
   ) {
      super(var1, var2, var3);
      this.field13 = (ColorOption)OptionFactory.method8("skyblockTitleColor").method8(this.method15()).method31();
      this.field14 = (ColorOption)OptionFactory.method8("skyblockSubjectColor").method8(this.method16()).method31();
      this.field15 = (ColorOption)OptionFactory.method8("skyblockValueColor").method8(this.method17()).method31();
      MixinCore5Iterator var4 = new MixinCore5Iterator(true).method6(MixinCore5Iterator.field2, new PaddedHudComponent(this.method5()).method10(2.0F));
      this.method3(var4);
      this.method3(Click17.withPadding(var4));
   }

   @Override
   public void method1(RootSettingsAssembler var1) {
      super.method7(var1);
      if (this.field16) {
         var1.method11(new ClientOption[]{this.field13});
      }

      if (this.field17) {
         var1.method11(new ClientOption[]{this.field14});
      }

      if (this.field18) {
         var1.method11(new ClientOption[]{this.field15});
      }
   }

   protected abstract MixinCore5 method5();

   protected abstract void method3(MixinCore5Iterator var1);

   protected AdventureChatFormatting method15() {
      return AdventureChatFormatting.GOLD;
   }

   protected AdventureChatFormatting method16() {
      return AdventureChatFormatting.GOLD;
   }

   protected AdventureChatFormatting method17() {
      return AdventureChatFormatting.WHITE;
   }

   public RewindhandlersExtension method19() {
      this.field16 = true;
      return RewindhandlersExtension.method23(this.field13.method14(0.0F));
   }

   public RewindhandlersExtension method20() {
      this.field17 = true;
      return RewindhandlersExtension.method23(this.field14.method14(0.0F));
   }

   public RewindhandlersExtension method22() {
      this.field18 = true;
      return RewindhandlersExtension.method23(this.field15.method14(0.0F));
   }
}
