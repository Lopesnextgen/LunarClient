package com.moonsworth.lunar.ichor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public abstract class IchorModule implements Comparable<IchorModule> {
   private String id;

   public IchorModule(String var1) {
      this.id = var1;
   }

   public abstract List<Ichor5> method1(IchorPipeline var1);

   public Map<String, String> method2(IchorPipeline var1) {
      return new HashMap<>();
   }

   public void method3(IchorPipeline var1, String text, BiConsumer<String, Supplier<byte[]>> consumer) {
      Path var4 = var1.method11(text).orElseThrow();

      try (ZipFile var5 = new ZipFile(var4.toFile())) {
         Enumeration var6 = var5.entries();

         while (var6.hasMoreElements()) {
            ZipEntry var7 = (ZipEntry)var6.nextElement();
            String var8 = var7.getName();
            consumer.accept(var8, () -> {
               try {
                  return var5.getInputStream(var7).readAllBytes();
               } catch (IOException var4x) {
                  throw new RuntimeException("couldn't read file " + var8, var4x);
               }
            });
         }
      } catch (Exception var11) {
         throw new IllegalStateException("Failed to open entries of " + var4, var11);
      }
   }

   public int method4(@NotNull IchorModule var1) {
      return this.method5().compareTo(var1.method5());
   }

   public IchorModule.Type method5() {
      return IchorModule.Type.NORMAL;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   public enum Type {
      PRE_INIT,
      INIT,
      NORMAL,
      LATE,
      LAST;
   }
}
