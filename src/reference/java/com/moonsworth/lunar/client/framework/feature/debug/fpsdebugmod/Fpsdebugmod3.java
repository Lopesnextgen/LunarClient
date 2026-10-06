package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.files.Files6_2;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class Fpsdebugmod3 {
   private final List<Fpsdebugmod2> field1 = new ArrayList<>();

   public void clear() {
      this.field1.clear();
   }

   public Fpsdebugmod3 method1(Fpsdebugmod2 var1) {
      this.field1.add(var1);
      return this;
   }

   public Fpsdebugmod3 method2(List<Fpsdebugmod2> var1) {
      this.field1.addAll(var1);
      return this;
   }

   public Duration method3() {
      return Arrays.stream(FpsdebugmodType.values()).map(this::method4).reduce(Duration.ZERO, Duration::plus);
   }

   private Duration method4(FpsdebugmodType var1) {
      Duration var2 = this.method6(var1).map(Fpsdebugmod2::method2).reduce(null, Fpsdebugmod3::method5);
      return var2 == null ? Duration.ZERO : var2;
   }

   private static Duration method5(Duration var0, Duration var1) {
      if (var0 == null) {
         return var1;
      } else if (var1 == null) {
         return var0;
      } else {
         return var0.minus(var1).isNegative() ? var1 : var0;
      }
   }

   private Stream<Fpsdebugmod2> method6(FpsdebugmodType var1) {
      return this.method7().filter(var1x -> var1x.method3() == var1);
   }

   private Stream<Fpsdebugmod2> method7() {
      return this.field1.stream().filter(Fpsdebugmod2::method1);
   }

   public Future<Fpsdebugmod> method8() {
      return CompletableFuture.supplyAsync(() -> Arrays.stream(FpsdebugmodType.values()).map(var1 -> {
         try {
            return this.method9(var1).get();
         } catch (InterruptedException | ExecutionException var3) {
            return new Fpsdebugmod().method4("Failed to collect data for phase " + var1, var3);
         }
      }).reduce(new Fpsdebugmod(), Fpsdebugmod::method3));
   }

   private Future<Fpsdebugmod> method9(FpsdebugmodType var1) {
      return CompletableFuture.supplyAsync(() -> this.method6(var1).map(var0 -> Files6_2.method1(var0, var0.method4())).toList().stream().map(var0 -> {
         try {
            return (Fpsdebugmod)((Future)var0.field2).get();
         } catch (InterruptedException | ExecutionException var2) {
            return new Fpsdebugmod().method4("Failed to collect data for " + ((Fpsdebugmod2)var0.field1).name(), var2);
         }
      }).reduce(new Fpsdebugmod(), Fpsdebugmod::method3));
   }
}
