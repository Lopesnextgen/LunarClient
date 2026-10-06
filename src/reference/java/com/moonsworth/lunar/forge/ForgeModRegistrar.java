package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.IchorPipeline;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
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

public class ForgeModRegistrar {
   public ForgeModRegistrar() {
   }

   public static void method1(IchorPipeline ichor70, String text1, String text2, ModDiscoverer moddiscoverer3) {
      File file4 = ((Path)ichor70.method11(text1).orElseThrow()).toFile();
      Ichor6Impl.field2.info("Loading mod " + text1 + " " + text2, new Object[0]);
      ModCandidate modcandidate5 = new ModCandidate(file4, file4, ContainerType.JAR, false, true);
      String text6 = text2.replace('.', '/') + ".class";

      try (InputStream input7 = ForgeModRegistrar.class.getClassLoader().getResourceAsStream(text6)) {
         if (input7 != null) {
            ASMModParser asmmodparser8 = new ASMModParser(input7);
            asmmodparser8.validate();
            ASMDataTable asmdatatable9 = moddiscoverer3.getASMTable();
            asmmodparser8.sendToTable(asmdatatable9, modcandidate5);
            ModContainer modcontainer10 = ModContainerFactory.instance().build(asmmodparser8, file4, modcandidate5);

            try (ZipFile zipfile11 = new ZipFile(file4)) {
               ZipEntry zipentry12 = zipfile11.getEntry("mcmod.info");
               if (zipentry12 == null) {
                  throw new IllegalStateException("Couldn't find mcmod.info in " + file4.getName());
               }

               MetadataCollection metadatacollection13 = MetadataCollection.from(zipfile11.getInputStream(zipentry12), modcandidate5.getModContainer().getName());
               asmdatatable9.addContainer(modcontainer10);
               modcontainer10.bindMetadata(metadatacollection13);
               Loader.instance().mods.add(modcontainer10);
            }
         }
      } catch (Exception exception18) {
         throw new IllegalStateException("Couldn't load Mod " + text1 + " with class " + text2, exception18);
      }
   }
}
