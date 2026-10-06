package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.Arrays;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class AutoCloseableImpl implements AutoCloseable {
   private final boolean field1;
   private final ResourceLocationBridge field2;
   @Nullable
   private final Bridge8Extension34 field3;
   private Bridge8Extension3 @Nullable [] field4;

   public void cleanUp() {
      Bridge.method9().bridge$getTextureManager().bridge$deleteTexture(this.field2);
   }

   @Override
   public void close() {
      if (this.field4 != null) {
         for (Bridge8Extension3 var4 : this.field4) {
            if (var4 != null) {
               var4.method1();
            }
         }

         Arrays.fill(this.field4, null);
         this.field4 = null;
      }
   }

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Generated
   public ResourceLocationBridge method2() {
      return this.field2;
   }

   @Nullable
   @Generated
   public Bridge8Extension34 method3() {
      return this.field3;
   }

   @Generated
   public Bridge8Extension3 @Nullable [] method4() {
      return this.field4;
   }

   @Generated
   public AutoCloseableImpl(boolean flag, ResourceLocationBridge resourceLocationBridge, @Nullable Bridge8Extension34 var3, Bridge8Extension3 @Nullable [] var4) {
      this.field1 = flag;
      this.field2 = resourceLocationBridge;
      this.field3 = var3;
      this.field4 = var4;
   }
}
