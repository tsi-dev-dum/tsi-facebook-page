package com.tsi.page.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GmailAPI {

	  private static final String APPLICATION_NAME = "Gmail API For Teligent Facebook System";
	  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	  private static final String TOKENS_DIRECTORY_PATH = "tokens";
	  private static final List<String> SCOPES = Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM);
	  private static final String CREDENTIALS_FILE_PATH = "/secret.json";
	  
	  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
		      throws IOException {
		    InputStream in = GmailAPI.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		    if (in == null) {
		      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		    }
		    GoogleClientSecrets clientSecrets =
		        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
		        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
		        .setAccessType("offline")
		        .build();
		    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
		    return credential;
		  }
	  
	  public Gmail getAuthorizedService() throws GeneralSecurityException, IOException {
		  final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		    Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
		        .setApplicationName(APPLICATION_NAME)
		        .build();
		    return service;
	  }
	  
	  public void printLabelResult(Gmail service) throws IOException {
		  String user = "me";
		    ListLabelsResponse listResponse = service.users().labels().list(user).execute();
		    List<Label> labels = listResponse.getLabels();
		    if (labels.isEmpty()) {
		      System.out.println("No labels found.");
		    } else {
		      System.out.println("Labels:");
		      for (Label label : labels) {
		        System.out.printf("- %s\n", label.getName());
		      }
		    }
	  }
	  
	  public String checkMessage(Gmail service, String q) throws IOException{
		  ListThreadsResponse messagesResp = service.users().threads().list("me").setQ(q).execute();
		  List<com.google.api.services.gmail.model.Thread> message = messagesResp.getThreads();
		  if(message.isEmpty()) {
			  return new String();
		  }
		  else {
			  return message.get(0).getId();
		  }
	  }
}
