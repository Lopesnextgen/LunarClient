package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.api.IchorAPI2;
import com.moonsworth.lunar.ichor.api.IchorAPI3;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.ichor.util.FatalIchorError11;
import com.moonsworth.lunar.ichor.util.FatalIchorError13;
import com.moonsworth.lunar.ichor.util.FatalIchorError14;
import com.moonsworth.lunar.ichor.util.FatalIchorError3;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.ichor.util.FatalIchorError7;
import com.moonsworth.lunar.ichor.util.FatalIchorError8;
import com.moonsworth.lunar.ichor.util.FlawlessFrames;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Generated;
import net.minecraft.launchwrapper.IClassTransformer;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.provider.ClassProvider;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.IMixinServiceBootstrap;

public class URLClassLoader
   extends java.net.URLClassLoader
   implements IchorAPI2,
   FatalIchorError3,
   org.cadixdev.bombe.analysis.InheritanceProvider,
   ClassProvider {
   public static final String field1 = "org.spongepowered.asm.";
   private static final FatalIchorError5 field2 = FatalIchorError5.field1;
   private static final HashSet<String> field3 = new HashSet<>();
   public static boolean field4 = false;
   public static boolean field5 = true;
   private static final Map<Path, Path> field6 = new ConcurrentHashMap<>();
   private final IchorTransformer field7;
   private final org.cadixdev.bombe.analysis.InheritanceProvider field8;
   private final Ichor4 field9;
   boolean field10 = false;
   private static final AtomicBoolean field11;
   private MixinInternal2 field12;
   private URLClassLoader field13;

   public URLClassLoader(IchorTransformer var1, Ichor4 var2, ClassLoader var3) {
      super("IchorClassLoader(" + var2.name() + ")", new URL[0], var3);
      this.field7 = var1;
      this.field8 = new com.moonsworth.lunar.ichor.util.InheritanceProvider(new InheritanceProvider(this));
      this.field9 = var2;
      if (!var1.method20().method33().contains(var2)) {
         throw new IllegalStateException(
            "Tried to initialize an IchorClassLoader with stage " + var2.name() + " but that stage isn't in the supplied IchorInjector's pipeline."
         );
      }
   }

   public void method2() {
      try {
         if (!this.field10 && this.field9.hasMixinRuntime() && !field11.get()) {
            field11.set(true);

            try {
               this.field10 = true;
               IchorAPI2 var1 = this.field9.shouldUseParentAsMixinRuntime() && this.getParent() instanceof IchorAPI2 var2 ? var2 : this;
               var1.method3(this);
               Class var11 = var1.loadClass("org.spongepowered.asm.launch.MixinBootstrap", true);
               var11.getMethod("init").invoke(null);
               if (this.field12 == null) {
                  throw new IllegalStateException(this.getName() + ": MixinProxy is null");
               }
            } catch (Throwable var8) {
               System.err.println(this.getName() + ".loadSandboxedMixin: loadedMixin: " + this.field10 + " loadingMixin: " + this.field10);
               var8.printStackTrace();
               throw var8;
            } finally {
               field11.set(false);
            }
         }
      } catch (Throwable var10) {
         throw var10;
      }
   }

   public void method3() {
      try {
         if (this.field12 == null) {
            throw new IllegalStateException(this.getName() + ": MixinProxy is null");
         }

         for (MixinInternal3 var2 : this.field7.method22()) {
            var2.method1(this.field9, this.field12, this);
         }
      } catch (Throwable var3) {
         System.err.println(this.getName() + ".setupMixins: error occurred " + var3.getMessage());
         var3.printStackTrace();
         throw var3;
      }
   }

   @Override
   public Class<?> loadClass(String var1, boolean var2) {
      synchronized (this.getClassLoadingLock(var1)) {
         Class var4 = this.findLoadedClass(var1);
         if (var4 != null) {
            return var4;
         }

         if (var1.equals(this.getClass().getName())) {
            return this.getClass();
         }

         boolean var5 = false;
         if (this.getParent() instanceof IchorAPI3 var6) {
            var5 = var6.isExcluded(var1);
         }

         if (method10(var1, this.method1()) && !var5 && !method11(var1) && (!this.field9.hasMixinRuntime() || !this.field9.shouldUseParentAsMixinRuntime())) {
            try {
               Class var11 = this.findClass(var1);
               if (var11 != null) {
                  return var11;
               }
            } catch (Exception var9) {
               if (!(var9 instanceof ClassNotFoundException)) {
                  field2.method5(FatalIchorError5.Type.ERROR, "IchorClassLoader.loadClass: Failed to find class " + var1);
                  var9.printStackTrace();
               }
            }
         }

         return super.loadClass(var1.replace('/', '.'), var2);
      }
   }

   @Override
   public void method3(URLClassLoader var1) {
      this.field13 = var1;
   }

   @Override
   public URLClassLoader method4() {
      return this.field13;
   }

   private boolean method5(String var1) {
      return !var1.startsWith("org.spongepowered.asm.")
         && !var1.startsWith("com.moonsworth.lunar.genesis.lib.google.common")
         && !var1.startsWith("org.apache.logging.log4j");
   }

   @Override
   protected Class<?> findClass(String var1) {
      try {
         FatalIchorError14 var2 = this.method7(var1, true);
         if (var2.method1() != null) {
            ProtectionDomain var3 = method13(this, this.getParent(), var1);
            return this.defineClass(var2.className(), var2.method1(), 0, var2.method1().length, var3);
         } else {
            return super.findClass(var1);
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public ClassNode method6(String var1, boolean var2) {
      try {
         if (var2) {
            this.method2();
         }

         long var3 = System.currentTimeMillis();
         IchorPipeline var5 = this.field7.method20();
         List var6 = var5.method33();
         int var7 = var6.indexOf(this.field9);
         int var8 = 0;
         Set var9 = this.field7.method13(this.field9, this.field7.method15(this.field9, var1));
         byte[] var10 = null;
         String var11 = var1;
         Optional var12 = var5.method34().method1(var9);
         if (var12.isPresent()) {
            MixinExtra2 var13 = (MixinExtra2)var12.get();
            int var14 = var6.indexOf(var13.method1());
            if (var14 <= var7) {
               var10 = var13.method2();
               var11 = var13.className();
               var8 = var14 + 1;
            }
         }

         if (var10 == null) {
            for (String var20 : var9) {
               String var15 = var20.replace('.', '/') + ".class";
               InputStream var16 = this.getResourceAsStream(var15);
               if (var16 != null) {
                  var10 = FatalIchorError7.toByteArray(var16);
                  break;
               }
            }
         }

         FatalIchorError8 var19 = null;
         if (var10 != null) {
            var19 = new FatalIchorError8(var10);
         }

         if (var8 < var7) {
            Ichor4[] var21 = new Ichor4[var7 - var8];

            for (int var24 = var8; var24 < var7; var24++) {
               var21[var24 - var8] = (Ichor4)var6.get(var24);
            }

            boolean var25 = var2 && this.method5(var1);
            var19 = this.field7.method4(var21, this.getParent(), var25, var11, var19);
         }

         if (var19 != null && !var19.method1()) {
            var19.method4(0);
         }

         if (!var2 && var19 != null && var5.method34().method6().get("computeFrames") == Boolean.TRUE) {
            byte[] var22 = this.field7.method9(this, var19.getClassNode(), true);
            var19.method2(FatalIchorError6.method16(var22, 0));
         }

         long var23 = System.currentTimeMillis() - var3;
         if (var23 > 0L && IchorPipeline.field1) {
            this.method1().method40().field7.getAndAdd(var23);
            String var26 = Thread.currentThread().getName();
            if (var26.equals("Client thread") || var26.equals("Render thread")) {
               this.method1().method40().field8.getAndAdd(var23);
            }
         }

         if (var19 == null) {
            throw new ClassNotFoundException(this.getName() + " couldn't find class bytes for " + var1 + " (checked " + var9 + ").");
         } else {
            return var19.getClassNode();
         }
      } catch (Throwable var17) {
         throw var17;
      }
   }

   public FatalIchorError14 method7(String var1, boolean var2) {
      try {
         ClassNode var3 = this.method6(var1, var2);
         byte[] var4 = this.field7.method9(this, var3, true);
         return new FatalIchorError14(var3.name.replace('/', '.'), var4);
      } catch (Throwable var5) {
         throw var5;
      }
   }

   @Override
   public InputStream getResourceAsStream(String var1) {
      if (method12(this, var1)) {
         return null;
      }

      ClassLoader var2 = this.getParent();
      InputStream var3;
      if (var2 instanceof FatalIchorError3 var4) {
         var3 = var4.method1(var1);
      } else {
         var3 = var2.getResourceAsStream(var1);
      }

      return var3 != null ? var3 : super.getResourceAsStream(var1);
   }

   @Override
   public InputStream method1(String var1) {
      return this.getResourceAsStream(var1);
   }

   @Override
   public String toString() {
      return "IchorClassLoader(" + this.field9 + ")";
   }

   @Deprecated
   public byte[] get(String var1) {
      try {
         try {
            FatalIchorError14 var2 = this.method7(var1, true);
            return var2.method1();
         } catch (Exception var3) {
            return null;
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public ClassNode getAsNode(String var1) {
      try {
         return this.method6(var1, true);
      } catch (Exception var3) {
         return null;
      }
   }

   public ClassNode getAsNode(String var1, int var2) {
      try {
         return this.method6(var1, true);
      } catch (Exception var4) {
         return null;
      }
   }

   public Optional<ClassInfo> provide(String var1) {
      return this.field8.provide(var1);
   }

   @Override
   public IchorPipeline method1() {
      return this.field7.method20();
   }

   public static boolean method10(String var0, IchorPipeline var1) {
      return !var0.startsWith("java.")
         && (!var0.startsWith("javax.") || var0.startsWith("javax.script."))
         && (!var0.startsWith("kotlin.") || !var1.hasModule("forge"))
         && !var0.startsWith("jdk.")
         && !var0.startsWith("org.graalvm.")
         && !var0.startsWith("com.oracle.truffle.")
         && !var0.startsWith("com.yourkit.")
         && !var0.startsWith("org.w3c.")
         && !var0.startsWith("org.xml.")
         && !var0.startsWith("com.fasterxml.")
         && !var0.startsWith("com.sun.")
         && !var0.startsWith("sun.")
         && !var0.startsWith("org.objectweb.asm.")
         && !var0.startsWith("com.moonsworth.lunar.genesis.lib.google.common.")
         && !var0.startsWith("javafx.")
         && !field3.contains(var0)
         && !var0.startsWith("net.fabricmc.api.")
         && !var0.startsWith("net.fabricmc.loader.")
         && !var0.startsWith("org.apache.")
         && !var0.startsWith("org.slf4j.")
         && (!var0.startsWith("com.replaymod.lib.") || var0.startsWith("com.replaymod.lib.de.johni0702.minecraft.gui."));
   }

   private static boolean method11(String var0) {
      return var0.startsWith("com.llamalad7.mixinextras.sugar.impl.ref.generated.");
   }

   public boolean hasClass(String var1) {
      try {
         for (String var4 : this.field7.method13(this.field9, this.field7.method15(this.field9, var1))) {
            String var5 = var4.replace('.', '/') + ".class";
            if (this.getResource(var5) != null) {
               return true;
            }
         }

         return false;
      } catch (Throwable var6) {
         throw var6;
      }
   }

   @Override
   public void addURL(URL var1) {
      try {
         super.addURL(var1);
         MixinMoreHandler.method1(this.getParent(), this.method1(), var1);
      } catch (Throwable var3) {
         throw var3;
      }
   }

   public static boolean method12(IchorAPI2 var0, String var1) {
      boolean var2 = !var0.method1().hasModule("fabric") || field4 && !field5;
      return var2 && var1.contains("refmap") && var1.endsWith(".json");
   }

   public static ProtectionDomain method13(ClassLoader var0, ClassLoader var1, String var2) {
      long var3 = System.nanoTime();
      ProtectionDomain var5 = method14(var0, var1, var2);
      long var6 = System.nanoTime();
      Ichor.field1 += var6 - var3;
      return var5;
   }

   public static ProtectionDomain method14(ClassLoader var0, ClassLoader var1, String var2) {
      try {
         String var3 = var2.replace(".", "/").concat(".class");
         URL var4 = var1.getResource(var3);
         if (var4 != null && (var4.getProtocol().equals("file") || var4.getProtocol().equals("jar"))) {
            Path var5 = method15(var4, var3);
            Path var6 = field6.get(var5);
            if (var6 == null) {
               var6 = var5.toRealPath();
               field6.put(var5, var6);
            }

            return new ProtectionDomain(new CodeSource(var6.toUri().toURL(), (Certificate[])null), null, var0, null);
         } else {
            return null;
         }
      } catch (Exception var7) {
         field2.warn("Failed to find codesource for %s: %s %s", var2, var7.getClass().getName(), var7.getMessage());
         return null;
      }
   }

   private static Path method15(URL var0, String var1) {
      if (var0.openConnection() instanceof JarURLConnection var3) {
         return Paths.get(var3.getJarFileURL().toURI());
      } else {
         URI var4 = var0.toURI();
         String var5 = var4.getPath();
         if (var5.endsWith(var1)) {
            String var6 = var5.substring(0, var5.length() - var1.length());
            URI var7 = new URI(var4.getScheme(), var4.getUserInfo(), var4.getHost(), var4.getPort(), var6, var4.getQuery(), var4.getFragment());
            return Paths.get(var7);
         } else {
            throw new IllegalArgumentException("Could not figure out code source for file '" + var1 + "' in URL '" + var0 + "'!");
         }
      }
   }

   @Generated
   public IchorTransformer method16() {
      return this.field7;
   }

   @Generated
   public org.cadixdev.bombe.analysis.InheritanceProvider method17() {
      return this.field8;
   }

   @Generated
   public Ichor4 method18() {
      return this.field9;
   }

   @Generated
   public void method19(MixinInternal2 var1) {
      this.field12 = var1;
   }

   @Generated
   public MixinInternal2 method20() {
      return this.field12;
   }

   static {
      try {
         Class.forName("com.moonsworth.lunar.magnify.ichor.ParameterAnnotationIchor");
         field4 = true;
         field5 = System.getProperty("ichor.fabric.intermediary") != null;
      } catch (ClassNotFoundException var1) {
      }

      field11 = new AtomicBoolean(false);
      registerAsParallelCapable();
      List.of(
            MixinInternal2.class,
            Ichor4.class,
            FatalIchorError14.class,
            IchorAPI.class,
            IchorAPI2.class,
            IchorAPI3.class,
            FatalIchorError5.class,
            AutoCloseableIterator.class,
            Ichor5.class,
            Ichor3.class,
            IchorPipeline.class,
            IchorPipeline.Data.class,
            IchorTransformer.class,
            Ichor5Handler_2.class,
            MixinInternalTask.class,
            MixinInternal.class,
            FatalIchorError13.class,
            FatalIchorError13.Data.class,
            FatalIchorError.class,
            MixinInternalError.class,
            IClassTransformer.class,
            MixinSupport.class,
            FatalIchorError11.class,
            IMixinServiceBootstrap.class,
            FatalIchorError6.class,
            FlawlessFrames.class
         )
         .forEach(var0 -> field3.add(var0.getName()));
      field3.addAll(List.of("org.apache.logging.log4j.core.impl.ThreadContextDataInjector", "org.objectweb.asm.commons.RemappingClassAdapter"));
   }
}
