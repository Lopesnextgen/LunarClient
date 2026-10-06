package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.cosmetics.gecko.IBoneSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.GeoModelSerializer;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockGeometry;
import com.moonsworth.lunar.client.cosmetics.gecko.ModelQuad;
import com.moonsworth.lunar.client.cosmetics.gecko.BedrockGeometryFile;
import com.moonsworth.lunar.client.cosmetics.gecko.RewindhandlersException;
import com.moonsworth.lunar.client.cosmetics.gecko.RewindhandlersIterator;
import com.moonsworth.lunar.client.cosmetics.gecko.FormatVersion;
import com.moonsworth.lunar.client.cosmetics.gecko.CubeMesh;
import it.unimi.dsi.fastutil.ints.IntObjectPair;
import lombok.Generated;

public final class ModelGeometryLoader {
   public static IntObjectPair<BedrockGeometry> method1(Bridge11_2 bridge11_20, ResourceLocationBridge horsestats141) {
      try {
         String text2 = AnimationFileLoader.method3(horsestats141, bridge11_20);
         BedrockGeometryFile rewindhandlers3_23 = GeoModelSerializer.method3(text2);
         if (rewindhandlers3_23.method1() != FormatVersion.VERSION_1_12_0 && rewindhandlers3_23.method1() != FormatVersion.VERSION_1_21_20) {
            throw new RewindhandlersException(horsestats141, "Wrong geometry json version, expected 1.12.0");
         }

         com.moonsworth.lunar.client.cosmetics.gecko.BoneHierarchyBuilder rewindhandlers24 = com.moonsworth.lunar.client.cosmetics.gecko.BoneHierarchyBuilder.method1(
            rewindhandlers3_23
         );
         BedrockGeometry rewindhandlers2_25 = RewindhandlersIterator.method2(horsestats141.bridge$getDomain()).method1(rewindhandlers24);
         int number6 = 0;

         for (IBoneSerializer iboneserializer8 : rewindhandlers2_25.field1) {
            number6 += method2(iboneserializer8);
         }

         return IntObjectPair.of(number6, rewindhandlers2_25);
      } catch (Exception exception9) {
         LunarLogger.error(String.format("Error parsing %s", horsestats141), exception9);
         throw new RuntimeException(exception9);
      }
   }

   private static int method2(IBoneSerializer iboneserializer0) {
      int number1 = 0;

      for (IBoneSerializer iboneserializer3 : iboneserializer0.field1) {
         number1 += method2(iboneserializer3);
      }

      for (CubeMesh rewindhandlers_29 : iboneserializer0.field2) {
         if (rewindhandlers_29 != null) {
            for (ModelQuad rewindhandlers37 : rewindhandlers_29.field1) {
               if (rewindhandlers37 != null) {
                  number1 += 12 + rewindhandlers37.field3.length * 4 * 5;
               }
            }

            number1 += 36;
         }
      }

      return number1;
   }

   @Generated
   private ModelGeometryLoader() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
