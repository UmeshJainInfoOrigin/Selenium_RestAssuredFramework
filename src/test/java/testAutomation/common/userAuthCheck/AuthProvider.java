package testAutomation.common.userAuthCheck;

import testAutomation.common.ReadProperties;

public class AuthProvider {
    public Auth getAuthProvider() {
        switch (ReadProperties.getInstance().getValue("AuthProvider")) {
            case "Okta": {
                return new OAuthProvider();
            }

            case "OAuth": {
               return new OktaProvider();
            }
            default:
                throw new IllegalStateException("Unexpected value: " + ReadProperties.getInstance().getValue("AuthProvider"));
        }
    }

}
