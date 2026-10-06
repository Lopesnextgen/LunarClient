package com.moonsworth.lunar.v1_12.mixin;

import com.moonsworth.lunar.client.framework.Client;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMixin {
   public ClientBrandRetrieverMixin() {
   }

   @Overwrite
   public static String getClientModName() {
      return Client.getClientBrand() + "," + FMLCommonHandler.instance().getModName();
   }
}
