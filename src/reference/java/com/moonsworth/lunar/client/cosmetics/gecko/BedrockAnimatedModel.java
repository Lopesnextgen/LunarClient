package com.moonsworth.lunar.client.cosmetics.gecko;

import com.eliotlash.molang.ast.Evaluator;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockGeometry;
import java.util.Optional;

public abstract class BedrockAnimatedModel<T> {
   protected double field1;
   protected boolean field2;

   public BedrockAnimatedModel() {
   }

   public Optional<BedrockGeometry> method1(ResourceLocationBridge horsestats141) {
      return Client.method109().method76().method11(horsestats141);
   }

   public abstract ResourceLocationBridge method2(T value1, Evaluator evaluator2);

   public abstract ResourceLocationBridge method3(T value1, Evaluator evaluator2);
}
