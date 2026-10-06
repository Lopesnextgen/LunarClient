package com.moonsworth.lunar.forge;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.util.AsmUtils;
import com.moonsworth.lunar.loader.PipelineStage;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import lombok.Generated;

public class ForgeModMixinScanner extends Ichor5Handler_2 {
   private static final String field2 = "MixinConfigs: ";
   private static final String field3 = "TweakClass: ";
   private static final String field4 = "TweakClass: org.spongepowered.asm.launch.MixinTweaker";
   private static final String field5 = "ForceLoadAsMod: ";
   private static final String field6 = "FMLCorePluginContainsFMLMod: ";
   private static final String field7 = "FMLCorePlugin: ";
   private static final String field8 = "TweakOrder: ";
   private static final String field9 = "ModSide: ";
   private static final String field10 = "Lnet/minecraftforge/fml/common/Mod;";
   private static final String field11 = "version=";
   private final Path field12;
   private List<String> field13 = List.of();
   private List<JsonObject> field14 = List.of();
   private String field15 = null;
   private String field16 = null;
   private String field17 = null;

   public ForgeModMixinScanner(String text1, Path path2) {
      super(text1, null, PipelineStage.MIXIN);
      this.field12 = path2;
   }

   public void loadIchor(IchorTransformer autocloseableiterator21) {
      try {
         super.loadIchor(autocloseableiterator21);
         this.field13 = new ArrayList<>();
         this.field14 = new ArrayList<>();
         Optional optional2 = this.method3(autocloseableiterator21.method20());
         if (optional2.isPresent()) {
            JarFile jarfile3 = new JarFile(((Path)optional2.orElseThrow()).toFile());

            try {
               Enumeration enumeration4 = jarfile3.entries();

               while (enumeration4.hasMoreElements()) {
                  JarEntry jarentry5 = (JarEntry)enumeration4.nextElement();
                  String text6 = jarentry5.getName();
                  if (text6.equals("META-INF/MANIFEST.MF")) {
                     String[] items18 = new String(jarfile3.getInputStream(jarentry5).readAllBytes()).split("\n");

                     for (String text22 : items18) {
                        if (text22.startsWith("MixinConfigs: ")) {
                           String text12 = text22.substring("MixinConfigs: ".length()).trim();
                           this.field13 = List.of(text12);
                           Ichor6Iterator.field2.info(this.key + " mixinConfigs: " + text12, new Object[0]);
                        } else if (text22.startsWith("TweakClass: ") && !text22.contains("TweakClass: org.spongepowered.asm.launch.MixinTweaker")) {
                           this.field17 = text22.substring("TweakClass: ".length()).trim();
                           Ichor6Iterator.field2.info(this.key + " tweakClass: " + this.field17, new Object[0]);
                        } else if (text22.startsWith("FMLCorePlugin: ")) {
                           this.field16 = text22.substring("FMLCorePlugin: ".length()).trim();
                           Ichor6Iterator.field2.info(this.key + " fmlCorePlugin: " + this.field16, new Object[0]);
                           Ichor6Iterator.method4(this.field16);
                        }
                     }
                  } else if (text6.equals("META-INF/mods.toml")) {
                     String[] items17 = new String(jarfile3.getInputStream(jarentry5).readAllBytes()).split("\n");

                     for (String text11 : items17) {
                        if (text11.startsWith("version=")) {
                           this.version = text11.substring("version=".length()).trim();
                        }
                     }
                  } else if (text6.contains("refmap") && jarentry5.getName().endsWith(".json")) {
                     JsonObject json16 = JsonParser.parseReader(new InputStreamReader(jarfile3.getInputStream(jarentry5))).getAsJsonObject();
                     Ichor6Iterator.field2.info("ForgeMod " + this.key + " has refmap " + jarentry5.getName(), new Object[0]);
                     this.field14.add(json16);
                  } else if (text6.endsWith(".class")) {
                     byte[] items7 = jarfile3.getInputStream(jarentry5).readAllBytes();
                     if (AsmUtils.method19(AsmUtils.method16(items7, 0), "Lnet/minecraftforge/fml/common/Mod;")) {
                        this.field15 = text6.substring(0, text6.length() - ".class".length());
                     }
                  }
               }
            } catch (Throwable exception14) {
               try {
                  jarfile3.close();
               } catch (Throwable exception13) {
                  exception14.addSuppressed(exception13);
               }

               throw exception14;
            }

            jarfile3.close();
            if (this.field15 == null) {
               Ichor6Iterator.field2.warn("ForgeMod " + this.key + " does not have a main class!", new Object[0]);
            }
         } else {
            Ichor6Iterator.field2.warn("Missing jar file for " + this.key, new Object[0]);
         }
      } catch (Throwable exception15) {
         throw exception15;
      }
   }

   public List<String> method1(IchorTransformer autocloseableiterator21) {
      try {
         return this.field13;
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   public List<JsonObject> method2(IchorTransformer autocloseableiterator21) {
      return this.field14;
   }

   public Optional<Path> method3(IchorPipeline ichor71) {
      return Optional.of(this.field12);
   }

   @Generated
   public String method4() {
      return this.field15;
   }

   @Generated
   public String method6() {
      return this.field16;
   }

   @Generated
   public String method7() {
      return this.field17;
   }
}
