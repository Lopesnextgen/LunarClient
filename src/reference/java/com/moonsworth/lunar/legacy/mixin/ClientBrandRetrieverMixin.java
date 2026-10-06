package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.ichor.MixinCondition;
import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ClientBrandRetriever.class)
public abstract class ClientBrandRetrieverMixin {
   public ClientBrandRetrieverMixin() {
   }

   @Overwrite
   @MixinCondition(absent = "forge")
   public static String getClientModName() {
      return Client.getClientBrand();
   }
}
