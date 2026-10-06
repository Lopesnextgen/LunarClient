package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.ichor.api.IchorAPI2;
import java.util.Optional;
import net.minecraftforge.fml.common.eventhandler.ASMEventHandler.ASMClassLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ASMClassLoader.class)
public abstract class ASMClassLoaderMixin extends ClassLoader {
   @Overwrite
   public Class<?> define(String var1, byte[] var2) {
      if (this.getParent() instanceof IchorAPI2 var3) {
         Optional var5 = var3.method2(var1, var2);
         if (var5.isPresent()) {
            return (Class<?>)var5.get();
         }
      }

      return this.defineClass(var1, var2, 0, var2.length);
   }
}
