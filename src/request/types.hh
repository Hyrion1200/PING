/**
 * \file request/types.hh
 * \brief STATUS_CODE declaration.
 */

#pragma once

#include <cstdint>
#include <stdexcept>
#include <utility>

namespace http
{
    constexpr auto http_crlf = "\r\n";

    enum STATUS_CODE : uint16_t
    {
        OK = 200,
        CREATED = 201,
        BAD_REQUEST = 400,
        UNAUTHORIZED = 401,
        FORBIDDEN = 403,
        NOT_FOUND = 404,
        METHOD_NOT_ALLOWED = 405,
        PROXY_AUTHENTICATION_REQUIRED = 407,
        REQUEST_TIMEOUT = 408,
        CONFLICT = 409,
        PAYLOAD_TOO_LARGE = 413,
        URI_TOO_LONG = 414,
        UPGRADE_REQUIRED = 426,
        HEADER_FIELDS_TOO_LARGE = 431,
        INTERNAL_SERVER_ERROR = 500,
        NOT_IMPLEMENTED = 501,
        BAD_GATEWAY = 502,
        SERVICE_UNAVAILABLE = 503,
        GATEWAY_TIMEOUT = 504,
        HTTP_VERSION_NOT_SUPPORTED = 505
    };

    /**
     * \brief Associate a status code to its message.
     *
     * \param code STATUS_CODE http status code.
     * \return pair associating a status code to its message.
     */
    inline constexpr std::pair<STATUS_CODE, decltype(http_crlf)>
    statusCode(STATUS_CODE code)
    {
        switch (code)
        {
        case OK:
            return { OK, "OK" };
        case BAD_REQUEST:
            return { BAD_REQUEST, "Bad Request" };
        case UNAUTHORIZED:
            return { UNAUTHORIZED, "Unauthorized" };
        case FORBIDDEN:
            return { FORBIDDEN, "Forbidden" };
        case NOT_FOUND:
            return { NOT_FOUND, "Not Found" };
        case METHOD_NOT_ALLOWED:
            return { METHOD_NOT_ALLOWED, "Method Not Allowed" };
        case PROXY_AUTHENTICATION_REQUIRED:
            return { PROXY_AUTHENTICATION_REQUIRED,
                     "Proxy Authentication Required" };
        case REQUEST_TIMEOUT:
            return { REQUEST_TIMEOUT, "Request Timeout" };
        case PAYLOAD_TOO_LARGE:
            return { PAYLOAD_TOO_LARGE, "Payload Too Large" };
        case URI_TOO_LONG:
            return { URI_TOO_LONG, "URI Too Long" };
        case UPGRADE_REQUIRED:
            return { UPGRADE_REQUIRED, "Upgrade Required" };
        case HEADER_FIELDS_TOO_LARGE:
            return { HEADER_FIELDS_TOO_LARGE,
                     "Request Header Fields Too Large" };
        case INTERNAL_SERVER_ERROR:
            return { INTERNAL_SERVER_ERROR, "Internal Server Error" };
        case NOT_IMPLEMENTED:
            return { NOT_IMPLEMENTED, "Not Implemented" };
        case BAD_GATEWAY:
            return { BAD_GATEWAY, "Bad Gateway" };
        case SERVICE_UNAVAILABLE:
            return { SERVICE_UNAVAILABLE, "Service Unavailable" };
        case GATEWAY_TIMEOUT:
            return { GATEWAY_TIMEOUT, "Gateway Timeout" };
        case HTTP_VERSION_NOT_SUPPORTED:
            return { HTTP_VERSION_NOT_SUPPORTED, "HTTP Version Not Supported" };
        case CONFLICT:
            return { CONFLICT, "Conflict" };
        case CREATED:
            return { CREATED, "Created" };
        default:
            throw std::logic_error("unknown status_code");
        }
    }
} // namespace http
