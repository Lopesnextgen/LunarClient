package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.Nameplate4Impl22;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Framework10 {
   @Nullable
   ResourceLocationBridge method1();

   @Nullable
   ResourceLocationBridge method2();

   boolean method3();

   void method4(boolean var1);

   long getLastModified();

   void setLastModified(long var1);

   boolean method6();

   void method7(boolean var1);

   void method8(MixinHelper_4 var1, float var2, float var3, float var4, float var5, Data2 var6, boolean var7);

   static Framework10 method9(@Nullable ResourceLocationBridge var0, @Nullable ResourceLocationBridge var1, @NotNull Supplier<String> var2, boolean var3, long var4, boolean var6) {
      return new Nameplate4Impl22(var0, var1, var2, var3, var4, var6);
   }
}
