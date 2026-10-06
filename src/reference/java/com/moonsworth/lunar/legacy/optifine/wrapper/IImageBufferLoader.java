package com.moonsworth.lunar.legacy.optifine.wrapper;

import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.util.ResourceLocation;
import net.optifine.player.CapeUtils;

public class IImageBufferLoader implements IImageBuffer {
   public ImageBufferDownload field1;
   public final WeakReference<AbstractClientPlayer> field2;
   public final ResourceLocation field3;

   public IImageBufferLoader(AbstractClientPlayer player1, ResourceLocation location2) {
      this.field2 = new WeakReference<>(player1);
      this.field3 = location2;
      this.field1 = new ImageBufferDownload();
   }

   public BufferedImage parseUserSkin(BufferedImage bufferedimage1) {
      return CapeUtils.parseCape(bufferedimage1);
   }

   @VersionGate(max = 0)
   public void func_152634_a$v1_7() {
      this.method1();
   }

   @VersionGate(min = 1)
   public void skinAvailable() {
      this.method1();
   }

   public void method1() {
      AbstractClientPlayer player1 = this.field2.get();
      if (player1 != null) {
         player1.setLocationOfCape(this.field3);
      }
   }
}
