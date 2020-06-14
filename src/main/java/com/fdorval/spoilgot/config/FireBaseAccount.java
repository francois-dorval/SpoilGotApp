package com.fdorval.spoilgot.config;

import com.fdorval.spoilgot.util.exception.TechnicalException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Configuration
public class FireBaseAccount {

    String KEY_FILE = "eyAgInR5cGUiOiAic2VydmljZV9hY2NvdW50IiwgICJwcm9qZWN0X2lkIjogImJvY2RlbW9mYiIsICAicHJpdmF0ZV9rZXlfaWQiOiAiN2JjYjY3MmJjZjdkNWRlNDNkNWUxMmM1Mzg3NTI5ZGFmYmI0Y2MyYSIsICAicHJpdmF0ZV9rZXkiOiAiLS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tXG5NSUlFdkFJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLWXdnZ1NpQWdFQUFvSUJBUUMxOXhCeW5Tbnd0T01HXG5LYkROOUdCQ0dHaGhhajBSMFNURnFnRFVBeFpiYk5JQ0xITVZBTTlXSGdsTXBGdVZlS21ndUlCS1dkdW5WdllqXG5BUkpOY1owWS9Ra2VlK09ZODF4RHJ6aDdlZWdxeGhUV3Q0UlVhUlM2YVV4YWdNa1IzL0R1ZERwK2l6UCtyRTUvXG5QaUgvQ1pkcmVJaDZ6T2dMdHowemFucFpUN3hSQmN0YVZHaENDeUpYVlR4SU5PSmRmbE91TXp4YmlpRXpDelJTXG5aMHBVQ29pVjBOd3lhaC9JV0hFMjJUZzhoUTNWWWUrWXNFZ20wOHV2Zm56Q25aVU96WjZRQnBLWHVMY2R6d2VzXG53aHI2UXhiTFhoQndYTVpMa0xocTJVaFhkUW9BdDhSNnh5MkljMUwvUk5xRlpGL3RCY09rb0tyRDl5UHhCZThWXG5pUmVwOFJyL0FnTUJBQUVDZ2dFQVBmUTRMK1hOMVV3cExieDlHNnBVS2J6WXNTNDBTOEFySm5lcTM0U0xsS1k0XG4zSStZd1RKczVYUEZrOGd6L0hEQVJGMzlKQk56STRjS3Q4RlNJM2NHMTI2enI2TGJ5NFEwU2tFNjJHT1p4aEo2XG5IdDhOMlpXTGZxcUNXaUFXRlpWckdTME9pMjVHempPSmFHNVZzZGxvcWlrNFFxTkljRkM3c0NsVkxkcGxraW4xXG5VNS9FV3E3UlhaZ2toMVlJUDNPM2VmbzBmdFRuQ242cmhVVkFOemNyUkV4MGxpSTZQeWVTSnZOMmVaMUsrTC9yXG52WFIveXk4YTNBUDc0OFNNL05UQjlSUkpHSjNOTDhIeWJNVnNUSk1ZY3dibENjd0VGNjZZOWxBMzVtNHUzUnRxXG5pUUhtTmQwSVFRblp5b3k1eWpDVHRrdVliNExmRU9WWUZwVk9aRWF2Q1FLQmdRRGZWb1QzK0NKQkhhR0IyMTRqXG5FbjgyVjE3SkdlYnBmMHViZU01dmxzQnZiQ3cvWnBKd2FOL3JkWXh1ZFNMSEFVM2tPYjRIcXFtRWFLV2x2bm9yXG5HRVc0V01POFBJTUlxY3I0WGZCbW82L0FrdmIvMk0vQ1k0VnBGVWJnNXdObHdNUFZrSHArcnExTUc0RTNtTkdyXG5jd00xREZPOHR1MVY3OEl4Rjc3bmlXT3BTUUtCZ1FEUWs1bEpjcWRVTnBxeTRGR0JpODZsOUxrbW5JYmdGRWpWXG4zb0VlQXREL0hwN3E0cWNaU3Jmc2ZwS0lYQkJFZjdzZnY0V3YzSkV3aXJkMGRoWnQ3eGx2WkthV0V5dzFCZ3FFXG5WWjViVmw3SGZ4RFo1SkxJR2c3WS9obkNNdzZ0enlNS0RLbGJ2K2U0cmFVOE9jN3Fuam5EL3pQelp0eVZYeXZQXG5WdFQ5Y0dXcUJ3S0JnQ1RtV3BuTnVYc2d0aXFLWUFkVGF3bERiam16dWQ4aVZRK2U2aHl0djZXcExiWmI4QjM2XG5kZVNsUzBxRXQvWkJWT2s4VVJWYk1SN2JXMk1ERVpSRk1sKys2cmdURjJNN0NubjJxMTJKOWVQUFpGZnBMb3BqXG5md3RacEpoTUV0ZWcvYm92WU0zRmRvUjRsdVRrcnQrdzVQckN5cU1IWmpjb29lRVNsMHdSK3hGaEFvR0FFSUxlXG5xNVhMOFRxeFhGK3RsQmZCTUJvRFhpOGQ3amNzM09GVVQwL2h3NUJrL0NzZEZ6OTI5OTI5TTlZMTQzWkpGZVpQXG5hdkNHL2RTOVdSYlFCN3ZadzV1d0pFazRRcXBMd3RZVVJ4NjdPZ2V4Q2M2eVgrelFLQlZWYks0bUIxUlNiY1U2XG4wdXlLSjNCM21DRHJaRjlLSzEzVWV2dzFrZzJLcGtzMWFTcXdvWmtDZ1lBMTJLNkVBbm9rOEtKZXUwOThVdGU1XG5McnY4SzdSNURXRWYzMG5iUzBjSkpsa2pCbDVHZ0dMTGh4QW5saTJTOGpnUk9CWG16QWpVRXNON0RUQnoxMlNrXG5aZEJoYnhTeEJpalZORGM5TW15Z2N2MWZWNzJaSFp6K1A3R2RTeHhvT2o0bFNqWi9ucU5PaitMZmFYRDZmcCtRXG5pdTVnOEd3a2hMNlFqam1Fa2t6ZTd3PT1cbi0tLS0tRU5EIFBSSVZBVEUgS0VZLS0tLS1cbiIsICAiY2xpZW50X2VtYWlsIjogImZpcmViYXNlLWFkbWluc2RrLWR0dW82QGJvY2RlbW9mYi5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsICAiY2xpZW50X2lkIjogIjExNDQ4OTQ5MDcyMTI1OTIzNDc5MCIsICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLCAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9maXJlYmFzZS1hZG1pbnNkay1kdHVvNiU0MGJvY2RlbW9mYi5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSJ9";

    String CACHE_NAME = "monsupercache";

    Logger LOG = LoggerFactory.getLogger(FireBaseAccount.class);

    @Bean
    public DatabaseReference firebaseDatabase() {
        DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
        return firebase;
    }

    @PostConstruct
    void init() throws TechnicalException {
        try {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(firebaseKey()))
                    .setDatabaseUrl("https://bocdemofb.firebaseio.com").build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (FileNotFoundException e) {
            TechnicalException.throwTechnicalException("key file not found", e);
        } catch (IOException e) {
            TechnicalException.throwTechnicalException("io exception", e);
        }
    }

    @Bean
    public InputStream firebaseKey() throws IOException {
        byte[] decode = Base64.getDecoder().decode(KEY_FILE);
        return new ByteArrayInputStream(decode);

    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

}
