package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.GuiScreenResourcePacksBridge;
import com.moonsworth.lunar.bridge.ResourcePackFormat;
import com.moonsworth.lunar.bridge.minecraft.GuiResourcePackListBridge;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ResourcePackUtils;
import java.util.List;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.util.ResourceLocation;

public class ResourcePackListEntryImpl extends ResourcePackListEntry {
   private final String field1;
   private final List<ResourcePackListEntry> field2;

   public ResourcePackListEntryImpl(GuiScreenResourcePacks guiscreenresourcepacks1, String text2, List<ResourcePackListEntry> list3) {
      super(guiscreenresourcepacks1);
      this.field1 = text2;
      this.field2 = list3;
   }

   public int getResourcePackFormat$v1_12() {
      return ResourcePackFormat.v1_11$1_12.getId();
   }

   public int func_183019_a() {
      return ResourcePackFormat.v1_6$1_8.getId();
   }

   public String getResourcePackDescription$v1_12() {
      return "Back to " + this.field1;
   }

   public String func_148311_a() {
      return this.getResourcePackDescription$v1_12();
   }

   public String getResourcePackName() {
      return "..";
   }

   public String func_148312_b() {
      return this.getResourcePackName();
   }

   public void bindResourcePackIcon$v1_12() {
      this.mc.getTextureManager().bindTexture((ResourceLocation)ResourcePackUtils.method5());
   }

   public void func_148313_c() {
      this.bindResourcePackIcon$v1_12();
   }

   public boolean mousePressed(int number1, int number2, int number3, int number4, int number5, int number6) {
      if (Ref.MC_VERSION >= 1) {
         this.resourcePacksGUI.availableResourcePacks = this.field2;
      } else {
         this.resourcePacksGUI.field_146966_g$v1_7 = this.field2;
      }

      ((GuiResourcePackListBridge)this.resourcePacksGUI.availableResourcePacksList).setUnderlyingList(this.field2);
      this.resourcePacksGUI.keyTyped('\u0000', 0);
      ((GuiScreenResourcePacksBridge)this.resourcePacksGUI).bridge$handlePackSwapList();
      return false;
   }
}
