/**
 * \file request/error.hh
 * \brief http::error::<error> declarations.
 */

#pragma once

#include "request/response.hh"
#include "request/types.hh"
#include "vhost/vhost.hh"

namespace http::error
{
    /**
     * \brief Create an error response from a request.
     */
    Response bad_request();
    Response unauthorized(const Request&, shared_vhost vhost);
    Response forbidden(const Request&);
    Response not_found(const Request&);
    Response method_not_allowed(const Request&);
    Response proxy_authentication_required(const Request& request,
                                           shared_vhost vhost);
    Response request_timeout(const std::string& reason);
    Response payload_too_large();
    Response uri_too_long();
    Response upgrade_required(const Request&);
    Response header_fields_too_large();
    Response internal_server_error(const Request&);
    Response not_implemented(const Request&);
    Response bad_gateway(const Request&);
    Response service_unavailable(const Request&);
    Response gateway_timeout();
    Response http_version_not_supported(const Request&);
    Response conflict(const Request&);
} // namespace http::error
