package com.moonsworth.lunar.client.framework.loading;

import java.util.Set;

public interface LoadingStage {
   String getCategory();

   Set<? extends LoadableResource> method1();

   default void method2() {
   }
}
