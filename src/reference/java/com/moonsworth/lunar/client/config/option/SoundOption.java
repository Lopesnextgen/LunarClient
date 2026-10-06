package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.SoundListOptionWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.Coordinates;
import com.moonsworth.lunar.client.util.mixin.MixinHelper2;
import java.util.function.Function;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class SoundOption extends AbstractValueOption<String> {
   public static final String NONE = "none";
   public static final String field7 = "file:";
   private final IntegerOption field8 = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4("volume")
            .method4(25))
         .method7(0, 100))
      .method31();

   protected SoundOption(@Annotation(method1 = Annotation.Type.SETTING) String var1, @Nullable Codec<String> var2, String var3) {
      super(var1, var2, var3);
   }

   @Override
   protected void method11(MixinHelper2<ClientOption<?>> var1) {
      super.method11(var1);
      var1.method2(new ClientOption[]{this.field8});
   }

   public boolean method7() {
      return "none".equals(this.get());
   }

   public boolean isFile() {
      return this.get().startsWith("file:");
   }

   public String getFileName() {
      return this.isFile() ? this.get().substring("file:".length()) : this.get();
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new SoundListOptionWidget(this, var1);
   }

   @Override
   public void reset() {
      this.field8.reset();
      super.reset();
   }

   @Override
   public boolean isDefault() {
      return super.isDefault() && this.field8.isDefault();
   }

   @Generated
   public IntegerOption method8() {
      return this.field8;
   }

   public static class Data extends DefaultValueBuilder<SoundOption.Data, SoundOption, String> {
      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.DROPDOWN;
      }

      @Override
      protected @Nullable Codec<String> method3() {
         return Codec.STRING;
      }

      protected String method11() {
         return "none";
      }

      @Override
      protected @Nullable Function<SoundOption, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               JsonObject var1x = super.provide().getAsJsonObject();
               var1x.add("choices", Coordinates.method11());
               IntegerOption var2 = ((SoundOption)this.option).field8;
               OptionDataProvider var3 = (OptionDataProvider)var2.HIRHCCHIRHRORIICOIHIHCICOIRHHC(OptionTraits.field10);
               if (var3 != null) {
                  var1x.add("volume", var3.provide());
               }

               return var1x;
            }
         };
      }

      protected SoundOption method12() {
         return new SoundOption(this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC, this.codec, this.defaultValue);
      }
   }
}
