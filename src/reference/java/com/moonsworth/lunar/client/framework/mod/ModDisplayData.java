package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModDisplay;
import com.moonsworth.lunar.client.config.option.JsonConfigurable;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModDisplayData implements ModDisplay, JsonConfigurable {
   @Nullable
   private final ResourceLocationBridge field1;
   @Nullable
   private final ResourceLocationBridge field2;
   @NotNull
   private final Supplier<String> field3;
   private boolean field4;
   private long lastModified;
   private boolean field5;

   @Override
   public void method8(MixinHelper_4 mixinhelper_41, float value, float value3, float value4, float value5, Data2 data, boolean flag7) {
      this.method2(mixinhelper_41, this.field3.get(), value, value3, value4, value5);
   }

   protected void method2(MixinHelper_4 mixinhelper_41, String text, float value3, float value4, float value5, float value) {
      float value7 = Ref.method10().bridge$getStringWidth(text);
      float value8 = value3 + value5 / 2.0F - value7 / 2.0F;
      mixinhelper_41.method19(Ref.method10(), text, value8, value4 + value / 2.0F, -1, true);
   }

   public void load(JsonObject json1) {
      if (json1.has("seen")) {
         this.method4(json1.get("seen").getAsBoolean());
      }

      if (json1.has("lastModified")) {
         this.setLastModified(json1.get("lastModified").getAsLong());
      }

      if (json1.has("favorite")) {
         this.method7(json1.get("favorite").getAsBoolean());
      }
   }

   public void method1(JsonObject json1) {
      if (this.method3()) {
         json1.addProperty("seen", true);
      }

      if (this.getLastModified() != 0L) {
         json1.addProperty("lastModified", this.getLastModified());
      }

      if (this.method6()) {
         json1.addProperty("favorite", true);
      }
   }

   @Nullable
   @Generated
   @Override
   public ResourceLocationBridge method1() {
      return this.field1;
   }

   @Nullable
   @Generated
   @Override
   public ResourceLocationBridge method2() {
      return this.field2;
   }

   @NotNull
   @Generated
   public Supplier<String> method7() {
      return this.field3;
   }

   @Generated
   @Override
   public boolean method3() {
      return this.field4;
   }

   @Generated
   @Override
   public long getLastModified() {
      return this.lastModified;
   }

   @Generated
   @Override
   public boolean method6() {
      return this.field5;
   }

   @Generated
   public ModDisplayData(@Nullable ResourceLocationBridge horsestats141, @Nullable ResourceLocationBridge horsestats142, @NotNull Supplier<String> supplier3, boolean flag, long value, boolean flag7) {
      if (supplier3 == null) {
         throw new NullPointerException("thumbnailSupplier is marked non-null but is null");
      }

      this.field1 = horsestats141;
      this.field2 = horsestats142;
      this.field3 = supplier3;
      this.field4 = flag;
      this.lastModified = value;
      this.field5 = flag7;
   }

   @Generated
   @Override
   public void method4(boolean flag1) {
      this.field4 = flag1;
   }

   @Generated
   @Override
   public void setLastModified(long value) {
      this.lastModified = value;
   }

   @Generated
   @Override
   public void method7(boolean flag1) {
      this.field5 = flag1;
   }
}
