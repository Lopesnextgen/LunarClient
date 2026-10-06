package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.IchorPipeline;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModContainerFactory;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ContainerType;
import net.minecraftforge.fml.common.discovery.ModCandidate;
import net.minecraftforge.fml.common.discovery.ModDiscoverer;
import net.minecraftforge.fml.common.discovery.asm.ASMModParser;

public class MixinCore {
   public static void method1(IchorPipeline var0, String var1, String var2, ModDiscoverer var3) {
      File var4 = var0.method11(var1).orElseThrow().toFile();
      Ichor6Impl.field2.info("Loading mod " + var1 + " " + var2);
      ModCandidate var5 = new ModCandidate(var4, var4, ContainerType.JAR, false, true);
      String var6 = var2.replace('.', '/') + ".class";

      try (InputStream var7 = MixinCore.class.getClassLoader().getResourceAsStream(var6)) {
         if (var7 != null) {
            ASMModParser var8 = new ASMModParser(var7);
            var8.validate();
            ASMDataTable var9 = var3.getASMTable();
            var8.sendToTable(var9, var5);
            ModContainer var10 = ModContainerFactory.instance().build(var8, var4, var5);

            try (ZipFile var11 = new ZipFile(var4)) {
               ZipEntry var12 = var11.getEntry("mcmod.info");
               if (var12 == null) {
                  throw new IllegalStateException("Couldn't find mcmod.info in " + var4.getName());
               }

               MetadataCollection var13 = MetadataCollection.from(var11.getInputStream(var12), var5.getModContainer().getName());
               var9.addContainer(var10);
               var10.bindMetadata(var13);
               Loader.instance().mods.add(var10);
            }
         }
      } catch (Exception var18) {
         throw new IllegalStateException("Couldn't load Mod " + var1 + " with class " + var2, var18);
      }
   }
}
