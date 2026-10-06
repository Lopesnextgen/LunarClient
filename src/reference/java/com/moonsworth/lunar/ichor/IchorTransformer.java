package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.ClassVisitorImpl;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.ichor.util.FatalIchorError13;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.ichor.util.FatalIchorError8;
import com.moonsworth.lunar.ichor.util.LinkedHashMapImpl;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lombok.Generated;
import org.cadixdev.lorenz.MappingSet;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.CheckClassAdapter;

public class IchorTransformer implements AutoCloseable {
   private static final int field1 = 250;
   private static final boolean field2 = Boolean.parseBoolean(System.getProperty("ichor.failOnMixinError", "true"));
   private static final String[] field3 = Arrays.stream(System.getProperty("ichor.dumpClasses", "").split(";"))
      .map(var0 -> var0.replace('.', '/'))
      .toArray(String[]::new);
   private static final boolean field4 = Boolean.parseBoolean(System.getProperty("ichor.markClasses", "false"));
   private static final boolean field5 = Boolean.parseBoolean(System.getProperty("ichor.testWriteNodesToBytes", "false"));
   private final IchorPipeline field6;
   private final Map<Ichor4, List<AutoCloseableIterator>> field7;
   private final Map<IchorTransformer.Data, URLClassLoader> field8;
   private final Map<IchorTransformer.Data2, WeakReference<MixinShared>> field9;
   final Set<Ichor4> field10 = Collections.newSetFromMap(new ConcurrentHashMap<>(5, 1.0F));
   final AtomicBoolean field11 = new AtomicBoolean(false);
   private final List<MixinInternal3> field12;

   public IchorTransformer(IchorPipeline var1) {
      this.field6 = var1;
      this.field7 = new ConcurrentHashMap<>(this.field6.method33().size());
      this.field8 = new ConcurrentHashMap<>();
      this.field9 = Collections.synchronizedMap(new LinkedHashMapImpl<>(250));

      for (Ichor4 var3 : this.field6.method33()) {
         this.field7.put(var3, new CopyOnWriteArrayList<>());
      }

      this.field12 = new ArrayList<>();

      try {
         Path var9 = Paths.get("./.ichor/dump");
         if (Files.exists(var9)) {
            try (Stream var10 = Files.walk(var9)) {
               var10.forEach(var0 -> {
                  try {
                     Files.deleteIfExists(var0);
                  } catch (IOException var2) {
                  }
               });
            }
         }
      } catch (IOException var8) {
      }
   }

   public AutoCloseableIterator method1(IchorInjection var1) {
      this.field6.method37().add(var1.getClass().getName());
      AutoCloseableIterator var2 = new AutoCloseableIterator(var1, this.field6);

      for (Ichor4 var6 : var1.method2()) {
         List var7 = this.field7.get(var6);
         if (var7 != null) {
            var7.add(var2);
         }
      }

      return var2;
   }

   public void method2(ClassLoader var1) {
      if (!this.field11.get()) {
         this.method3(var1);
      }
   }

   private synchronized void method3(ClassLoader var1) {
      if (!this.field11.getAndSet(true)) {
         long var2 = System.currentTimeMillis();
         long var4 = System.currentTimeMillis();

         for (Ichor4 var7 : this.field6.method33()) {
            if (var7.hasMixinRuntime()) {
               IchorTransformer.Data var8 = new IchorTransformer.Data(var7, var1);
               URLClassLoader var9 = this.field8.computeIfAbsent(var8, var3 -> new URLClassLoader(this, var7, var1));
               IchorPipeline.field3.info("Loading Mixin runtime for " + var7);
               var9.method2();
            }
         }

         long var13 = System.currentTimeMillis() - var4;
         IchorPipeline.field3.info("Done loading Mixin runtimes in " + var13 + "ms");
         var4 = System.currentTimeMillis();

         for (Ichor4 var18 : this.field6.method33()) {
            if (var18.hasMixinRuntime()) {
               IchorTransformer.Data var20 = new IchorTransformer.Data(var18, var1);
               URLClassLoader var22 = this.field8.get(var20);
               IchorPipeline.field3.info("Setting up mixins for " + var18);
               if (var18.name().equals("MIXIN") && this.field6.hasModule("fabric")) {
                  this.triggerFabricPreLaunch();
               }

               var22.method3();
            }
         }

         var13 = System.currentTimeMillis() - var4;
         IchorPipeline.field3.info("Done registering Mixins in " + var13 + "ms");
         var4 = System.currentTimeMillis();

         for (Ichor4 var19 : this.field6.method33()) {
            if (var19.hasMixinRuntime()) {
               IchorTransformer.Data var21 = new IchorTransformer.Data(var19, var1);
               URLClassLoader var23 = this.field8.get(var21);
               IchorPipeline.field3.info("Going to default mixins stage for " + var19);
               var23.method20().gotoDefaultPhase();
               this.field10.add(var19);
               MixinSupport.method2(var23);
            }
         }

         var13 = System.currentTimeMillis() - var4;
         IchorPipeline.field3.info("Mixin runtimes prepared in " + var13 + "ms");
         var4 = System.currentTimeMillis() - var2;
         IchorPipeline.field3.info("Initialized Mixin in " + var4 + "ms");
      }
   }

