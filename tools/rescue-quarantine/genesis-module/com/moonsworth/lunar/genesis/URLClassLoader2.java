package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.Ichor7;
import com.moonsworth.lunar.ichor.MixinMore;
import com.moonsworth.lunar.ichor.MixinMoreHandler;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.api.IchorAPI2;
import com.moonsworth.lunar.ichor.api.IchorAPI3;
import com.moonsworth.lunar.ichor.util.FatalIchorError14;
import com.moonsworth.lunar.ichor.util.FatalIchorError3;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.ichor.util.FatalIchorError7;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;
import com.google.common.cache.Cache;

public final class URLClassLoader2 extends java.net.URLClassLoader implements IchorAPI2, IchorAPI3, FatalIchorError3 {
   static final boolean field1 = Boolean.parseBoolean(System.getProperty("ichor.debugPrebakedClasses", "false"));
   private final Ichor7 field2;
   private Set<String> field3 = new HashSet<>();
   private final ConcurrentMap<String, byte[]> field4 = new ConcurrentHashMap<>();
   private volatile boolean field5;
   private com.moonsworth.lunar.ichor.URLClassLoader field6;

   public URLClassLoader2(URL[] var1, Ichor7 var2) {
      super("Genesis", var1, URLClassLoader2.class.getClassLoader());
      this.field2 = var2;
   }

   @Override
   public Class<?> loadClass(String var1, boolean var2) {
      if (com.moonsworth.lunar.ichor.URLClassLoader.method10(var1, this.field2)
         && !this.isExcluded(var1)
         && !var1.equals(com.moonsworth.lunar.ichor.URLClassLoader.class.getName())) {
         String var3 = this.field5 ? this.method9(var1) : var1;
         synchronized (this.getClassLoadingLock(var3)) {
            Class var5 = this.findLoadedClass(var1);
            if (var5 != null) {
               return var5;
            }

            if (var1.equals(this.getClass().getName())) {
               return this.getClass();
            }

            try {
               Class var6 = this.findClass(var1);
               if (var6 != null) {
                  if (var2) {
                     this.resolveClass(var6);
                  }

                  return var6;
               } else {
                  return super.loadClass(var1.replace('/', '.'), var2);
               }
            } catch (Exception var8) {
               throw new ClassNotFoundException("Failed to find class " + var1, var8);
            }
         }
      } else {
         return super.loadClass(var1.replace('/', '.'), var2);
      }
   }

   @Override
   public void method3(com.moonsworth.lunar.ichor.URLClassLoader var1) {
      this.field6 = var1;
   }

   @Override
   public com.moonsworth.lunar.ichor.URLClassLoader method4() {
      return this.field6;
   }

   @Override
   protected Class<?> findClass(String var1) {
      Class var2 = this.findLoadedClass(var1);
      if (var2 != null) {
         return var2;
      }

      if (com.moonsworth.lunar.ichor.URLClassLoader.method10(var1, this.field2) && !this.isExcluded(var1)) {
         String var3 = this.field5 ? this.method9(var1) : var1;
         byte[] var4 = this.field4.get(var3);
         FatalIchorError14 var5;
         if (var4 != null) {
            Ichor4 var6 = IchorAPI.getClassCacheLevel();
            if (var6 != null) {
               var5 = this.field2.method4(var3, var4, this, var6, null);
            } else {
               var5 = new FatalIchorError14(var3, var4);
            }
         } else if (this.field5 && Genesis2.method4(var1)) {
            Ichor4 var15 = IchorAPI.getClassCacheLevel();
            URLClassLoader2.Data var7 = this.method8(
               var1, () -> var15 != null ? this.field2.method4(var1, null, this, null, var15) : this.field2.method3(var1, null, this)
            );
            var5 = var7.method1();
            if (var15 != null) {
               var5 = this.field2.method4(var5.className(), var5.method1(), this, var15, null);
            }
         } else {
            var5 = this.field2.method3(var1, null, this);
         }

         var2 = this.findLoadedClass(var5.className());
         if (var2 != null) {
            return var2;
         }

         try {
            ProtectionDomain var16 = com.moonsworth.lunar.ichor.URLClassLoader.method13(this, this, var1);
            return this.defineClass(var5.className(), var5.method1(), 0, var5.method1().length, var16);
         } catch (NoClassDefFoundError var13) {
            if (var13.getMessage().contains("wrong name: ")) {
               String var17 = var1;
               List var8 = this.field2.method33();

               for (int var9 = var8.size() - 1; var9 >= 0; var9--) {
                  Ichor4 var10 = (Ichor4)var8.get(var9);
                  var17 = this.field2.method30().method12(var10, var17);
               }

               Genesis.LOGGER.fatal("Encountered mismatched name while loading " + var1 + "!");
               Genesis.LOGGER.fatal("  Error message: " + var13.getMessage());
               Genesis.LOGGER.fatal("  Name from class bytes: " + FatalIchorError6.method6(var5.method1()));
               Genesis.LOGGER.fatal("Remap trace for " + var1 + " -> " + var5.className() + ":");
               int var18 = 1;

               for (Ichor4 var11 : var8) {
                  String var12 = this.field2.method30().method11(var11, var17);
                  if (!var12.equals(var17)) {
                     Genesis.LOGGER.fatal(var18 + ". " + var11.name() + ": " + var17 + " -> " + var12);
                     var18++;
                  }

                  var17 = var12;
               }
            }

            throw var13;
         }
      } else {
         return super.findClass(var1);
      }
   }

