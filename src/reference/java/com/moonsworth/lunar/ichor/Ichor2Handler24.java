package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.loader.Ichor4Type;

public class Ichor2Handler24 extends com.moonsworth.lunar.ichor.util.Ichor2Handler2 implements LunarMappingProviderHook, MixinHelper3_2 {
   public Ichor2Handler24() {
      super("com.replaymod.*");
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.EXTERNAL_REMAP};
   }
}