   private void triggerFabricPreLaunch() {
      try {
         Class var1 = Class.forName("net.fabricmc.loader.impl.FabricLoaderImpl");
         Object var2 = var1.getDeclaredField("INSTANCE").get(null);
         var2.getClass().getDeclaredMethod("triggerFabricPreLaunch").invoke(var2);
      } catch (Throwable var3) {
         throw var3;
      }
   }

   @Override
   public void close() {
      for (URLClassLoader var2 : this.field8.values()) {
         var2.close();
      }

      for (List var6 : this.field7.values()) {
         for (AutoCloseableIterator var4 : var6) {
            var4.close();
         }

         var6.clear();
      }

      this.field7.clear();
      this.field8.clear();
      this.field9.clear();
      this.field12.clear();
   }

   public FatalIchorError8 method4(Ichor4[] var1, ClassLoader var2, boolean var3, String var4, @Nullable FatalIchorError8 var5) {
      try {
         String var6 = var4;

         for (int var7 = var1.length - 1; var7 >= 0; var7--) {
            var6 = this.method12(var1[var7], var6);
         }

         String var21 = var6;

         for (Ichor4 var11 : var1) {
            var21 = this.method11(var11, var21);
            var5 = this.method5(var11, var2, var3, var21, var5, null);
            if (var5 != null) {
               byte[] var12 = null;
               if (field5) {
                  try {
                     if (var5.method1()) {
                        var12 = this.method8(var11, var2, var5.getClassNode(), false);
                        FatalIchorError6.method2(var5.getClassNode(), FatalIchorError6.method16(var12, 0));
                     }
                  } catch (Throwable var18) {
                     throw new FatalIchorError("Failed to write " + var4 + " to bytes at " + var11, var18);
                  }
               }

               String var13 = var21.replace('.', '/').replace('$', '_');
               if (method18(var13)) {
                  try {
                     if (var12 == null) {
                        if (var5.method1()) {
                           var12 = this.method8(var11, var2, var5.getClassNode(), false);
                        } else {
                           var12 = var5.getClassBytes();
                        }
                     }

                     String var14 = this.field6.method33().indexOf(var11) + "_" + var11.name();
                     String var15 = var13.substring(0, var13.lastIndexOf(47));
                     String var16 = var13.substring(var13.lastIndexOf(47) + 1);
                     Path var17 = Paths.get(".ichor/dump/" + var15 + "/" + var14 + "/" + var16 + ".class");
                     Files.createDirectories(var17.getParent());
                     Files.write(var17, var12);
                  } catch (Throwable var19) {
                     new IllegalStateException("Failed to write bytes for " + var21 + " at " + var11, var19).printStackTrace();
                  }
               }
            }
         }

         return var5;
      } catch (Throwable var20) {
         throw var20;
      }
   }

   private FatalIchorError8 method5(Ichor4 var1, ClassLoader var2, boolean var3, String var4, @Nullable FatalIchorError8 var5, @Nullable FatalIchorError13 var6) {
      URLClassLoader var7 = this.method7(var1, var2);
      if (var5 != null) {
         method6(var1, var5);
         IchorTransformer.Data3 var8;
         if (!var1.shouldUseClassBytes()) {
            if (!var5.method1()) {
               var5.method4(0);
            }

            var8 = new IchorTransformer.Data3(var1, var5.getClassNode().name, var5, var7);
         } else {
            var8 = new IchorTransformer.Data3(var1, var5.getClassName(), var5, var7);
         }

         List var9 = this.field7.get(var1);
         if (var9 != null) {
            for (AutoCloseableIterator var11 : var9) {
               var11.method3(var8);
            }
         }

         if (var6 != null) {
            var6.method1(this.field6, var1, var5);
         }
      }

      try {
         if (this.field10.contains(var1)
            && var1.hasMixinRuntime()
            && (var3 || !var1.shouldUseParentAsMixinRuntime() && var5 != null && var5.method1() && FatalIchorError6.method17(var5.getClassNode()))
            && var7.method20() != null) {
            if (var5 == null) {
               ClassNode var14 = var7.method20().transformClassNode(this.field6, var4, var4, null);
               if (var14 != null) {
                  var5 = new FatalIchorError8(var14);
               }
            } else {
               var5.method2(var7.method20().transformClassNode(this.field6, var4, var4, var5.getClassNode()));
            }
         }
      } catch (Throwable var12) {
         Throwable var13 = var12;
         if (var12 instanceof FatalIchorError || field2 && var12 instanceof MixinInternalError) {
            throw new FatalIchorError("Failed to apply " + var1 + " to " + var4, var12);
         }

         boolean var15 = var12 instanceof IllegalArgumentException && var12.getMessage().contains("JSR/RET are not supported with computeFrames option");
         if (!var15) {
            IchorPipeline.field3
               .method5(FatalIchorError5.Type.ERROR, " Failed to apply " + var1 + " to " + var4 + ": " + var12.getClass().getName() + " " + var12.getMessage());
            if (var12 instanceof ClassCircularityError var16) {
               var13 = new RuntimeException(
                  "IchorInjector#transform: On stage " + var1 + " using " + var2.getName() + " trying to transform " + var4 + " we exploded???", var16
               );
            }

            var7.method1().method38().method2(var13);
         }
      }

      return var5;
   }

