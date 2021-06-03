package com.okta.examples.apiamhooks.controller;

import com.okta.examples.apiamhooks.model.Beer;
import com.okta.examples.apiamhooks.model.Person;
import com.okta.examples.apiamhooks.model.hooks.IDTokenPatchResponse;
import com.okta.examples.apiamhooks.model.hooks.TokenHookRequest;
import com.okta.examples.apiamhooks.model.hooks.TokenHookResponse;
import com.okta.examples.apiamhooks.model.hooks.TokenPatchResponse;
import com.okta.examples.apiamhooks.repository.PersonRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hooks")
public class HooksController {

    private PersonRepository personRepository;

    public HooksController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/apiam")
    public TokenHookResponse apiam(@RequestBody TokenHookRequest request) {
        String login = request.getData().getContext().getUser().getProfile().getLogin();
        Person person = personRepository.findByEmail(login);

        TokenHookResponse response = new TokenHookResponse();
        if (person != null) {
            IDTokenPatchResponse idTokenPatchResponse = new IDTokenPatchResponse();
            idTokenPatchResponse.getValue().add(
                new TokenPatchResponse.Value(
                    "add", "/claims/beers", transformBeers(person.getFavoriteBeers())
                )
            );
            response.getCommands().add(idTokenPatchResponse);
        }

        patchClientIpAddress(request, response);
        patchCustomerId(request, response);
        
        return response;
    }

    private void patchClientIpAddress(TokenHookRequest request, TokenHookResponse response) {
        String ipAddress = request.getData().getContext().getRequest().getIpAddress();
        if (ipAddress != null) {
            IDTokenPatchResponse idTokenPatchResponse = new IDTokenPatchResponse();
            idTokenPatchResponse.getValue().add(
                new TokenPatchResponse.Value(
                    "add", "/claims/client_ip_address", ipAddress
                )
            );
            response.getCommands().add(idTokenPatchResponse);
        }
    }

    private void patchCustomerId(TokenHookRequest request, TokenHookResponse response) {
        String url = request.getData().getContext().getRequest().getUrl().getValue();
        if (url != null) {
            String[] pairs = url.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = pair.substring(0, idx);
                if ("customer_id".equals(key)) {
                    String customerId = pair.substring(idx + 1);

                    IDTokenPatchResponse idTokenPatchResponse = new IDTokenPatchResponse();
            idTokenPatchResponse.getValue().add(
                new TokenPatchResponse.Value(
                    "add", "/claims/customer_id", customerId
                )
            );
            response.getCommands().add(idTokenPatchResponse);

                }
            }
            
        }
    }


    private List<String> transformBeers(List<Beer> beers) {
        return beers.stream().map(Beer::getName).collect(Collectors.toList());
    }
}
