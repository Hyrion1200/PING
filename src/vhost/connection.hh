/**
 * \file vhost/connection.hh
 * \brief Connection declaration.
 */

#pragma once

#include <memory>

#include "request/types.hh"
#include "socket/socket.hh"

namespace http
{
    class VHost;

    /**
     * \struct Connection
     * \brief Value object representing a connection.
     *
     * We need to keep track of the state of each request while it has not
     * been fully processed.
     */
    struct Connection
    {
    public:
        Connection() = default;
        Connection(const Connection& con) = default;
        Connection& operator=(const Connection&) = default;
        Connection(Connection&&) = default;
        Connection& operator=(Connection&&) = default;
        ~Connection() = default;
    };
    using shared_connection = std::shared_ptr<Connection>;
} // namespace http
