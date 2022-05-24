package main

import (
	"log"
	"strconv"
	"strings"

	"github.com/confluentinc/confluent-kafka-go/kafka"
)

func main() {
	producer := NewKafkaProducer()
	deliveryChannel := make(chan kafka.Event)
	for i := 0; i < 10; i++ {
		msg := []string{"teste", strconv.Itoa(i)}
		PublishMessage(strings.Join(msg, "-"), "teste", producer, nil, deliveryChannel)
	}

	go DeliveryReport(deliveryChannel)

	producer.Flush(1000)
}

func NewKafkaProducer() *kafka.Producer {
	ConfigMap := &kafka.ConfigMap{
		"bootstrap.servers":   "kafka_kafka_1:9092",
		"delivery.timeout.ms": "30000",
		"acks":                "all",
		"enable.idempotence":  "true",
	}

	producer, err := kafka.NewProducer(ConfigMap)
	if err != nil {
		log.Println(err.Error())
	}

	return producer
}

func PublishMessage(msg string, topic string, producer *kafka.Producer, key []byte, deliveryChannel chan kafka.Event) error {
	message := &kafka.Message{
		Value: []byte(msg),
		TopicPartition: kafka.TopicPartition{
			Topic:     &topic,
			Partition: kafka.PartitionAny,
		},
		Key: key,
	}

	err := producer.Produce(message, deliveryChannel)
	if err != nil {
		return err
	}
	return nil
}

func DeliveryReport(deliveryChannel chan kafka.Event) {
	for eventDelivery := range deliveryChannel {
		message := eventDelivery.(*kafka.Message)

		if message.TopicPartition.Error != nil {
			log.Printf("Delivery failed: %v\n", message.TopicPartition.Error)
		} else {
			log.Printf("Delivered message to topic %s [%d] at offset %v\n",
				*message.TopicPartition.Topic, message.TopicPartition.Partition, message.TopicPartition.Offset)
		}
	}
}
