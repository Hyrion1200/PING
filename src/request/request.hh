/**
 * \file request/request.hh
 * \brief Request declaration.
 */

#pragma once

namespace http
{
    /**
     * \struct Request
     * \brief Value object representing a request.
     */
    struct Request
    {
    public:
        Request() = default;
        Request(const Request&) = default;
        Request& operator=(const Request&) = default;
        Request(Request&&) = default;
        Request& operator=(Request&&) = default;
        ~Request() = default;
    };
} // namespace http
