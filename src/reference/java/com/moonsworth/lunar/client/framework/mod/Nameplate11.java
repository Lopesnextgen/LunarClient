package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModSupport;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class Nameplate11 implements ModSupport {
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
   public boolean method3(KeystrokesType var1) {
      return this.field2 == var1;
   }

   public static Nameplate11 method4(boolean flag, @Nullable KeystrokesType var1) {
      return new Nameplate11(flag, var1);
   }

   @Generated
   public Nameplate11(boolean var1, @Nullable KeystrokesType var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Generated
   @Override
   public boolean method4() {
      return this.field3;
   }

   @Generated
   @Override
   public void setFlipped(boolean var1) {
      this.field3 = var1;
   }
}
