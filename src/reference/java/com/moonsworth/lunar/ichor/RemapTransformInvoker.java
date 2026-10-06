package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.files.Files5_2;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.moonsworth.lunar.genesis.MixinHelper8_4;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.MappingsWriter;
import org.objectweb.asm.tree.ClassNode;

public class RemapTransformInvoker extends MixinHelper<Annotation8> {
   private LoadingCache<RemapTransformInvoker.Data31, RemapperIterator2> field4;
   private MappingSet field5;
   private final AtomicBoolean field6 = new AtomicBoolean(false);
   private final AtomicBoolean field7 = new AtomicBoolean(false);
   private Function<String, Boolean> field8 = null;

   public RemapTransformInvoker(Annotation8 var1, AutoCloseableIterator var2, Method var3) {
      super(var1, var2, var3);
      if (!var1.method5()) {
         this.method1(var2.method11(), null);
      }
   }

   private synchronized void method1(IchorPipeline var1, @Nullable URLClassLoader var2) {
      try {
         if (!this.field6.getAcquire() && !this.field7.getAcquire()) {
            long var3 = System.currentTimeMillis();
            String var5 = this.field3.getDeclaringClass().getName() + "." + this.field3.getName();
            Path var6 = var1.method39();
            Object var7 = null;
            RemapTransformInvoker.Extension var8 = null;
            if (!this.field6.getAcquire()) {
               Object var9 = null;
               if (((Annotation8)this.field1).method5() && this.field3.getParameterTypes().length > 0) {
                  var9 = this.field3.invoke(this.field2.method6(), var2);
               } else {
                  var9 = this.field3.invoke(this.field2.method6());
               }

               if (var9 == null) {
                  this.field7.setRelease(true);
                  return;
               }

               long var10 = System.currentTimeMillis() - var3;
               if (var10 > 200L) {
                  IchorPipeline.field3.info("Built mapping set for " + var5 + " " + var10 + "ms on thread " + Thread.currentThread().getName());
               }

               if (var9 instanceof RemapTransformInvoker.RemapTransformResult var13) {
                  this.field8 = var13.field2;
                  this.method2(var13.field1);
                  if (var7 != null) {
                     MappingsWriter var15 = Files5_2.field1.createWriter(new FileOutputStream(var7.toFile()));

                     try {
                        var15.write(var13.field1);
                     } catch (Throwable var21) {
                        if (var15 != null) {
                           try {
                              var15.close();
                           } catch (Throwable var19) {
                              var21.addSuppressed(var19);
                           }
                        }

                        throw var21;
                     }

                     if (var15 != null) {
                        var15.close();
                     }
                  }

                  var8 = var13.field3;
               } else if (var9 instanceof MappingSet var14) {
                  this.method2(var14);
                  if (var7 != null) {
                     MappingsWriter var26 = Files5_2.field1.createWriter(new FileOutputStream(var7.toFile()));

                     try {
                        var26.write(var14);
                     } catch (Throwable var20) {
                        if (var26 != null) {
                           try {
                              var26.close();
                           } catch (Throwable var18) {
                              var20.addSuppressed(var18);
                           }
                        }

                        throw var20;
                     }

                     if (var26 != null) {
                        var26.close();
                     }
                  }
               } else {
                  if (!(var9 instanceof MixinMisc2 var12)) {
                     throw new IllegalStateException("RemapNectarHandler(" + this.field3.getName() + ") didn't return a MappingSet!");
                  }

                  this.field2.method9().add(var12);
               }
            }

            final RemapTransformInvoker.Extension var24 = var8;
            MixinHelper8_4 var25 = new MixinHelper8_4<RemapTransformInvoker.Data31, RemapperIterator2>() {
               public RemapperIterator2 method1(RemapTransformInvoker.Data31 var1) {
                  Object var2x;
                  if (((Annotation8)RemapTransformInvoker.this.field1).method2()) {
                     var2x = new InheritanceProvider2(var1.field2, RemapTransformInvoker.this.field5, var24);
                  } else {
                     var2x = var1.field2;
                  }

                  return new RemapperIterator2(
                     RemapTransformInvoker.this.field2,
                     RemapTransformInvoker.this.field5,
                     (org.cadixdev.bombe.analysis.InheritanceProvider)var2x,
                     ((Annotation8)RemapTransformInvoker.this.field1).method3(),
                     ((Annotation8)RemapTransformInvoker.this.field1).method4()
                  );
               }
            };
            this.field4 = CacheBuilder.method1().method11(20L).method19().method23(10L, TimeUnit.SECONDS).method34(var25);
            this.field6.setRelease(true);
         }
      } catch (Throwable var22) {
         throw var22;
      }
   }

