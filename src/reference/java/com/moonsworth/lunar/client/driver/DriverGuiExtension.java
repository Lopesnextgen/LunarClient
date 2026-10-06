package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.driver.bridge.DriverDataProvider;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import java.nio.file.Path;
import java.util.List;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public interface DriverGuiExtension extends DriverDataProvider, JsonProvider {
   default void method1(String text) {
   }

   default void method2(KeyCode bridgetype_81, int number2, int number3, int number4, int value) {
   }

   default void method3(KeyCode bridgetype_81, int number2, int number3, int number4, MarkerModel.Data5 data) {
   }

   default void method4(double value, double value2) {
   }

   default void method5(List<Path> list) {
   }

   default boolean method6() {
      return false;
   }
}
