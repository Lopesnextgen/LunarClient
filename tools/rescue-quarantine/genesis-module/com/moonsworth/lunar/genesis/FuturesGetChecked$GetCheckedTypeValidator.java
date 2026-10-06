package com.moonsworth.lunar.genesis;
import com.google.common.eventbus.Subscribe;

@Subscribe
interface FuturesGetChecked$GetCheckedTypeValidator {
   void validateClass(Class<? extends Exception> clazz1);
}
