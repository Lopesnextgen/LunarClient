package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModSupport;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class KeystrokeSupport implements ModSupport {
   private final boolean field1;
   @Nullable
   private final KeystrokesType field2;
   private boolean field3;

   @Override
   public boolean method1() {
      return this.field1;
   }

   @Override
   public boolean method2() {
      return this.field2 != null;
   }

   @Override
   public boolean method3(KeystrokesType keystrokestype1) {
      return this.field2 == keystrokestype1;
   }

   public static KeystrokeSupport method4(boolean flag, @Nullable KeystrokesType keystrokestype1) {
      return new KeystrokeSupport(flag, keystrokestype1);
   }

   @Generated
   public KeystrokeSupport(boolean flag1, @Nullable KeystrokesType keystrokestype2) {
      this.field1 = flag1;
      this.field2 = keystrokestype2;
   }

   @Generated
   @Override
   public boolean method4() {
      return this.field3;
   }

   @Generated
   @Override
   public void setFlipped(boolean flag1) {
      this.field3 = flag1;
   }
}
