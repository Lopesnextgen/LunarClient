package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModDisplayData;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ModDisplay {
   @Nullable
   ResourceLocationBridge method1();

   @Nullable
   ResourceLocationBridge method2();

   boolean method3();

   void method4(boolean flag1);

   long getLastModified();

   void setLastModified(long number1);

   boolean method6();

   void method7(boolean flag1);

   void method8(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, Data2 data26, boolean flag7);

   static ModDisplay method9(@Nullable ResourceLocationBridge horsestats140, @Nullable ResourceLocationBridge horsestats141, @NotNull Supplier<String> supplier2, boolean flag, long value, boolean flag2) {
      return new ModDisplayData(horsestats140, horsestats141, supplier2, flag, value, flag2);
   }
}
