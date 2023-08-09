package com.dq.akka.demo;

import akka.actor.typed.ActorSystem;

public class AkkaQuickstart {

    public static void main(String[] args) {
        ActorSystem<GreeterMain.SayHello> greeterMain = ActorSystem.create(GreeterMain.create(), "helloakka");

        greeterMain.tell(new GreeterMain.SayHello("Charles"));

        try {
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            greeterMain.terminate();
        }
    }
}
