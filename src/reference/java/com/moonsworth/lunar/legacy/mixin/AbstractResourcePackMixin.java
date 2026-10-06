package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractResourcePack.class)
public abstract class AbstractResourcePackMixin implements ResourcePackBridge {
   public AbstractResourcePackMixin() {
   }

   @Shadow
   public abstract InputStream getInputStreamByName(String text1);

   @Shadow
   public abstract String getPackName();

   @Shadow
   public abstract InputStream getInputStream(ResourceLocation location1);

   @Shadow
   public abstract <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer imetadataserializer1, String text2);

   @Shadow
   public abstract boolean hasResourceName(String text1);

   public InputStream bridge$getInputStream(ResourceLocationBridge horsestats141) {
      return this.getInputStream((ResourceLocation)horsestats141);
   }

   @Overwrite
   public BufferedImage getPackImage() {
      int number1 = Client.method21();
      InputStream input2 = this.getInputStreamByName("pack.png");
      BufferedImage bufferedimage3 = Ref.MC_VERSION == 1 ? TextureUtil.readBufferedImage(input2) : ImageIO.read(input2);
      if (bufferedimage3 == null) {
         return null;
      }

      if (bufferedimage3.getWidth() <= number1 && bufferedimage3.getHeight() <= number1) {
         return bufferedimage3;
      }

      LunarLogger.method4("MemoryFix", "Scaling resource pack icon from " + bufferedimage3.getWidth() + " to " + number1, new Object[0]);
      BufferedImage bufferedimage4 = new BufferedImage(number1, number1, 2);
      Graphics graphics5 = bufferedimage4.getGraphics();
      graphics5.drawImage(bufferedimage3, 0, 0, number1, number1, null);
      graphics5.dispose();
      return bufferedimage4;
   }

   public String bridge$getPackName() {
      return this.getPackName();
   }

   public Bridge2_42 bridge$getDescription() {
      PackMetadataSection packmetadatasection1 = this.getPackMetadata(Minecraft.getMinecraft().metadataSerializer_, "pack");
      return Ref.MC_VERSION >= 1 ? (Bridge2_42)packmetadatasection1.packDescription : (Bridge2_42)packmetadatasection1.packDescription$v1_7;
   }

   public Optional<BufferedImage> bridge$getPackImage() {
      try {
         try {
            return Optional.ofNullable(this.getPackImage());
         } catch (IOException exception2) {
            return Optional.empty();
         }
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   public boolean bridge$hasPath(String text1) {
      if (this instanceof FileResourcePack fileresourcepack2) {
         ZipFile zipfile7;
         try {
            zipfile7 = fileresourcepack2.getResourcePackZipFile();
         } catch (IOException exception6) {
            return false;
         }

         Enumeration enumeration5 = zipfile7.entries();

         while (enumeration5.hasMoreElements()) {
            if (((ZipEntry)enumeration5.nextElement()).getName().startsWith(text1)) {
               return true;
            }
         }
      } else if (this instanceof FolderResourcePack folderresourcepack3) {
         return new File(folderresourcepack3.resourcePackFile, text1).exists();
      }

      return false;
   }
}
