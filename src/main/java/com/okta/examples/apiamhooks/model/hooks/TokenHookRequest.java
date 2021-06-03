package com.okta.examples.apiamhooks.model.hooks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenHookRequest {

    private String source;
    private String eventType;
    private Data data = new Data();

    @lombok.Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        private Context context = new Context();

        @lombok.Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Context {

            private User user = new User();
            private Request request = new Request();

            @lombok.Data
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class User {

                private String id;
                private Profile profile = new Profile();

                @lombok.Data
                @NoArgsConstructor
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Profile {

                    private String login;
                    private String firstName;
                    private String lastName;
                }
            }

            @lombok.Data
            @NoArgsConstructor
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Request {

                private String id;
                private Url url = new Url();
                private String ipAddress;


                @lombok.Data
                @NoArgsConstructor
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Url {

                    private String value;
                }
            }
        }
    }
}
