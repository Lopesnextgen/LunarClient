package com.moonsworth.lunar.ichor;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.files.Files2_2;
import com.moonsworth.lunar.files.Files3;
import com.moonsworth.lunar.files.Files5_2;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.moonsworth.lunar.ichor.api.IchorAPI3;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.ichor.util.FatalIchorError11;
import com.moonsworth.lunar.ichor.util.FatalIchorError13;
import com.moonsworth.lunar.ichor.util.FatalIchorError14;
import com.moonsworth.lunar.ichor.util.FatalIchorError3;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.ichor.util.FatalIchorError8;
import com.moonsworth.lunar.ichor.util.FatalIchorError9;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lombok.Generated;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.MappingsReader;

public class IchorPipeline implements AutoCloseable {
   public static final boolean field1 = Boolean.parseBoolean(System.getProperty("ichor.debugPipelineTimings", "false"));
   public static final String field2 = "absolute::";
   public static final FatalIchorError5 field3 = new FatalIchorError5("IchorPipeline");
   private final ClassLoader field4;
   @Nullable
   private ClassLoader field5;
   private final IchorTransformer field6;
   private final Map<String, IchorModule> field7;
   private final Map<Object, AutoCloseableIterator> field8;
   private final List<Ichor4> field9;
   private final Ichor3 field10;
   private final Table<String, String, String> field11;
   private final Table<String, String, String> field12;
   private final List<Ichor5> field13;
   private final Set<String> field14;
   private final FatalIchorError11 field15;
   @Nullable
   private Path field16;
   private final IchorPipeline.Data field17 = new IchorPipeline.Data();

   public IchorPipeline(List<Ichor4> var1, Ichor3 var2, ClassLoader var3) {
      if (field1) {
         field3.info("Creating pipeline for " + var1);
      }

      this.field9 = var1;
      if (var1.isEmpty()) {
         throw new IllegalArgumentException("Must provide at least one InjectStage");
      }

      this.field7 = new ConcurrentHashMap<>();
      this.field8 = new ConcurrentHashMap<>();
      this.field10 = var2;
      this.field11 = HashBasedTable.method1();
      this.field12 = HashBasedTable.method1();
      this.field6 = new IchorTransformer(this);
      this.field14 = new HashSet<>();
      this.field13 = new ArrayList<>();
      this.field15 = new FatalIchorError11();
      long var4 = System.currentTimeMillis();
      this.field4 = var3;
      if (field1) {
         field3.info("attempting to load IchorModules using " + var3);
      }

      Iterator var6 = ServiceLoader.load(IchorModule.class, var3).iterator();
      ArrayList var7 = new ArrayList();
      var6.forEachRemaining(var7::add);
      Collections.sort(var7);
      var7.forEach(var1x -> {
         if (field1) {
            field3.info("found IchorModule " + var1x.getClass().getName());
         }

         this.field14.add(var1x.getClass().getName());
         this.field7.put(var1x.getId(), var1x);

         for (Ichor5 var3x : var1x.method1(this)) {
            this.field14.add(var3x.getClass().getName());
            this.field13.add(var3x);
         }

         Map var4x = var1x.method2(this);
         if (var4x != null) {
            this.field11.row(var1x.getId()).putAll(var4x);
         }
      });
      if (field1 && this.field7.isEmpty()) {
         field3.method5(FatalIchorError5.Type.ERROR, "Couldn't find any IchorModules on the classpath! This is likely a fatal error.");
      }

      this.field13.forEach(var1x -> var1x.loadIchor(this.field6));
      this.method2();
      if (field1) {
         long var8 = System.currentTimeMillis() - var4;
         field3.info("Found " + this.field11.size() + " external files and " + this.field13.size() + " IchorLoaders.");
         field3.info("Done creating IchorPipeline in " + var8 + "ms.");
      }
   }

   public void method1(Ichor5 var1) {
      this.field14.add(var1.getClass().getName());
      this.field13.add(var1);
      var1.loadIchor(this.field6);
   }

