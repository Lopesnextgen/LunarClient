package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.Annotation;
import com.moonsworth.lunar.bridge.BridgeVersionMapping;
import com.moonsworth.lunar.bridge.BridgeTargetMapping;
import java.security.Key;
import java.security.PublicKey;
import javax.crypto.SecretKey;

@Annotation(
   mappings = {
         @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("net/minecraft/util/CryptManager")),
         @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("net/minecraft/util/Crypt"))
   }
)
public interface CryptManagerBridge {
   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("encryptData")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("encryptUsingKey"))
      }
   )
   static byte[] method1(Key var0, byte[] var1) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("decodePublicKey")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("byteToPublicKey"))
      }
   )
   static PublicKey method2(byte[] var0) {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("createNewSharedKey")),
            @BridgeVersionMapping(version = 6, targets = @BridgeTargetMapping("generateSecretKey"))
      }
   )
   static SecretKey method3() {
      throw new AbstractMethodErrorImpl();
   }

   @Annotation(
      mappings = {
            @BridgeVersionMapping(version = 0, targets = @BridgeTargetMapping("getServerIdHash")),
            @BridgeVersionMapping(
               version = 6,
               targets = @BridgeTargetMapping("digestData(Ljava/lang/String;Ljava/security/PublicKey;Ljavax/crypto/SecretKey;)[B")
            )
      }
   )
   static byte[] method4(String var0, PublicKey var1, SecretKey var2) {
      throw new AbstractMethodErrorImpl();
   }
}
