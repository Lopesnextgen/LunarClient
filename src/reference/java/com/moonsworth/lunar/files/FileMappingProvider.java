package com.moonsworth.lunar.files;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.cadixdev.lorenz.io.MappingsReader;
import org.cadixdev.lorenz.io.srg.SrgReader;
import org.cadixdev.lorenz.io.srg.csrg.CSrgReader;
import org.cadixdev.lorenz.io.srg.tsrg.TSrgReader;
import org.cadixdev.lorenz.io.srg.xsrg.XSrgReader;

public class FileMappingProvider extends DelegatingMappingProvider {
   private final Path field2;

   public FileMappingProvider(Files4_2 files4_21, Path path2) {
      super(files4_21);

      try {
         this.field2 = path2;
         if (!Files.exists(path2)) {
            Files.createDirectories(path2);
         }
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   public Path method1(Files6 files61, Files3 files32) {
      String text3 = files61.method5().stream().filter(arg0 -> arg0.key().equals("mcVer")).findFirst().orElseThrow().value();
      String text4 = text3
         + "/"
         + files32.namespace()
         + "/"
         + files61.method6().getId()
         + "-"
         + Integer.toHexString(files61.method6().hashCode())
         + "/"
         + files32.method1(files61).method4();
      return this.field2.resolve(text4);
   }

   public Optional<ArtifactData> method4(Files6 files61, Files3 files32) {
      try {
         Path path3 = this.method1(files61, files32);
         byte[] items4 = this.method4(path3);
         if (!files32.method5() && items4 != null) {
            return Optional.of(new ArtifactData(files32, items4));
         }

         Optional optional5 = super.method4(files61, files32);
         optional5.ifPresent(arg2x -> this.method5(files61, arg2x));
         return optional5.isEmpty() && items4 != null ? Optional.of(new ArtifactData(files32, items4)) : optional5;
      } catch (Throwable exception6) {
         throw exception6;
      }
   }

   public void method5(Files6 files61, ArtifactData files2_22) {
      try {
         if (files2_22.method1() != null) {
            Path path3 = this.method1(files61, files2_22.method1());
            Files.createDirectories(path3.getParent());
            Files.write(path3, files2_22.method2());
         }

         super.method5(files61, files2_22);
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   @Nullable
   public byte[] method4(Path path1) {
      try {
         if (Files.exists(path1)) {
            try {
               byte[] items2 = Files.readAllBytes(path1);
               String text3 = path1.toString();
               if (text3.endsWith(".srg")) {
                  this.method5(items2, SrgReader::new);
               } else if (text3.endsWith(".xsrg")) {
                  this.method5(items2, XSrgReader::new);
               } else if (text3.endsWith(".csrg")) {
                  this.method5(items2, CSrgReader::new);
               } else if (text3.endsWith(".tsrg")) {
                  this.method5(items2, TSrgReader::new);
               } else if (text3.endsWith(".kin")) {
                  this.method6(items2);
               }

               return items2;
            } catch (Exception exception4) {
               if (exception4.getMessage() != null && !exception4.getMessage().contains("tsrg2")) {
                  new IllegalStateException("Failed to read data in " + path1, exception4).printStackTrace();
                  Files.delete(path1);
               }
            }
         }

         return null;
      } catch (Throwable exception5) {
         throw exception5;
      }
   }

   private void method5(byte[] items1, Function<Reader, MappingsReader> function2) {
      try {
         MappingsReader mappingsreader3 = (MappingsReader)function2.apply(new InputStreamReader(new ByteArrayInputStream(items1), StandardCharsets.UTF_8));
         mappingsreader3.read();
         mappingsreader3.close();
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   private void method6(byte[] items1) {
      try {
         MappingsReader mappingsreader2 = MappingFormats.KIN.createReader(new ByteArrayInputStream(items1));

         try {
            mappingsreader2.read();
         } catch (Throwable exception6) {
            if (mappingsreader2 != null) {
               try {
                  mappingsreader2.close();
               } catch (Throwable exception5) {
                  exception6.addSuppressed(exception5);
               }
            }

            throw exception6;
         }

         if (mappingsreader2 != null) {
            mappingsreader2.close();
         }
      } catch (Throwable exception7) {
         throw exception7;
      }
   }
}
