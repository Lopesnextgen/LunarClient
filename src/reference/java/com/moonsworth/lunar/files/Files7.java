package com.moonsworth.lunar.files;

import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.Generated;

public class Files7 implements Files5.Extension, Consumer<Files6> {
   private static final boolean field1 = System.getProperty("ichor.mx.debug", "true").equals("true");
   public static final Files4 field2 = Files4.method1("MRegistry");
   private final Files4Base3 field3;
   private final Set<Files_3> field4;
   private final List<SoftReference<Files2_2>> field5;
   private final List<Files5> field6;
   private final Map<Files3, Set<Files3>> field7;
   private final Map<Files3, Files_3> field8;

   public Files7(Files4_2 var1) {
      this.field3 = new Files4Base3(var1, this);
      this.field4 = Collections.synchronizedSet(new LinkedHashSet<>());
      this.field5 = Collections.synchronizedList(new ArrayList<>());
      this.field6 = Collections.synchronizedList(new ArrayList<>());
      this.field7 = new ConcurrentHashMap<>();
      this.field8 = new HashMap<>();
   }

   public void method1(Files2_2 var1) {
      this.field5.add(new SoftReference<>(var1));
   }

   private Optional<Files2_2> method2(Predicate<Files2_2> var1) {
      synchronized (this.field5) {
         Iterator var3 = this.field5.iterator();

         while (var3.hasNext()) {
            SoftReference var4 = (SoftReference)var3.next();
            Files2_2 var5 = (Files2_2)var4.get();
            if (var5 == null) {
               var3.remove();
            } else if (var1.test(var5)) {
               return Optional.of(var5);
            }
         }
      }

      return Optional.empty();
   }

   private void method3(Files6 var1) {
      synchronized (this.field5) {
         Iterator var3 = this.field5.iterator();

         while (var3.hasNext()) {
            SoftReference var4 = (SoftReference)var3.next();
            Files2_2 var5 = (Files2_2)var4.get();
            if (var5 == null) {
               var3.remove();
            } else {
               var1.method1(var5);
            }
         }
      }
   }

   public void method4(Files_3 var1) {
      this.field4.add(var1);
   }

   public void method5(Files_3 var1, Files3 var2) {
      this.method4(var1);
      this.field7.put(var2, var1.method2(this));
      this.field8.put(var2, var1);

      for (Files3 var4 : var1.method2(this)) {
         this.field8.put(var4, var1);
         this.field8.put(var4.method1(this), var1);
      }
   }

   public void method6(Files5 var1) {
      this.field6.add(var1);
   }

   private Files4$Data method7(Files_3 var1) {
      long var2 = System.currentTimeMillis();

      try {
         Files4$Data var4 = this.method8(var1);
         if (var4 != null) {
            long var11 = System.currentTimeMillis() - var2;
            if (var11 > 500L) {
               field2.info("[Timing] compute(" + var1.getId() + ") cache-hit in " + var11 + "ms");
            }

            return var4;
         } else {
            var1.getParents().forEach(var1x -> {
               for (Files3 var3 : var1x.method2(this)) {
                  if (this.method9(var3).isEmpty()) {
                     this.method7(var1x);
                     return;
                  }
               }
            });
            if (this.field3.method2(this.field6, var1, this) && var1 instanceof FilesImpl) {
               for (Files3 var6 : var1.method5().apply(this)) {
                  Files3 var7 = var6.method1(this);
                  Files_3 var8 = this.field8.get(var7);
                  if (var8 != null) {
                     var1 = var8;
                     var1.getParents().forEach(this::method7);
                     break;
                  }
               }
            }

            Files4$Data var10 = this.field3.method5(this.field6, var1, this);
            long var12 = System.currentTimeMillis() - var2;
            if (var12 > 500L) {
               field2.info("[Timing] compute(" + var1.getId() + ") full-compute in " + var12 + "ms");
            }

            return var10;
         }
      } catch (Exception var9) {
         throw new IllegalStateException("Failed to compute " + var1.getId(), var9);
      }
   }

