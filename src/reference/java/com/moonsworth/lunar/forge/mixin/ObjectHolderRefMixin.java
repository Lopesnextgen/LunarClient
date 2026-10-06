package com.moonsworth.lunar.forge.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import java.lang.reflect.Field;
import net.minecraftforge.fml.common.registry.ObjectHolderRef;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sun.misc.Unsafe;

@Mixin(ObjectHolderRef.class)
public class ObjectHolderRefMixin {
   @Shadow
   public Field field;
   @Unique
   private static final Unsafe lunar$UNSAFE;

   @Inject(method = "makeWritable", at = @At("HEAD"), cancellable = true)
   private static void lunar$makeWritable(CallbackInfo var0) {
      var0.cancel();
   }

   @Inject(
      method = "apply",
      at = @At(value = "INVOKE", target = "Ljava/lang/reflect/Method;invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;"),
      cancellable = true
   )
   private void lunar$setValue(CallbackInfo var1, @Local(index = 1) Object var2) {
      Object var3 = lunar$UNSAFE.staticFieldBase(this.field);
      long var4 = lunar$UNSAFE.staticFieldOffset(this.field);
      lunar$UNSAFE.putObject(var3, var4, var2);
      var1.cancel();
   }

   static {
      try {
         Field var0 = Unsafe.class.getDeclaredField("theUnsafe");
         var0.setAccessible(true);
         lunar$UNSAFE = (Unsafe)var0.get(null);
      } catch (Throwable var1) {
         throw new RuntimeException(var1);
      }
   }
}
