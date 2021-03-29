package com.demo.gcp.gcppubsubdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubMesageController {

	@Autowired
	private GcpPubSubDemoApplication.PubsubOutboundGateway messagingGateway;

	@PostMapping("/publishMessage")
	public ResponseEntity<?> publishMessage(@RequestParam("message") String message) {
		messagingGateway.sendToPubsub(message);
		return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
	}
}
