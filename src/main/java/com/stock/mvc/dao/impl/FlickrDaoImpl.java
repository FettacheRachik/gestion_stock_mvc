package com.stock.mvc.dao.impl;

import java.io.InputStream;

import javax.swing.JOptionPane;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;

import com.stock.mvc.dao.IFlickrDao;

public class FlickrDaoImpl implements IFlickrDao {
	
	private Flickr flickr;

    private UploadMetaData uploadMetaData = new UploadMetaData();

    private String apiKey = "5b66d165ec63d414b7ae5afb5f16635d";

    private String sharedSecret = "3a8c05f88edbf201";


    private void connect(){
        flickr = new Flickr(apiKey, sharedSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.READ);
        auth.setToken("72157711148770758-a3fd44b5feb32405");
        auth.setTokenSecret("bb61203cb1056e6e" );
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        flickr.setAuth(auth);
    }

    @Override
    public String savePhoto(InputStream photo, String title) throws Exception{
        connect();
        uploadMetaData.setTitle(title);
        String photoId = flickr.getUploader().upload(photo, uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }

    public void auth(){
        flickr = new Flickr(apiKey, sharedSecret, new REST());

        AuthInterface authInterface = flickr.getAuthInterface();

        Token token = authInterface.getRequestToken();
        System.out.println("token: " + token);

        String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
        System.out.println("Follow this URL to authorise yourself on Flickr");
        System.out.println(url);
        System.out.println("Paste in the token it gives you:");
        System.out.print(">>");

        String tokenKey = JOptionPane.showInputDialog(null);

        Token requestToken = authInterface.getAccessToken(token, new Verifier(tokenKey));
        System.out.println("Authentication success");

        Auth auth = null;
        try {
            auth = authInterface.checkToken(requestToken);
        } catch (FlickrException e) {
            e.printStackTrace();
        }

        // This token can be used until the user revokes it.
        System.out.println("Token: " + requestToken.getToken());
        System.out.println("Secret: " + requestToken.getSecret());
        System.out.println("nsid: " + auth.getUser().getId());
        System.out.println("Realname: " + auth.getUser().getRealName());
        System.out.println("Username: " + auth.getUser().getUsername());
        System.out.println("Permission: " + auth.getPermission().getType());

    }

}
