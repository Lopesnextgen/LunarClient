package com.moonsworth.lunar.client.framework.mod;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.Framework10;
import com.moonsworth.lunar.client.config.option.JsonPersistable;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.function.Supplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Nameplate4Impl22 implements Framework10, JsonPersistable {
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
   public void method8(MixinHelper_4 var1, float var2, float var3, float var4, float var5, Data2 var6, boolean var7) {
      this.method2(var1, this.field3.get(), var2, var3, var4, var5);
   }

   protected void method2(MixinHelper_4 var1, String var2, float var3, float var4, float var5, float var6) {
      float var7 = ThreadModuleDump63.method10().bridge$getStringWidth(var2);
      float var8 = var3 + var5 / 2.0F - var7 / 2.0F;
      var1.method19(ThreadModuleDump63.method10(), var2, var8, var4 + var6 / 2.0F, -1, true);
   }

   public void load(JsonObject var1) {
      if (var1.has("seen")) {
         this.method4(var1.get("seen").getAsBoolean());
      }

      if (var1.has("lastModified")) {
         this.setLastModified(var1.get("lastModified").getAsLong());
      }

      if (var1.has("favorite")) {
         this.method7(var1.get("favorite").getAsBoolean());
      }
   }

   public void method1(JsonObject var1) {
      if (this.method3()) {
         var1.addProperty("seen", true);
      }

      if (this.getLastModified() != 0L) {
         var1.addProperty("lastModified", this.getLastModified());
      }

      if (this.method6()) {
         var1.addProperty("favorite", true);
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
   public Nameplate4Impl22(@Nullable ResourceLocationBridge var1, @Nullable ResourceLocationBridge var2, @NotNull Supplier<String> var3, boolean var4, long var5, boolean var7) {
      if (var3 == null) {
         throw new NullPointerException("thumbnailSupplier is marked non-null but is null");
      }

      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.lastModified = var5;
      this.field5 = var7;
   }

   @Generated
   @Override
   public void method4(boolean var1) {
      this.field4 = var1;
   }

   @Generated
   @Override
   public void setLastModified(long var1) {
      this.lastModified = var1;
   }

   @Generated
   @Override
   public void method7(boolean var1) {
      this.field5 = var1;
   }
}
