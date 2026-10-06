package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.driver.PhosphorIcon.Type;
import com.moonsworth.lunar.client.driver.DriverFieldType;
import com.moonsworth.lunar.client.config.option.DirtyFlag;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class DisplaySpec implements OptionDisplay {
   protected final @Nullable DriverFieldType field1;
   protected final @Nullable Supplier<String> field2;
   protected final @Nullable DriverFieldType field3;
   protected final @Nullable BooleanSupplier field4;
   protected final DisplaySpec.@Nullable Data field5;
   protected final boolean field6;
   protected boolean field7;

   DisplaySpec(
      @Nullable DriverFieldType markerstype1,
      @Nullable Supplier<String> supplier2,
      @Nullable DriverFieldType markerstype3,
      @Nullable BooleanSupplier booleansupplier4,
      DisplaySpec.@Nullable Data data5,
      boolean flag6,
      boolean flag7
   ) {
      if (markerstype3 != null && markerstype1 != DriverFieldType.CATEGORY && markerstype3.getType() != Type.STROKE) {
         throw new IllegalArgumentException("Option icons must be of type 'stroke'");
      }

      this.field1 = markerstype1;
      this.field2 = supplier2;
      this.field3 = markerstype3;
      this.field4 = booleansupplier4;
      this.field5 = data5;
      this.field6 = flag6;
      this.field7 = flag7;
   }

   DisplaySpec(
      @Nullable DriverFieldType markerstype1,
      @Nullable Supplier<String> supplier2,
      @Nullable DriverFieldType markerstype3,
      @Nullable BooleanSupplier booleansupplier4,
      DisplaySpec.@Nullable Data data5,
      boolean flag6
   ) {
      this(markerstype1, supplier2, markerstype3, booleansupplier4, data5, flag6, booleansupplier4 != null);
   }

   @Override
   public @Nullable String method2() {
      return this.field2 == null ? null : this.field2.get();
   }

   @Override
   public @Nullable DriverFieldType icon() {
      return this.field3;
   }

   @Override
   public @Nullable Runnable method3() {
      return this.field5 == null ? null : this.field5.method1();
   }

   @Override
   public boolean method4(ClientOption<?> lightingextension1) {
      return this.method4(lightingextension1, true);
   }

   public boolean method4(ClientOption<?> lightingextension1, boolean flag2) {
      if (this.field4 != null) {
         boolean flag3 = this.field4.getAsBoolean();
         if (this.field7 != flag3) {
            this.field7 = flag3;
            if (flag2) {
               DirtyFlag.method1();
            }
         }

         return flag3 || lightingextension1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).flatMap(SettingIntercept::method3).isPresent();
      } else {
         return lightingextension1.CCHOHROIOHHCCCOCIIOIRCHRROHHRC(OptionTraits.field5).flatMap(SettingIntercept::method3).isPresent();
      }
   }

   @Override
   public void method6(ClientOption<?> lightingextension1, JsonObject json2) {
      if (this.field2 != null) {
         json2.addProperty("name", this.field2.get());
      }

      if (this.field1 != null) {
         json2.addProperty("type", this.field1.getId());
      }

      json2.addProperty("isHidden", this.method4(lightingextension1, false));
      if (this.field3 != null) {
         json2.addProperty("icon", this.field3.ordinal());
      }

      if (this.field5 != null) {
         JsonObject json3 = new JsonObject();
         if (this.field5.icon() != null) {
            json3.addProperty("icon", this.field5.icon().ordinal());
         }

         json3.addProperty("text", this.field5.text());
         json2.add("actionButton", json3);
      }
   }

   @Override
   public OptionDisplay method7(BooleanSupplier booleansupplier1) {
      BooleanSupplier booleansupplier2;
      if (this.field4 != null) {
         booleansupplier2 = () -> booleansupplier1.getAsBoolean() || this.field4.getAsBoolean();
      } else {
         booleansupplier2 = booleansupplier1;
      }

      return new DisplaySpec(this.field1, this.field2, this.field3, booleansupplier2, this.field5, this.field6);
   }

   @Generated
   @Override
   public @Nullable DriverFieldType method1() {
      return this.field1;
   }

   @Generated
   @Override
   public boolean method5() {
      return this.field6;
   }

   protected class Data {
      private final @Nullable DriverFieldType field1;
      private final String field2;
      private final Runnable field3;

      protected Data(@Nullable DriverFieldType markerstype1, String text2, Runnable runnable3) {
         this.field1 = markerstype1;
         this.field2 = text2;
         this.field3 = runnable3;
      }

      public @Nullable DriverFieldType icon() {
         return this.field1;
      }

      public String text() {
         return this.field2;
      }

      public Runnable method1() {
         return this.field3;
      }
   }
}
