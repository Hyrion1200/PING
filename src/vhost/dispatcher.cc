#include "dispatcher.hh"

namespace http
{

    void Dispatcher::dispatch(Request&, shared_connection)
    {
        /* FIXME */
    }

    void Dispatcher::add_vhost(shared_vhost vhost)
    {
        vhosts_.push_back(vhost);
    }

} // namespace http
