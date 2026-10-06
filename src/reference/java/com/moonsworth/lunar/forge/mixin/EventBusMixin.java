package com.moonsworth.lunar.forge.mixin;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.IEventExceptionHandler;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(net.minecraftforge.fml.common.eventhandler.EventBus.class)
public abstract class EventBusMixin {
   @Shadow
   public IEventExceptionHandler exceptionHandler;
   @Shadow
   public int busID;

   public EventBusMixin() {
   }

   @Overwrite
   public boolean post(Event event1) {
      IEventListener[] items2 = event1.getListenerList().getListeners(this.busID);
      int index3 = 0;

      try {
         for (; index3 < items2.length; index3++) {
            try {
               items2[index3].invoke(event1);
            } catch (Throwable exception5) {
               if (!(exception5 instanceof ClassCastException)) {
                  exception5.printStackTrace();
               }
            }
         }
      } catch (Throwable exception6) {
         this.exceptionHandler.handleException((net.minecraftforge.fml.common.eventhandler.EventBus)this, event1, items2, index3, exception6);
      }

      return event1.isCancelable() && event1.isCanceled();
   }

   @Redirect(
      method = "register(Ljava/lang/Object;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/fml/common/FMLLog;log(Lorg/apache/logging/log4j/Level;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V"
      )
   )
   public void ichor$silenceRegisterLog(Level level1, Throwable exception2, String text3, Object[] items4) {
   }
}
