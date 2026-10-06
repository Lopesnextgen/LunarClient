package com.moonsworth.lunar.legacy.wrapper;

import com.google.common.collect.Lists;
import com.moonsworth.lunar.bridge.GuiScreenResourcePacksBridge;
import com.moonsworth.lunar.bridge.ResourcePackFormat;
import com.moonsworth.lunar.bridge.minecraft.GuiResourcePackListBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ResourcePackUtils;
import java.io.File;
import java.util.List;
import java.util.Map;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository.Entry;
import net.minecraft.util.ResourceLocation;

public class ResourcePackListEntryIterator extends ResourcePackListEntry {
   private final File field1;
   public final Entry field2;
   private final List<ResourcePackListEntry> field3 = Lists.newArrayList();
   private int field4;
   private int field5;

   public ResourcePackListEntryIterator(
      GuiScreenResourcePacks guiscreenresourcepacks1, File file2, File file3, Entry entry4, List<ResourcePackListEntry> list5, Map<File, ResourcePackListEntryIterator> map6
   ) {
      super(guiscreenresourcepacks1);
      this.field1 = file2;
      this.field2 = entry4;
      map6.put(file2, this);
      this.field3.add(new ResourcePackListEntryImpl(guiscreenresourcepacks1, file3.getName(), list5));
      String[] items7 = file2.list(ResourcePackUtils.method7());

      for (String text11 : items7) {
         File file12 = new File(file2, text11);
         this.field3.add(new ResourcePackListEntryIterator(guiscreenresourcepacks1, file12, file2, null, this.field3, map6));
      }

      ResourcePackRepository resourcepackrepository15 = new ResourcePackRepository(
         file2,
         new File(this.mc.mcDataDir, "server-resource-packs"),
         this.mc.mcDefaultResourcePack,
         this.mc.metadataSerializer_,
         Minecraft.getMinecraft().gameSettings
      );
      int number16 = Ref.MC_VERSION >= 5 ? 3 : 1;

      for (Entry entry19 : Ref.MC_VERSION >= 1 ? resourcepackrepository15.repositoryEntriesAll : resourcepackrepository15.repositoryEntriesAll$v1_7) {
         List list13 = Ref.MC_VERSION >= 1 ? this.mc.gameSettings.resourcePacks : this.mc.gameSettings.resourcePacks$v1_7;
         if (!list13.contains(entry19.getResourcePack().getPackName())) {
            int number14 = Ref.MC_VERSION >= 5
               ? entry19.getPackFormat$v1_12()
               : (Ref.MC_VERSION >= 1 ? entry19.func_183027_f() : entry19.rePackMetadataSection.getPackFormat());
            if (Ref.MC_VERSION <= 0 || number14 == number16 || (Boolean)Client.method109().method40().method57().method13().get()) {
               this.field3.add(new ResourcePackListEntryFound(guiscreenresourcepacks1, entry19));
            }
         }
      }

      this.method1();
   }

   public void method1() {
      this.field5 = method2(this);
      this.field4 = method3(this);
   }

   public int func_183019_a() {
      return ResourcePackFormat.v1_6$1_8.getId();
   }

   public int getResourcePackFormat$v1_12() {
      return ResourcePackFormat.v1_11$1_12.getId();
   }

   public String getResourcePackDescription$v1_12() {
      return this.field5
         + " "
         + (this.field5 != 0 && this.field5 <= 1 ? "Folder" : "Folders")
         + " / "
         + this.field4
         + " "
         + (this.field4 != 0 && this.field4 <= 1 ? "Pack" : "Packs");
   }

   public String func_148311_a() {
      return this.getResourcePackDescription$v1_12();
   }

   public String getResourcePackName() {
      return this.field1.getName();
   }

   public String func_148312_b() {
      return this.getResourcePackName();
   }

   public void bindResourcePackIcon$v1_12() {
      this.mc.getTextureManager().bindTexture((ResourceLocation)ResourcePackUtils.method6());
   }

   public void func_148313_c() {
      this.bindResourcePackIcon$v1_12();
   }

   public boolean mousePressed(int number1, int number2, int number3, int number4, int number5, int number6) {
      if (Ref.MC_VERSION >= 1) {
         this.resourcePacksGUI.availableResourcePacks = this.field3;
      } else {
         this.resourcePacksGUI.field_146966_g$v1_7 = this.field3;
      }

      ((GuiResourcePackListBridge)this.resourcePacksGUI.availableResourcePacksList).setUnderlyingList(this.field3);
      this.resourcePacksGUI.keyTyped('\u0000', 0);
      ((GuiScreenResourcePacksBridge)this.resourcePacksGUI).bridge$handlePackSwapList();
      return false;
   }

   private static int method2(ResourcePackListEntryIterator resourcepacklistentryiterator0) {
      int number1 = 0;

      for (ResourcePackListEntry resourcepacklistentry3 : resourcepacklistentryiterator0.field3) {
         if (resourcepacklistentry3 instanceof ResourcePackListEntryIterator) {
            number1 += 1 + method2((ResourcePackListEntryIterator)resourcepacklistentry3);
         }
      }

      return number1;
   }

   private static int method3(ResourcePackListEntryIterator resourcepacklistentryiterator0) {
      int index1 = 0;

      for (ResourcePackListEntry resourcepacklistentry3 : resourcepacklistentryiterator0.field3) {
         if (resourcepacklistentry3 instanceof ResourcePackListEntryIterator) {
            index1 += method3((ResourcePackListEntryIterator)resourcepacklistentry3);
         } else if (!(resourcepacklistentry3 instanceof ResourcePackListEntryImpl)) {
            index1++;
         }
      }

      return index1;
   }

   @Generated
   public List<ResourcePackListEntry> method4() {
      return this.field3;
   }
}
