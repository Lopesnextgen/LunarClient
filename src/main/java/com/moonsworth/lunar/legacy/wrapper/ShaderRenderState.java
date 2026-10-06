package com.moonsworth.lunar.legacy.wrapper;

public interface ShaderRenderState {
   void lunar$setupState();

   void lunar$clearState();

   static int method1(int value) {
      return value == 0 ? 0 : value + 1;
   }
}
