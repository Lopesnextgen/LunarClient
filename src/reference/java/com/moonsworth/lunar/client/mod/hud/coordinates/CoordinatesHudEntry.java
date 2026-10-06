package com.moonsworth.lunar.client.mod.hud.coordinates;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.ApplyToAllColorWidget;
import com.moonsworth.lunar.client.render.texture.TexturePathResolver;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.text.TextUtils;
import com.moonsworth.lunar.client.driver.MarkerPredicate;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.Supplier;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class CoordinatesHudEntry extends AbstractFeature {
   private final TexturePathResolver clickHandler = (TexturePathResolver)this.method63(TexturePathResolver.class);
   protected final ToggleOption showLabel = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showLabel").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   protected final ColorOption labelColor = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
               "labelColor"
            )
            .HORHROIOIOICIRHIOCOICHHHIHCIIO((arg1x, arg2x) -> new ApplyToAllColorWidget(arg1x, arg2x, this.clickPredicate())))
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   protected final String tag;
   protected final Supplier<String> valueSupplier;
   protected final String previewValue;

   protected CoordinatesHudEntry(CoordinatesHud coordinates1, String text2, Supplier<String> supplier3, String text4) {
      super(true);
      this.tag = text2;
      this.valueSupplier = supplier3;
      this.previewValue = text4;
      this.registerOptions(ModTraits.field16, ChildModBinding.method3(coordinates1));
      this.registerOptions(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.registerOptions(ModTraits.field1, this.registerOptions(coordinates1));
   }

   @Contract("_->param1")
   protected ToggleOptionBuilder configureBackground(ToggleOptionBuilder data21) {
      return data21;
   }

   protected CoordinatesHudEntry.Data registerOptions(final CoordinatesHud coordinates1) {
      return new CoordinatesHudEntry.Data(0.0F, 0.0F) {
         @Override
         protected ToggleOptionBuilder configureBackground(ToggleOptionBuilder data21x) {
            return (ToggleOptionBuilder)data21x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }

         @Override
         protected ToggleOptionBuilder registerOptions(ToggleOptionBuilder data21x) {
            return (ToggleOptionBuilder)data21x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }

         @Override
         protected com.moonsworth.lunar.client.config.option.FloatOption.Data configureBackground(com.moonsworth.lunar.client.config.option.FloatOption.Data data1x) {
            return (com.moonsworth.lunar.client.config.option.FloatOption.Data)data1x.ORICHRORRORHORHOIHCRHOORCRRHOI(coordinates1::moveChildrenTogether);
         }
      };
   }

   public String getId() {
      throw new IllegalStateException("CoordinatesHudEntry must be created using CoordinatesHudEntry.create()!");
   }

   @OverridingMethodsMustInvokeSuper
   public void registerOptions(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> arg1x.method9(new ClientOption[]{this.showLabel}));
      lightingextension231.method7(SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.labelColor}));
   }

   private MarkerPredicate clickPredicate() {
      return (arg1, arg2) -> {
         CoordinatesHud coordinates3 = (CoordinatesHud)((ChildModBinding)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         coordinates3.shouldRender(this);
         return true;
      };
   }

   public ColorOption getLabelColorOption() {
      if (this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field1) instanceof CoordinatesHudEntry.Data data1) {
         return data1.method35();
      } else {
         throw new RuntimeException("Unable to find the text color option for this mod!");
      }
   }

   public static CoordinatesHudEntry create(CoordinatesHud coordinates0, String text1, Supplier<String> supplier2, String text3) {
      final String text4 = "COORDINATES_" + TextUtils.toUpperSnakeCase(text1) + "_CHILD";
      return new CoordinatesHudEntry(coordinates0, text1, supplier2, text3) {
         @Override
         public String getId() {
            return text4;
         }
      };
   }

   @Generated
   public ToggleOption getShowLabel() {
      return this.showLabel;
   }

   @Generated
   public ColorOption getLabelColor() {
      return this.labelColor;
   }

   protected class Data extends TypedHudRenderer<String> {
      public Data(float value2, float value3) {
         super(value2, value3, HudAnchor.TOP_LEFT);
      }

      public boolean shouldRender(boolean flag1) {
         CoordinatesHud coordinates2 = (CoordinatesHud)((ChildModBinding)CoordinatesHudEntry.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         return !coordinates2.getMoveChildrenIndividually().get()
            ? false
            : (Boolean)coordinates2.getShowWhileTyping().get() || !CoordinatesHudEntry.this.mc.bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen();
      }

      @Contract("_->param1")
      protected ToggleOptionBuilder configureBackground(ToggleOptionBuilder data21) {
         return (ToggleOptionBuilder)data21.ORICHRORRORHORHOIHCRHOORCRRHOI(this::method30);
      }

      @Contract("_->param1")
      protected ToggleOptionBuilder registerOptions(ToggleOptionBuilder data21) {
         return data21;
      }

      @Contract("_->param1")
      protected com.moonsworth.lunar.client.config.option.FloatOption.Data configureBackground(com.moonsworth.lunar.client.config.option.FloatOption.Data data1) {
         return data1;
      }

      public HudSize getShowLabel() {
         return new HudSize(10, 10, 10, 0, 0, 0);
      }

      @Nullable
      public String create(boolean flag1) {
         return null;
      }

      public boolean method30() {
         CoordinatesHud coordinates1 = (CoordinatesHud)((ChildModBinding)CoordinatesHudEntry.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         return !coordinates1.moveChildrenTogether();
      }

      public void render(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         boolean flag5 = (Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
         boolean flag6 = !flag5 && (Boolean)this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII.get();
         String text7 = flag4 ? CoordinatesHudEntry.this.previewValue : CoordinatesHudEntry.this.valueSupplier.get();
         String text8 = CoordinatesHudEntry.this.showLabel.get() ? CoordinatesHudEntry.this.tag + ": " + text7 : text7;
         float value9 = Ref.method10().bridge$getStringWidth(text8);
         if (flag6) {
            value9 += 9.0F;
         }

         float value10 = flag5 ? 4.0F : 2.0F;
         this.method11(value9 + value10 * 2.0F, Ref.method10().method19() + value10 * 2.0F);
         float value11 = value2 + value10;
         float value12 = value3 + (this.getHeight() / 1.88F - Ref.method10().method19() / 2.0F + 0.5F);
         MixinHelper_4 mixinhelper_413 = highlightimpl1.method2();
         if (flag5) {
            this.HROIRCHIHORCHCHCRICOOOIOOHIRCH.method11(mixinhelper_413, value2, value3, this.getWidth(), this.getHeight());
            if ((Boolean)this.OIRIICOHRIHHOCCCIICCRRCICOOCHI.get()) {
               this.CRROIIOHCOROIIOROHHCHIRRCORCRH
                  .method11(mixinhelper_413, this, value2, value3, this.getWidth(), this.getHeight(), (Float)this.HCHIRRHHICRCCIOOHCOICHHIORICHH.get());
            }
         }

         boolean flag14 = (Boolean)this.ORHRIHRICHICRCOCIIRIOICOIICHOI.get();
         if (flag6) {
            value11 = this.OOOCCCRICCHOORCCRHHRHHCOOCORRC.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_413, "[", value11, value12, flag14);
         }

         if ((Boolean)CoordinatesHudEntry.this.showLabel.get()) {
            value11 = CoordinatesHudEntry.this.labelColor.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_413, CoordinatesHudEntry.this.tag + ": ", value11, value12, flag14);
         }

         mixinhelper_413.method19(Ref.method10(), text7, value11, value12, this.getTextColorArgb(), flag14);
         value11 += Ref.method10().bridge$getStringWidth(text7);
         if (flag6) {
            this.OOOCCCRICCHOORCCRHHRHHCOOCORRC.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_413, "]", value11, value12, flag14);
         }
      }

      public HudConditionSet getConditions() {
         return HudConditionSet.method5().method1(true).method2(true).method8();
      }

      protected int getTextColorArgb() {
         return this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR.method14(0.0F);
      }
   }
}