   private static void method6(Ichor4 var0, FatalIchorError8 var1) {
      if (field4 && var1.method1()) {
         ClassNode var2 = var1.getClassNode();
         AnnotationNode var3 = FatalIchorError6.method47(
            com.moonsworth.lunar.ichor.util.Annotation3.class, Map.of("stage", var0.name(), "time", System.currentTimeMillis())
         );
         List var4 = var2.visibleAnnotations;
         if (var4 == null) {
            var4 = new ArrayList();
         }

         var4.add(var3);
         var2.visibleAnnotations = var4;
      }
   }

   @NotNull
   private URLClassLoader method7(Ichor4 var1, ClassLoader var2) {
      URLClassLoader var3 = this.field8.computeIfAbsent(new IchorTransformer.Data(var1, var2), var3x -> new URLClassLoader(this, var1, var2));
      if (var1.hasMixinRuntime() && !this.field10.contains(var1)) {
         this.method2(var2);
      }

      return var3;
   }

   public byte[] method8(Ichor4 var1, ClassLoader var2, @Nullable ClassNode var3, boolean var4) {
      if (var3 == null) {
         return null;
      }

      URLClassLoader var5 = this.method7(var1, var2);
      return this.method9(var5, var3, var4);
   }

   public byte[] method9(URLClassLoader var1, @Nullable ClassNode var2, boolean var3) {
      if (var2 == null) {
         return null;
      }

      int var5 = var3 ? 2 : 0;
      ClassWriter var6 = new ClassWriter(var5, this, var1);

      try {
         try {
            var2.accept(var6);
         } catch (IllegalArgumentException var11) {
            if (!var11.getMessage().contains("JSR/RET")) {
               throw var11;
            }

            var6 = new ClassWriter(var5, this, var1);
            var2.accept(new ClassVisitorImpl(589824, var6));
         }
      } catch (Exception var12) {
         FatalIchorError var8 = new FatalIchorError("Failed to write class " + var2.name + " with COMPUTE_FRAMES", var12);

         try {
            var2.accept(new CheckClassAdapter(null, true));
         } catch (Throwable var10) {
            var8.addSuppressed(var10);
         }

         throw var8;
      }

      return var6.toByteArray();
   }

   public MixinShared method10(Ichor4 var1, ClassLoader var2, MixinShared var3) {
      String var4 = var3.getResourcePath();
      WeakReference var5 = this.field9.get(new IchorTransformer.Data2(var1, var4));
      if (var5 != null) {
         MixinShared var6 = (MixinShared)var5.get();
         if (var6 != null) {
            return var6;
         }
      }

      URLClassLoader var10 = this.field8.computeIfAbsent(new IchorTransformer.Data(var1, var2), var3x -> new URLClassLoader(this, var1, var2));
      List var7 = this.field7.get(var1);
      if (var7 != null) {
         for (AutoCloseableIterator var9 : var7) {
            var9.method4(var3, var10);
         }
      }

      if (var3.method3() != null) {
         this.field9.put(new IchorTransformer.Data2(var1, var4), new WeakReference<>(var3));
      }

      return var3;
   }

   public String method11(Ichor4 var1, String var2) {
      if (var2 == null) {
         return null;
      }

      var2 = var2.replace('.', '/');
      List var3 = this.field7.get(var1);
      if (var3 != null) {
         for (AutoCloseableIterator var5 : var3) {
            for (MixinMisc2 var7 : var5.method9()) {
               var2 = var7.remap(var2);
            }
         }
      }

      var2 = var2.replace('/', '.');
      return var2.intern();
   }

