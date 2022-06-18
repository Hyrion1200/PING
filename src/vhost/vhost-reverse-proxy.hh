/**
 * \file vhost/vhost-reverse-proxy.hh
 * \brief VhostReverseProxy declaration.
 */

#pragma once

#include "config/config.hh"
#include "misc/addrinfo/addrinfo-iterator.hh"
#include "misc/addrinfo/addrinfo.hh"
#include "request/request.hh"
#include "vhost/connection.hh"
#include "vhost/vhost.hh"

namespace http
{
    /**
     * \class VHostReverseProxy
     * \brief VHost in charge of forwarding the Request to the upstream/backend
     * service.
     */
    class VHostReverseProxy : public VHost
    {
    public:
        friend class VHostFactory;
        virtual ~VHostReverseProxy() = default;

    private:
        /**
         * \brief Constructor called by the factory.
         *
         * \param config VHostConfig virtual host configuration.
         */
        explicit VHostReverseProxy(const VHostConfig&);

    public:
        /**
         * \brief Send request to the upstream.
         *
         * \param req Request.
         * \param conn std::shared_ptr<Connection>.
         */
        void respond(Request&, shared_connection) final;
    };

} // namespace http
