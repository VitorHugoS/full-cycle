package main

import (
	"log"

	"github.com/confluentinc/confluent-kafka-go/kafka"
)

func main() {
	consumer := NewKafkaConsumer()
	topics := []string{"teste"}
	ConsumerTopics(topics, consumer)
}

func NewKafkaConsumer() *kafka.Consumer {
	ConfigMap := &kafka.ConfigMap{
		"bootstrap.servers": "kafka_kafka_1:9092",
		"client.id":         "go-app-consumer",
		"group.id":          "goapp-group",
	}

	consumer, err := kafka.NewConsumer(ConfigMap)
	if err != nil {
		log.Println(err.Error())
	}

	return consumer
}

func ConsumerTopics(topics []string, consumer *kafka.Consumer) error {
	consumer.SubscribeTopics(topics, nil)
	for {
		message, err := consumer.ReadMessage(-1)
		if err == nil {
			log.Printf("Message on topic: %s[%d]: {%s}", *message.TopicPartition.Topic, message.TopicPartition.Partition, string(message.Value))
		}
	}
}
