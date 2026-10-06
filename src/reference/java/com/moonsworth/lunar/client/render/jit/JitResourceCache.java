package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.client.util.ThreadModuleDump24;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import lombok.Generated;

@ThreadSafe
public class JitResourceCache {
   private final ThreadModuleDump24<JitAssetKey, JitResource<?>> field1;

   public JitResourceCache(JitCacheSettings var1) {
      this.field1 = new ThreadModuleDump24(var0 -> (long)var0.method11(), this::method1, var1.method2().toNanos(), var1.method3().toNanos(), var1.method4());
   }

   private void method1(JitAssetKey var1, @Nullable JitResource<?> var2) {
      if (var2 != null) {
         var2.cleanUp();
      }
   }

   @Nullable
   public <T, U extends JitResource<T>> Optional<U> method2(JitAssetKey var1) {
      return (Optional<U>)Optional.ofNullable((JitResource)this.field1.get(var1));
   }

   public void method3(JitAssetKey var1, JitResource<?> var2) {
      this.field1.put(var1, var2);
   }

   public void method4(JitAssetKey var1) {
      this.field1.get(var1);
   }

   public void tick() {
      this.field1.method6(var0 -> {
         if (var0.method10().method2()) {
            var0.tick();
         }
      });
   }

   public void clear() {
      this.field1.clear();
   }

   public void method5(JitAssetKey var1) {
      this.field1.remove(var1);
   }

   public void method6(List<String> var1) {
      var1.add("");
      this.method7((var1x, var2) -> var1.add(var1x + var2));
   }

   public void method7(BiConsumer<String, String> var1) {
      var1.accept("[LC JIT] Cache Size: ", this.field1.method7() + "");
      var1.accept("[LC JIT] Video Memory Used: ", this.field1.method3() + " bytes");
   }

   @Generated
   public JitResourceCache(ThreadModuleDump24<JitAssetKey, JitResource<?>> var1) {
      this.field1 = var1;
   }
}