   @Override
   public InputStream getResourceAsStream(String var1) {
      if (com.moonsworth.lunar.ichor.URLClassLoader.method12(this, var1)) {
         return null;
      }

      try {
         InputStream var2 = super.getResourceAsStream(var1);
         if (var2 == null) {
            for (Ichor5 var4 : this.method1().method36()) {
               if (var4 instanceof MixinMore var5) {
                  var2 = var5.getResourceAsStream(var1);
                  if (var2 != null) {
                     break;
                  }
               }
            }
         }

         byte[] var7 = var2 == null ? null : FatalIchorError7.toByteArray(var2);
         byte[] var8 = this.field2.method6(var1, var7, this).method3();
         return var8 == null ? null : new ByteArrayInputStream(var8);
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      }
   }

   @Override
   public URL findResource(String var1) {
      if (com.moonsworth.lunar.ichor.URLClassLoader.method12(this, var1)) {
         return null;
      }

      List var2;
      if (var1.endsWith(".class")) {
         String var3 = var1.substring(0, var1.length() - 6).replace('/', '.');
         Set var4 = this.field2.method30().method13(null, var3);
         var2 = new ArrayList(var4.size());

         for (String var6 : var4) {
            var2.add(var6.replace('.', '/') + ".class");
         }
      } else {
         var2 = Collections.singletonList(var1);
      }

      for (String var10 : var2) {
         URL var11 = super.findResource(var10);
         if (var11 == null) {
            for (Ichor5 var7 : this.method1().method36()) {
               if (var7 instanceof MixinMore var8) {
                  var11 = var8.findResource(var10);
                  if (var11 != null) {
                     break;
                  }
               }
            }
         }

         if (var11 != null) {
            return var11;
         }
      }

      return null;
   }

   @Override
   public Enumeration<URL> findResources(String var1) {
      ArrayList var2 = Collections.list(super.findResources(var1));

      for (Ichor5 var4 : this.method1().method36()) {
         if (var4 instanceof MixinMore var5) {
            URL var6 = var5.findResource(var1);
            if (var6 != null) {
               var2.add(var6);
            }
         }
      }

      return Collections.enumeration(var2);
   }

   @Override
   public InputStream method1(String var1) {
      if (com.moonsworth.lunar.ichor.URLClassLoader.method12(this, var1)) {
         return null;
      }

      try {
         InputStream var2 = super.getResourceAsStream(var1);
         if (var2 == null) {
            var2 = this.getResourceAsStream(var1);
         }

         return var2;
      } catch (SecurityException var3) {
         throw new RuntimeException("Failed to get raw resource stream for " + var1, var3);
      }
   }

   @Override
   public String toString() {
      String var1 = Config.method36(this.field2.method34().method6()).getId();
      return "GenesisClassLoader(" + var1 + ")";
   }

   @Override
   public Ichor7 method1() {
      return this.field2;
   }

   @Override
   public void method1(Set<String> var1) {
      this.field3 = var1;
      if (field1) {
         Genesis.LOGGER.info("Excluding " + this.field3.size() + " classes from GenesisClassLoader");

         for (String var3 : this.field3) {
            Genesis.LOGGER.info("  excluding " + var3);
         }
      }
   }

   @Override
   public boolean isExcluded(String var1) {
      return this.field3.contains(var1.replace('/', '.'));
   }

   @Override
   public Optional<Class<?>> method2(String var1, byte[] var2) {
      try {
         return Optional.of(this.defineClass(var1, var2, 0, var2.length));
      } catch (ClassFormatError var4) {
         return Optional.empty();
      }
   }

   public ConcurrentMap<String, byte[]> method7() {
      return this.field4;
   }

   URLClassLoader2.Data method8(String var1, Supplier<FatalIchorError14> var2) {
      String var3 = this.method9(var1);
      synchronized (this.getClassLoadingLock(var3)) {
         byte[] var5 = this.field4.get(var3);
         if (var5 != null) {
            return new URLClassLoader2.Data(new FatalIchorError14(var3, var5), false);
         }

         FatalIchorError14 var6 = (FatalIchorError14)var2.get();
         String var7 = this.method9(var6.className());
         if (!var7.equals(var3)) {
            throw new IllegalStateException("Cache transform renamed " + var3 + " to a different canonical key " + var7);
         }

         this.field4.put(var7, var6.method1());
         return new URLClassLoader2.Data(var6, true);
      }
   }

   String method9(String var1) {
      if (this.field2 != null) {
         var1 = this.field2.method30().method14(var1);
         var1 = this.field2.method30().method16(var1);
      }

      return var1.replace('/', '.');
   }

   public void method10() {
      this.field5 = true;
   }

   @Override
   public void close() {
      try {
         super.close();
         this.field2.close();
      } catch (Throwable var2) {
         throw var2;
      }
   }

   @Override
   public void addURL(URL var1) {
      try {
         super.addURL(var1);
         MixinMoreHandler.method1(this, this.method1(), var1);
      } catch (Throwable var3) {
         throw var3;
      }
   }

   static {
      registerAsParallelCapable();
   }

   class Data {
      private final FatalIchorError14 field1;
      private final boolean field2;

      Data(FatalIchorError14 var1, boolean var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public FatalIchorError14 method1() {
         return this.field1;
      }

      public boolean method2() {
         return this.field2;
      }
   }
}
