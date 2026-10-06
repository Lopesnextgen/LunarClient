package com.moonsworth.lunar.bridge;

public interface AnimationMetadataSectionBridge extends IMetadataSectionBridge {
   int bridge$getFrameHeight();

   int bridge$getFrameWidth();

   int bridge$getFrameCount();

   int bridge$getFrameTime();

   int bridge$getFrameTimeSingle(int number1);

   boolean bridge$hasTime(int number1);

   int bridge$getFrameIndex(int number1);
}
