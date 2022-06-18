#include "request/error.hh"

#include "request/types.hh"

namespace http::error
{
    Response bad_request()
    {
        return Response(STATUS_CODE::BAD_REQUEST);
    }

    Response unauthorized(const Request&, shared_vhost)
    {
        return Response(STATUS_CODE::UNAUTHORIZED);
    }

    Response forbidden(const Request& request)
    {
        return Response(request, STATUS_CODE::FORBIDDEN);
    }

    Response not_found(const Request& request)
    {
        return Response(request, STATUS_CODE::NOT_FOUND);
    }

    Response method_not_allowed(const Request& request)
    {
        return Response(request, STATUS_CODE::METHOD_NOT_ALLOWED);
    }

    Response request_timeout(const std::string&)
    {
        return Response(STATUS_CODE::REQUEST_TIMEOUT);
    }

    Response proxy_authentication_required(const Request&, shared_vhost)
    {
        return Response(STATUS_CODE::PROXY_AUTHENTICATION_REQUIRED);
    }

    Response payload_too_large()
    {
        return Response(STATUS_CODE::PAYLOAD_TOO_LARGE);
    }

    Response uri_too_long()
    {
        return Response(STATUS_CODE::URI_TOO_LONG);
    }

    Response upgrade_required(const Request& request)
    {
        return Response(request, STATUS_CODE::UPGRADE_REQUIRED);
    }

    Response header_fields_too_large()
    {
        return Response(STATUS_CODE::HEADER_FIELDS_TOO_LARGE);
    }

    Response internal_server_error(const Request& request)
    {
        return Response(request, STATUS_CODE::INTERNAL_SERVER_ERROR);
    }

    Response not_implemented(const Request& request)
    {
        return Response(request, STATUS_CODE::NOT_IMPLEMENTED);
    }

    Response bad_gateway(const Request& request)
    {
        return Response(request, STATUS_CODE::BAD_GATEWAY);
    }

    Response service_unavailable(const Request& request)
    {
        return Response(request, STATUS_CODE::SERVICE_UNAVAILABLE);
    }

    Response gateway_timeout()
    {
        return Response{ STATUS_CODE::GATEWAY_TIMEOUT };
    }

    Response http_version_not_supported(const Request& request)
    {
        return Response(request, STATUS_CODE::HTTP_VERSION_NOT_SUPPORTED);
    }

    Response conflict(const Request& request)
    {
        return Response(request, STATUS_CODE::CONFLICT);
    }
} // namespace http::error