   public String method12(Ichor4 var1, String var2) {
      if (var2 == null) {
         return null;
      }

      var2 = var2.replace('.', '/');
      List var3 = this.field7.get(var1);
      if (var3 != null) {
         ArrayList var4 = new ArrayList(var3);
         Collections.reverse(var4);

         for (AutoCloseableIterator var6 : var4) {
            ArrayList var7 = new ArrayList<>(var6.method9());
            Collections.reverse(var7);

            for (MixinMisc2 var9 : var7) {
               var2 = var9.unmap(var2);
            }
         }
      }

      var2 = var2.replace('/', '.');
      return var2.intern();
   }

   public Set<String> method13(Ichor4 var1, String var2) {
      HashSet var3 = new HashSet();
      var3.add(var2);
      ArrayList var4 = new ArrayList<>(this.field6.method33());
      Collections.reverse(var4);

      for (Ichor4 var6 : var4) {
         HashSet var7 = new HashSet();

         for (String var9 : var3) {
            var7.add(this.method12(var6, var9));
         }

         var3.addAll(var7);
      }

      return var3;
   }

   public String method14(String var1) {
      ArrayList var2 = new ArrayList<>(this.field6.method33());
      Collections.reverse(var2);

      for (Ichor4 var4 : var2) {
         var1 = this.method12(var4, var1);
      }

      return var1;
   }

   public String method15(Ichor4 var1, String var2) {
      for (Ichor4 var5 : new ArrayList<>(this.field6.method33())) {
         var2 = this.method11(var5, var2);
         if (var5 == var1) {
            break;
         }
      }

      return var2;
   }

   public String method16(String var1) {
      for (Ichor4 var4 : new ArrayList<>(this.field6.method33())) {
         var1 = this.method11(var4, var1);
      }

      return var1;
   }

   public List<MappingSet> method17(Ichor4 var1) {
      List var2 = this.field7.get(var1);
      if (var2 == null) {
         return List.of();
      }

      ArrayList var3 = new ArrayList();

      for (AutoCloseableIterator var5 : var2) {
         var3.addAll(var5.method10());
      }

      return var3;
   }

   public static boolean method18(String var0) {
      for (String var4 : field3) {
         if (!var4.isBlank() && var0.startsWith(var4)) {
            return true;
         }
      }

      return false;
   }

   Collection<URLClassLoader> method19() {
      return this.field8.values();
   }

   @Generated
   public IchorPipeline method20() {
      return this.field6;
   }

   @Generated
   public Map<Ichor4, List<AutoCloseableIterator>> method21() {
      return this.field7;
   }

   @Generated
   public List<MixinInternal3> method22() {
      return this.field12;
   }

   private class Data {
      private final Ichor4 field1;
      private final ClassLoader field2;

      private Data(Ichor4 var1, ClassLoader var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public Ichor4 method1() {
         return this.field1;
      }

      public ClassLoader method2() {
         return this.field2;
      }
   }

   private class Data2 {
      private final Ichor4 field1;
      private final String field2;

      private Data2(Ichor4 var1, String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public Ichor4 method1() {
         return this.field1;
      }

      public String className() {
         return this.field2;
      }
   }

   public class Data3 {
      private final Ichor4 field1;
      private final String field2;
      private final FatalIchorError8 field3;
      private final URLClassLoader field4;

      public Data3(Ichor4 var1, String var2, FatalIchorError8 var3, URLClassLoader var4) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
         this.field4 = var4;
      }

      public byte[] method1(Class<?> var1) {
         if (this.field3.method1()) {
            throw new IllegalStateException("Trying to get class bytes at a not-early stage: " + this.field1 + " -> " + var1.getSimpleName());
         } else {
            return this.field3.getClassBytes();
         }
      }

      public void method2(byte[] var1) {
         if (this.field3.method1()) {
            throw new IllegalStateException("Trying to set class name at a not-early stage: " + this.field1);
         }

         this.field3.method3(var1, this.field3.getClassName());
      }

      public ClassNode method3(Class<?> var1) {
         if (this.field3.method1()) {
            return this.field3.getClassNode();
         } else {
            throw new IllegalStateException("Trying to get ClassNode at an early stage: " + this.field1 + " -> " + var1.getSimpleName());
         }
      }

      public Ichor4 method4() {
         return this.field1;
      }

      public String className() {
         return this.field2;
      }

      public FatalIchorError8 method5() {
         return this.field3;
      }

      public URLClassLoader method6() {
         return this.field4;
      }
   }
}
