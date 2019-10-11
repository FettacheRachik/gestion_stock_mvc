package com.stock.mvc;



import java.util.Scanner;

import org.scribe.*;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.oauth.OAuthService;
import org.scribe.model.*;
public class Test {

	

	public static void main(String[] args) {


		/*FlickrDaoImpl flickr = new FlickrDaoImpl();
		flickr.auth();*/
		
		
		Token accessToken = null;
	    String API_SECRET ="3a8c05f88edbf201" ;
	    String API_KEY = "5b66d165ec63d414b7ae5afb5f16635d";
		OAuthService service = new ServiceBuilder()
	                            .provider(FlickrApi.class)
	                            .apiKey(API_KEY)
	                            .apiSecret(API_SECRET)
	                            .build();
	    try{
	            System.out.println("There is no stored Access token we need to make one");
	            Scanner in = new Scanner(System.in);
	            System.out.println("Inside Auth....");
	            Token requestToken = service.getRequestToken();
	            System.out.println(service.getAuthorizationUrl(requestToken));
	            try
	            {
	                requestToken = service.getRequestToken();

	                System.out.println("Request Token : "+requestToken.toString());
	                System.out.println(service.getAuthorizationUrl(requestToken));
	                System.out.println("And paste the verifier here");
	                System.out.println(">>");
	                String authUrl = service.getAuthorizationUrl(requestToken);
	                Verifier verifier = new Verifier(authUrl);
	                System.out.println("Verifier : "+verifier.getValue());                
	                accessToken = service.getAccessToken(requestToken, verifier);

	            }catch (Exception e) {
	            	
	            }
	            
	    }catch (Exception e){
	    	
	    }

}
	}
