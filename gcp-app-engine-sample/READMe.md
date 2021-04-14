# Sample Service to run in GCP App Engine 

### Configurations specific to App Engine 

#### pom.xml

add **appengine-maven-plugin**

```xml

			<plugin>
				<groupId>com.google.cloud.tools</groupId>
				<artifactId>appengine-maven-plugin</artifactId>
				<version>2.2.0</version>
				<configuration>
					<version>1</version>
				</configuration>
			</plugin>

```


### Deploy Default Service - Default & default version


```sh

	$ gcloud app  deploy 
	Services to deploy:
	
	descriptor:      [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample/app.yaml]
	source:          [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample]
	target project:  [terraform-28061984]
	target service:  [default]
	target version:  [20210413t061705]
	target url:      [https://terraform-28061984.uc.r.appspot.com]
	
	
	Do you want to continue (Y/n)?  y
	
	Beginning deployment of service [default]...
	╔════════════════════════════════════════════════════════════╗
	╠═ Uploading 0 files to Google Cloud Storage                ═╣
	╚════════════════════════════════════════════════════════════╝
	File upload done.
	Updating service [default]...done.
	Setting traffic split for service [default]...done.
	Deployed service [default] to [https://terraform-28061984.uc.r.appspot.com]
	
	You can stream logs from the command line by running:
	  $ gcloud app logs tail -s default
	
	To view your application in the web browser run:
	  $ gcloud app browse
	
```

### get the url and validate it


```
	$ gcloud app browse
	Did not detect your browser. Go to this link to view your app:
	https://terraform-28061984.uc.r.appspot.com
	
	
	$ curl https://terraform-28061984.uc.r.appspot.com/api/health
	App is Healthy : app.version:v1
```

### Deploy Default Service - Default & default v2

```sh

	$ gcloud app  deploy --version v2
	Services to deploy:
	descriptor:      [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample/app.yaml]
	source:          [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample]
	target project:  [terraform-28061984]
	target service:  [default]
	target version:  [v2]
	target url:      [https://terraform-28061984.uc.r.appspot.com]
	Do you want to continue (Y/n)?  y
	Beginning deployment of service [default]...
	╔════════════════════════════════════════════════════════════╗
	╠═ Uploading 1 file to Google Cloud Storage                 ═╣
	╚════════════════════════════════════════════════════════════╝
	File upload done.
	                                                                                                                                                                                                         
	                                                                                                                                   Updating service [default]...done.
	Setting traffic split for service [default]...done.
	Deployed service [default] to [https://terraform-28061984.uc.r.appspot.com]
	You can stream logs from the command line by running:
	  $ gcloud app logs tail -s default
	To view your application in the web browser run:
	  $ gcloud app browse

```

### List the available services , here we can see it has 2 versions.

```sh

	$ gcloud app services list
	SERVICE  NUM_VERSIONS
	default  2
```

### List the available services , here we can see it has 2 versions.

```sh

	$ gcloud app  browse --version v1
	Did not detect your browser. Go to this link to view your app:
	https://v1-dot-terraform-28061984.uc.r.appspot.com
```

### Validate the v1. 
```sh

	$ curl https://v1-dot-terraform-28061984.uc.r.appspot.com/api/health
	App is Healthy : app.version:v1

```

### Deploy with Specific service name , update app.yaml with service. 

```yaml

	runtime: java11
	service: gcp-app-engine-sample-service
```

### Deploy

```sh

	$ gcloud app  deploy --version v1
	Services to deploy:
	
	descriptor:      [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample/app.yaml]
	source:          [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample]
	target project:  [terraform-28061984]
	target service:  [gcp-app-engine-sample-service]
	target version:  [v1]
	target url:      [https://gcp-app-engine-sample-service-dot-terraform-28061984.uc.r.appspot.com]
	
	
	Do you want to continue (Y/n)?  y
	
	Beginning deployment of service [gcp-app-engine-sample-service]...
	Created .gcloudignore file. See `gcloud topic gcloudignore` for details.
	╔════════════════════════════════════════════════════════════╗
	╠═ Uploading 5 files to Google Cloud Storage                ═╣
	╚════════════════════════════════════════════════════════════╝
	File upload done.
	Updating service [gcp-app-engine-sample-service]...done.
	Setting traffic split for service [gcp-app-engine-sample-service]...done.
	Deployed service [gcp-app-engine-sample-service] to [https://gcp-app-engine-sample-service-dot-terraform-28061984.uc.r.appspot.com]
	
	You can stream logs from the command line by running:
	  $ gcloud app logs tail -s gcp-app-engine-sample-service
	
	To view your application in the web browser run:
	  $ gcloud app browse -s gcp-app-engine-sample-service

```

### Validate

```

	$ curl https://gcp-app-engine-sample-service-dot-terraform-28061984.uc.r.appspot.com/api/health
	App is Healthy : app.version:v1

```

### Deploy the version 2. 

```sh

	$ gcloud app  deploy --version v2
	Services to deploy:
	
	descriptor:      [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample/app.yaml]
	source:          [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample]
	target project:  [terraform-28061984]
	target service:  [gcp-app-engine-sample-service]
	target version:  [v2]
	target url:      [https://gcp-app-engine-sample-service-dot-terraform-28061984.uc.r.appspot.com]
	
	
	Do you want to continue (Y/n)?  y
	
	Beginning deployment of service [gcp-app-engine-sample-service]...
	Created .gcloudignore file. See `gcloud topic gcloudignore` for details.
	╔════════════════════════════════════════════════════════════╗
	╠═ Uploading 4 files to Google Cloud Storage                ═╣
	╚════════════════════════════════════════════════════════════╝
	File upload done.
	Updating service [gcp-app-engine-sample-service]...done.
	Setting traffic split for service [gcp-app-engine-sample-service]...done.
	Deployed service [gcp-app-engine-sample-service] to [https://gcp-app-engine-sample-service-dot-terraform-28061984.uc.r.appspot.com]
	
	You can stream logs from the command line by running:
	  $ gcloud app logs tail -s gcp-app-engine-sample-service
	
	To view your application in the web browser run:
	  $ gcloud app browse -s gcp-app-engine-sample-service
```


### Version 2 URL.

```sh

	$ gcloud app browse -s gcp-app-engine-sample-service --version v2
	Did not detect your browser. Go to this link to view your app:
	https://v2-dot-gcp-app-engine-sample-service-dot-terraform-28061984.uc.r.appspot.com

``` 

### Now Split the traffic between 2 versions.


```sh

	$ gcloud app services set-traffic gcp-app-engine-sample-service --splits=v1=.5,v2=.5
	Setting the following traffic allocation:
	 - terraform-28061984/gcp-app-engine-sample-service/v1: 0.5
	 - terraform-28061984/gcp-app-engine-sample-service/v2: 0.5
	NOTE: Splitting traffic by ip.
	Any other versions of the specified service will receive zero traffic.
	Do you want to continue (Y/n)?  y
	
	Setting traffic split for service [gcp-app-engine-sample-service]...done.

```
