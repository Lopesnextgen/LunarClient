package com.moonsworth.lunar.client.config.override;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface ChildContainer<PARENT, CHILD> {
   List<CHILD> getChildren();

   void method1(PARENT obj1, List<CHILD> list2);

   boolean method2(@NotNull CHILD child1);

   void method3(@NotNull CHILD child1);
}
