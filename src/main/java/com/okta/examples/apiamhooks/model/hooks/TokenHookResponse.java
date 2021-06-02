package com.okta.examples.apiamhooks.model.hooks;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TokenHookResponse {

    private List<TokenPatchResponse> commands = new ArrayList<>();
}
