package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.Annotation2;
import java.io.File;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class FolderResourcePackImpl extends FolderResourcePack {
   public final String field1;
   public final String field2;
   public final String field3;

   public FolderResourcePackImpl(String var1, String var2, String var3, File var4) {
      super(var4);
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   @Annotation2(max = 0)
   public IMetadataSection getPackMetadata(IMetadataSerializer var1, String var2) {
      return var2.equals("pack") ? new PackMetadataSection((IChatComponent)(new ChatComponentText(this.field3)), 1) : null;
   }

   @Annotation2(min = 1)
   public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer var1, String var2) {
      return (T)(var2.equals("pack") ? new PackMetadataSection((IChatComponent)(new ChatComponentText(this.field3)), 1) : null);
   }
}
