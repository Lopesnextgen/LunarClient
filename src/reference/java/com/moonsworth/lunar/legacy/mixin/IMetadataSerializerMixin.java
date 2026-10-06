package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.legacy.wrapper.util.Bridge3Extension2;
import com.moonsworth.lunar.legacy.wrapper.util.JsonDeserializerImpl;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSectionSerializer;
import net.minecraft.client.resources.data.IMetadataSectionSerializer_v1_7;
import net.minecraft.client.resources.data.IMetadataSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IMetadataSerializer.class)
public abstract class IMetadataSerializerMixin {
   @Shadow
   public abstract <T extends IMetadataSection> void registerMetadataSectionType(IMetadataSectionSerializer<T> var1, Class<T> var2);

   @Shadow
   public abstract void registerMetadataSectionType(IMetadataSectionSerializer_v1_7 var1, Class var2);

   @Inject(method = "<init>", at = @At("RETURN"))
   public void lunar$init(CallbackInfo var1) {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.registerMetadataSectionType(com.moonsworth.lunar.legacy.wrapper.util.IMetadataSectionSerializer.field1, Bridge3Extension2.class);
      } else {
         this.registerMetadataSectionType(JsonDeserializerImpl.field1, Bridge3Extension2.class);
      }
   }
}
