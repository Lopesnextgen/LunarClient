package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.legacy.optifine.wrapper.IImageBufferLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.optifine.player.CapeUtils;
import org.apache.commons.io.FilenameUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CapeUtils.class)
public abstract class CapeUtilsMixin {
   public CapeUtilsMixin() {
   }

   @Overwrite
   public static void downloadCape(AbstractClientPlayer player0) {
      if (FeatureFlag.SHOW_OPTIFINE_CAPES.isEnabled()) {
         String text1 = player0.getNameClear();
         if (text1 != null && !text1.isEmpty() && !text1.contains("\u0000")) {
            String text2 = Bridge.method45() + "/capes/" + text1 + ".png";
            String text3 = FilenameUtils.getBaseName(text2);
            ResourceLocation location4 = new ResourceLocation("capeof/" + text3);
            TextureManager texturemanager5 = Minecraft.getMinecraft().getTextureManager();
            if (texturemanager5.getTexture(location4) instanceof ThreadDownloadImageData threaddownloadimagedata7 && threaddownloadimagedata7.imageFound != null) {
               if (threaddownloadimagedata7.imageFound) {
                  player0.setLocationOfCape(location4);
               }

               return;
            }

            IImageBufferLoader iimagebufferloader9 = new IImageBufferLoader(player0, location4);
            ThreadDownloadImageData threaddownloadimagedata8 = new ThreadDownloadImageData(null, text2, null, iimagebufferloader9);
            texturemanager5.loadTexture(location4, threaddownloadimagedata8);
         }
      }
   }
}
