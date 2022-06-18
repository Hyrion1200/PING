/**
 * \file vhost/dispatcher.hh
 * \brief Dispatcher declaration.
 */

#pragma once

#include "connection.hh"
#include "request/request.hh"
#include "vhost.hh"

namespace http
{
    /**
     * \class Dispatcher
     * \brief Instance in charge of dispatching requests between vhosts.
     */
    class Dispatcher
    {
    public:
        Dispatcher() = default;
        Dispatcher(const Dispatcher&) = delete;
        Dispatcher& operator=(const Dispatcher&) = delete;
        Dispatcher(Dispatcher&&) = delete;
        Dispatcher& operator=(Dispatcher&&) = delete;

        void dispatch(Request&, shared_connection);
        void add_vhost(shared_vhost);
        inline const std::vector<shared_vhost>& get_vhosts() const noexcept
        {
            return vhosts_;
        }

    private:
        std::vector<shared_vhost> vhosts_;
    };

    /**
     * \brief Service object.
     */
    extern Dispatcher dispatcher;
} // namespace http
