package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.loader.Ichor4Type;

public class Ichor2Impl extends com.moonsworth.lunar.ichor.util.Ichor2Handler implements MixinHelper_2 {
   public Ichor2Impl() {
      super("net/minecraftforge/");
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.POST_OPTIFINE_PATCH};
   }
}
