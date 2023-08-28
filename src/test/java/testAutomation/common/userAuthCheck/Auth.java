package testAutomation.common.userAuthCheck;

import org.apache.http.Header;

public interface Auth {
    String getAccessToken();
    Boolean authenticate();
    Header getHeader();
    Object getPayload();
    String getClientId();

    Object getUserDetails();

}
