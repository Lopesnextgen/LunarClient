package com.moonsworth.lunar.ichor;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.ichor.util.FatalIchorError2;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import lombok.Generated;
import org.cadixdev.lorenz.MappingSet;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class AutoCloseableIterator implements AutoCloseable {
   private static final Map<String, Pattern> field1 = new ConcurrentHashMap<>();
   private final IchorInjection field2;
   private final List<MixinHelper<?>> field3;
   private final List<MixinHelper7> field4;
   private final List<MixinMisc2> field5;
   private final List<MappingSet> field6;
   private final IchorPipeline field7;

   public AutoCloseableIterator(IchorInjection var1, IchorPipeline var2) {
      this.field2 = var1;
      this.field5 = new ArrayList<>();
      this.field6 = new ArrayList<>();
      this.field7 = var2;
      var2.method32().put(var1, this);
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      this.method1(Annotation7.class, ClassTransformInvoker::new, var3, var4);
      this.method1(Annotation11.class, FieldTransformInvoker::new, var3, var4);
      this.method1(Annotation.class, MethodTransformInvoker::new, var3, var4);
      this.method1(Annotation8.class, RemapTransformInvoker::new, var3, var4);
      this.method1(Annotation4.class, AnnotationTransformInvoker::new, var3, var4);
      this.method1(Annotation10.class, MixinHelper7::new, var3, var4);
      this.method1(Annotation5.class, MixinHelper2_3::new, var3, var4);
      this.field3 = this.method2(var3);
      this.field4 = ImmutableList.method15(var4);
   }

   private <Nectar extends java.lang.annotation.Annotation> void method1(
      Class<Nectar> var1,
      FatalIchorError2<Nectar, AutoCloseableIterator, Method, MixinHelper<Nectar>> var2,
      ArrayList<MixinHelper<?>> var3,
      ArrayList<MixinHelper7> var4
   ) {
      ArrayList var5 = new ArrayList();
      ArrayList var6 = new ArrayList();
      var6.add(this.field2.getClass());

      while (!var6.isEmpty()) {
         for (Class var8 : List.copyOf(var6)) {
            if (var8 == this.field2.getClass() || var8.getAnnotation(Annotation3.class) != null) {
               var5.add(var8);
            }

            var6.remove(var8);
            if (var8.getSuperclass() != null) {
               var6.add(var8.getSuperclass());
            }

            Class[] var9 = var8.getInterfaces();
            var6.addAll(Arrays.asList(var9));
         }
      }

      Collections.reverse(var5);

      for (Class var16 : var5) {
         for (Method var12 : var16.getDeclaredMethods()) {
            java.lang.annotation.Annotation var13 = var12.getAnnotation(var1);
            if (var13 != null) {
               MixinHelper var14 = (MixinHelper)var2.apply(var13, this, var12);
               if (var1.equals(Annotation10.class)) {
                  var4.add((MixinHelper7)var14);
               } else {
                  var3.add(var14);
               }
            }
         }
      }
   }

   private ImmutableList<MixinHelper<?>> method2(ArrayList<MixinHelper<?>> var1) {
      Class var2 = this.field2.getClass();
      ArrayList var3 = new ArrayList();
      ArrayList var4 = new ArrayList();
      var3.add(var2.getName().replace('.', '/'));

      try {
         while (!var3.isEmpty()) {
            String var5 = (String)var3.remove(0);

            try (InputStream var6 = var2.getClassLoader().getResourceAsStream(var5 + ".class")) {
               if (var6 == null) {
                  throw new ClassNotFoundException();
               }

               ClassReader var7 = new ClassReader(var6);
               ClassNode var8 = new ClassNode();
               var7.accept(var8, 0);
               var4.add(var8);
               if (var8.superName != null && !"java/lang/Object".equals(var8.superName)) {
                  var3.add(var8.superName);
               }
            }
         }

         HashMap var14 = new HashMap();
         int var15 = 0;
         Collections.reverse(var4);

         for (ClassNode var17 : var4) {
            for (MethodNode var10 : var17.methods) {
               var14.put(var10.name, var15++);
            }
         }

         return ImmutableList.method19(Comparator.comparingInt(var1x -> var14.getOrDefault(var1x.getMethodName(), Integer.MAX_VALUE)), var1);
      } catch (Exception var13) {
         throw new RuntimeException(var13);
      }
   }

   public void method3(IchorTransformer.Data3 var1) {
      if (!var1.className().startsWith("com/moonsworth/lunar/ichor/") && this.field2.method1(var1)) {
         ClassLoader var2 = Thread.currentThread().getContextClassLoader();
         Thread.currentThread().setContextClassLoader(var1.method6());

         for (MixinHelper var4 : this.field3) {
            try {
               long var5 = System.currentTimeMillis();
               String var12 = var4.toString();
               boolean var13 = var4.method1(var1);
               long var9 = System.currentTimeMillis() - var5;
               if (IchorPipeline.field1) {
                  this.field7.method40().method1().compute(var12, (var2x, var3) -> var3 == null ? var9 : var3 + var9);
                  if (var13) {
                     this.field7.method40().method2().compute(var12, (var0, var1x) -> var1x == null ? 1L : var1x + 1L);
                  }
               }
            } catch (NoClassDefFoundError | Exception var11) {
               if (var11.getCause() instanceof Error var7) {
                  throw var7;
               }

               IllegalStateException var8 = new IllegalStateException("IchorContainer.inject: Failed to inject into ClassNode " + var1.className(), var11);
               this.field7.method38().method2(var8);
               throw var8;
            }
         }

         Thread.currentThread().setContextClassLoader(var2);
      }
   }

   public void method4(MixinShared var1, URLClassLoader var2) {
      ClassLoader var3 = Thread.currentThread().getContextClassLoader();
      Thread.currentThread().setContextClassLoader(var2);

      for (MixinHelper7 var5 : this.field4) {
         try {
            var5.method2(var1, var2);
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

      Thread.currentThread().setContextClassLoader(var3);
   }

   public static Pattern method5(String var0) {
      return field1.computeIfAbsent(var0, Pattern::compile);
   }

   @Override
   public String toString() {
      return this.method6().getClass().getSimpleName();
   }

   @Override
   public void close() {
      if (this.field2 instanceof AutoCloseable var1) {
         var1.close();
      }

      this.field5.clear();
      this.field6.clear();
   }

   @Generated
   public IchorInjection method6() {
      return this.field2;
   }

   @Generated
   public List<MixinHelper<?>> method7() {
      return this.field3;
   }

   @Generated
   public List<MixinHelper7> method8() {
      return this.field4;
   }

   @Generated
   public List<MixinMisc2> method9() {
      return this.field5;
   }

   @Generated
   public List<MappingSet> method10() {
      return this.field6;
   }

   @Generated
   public IchorPipeline method11() {
      return this.field7;
   }
}
