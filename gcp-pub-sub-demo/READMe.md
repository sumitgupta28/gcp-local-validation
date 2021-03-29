# Running GCP PUB SUB Service from Local 

Use Google Cloud Platform’s (GCP) Pub/Sub service in combination with a Spring Boot application using Spring Integration. 

We will send a message to a sender application which publishes the message to a Topic where a receiver application receives the messages of a Subscription.


## Prerequisite

### GCP Account Setup. 

1. Create GCP account with a project name , download the credentials JSON file for our service account.
2. either pass PROJECT_ID as VM argument or replace ${PROJECT_ID} with your project id
3. keep the JSON at /src/main/resources/
(this is just to demo the pub/sub)

```yaml

	## GCP configuration
	spring:
	  cloud:
	    gcp:
	      project-id: ${PROJECT_ID}
	      credentials:
	        location: file:src/main/resources/service-account.json
```

### Topic Creation. 

#### [via GCP command line](https://cloud.google.com/sdk/gcloud/reference/pubsub/topics/create) 

```sh

	$ gcloud pubsub topics create transaction-topic
	Created topic [projects/test-project-1/topics/transaction-topic].
```

**topics list**
```sh	
	$ gcloud pubsub topics list
	---
	name: projects/test-project-1/topics/transaction-topic

```


### Subscription Creation. 

#### [via GCP Command line](https://cloud.google.com/sdk/gcloud/reference/pubsub/subscriptions/create)

```sh

	$ gcloud pubsub subscriptions create transaction-subscription --topic=transaction-topic
	Created subscription [projects/test-project-1/subscriptions/transaction-subscription].
	
	$ gcloud pubsub subscriptions create transaction-notification-subscription --topic=transaction-topic
	Created subscription [projects/test-project-1/subscriptions/transaction-notification-subscription].

```

**Subscriptions list**

```sh

	$ gcloud pubsub subscriptions list
	---
	ackDeadlineSeconds: 10
	expirationPolicy:
	  ttl: 2678400s
	messageRetentionDuration: 604800s
	name: projects/test-project-1/subscriptions/transaction-subscription
	pushConfig: {}
	topic: projects/test-project-1/topics/transaction-topic
	---
	ackDeadlineSeconds: 10
	expirationPolicy:
	  ttl: 2678400s
	messageRetentionDuration: 604800s
	name: projects/test-project-1/subscriptions/transaction-notification-subscription
	pushConfig: {}
	topic: projects/test-project-1/topics/transaction-topic

```

## How to run locally

```
	mvn spring-boot:run 
```

### send message to topic

#### via GCP Console 

```sh

	$ gcloud pubsub topics publish transaction-topic --message="Hello World!"
	messageIds:
	- '2131805202858617'
```

validate application logs

```

	[sub-subscriber2] c.d.g.g.GcpPubSubDemoApplication         : Message arrived! SubscriptionName: transaction-notification-subscription Payload: Hello World!
	[sub-subscriber1] c.d.g.g.GcpPubSubDemoApplication         : Message arrived! SubscriptionName: transaction-subscription Payload: Hello World!


```


#### via publishMessge api exposed using PubSubMesageController

```sh

	$ curl --data "message= Congratulations! GCP Messaging is Working" localhost:8181/publishMessage

``` 


validate application logs

```

	[sub-subscriber3] c.d.g.g.GcpPubSubDemoApplication         : Message arrived! SubscriptionName: transaction-notification-subscription Payload:  Congratulations! GCP Messaging is Working
	[sub-subscriber1] c.d.g.g.GcpPubSubDemoApplication         : Message arrived! SubscriptionName: transaction-subscription Payload:  Congratulations! GCP Messaging is Working

```