   private void method2() {
      for (Ichor5 var2 : this.field13) {
         if (var2 instanceof Ichor5Handler_2 var3) {
            for (JsonObject var6 : var3.method2(this.field6)) {
               try {
                  JsonElement var7 = var6.get("mappings");
                  if (var7 != null) {
                     JsonObject var8 = var7.getAsJsonObject();

                     for (String var10 : var8.keySet()) {
                        JsonElement var11 = var8.get(var10);
                        if (var11 != null) {
                           JsonObject var12 = var11.getAsJsonObject();

                           for (String var14 : var12.keySet()) {
                              String var15 = var12.get(var14).getAsString();
                              this.field12.put(var10, var14, var15);
                           }
                        }
                     }
                  }
               } catch (Exception var16) {
                  field3.warn("Failed reading refmap json from %s: %s %s", var3.getId(), var16.getClass().getName(), var16.getMessage());
               }
            }
         }
      }
   }

   public FatalIchorError14 method3(String var1, @Nullable byte[] var2, ClassLoader var3) {
      try {
         return this.method4(var1, var2, var3, null, null);
      } catch (Throwable var5) {
         throw var5;
      }
   }

   public FatalIchorError14 method4(String var1, @Nullable byte[] var2, ClassLoader var3, @Nullable Ichor4 var4, @Nullable Ichor4 var5) {
      try {
         if (var1.endsWith("module-info")) {
            return new FatalIchorError14(var1, var2);
         }

         this.method22(var3);
         long var6 = System.currentTimeMillis();
         String var8 = var1;
         HashSet var9 = new HashSet();
         var9.add(var8);
         List var10 = this.field9;
         if (var4 != null) {
            int var11 = var10.indexOf(var4);
            if (var11 != -1 && var11 != var10.size() - 1) {
               var10 = var10.subList(var11 + 1, var10.size());
            }
         }

         if (var5 != null) {
            int var22 = var10.indexOf(var5);
            if (var22 != -1) {
               var10 = var10.subList(0, var22 + 1);
            }
         }

         for (int var23 = var10.size() - 1; var23 >= 0; var23--) {
            Ichor4 var12 = (Ichor4)var10.get(var23);
            var8 = this.field6.method12(var12, var8);
            var9.add(var8);
         }

         for (Ichor4 var26 : var10) {
            HashSet var13 = new HashSet();

            for (String var15 : var9) {
               var13.add(this.field6.method12(var26, var15));
            }

            var9.addAll(var13);
         }

         int var25 = 0;
         String var27 = var8;
         byte[] var29 = var2;
         Optional var30 = this.field10.method1(var9);
         if (var30.isPresent()) {
            MixinExtra2 var31 = (MixinExtra2)var30.get();
            var25 = var10.indexOf(var31.method1()) + 1;
            var27 = var31.className();
            var29 = var31.method2();
         }

         if (var29 == null) {
            Function var32;
            if (var3 instanceof FatalIchorError3 var16) {
               var32 = var16::method1;
            } else {
               var32 = var3::getResourceAsStream;
            }

            InputStream var34 = (InputStream)var32.apply(var8.replace('.', '/').concat(".class"));
            if (var34 == null) {
               var34 = (InputStream)var32.apply(var1.replace('.', '/').concat(".class"));
            }

            if (var34 == null) {
               for (String var18 : var9) {
                  var34 = (InputStream)var32.apply(var18.replace('.', '/').concat(".class"));
                  if (var34 != null) {
                     break;
                  }
               }
            }

            if (var34 == null) {
               for (Ichor5 var40 : this.field13) {
                  if (var40 instanceof ClassProvider var19) {
                     var29 = var19.get(var8.replace('.', '/'));
                     if (var29 != null) {
                        break;
                     }
                  }
               }
            }

            if (var29 == null && var34 != null) {
               var29 = var34.readAllBytes();
               var34.close();
            }
         }

         FatalIchorError8 var33 = null;
         if (var29 != null) {
            var33 = new FatalIchorError8(var29);
         }

         Ichor4[] var35 = new Ichor4[var10.size() - var25];

         for (int var37 = var25; var37 < var10.size(); var37++) {
            var35[var37 - var25] = (Ichor4)var10.get(var37);
         }

         if (var33 == null || !var1.startsWith("org.spongepowered.asm.mixin.") && !var1.startsWith("com.llamalad7.mixinextras.") && !var1.startsWith("kotlin.")
            )
          {
            try {
               var33 = this.field6.method4(var35, var3, true, var27, var33);
            } catch (Throwable var20) {
               throw new FatalIchorError("Failed to transform " + var1, var20);
            }
         }

         if (var33 != null && !var33.method1()) {
            var33.method4(0);
         }

         if (var33 != null) {
            var29 = this.field6.method8(var35[var35.length - 1], var3, var33.getClassNode(), var5 == null);
         }

         if (var29 == null) {
            String var39 = var1;
            if (!var8.equals(var1)) {
               var39 = var8 + "(" + var1 + ")";
            }

            throw new ClassNotFoundException("IchorPipeline can't find class in " + var3.getName() + ": " + var39);
         } else {
            var27 = var33.getClassNode().name.replace('/', '.');
            long var38 = System.currentTimeMillis() - var6;
            if (field1) {
               this.field17.field3.put(var27, var38);
            }

            return new FatalIchorError14(var27, var29);
         }
      } catch (Throwable var21) {
         throw var21;
      }
   }

