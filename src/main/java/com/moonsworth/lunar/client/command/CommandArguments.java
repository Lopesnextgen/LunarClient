package com.moonsworth.lunar.client.command;

import java.time.Duration;

public interface CommandArguments {
   String getString(String text1);

   int getInteger(String text1);

   double getDouble(String text1);

   Duration getDuration(String text1);
}
