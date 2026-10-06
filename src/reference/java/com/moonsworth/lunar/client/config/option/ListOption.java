package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.lunarclient.dfu.serialization.JsonOps;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.MultiSelectWidget;
import com.moonsworth.lunar.client.ui.widget.OptionWidget;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.OptionJsonProvider;
import com.moonsworth.lunar.client.driver.core.DriverFieldTypeLegacy;
import com.moonsworth.lunar.client.util.Annotation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Generated;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public class ListOption<T> extends AbstractValueOption<List<T>> {
   protected final boolean field7;
   protected final @Nullable Consumer<T> field8;
   protected final @Nullable Function<T, String> field9;
   protected final @Nullable Codec<T> field10;

   public ListOption(
      @Annotation(method1 = Annotation.Type.SETTING) String var1,
      @Nullable Codec<List<T>> var2,
      List<T> var3,
      boolean var4,
      @Nullable Consumer<T> var5,
      @Nullable Function<T, String> var6,
      @Nullable Codec<T> var7
   ) {
      super(var1, var2, var3);
      this.field7 = var4;
      this.field8 = var5;
      this.field9 = var6;
      this.field10 = var7;
   }

   public void method1(List<T> var1) {
      super.method10(new ArrayList(var1));
   }

   @Override
   public void method21(String var1) {
      JsonElement var2 = method22(var1);
      if (this.field10 != null && !var2.isJsonArray()) {
         this.field10
            .parse(JsonOps.INSTANCE, var2)
            .ifError(var1x -> Inventorymod2.method5(new IOException("Error parsing element of option " + this + ": " + var1x), "Option Parse"))
            .result()
            .ifPresent(this::method6);
      } else {
         super.method21(var1);
      }
   }

   public void method6(T var1) {
      if (this.contains((T)var1)) {
         this.remove((T)var1);
      } else {
         this.add((T)var1);
      }
   }

   public boolean add(T var1) {
      try {
         ArrayList var2 = new ArrayList<>(this.get());
         boolean var3;
         if (this.field7) {
            var2.add(0, var1);
            var3 = true;
         } else {
            var3 = var2.add(var1);
         }

         if (this.field8 != null) {
            this.field8.accept((T)var1);
         }

         super.method10(var2);
         return var3;
      } catch (Exception var4) {
         var4.printStackTrace();
         return false;
      }
   }

   public boolean remove(T var1) {
      try {
         ArrayList var2 = new ArrayList<>(this.get());
         boolean var3 = var2.remove(var1);
         if (this.field8 != null) {
            this.field8.accept((T)var1);
         }

         super.method10(var2);
         return var3;
      } catch (Exception var4) {
         var4.printStackTrace();
         return false;
      }
   }

   public boolean contains(T var1) {
      try {
         return this.get().contains(var1);
      } catch (Exception var3) {
         var3.printStackTrace();
         return false;
      }
   }

   @Override
   protected OptionWidget<?> method25(GuiWidget var1) {
      return new MultiSelectWidget(this, var1);
   }

   @Override
   public ClientOption<List<T>> method20() {
      ClientOption var1 = super.method20();
      var1.method10(this.get());
      return var1;
   }

   @Generated
   public @Nullable Function<T, String> method7() {
      return this.field9;
   }

   public static class Data<B extends ListOption.Data<B, T>, T>
      extends DefaultValueBuilder<ListOption.Data<B, T>, ListOption<T>, List<T>> {
      private boolean field14 = false;
      private @Nullable Consumer<T> field15;
      private @Nullable Function<T, String> field16;
      private @Nullable Codec<T> elementCodec;

      protected Data(@Annotation(method1 = Annotation.Type.SETTING) String var1) {
         super(var1);
      }

      @Override
      protected DriverFieldTypeLegacy method2() {
         return DriverFieldTypeLegacy.MODIFIABLE_LIST;
      }

      @Override
      protected @Nullable Codec<List<T>> method3() {
         return null;
      }

      @Override
      protected @Nullable Function<ListOption<T>, OptionDataProvider> method4() {
         return var1 -> new OptionJsonProvider(var1) {
            @Override
            public JsonElement provide() {
               ListOption var1x = (ListOption)this.option;
               JsonObject var2 = super.provide().getAsJsonObject();
               var2.add("value", this.method1(var1x));
               return var2;
            }

            private JsonArray method1(ListOption<T> var1) {
               JsonArray var2 = new JsonArray();

               for (Object var4 : (List)var1.ICCOIHCHIRROOOHIOIHOCIIHHICCCI()) {
                  JsonObject var5 = new JsonObject();
                  var5.addProperty("name", this.method2(var1, (T)var4));
                  var5.addProperty("value", var4.toString());
                  var2.add(var5);
               }

               return var2;
            }

            private String method2(ListOption<T> var1, T var2) {
               return var1.field9 != null ? var1.field9.apply((T)var2) : var2.toString();
            }
         };
      }

      @Contract("->this")
      public B method11() {
         this.field14 = true;
         return (B)this;
      }

      @Contract("_->this")
      public B method5(@Nullable Consumer<T> var1) {
         this.field15 = var1;
         return (B)this;
      }

      @Contract("_->this")
      public B method6(@Nullable Function<T, String> var1) {
         this.field16 = var1;
         return (B)this;
      }

      @Contract("_->this")
      public B method7(@Nullable Codec<T> var1) {
         this.elementCodec = var1;
         return (B)this;
      }

      protected ListOption<T> method12() {
         return new ListOption<>(
            this.ROICCCHOIIOHIIOHIRIHHCIIRRCHCC,
            this.codec,
            this.defaultValue == null ? new ArrayList<>() : this.defaultValue,
            this.field14,
            this.field15,
            this.field16,
            this.elementCodec
         );
      }
   }
}
