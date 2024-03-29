/*
 * Library
 * Library
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.InlineObject;
import org.openapitools.client.model.InlineResponse200;
import org.openapitools.client.model.InlineResponse400;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for LoginApi
 */
@Ignore
public class LoginApiTest {

    private final LoginApi api = new LoginApi();

    
    /**
     * Login
     *
     * Logs in a user with a login and password.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void webappJSPEntranceJspPostTest() throws ApiException {
        InlineObject inlineObject = null;
        InlineResponse200 response = api.webappJSPEntranceJspPost(inlineObject);

        // TODO: test validations
    }
    
}
