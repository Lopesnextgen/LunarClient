package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.files.Files7;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;

public class IchorOptions {
   private final Path field1;
   private final Path field2;
   @Nullable
   private final Path field3;
   private final Files7 field4;
   @Nullable
   private final List<MixinSourceArchive> field5;
   @Nullable
   private final List<String> field6;
   @Nullable
   private final List<String> field7;
   private final Map<String, Object> field8;

   public IchorOptions(
      Path path1,
      Path path2,
      @Nullable Path path3,
      Files7 files74,
      @Nullable List<MixinSourceArchive> list5,
      @Nullable List<String> list6,
      @Nullable List<String> list7,
      Map<String, Object> map
   ) {
      this.field1 = path1;
      this.field2 = path2;
      this.field3 = path3;
      this.field4 = files74;
      this.field5 = list5;
      this.field6 = list6;
      this.field7 = list7;
      this.field8 = map;
   }

   Optional<MixinClassSource> method1(Collection<String> list) {
      if (this.field5 != null) {
         for (MixinSourceArchive mixinextra3 : this.field5) {
            Optional optional4 = mixinextra3.method1(list);
            if (optional4.isPresent()) {
               return optional4;
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
   public List<MixinSourceArchive> method4() {
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
