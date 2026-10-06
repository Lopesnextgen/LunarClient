package com.moonsworth.lunar.client.driver.component;

import com.moonsworth.lunar.client.driver.DriverComponent;
import java.nio.file.Path;
import java.util.List;

@FunctionalInterface
public interface ComponentDropCallback<T extends DriverComponent<?>> {
   boolean accept(T value1, List<Path> list2);
}
