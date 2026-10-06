package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.files.Files7;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;

public class Ichor3 {
   private final Path field1;
   private final Path field2;
   @Nullable
   private final Path field3;
   private final Files7 field4;
   @Nullable
   private final List<MixinExtra> field5;
   @Nullable
   private final List<String> field6;
   @Nullable
   private final List<String> field7;
   private final Map<String, Object> field8;

   public Ichor3(
      Path var1,
      Path path,
      @Nullable Path var3,
      Files7 var4,
      @Nullable List<MixinExtra> var5,
      @Nullable List<String> var6,
      @Nullable List<String> var7,
      Map<String, Object> map
   ) {
      this.field1 = var1;
      this.field2 = path;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = map;
   }

   Optional<MixinExtra2> method1(Collection<String> var1) {
      if (this.field5 != null) {
         for (MixinExtra var3 : this.field5) {
            Optional var4 = var3.method1(var1);
            if (var4.isPresent()) {
               return var4;
            }
         }
      }

      return Optional.empty();
   }

   public Path classpathDir() {
      return this.field1;
   }

   public Path method2() {
      return this.field2;
   }

   @Nullable
   public Path overridesDir() {
      return this.field3;
   }

   public Files7 method3() {
      return this.field4;
   }

   @Nullable
   public List<MixinExtra> method4() {
      return this.field5;
   }

   @Nullable
   public List<String> classesToDump() {
      return this.field6;
   }

   @Nullable
   public List<String> method5() {
      return this.field7;
   }

   public Map<String, Object> method6() {
      return this.field8;
   }
}
