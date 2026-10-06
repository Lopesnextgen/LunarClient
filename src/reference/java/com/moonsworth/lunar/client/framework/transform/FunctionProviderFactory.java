package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.ichor.util.KeepName;
import com.moonsworth.lunar.client.framework.transform.LwjglFunctionProvider;

@KeepName
public class FunctionProviderFactory {
   public FunctionProviderFactory() {
   }

   public static Object newFunctionProvider() {
      return new LwjglFunctionProvider();
   }
}
