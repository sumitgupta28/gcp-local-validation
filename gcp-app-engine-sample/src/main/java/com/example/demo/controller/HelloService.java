package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping(path = "/api")
@Api(description = "Set of endpoints for OCP Demo APP.")
public class HelloService {
	
	
	@Value("app.version:v1")
	String appVersion;

	@RequestMapping(method = RequestMethod.GET, path = "/health")
	@ApiOperation(value = "Return 200 Healthy Response", notes = "Return 200 Healthy Response")
	// @formatter:off
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Some Sample Message"),
			@ApiResponse(code = 404, message = "Some Sample Message"),
			@ApiResponse(code = 400, message = "Some Sample Message"),
			@ApiResponse(code = 500, message = "Some Sample Message") })
	// @formatter:on
	public ResponseEntity<String> health() {
		return ResponseEntity.ok().body("App is Healthy : "+ appVersion);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/hello")
	@ApiOperation(value = "Return Hello OCP", notes = "Return Hello Google app Engine")
	// @formatter:off
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Some Sample Message"),
			@ApiResponse(code = 404, message = "Some Sample Message"),
			@ApiResponse(code = 400, message = "Some Sample Message"),
			@ApiResponse(code = 500, message = "Some Sample Message") })
	// @formatter:on
	public String hello() {
		return "Hello Google app Engine";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hello/{name}")
	@ApiOperation(value = "Return Hello + Name", notes = "Return Hello Google app Engine + Name")
	// @formatter:off
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Some Sample Message"),
			@ApiResponse(code = 404, message = "Some Sample Message", 
			responseHeaders = {
					@ResponseHeader(name = "Privacy-Declaration"), 
					@ResponseHeader(name = "X-Request-ID"),
					@ResponseHeader(name = "X-Correlation-ID"), 
					@ResponseHeader(name = "API-Version"),
					@ResponseHeader(name = "Backend-Version") }),
			@ApiResponse(code = 400, message = "Some Sample Message"),
			@ApiResponse(code = 500, message = "Some Sample Message") })
	// @formatter:on

	public String helloName(@ApiParam("name.") @PathVariable(value = "name") String name) {
		return "Hello Google app Engine " + name;
	}
}