   private synchronized void method2(MappingSet var1) {
      String var2 = this.field3.getDeclaringClass().getName() + "." + this.field3.getName();
      if (var1.getTopLevelClassMappings().isEmpty()) {
         String var3 = "MappingSet for " + var2 + " has 0 top level class mappings.";
         if (!((Annotation8)this.field1).optional()) {
            throw new FatalIchorError(var3);
         }

         IchorPipeline.field3.warn(var3);
      }

      if (var1.getTopLevelClassMappings().stream().flatMap(var0 -> var0.getFieldMappings().stream()).findFirst().isEmpty()) {
         IchorPipeline.field3.warn("MappingSet for " + var2 + " has 0 field mappings.");
      }

      if (var1.getTopLevelClassMappings().stream().flatMap(var0 -> var0.getMethodMappings().stream()).findFirst().isEmpty()) {
         IchorPipeline.field3.warn("MappingSet for " + var2 + " has 0 method mappings.");
      }

      this.field5 = var1;
      if (((Annotation8)this.field1).method6()) {
         this.field2.method10().add(this.field5);
         this.field2.method9().add(new MixinMisc2Handler(this.field5));
      }
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      if (this.field5 == null && !((Annotation8)this.field1).method5()) {
         return false;
      }

      IchorPipeline var2 = var1.method6().method1();
      if (!this.field6.getAcquire()) {
         this.method1(var2, var1.method6());
      }

      if (this.field7.getAcquire()) {
         return false;
      }

      if (this.field8 != null && !this.field8.apply(var1.className())) {
         return false;
      }

      URLClassLoader var3 = var1.method6();

      try {
         RemapperIterator2 var4 = this.field4.get(new RemapTransformInvoker.Data31(Thread.currentThread(), var3));
         ClassNode var5 = var1.method3(this.field3.getDeclaringClass());
         ClassNodeRemapper.method1(var2, var5, var4);
         if (((Annotation8)this.field1).method1()) {
            MixinTargetRemapper.method1(this.field2, var3, var5, this.field5, var4);
         }

         return true;
      } catch (Throwable var6) {
         throw new FatalIchorError("Failed to remap " + var1.className(), var6);
      }
   }

   public class RemapTransformResult {
      private final MappingSet field1;
      private final Function<String, Boolean> field2;
      private final RemapTransformInvoker.Extension field3;

      public RemapTransformResult(MappingSet var1, Function<String, Boolean> var2) {
         this(var1, var2, null);
      }

      public RemapTransformResult(MappingSet var1, Function<String, Boolean> var2, RemapTransformInvoker.Extension var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public MappingSet method1() {
         return this.field1;
      }

      public Function<String, Boolean> method2() {
         return this.field2;
      }

      public RemapTransformInvoker.Extension method3() {
         return this.field3;
      }
   }

   public class Data31 {
      private final Thread field1;
      private final URLClassLoader field2;

      public Data31(Thread var1, URLClassLoader var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public Thread method1() {
         return this.field1;
      }

      public URLClassLoader method2() {
         return this.field2;
      }
   }

   @FunctionalInterface
   public interface Extension {
      @Nullable
      ClassInfo fix(org.cadixdev.bombe.analysis.InheritanceProvider var1, String var2, @Nullable ClassInfo var3);
   }
}
