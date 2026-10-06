package com.moonsworth.lunar.framework;

import com.moonsworth.lunar.config.Config;
import java.util.Optional;
import java.util.function.Function;
import lombok.Generated;

public enum FrameworkType {
   NONE("", Optional::of),
   NOTCH("notch", var0 -> {
      if (var0.startsWith("notch/") || var0.startsWith("patch/notch/")) {
         var0 = var0.replace("notch/", "");
         return Optional.of(var0);
      } else {
         return !var0.startsWith("srg/") && !var0.startsWith("patch/srg/") ? Optional.of(var0) : Optional.empty();
      }
   }),
   SRG("srg", var0 -> {
      if (var0.startsWith("srg/") || var0.startsWith("patch/srg/")) {
         var0 = var0.replace("srg/", "");
         return Optional.of(var0);
      } else {
         return !var0.startsWith("net/optifine/") && !var0.startsWith("patch/") ? Optional.of(var0) : Optional.empty();
      }
   }),
   PATCH("patch", var0 -> {
      if (var0.startsWith("srg/") || var0.startsWith("patch/srg/")) {
         return Optional.empty();
      } else {
         return !var0.startsWith("net/optifine/") && !var0.startsWith("patch/") ? Optional.of(var0) : Optional.of(var0);
      }
   });

   private final String name;
   private final Function<String, Optional<String>> fileFilter;

   public static FrameworkType from(Config var0, boolean flag) {
      if (var0.method21()) {
         return NONE;
      } else {
         return var0.method40() ? NOTCH : PATCH;
      }
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public Function<String, Optional<String>> getFileFilter() {
      return this.fileFilter;
   }

   @Generated
   FrameworkType(String text, Function<String, Optional<String>> function) {
      this.name = text;
      this.fileFilter = function;
   }
}