   public void method5() {
      if (field1) {
         field3.info("========= Debugging class transformation times =========");
         field3.info("Time since IchorPipeline init: " + (System.currentTimeMillis() - this.field17.field9) + "ms");
         field3.info("Time spent in IchorClassLoader.getTransformedClass(): " + this.field17.field7.get() + "ms");
         field3.info("Time spent in IchorClassLoader.getTransformedClass() on the main thread: " + this.field17.field8.get() + "ms");
         long var1 = this.field17.field3.values().stream().mapToLong(var0 -> var0).sum();
         field3.info("classes: " + var1 + "ms");
         this.field17.field3.entrySet().stream().sorted(Entry.<String, Long>comparingByValue().reversed()).limit(5L).forEach(var1x -> {
            long var2 = this.field17.field4.getOrDefault(var1x.getKey(), 0L);
            field3.info("- " + var1x.getKey() + ": " + var1x.getValue() + "ms" + (var2 > 0L ? " (mixin: " + var2 + "ms)" : ""));
         });
         HashMap var3 = new HashMap();
         this.field17.field3.forEach((var1x, var2) -> {
            StringBuilder var3x;
            if (var1x.contains(".")) {
               String[] var4x = var1x.split("\\.");
               var3x = new StringBuilder(var4x[0]);

               for (int var5 = 1; var5 < Math.min(2, var4x.length - 1); var5++) {
                  var3x.append(".").append(var4x[var5]);
               }
            } else {
               var3x = new StringBuilder(var1x);
            }

            var3.merge(var3x.toString(), var2, Long::sum);
         });
         field3.info("packages:");
         var3.entrySet()
            .stream()
            .sorted(Entry.comparingByValue().reversed())
            .limit(10L)
            .forEach(var0 -> field3.info("- " + (String)var0.getKey() + ": " + var0.getValue() + "ms"));
         long var4 = this.field17.field5.values().stream().mapToLong(var0 -> var0).sum();
         if (var4 > 2000L) {
            field3.info("remapping: " + var4 + "ms");
            this.field17
               .field5
               .entrySet()
               .stream()
               .sorted(Entry.<String, Long>comparingByValue().reversed())
               .limit(15L)
               .forEach(var0 -> field3.info("- " + var0.getKey() + ": " + var0.getValue() + "ms"));
         }

         field3.info("nectar timings:");
         this.field17
            .field1
            .entrySet()
            .stream()
            .sorted(Entry.<String, Long>comparingByValue().reversed())
            .limit(10L)
            .filter(var0 -> var0.getValue() > 200L)
            .forEach(var0 -> field3.info("- " + var0.getKey() + ": " + var0.getValue() + "ms"));
         field3.info("nectar matches:");
         this.field17
            .field2
            .entrySet()
            .stream()
            .sorted(Entry.<String, Long>comparingByValue().reversed())
            .limit(10L)
            .filter(var0 -> var0.getValue() > 500L)
            .forEach(var0 -> field3.info("- " + var0.getKey() + ": " + var0.getValue()));
         field3.info("========================================================");
      }
   }

