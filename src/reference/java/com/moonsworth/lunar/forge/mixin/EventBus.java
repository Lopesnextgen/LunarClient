package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.forge.Ichor6Impl;
import com.moonsworth.lunar.forge.Ichor6Iterator;
import com.moonsworth.lunar.ichor.IchorPipeline;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
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
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class EventBus {
   public EventBus() {
   }

   public static void method1(IchorPipeline ichor70, String text1, String text2, ModDiscoverer moddiscoverer3) {
      File file4 = ((Path)ichor70.method11(text1).orElseThrow()).toFile();
      Ichor6Impl.field2.info("Loading mod " + text1 + " " + text2, new Object[0]);
      ModCandidate modcandidate5 = new ModCandidate(file4, file4, ContainerType.JAR, false, true);
      String text6 = text2.replace('.', '/') + ".class";

      try (InputStream input7 = EventBus.class.getClassLoader().getResourceAsStream(text6)) {
         if (input7 != null) {
            ASMModParser asmmodparser8 = new ASMModParser(input7);
            asmmodparser8.validate();
            ASMDataTable asmdatatable9 = moddiscoverer3.getASMTable();
            asmmodparser8.sendToTable(asmdatatable9, modcandidate5);
            ModContainer modcontainer10 = ModContainerFactory.instance().build(asmmodparser8, file4, modcandidate5);

            MetadataCollection metadatacollection11;
            try (ZipFile zipfile12 = new ZipFile(file4)) {
               ZipEntry zipentry13 = zipfile12.getEntry("mcmod.info");
               if (zipentry13 == null) {
                  throw new IllegalStateException("Couldn't find mcmod.info in " + file4.getName());
               }

               metadatacollection11 = MetadataCollection.from(zipfile12.getInputStream(zipentry13), modcandidate5.getModContainer().getName());
            } catch (Exception exception18) {
               metadatacollection11 = new MetadataCollection();
            }

            asmdatatable9.addContainer(modcontainer10);
            if (metadatacollection11 != null) {
               modcontainer10.bindMetadata(metadatacollection11);
            }

            Loader.instance().mods.add(modcontainer10);
         }
      } catch (Exception exception20) {
         throw new IllegalStateException("Couldn't load Mod " + text1 + " with class " + text2, exception20);
      }
   }

   public static List<IFMLLoadingPlugin> method2(List<String> list0, ClassLoader classloader1) {
      List list2 = Ichor6Iterator.method6(classloader1);

      for (IFMLLoadingPlugin ifmlloadingplugin4 : list2) {
         String text5 = ifmlloadingplugin4.getModContainerClass();
         if (text5 != null) {
            list0.add(text5);
         }
      }

      return list2;
   }
}
