package com.moonsworth.lunar.ichor;

public interface IchorStage {
   String name();

   boolean hasMixinRuntime();

   boolean shouldUseParentAsMixinRuntime();

   boolean shouldUseClassBytes();
}
