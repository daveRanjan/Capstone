package com.scaler.userservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "`client`")
public class Client {
    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    @Column(length = 1000)
    @Lob
    private String clientAuthenticationMethods;

    @Column(length = 1000)
    @Lob
    private String authorizationGrantTypes;

    @Column(length = 1000)
    @Lob
    private String redirectUris;

    @Column(length = 1000)
    @Lob
    private String postLogoutRedirectUris;

    @Column(length = 1000)
    @Lob
    private String scopes;

    @Column(length = 2000)
    @Lob
    private String clientSettings;

    @Column(length = 2000)
    @Lob
    private String tokenSettings;
}