   private Files4$Data method8(Files_3 var1) {
      Files6 var2 = new Files6(this.field6, var1);
      this.method3(var2);
      ArrayList var3 = new ArrayList();
      boolean var4 = false;

      for (Files3 var6 : var1.method2(this)) {
         Files3 var7 = var6.method1(var2);
         if (var7.method3(var2)) {
            Optional var8 = this.field3.method4(var2, var7);
            if (!var8.isPresent()) {
               var4 = true;
               break;
            }

            Files2_2 var9 = (Files2_2)var8.get();
            var3.add(var9);
         }
      }

      if (!var4 && !var3.isEmpty()) {
         for (Files2_2 var11 : var3) {
            this.field3.method5(var2, var11);
         }

         return new Files4$Data(var3, null);
      } else {
         return null;
      }
   }

   public Optional<Files2_2> method9(Files3 var1) {
      Files3 var2 = var1.method1(this);
      Optional var3 = this.method2(var2x -> {
         Files3 var3x = var2x.method1();
         return var1.equals(var3x) || var2.equals(var3x);
      });
      if (var3.isPresent()) {
         return var3;
      }

      Set var4 = this.field7.get(var1);
      if (var4 != null) {
         Optional var5 = this.method2(var2x -> {
            Files3 var3x = var2x.method1();

            for (Files3 var5x : var4) {
               Files3 var6 = var5x.method1(this);
               if (var5x.equals(var3x) || var6.equals(var3x)) {
                  return true;
               }
            }

            return false;
         });
         if (var5.isPresent()) {
            return var5;
         }
      }

      return Optional.empty();
   }

   public Optional<Files2_2> method10(Files3 var1) {
      long var2 = System.currentTimeMillis();
      Files3 var4 = var1.method1(this);
      Optional var5 = this.method9(var1);
      if (var5.isPresent()) {
         return var5;
      }

      synchronized (this.field4) {
         for (Files_3 var8 : this.field4) {
            Set var9 = var8.method2(this);
            if (var9.contains(var1) || var9.contains(var4)) {
               Files4$Data var10 = this.method7(var8);
               Collection var11 = var10.method2();
               if (var11 == null) {
                  if (var10.method3() != null && !var10.method3().isEmpty() && field1) {
                     field2.info("Failed to get data for " + var4.method4());

                     for (Files3 var21 : var10.method3()) {
                        field2.info("missing data = " + var21);
                     }
                  }
               } else {
                  for (Files2_2 var13 : var11) {
                     Files3 var14 = var13.method1();
                     if (var14 != null && (var14.equals(var1) || var14.equals(var4))) {
                        long var15 = System.currentTimeMillis() - var2;
                        if (var15 > 500L) {
                           field2.info("[Timing] getData(" + var4.method4() + ") via " + var8.getId() + " in " + var15 + "ms");
                        }

                        return Optional.of(var13);
                     }
                  }
               }
            }
         }
      }

      long var19 = System.currentTimeMillis() - var2;
      if (var19 > 500L) {
         field2.info("[Timing] getData(" + var4.method4() + ") fallback in " + var19 + "ms");
      }

      return this.method9(var1);
   }

   public Optional<Files4_2> method11(Function<Files4_2, Boolean> var1) {
      Files4Base var2 = this.field3;

      while (var2 != null) {
         if ((Boolean)var1.apply(var2)) {
            return Optional.of(var2);
         }

         if (var2.method3() instanceof Files4Base var4) {
            var2 = var4;
         } else {
            var2 = null;
         }
      }

      return Optional.empty();
   }

   public Optional<Path> method12(Files_3 var1, Files3 var2) {
      return this.method11(var0 -> var0 instanceof Files4Base2).map(var3 -> {
         Files4Base2 var4 = (Files4Base2)var3;
         Files6 var5 = var4.method5(this.field6, var1);
         this.method3(var5);
         return var4.method1(var5, var2);
      });
   }

   @Override
   public Collection<Files5> method1() {
      return this.field6;
   }

   public void method14(Files6 var1) {
      this.method3(var1);
   }

   @Generated
   public Files4Base3 method15() {
      return this.field3;
   }

   @Generated
   public Set<Files_3> method16() {
      return this.field4;
   }

   @Generated
   public List<Files5> method17() {
      return this.field6;
   }

   @Generated
   public Map<Files3, Set<Files3>> method18() {
      return this.field7;
   }

   @Generated
   public Map<Files3, Files_3> method19() {
      return this.field8;
   }
}