   public MixinShared method6(String var1, @Nullable byte[] var2, ClassLoader var3) {
      MixinShared var4 = new MixinShared(var1, var2);

      for (Ichor4 var6 : this.field9) {
         var4 = this.field6.method10(var6, var3, var4);
      }

      return var4;
   }

   public void method7(Path var1, Path var2, ClassLoader var3) {
      try {
         FatalIchorError9.method2(var1, var2, (var3x, var4) -> {
            String var5x = var3x.replace('.', '/').concat(".class");
            MixinShared var6 = this.method6(var5x, var4, var3);
            String var7 = var6.getResourcePath().replace(".class", "").replace('/', '.');

            FatalIchorError14 var8;
            try {
               var8 = this.method3(var7, var6.method3(), var3);
            } catch (Throwable var11) {
               field3.warn("Failed to transform " + var7 + " from " + var1 + ": " + var11.getMessage());
               var8 = new FatalIchorError14(var7, var4);
            }

            FatalIchorError14 var9 = var8;
            String var10 = FatalIchorError6.method6(var8.method1()).orElseGet(() -> this.field6.method15(this.method33().get(0), var9.className()));
            return new FatalIchorError14(var10, var8.method1());
         });
      } catch (IOException var5) {
         throw var5;
      }
   }

   public Optional<Files2_2> method8(Files3 var1) {
      return this.method34().method3().method10(var1);
   }

   public Optional<MappingSet> method9(Files3 var1, Function<Reader, MappingsReader> var2) {
      return this.method34().method3().method10(var1).map(var2x -> {
         MappingsReader var3 = (MappingsReader)var2.apply(new InputStreamReader(new ByteArrayInputStream(var2x.method2()), StandardCharsets.UTF_8));

         try {
            MappingSet var4 = var3.read();
            var3.close();
            return var4;
         } catch (IOException var6) {
            throw new FatalIchorError("Failed to parse mappings from " + var1.method4(), var6);
         }
      });
   }

   public Optional<MappingSet> method10(Files3 var1) {
      return this.method34().method3().method10(var1).map(var1x -> {
         try {
            MappingsReader var2 = Files5_2.field1.createReader(new ByteArrayInputStream(var1x.method2()));

            MappingSet var3;
            try {
               var3 = var2.read();
            } catch (Throwable var6) {
               if (var2 != null) {
                  try {
                     var2.close();
                  } catch (Throwable var5) {
                     var6.addSuppressed(var5);
                  }
               }

               throw var6;
            }

            if (var2 != null) {
               var2.close();
            }

            return var3;
         } catch (IOException var7) {
            throw new FatalIchorError("Failed to parse mappings from " + var1.method4(), var7);
         }
      });
   }

   public Optional<Path> method11(String var1) {
      return this.field11.column(var1).values().stream().findAny().flatMap(this::method12);
   }

   public Optional<Path> method12(String var1) {
      try {
         Path var2 = this.field10.classpathDir();
         Path var3 = this.field10.overridesDir();
         boolean var4 = var3 != null;
         Path var5 = null;
         if (var1.startsWith("absolute::")) {
            var5 = Paths.get(var1.substring("absolute::".length()));
         } else if (var1.endsWith(".jar")) {
            if (var4) {
               var5 = var3.resolve(var1);
               if (Files.notExists(var5)) {
                  var5 = var2.resolve(var1);
               }
            } else {
               var5 = var2.resolve(var1);
            }
         } else {
            var5 = this.method13(var1, var2);
            if (var4) {
               Path var6 = this.method13(var1, var3);
               if (var6 != null) {
                  var5 = var6;
               }
            }
         }

         return Optional.ofNullable(var5);
      } catch (Throwable var7) {
         throw var7;
      }
   }

