package com.moonsworth.lunar.forge.mixin;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.IEventExceptionHandler;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(net.minecraftforge.fml.common.eventhandler.EventBus.class)
public abstract class EventBusMixin3 {
   @Shadow
   public IEventExceptionHandler exceptionHandler;
   @Shadow
   public int busID;

   @Overwrite
   public boolean post(Event var1) {
      IEventListener[] var2 = var1.getListenerList().getListeners(this.busID);
      int var3 = 0;

      try {
         for (; var3 < var2.length; var3++) {
            try {
               var2[var3].invoke(var1);
            } catch (Throwable var5) {
               if (!(var5 instanceof ClassCastException)) {
                  var5.printStackTrace();
               }
            }
         }
      } catch (Throwable var6) {
         this.exceptionHandler.handleException((net.minecraftforge.fml.common.eventhandler.EventBus)this, var1, var2, var3, var6);
      }

      return var1.isCancelable() && var1.isCanceled();
   }

   @Redirect(
      method = "register(Ljava/lang/Object;)V",
      at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V")
   )
   public void ichor$silenceRegisterLog(Logger var1, String var2, Object var3, Object var4) {
   }
}
