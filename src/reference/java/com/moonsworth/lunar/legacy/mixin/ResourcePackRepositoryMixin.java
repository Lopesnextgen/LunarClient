package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ResourcePackUtils;
import com.moonsworth.lunar.ichor.VersionGate;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository.Entry;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResourcePackRepository.class)
public abstract class ResourcePackRepositoryMixin {
   @Final
   @Shadow
   public File dirServerResourcepacks;
   @Shadow
   public List<Entry> repositoryEntriesAll;
   @Shadow
   public List<Entry> repositoryEntries;

   public ResourcePackRepositoryMixin() {
   }

   @Shadow
   public abstract List<File> getResourcePackFiles();

   @Inject(method = "deleteOldServerResourcesPacks$v1_8", at = @At("HEAD"), cancellable = true)
   @VersionGate(1)
   private void impl$deleteOldServerResourcesPacks(CallbackInfo callback1) {
      if (!this.dirServerResourcepacks.exists()) {
         callback1.cancel();
      }
   }

   @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Iterator;hasNext()Z"))
   private boolean impl$onIterateResourcePacks(Iterator iterator1) {
      return false;
   }

   @Inject(method = "<init>", at = @At("TAIL"))
   private void impl$onConstruct(File file1, File file2, IResourcePack iresourcepack3, IMetadataSerializer imetadataserializer4, GameSettings gamesettings5, CallbackInfo callback6) {
      Constructor constructor7 = Entry.class.getDeclaredConstructor(ResourcePackRepository.class, File.class);
      constructor7.setAccessible(true);

      for (String text10 : Ref.MC_VERSION >= 1 ? gamesettings5.resourcePacks : gamesettings5.resourcePacks$v1_7) {
         try {
            boolean flag11 = false;
            boolean flag12 = false;

            for (Entry entry14 : this.repositoryEntriesAll) {
               if (entry14.getResourcePackName().equals(text10) || text10.equals("file/" + entry14.getResourcePackName())) {
                  flag11 = true;
                  if (this.lunar$shouldLoadPack(gamesettings5, entry14)) {
                     this.repositoryEntries.add(entry14);
                     flag12 = true;
                  }
               }
            }

            if (!flag11) {
               File file16 = ResourcePackUtils.method2(file1, text10);
               if (file16 != null) {
                  Entry entry17 = (Entry)constructor7.newInstance(this, file16);
                  entry17.updateResourcePack();
                  flag11 = true;
                  if (this.lunar$shouldLoadPack(gamesettings5, entry17)) {
                     this.repositoryEntries.add(entry17);
                     flag12 = true;
                  }
               }
            }

            if (flag11 && !flag12) {
               LunarLogger.method5("Not loading resource pack %s because it was made for a newer version", new Object[]{text10});
            }
         } catch (Exception exception15) {
            exception15.printStackTrace();
         }
      }
   }

   @Unique
   private boolean lunar$shouldLoadPack(GameSettings gamesettings1, Entry entry2) {
      if (Ref.MC_VERSION <= 0) {
         return true;
      }

      int number3 = Ref.MC_VERSION >= 5 ? entry2.getPackFormat$v1_12() : entry2.func_183027_f();
      int number4 = Ref.MC_VERSION >= 5 ? 3 : 1;
      return number3 <= number4 || gamesettings1.incompatibleResourcePacks.contains(entry2.getResourcePackName());
   }

   @Overwrite
   public void updateRepositoryEntriesAll() {
      try {
         Constructor constructor1 = Entry.class.getDeclaredConstructor(ResourcePackRepository.class, File.class);
         constructor1.setAccessible(true);
         LinkedHashSet set2 = new LinkedHashSet();
         HashMap map3 = new HashMap();

         for (Entry entry5 : this.repositoryEntriesAll) {
            map3.put(entry5.hashCode(), entry5);
         }

         for (File file14 : this.getResourcePackFiles()) {
            try {
               Entry entry6 = (Entry)constructor1.newInstance(this, file14);
               int index7 = entry6.hashCode();
               if (!map3.containsKey(index7)) {
                  try {
                     entry6.updateResourcePack();
                     set2.add(entry6);
                  } catch (Exception exception9) {
                     set2.remove(entry6);
                  }
               } else {
                  set2.add((Entry)map3.get(index7));
               }
            } catch (Exception exception10) {
               exception10.printStackTrace();
            }
         }

         for (Entry entry15 : map3.values()) {
            if (!set2.contains(entry15)) {
               entry15.closeResourcePack();
            }
         }

         this.repositoryEntriesAll = new ArrayList<>(set2);
      } catch (Exception exception11) {
         exception11.printStackTrace();
      }
   }
}
