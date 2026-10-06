package com.moonsworth.lunar.files;

import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteMappingProvider extends Files4_2 {
   private final Path field1;
   private final boolean field2;
   private static final Map<String, SoftReference<byte[]>> field3 = new ConcurrentHashMap<>();

   public RemoteMappingProvider(Path path1) {
      this(path1, "true".equals(System.getProperty("mx.offline")));
   }

   public RemoteMappingProvider(Path path1, boolean flag2) {
      try {
         this.field1 = path1;
         this.field2 = flag2;
         Files.createDirectories(path1);
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   public Optional<ArtifactData> method4(Files6 files61, Files3 files32) {
      String text3 = files32.url();
      if (text3 == null) {
         return Optional.empty();
      }

      if (text3.contains("${")) {
         return Optional.empty();
      }

      String text4 = this.method2(text3);
      Path path5 = this.field1.resolve(text4);
      SoftReference softreference6 = field3.get(text4);
      byte[] items7 = softreference6 != null ? (byte[])softreference6.get() : null;

      try {
         if (items7 == null) {
            if (Files.exists(path5) && !files32.method5()) {
               long number14 = System.currentTimeMillis();
               items7 = Files.readAllBytes(path5);
               long number15 = System.currentTimeMillis() - number14;
               if (number15 > 100L) {
                  Files7.field2.info("Reading from file " + path5 + " took " + number15 + "ms", new Object[0]);
               }
            } else {
               if (this.field2) {
                  return Optional.empty();
               }

               long number8 = System.currentTimeMillis();

               try {
                  items7 = MappingDownloader.method3(text3);
               } catch (MappingException filesexception12) {
               }

               if (items7 == null) {
                  return Optional.empty();
               }

               long number10 = System.currentTimeMillis() - number8;
               if (number10 > 100L) {
                  Files7.field2.info("Reading from URL " + text3 + " took " + number10 + "ms", new Object[0]);
               }

               if (!files32.method5()) {
                  Files.write(path5, items7);
               }
            }

            field3.put(text4, new SoftReference<>(items7));
         }

         return Optional.of(new ArtifactData(files32, items7));
      } catch (Exception exception13) {
         return Optional.empty();
      }
   }

   private String method2(String text1) {
      return text1.replaceAll("[^a-zA-Z0-9-_.]", "_");
   }

   public void method5(Files6 files61, ArtifactData files2_22) {
   }
}
