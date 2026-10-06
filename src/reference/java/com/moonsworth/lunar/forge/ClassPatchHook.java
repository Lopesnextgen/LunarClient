package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.ichor.TransformBytecode;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.IchorStage;
import com.moonsworth.lunar.ichor.util.IchorLogger.Type;
import com.moonsworth.lunar.loader.PipelineStage;

public class ClassPatchHook implements IchorInjection {
   private final MixinMisc field1;

   public ClassPatchHook(MixinMisc mixinmisc1) {
      this.field1 = mixinmisc1;
   }

   public IchorStage[] method2() {
      return new IchorStage[]{PipelineStage.FORGE_PATCH};
   }

   @TransformBytecode
   public byte[] method2(String text1, byte[] items2) {
      try {
         ClassPatch mixinmisc23 = (ClassPatch)this.field1.method6().get(text1);
         if (mixinmisc23 != null) {
            try {
               return mixinmisc23.method2(items2);
            } catch (Exception exception5) {
               Ichor6Impl.field2.method5(Type.FATAL, "Failed to patch " + text1 + " -> " + mixinmisc23.field1, new Object[0]);
               exception5.printStackTrace();
            }
         }

         return items2;
      } catch (Throwable exception6) {
         throw exception6;
      }
   }

   public MixinMisc method3() {
      return this.field1;
   }
}
