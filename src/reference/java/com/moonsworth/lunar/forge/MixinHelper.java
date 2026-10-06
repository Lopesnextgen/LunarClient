package com.moonsworth.lunar.forge;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.Ichor5Handler_2;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import lombok.Generated;

public class MixinHelper extends Ichor5Handler_2 {
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

   public MixinHelper(String var1, Path var2) {
      super(var1, null, Ichor4Type.MIXIN);
      this.field12 = var2;
   }

   @Override
   public void loadIchor(IchorTransformer var1) {
      try {
         super.loadIchor(var1);
         this.field13 = new ArrayList<>();
         this.field14 = new ArrayList<>();
         Optional var2 = this.method3(var1.method20());
         if (var2.isPresent()) {
            JarFile var3 = new JarFile(((Path)var2.orElseThrow()).toFile());

            try {
               Enumeration var4 = var3.entries();

               while (var4.hasMoreElements()) {
                  JarEntry var5 = (JarEntry)var4.nextElement();
                  String var6 = var5.getName();
                  if (var6.equals("META-INF/MANIFEST.MF")) {
                     String[] var18 = new String(var3.getInputStream(var5).readAllBytes()).split("\n");

                     for (String var22 : var18) {
                        if (var22.startsWith("MixinConfigs: ")) {
                           String var12 = var22.substring("MixinConfigs: ".length()).trim();
                           this.field13 = List.of(var12);
                           Ichor6Iterator.field2.info(this.key + " mixinConfigs: " + var12);
                        } else if (var22.startsWith("TweakClass: ") && !var22.contains("TweakClass: org.spongepowered.asm.launch.MixinTweaker")) {
                           this.field17 = var22.substring("TweakClass: ".length()).trim();
                           Ichor6Iterator.field2.info(this.key + " tweakClass: " + this.field17);
                        } else if (var22.startsWith("FMLCorePlugin: ")) {
                           this.field16 = var22.substring("FMLCorePlugin: ".length()).trim();
                           Ichor6Iterator.field2.info(this.key + " fmlCorePlugin: " + this.field16);
                           Ichor6Iterator.method4(this.field16);
                        }
                     }
                  } else if (var6.equals("META-INF/mods.toml")) {
                     String[] var17 = new String(var3.getInputStream(var5).readAllBytes()).split("\n");

                     for (String var11 : var17) {
                        if (var11.startsWith("version=")) {
                           this.version = var11.substring("version=".length()).trim();
                        }
                     }
                  } else if (var6.contains("refmap") && var5.getName().endsWith(".json")) {
                     JsonObject var16 = JsonParser.parseReader(new InputStreamReader(var3.getInputStream(var5))).getAsJsonObject();
                     Ichor6Iterator.field2.info("ForgeMod " + this.key + " has refmap " + var5.getName());
                     this.field14.add(var16);
                  } else if (var6.endsWith(".class")) {
                     byte[] var7 = var3.getInputStream(var5).readAllBytes();
                     if (FatalIchorError6.method19(FatalIchorError6.method16(var7, 0), "Lnet/minecraftforge/fml/common/Mod;")) {
                        this.field15 = var6.substring(0, var6.length() - ".class".length());
                     }
                  }
               }
            } catch (Throwable var14) {
               try {
                  var3.close();
               } catch (Throwable var13) {
                  var14.addSuppressed(var13);
               }

               throw var14;
            }

            var3.close();
            if (this.field15 == null) {
               Ichor6Iterator.field2.warn("ForgeMod " + this.key + " does not have a main class!");
            }
         } else {
            Ichor6Iterator.field2.warn("Missing jar file for " + this.key);
         }
      } catch (Throwable var15) {
         throw var15;
      }
   }

   @Override
   public List<String> method1(IchorTransformer var1) {
      try {
         return this.field13;
      } catch (Throwable var3) {
         throw var3;
      }
   }

   @Override
   public List<JsonObject> method2(IchorTransformer var1) {
      return this.field14;
   }

   @Override
   public Optional<Path> method3(IchorPipeline var1) {
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
