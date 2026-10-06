package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.ui.widget.ColorPickerOptionWidget;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.mixin.MixinHelper2;
import com.moonsworth.lunar.client.util.nameplate.Nameplate;
import com.moonsworth.lunar.client.util.rewindhandlers.RewindhandlersExtension3;
import com.moonsworth.lunar.client.util.rewindhandlers.RewindhandlersExtension4;
import java.awt.Color;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ColorOption extends IntegerOption implements RewindhandlersExtension3, RewindhandlersExtension4 {
   public static final Codec<Integer> field8 = Codec.withAlternative(Codec.INT, Nameplate.field22);
   private final ToggleOption field9;
   private final IntegerOption field10;
   private final EnumOption<com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension> field11;
   private final boolean field12;
   private final boolean field13;

   protected ColorOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<Integer> var2,
      int var3,
      boolean var4,
      boolean var5,
      ToggleOption.ToggleOptionBuilder var6,
      IntegerOption.Data var7,
      EnumOption.Data<com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension> var8
   ) {
      super(var1, var2, var3);
      this.field9 = (ToggleOption)var6.method31();
      this.field10 = (IntegerOption)var7.method31();
      this.field11 = (EnumOption<com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension>)var8.method31();
      this.field12 = var4;
      this.field13 = var5;
   }

   @Override
   protected void method11(MixinHelper2<ClientOption<?>> var1) {
      super.method2(var1);
      var1.method2(new ClientOption[]{this.field9, this.field10, this.field11});
   }

   public boolean method2(ColorOption var1) {
      if (!this.field9.get() || var1.field10.get().equals(this.field10.get()) && var1.field11.get() == this.field11.get()) {
         float[] var2 = this.method7();
         float[] var3 = var1.method7();
         float var4 = Math.abs(var3[0] - var2[0]);
         if (var4 > 0.015F) {
            return false;
         }

         float var5 = Math.abs(var3[1] - var2[1]);
         if (var5 > 0.015F) {
            return false;
         }

         float var6 = Math.abs(var3[2] - var2[2]);
         if (var6 > 0.015F) {
            return false;
         }

         float var7 = Math.abs(var1.getAlpha() - this.getAlpha());
         return var7 <= 4.0F;
      } else {
         return false;
      }
   }

   public boolean method9() {
      return this.field12;
   }

   @Override
   public boolean method14() {
      return this.field9.get();
   }

   @Override
   public int method11() {
      return this.method21().get();
   }

   @Override
   public boolean method10() {
      return this.field13;
   }

   @Override
   public void method1(@NonNull Integer var1) {
      if (!this.field13) {
         var1 = var1 & 16777215 | 0xFF000000;
      }

      super.method1(var1);
   }

   @Override
   public void method21(String var1) {
      if (method22(var1).isJsonObject()) {
         this.method10(var1);
      } else {
         super.method21(var1);
      }
   }

   public void method10(String var1) {
      try {
         int var2 = method24(var1);
         this.method1(Integer.valueOf(var2));
      } catch (NumberFormatException var11) {
         try {
            JsonElement var3 = JsonParser.parseString(var1);
            if (var3.isJsonObject()) {
               JsonObject var4 = var3.getAsJsonObject();
               if (var4.has("chroma")) {
                  JsonObject var5 = var4.getAsJsonObject("chroma");
                  if (var5.has("chroma")) {
                     this.field9.method10(var5.get("chroma").getAsBoolean());
                  }

                  if (var5.has("chromaType")) {
                     this.field11.method14(var5.get("chromaType")).ifPresent(this.field11::method10);
                  }

                  if (var5.has("chromaSpeed")) {
                     this.field10.method1(var5.get("chromaSpeed").getAsInt());
                  }
               }

               if (var4.has("hex")) {
                  this.method1(Integer.valueOf(method24(var4.get("hex").getAsString())));
               } else {
                  float[] var12 = new float[]{-1.0F, -1.0F, -1.0F};
                  if (var4.has("hue")) {
                     var12[0] = var4.get("hue").getAsFloat();
                  }

                  if (var4.has("saturation")) {
                     var12[1] = var4.get("saturation").getAsFloat();
                  }

                  if (var4.has("brightness")) {
                     var12[2] = var4.get("brightness").getAsFloat();
                  }

                  float var6 = -1.0F;
                  if (var4.has("opacity") && this.field13) {
                     var6 = var4.get("opacity").getAsFloat();
                  }

                  boolean var7 = var12[0] != -1.0F || var12[1] != -1.0F || var12[2] != -1.0F;
                  if (var7 || var6 != -1.0F) {
                     int var8 = 0;
                     if (var7) {
                        if (var12[0] != -1.0F && var12[1] != -1.0F && var12[2] != -1.0F) {
                           var8 = ThreadModuleDump23.method38(var12[0], var12[1], var12[2]);
                        } else {
                           float[] var9 = this.method7();
                           var8 = ThreadModuleDump23.method38(
                              var12[0] != -1.0F ? var12[0] : var9[0], var12[1] != -1.0F ? var12[1] : var9[1], var12[2] != -1.0F ? var12[2] : var9[2]
                           );
                        }
                     }

                     if (var6 != -1.0F) {
                        var8 = ThreadModuleDump23.method26(var8, var6);
                     }

                     this.method1(Integer.valueOf(var8));
                  }
               }
            }
         } catch (JsonSyntaxException var10) {
            var10.printStackTrace();
         }
      }
   }

   public static int method24(String var0) {
      if (var0.startsWith("#")) {
         var0 = var0.substring(1);
      }

      return (int)Long.parseLong(var0, 16);
   }

   public void method11(ColorOption var1) {
      this.method19().OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.method19().get());
      this.method21().method1(var1.method21().get());
      this.method23().OIRHOOIICOCIOOHICRRRICORIHHIHC(var1.method23().get());
      this.method1(var1.method13());
   }

   @Override
   public void method13(Object var1) {
      if (var1 instanceof ColorOption var2) {
         this.method19().method10(var2.method19().get());
         this.method21().method10(var2.method21().get());
         this.method23().method10(var2.method23().get());
         if (!this.field13) {
            var1 = var2.method13() & 16777215 | 0xFF000000;
         } else {
            var1 = var2.method13();
         }
      }

      if (var1 instanceof Integer var3) {
         super.method10(var3);
      }
   }

   public Integer method13() {
      int var1 = super.get();
      if (!this.field13) {
         var1 = var1 & 16777215 | 0xFF000000;
      }

      return var1;
   }

   public Integer method14(float var1) {
      if (this.field9.get()) {
         var1 = LcuiScreen.method151().getScaledWidth() + LcuiScreen.method151().getScaledHeight() - var1;
         return this.field11.get().color().apply(var1, this);
      } else {
         return this.method13();
      }
   }

   @Override
   public int getColor() {
      return this.method13();
   }

   @Override
   public int method1(float var1) {
      return this.method14(var1);
   }

   @Override
   public void method1(int var1) {
      this.method1(Integer.valueOf(var1));
   }

   public void method17() {
      this.method1(ThreadModuleDump48.field24.nextInt() & 16777215);
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new ColorPickerOptionWidget(this, var1);
   }

   @Override
   public void reset() {
      this.field9.reset();
      this.field10.reset();
      this.field11.reset();
      super.reset();
   }

   @Override
   public boolean isDefault() {
      return super.isDefault() && this.field9.isDefault() && this.field10.isDefault() && this.field11.isDefault();
   }

   @Generated
   public ToggleOption method19() {
      return this.field9;
   }

   @Generated
   public IntegerOption method21() {
      return this.field10;
   }

   @Generated
   public EnumOption<com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension> method23() {
      return this.field11;
   }

   public static class Data extends IntegerRangeOptionBuilder<ColorOption.Data, ColorOption> {
      private boolean field17 = true;
      private boolean field18 = true;
      private final ToggleOption.ToggleOptionBuilder field19 = OptionFactory.method7("chroma");
      private final IntegerOption.Data field20 = (IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4("chromaSpeed")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(40))
         .OCRRICRIORICCCRHIOHORCICIHHICO(1, 100);
      private final EnumOption.Data<com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension> field21 = OptionFactory.method10(
         "chromaType", com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension.WAVE
      );

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.COLOR_PICKER;
      }

      @Override
      protected Codec<Integer> method3() {
         return ColorOption.field8;
      }

      @Override
      protected @Nullable Function<ColorOption, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               ColorOption var1x = (ColorOption)this.option;
               int var2 = var1x.method13();
               int var3 = var2 << 8 | var2 >>> 24;
               JsonObject var4 = super.provide().getAsJsonObject();
               var4.addProperty("value", "#" + String.format("%08x", var3));
               JsonObject var5 = new JsonObject();
               OptionDataProvider var6 = (OptionDataProvider)var1x.field9.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field10);
               if (var6 != null) {
                  var5.add("chroma", var6.provide());
               }

               var6 = (OptionDataProvider)var1x.field10.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field10);
               if (var6 != null) {
                  var5.add("chromaSpeed", var6.provide());
               }

               var6 = (OptionDataProvider)var1x.field11.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field10);
               if (var6 != null) {
                  var5.add("chromaType", var6.provide());
               }

               var4.add("chroma", var5);
               var4.addProperty("hex", String.format("%08x", var2));
               float[] var7 = ThreadModuleDump23.method39(var2);
               var4.addProperty("hue", var7[0]);
               var4.addProperty("saturation", var7[1]);
               var4.addProperty("brightness", var7[2]);
               var4.addProperty("alpha", ThreadModuleDump23.method8(var2));
               return var4;
            }
         };
      }

      @Contract("_->this")
      public ColorOption.Data method4(Consumer<ToggleOption.ToggleOptionBuilder> var1) {
         var1.accept(this.field19);
         return this;
      }

      @Contract("_->this")
      public ColorOption.Data method5(Consumer<IntegerOption.Data> var1) {
         var1.accept(this.field20);
         return this;
      }

      @Contract("_->this")
      public ColorOption.Data method6(Consumer<EnumOption.Data<com.moonsworth.lunar.client.util.rewindhandlers.Gui2Extension>> var1) {
         var1.accept(this.field21);
         return this;
      }

      @Contract("_->this")
      public ColorOption.Data method7(TextColor var1) {
         return (ColorOption.Data)this.ORCRHOICOIHCRRIOHIHIROHOCRRIOO(var1.value());
      }

      @Contract("_->this")
      public ColorOption.Data method8(AdventureChatFormatting var1) {
         return (ColorOption.Data)this.ORCRHOICOIHCRRIOHIHIROHOCRRIOO(Objects.requireNonNull(var1.getAdventureColor()).value() | 0xFF000000);
      }

      @Contract("_->this")
      public ColorOption.Data method9(Color var1) {
         return (ColorOption.Data)this.ORCRHOICOIHCRRIOHIHIROHOCRRIOO(var1.getRGB());
      }

      @Contract("_,_,_,_->this")
      public ColorOption.Data method10(
         @Range(from = 0L, to = 255L) int var1,
         @Range(from = 0L, to = 255L) int var2,
         @Range(from = 0L, to = 255L) int var3,
         @Range(from = 0L, to = 255L) int var4
      ) {
         return (ColorOption.Data)this.ORCRHOICOIHCRRIOHIHIROHOCRRIOO(ThreadModuleDump23.method10(var1, var2, var3, var4));
      }

      @Contract("_,_,_->this")
      public ColorOption.Data method11(
         @Range(from = 0L, to = 255L) int var1, @Range(from = 0L, to = 255L) int var2, @Range(from = 0L, to = 255L) int var3
      ) {
         return this.method10(var1, var2, var3, 255);
      }

      @Contract("_,_,_,_->this")
      public ColorOption.Data method12(
         @Range(from = 0L, to = 1L) float var1,
         @Range(from = 0L, to = 1L) float var2,
         @Range(from = 0L, to = 1L) float var3,
         @Range(from = 0L, to = 1L) float var4
      ) {
         return (ColorOption.Data)this.ORCRHOICOIHCRRIOHIHIROHOCRRIOO(ThreadModuleDump23.method11(var1, var2, var3, var4));
      }

      @Contract("_,_,_->this")
      public ColorOption.Data method13(
         @Range(from = 0L, to = 1L) float var1, @Range(from = 0L, to = 1L) float var2, @Range(from = 0L, to = 1L) float var3
      ) {
         return this.method12(var1, var2, var3, 1.0F);
      }

      @Contract("->this")
      public ColorOption.Data method14() {
         this.field19.OOOIROIIOCOOHICRIRHHHRROHHHHIO(true);
         return this;
      }

      @Contract("->this")
      public ColorOption.Data method15() {
         this.field17 = false;
         return this;
      }

      @Contract("->this")
      public ColorOption.Data method16() {
         this.field18 = false;
         return this;
      }

      @Contract("_->this")
      public ColorOption.Data method17(boolean var1) {
         this.field17 = var1;
         return this;
      }

      @Contract("_->this")
      public ColorOption.Data method18(boolean var1) {
         this.field18 = var1;
         return this;
      }

      protected ColorOption method19() {
         return new ColorOption(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC,
            this.codec,
            this.field7,
            this.field17,
            this.field18,
            this.field19,
            this.field20,
            this.field21
         );
      }
   }
}
