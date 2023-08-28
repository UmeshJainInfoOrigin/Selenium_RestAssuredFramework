package testAutomation.api.apiAssertsCheck;

public class APIAsserts {
    public Number isOk(){
        //“OK” success code, for GET, HEAD, and some PATCH requests.
        return 200;
    }

    public Number isCreated(){
        //The server has fulfilled the browser’s request, and as a result, has created a new resource.
        return 201;
    }

    public Number isAccepted(){
        //The server has accepted your browser’s request but is still processing it. The request ultimately may or may not result in a completed response.
        return 202;
    }

    public Number isNonAuthoritativeInformation(){
        // This status code may appear when a proxy is in use. It means that the proxy server received a 200 “Everything is OK” status code from the origin server, but has modified the response before passing it on to your browser.
        return 203;
    }

    public Number hasNoContent(){
        //This code means that the server has successfully processed the request, but is not going to return any content.
        return 204;
    }

    public Number badRequest(){
        //“Bad Request.” The server can’t return a response due to an error on the client’s end. See our guide for resolving this error.
        return 400;
    }
    public Number unauthorized(){
        //“Unauthorized” or “Authorization Required.” This is returned by the server when the target resource lacks valid authentication credentials. You might see this if you’ve set up basic HTTP authentication using htpasswd.
        return 401;
    }

    public Number accessForbidden(){
        //“Access to that resource is forbidden.” This code is returned when a user attempts to access something that they don’t have permission to view. For example, trying to reach password-protected content without logging in might produce a 403 error.
        return 403;
    }

    public Number resourceNotFound(){
        //“The requested resource was not found.” This is the most common error message of them all. This code means that the requested resource does not exist, and the server does not know if it ever existed.
        return 404;
    }

    public Number methodNotAllowed(){
        //“Method not allowed.” This is generated when the hosting server (origin server) supports the method received, but the target resource doesn’t.
        return 405;
    }
}
