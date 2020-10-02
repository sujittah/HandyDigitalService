package com.techexcellence.handydigital;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ProductMaster implements RequestHandler<Object, String> {

	@Override
	public String handleRequest(Object input, Context context) {
		StringBuilder result = new StringBuilder();
		result.append("Result from Lamdba..Sujit");
		context.getLogger().log("Input: " + input);
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		ScanRequest scanRequest = new ScanRequest().withTableName("ProductCatalog").withLimit(10)
				.withExclusiveStartKey(null);

		ScanResult scanRresult = client.scan(scanRequest);
		for (Map<String, AttributeValue> items : scanRresult.getItems()) {
			for (AttributeValue attrValue : items.values()) {
				result.append("Attr - ").append(attrValue.toString());
			}
		}
		return result.toString();
	}

}
