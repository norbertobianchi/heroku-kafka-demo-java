package com.heroku.kafka.demo;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration>  {
  public static void main(String[] args) throws Exception {
    new DemoApplication().run(args);
  }

  @Override
  public String getName() {
    return "heroku-kafka-demo";
  }

  @Override
  public void run(DemoConfiguration config, Environment env) throws Exception {
    DemoProducer producer = new DemoProducer(config.getKafkaConfig());
    DemoConsumer consumer = new DemoConsumer(config.getKafkaConfig());

    env.lifecycle().manage(producer);
    env.lifecycle().manage(consumer);

    env.jersey().register(new DemoResource(producer, consumer));
  }
}