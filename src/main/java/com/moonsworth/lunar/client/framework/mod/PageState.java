package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.MutablePageState;

public interface PageState {
   boolean method1();

   PageState method2(boolean flag1);

   static PageState method3() {
      return new MutablePageState();
   }
}
