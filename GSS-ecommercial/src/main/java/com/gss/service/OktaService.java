package com.gss.service;

import org.springframework.stereotype.Service;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Clients;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;
import org.springframework.beans.factory.annotation.Value;


@Service
public class OktaService {
	private final Client client;

	public OktaService(@Value("${okta.client.token}") String token, 
						@Value("${okta.client.orgUrl}") String orgUrl) {
		this.client = Clients.builder()
				.setOrgUrl(orgUrl)
				.setClientCredentials(new TokenClientCredentials(token))
				.build();
	}

	public User createUser(String firstName, String lastName, String email, String password) {
		return UserBuilder.instance()
				.setEmail(email)
				.setLogin(email)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setPassword(password.toCharArray())
				.buildAndCreate(client);
	}
}
