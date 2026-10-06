package com.moonsworth.lunar.client.mod.render.titles;

import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudElementBase;
import com.moonsworth.lunar.client.framework.feature.titles.TitleRenderer;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import net.kyori.adventure.util.Ticks;

public class Titles extends AbstractFeature {
   private final ToggleOption field8 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showInHudEditor")
      .method31();
   private final ToggleOption field9 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("keepTitleCentered")
         .method4(true))
      .method31();
   private final TextOption field10 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method12(
            "title"
         )
         .method2("BED DESTROYED!"))
      .method31();
   private final ColorOption field11 = (ColorOption)com.moonsworth.lunar.client.config.option.OptionFactory.method8("titleColor")
      .method8(ChatFormatting.RED)
      .method31();
   private final TextOption field12 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method12(
            "subtitle"
         )
         .method2("You will no longer respawn!"))
      .method31();
   private final ColorOption field13 = (ColorOption)com.moonsworth.lunar.client.config.option.OptionFactory.method8("subtitleColor")
      .method8(ChatFormatting.WHITE)
      .method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("useMinecraftGUIScale")
         .method4(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showTitle")
         .method4(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showSubtitle")
         .method4(true))
      .method31();
   private Title field17;
   private final TitleRenderer field18 = new TitleRenderer();

   public Titles() {
      super(true);
      this.method12(ModTraits.field1, new Titles.Data());
   }

   private void method13() {
      this.field17 = Title.title(
         Component.text((String)this.field10.get()).style(Style.style(TextColor.color(this.field11.method13()))),
         Component.text((String)this.field12.get()).color(TextColor.color(this.field13.method13())),
         Times.times(Ticks.duration(0L), Ticks.duration(30L), Ticks.duration(0L))
      );
   }

   public String getId() {
      return "TITLES";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         this.field8,
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10, this.field11, this.field12, this.field13, this.field14})
      );
      lightingextension231.method9(new ClientOption[]{this.field9, this.field15, this.field16});
      this.field10.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method13());
      this.field11.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method13());
      this.field12.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method13());
      this.field13.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1x -> this.method13());
   }

   protected ModDetails method20() {
      return ModDetails.method7().method9(true).method11(this);
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.method13();
      }
   }

   public boolean method14() {
      return this.isEnabled() || Ref.method4().method40().method67().method15();
   }

   @Generated
   public TitleRenderer method15() {
      return this.field18;
   }

   private class Data extends HudElementBase {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_CENTER);
      }

      public float method7() {
         return 0.5F;
      }

      public float method8() {
         return 1.5F;
      }

      public void method18() {
         super.method18();
         this.method10(this.HHIHOHIIHCRIRIRRCCOHHRORHHCRIH());
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         if (!Ref.method4().method40().method85().method17(arg0 -> !arg0.method53().method16())) {
            if (!flag4 || (Boolean)Titles.this.field8.get()) {
               Bridge10_2 bridge10_25 = Ref.method10();
               if (bridge10_25 != null) {
                  Title title6 = flag4 ? Titles.this.field17 : Titles.this.mc.bridge$getGuiIngame().bridge$getTitle();
                  if (title6 != null) {
                     int number7 = flag4 ? 30 : Titles.this.mc.bridge$getGuiIngame().bridge$titlesTimer();
                     this.method5(highlightimpl1.method2(), title6, number7, value2, value3, flag4);
                     this.method10(bridge10_25, title6, flag4);
                  }
               }
            }
         }
      }

      private void method5(MixinHelper_4 mixinhelper_41, Title title2, int number3, float value4, float value5, boolean flag6) {
         Titles.this.field18
            .method2(
               mixinhelper_41, title2, number3, value4, value5, this.getWidth(), this.getHeight(), flag6, (Boolean)Titles.this.field15.get(), (Boolean)Titles.this.field16.get()
            );
      }

      public boolean method4(boolean flag1) {
         if (Ref.method10() == null) {
            return false;
         }

         if (flag1) {
            return !Titles.this.field8.get() ? false : Titles.this.field17 != null;
         }

         Title title2 = Titles.this.mc.bridge$getGuiIngame().bridge$getTitle();
         if (title2 == null) {
            return false;
         }

         int number3 = Titles.this.mc.bridge$getGuiIngame().bridge$titlesTimer();
         return number3 > 0;
      }

      public boolean method30() {
         return (Boolean)Titles.this.field8.get();
      }

      public boolean method33() {
         return (Boolean)Titles.this.field14.get();
      }

      public boolean method29() {
         return true;
      }

      private void method10(Bridge10_2 bridge10_21, Title title2, boolean flag3) {
         if (flag3) {
            title2 = Titles.this.field17;
         }

         float value4 = TextBridge.getTextWidth(title2.title(), bridge10_21) * 4.0F;
         float value5 = TextBridge.getTextWidth(title2.subtitle(), bridge10_21) * 2.0F;
         float value6 = Math.max(value4, value5);
         float value7 = this.getWidth();
         if ((Boolean)Titles.this.field9.get() && value7 != value6 && value7 != 0.0F) {
            float value8 = value7 / 2.0F;
            float value9 = value6 / 2.0F;
            switch (this.IIRHROCCOCCRIOCCIHRHRRCHICOHIH().getHorizontal()) {
               case LEFT:
                  this.method59(this.getX() + (value8 - value9), this.getY());
                  break;
               case RIGHT:
                  this.method59(this.getX() - (value8 - value9), this.getY());
            }
         }

         this.method58(value6, 72.0F);
      }
   }
}