   private Path method13(String var1, Path var2) {
      try {
         Path var3 = null;

         try (Stream var4 = Files.list(var2)) {
            Optional var5 = var4.filter(var1x -> var1x.getFileName().toString().startsWith(var1)).filter(var1x -> {
               boolean var2x = this.field10.method5() == null;
               String var3x = var1x.getFileName().toString();
               return var2x || this.field10.method5().contains(var3x);
            }).findFirst();
            if (var5.isPresent()) {
               var3 = (Path)var5.get();
            }
         }

         return var3;
      } catch (Throwable var9) {
         throw var9;
      }
   }

   public Collection<Path> method14() {
      return this.field11.values().stream().sorted().map(this::method12).flatMap(Optional::stream).collect(Collectors.toList());
   }

   public IchorModule method15(String var1) {
      return this.field11
         .column(var1)
         .keySet()
         .stream()
         .findAny()
         .flatMap(this::method21)
         .orElseThrow(() -> new IllegalArgumentException("No module found for file key " + var1));
   }

   public List<JsonObject> method16() {
      ArrayList var1 = new ArrayList();

      for (Ichor5 var3 : this.field13) {
         if (var3 instanceof Ichor5Handler_2 var4) {
            var1.addAll(var4.method2(this.field6));
         }
      }

      return var1;
   }

   public String method17(String var1, String var2) {
      String var3 = this.field12.get(var1, var2);
      return var3 == null ? var2 : var3;
   }

   public Stream<Ichor5Handler_2> method18() {
      return this.field13.stream().filter(var0 -> var0 instanceof Ichor5Handler_2).map(var0 -> (Ichor5Handler_2)var0);
   }

   public <T extends Ichor5> Optional<T> method19(Class<T> var1) {
      return this.field13.stream().filter(var1::isInstance).map(var1::cast).findFirst();
   }

   public <T extends Ichor5 & MixinMisc5> void method20(Class<T> var1, MappingSet var2, ClassProvider var3) {
      this.<Ichor5>method19(var1).ifPresent(var2x -> ((MixinMisc5)var2x).method5(var2, var3));
   }

   public boolean hasModule(String var1) {
      return this.field7.containsKey(var1);
   }

   public Optional<IchorModule> method21(String var1) {
      return Optional.ofNullable(this.field7.get(var1));
   }

   private void method22(ClassLoader var1) {
      if (this.field5 != var1) {
         if (this.field5 != null) {
            field3.info("Switching running ClassLoader from " + this.field5.getName() + " to " + var1.getName());
         }

         this.field5 = var1;
         if (var1 instanceof IchorAPI3 var2) {
            var2.method1(this.field14);
         }
      }
   }

   @Override
   public void close() {
      this.field6.close();
      this.field8.clear();
      this.field7.clear();
      this.field13.clear();
      this.field14.clear();
      this.field11.clear();
      if (field1) {
         this.field17.field6.clear();
      }
   }

   public Set<String> method23(ClassLoader var1) {
      this.field6.method2(var1);
      return this.field6
         .method19()
         .stream()
         .filter(var0 -> var0.method18().hasMixinRuntime())
         .flatMap(var0 -> var0.method20().getMixinAndTargetClasses().stream())
         .collect(Collectors.toSet());
   }

   public Set<String> getSyntheticClasses() {
      HashSet var1 = new HashSet();

      for (URLClassLoader var3 : this.field6.method19()) {
         if (var3.method18().hasMixinRuntime()) {
            var1.addAll(var3.method20().getSyntheticClasses());
         }
      }

      return var1;
   }

