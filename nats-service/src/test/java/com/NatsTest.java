package com;

import io.nats.client.Connection;
import io.nats.client.Nats;

import java.io.IOException;

/**
 * @author swiChen
 * @date 2022/1/15
 **/
public class NatsTest {

 public static void main(String[] args) {
  try {
   Connection connection = Nats.connect("nats://119.23.60.23:4222");
   System.out.println(connection);
  } catch (IOException e) {
   e.printStackTrace();
  } catch (InterruptedException e) {
   e.printStackTrace();
  }
 }

}
