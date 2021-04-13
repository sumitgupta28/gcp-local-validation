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




```sh

	sumitgupta28@cloudshell:~/gcp-local-validation/gcp-app-engine-sample (river-sunlight-310506)$ gcloud app deploy
	Services to deploy:
	
	descriptor:      [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample/app.yaml]
	source:          [/home/sumitgupta28/gcp-local-validation/gcp-app-engine-sample]
	target project:  [river-sunlight-310506]
	target service:  [default]
	target version:  [20210413t030941]
	target url:      [https://river-sunlight-310506.uc.r.appspot.com]
	
	
	Do you want to continue (Y/n)?  y
	
	Beginning deployment of service [default]...
	╔════════════════════════════════════════════════════════════╗
	╠═ Uploading 1 file to Google Cloud Storage                 ═╣
	╚════════════════════════════════════════════════════════════╝
	File upload done.
	Updating service [default]...done.
	Setting traffic split for service [default]...done.
	Deployed service [default] to [https://river-sunlight-310506.uc.r.appspot.com]
	
	You can stream logs from the command line by running:
	  $ gcloud app logs tail -s default
	
	To view your application in the web browser run:
	  $ gcloud app browse
	sumitgupta28@cloudshell:~/gcp-local-validation/gcp-app-engine-sample (river-sunlight-310506)$ gcloud app browse
	Did not detect your browser. Go to this link to view your app:
	https://river-sunlight-310506.uc.r.appspot.com

	sumitgupta28@cloudshell:~/gcp-local-validation/gcp-app-engine-sample (river-sunlight-310506)$ curl https://river-sunlight-310506.uc.r.appspot.com/api/hello
	Hello Google app Engine

```