   public Map<String, byte[]> getExtraClassDefinitions() {
      HashMap var1 = new HashMap();

      for (URLClassLoader var3 : this.field6.method19()) {
         if (var3.method18().hasMixinRuntime()) {
            var1.putAll(var3.method20().getExtraClassDefinitions());
         }
      }

      return var1;
   }

   public void method24() {
      this.field6.field11.set(true);

      for (Ichor4 var2 : this.method33()) {
         if (var2.hasMixinRuntime()) {
            this.field6.field10.add(var2);
         }
      }

      for (URLClassLoader var4 : this.field6.method19()) {
         var4.field10 = true;
      }
   }

   public Set<String> method25(ClassLoader var1, Path var2) {
      Set var3 = this.method23(var1);
      FatalIchorError9.method6(var2, var3);

      for (Path var5 : this.method14()) {
         FatalIchorError9.method6(var5, var3);
      }

      return var3;
   }

   public AutoCloseableIterator method26(Object var1) {
      AutoCloseableIterator var2 = this.field8.get(var1);
      if (var2 == null) {
         throw new IllegalArgumentException(var1.toString() + " isn't an instance of an Ichor injection!");
      } else {
         return var2;
      }
   }

   @Nullable
   public FatalIchorError13 method27(String var1) {
      return !field1 ? null : this.field17.field6.get(var1);
   }

   @Generated
   public ClassLoader method28() {
      return this.field4;
   }

   @Nullable
   @Generated
   public ClassLoader method29() {
      return this.field5;
   }

   @Generated
   public IchorTransformer method30() {
      return this.field6;
   }

   @Generated
   public Map<String, IchorModule> method31() {
      return this.field7;
   }

   @Generated
   public Map<Object, AutoCloseableIterator> method32() {
      return this.field8;
   }

   @Generated
   public List<Ichor4> method33() {
      return this.field9;
   }

   @Generated
   public Ichor3 method34() {
      return this.field10;
   }

   @Generated
   public Table<String, String, String> method35() {
      return this.field11;
   }

   @Generated
   public List<Ichor5> method36() {
      return this.field13;
   }

   @Generated
   public Set<String> method37() {
      return this.field14;
   }

   @Generated
   public FatalIchorError11 method38() {
      return this.field15;
   }

   @Nullable
   @Generated
   public Path method39() {
      return this.field16;
   }

   @Generated
   public IchorPipeline.Data method40() {
      return this.field17;
   }

   @Generated
   public void method41(@Nullable Path var1) {
      this.field16 = var1;
   }

   public static class Data {
      @Nullable
      private final Map<String, Long> field1 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
      @Nullable
      private final Map<String, Long> field2 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
      @Nullable
      private final Map<String, Long> field3 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
      @Nullable
      public final Map<String, Long> field4 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
      @Nullable
      public final Map<String, Long> field5 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
      @Nullable
      private final Map<String, FatalIchorError13> field6 = IchorPipeline.field1 ? new ConcurrentHashMap<>() : null;
      final AtomicLong field7 = new AtomicLong(0L);
      final AtomicLong field8 = new AtomicLong(0L);
      private final long field9 = System.currentTimeMillis();

      @Nullable
      @Generated
      public Map<String, Long> method1() {
         return this.field1;
      }

      @Nullable
      @Generated
      public Map<String, Long> method2() {
         return this.field2;
      }

      @Nullable
      @Generated
      public Map<String, Long> method3() {
         return this.field3;
      }

      @Nullable
      @Generated
      public Map<String, Long> method4() {
         return this.field4;
      }

      @Nullable
      @Generated
      public Map<String, Long> method5() {
         return this.field5;
      }

      @Nullable
      @Generated
      public Map<String, FatalIchorError13> method6() {
         return this.field6;
      }

      @Generated
      public AtomicLong method7() {
         return this.field7;
      }

      @Generated
      public AtomicLong method8() {
         return this.field8;
      }

      @Generated
      public long method9() {
         return this.field9;
      }
   }
}
