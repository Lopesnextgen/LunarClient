package com.moonsworth.lunar.client.alert;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface Alert<PARENT, CHILD> {
   List<CHILD> getChildren();

   void method1(PARENT var1, List<CHILD> var2);

   boolean method2(@NotNull CHILD var1);

   void method3(@NotNull CHILD var1);
}
